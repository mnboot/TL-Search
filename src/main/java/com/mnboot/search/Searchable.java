package com.mnboot.search;

import com.mnboot.scraper.HtmlDocument;

import java.util.List;
import java.util.PriorityQueue;

public interface Searchable {
    /**
     * start search then rank
     * @param query user-inputted search query
     * @param limit the maximum search result
     * @return PriorityQueue of sorted descendingly (biggest score is first, lowest score is last)
     */
    List<HtmlDocument> search(String query, int limit);
}
