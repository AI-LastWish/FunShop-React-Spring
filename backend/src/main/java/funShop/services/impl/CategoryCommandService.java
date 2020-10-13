package funShop.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import funShop.domain.Category;
import funShop.repositories.CategoryRepository;
import funShop.services.ICategoryCommandService;

@Service
public class CategoryCommandService implements ICategoryCommandService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category saveOrUpdate(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public void deleteCategory(Long id) {

		var category = categoryRepository.findById(id).get();

		categoryRepository.delete(category);
	}

}
