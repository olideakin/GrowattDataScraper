package org.ramekin.scraper;

import com.amazonaws.regions.Regions;
import com.google.gson.Gson;
import org.ramekin.model.Login;
import org.ramekin.model.MixDetail;
import org.ramekin.model.Plant;
import org.ramekin.model.PlantList;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.CookieManager;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class GrowattDataScraper {

    private static final String S3_BUCKET_NAME = "growatt-scraper-bucket";

    // On some detail APIS this enum is used to indicate the timespan desired
    private enum TIMESPAN { HOUR, DAY, MONTH };

    private final String scraperURL;
    private final Gson gson = new Gson();
    private final HttpClient httpClient = HttpClient.newBuilder()
            .cookieHandler(new CookieManager())
            .build();

    public GrowattDataScraper(final String scraperURL) {
        this.scraperURL = scraperURL + (scraperURL.endsWith("/") ? "" : "/");
    }

    public void scrape(final String username, final String password) throws Exception {

        // Set up AWS client and create bucket if it doesn't already exist
        final AWSClient awsClient = new AWSClient(Regions.EU_WEST_1);
        awsClient.createS3Bucket(S3_BUCKET_NAME);

        // Login to the service
        final Login login = login(username, password);
        System.out.println(login);
        if (!login.isSuccess()) {
            throw new RuntimeException("Unsuccessful login attempt");
        }

        // Get the plant list
        final PlantList plantList = getPlantList();
        System.out.println(plantList);
        if (!plantList.isSuccess()) {
            throw new RuntimeException("Unsuccessful attempting to gather plant list information");
        }

        // Get the first plant
        // TODO: Write out any useful plant data
        final Plant plant = getPlant(plantList.getPlantId(0));
        System.out.println(plant);

        // Used to request data for certain date ranges from the Growatt API
        final LocalDate today = LocalDate.now();

        // Check whether any files exist or not, which indicates whether we should backfill history
        LocalDate startDate;
        if (awsClient.isBucketEmpty(S3_BUCKET_NAME)) {
            // Growatt only stores up to 2 previous months of back history from today backwards starting on the first of that month, so request from there
            startDate = today.minus(2, ChronoUnit.MONTHS);
            System.out.println("Backfill is required as the bucket is empty");
        } else if (today.getDayOfMonth() == 1) {
            // Hack to avoid missing data at the roll over of the month. This can be resolved by a more complicated implementation of the below for loop,
            // but this seems like a reasonable and cheap work around for one day a month to make sure data is not missed at the roll over.
            startDate = today.minus(1, ChronoUnit.MONTHS);
            System.out.println("Reading from last month's data to ensure none is lost across the month roll-over");
        } else {
            startDate = today;
            System.out.println("Reading only this month's data");
        }

        /*
         * TODO: Simplistic implementation for now - just start from the 1st of each month that we need to request (either backfilling or just the current month)
         * and write the entire month's data into a file which is then PUT to S3. Keeping this simple for now as the cost of doing this with such a small
         * amount of data and low frequency of requests is very low. A better implementation might use Athena, for example, to just INSERT the newest entries,
         * but that would mean configuring the Glue catalogue that probably isn't worth it for now given the simplicity of this use case. Possibly could download
         * the S3 object, append and then upload again - that will probably be the next revision of this code to reduce wasted Growatt API calls.
         */
        for (LocalDate date = startDate; !date.isAfter(today); date = date.plus(1, ChronoUnit.MONTHS)) {
            // Set up a file to write the metrics to based on the current month under inspection
            final String currentMonth = date.format(DateTimeFormatter.ofPattern("yyyy-MM"));
            System.out.println("Current month under inspection is " + currentMonth);
            final String fileName = currentMonth + ".csv";
            final PrintWriter printWriter = new PrintWriter(new FileWriter(fileName));
            printWriter.println("timestamp,solarOutputKW,exportToGridKW,importFromGridKW,batteryDischargeKW,localConsumptionKW");

            // Get mix system details and write to local file
            final int lastDayInMonthToRequest = date.getMonthValue() < today.getMonthValue() ? date.lengthOfMonth() : today.getDayOfMonth();
            for (int dayOfMonth = 1; dayOfMonth <= lastDayInMonthToRequest; dayOfMonth++) {
                final LocalDate dateToProcess = LocalDate.of(date.getYear(), date.getMonth(), dayOfMonth);
                final String formattedDate = dateToProcess.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                final MixDetail mixDetail = getMixDetail(plant.getDeviceSn(0), plantList.getPlantId(0), TIMESPAN.HOUR, dateToProcess);
                mixDetail.getChartData().entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByKey())
                        .forEach(entry -> printWriter.printf("%s %s,%s,%s,%s,%s,%s\n", formattedDate, entry.getKey(), entry.getValue().getPpv(),
                                entry.getValue().getPacToGrid(), entry.getValue().getPacToUser(), entry.getValue().getPdischarge(), entry.getValue().getSysOut()));
            }
            printWriter.close();

            // Push the local file to S3
            awsClient.writeObjectToBucket(S3_BUCKET_NAME, fileName, fileName);
        }
    }

    private Login login(final String username, final String password) throws IOException, InterruptedException {
        final String loginURL = scraperURL + "newTwoLoginAPI.do";

        Map<String, String> params = new HashMap<>();
        params.put("userName", username);
        params.put("password", password);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(loginURL))
                .headers("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(getParamsAsString(params)))
                .build();

        HttpResponse<String> response;
        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return gson.fromJson(response.body(), Login.class);
    }

    private PlantList getPlantList() throws IOException, InterruptedException {
        final String plantListURL = scraperURL + "PlantListAPI.do";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(plantListURL))
                .GET()
                .build();

        HttpResponse<String> response;
        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return gson.fromJson(response.body(), PlantList.class);
    }

    private Plant getPlant(final String plantId) throws IOException, InterruptedException {
        Map<String, String> params = new HashMap<>();
        params.put("op", "getAllDeviceList");
        params.put("plantId", plantId);
        //params.put("pageNum", "1");
        //params.put("pageSize", "1");

        final String plantListURL = scraperURL + "newTwoPlantAPI.do" + "?" + getParamsAsString(params);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(plantListURL))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), Plant.class);
    }

    private MixDetail getMixDetail(final String mixId, final String plantId, final TIMESPAN timespan, final LocalDate date) throws IOException, InterruptedException {
        Map<String, String> params = new HashMap<>();
        params.put("op", "getEnergyProdAndCons_KW");
        params.put("mixId", mixId);
        params.put("plantId", plantId);
        params.put("type", Integer.toString(timespan.ordinal()));
        params.put("date", date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        final String mixDetailUrl = scraperURL + "newMixApi.do" + "?" + getParamsAsString(params);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(mixDetailUrl))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), MixDetail.class);
    }

    private static String getParamsAsString(Map<String, String> params) {
        StringBuilder paramsBuilder = new StringBuilder();
        for (Map.Entry<String, String> singleEntry : params.entrySet()) {
            if (paramsBuilder.length() > 0) {
                paramsBuilder.append("&");
            }
            paramsBuilder.append(URLEncoder.encode(singleEntry.getKey(), StandardCharsets.UTF_8));
            paramsBuilder.append("=");
            paramsBuilder.append(URLEncoder.encode(singleEntry.getValue(), StandardCharsets.UTF_8));
        }
        return paramsBuilder.toString();
    }
}
