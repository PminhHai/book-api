package com.pmh.library.service;

import java.util.List;

import com.pmh.library.dto.AuthorDTO;
import com.pmh.library.entity.Author;

public interface AuthorService {
	
	public List<AuthorDTO> findAllAuthor();
	
	public Author findAuthorByID(int id);
	
	public Author findAuthorByName(String name);
	
	public void createAuthor(Author author);
	
	public void updateAuthor(Author author);
	
	public void deleteAuthor(int id);
}
