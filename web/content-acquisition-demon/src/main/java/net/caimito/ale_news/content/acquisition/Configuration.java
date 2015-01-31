package net.caimito.ale_news.content.acquisition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Configuration {
    Map<ContentType, List<String>> sourceLocations = new HashMap<ContentType, List<String>>() ;

    public void addSource(ContentType sourceType, String location) {
        List<String> locations = sourceLocations.get(sourceType) ;

        if (locations == null) {
            locations = new ArrayList<String>() ;
            sourceLocations.put(sourceType, locations) ;
        }

        locations.add(location);
    }

    public List<String> listSourceLocationsByType(ContentType type) {
        if (sourceLocations.containsKey(type))
            return sourceLocations.get(type) ;
        else
            return new ArrayList<String>() ;
    }
}
