package alenews.content.analysis;

import alenews.content.acquisition.Content;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.*;

public class HTMLLinkFinderTest {

    @Test
    public void testFindOutgoingLinks() throws MalformedURLException {
        Content content = new Content() ;
        content.setTitle("Pair Programming Economics");
        content.setSourceLocation(new URL(String.format("file://%s/%s", System.getProperty("user.dir"), "src/test/resources/trustartist.com.2015-01-27.html")));

        HTMLLinkFinder linkFinder = new HTMLLinkFinder() ;
        content = linkFinder.findOutgoingLinks(content) ;

        assertThat(content.getOutgoingLinks(), hasItem(new URL("http://wingman-sw.com/about"))) ;
    }
}