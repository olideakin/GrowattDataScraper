package org.ramekin;

import org.ramekin.scraper.GrowattDataScraper;

public class GrowattDataScraperApplication {
    public static void main(String[] args) throws Exception {

        final String uri = args[0];
        final String username = args[1];
        final String passwordMD5 = args[2];

        GrowattDataScraper growattDataScraper = new GrowattDataScraper(uri);
        growattDataScraper.scrape(username, passwordMD5);
    }
}