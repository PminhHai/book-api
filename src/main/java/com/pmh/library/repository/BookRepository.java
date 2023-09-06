package com.pmh.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pmh.library.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
	List<Book> findAll();
	
	Book findById(int id);

	Book findByName(String name);
}
