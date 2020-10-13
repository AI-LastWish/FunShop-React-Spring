package funShop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import funShop.domain.Product;
import funShop.domain.dto.ProductDTO;
import funShop.services.impl.ProductCommandService;
import funShop.services.impl.ProductQueryService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {

	@Autowired
	private ProductQueryService productQueryService;

	@Autowired
	private ProductCommandService productCommandService;

//	CREATE
	@PostMapping("")
	public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDTO productDto, BindingResult result) {
		
		if(result.hasErrors()) {			
			Map<String, String> errorMap=new HashMap<>();
			
			for (FieldError error : result.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			
			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		}

		var product = productCommandService.saveOrUpdate(productDto);

		return new ResponseEntity<Product>(product, HttpStatus.CREATED);
	}

//	READ
	@GetMapping("")
	public Iterable<Product> getAllProducts() {
		return productQueryService.getAllProducts();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getProduct(@PathVariable Long id) {

		Product product = productQueryService.getProduct(id);

		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

//	UPDATE
	@PutMapping("/{id}")
	public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductDTO request, @PathVariable Long id) {
//		Throw error if user enter invalid product ID

		var updateProductDto = productQueryService.updateProductDto(request, id);
//
		productCommandService.saveOrUpdate(updateProductDto);

		return new ResponseEntity<ProductDTO>(updateProductDto, HttpStatus.OK);
	}

//	DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long id) {

		productCommandService.deleteProduct(id);

		var resMeg = "Product with ID: '" + id + "' was deleted";

		return new ResponseEntity<String>(resMeg, HttpStatus.OK);
	}
}
