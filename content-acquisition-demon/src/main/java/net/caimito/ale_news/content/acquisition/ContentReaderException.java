package net.caimito.ale_news.content.acquisition;

import com.rometools.rome.io.FeedException;

public class ContentReaderException extends RuntimeException {

    public ContentReaderException(Throwable e) {
        super(e) ;
    }

}
