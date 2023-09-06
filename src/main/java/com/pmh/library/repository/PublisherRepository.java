package com.pmh.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pmh.library.entity.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
	
	List<Publisher> findAll();
	
	Publisher findById(int id);

	Publisher findByName(String name);

}
