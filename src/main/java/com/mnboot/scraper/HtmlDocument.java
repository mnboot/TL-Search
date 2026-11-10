package com.mnboot.scraper;

import lombok.Data;

/**
 * single data class that stores HTML + URL of a single web page
 * @implSpec for {@code url} it must have Domain.
 *          Paths and Fragments are optional. However, the rest in not allowed (e.g., example.com/foo/bar#baz ALLOWED,
 *          <strong>https://</strong>example.com:<Strong>8080</Strong><Strong>?utm_source=chatgpt</Strong> NOT ALLOWED)
 *
 */
@Data
public class HtmlDocument {
    // Full URL, including domain
    private String url;   // the most priority
    private String title; // the second most
    private String body;  // the least most

    //TODO: override setUrl to validate the url
    // void setURL(String url){}

}
