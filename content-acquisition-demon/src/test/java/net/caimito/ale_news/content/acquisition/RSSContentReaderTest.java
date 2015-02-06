package net.caimito.ale_news.content.acquisition;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;

public class RSSContentReaderTest {

    @Test
    public void returnFirstItemFromFeed() throws MalformedURLException {
        RSSContentReader reader = new RSSContentReader() ;
        Content content = reader.read(new URL(String.format("file://%s/%s", System.getProperty("user.dir"), "src/test/resources/content.rss"))) ;
        assertThat(content, is(notNullValue())) ;
        assertThat(content.getBodyContentType(), is(ContentType.HTML)) ;
        assertThat(content.getBody(), is(notNullValue())) ;
        assertThat(content.getSourceLocation().toExternalForm(), is(equalTo("http://trustartist.com/2015/01/27/pair-programming-economics/"))) ;
    }
}
