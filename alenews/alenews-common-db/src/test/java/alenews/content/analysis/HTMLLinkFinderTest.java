package alenews.content.analysis;

import alenews.content.acquisition.Content;
import alenews.content.db.ContentService;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HTMLLinkFinderTest {

    @Test
    public void testFindOutgoingLinks() throws MalformedURLException {
        Content content = new Content() ;
        content.setTitle("Pair Programming Economics");
        content.setSourceLocation(new URL(String.format("file://%s/%s", System.getProperty("user.dir"), "src/test/resources/trustartist.com.2015-01-27.html")));

        ContentService contentService = mock(ContentService.class) ;
        when(contentService.hasContentByLocation("http://wingman-sw.com/about")).thenReturn(true) ;

        HTMLLinkFinder linkFinder = new HTMLLinkFinder(contentService) ;
        content = linkFinder.findDiscussionLinks(content) ;

        assertThat(content.getDiscussionLinks(), hasItem(new URL("http://wingman-sw.com/about"))) ;
    }
}