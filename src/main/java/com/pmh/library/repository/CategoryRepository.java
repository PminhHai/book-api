package com.pmh.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pmh.library.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	List<Category> findAll();
	
	Category findById(int id);

	Category findByName(String name);
}
