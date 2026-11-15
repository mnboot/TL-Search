package scraper;

import com.mnboot.scraper.HtmlDatabaseInMemory;
import com.mnboot.scraper.HtmlDocument;
import com.mnboot.scraper.Scraper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ScraperTest {

    private Scraper scraper;

    @BeforeEach
    public void beforeEach(){
        scraper = new Scraper(new HtmlDatabaseInMemory());
    }

    @Test
    public void main()  {

        try  {
            HtmlDocument htmlFromURL = scraper.getHtmlFromURL("https://en.wikipedia.org/wiki/Java_(programming_language)");
            System.out.println(htmlFromURL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
