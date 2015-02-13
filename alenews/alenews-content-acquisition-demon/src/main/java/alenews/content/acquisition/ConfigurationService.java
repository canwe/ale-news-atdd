package alenews.content.acquisition;

import java.net.URL;
import java.util.List;

public interface ConfigurationService {

    public void addSource(ContentSourceType sourceType, String location) ;

    public List<URL> listSourceLocationsByType(ContentSourceType type) ;

}
