package funShop.services;

import funShop.domain.Product;
import funShop.domain.dto.ProductDTO;

public interface IProductQueryService {
	
	Iterable<Product> getAllProducts();
	
	Product getProduct(Long id);
	
	ProductDTO updateProductDto(ProductDTO request, Long id);

}
