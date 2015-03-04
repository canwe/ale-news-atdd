package alenews.content.db;

import alenews.content.PersistenceContext;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.persistence.EntityManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceContext.class})
@TransactionConfiguration(defaultRollback = false)
public abstract class MyDatabaseTest extends AbstractTransactionalJUnit4SpringContextTests {

    @javax.persistence.PersistenceContext
    protected EntityManager entityManager ;

    @Before
    public void cleanDatabase() {
        deleteFromTables("content_category", "categories", "content_content", "content") ;
    }
}
