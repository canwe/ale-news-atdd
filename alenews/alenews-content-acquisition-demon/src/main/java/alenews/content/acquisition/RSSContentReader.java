package alenews.content.acquisition;

import com.rometools.rome.feed.synd.SyndCategory;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class RSSContentReader implements ContentReader {
    private static final Log logger = LogFactory.getLog(RSSContentReader.class) ;

    @Override
    public Content read(URL location) {
        SyndFeedInput input = new SyndFeedInput() ;

        try {
            SyndFeed feed = input.build(new InputStreamReader(location.openStream())) ;
            Content content = extractFirstEntry(feed) ;
            if (content == null)
                throw new ContentReaderException(String.format("Feed %s has no content", location.toExternalForm())) ;
            else
                return content ;
        } catch (FeedException e) {
            logger.error(e);
            throw new ContentReaderException(e) ;
        } catch (IOException e) {
            logger.error(e);
            throw new ContentReaderException(e) ;
        }
    }

    protected Content extractFirstEntry(SyndFeed feed) {
        if (feed.getEntries().size() > 0) {
            SyndEntry entry = feed.getEntries().get(0) ;

            String language = LanguageNormalizer.normalize(feed.getLanguage()) ;

            return extractContent(language, entry);
        } else
            return null ;
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
            return cleanUpDescription(entry.getDescription().getValue().trim());
        } else {
            if (entry.getContents().size() > 0) {
                return cleanUpDescription(entry.getContents().get(0).getValue().trim());
            } else
                return "" ;
        }
    }

    protected String cleanUpDescription(String description) {
        Document document = Jsoup.parse(description) ;
        return document.text() ;
    }

}
