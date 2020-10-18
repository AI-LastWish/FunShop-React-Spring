package funShop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import funShop.domain.Brand;
import funShop.services.impl.BrandCommandService;
import funShop.services.impl.BrandQueryService;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

	@Autowired
	private BrandQueryService brandQueryService;

	@Autowired
	private BrandCommandService brandCommandService;

//	CREATE
	@PostMapping("")
	public ResponseEntity<?> createBrand(@Valid @RequestBody Brand request, BindingResult result) {

		var brand = brandCommandService.saveOrUpdate(request);

		return new ResponseEntity<Brand>(brand, HttpStatus.CREATED);
	}

//	READ
	@GetMapping("")
	public Iterable<Brand> getAllBrands() {
		return brandQueryService.getAllBrands();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getBrand(@PathVariable Long id) throws Exception {

		var brand = brandQueryService.getBrand(id);

		return new ResponseEntity<Brand>(brand, HttpStatus.OK);
	}

//	UPDATE
	@PutMapping("/{id}")
	public ResponseEntity<?> updateBrand(@Valid @RequestBody Brand request, @PathVariable Long id) throws Exception {

		var oldBrand = brandQueryService.getBrand(id);

		oldBrand.setName(request.getName() != null ? request.getName() : oldBrand.getName());

		var brand = brandCommandService.saveOrUpdate(oldBrand);

		return new ResponseEntity<Brand>(brand, HttpStatus.OK);
	}

//	DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBrand(@PathVariable Long id) throws Exception {

		brandCommandService.deleteBrand(id);

		var resMeg = "Brand with ID: '" + id + "' was deleted";

		return new ResponseEntity<String>(resMeg, HttpStatus.OK);
	}
}
