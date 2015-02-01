package net.caimito.ale_news.content.acquisition;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Configuration {
    Map<ContentType, List<URL>> sourceLocations = new HashMap<ContentType, List<URL>>() ;

    public void addSource(ContentType sourceType, String location) {
        List<URL> locations = sourceLocations.get(sourceType) ;

        if (locations == null) {
            locations = new ArrayList<URL>() ;
            sourceLocations.put(sourceType, locations) ;
        }

        try {
            locations.add(new URL(location));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e) ;
        }
    }

    public List<URL> listSourceLocationsByType(ContentType type) {
        if (sourceLocations.containsKey(type))
            return sourceLocations.get(type) ;
        else
            return new ArrayList<URL>() ;
    }
}
