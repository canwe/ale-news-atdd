package alenews.content.acquisition;

import alenews.content.analysis.LinkFinder;
import alenews.content.db.ContentService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class HTMLContentFetcher implements ContentFetcher {
    private static final Log logger = LogFactory.getLog(HTMLContentFetcher.class) ;
    private final LinkFinder linkFinder;
    private final ContentService contentService;
//    private final HTMLArticleReader htmlArticleReader;

    public HTMLContentFetcher(LinkFinder linkFinder, ContentService contentService) {
        this.linkFinder = linkFinder ;
        this.contentService = contentService ;
//        this.htmlArticleReader = htmlArticleReader ;
    }

    @Override
    public void fetchFromLocation(URL location) {
        // todo
//        FullTextArticle fullTextArticle = htmlArticleReader.readFromLocation(location) ;
//
//        Content content = new Content() ;
//        content.setDescription(fullTextArticle.getFirstParagraph());
//
//        content = linkFinder.findDiscussionLinks(content);
//        contentService.addContent(content) ;
    }

}
