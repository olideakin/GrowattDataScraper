package org.ramekin.scraper;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class GrowattDataScraper {

    private final String scraperURL;

    public GrowattDataScraper(final String scraperURL) {
        this.scraperURL = scraperURL + (scraperURL.endsWith("/") ? "" : "/");
    }

    public void scrape(final String username, final String password) {
        login(username, password);
    }

    private void login(final String username, final String password) {
        final String loginPage = scraperURL + "newTwoLoginAPI.do";

        HttpClient client = HttpClient.newHttpClient();

        Map<String, String> formData = new HashMap<>();
        formData.put("userName", username);
        formData.put("password", password);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(loginPage))
                .headers("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(getFormDataAsString(formData)))
                .build();

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (final Exception e) {
            e.printStackTrace();
            return;
        }

        System.out.println(response.body());
    }

    private static String getFormDataAsString(Map<String, String> formData) {
        StringBuilder formBodyBuilder = new StringBuilder();
        for (Map.Entry<String, String> singleEntry : formData.entrySet()) {
            if (formBodyBuilder.length() > 0) {
                formBodyBuilder.append("&");
            }
            formBodyBuilder.append(URLEncoder.encode(singleEntry.getKey(), StandardCharsets.UTF_8));
            formBodyBuilder.append("=");
            formBodyBuilder.append(URLEncoder.encode(singleEntry.getValue(), StandardCharsets.UTF_8));
        }
        return formBodyBuilder.toString();
    }
}
