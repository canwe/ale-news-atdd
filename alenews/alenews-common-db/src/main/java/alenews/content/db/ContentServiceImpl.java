package alenews.content.db;

import alenews.content.acquisition.Content;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        ContentEntity existingContentEntity = repository.findByTitle(content.getTitle()) ;
        if (existingContentEntity == null) {
            ContentEntity entity = new ContentEntity();
            entity.setTitle(content.getTitle());
            entity.setDescription(content.getDescription());
            entity.setAuthor(content.getAuthor());
            entity.setLanguage(content.getLanguage());
            entity.setSourceLocation(content.getSourceLocation().toExternalForm());

            if (content.getPublishedDate() == null)
                entity.setPublishedDate(new Date());
            else
                entity.setPublishedDate(content.getPublishedDate());

            try {
                ObjectMapper mapper = new ObjectMapper();
                entity.setJson(mapper.writeValueAsString(content));
            } catch (JsonProcessingException e) {
                logger.error(e);
            }

            try {
                repository.save(entity);
            } catch (DataIntegrityViolationException e) {
                logger.error(e);
            }
        } else
            logger.debug(String.format("Not saving content '%s' from %s", content.getTitle(), content.getSourceLocation())) ;
    }

    @Override
    public List<Content> findAll() {
        List<Content> contentList = new ArrayList<Content>() ;

        for (ContentEntity entity : repository.findAll()) {
            try {
                Content content = new Content();
                content.setTitle(entity.getTitle());
                content.setDescription(entity.getDescription());
                content.setAuthor(entity.getAuthor());
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

                    for (URL outgoingLink : fromJson.getDiscussionLinks()) {
                        content.addDiscussionLink(outgoingLink);
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

        Collections.sort(contentList, new Comparator<Content>() {
            @Override
            public int compare(Content o1, Content o2) {
                return -o1.getPublishedDate().compareTo(o2.getPublishedDate()) ;
            }
        }) ;

        return contentList ;
    }

    @Override
    public Content findByLocation(String location) {
        ContentEntity entity = repository.findBySourceLocation(location) ;

        return null;
    }

}
