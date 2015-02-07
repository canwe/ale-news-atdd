package net.caimito.ale_news.content.acquisition;

import net.caimito.ale_news.content.analysis.LinkFinder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;

@EnableScheduling
@Component
public class ContentAcquisitionDemon {
    protected static Log logger = LogFactory.getLog(ContentAcquisitionDemon.class) ;

    private final ConfigurationService configurationService;
    private final ContentMessenger messenger ;
    private final LinkFinder linkFinder;

    @Autowired
    public ContentAcquisitionDemon(ConfigurationService configurationService, ContentMessenger messenger, LinkFinder linkFinder) {
        this.configurationService = configurationService;
        this.messenger = messenger ;
        this.linkFinder = linkFinder ;
    }

    @Scheduled(fixedRate = 1000 * 60 * 12)
    public void startAcquisition() {
        logger.info("Content acquisition starting");
        for (ContentType contentType : ContentType.values()) {
            logger.debug(String.format("Listing for content type %s", contentType)) ;
            List<URL> locations = configurationService.listSourceLocationsByType(contentType) ;
            logger.debug(String.format("Found locations %s", locations)) ;

            for (URL location : locations) {
                logger.debug(String.format("Reading from %s", location)) ;
                ContentReader reader = ContentReaderSelector.selectReader(contentType) ;
                Content content = reader.read(location) ;

                content = linkFinder.findOutgoingLinks(content) ;

                messenger.publish(content) ;
            }
        }
    }
}
