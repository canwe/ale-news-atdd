package alenews.content.db;

import alenews.content.acquisition.Content;

import java.net.URL;
import java.util.List;

public interface ContentService {

    void addContent(Content content) ;

    List<Content> findAll() ;

    boolean hasContentByLocation(String location);

    void addDiscussions(Content content, List<URL> discussionLinks);

}
