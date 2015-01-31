package net.caimito.ale_news.content.acquisition;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ContentAcquisitionDemonTest {

    @Test
    public void startAcquisition() {
        Configuration configuration = new Configuration() ;
        configuration.addSource(ContentType.HTML, "file://test/resources/content.html") ;

        Content content = new Content() ;
        content.setContentType(ContentType.HTML) ;
        content.setBody(readFile("file://test/resources/content.html")) ;

        ContentMessenger messenger = mock(ContentMessenger.class) ;

        ContentAcquisitionDemon caDemon = new ContentAcquisitionDemon(configuration, messenger) ;

        caDemon.startAcquisition() ;

        verify(messenger).triggerAnalysis(content) ;
    }

    private String readFile(String location) {
        return null ;
    }

}
