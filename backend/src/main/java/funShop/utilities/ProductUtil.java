package funShop.utilities;

import java.util.ArrayList;
import java.util.List;

import funShop.domain.Category;

public class ProductUtil {

	public List<Long> CategoriesToCategoryIds(List<Category> categories) {
		List<Long> categoryIds = new ArrayList<Long>();
		
		for (Category category : categories) {
			categoryIds.add(category.getId());
		}
		
		return categoryIds;
	}

}
