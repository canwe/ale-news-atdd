package net.caimito.ale_news.content.acquisition;

import java.net.URL;

public interface ContentReader {

    public Content read(URL location);

}
