package funShop.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import funShop.domain.Category;
import funShop.repositories.CategoryRepository;
import funShop.services.ICategoryQueryService;

@Service
public class CategoryQueryService implements ICategoryQueryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Iterable<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Category getCategory(Long id) throws Exception {
		
		Category category;
		
		try {
			category=categoryRepository.findById(id).get();
		} catch (Exception e) {
			var resMeg = "Category with ID: '" + id + "' not found";
			throw new Exception(resMeg);
		}
		return category;
	}

}
