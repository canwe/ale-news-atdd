package alenews.content.acquisition;

import alenews.content.analysis.LinkFinder;
import alenews.content.db.ContentService;
import org.junit.Test;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ContentAcquisitionDemonTest {

    @Test
    public void acquireContentFromRSS() throws Exception {
        String rssFeedLocation = String.format("file://%s/%s", System.getProperty("user.dir"),
                "src/test/resources/trustartistOneItem.rss") ;

        ConfigurationService configurationService = mock(ConfigurationService.class) ;
        ContentService contentService = mock(ContentService.class) ;
        ContentFetcherSelector contentFetcherSelector = new ContentFetcherSelector(contentService) ;

        List<URL> rssSourceLocations = Arrays.asList(new URL(rssFeedLocation)) ;
        when(configurationService.listSourceLocationsByType(ContentSourceType.RSS)).thenReturn(rssSourceLocations) ;

        ContentAcquisitionDemon demon = new ContentAcquisitionDemon(configurationService, contentFetcherSelector) ;
        demon.startAcquisition();


        Content expectedContent = new Content() ;
        expectedContent.setTitle("Pair Programming Economics");
        expectedContent.setLanguage("de");
        expectedContent.setAuthor("Olaf Lewitz");
        expectedContent.setSourceLocation(new URL("http://trustartist.com/2015/01/27/pair-programming-economics/"));
        expectedContent.setPublishedDate(getDate("2015-01-26 22:25:35 UTC"));
        expectedContent.addCategory("Agile with a Purpose");
        expectedContent.addCategory("Conference");
        expectedContent.addCategory("Programming");
        expectedContent.addCategory("pair programming");
        expectedContent.addCategory("TDD");
        expectedContent.setDescription("I’m at OOP this week and had the pleasure yesterday to meet James Grenning for the first time. We had an inspiring conversation last night exchanging tips and strategies on how we inspire developers to try XP practices like TDD and pair programming. I really like his approach for TDD. James asks devs at the […]");

        verify(contentService).addContent(expectedContent);
    }

    private Date getDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss Z") ;
        return dateFormat.parse(dateString) ;
    }
}
