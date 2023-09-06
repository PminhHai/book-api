package com.pmh.library.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pmh.library.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

	List<Author> findAll();
	
	Author findById(int id);

	Author findByName(String name);
}
