package net.caimito.ale_news.content.acquisition;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

@ComponentScan
public class Application {
    protected static Log logger = LogFactory.getLog(Application.class) ;

    public static void main(String[] args) {
        logger.info("Content Acquisition Demon starting");

        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        ContentAcquisitionDemon contentAcquisitionDemon = context.getBean(ContentAcquisitionDemon.class) ;
        contentAcquisitionDemon.startAcquisition(); ;
    }
}
