package alenews.content.acquisition;

import alenews.content.analysis.LinkFinder;
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

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class RSSContentFetcher implements ContentFetcher {
    private static final Log logger = LogFactory.getLog(RSSContentFetcher.class) ;
    private final ContentService contentService;

    public RSSContentFetcher(ContentService contentService) {
        this.contentService = contentService ;
    }

    @Override
    public void fetchFromLocation(URL location) {
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
                logger.debug(String.format("Parsing entry %s", entry));
                Content content = extractContent(language, entry);
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

    protected Content extractContent(String language, SyndEntry entry) {
        Content content = new Content();
        content.setLanguage(language) ;

        try {
            content.setSourceLocation(new URL(entry.getLink())) ;
        } catch (MalformedURLException e) {
            throw new ContentReaderException(e) ;
        }

        content.setTitle(entry.getTitle().trim()) ;
        content.setPublishedDate(entry.getPublishedDate()) ;
        content.setAuthor(entry.getAuthor().trim()) ;

        for (SyndCategory category : entry.getCategories()) {
            content.addCategory(category.getName().trim()) ;
        }

        content.setDescription(extractDescription(entry)) ;
        return content;
    }

    private String extractDescription(SyndEntry entry) {
        if (entry.getDescription() != null) {
            return extractFirstParagraph(entry.getDescription().getValue().trim());
        } else {
            if (entry.getContents().size() > 0) {
                return extractFirstParagraph(entry.getContents().get(0).getValue().trim());
            } else
                return "" ;
        }
    }

    protected String extractFirstParagraph(String input) {
        Document document = Jsoup.parse(input, "UTF-8") ;
        Element element = document.select("p").first() ;

        if (element == null)
            return document.text() ;
        else
            return element.text() ;
    }

}
