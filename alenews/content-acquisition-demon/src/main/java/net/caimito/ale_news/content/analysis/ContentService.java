package net.caimito.ale_news.content.analysis;

import net.caimito.ale_news.content.acquisition.Content;

import java.util.List;

public interface ContentService {

    void addContent(Content content) ;

    List<Content> findAll() ;

}
