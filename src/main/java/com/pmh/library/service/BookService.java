package com.pmh.library.service;

import java.util.List;

import com.pmh.library.entity.Book;

public interface BookService {
	
	public List<Book> findAllBooks();
	
	public Book findBookByID(int id);
	
	public Book findBookByName(String name);
	
	public void createBook(Book book);
	
	public void updateBook(Book book);
}
