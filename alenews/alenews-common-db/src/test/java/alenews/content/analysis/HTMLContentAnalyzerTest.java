package alenews.content.analysis;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class HTMLContentAnalyzerTest {
    private HTMLContentAnalyzer htmlContentAnalyzer = new HTMLContentAnalyzer() ;

    @Test
    public void noFirstParagraph() {
        String html = "<html><head/><body></body></html>" ;

        assertThat(htmlContentAnalyzer.findFirstParagraph(html), is(equalTo(""))) ;
    }

    @Test
    public void oneParagraph() {
        String html = "<html><head/><body>" +
                "<p>First paragraph.</p>" +
                "</body></html>" ;

        assertThat(htmlContentAnalyzer.findFirstParagraph(html), is(equalTo("First paragraph."))) ;
    }

    @Test
    public void oneParagraphAfterH1() {
        String html = "<html><head/><body>" +
                "<h1>Title</h1>" +
                "<p>First paragraph.</p>" +
                "</body></html>" ;

        assertThat(htmlContentAnalyzer.findFirstParagraph(html), is(equalTo("First paragraph."))) ;
    }

}
