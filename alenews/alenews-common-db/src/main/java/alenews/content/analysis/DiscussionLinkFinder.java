package alenews.content.analysis;

import alenews.content.db.ContentService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class DiscussionLinkFinder {
    private static final Log logger = LogFactory.getLog(DiscussionLinkFinder.class);
    private final ContentService contentService;
    private HTMLLinkFinder htmlLinkFinder = new HTMLLinkFinder() ;

    @Autowired
    public DiscussionLinkFinder(ContentService contentService) {
        this.contentService = contentService ;
    }

    public List<URL> findDiscussionLinks(URL sourceLocation, String sourceTitle) {
        List<URL> discussionLinks = new ArrayList<>() ;

        List<URL> articleOutboundLinks = htmlLinkFinder.findArticleOutboundLinks(sourceLocation, sourceTitle) ;

        for (URL link : articleOutboundLinks) {
            if (contentService.hasContentByLocation(link.toExternalForm())) {
                logger.debug(String.format("%s is known", link));
                discussionLinks.add(link);
            } else
                logger.debug(String.format("%s is NOT known", link)) ;
        }

        return discussionLinks;
    }

}
