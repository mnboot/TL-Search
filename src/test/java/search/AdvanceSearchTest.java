package search;

import com.mnboot.scraper.HtmlDatabase;
import com.mnboot.scraper.HtmlDatabaseInMemory;
import com.mnboot.scraper.HtmlDocument;
import com.mnboot.scraper.Scraper;
import com.mnboot.search.AdvanceSearch.Search;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.PriorityQueue;

public class AdvanceSearchTest {
    @Test
    public void firstTest() throws IOException {
        HtmlDatabase Db = new HtmlDatabaseInMemory();
        Scraper scraper = new Scraper(Db);
        scraper.getHtmlFromURL("https://en.wikipedia.org/wiki/Java_(programming_language)");
        scraper.getHtmlFromURL("https://en.wikipedia.org/wiki/Jakarta_EE");
        scraper.getHtmlFromURL("https://en.wikipedia.org/wiki/Kuwait");
        Search search = new Search(scraper);

        PriorityQueue<HtmlDocument> result = search.search("what is java", 3);
        System.out.println(result);

    }
}
