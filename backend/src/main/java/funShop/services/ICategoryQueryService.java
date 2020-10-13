package funShop.services;

import funShop.domain.Category;

public interface ICategoryQueryService {

	Iterable<Category> getAllCategories();

	Category getCategory(Long id);

}
