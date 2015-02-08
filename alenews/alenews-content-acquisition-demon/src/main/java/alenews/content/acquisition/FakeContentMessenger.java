package alenews.content.acquisition;

import alenews.content.db.ContentService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FakeContentMessenger implements ContentMessenger {
    protected static final Log logger = LogFactory.getLog(FakeContentMessenger.class) ;

    private final ContentService contentService;

    @Autowired
    public FakeContentMessenger(ContentService contentService) {
        this.contentService = contentService ;
    }

    @Override
    public void publish(Content content) {
//        JSONObject jsonObject = new JSONObject(content) ;
//        logger.info("Storing " + jsonObject.toString(2));

        contentService.addContent(content) ;
    }
}
