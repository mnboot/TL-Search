package com.mnboot.scraper;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

//this is regex
// 1: "^" means match from start
// 2: "(https?://)" means find http:// or https:// (question mark means zero or one so "ab?" either a or ab)
// 3: "?" means if (2:) exists. Btw it is not needed since won't exist if there was or wasn't http
// 4: "(www\\.)" remove www. (double slash to escape the ".")
// 5: "?" same as (3:) but with (4:)
// 6: |[?#] condition if letter is "?" (query string e.g., ep.com/test?filter=asc) OR (because of "|") "#" (fragment e.g., ep.com/blog#introduction)
// 7: (.*) if condition (6:) is truthy, remove every letter afterward ".*" until the end "$"

/**
 * single data class that stores HTML + URL of a single web page
 * @implSpec for {@code url} it must have Domain.
 *          Paths and Fragments are optional. However, the rest in not allowed (e.g., example.com/foo/bar ALLOWED,
 *          <strong>https://</strong>example.com:<Strong>8080</Strong><Strong>?utm_source=chatgpt</Strong> NOT ALLOWED)
 *
 */
@Data
@Builder // this is a cool one
public final class HtmlDocument {

    public static final double URL_SCORE = 4.0; // the most priority
    @Getter
    private String url;

    public static final double TITLE_SCORE = 3.0; // the second most priority
    private String title;

    public static final double BODY_SCORE = 1.0; // the least most priority
    private String body;


    /**
     * validate input to be web URL
     * @param url input to check whether web-compatible URL
     * @return {@code true} valid URL and it is web-compatible protocol (i.e., http and https), {@code false} otherwise
     */
    public static boolean validateHttpURL(String url) {
        if (url == null || url.isBlank()) {
            return false;
        }

        try {
            URI uri = URI.create(url);
            String protocol = uri.getScheme();

            return protocol != null &&
                    (protocol.equalsIgnoreCase("http") ||
                            protocol.equalsIgnoreCase("https")) &&
                    uri.getHost() != null;

        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * remove protocol, "www." subdomain, query string, and fragments
     * @param url URL to be set
     * @throws IllegalArgumentException when input is not a valid URL
     * @see validateHttpURL
     */
    public void setUrl(String url) {
        if (!validateHttpURL(url)) {
            throw new IllegalArgumentException(
                    "URL is not web-compatible"
            );
        }

        //this is regex
        // 1: "^" means match from start
        // 2: "(https?://)" means find http:// or https:// (question mark means zero or one so "ab?" either a or ab)
        // 3: "?" means if (2:) exists. Btw it is not needed since won't exist if there was or wasn't http
        // 4: "(www\\.)" remove www. (double slash to escape the ".")
        // 5: "?" same as (3:) but with (4:)
        // 6: |[?#] condition if letter is "?" (query string e.g., ep.com/test?filter=asc) OR (because of "|") "#" (fragment e.g., ep.com/blog#introduction)
        // 7: (.*) if condition (6:) is truthy, remove every letter afterward ".*" until the end "$"
        String regexPattern =
                "^(https?://)(?:www\\.)?|[?#].*$";

        this.url = url.replaceAll(regexPattern, "");
    }

}
