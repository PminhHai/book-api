package com.pmh.library.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmh.library.dto.AuthorDTO;
import com.pmh.library.entity.Author;
import com.pmh.library.repository.AuthorRepository;
import com.pmh.library.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Transactional
	@Override
	public List<AuthorDTO> findAllAuthor() {
		
		List<Author> authors = authorRepository.findAll();
		
		List<AuthorDTO> authorDTOs = new ArrayList<>();
		
		for(int i = 0; i < authors.size(); i++) {
			
			AuthorDTO authorDTO = new AuthorDTO();
			
			authorDTO.setId(authors.get(i).getId());
			authorDTO.setName(authors.get(i).getName());
			authorDTO.setDescription(authors.get(i).getDescription());
			
			authorDTOs.add(authorDTO);
		}
		
		return authorDTOs;
	}
	
	@Transactional
	@Override
	public Author findAuthorByID(int id) {
		
		return authorRepository.findById(id);
	}
	
	@Transactional
	@Override
	public Author findAuthorByName(String name) {
		
		return authorRepository.findByName(name);
	}
	
	@Transactional
	@Override
	public void createAuthor(Author author) {
		authorRepository.save(author);
		
	}
	
	@Transactional
	@Override
	public void updateAuthor(Author author) {
		authorRepository.save(author);
		
	}
	
	@Transactional
	@Override
	public void deleteAuthor(int id) {
		
		Author author = findAuthorByID(id);
		
		if(author != null) {
			authorRepository.deleteById(author.getId());
		}
	}
	
	
}
