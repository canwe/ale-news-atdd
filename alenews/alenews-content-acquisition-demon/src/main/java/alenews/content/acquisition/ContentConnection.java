package alenews.content.acquisition;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class ContentConnection {
    private URL location ;

    public ContentConnection(URL location) {
        this.location = location ;
    }

    public InputStream getInputStream() throws IOException {
        URLConnection urlConnection = location.openConnection() ;
        urlConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:12.0) Gecko/20100101 Firefox/21.0");

        return urlConnection.getInputStream() ;
    }
}
