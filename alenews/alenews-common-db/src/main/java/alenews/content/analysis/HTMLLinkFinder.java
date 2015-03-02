package alenews.content.analysis;

import alenews.content.acquisition.ContentConnection;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HTMLLinkFinder {
    private final static Log logger = LogFactory.getLog(HTMLLinkFinder.class) ;

    public List<URL> findArticleOutboundLinks(URL sourceLocation, String sourceTitle) {
        logger.debug(String.format("Looking for outbound links in article %s", sourceLocation)) ;
        List<URL> outboundLinks = new ArrayList<>() ;

        if (sourceTitle.isEmpty())
            return outboundLinks ;

        ContentConnection contentConnection = new ContentConnection(sourceLocation) ;
        try {
            Document document = Jsoup.parse(contentConnection.getInputStream(), "utf-8", sourceLocation.toExternalForm());

            Elements articleElements = document.select(String.format("h1:containsOwn(%s)", sourceTitle)) ;
            Elements assumedArticleElements ;

            if (articleElements.size() > 0)
                assumedArticleElements = articleElements.get(0).siblingElements().select("p") ;
            else
                assumedArticleElements = articleElements ;

            for (Element link : assumedArticleElements.select("a[href]")) {
                String linkTarget = link.attr("abs:href").toString();
                logger.debug(String.format("  Found outbound link %s", linkTarget)) ;

                outboundLinks.add(new URL(linkTarget));
            }

        } catch (IOException e) {
            logger.error(String.format("  Trying to find outgoing links in %s", sourceLocation.toExternalForm()), e);
        }

        return outboundLinks;
    }

}
