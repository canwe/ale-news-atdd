package alenews.content.acquisition;

import com.rometools.rome.feed.synd.SyndCategory;
import com.rometools.rome.feed.synd.SyndEntry;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.net.MalformedURLException;
import java.net.URL;

public class RSSContentExtractor {
    private static final Log logger = LogFactory.getLog(RSSContentExtractor.class) ;

    public Content extractContent(String language, SyndEntry entry) {
        logger.debug(String.format("Extracting metadata for item with title '%s'", entry.getTitle()));
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
