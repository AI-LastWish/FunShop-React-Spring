package funShop.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import funShop.domain.Category;
import funShop.domain.Product;
import funShop.domain.dto.ProductDTO;
import funShop.exceptions.ProductException;
import funShop.repositories.ProductRepository;
import funShop.services.IProductCommandService;

@Service
public class ProductCommandService implements IProductCommandService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private BrandQueryService brandQueryService;

	@Autowired
	private CategoryQueryService categoryQueryService;

	@Override
	public Product saveOrUpdate(ProductDTO productDto) {

		try {
			var product = new Product();
			var brand = brandQueryService.getBrand(productDto.getBrand_id());
			var categories = new ArrayList<Category>();
			for (Long id : productDto.getCategory_id()) {
				var category = categoryQueryService.getCategory(id);
				categories.add(category);
			}
			product=product.dtoToProduct(productDto, brand, categories);

			return productRepository.save(product);
		}catch (Exception e) {
			throw new ProductException(e.getMessage());
		}
	}

	@Override
	public void deleteProduct(Long id) {

		var product = productRepository.findById(id).get();

//		if (product == null) {
//		var errMsg = "Cannot delete Product with ID '" + id + "', because it doesn't exists";
//		throw new ProductIdException(errMsg);
//	}

		productRepository.delete(product);
	}

}
