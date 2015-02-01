package net.caimito.ale_news.content.acquisition;

import net.caimito.TestHelper;
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
        String relativeLocationDemoFile = "content-acquisition-demon/src/test/resources/content.html" ;

        Configuration configuration = new Configuration() ;
        configuration.addSource(ContentType.HTML, String.format("file://%s/%s", System.getProperty("user.dir"), relativeLocationDemoFile)) ;

        Content content = new Content() ;
        content.setContentType(ContentType.HTML) ;
        content.setBody(TestHelper.readFile(relativeLocationDemoFile)) ;

        ContentMessenger messenger = mock(ContentMessenger.class) ;

        ContentAcquisitionDemon caDemon = new ContentAcquisitionDemon(configuration, messenger) ;

        caDemon.startAcquisition() ;

        verify(messenger).triggerAnalysis(content) ;
    }

}
