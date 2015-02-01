package net.caimito.ale_news.content.acquisition;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;

public class ConfigurationTest {

    /*
        Add source metadata to configuration database
     */
    @Test
    public void addSource() throws MalformedURLException {
        Configuration config = new Configuration() ;

        config.addSource(ContentType.HTML, "http://localhost") ;
        assertThat(config.listSourceLocationsByType(ContentType.HTML), hasItem(new URL("http://localhost"))) ;
    }

    /*
        Find sources by content source type
     */
    @Test
    public void listSourceLocationsByType() throws MalformedURLException {
        Configuration config = new Configuration() ;

        config.addSource(ContentType.HTML, "http://localhost/html") ;

        assertThat(config.listSourceLocationsByType(ContentType.HTML), hasItem(new URL("http://localhost/html"))) ;
    }

    @Test
    public void listOnlyOneSourceType() throws MalformedURLException {
        Configuration config = new Configuration() ;

        config.addSource(ContentType.HTML, "http://localhost/html") ;
        config.addSource(ContentType.RSS, "http://localhost/rss") ;

        assertThat(config.listSourceLocationsByType(ContentType.HTML).size(), is(1)) ;
        assertThat(config.listSourceLocationsByType(ContentType.HTML), hasItem(new URL("http://localhost/html"))) ;
    }

    @Test
    public void noLocationForRequestContentType() {
        Configuration config = new Configuration() ;

        assertThat(config.listSourceLocationsByType(ContentType.HTML), is(empty())) ;
    }

}