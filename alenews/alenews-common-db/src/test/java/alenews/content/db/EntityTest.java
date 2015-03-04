package alenews.content.db;

import alenews.content.PersistenceContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.persistence.EntityManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class EntityTest extends MyDatabaseTest {

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss Z") ;

    @Autowired
    private ContentRepository contentRepository ;

    @Autowired
    private CategoryRepository categoryRepository ;

    @Test
    public void saveContent() throws Exception {

        ContentEntity contentEntity = new ContentEntity() ;
        contentEntity.setTitle("This is the title");
        contentEntity.setLanguage("en");
        contentEntity.setSourceLocation("http://localhost");
        contentEntity.setPublishedDate(dateFormat.parse("2015-01-10 17:23:49 UTC"));
        contentEntity.setAuthor("Stephan");
        contentEntity.setDescription("This is the description");

        ContentEntity savedEntity = contentRepository.save(contentEntity) ;

        assertThat(savedEntity.id, is(notNullValue())) ;
        assertThat(countRowsInTable("content"), is(1)) ;
    }

    @Test
    public void saveContentWithCategories() throws Exception {
        CategoryEntity categoryEntity = new CategoryEntity() ;
        categoryEntity.setCategoryName("TDD");

        ContentEntity contentEntity = new ContentEntity() ;
        contentEntity.setTitle("This is the title");
        contentEntity.setLanguage("en");
        contentEntity.setSourceLocation("http://localhost");
        contentEntity.setPublishedDate(dateFormat.parse("2015-01-10 17:23:49 UTC"));
        contentEntity.setAuthor("Stephan");
        contentEntity.setDescription("This is the description");
        contentEntity.getCategories().add(categoryEntity) ;
        ContentEntity savedContentEntity = contentRepository.save(contentEntity) ;

        entityManager.flush();

        assertThat(countRowsInTable("content"), is(1)) ;
        assertThat(countRowsInTable("categories"), is(1)) ;
        assertThat(countRowsInTable("content_category"), is(1)) ;
    }

}
