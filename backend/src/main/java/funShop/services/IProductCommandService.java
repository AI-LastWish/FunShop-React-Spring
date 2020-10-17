package funShop.services;

import funShop.domain.Product;
import funShop.domain.dto.ProductDTO;

public interface IProductCommandService {

	Product saveOrUpdate(ProductDTO productDto) throws Exception;

	void deleteProduct(Long id) throws Exception;

}
