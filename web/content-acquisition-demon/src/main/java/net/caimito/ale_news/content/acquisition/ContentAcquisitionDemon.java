package net.caimito.ale_news.content.acquisition;

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
            List<String> locations = configuration.listSourceLocationsByType(contentType) ;

            for (String location : locations) {
                ContentReader reader = ContentReaderSelector.selectReader(contentType) ;
                Content content = reader.read() ;

                messenger.triggerAnalysis(content) ;
            }
        }
    }
}
