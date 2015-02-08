package net.caimito.ale_news.content.acquisition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class HTMLContentReader implements ContentReader {

    @Override
    public Content read(URL location) {
        StringBuilder out = new StringBuilder() ;
        BufferedReader in = null;

        try {
            in = new BufferedReader(new InputStreamReader(location.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null)
                out.append(inputLine);
            in.close();

        } catch (IOException e) {
            throw new RuntimeException(e) ;
        }

        Content content = new Content() ;
        content.setBodyContentType(ContentType.HTML);
        content.setBody(out.toString());

        return content ;
    }

}
