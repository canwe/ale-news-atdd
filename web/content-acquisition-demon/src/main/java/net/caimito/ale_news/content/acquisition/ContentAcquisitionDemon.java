package net.caimito.ale_news.content.acquisition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;

@Component
public class ContentAcquisitionDemon {
    private final Configuration configuration ;
    private final ContentMessenger messenger ;

    @Autowired
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
