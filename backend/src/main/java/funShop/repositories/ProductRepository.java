package funShop.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import funShop.domain.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}
