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
        String location = String.format("file://%s/content-acquisition-demon/src/test/resources/content.html", System.getProperty("user.dir")) ;

        Content contentExpected = new Content() ;
        contentExpected.setContentType(ContentType.HTML);
        contentExpected.setBody(TestHelper.readFile("content-acquisition-demon/src/test/resources/content.html"));

        ContentReader reader = new HTMLContentReader() ;

        Content contentActual = reader.read(new URL(location)) ;
        
        assertThat(contentActual, is(contentExpected)) ;
    }

}
