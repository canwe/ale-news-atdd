package net.caimito.ale_news.content.analysis;

import net.caimito.ale_news.content.acquisition.Content;
import net.caimito.ale_news.content.acquisition.HTMLContentReader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class HTMLLinkFinder implements LinkFinder {
    private static final Log logger = LogFactory.getLog(HTMLLinkFinder.class);

    @Override
    public Content findOutgoingLinks(Content content) {
        HTMLContentReader htmlContentReader = new HTMLContentReader();

        Document document = Jsoup.parse(htmlContentReader.read(content.getSourceLocation()).getBody());
        Elements articleElements = document.select(String.format("h1:containsOwn(%s)", content.getTitle())) ;
        Elements assumedArticleElements = articleElements.get(0).siblingElements().select("p") ;

        for (Element link : assumedArticleElements.select("a[href]")) {
            String linkTarget = link.attr("abs:href").toString();

            try {
                content.addOutgoingLink(new URL(linkTarget));
            } catch (MalformedURLException e) {
                logger.error(e);
            }
        }

        return content;
    }

}
