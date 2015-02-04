package net.caimito.ale_news.content.acquisition;

import net.caimito.TestHelper;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContentReaderTest {

    @Test
    public void readHTML() throws MalformedURLException {
        String relativeLocationDemoFile = "src/test/resources/content.html" ;
        String location = String.format("file://%s/%s", System.getProperty("user.dir"), relativeLocationDemoFile) ;

        Content contentExpected = new Content() ;
        contentExpected.setContentType(ContentType.HTML);
        contentExpected.setBody(TestHelper.readFile(relativeLocationDemoFile));

        ContentReader reader = new HTMLContentReader() ;

        Content contentActual = reader.read(new URL(location)) ;
        
        assertThat(contentActual, is(contentExpected)) ;
    }

}
