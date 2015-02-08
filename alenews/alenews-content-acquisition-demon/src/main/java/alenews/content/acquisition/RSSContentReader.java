package alenews.content.acquisition;

import com.rometools.rome.feed.synd.SyndCategory;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class RSSContentReader implements ContentReader {
    private static final Log logger = LogFactory.getLog(RSSContentReader.class) ;

    @Override
    public Content read(URL location) {
        SyndFeedInput input = new SyndFeedInput() ;

        try {
            SyndFeed feed = input.build(new InputStreamReader(location.openStream())) ;

            if (feed.getEntries().size() > 0) {
                SyndEntry entry = feed.getEntries().get(0) ;

                Content content = new Content();
                content.setLanguage(LanguageNormalizer.normalize(feed.getLanguage())) ;
                content.setSourceLocation(new URL(entry.getLink())) ;
                content.setTitle(entry.getTitle().trim()) ;
                content.setPublishedDate(entry.getPublishedDate()) ;
                content.setAuthor(entry.getAuthor().trim()) ;

                for (SyndCategory category : entry.getCategories()) {
                    content.addCategory(category.getName().trim()) ;
                }

                if (entry.getDescription() != null) {
                    content.setBodyContentType(ContentType.HTML);
                    content.setBody(entry.getDescription().getValue().trim());
                } else {
                    if (entry.getContents().size() > 0) {
                        content.setBodyContentType(ContentType.HTML);
                        content.setBody(entry.getContents().get(0).getValue().trim());
                    } else
                        throw new EmptyContentException(String.format("Feed %s has empty entry", location.toString())) ;
                }
                return content;
            } else
                throw new EmptyContentException(String.format("Feed %s has no entries", location.toString())) ;
        } catch (FeedException e) {
            logger.error(e);
            throw new ContentReaderException(e) ;
        } catch (IOException e) {
            logger.error(e);
            throw new ContentReaderException(e) ;
        }
    }

}
