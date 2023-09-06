package com.pmh.library.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.pmh.library.dto.CategoryDTO;
import com.pmh.library.entity.Category;
import com.pmh.library.repository.CategoryRepository;
import com.pmh.library.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Transactional
	@Override
	public List<CategoryDTO> findAllCategory() {
		
		List<Category> categories = categoryRepository.findAll();
		
		List<CategoryDTO> categoryDTOs = new ArrayList<>();
		
		for(int i = 0; i < categories.size();i++) {
			
			CategoryDTO categoryDTO = new CategoryDTO();
			
			categoryDTO.setId(categories.get(i).getId());
			categoryDTO.setName(categories.get(i).getName());
			
			categoryDTOs.add(categoryDTO);
		}
		
		return categoryDTOs;
	}
	
	@Transactional
	@Override
	public Category findCategoryByID(int id) {
		
		return categoryRepository.findById(id);			
	}
	
	@Transactional
	@Override
	public Category findCategoryByName(String name) {
		
		return categoryRepository.findByName(name);
	}
	
	@Transactional
	@Override
	public void createCategory(Category category) {
		categoryRepository.save(category);
	}
	
	@Transactional
	@Override
	public void updateCategory(Category category) {
		categoryRepository.save(category);
		
	}
	
	@Transactional
	@Override
	public void deleteCategory(int id) {
		Category category = categoryRepository.findById(id);
		
		if(category != null) {
			categoryRepository.deleteById(id);
		}
	}

}
