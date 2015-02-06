package net.caimito.ale_news.content.acquisition;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class FakeContentMessenger implements ContentMessenger {
    protected Log logger = LogFactory.getLog(FakeContentMessenger.class) ;

    @Override
    public void triggerAnalysis(Content content) {
        logger.info("FAKE sending out " + content.toString());
    }
}
