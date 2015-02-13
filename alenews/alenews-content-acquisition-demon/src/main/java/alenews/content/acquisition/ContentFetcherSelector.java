package alenews.content.acquisition;

import alenews.content.analysis.LinkFinder;
import alenews.content.db.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContentFetcherSelector {

    private final ContentService contentService;

    @Autowired
    public ContentFetcherSelector(ContentService contentService) {
        this.contentService = contentService ;
    }

    public ContentFetcher selectFetcher(ContentSourceType contentSourceType) {
        switch (contentSourceType) {
//            TODO: for the moment focus on RSS
//            case HTML:
//                return new HTMLContentFetcher(linkFinder, contentService) ;
            case RSS:
                return new RSSContentFetcher(contentService) ;
            default:
                throw new RuntimeException(String.format("Unknown content type %s", contentSourceType)) ;
        }
    }
}
