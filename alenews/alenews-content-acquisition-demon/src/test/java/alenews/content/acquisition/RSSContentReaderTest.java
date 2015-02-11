package alenews.content.acquisition;

import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndContentImpl;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndEntryImpl;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;

public class RSSContentReaderTest {

    @Test
    public void returnFirstItemFromFeed() throws MalformedURLException, ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss") ;

        RSSContentReader reader = new RSSContentReader() ;
        Content content = reader.read(new URL(String.format("file://%s/%s", System.getProperty("user.dir"), "src/test/resources/content.rss"))) ;

        assertThat(content, is(notNullValue())) ;
        assertThat(content.getDescription(), is(notNullValue())) ;
        assertThat(content.getSourceLocation().toExternalForm(), is(equalTo("http://trustartist.com/2015/01/27/pair-programming-economics/"))) ;
        assertThat(content.getTitle(), is(equalTo("Pair Programming Economics"))) ;
        assertThat(content.getPublishedDate(), is(dateFormat.parse("2015-01-26 23:25:35"))) ;
        assertThat(content.getAuthor(), is(equalTo("Olaf Lewitz"))) ;
        assertThat(content.getCategories(), hasItems("Agile with a Purpose", "Conference", "Programming", "pair programming", "TDD")) ;
    }

    @Test
    public void removeHTMLFromDescription() throws Exception {
        RSSContentReader reader = new RSSContentReader() ;

        assertThat(reader.cleanUpDescription("<p>This is some HTML</p>"), is(equalTo("This is some HTML"))) ;
    }
}
