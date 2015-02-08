package alenews.content.acquisition;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component("configurationService")
@Transactional
public class ConfigurationServiceImpl implements ConfigurationService {
    private static final Log logger = LogFactory.getLog(ConfigurationServiceImpl.class) ;
    private final ContentSourceRepository contentSources ;

    @Autowired
    public ConfigurationServiceImpl(ContentSourceRepository contentSources) {
        this.contentSources = contentSources ;
    }

    public void addSource(ContentType sourceType, String location) {
    }

    public List<URL> listSourceLocationsByType(ContentType type) {
        List<URL> urlList = new ArrayList<URL>() ;

        for (ContentSource source : contentSources.findAllByContentType(type)) {
            try {
                urlList.add(new URL(source.getLocation()));
            } catch (MalformedURLException e) {
                logger.error(e);
            }
        }

        return urlList ;
    }
}
