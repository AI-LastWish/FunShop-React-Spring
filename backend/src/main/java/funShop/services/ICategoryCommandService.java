package funShop.services;

import funShop.domain.Category;

public interface ICategoryCommandService {

	Category saveOrUpdate(Category category);

	void deleteCategory(Long id);

}
