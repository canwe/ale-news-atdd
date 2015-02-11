package alenews.content.acquisition;

import alenews.content.analysis.LinkFinder;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ContentAcquisitionDemonTest {

    @Test
    public void startAcquisition() throws MalformedURLException {
        List<URL> sourceList = Arrays.asList(new URL("http://source")) ;

        ConfigurationService configurationService = mock(ConfigurationService.class) ;
        when(configurationService.listSourceLocationsByType(ContentType.RSS)).thenReturn(sourceList) ;

        ContentMessenger messenger = mock(ContentMessenger.class) ;
        LinkFinder linkFinder = mock(LinkFinder.class) ;
        ContentReaderSelector contentReaderSelector = new FakeContentReaderSelector() ;

        ContentAcquisitionDemon demon = new ContentAcquisitionDemon(configurationService, messenger,
                contentReaderSelector, linkFinder) ;
        demon.startAcquisition();
    }
}
