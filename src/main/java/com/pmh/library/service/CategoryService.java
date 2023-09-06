package com.pmh.library.service;

import java.util.List;

import com.pmh.library.dto.CategoryDTO;
import com.pmh.library.entity.Category;

public interface CategoryService {
	public List<CategoryDTO> findAllCategory();
	
	public Category findCategoryByID(int id);
	
	public Category findCategoryByName(String name);
	
	public void createCategory(Category category);
	
	public void updateCategory(Category category);
	
	public void deleteCategory(int id);
}
