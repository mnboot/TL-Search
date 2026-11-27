package com.mnboot.scraper;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

//TODO: complete Scraping
public class Scraper {


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
        Document doc = Jsoup.connect(url)
                .userAgent("TL-Scraper") // unnecessary
                .get();

        HtmlDocument htmlDocument = HtmlDocument.builder()
                .url(url)
                .title(doc.title())
                .body(doc.body().text())
                .build();
        HtmlDb.insert(htmlDocument);
        return htmlDocument;
    }

    public List<HtmlDocument> getAllHtmlDocuments(){
        return HtmlDb.getAll();
    }
}
