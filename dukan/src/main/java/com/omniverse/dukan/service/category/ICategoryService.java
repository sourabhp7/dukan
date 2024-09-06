package com.omniverse.dukan.service.category;

import java.util.List;

import com.omniverse.dukan.model.Category;

public interface ICategoryService {
	
	Category getCategoryById(Long id);
	Category CatgeoryByName(String name);
	List<Category>getAllCategories();
	Category addCategory(Category category);
	Category updateCategory(Category category,Long id);
	void deleteCategoryById(Long id);

}
