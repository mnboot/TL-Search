package com.mnboot.scraper;


import lombok.Getter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

//TODO: complete Scraping
public class Scraper {


    @Getter
    private HtmlDatabase HtmlDb;

    /**
     * Create instance of Scraper
     * @param HtmlDb type of HTML database usually {@code HtmlDatabaseInMemoryImpl}
     * @see HtmlDatabaseInMemory
     */
    public Scraper(HtmlDatabase HtmlDb){
        this.HtmlDb = HtmlDb;

    }

    public HtmlDocument getHtmlFromURL(String url) throws IOException {
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("TL-Scraper")
                    .get();

            HtmlDocument htmlDocument = HtmlDocument.builder()
                    .url(url)
                    .title(doc.title())
                    .body(doc.body().text())
                    .build();

            HtmlDb.insert(htmlDocument);
            return htmlDocument;

        } catch (IllegalArgumentException e) {
            System.err.println("Invalid URL: " + url);
            return null;

        } catch (IOException e) {
            System.err.println("Connection error: " + e.getMessage());
            return null;
        }


    }


}
