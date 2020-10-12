package funShop.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import funShop.domain.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

}
