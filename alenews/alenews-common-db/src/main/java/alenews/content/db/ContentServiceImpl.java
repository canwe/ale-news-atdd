package alenews.content.db;

import alenews.content.acquisition.Content;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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

        try {
            ObjectMapper mapper = new ObjectMapper() ;
            entity.setJson(mapper.writeValueAsString(content)) ;
        } catch (JsonProcessingException e) {
            logger.error(e) ;
        }

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
                if (entity.getPublishedDate() != null) {
                    cal.setTime(entity.getPublishedDate());
                    content.setPublishedDate(cal.getTime());
                }

                content.setSourceLocation(new URL(entity.getSourceLocation()));

                try {
                    ObjectMapper mapper = new ObjectMapper() ;
                    Content fromJson = mapper.readValue(entity.getJson(), Content.class) ;

                    for (URL outgoingLink : fromJson.getOutgoingLinks()) {
                        content.addOutgoingLink(outgoingLink);
                    }

                    for (String category : fromJson.getCategories()) {
                        content.addCategory(category);
                    }
                } catch (JsonParseException e) {
                    logger.error(e);
                } catch (JsonMappingException e) {
                    logger.error(e);
                } catch (IOException e) {
                    logger.error(e);
                }

                contentList.add(content);
            } catch (MalformedURLException e) {
                logger.error(String.format("Ignoring content with title '%s'", entity.getTitle()), e) ;
            }
        }

        return contentList ;
    }

}
