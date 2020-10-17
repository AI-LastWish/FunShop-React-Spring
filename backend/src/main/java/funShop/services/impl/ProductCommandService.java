package funShop.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import funShop.domain.Category;
import funShop.domain.Product;
import funShop.domain.dto.ProductDTO;
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
	public Product saveOrUpdate(ProductDTO productDto) throws Exception {

		var product = new Product();
		var brand = brandQueryService.getBrand(productDto.getBrand_id());
		var categories = new ArrayList<Category>();
		for (Long id : productDto.getCategory_id()) {
			var category = categoryQueryService.getCategory(id);
			categories.add(category);
		}
		product=product.dtoToProduct(productDto, brand, categories);

		return productRepository.save(product);
	}

	@Override
	public void deleteProduct(Long id) throws Exception {

		Product product; 
		
		try {
			product= productRepository.findById(id).get();
		} catch (Exception e) {
			var resMeg = "Product with ID: '" + id + "' not found";
			throw new Exception(resMeg);
		}

		productRepository.delete(product);
	}

}
