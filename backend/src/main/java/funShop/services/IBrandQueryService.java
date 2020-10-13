package funShop.services;

import funShop.domain.Brand;

public interface IBrandQueryService {
	
	Iterable<Brand> getAllBrands();
	
	Brand getBrand(Long id);

}
