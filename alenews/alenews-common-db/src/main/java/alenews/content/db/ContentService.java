package alenews.content.db;

import alenews.content.acquisition.Content;

import java.util.List;

public interface ContentService {

    void addContent(Content content) ;

    List<Content> findAll() ;

}
