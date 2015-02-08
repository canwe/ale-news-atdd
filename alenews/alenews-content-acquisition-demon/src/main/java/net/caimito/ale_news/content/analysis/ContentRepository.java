package net.caimito.ale_news.content.analysis;

import net.caimito.ale_news.content.acquisition.Content;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends CrudRepository<ContentEntity, Long> {

}
