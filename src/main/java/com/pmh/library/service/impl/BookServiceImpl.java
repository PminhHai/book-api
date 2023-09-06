package com.pmh.library.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmh.library.entity.Book;
import com.pmh.library.repository.BookRepository;
import com.pmh.library.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	@Transactional
	@Override
	public List<Book> findAllBooks() {
		
		return bookRepository.findAll();
	}
	
	@Transactional
	@Override
	public Book findBookByID(int id) {
		
		return bookRepository.findById(id);
	}
	
	@Transactional
	@Override
	public Book findBookByName(String name) {
		
		return bookRepository.findByName(name);
	}
	
	@Transactional
	@Override
	public void createBook(Book book) {
		bookRepository.save(book);
	}
	
	@Transactional
	@Override
	public void updateBook(Book book) {
		bookRepository.save(book);
	}

}
