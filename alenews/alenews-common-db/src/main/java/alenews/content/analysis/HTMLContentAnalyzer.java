package alenews.content.analysis;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class HTMLContentAnalyzer {

    public String findFirstParagraph(String html) {
        Document document = Jsoup.parse(html);
        if (document == null)
            return "" ;

        Elements paragraphs = document.select("p") ;
        if (paragraphs.isEmpty())
            return "" ;
        else {
            return paragraphs.first().text() ;
        }
    }

}
