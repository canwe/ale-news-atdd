package net.caimito.ale_news;

import net.caimito.ale_news.article_service.ArticleMetadata;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ArticleStorage {

    private Map<String, ArticleMetadata> metaData = new HashMap<String, ArticleMetadata>() ;

    public Map<String, ArticleMetadata> getStorage() {
        return metaData;
    }
}
