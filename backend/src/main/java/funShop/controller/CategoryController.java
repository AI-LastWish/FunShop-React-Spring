package funShop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import funShop.domain.Category;
import funShop.services.impl.CategoryCommandService;
import funShop.services.impl.CategoryQueryService;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin
public class CategoryController {

	@Autowired
	private CategoryQueryService categoryQueryService;
	
	@Autowired
	private CategoryCommandService categoryCommandService;
	
//	CREATE
	@PostMapping("")
	public ResponseEntity<?> createCategory(@Valid @RequestBody Category request) {

		var category = categoryCommandService.saveOrUpdate(request);

		return new ResponseEntity<Category>(category, HttpStatus.CREATED);
	}
	
//	READ
	@GetMapping("")
	public Iterable<Category> getAllCategories() {
		return categoryQueryService.getAllCategories();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getCategory(@PathVariable Long id) throws Exception {

		var category = categoryQueryService.getCategory(id);

		return new ResponseEntity<Category>(category, HttpStatus.OK);
	}
	
//	UPDATE
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCategory(@Valid @RequestBody Category request, @PathVariable Long id) throws Exception {
//		Throw error if user enter invalid brand ID

		var oldCategory = categoryQueryService.getCategory(id);

		oldCategory.setName(request.getName() != null ? request.getName() : oldCategory.getName());

		var category = categoryCommandService.saveOrUpdate(oldCategory);

		return new ResponseEntity<Category>(category, HttpStatus.OK);
	}
	
//	DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable Long id) throws Exception {

		categoryCommandService.deleteCategory(id);

		var resMeg = "Category with ID: '" + id + "' was deleted";

		return new ResponseEntity<String>(resMeg, HttpStatus.OK);
	}
}
