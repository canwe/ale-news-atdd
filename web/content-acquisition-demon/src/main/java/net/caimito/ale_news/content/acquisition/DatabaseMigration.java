package net.caimito.ale_news.content.acquisition;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.flywaydb.core.Flyway;
import org.springframework.stereotype.Component;

@Component
public class DatabaseMigration {
    protected static Log logger = LogFactory.getLog(DatabaseMigration.class) ;

    public void migrate() {
        logger.info("Migrating database");

        Flyway flyway = new Flyway();
        flyway.setDataSource("jdbc:h2:file:target/content-aquisition", "sa", null);
        flyway.migrate();
    }
}
