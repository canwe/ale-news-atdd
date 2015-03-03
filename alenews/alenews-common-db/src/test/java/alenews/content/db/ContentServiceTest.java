package alenews.content.db;

import alenews.content.acquisition.Content;
import alenews.content.PersistenceContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceContext.class})
public class ContentServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private ContentService contentService ;

    @Test
    @Rollback(true)
    public void saveReadContent() throws Exception {
        Content content = getContentFixtureSource10();

        contentService.addContent(content);

        assertThat(contentService.findAll(), hasItem(content)) ;
    }

    @Test
    @Rollback(true)
    public void noDuplicateContent() throws Exception {
        Content content = getContentFixtureSource10();

        contentService.addContent(content);
        contentService.addContent(content);

        assertThat(contentService.findAll(), hasItem(content)) ;
        assertThat(contentService.findAll().size(), is(1)) ;
    }

    @Test
    @Rollback(true)
    public void findSortedByDate() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss Z") ;

        contentService.addContent(getContentFixtureSource10());
        contentService.addContent(getContentFixtureSource25());
        contentService.addContent(getContentFixtureSource20());

        List<Content> contentList = contentService.findAll() ;
        assertThat(contentList.size(), is(3)) ;

        ArrayList<Date> datesActual = new ArrayList<Date>() ;
        for (Content content : contentList) {
            datesActual.add(content.getPublishedDate());
        }

        assertThat(datesActual, contains(dateFormat.parse("2015-01-25 17:23:49 UTC"),
                dateFormat.parse("2015-01-20 17:23:49 UTC"),
                dateFormat.parse("2015-01-10 17:23:49 UTC"))) ;
    }

    @Test
    @Rollback(true)
    public void findAllWithDiscussion() {

        List<Content> contentList = contentService.findAll() ;

    }

    protected Content getContentFixtureSource10() throws ParseException, MalformedURLException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss Z") ;

        Content content = new Content() ;
        content.setPublishedDate(dateFormat.parse("2015-01-10 17:23:49 UTC"));
        content.setSourceLocation(new URL("http://source10"));
        content.setTitle("Title10");
        content.setLanguage("de");
        content.setDescription("body");
        content.setAuthor("Stephan");
        content.addCategory("TDD");
        content.addCategory("Programming");
        return content;
    }

    protected Content getContentFixtureSource20() throws ParseException, MalformedURLException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss Z") ;

        Content content = new Content() ;
        content.setPublishedDate(dateFormat.parse("2015-01-20 17:23:49 UTC"));
        content.setSourceLocation(new URL("http://source20"));
        content.setTitle("Title20");
        content.setLanguage("de");
        content.setDescription("body");
        content.setAuthor("Stephan");
        content.addCategory("TDD");
        content.addCategory("Programming");
        return content;
    }

    protected Content getContentFixtureSource25() throws ParseException, MalformedURLException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss Z") ;

        Content content = new Content() ;
        content.setPublishedDate(dateFormat.parse("2015-01-25 17:23:49 UTC"));
        content.setSourceLocation(new URL("http://source25"));
        content.setTitle("Title25");
        content.setLanguage("de");
        content.setDescription("body");
        content.setAuthor("Stephan");
        content.addCategory("TDD");
        content.addCategory("Programming");
        return content;
    }
}
