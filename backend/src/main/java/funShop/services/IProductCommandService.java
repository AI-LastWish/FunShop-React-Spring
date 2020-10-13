package funShop.services;

import funShop.domain.Product;
import funShop.domain.dto.ProductDTO;

public interface IProductCommandService {

	Product saveOrUpdate(ProductDTO productDto);

	void deleteProduct(Long id);

}
