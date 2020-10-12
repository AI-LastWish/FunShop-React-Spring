package funShop.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import funShop.domain.Product;
import funShop.repositories.ProductRepository;
import funShop.services.IProductQueryService;

@Service
public class ProductQueryService implements IProductQueryService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Iterable<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProduct(Long id) {
		return productRepository.findById(id).get();
	}

}
