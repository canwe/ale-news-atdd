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

    private final CategoryRepository categoryRepository ;

    @Autowired
    public ContentServiceImpl(ContentRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository ;
        this.categoryRepository = categoryRepository ;
    }

    @Override
    public void addContent(Content content) {
        ContentEntity existingContentEntity = repository.findByTitle(content.getTitle()) ;
        if (existingContentEntity == null) {
            ContentEntity contentEntity = new ContentEntity();
            contentEntity.setTitle(content.getTitle());
            contentEntity.setDescription(content.getDescription());
            contentEntity.setAuthor(content.getAuthor());
            contentEntity.setLanguage(content.getLanguage());
            contentEntity.setSourceLocation(content.getSourceLocation().toExternalForm());

            if (content.getPublishedDate() == null)
                contentEntity.setPublishedDate(new Date());
            else
                contentEntity.setPublishedDate(content.getPublishedDate());

            for (CategoryEntity categoryEntity : findCategoryEntities(content.getCategories())) {
                contentEntity.getCategories().add(categoryEntity) ;
            }

            try {
                repository.save(contentEntity);
            } catch (DataIntegrityViolationException e) {
                logger.error(e);
            }
        } else
            logger.debug(String.format("Not saving content '%s' from %s", content.getTitle(), content.getSourceLocation())) ;
    }

    private List<CategoryEntity> findCategoryEntities(SortedSet<String> categories) {
        List<CategoryEntity> categoryEntityList = new ArrayList<>() ;

        for (String categoryName : categories) {
            CategoryEntity categoryEntity = categoryRepository.findByCategoryName(categoryName) ;
            if (categoryEntity == null) {
                categoryEntity = new CategoryEntity() ;
                categoryEntity.setCategoryName(categoryName);
                categoryRepository.save(categoryEntity) ;
            }

            categoryEntityList.add(categoryEntity);
        }

        return categoryEntityList;
    }

    @Override
    public List<Content> findAll() {
        List<Content> contentList = new ArrayList<Content>() ;

        for (ContentEntity entity : repository.findAll()) {
            contentList.add(convertEntityToContentPojo(entity));
        }

        Collections.sort(contentList, new Comparator<Content>() {
            @Override
            public int compare(Content o1, Content o2) {
                return -o1.getPublishedDate().compareTo(o2.getPublishedDate()) ;
            }
        }) ;

        return contentList ;
    }

    private Content convertEntityToContentPojo(ContentEntity entity) {
        Content content = new Content();

        try {
            content.setTitle(entity.getTitle());
            content.setDescription(entity.getDescription());
            content.setAuthor(entity.getAuthor());
            content.setLanguage(entity.getLanguage());

            for (CategoryEntity categoryEntity : entity.getCategories()) {
                content.getCategories().add(categoryEntity.getCategoryName()) ;
            }

            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC")) ;
            if (entity.getPublishedDate() != null) {
                cal.setTime(entity.getPublishedDate());
                content.setPublishedDate(cal.getTime());
            }

            content.setSourceLocation(new URL(entity.getSourceLocation()));

            if (!entity.getDiscussion().isEmpty())
                loadDiscussion(content, entity);
        } catch (MalformedURLException e) {
            logger.error(String.format("Ignoring content with title '%s'", entity.getTitle()), e) ;
        }

        return content ;
    }

    private void loadDiscussion(Content content, ContentEntity contentEntity) {
        for (ContentEntity discussionEntity : contentEntity.getDiscussion()) {
            content.getDiscussion().add(convertEntityToContentPojo(discussionEntity)) ;
        }
    }

    @Override
    public boolean hasContentByLocation(String location) {
        ContentEntity entity = repository.findBySourceLocation(location) ;

        return entity != null ;
    }

    @Override
    public void addDiscussions(Content content, List<URL> discussionLinks) {
        ContentEntity contentEntity = repository.findBySourceLocation(content.getSourceLocation().toExternalForm()) ;
        if (contentEntity != null) {
            for (URL discussionLink : discussionLinks) {
                ContentEntity discussionTarget = repository.findBySourceLocation(discussionLink.toExternalForm()) ;
                if (discussionTarget != null) {
                    contentEntity.getDiscussion().add(discussionTarget) ;
                }
            }
            repository.save(contentEntity) ;
        } else
            throw new UnknownContentException(String.format("Content '%s' from %s is not known. Cannot add discussion with '%s' to it", content.getTitle(), content.getSourceLocation(), discussionLinks)) ;
    }

}
