package net.caimito.ale_news.content.analysis;

import net.caimito.ale_news.content.Application;
import net.caimito.ale_news.content.acquisition.Content;
import net.caimito.ale_news.content.acquisition.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ContentServiceTest {

    @Autowired
    private ContentService contentService ;

    @Test
    public void saveReadContent() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss Z") ;

        Content content = new Content() ;
        content.setPublishedDate(dateFormat.parse("2015-01-02 17:23:49 UTC"));
        content.setSourceLocation(new URL("http://source"));
        content.setTitle("Title");
        content.setLanguage("de");
        content.setBody("body");
        content.setAuthor("Stephan");
        content.setBodyContentType(ContentType.HTML);
        content.addOutgoingLink(new URL("http://outgoing1"));
        content.addOutgoingLink(new URL("http://outgoing2"));
        content.addCategory("TDD");
        content.addCategory("Programming");

        contentService.addContent(content);

        assertThat(contentService.findAll(), hasItem(content)) ;
    }
}