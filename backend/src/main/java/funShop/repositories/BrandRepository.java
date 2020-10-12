package funShop.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import funShop.domain.Brand;

@Repository
public interface BrandRepository extends CrudRepository<Brand, Long> {

}
