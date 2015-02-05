package net.caimito.ale_news.content.acquisition;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;

@Component
public class ContentAcquisitionDemon {
    protected static Log logger = LogFactory.getLog(ContentAcquisitionDemon.class) ;

    private final Configuration configuration ;
    private final ContentMessenger messenger ;

    @Autowired
    public ContentAcquisitionDemon(Configuration configuration, ContentMessenger messenger) {
        this.configuration = configuration ;
        this.messenger = messenger ;
    }

    public void startAcquisition() {
        logger.info("Content acquisition starting");
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
