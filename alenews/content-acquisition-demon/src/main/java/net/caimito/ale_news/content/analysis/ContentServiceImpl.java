package net.caimito.ale_news.content.analysis;

import net.caimito.ale_news.content.acquisition.Content;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.*;

@Component("contentService")
@Transactional
public class ContentServiceImpl implements ContentService {
    private static final Log logger = LogFactory.getLog(ContentServiceImpl.class) ;

    private final ContentRepository repository;

    @Autowired
    public ContentServiceImpl(ContentRepository repository) {
        this.repository = repository ;
    }

    @Override
    public void addContent(Content content) {
        ContentEntity entity = new ContentEntity() ;
        entity.setTitle(content.getTitle());
        entity.setBody(content.getBody());
        entity.setAuthor(content.getAuthor());
        entity.setContentType(content.getBodyContentType());
        entity.setLanguage(content.getLanguage());
        entity.setPublishedDate(content.getPublishedDate());
        entity.setSourceLocation(content.getSourceLocation().toExternalForm());

        JSONObject jsonObject = new JSONObject(content) ;
        entity.setJson(jsonObject.toString(2));

        repository.save(entity) ;
    }

    @Override
    public List<Content> findAll() {
        List<Content> contentList = new ArrayList<Content>() ;

        for (ContentEntity entity : repository.findAll()) {
            try {
                Content content = new Content();
                content.setTitle(entity.getTitle());
                content.setBody(entity.getBody());
                content.setAuthor(entity.getAuthor());
                content.setBodyContentType(entity.getContentType());
                content.setLanguage(entity.getLanguage());


                Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC")) ;
                cal.setTime(entity.getPublishedDate());
                content.setPublishedDate(cal.getTime());

                content.setSourceLocation(new URL(entity.getSourceLocation()));

                JSONObject json = new JSONObject(entity.getJson());

                JSONArray outgoingLinks = json.getJSONArray("outgoingLinks") ;
                for (int i=0; i < outgoingLinks.length(); i++) {
                    content.addOutgoingLink(new URL(outgoingLinks.getString(i)));
                }

                JSONArray categories = json.getJSONArray("categories") ;
                for (int i=0; i < categories.length(); i++) {
                    content.addCategory(categories.getString(i));
                }

                contentList.add(content);
            } catch (MalformedURLException e) {
                logger.error(String.format("Ignoring content with title '%s'", entity.getTitle()), e) ;
            }
        }

        return contentList ;
    }

}
