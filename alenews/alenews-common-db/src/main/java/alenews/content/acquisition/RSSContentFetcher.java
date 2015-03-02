package alenews.content.acquisition;

import alenews.content.db.ContentService;
import com.rometools.rome.feed.synd.SyndCategory;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@Component
public class RSSContentFetcher {
    private static final Log logger = LogFactory.getLog(RSSContentFetcher.class) ;
    private final ContentService contentService;
    private final RSSContentExtractor rssContentExtractor = new RSSContentExtractor();

    @Autowired
    public RSSContentFetcher(ContentService contentService) {
        this.contentService = contentService ;
    }

    public void fetchAllFromLocation(URL location) {
        SyndFeedInput input = new SyndFeedInput() ;

        try {
            ContentConnection contentConnection = new ContentConnection(location) ;

            SyndFeed feed = input.build(new InputStreamReader(contentConnection.getInputStream())) ;
            String language = LanguageNormalizer.normalize(feed.getLanguage()) ;
            if (logger.isDebugEnabled()) {
                logger.debug(String.format("Opened feed %s", location.toExternalForm())) ;
                logger.debug(String.format("Feed language is '%s'", language));
            }

            for (SyndEntry entry : feed.getEntries()) {
                Content content = rssContentExtractor.extractContent(language, entry);
                contentService.addContent(content) ;
            }
        } catch (FeedException e) {
            logger.error(e);
            throw new ContentReaderException(e) ;
        } catch (IOException e) {
            logger.error(e);

            try {
                URLConnection urlConnection = location.openConnection() ;
                logger.error(String.format("Connection to %s failed. Header fields: %s", location.toExternalForm(), urlConnection.getHeaderFields())) ;
            } catch (IOException e1) {
                logger.error(e1);
            }
            throw new ContentReaderException(e) ;
        }
    }

}
