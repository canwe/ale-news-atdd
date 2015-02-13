package alenews.content.acquisition;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class HTMLArticleReader {
    private static final Log logger = LogFactory.getLog(HTMLArticleReader.class) ;

    public FullTextArticle readFromLocation(URL location) {
        String html = readHTMLFromLocation(location) ;

        return null ; // todo finish this
    }

    private String readHTMLFromLocation(URL location) {
        StringBuilder out = new StringBuilder() ;
        BufferedReader in = null;

        try {
            ContentConnection contentConnection = new ContentConnection(location) ;

            in = new BufferedReader(new InputStreamReader(contentConnection.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null)
                out.append(inputLine);
            in.close();
        } catch (IOException e) {
            try {
                URLConnection urlConnection = location.openConnection() ;
                logger.error(String.format("Connection to %s failed. Header fields: %s", location.toExternalForm(), urlConnection.getHeaderFields())) ;
            } catch (IOException e1) {
                logger.error(e1);
            }
            throw new RuntimeException(e) ;
        }

        return out.toString() ;
    }

}
