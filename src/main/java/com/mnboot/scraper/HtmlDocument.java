package com.mnboot.scraper;

import lombok.Data;

/**
 * single data class that stores HTML + URL of a single web page
 */
@Data
public class HtmlDocument {
    // Full URL, including domain
    private String url;   // the most priority
    private String title; // the second most
    private String body;  // the least most
}
