package alenews.content.acquisition;

import java.net.URL;
import java.util.List;

public interface ConfigurationService {

    public void addSource(ContentType sourceType, String location) ;

    public List<URL> listSourceLocationsByType(ContentType type) ;

}
