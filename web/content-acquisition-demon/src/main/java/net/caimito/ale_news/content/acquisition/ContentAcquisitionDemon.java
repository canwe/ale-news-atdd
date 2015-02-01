package net.caimito.ale_news.content.acquisition;

import java.net.URL;
import java.util.List;

public class ContentAcquisitionDemon {
    private final Configuration configuration ;
    private final ContentMessenger messenger ;

    public ContentAcquisitionDemon(Configuration configuration, ContentMessenger messenger) {
        this.configuration = configuration ;
        this.messenger = messenger ;
    }

    public void startAcquisition() {
        for (ContentType contentType : ContentType.values()) {
            List<URL> locations = configuration.listSourceLocationsByType(contentType) ;

            for (URL location : locations) {
                ContentReader reader = ContentReaderSelector.selectReader(contentType) ;
                Content content = reader.read(location) ;

                messenger.triggerAnalysis(content) ;
            }
        }
    }
}
