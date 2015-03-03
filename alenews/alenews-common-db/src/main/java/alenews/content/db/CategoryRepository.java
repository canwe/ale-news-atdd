package alenews.content.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {

    CategoryEntity findByCategoryName(String categoryName);

}
