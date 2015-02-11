package alenews.content.acquisition;

import org.springframework.stereotype.Component;

@Component
public class ContentReaderSelector {

    public ContentReader selectReader(ContentType contentType) {
        switch (contentType) {
            case HTML:
                return new HTMLContentReader() ;
            case RSS:
                return new RSSContentReader() ;
            default:
                throw new RuntimeException(String.format("Unknown content type %s", contentType)) ;
        }
    }
}
