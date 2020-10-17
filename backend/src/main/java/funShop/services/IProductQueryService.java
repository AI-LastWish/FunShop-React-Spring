package funShop.services;

import funShop.domain.Product;
import funShop.domain.dto.ProductDTO;

public interface IProductQueryService {
	
	Iterable<Product> getAllProducts();
	
	Product getProduct(Long id) throws Exception;
	
	ProductDTO updateProductDto(ProductDTO request, Long id) throws Exception;

}
