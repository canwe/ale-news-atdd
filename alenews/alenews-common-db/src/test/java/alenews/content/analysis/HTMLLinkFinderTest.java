package alenews.content.analysis;

import alenews.content.db.ContentService;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HTMLLinkFinderTest {

    @Test
    public void emptyTitle() throws MalformedURLException {
        final String sourceTitle = "" ;
        final URL sourceLocation = new URL(String.format("file://%s/%s", System.getProperty("user.dir"), "src/test/resources/trustartist.com.2015-01-27.html"));

        HTMLLinkFinder linkFinder = new HTMLLinkFinder() ;
        List<URL> discussionLinks = linkFinder.findArticleOutboundLinks(sourceLocation, sourceTitle) ;

        assertThat(discussionLinks, is(empty())) ;
    }

}
