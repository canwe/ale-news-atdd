package alenews.content.db;

import alenews.content.acquisition.Content;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends CrudRepository<ContentEntity, Long> {

    ContentEntity findByTitle(String title);

    ContentEntity findBySourceLocation(String location);

}
