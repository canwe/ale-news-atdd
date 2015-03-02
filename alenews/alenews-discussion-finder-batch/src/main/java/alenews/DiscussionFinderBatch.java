package alenews;

import alenews.content.acquisition.ConfigurationService;
import alenews.content.acquisition.Content;
import alenews.content.acquisition.ContentSourceType;
import alenews.content.acquisition.RSSContentFetcher;
import alenews.content.analysis.DiscussionLinkFinder;
import alenews.content.db.ContentService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URL;
import java.util.List;

@SpringBootApplication
public class DiscussionFinderBatch implements CommandLineRunner {
    private final static Log logger = LogFactory.getLog(DiscussionFinderBatch.class) ;

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private RSSContentFetcher rssContentFetcher;

    @Autowired
    private ContentService contentService ;

    @Autowired
    private DiscussionLinkFinder discussionLinkFinder;

    @Override
    public void run(String... args) {
        fetchNewContentFromSources() ;
        findingDicussionsInContent() ;
    }

    private void findingDicussionsInContent() {
        logger.info("Finding Discussions in already fetched content") ;
        for (Content content : contentService.findAll()) {
            List<URL> discussionLinks = discussionLinkFinder.findDiscussionLinks(content.getSourceLocation(), content.getTitle()) ;
            logger.info(String.format("%s discusses with %s ", content.getSourceLocation(), discussionLinks)) ;
        }
    }

    private void fetchNewContentFromSources() {
        logger.info("Fetch new content from sources") ;
        for (ContentSourceType contentSourceType : ContentSourceType.values()) {
            logger.debug(String.format("Listing for content type %s", contentSourceType)) ;
            List<URL> locations = configurationService.listSourceLocationsByType(contentSourceType) ;
            logger.debug(String.format("Found locations %s", locations)) ;

            for (URL location : locations) {
                logger.info(String.format("Working on source location %s", location));
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

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DiscussionFinderBatch.class, args);
    }

}
