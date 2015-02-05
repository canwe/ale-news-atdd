package net.caimito.ale_news.content.acquisition;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

@ComponentScan
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        DatabaseMigration databaseMigration = context.getBean(DatabaseMigration.class) ;
        databaseMigration.migrate() ;

        ContentAcquisitionDemon contentAcquisitionDemon = context.getBean(ContentAcquisitionDemon.class) ;
        contentAcquisitionDemon.startAcquisition(); ;
    }
}
