package alenews.content.analysis;

import alenews.content.db.ContentService;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DiscussionLinkFinderTest {

    @Test
    public void testFindOutgoingLinks() throws MalformedURLException {
        final String sourceTitle = "Pair Programming Economics" ;
        final URL sourceLocation = new URL(String.format("file://%s/%s", System.getProperty("user.dir"), "src/test/resources/trustartist.com.2015-01-27.html"));

        ContentService contentService = mock(ContentService.class) ;
        when(contentService.hasContentByLocation("http://wingman-sw.com/about")).thenReturn(true) ;

        DiscussionLinkFinder linkFinder = new DiscussionLinkFinder(contentService) ;
        List<URL> discussionLinks = linkFinder.findDiscussionLinks(sourceLocation, sourceTitle) ;

        assertThat(discussionLinks, hasItem(new URL("http://wingman-sw.com/about"))) ;
    }

}