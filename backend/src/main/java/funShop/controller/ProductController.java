package funShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import funShop.domain.Product;
import funShop.services.impl.ProductQueryService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {

	@Autowired
	private ProductQueryService productQueryService;

	@GetMapping("")
	public Iterable<Product> getAllProducts() {
		return productQueryService.getAllProducts();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getProduct(@PathVariable Long id){
		
		Product product=productQueryService.getProduct(id);
		
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
}
