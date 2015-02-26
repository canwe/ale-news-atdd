package alenews.content.acquisition;

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
    private final RSSContentFetcher rssContentFetcher ;

    @Autowired
    public ContentAcquisitionDemon(ConfigurationService configurationService,
                                   RSSContentFetcher rssContentFetcher) {
        this.configurationService = configurationService;
        this.rssContentFetcher = rssContentFetcher ;
    }

    @Scheduled(fixedRate = 1000 * 60 * 12)
    public void startAcquisition() {
        logger.info("Content acquisition starting");
        for (ContentSourceType contentSourceType : ContentSourceType.values()) {
            logger.debug(String.format("Listing for content type %s", contentSourceType)) ;
            List<URL> locations = configurationService.listSourceLocationsByType(contentSourceType) ;
            logger.debug(String.format("Found locations %s", locations)) ;

            for (URL location : locations) {
                logger.debug(String.format("Working on source location %s", location));
                try {
                    if (contentSourceType == ContentSourceType.RSS) {
                        rssContentFetcher.fetchAllFromLocation(location);
                    }
                } catch (Exception e) {
                    logger.error(String.format("Skipping content acquisition for %s", location), e) ;
                }
            }
        }
    }
}
