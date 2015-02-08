package net.caimito.ale_news.content.acquisition;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentSourceRepository extends CrudRepository<ContentSource, Long> {

    List<ContentSource> findAllByContentType(ContentType type) ;

}
