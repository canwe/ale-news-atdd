package net.caimito.ale_news.content.acquisition;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.json.JSONWriter;
import org.springframework.stereotype.Component;

@Component
public class FakeContentMessenger implements ContentMessenger {
    protected Log logger = LogFactory.getLog(FakeContentMessenger.class) ;

    @Override
    public void publish(Content content) {
        JSONObject jsonObject = new JSONObject(content) ;
        logger.info("FAKE sending out " + jsonObject.toString(2));
    }
}
