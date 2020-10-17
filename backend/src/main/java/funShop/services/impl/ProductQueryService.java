package funShop.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import funShop.domain.Product;
import funShop.domain.dto.ProductDTO;
import funShop.repositories.ProductRepository;
import funShop.services.IProductQueryService;
import funShop.utilities.ProductUtil;

@Service
public class ProductQueryService implements IProductQueryService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Iterable<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProduct(Long id) throws Exception {
		Product product;
		
		try {
			product=	productRepository.findById(id).get();
		} catch (Exception e) {
			var resMeg = "Product with ID: '" + id + "' not found";
			throw new Exception(resMeg);
		}
		
		return product;
	}

	@Override
	public ProductDTO updateProductDto(ProductDTO request, Long id) throws Exception {

		Product updateProduct = getProduct(id);
		ProductUtil productUtil = new ProductUtil();

		request.setId(id);
		request.setName(request.getName() != null ? request.getName() : updateProduct.getName());
		request.setImage(request.getImage() != null ? request.getImage() : updateProduct.getImage());
		request.setDescription(
				request.getDescription() != null ? request.getDescription() : updateProduct.getDescription());
		request.setPrice(request.getPrice() > 0 ? request.getPrice() : updateProduct.getPrice());
		request.setCountInStock(
				request.getCountInStock() > 0 ? request.getCountInStock() : updateProduct.getCountInStock());
		request.setRating(request.getRating() > 0 ? request.getRating() : updateProduct.getRating());
		request.setNumReviews(request.getNumReviews() > 0 ? request.getNumReviews() : updateProduct.getNumReviews());
		request.setBrand_id(request.getBrand_id() != null ? request.getBrand_id() : updateProduct.getBrand().getId());
		request.setCategory_id(request.getCategory_id() != null ? request.getCategory_id()
				: productUtil.CategoriesToCategoryIds(updateProduct.getCategories()));

		return request;
	}

}
