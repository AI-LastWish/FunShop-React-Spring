package funShop.services;

import funShop.domain.Product;

public interface IProductQueryService {
	
	Iterable<Product> getAllProducts();
	
	Product getProduct(Long id);

}
