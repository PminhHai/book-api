package com.pmh.library.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pmh.library.dto.AddCategoryDTO;
import com.pmh.library.dto.CategoryDTO;
import com.pmh.library.entity.Category;
import com.pmh.library.repository.CategoryRepository;
import com.pmh.library.response.MessageResponse;
import com.pmh.library.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/allCategory")
	public List<CategoryDTO> getAllCategories(){
		
		return categoryService.findAllCategory();
	}
	
	@GetMapping("/findCategoryByID")
	public ResponseEntity<?> getCategoryById(@RequestParam int id) {
		Category category = categoryService.findCategoryByID(id);
		
		if(category == null) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Category is not exist!"));
		}
		
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(category.getId());
		categoryDTO.setName(category.getName());
		
		return ResponseEntity.ok(categoryDTO);
	}
	
	@PostMapping("/addCategory")
	public ResponseEntity<?> addCategory(@Valid @RequestBody AddCategoryDTO categoryDTO) {
		
		Category category = new Category();
		category.setName(categoryDTO.getName());
		
		categoryService.createCategory(category);
		
		return ResponseEntity.ok(new MessageResponse("Add category successfully!"));
	}
	
	@PutMapping("/updateCategory/{id}")
	public ResponseEntity<?> updateAuthor(@PathVariable int id, @RequestBody AddCategoryDTO categoryDTO) {
		Category category = categoryService.findCategoryByID(id);
		
		if(category == null) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Category is not exist!"));
		}
		
		category.setName(categoryDTO.getName());

		categoryRepository.save(category);
		
		return ResponseEntity.ok(new MessageResponse("Update category successfully!"));
	}
}
