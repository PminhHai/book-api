package com.pmh.library.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import com.pmh.library.dto.AddBookDTO;
import com.pmh.library.dto.BookDTO;
import com.pmh.library.dto.NameDTO;
import com.pmh.library.entity.Author;
import com.pmh.library.entity.Book;
import com.pmh.library.entity.Category;
import com.pmh.library.entity.Publisher;
import com.pmh.library.repository.BookRepository;
import com.pmh.library.response.MessageResponse;
import com.pmh.library.service.AuthorService;
import com.pmh.library.service.BookService;
import com.pmh.library.service.CategoryService;
import com.pmh.library.service.PublisherService;

@RestController
@RequestMapping("/api/book")
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	AuthorService authorService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	PublisherService publisherService;
	
	@GetMapping("/allBooks")
	public List<BookDTO> findAllBooks() {
		
		List<Book> books = bookService.findAllBooks();
		
		List<BookDTO> bookDTOs = new ArrayList<>();
		
		for(int i = 0; i < books.size(); i++) {
			BookDTO bookDTO = new BookDTO();
			
			bookDTO.setId(books.get(i).getId());
			bookDTO.setIsbn(books.get(i).getIsbn());
			bookDTO.setName(books.get(i).getName());
			bookDTO.setSerialName(books.get(i).getSerial());
			bookDTO.setDescription(books.get(i).getDescription());
			
			Set<Author> authors = books.get(i).getAuthors();
			
			List<Author> authors2 = new ArrayList<>();
			authors2.addAll(authors);
			
			List<NameDTO> authorNames = new ArrayList<>();
			for(int j = 0; j < authors2.size(); j++) {
				NameDTO authorName = new NameDTO();
				
				authorName.setName(authors2.get(j).getName());
				authorNames.add(authorName);
			}
			
			Set<Category> categories = books.get(i).getCategories();
			
			List<Category> categories2 = new ArrayList<>();
			categories2.addAll(categories);
			
			List<NameDTO> categoryNames = new ArrayList<>();
			for(int j = 0; j < categories2.size(); j++) {
				NameDTO categoryName = new NameDTO();
				
				categoryName.setName(categories2.get(j).getName());
				categoryNames.add(categoryName);
			}			
			
			bookDTO.setAuthors(authorNames);
			bookDTO.setCategories(authorNames);
					
			bookDTOs.add(bookDTO);
		}
		
		return bookDTOs;
	}
	
	@GetMapping("/bookById")
	public ResponseEntity<?> getBookByID(@RequestParam int id){
		Book book = bookService.findBookByID(id);
		
		if(book == null) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Book is not exist!"));
		}
		
		BookDTO bookDTO = new BookDTO();
		
		bookDTO.setId(book.getId());
		bookDTO.setIsbn(book.getIsbn());
		bookDTO.setName(book.getName());
		bookDTO.setSerialName(book.getSerial());
		bookDTO.setDescription(book.getDescription());
		
		Set<Author> authors = book.getAuthors();
		
		List<Author> authors2 = new ArrayList<>();
		authors2.addAll(authors);
		
		List<NameDTO> authorNames = new ArrayList<>();
		for(int j = 0; j < authors2.size(); j++) {
			NameDTO authorName = new NameDTO();
			
			authorName.setName(authors2.get(j).getName());
			authorNames.add(authorName);
		}
		
		Set<Category> categories = book.getCategories();
		
		List<Category> categories2 = new ArrayList<>();
		categories2.addAll(categories);
		
		List<NameDTO> categoryNames = new ArrayList<>();
		for(int j = 0; j < categories2.size(); j++) {
			NameDTO categoryName = new NameDTO();
			
			categoryName.setName(categories2.get(j).getName());
			categoryNames.add(categoryName);
		}			
		
		bookDTO.setAuthors(authorNames);
		bookDTO.setCategories(authorNames);
				
		return ResponseEntity.ok(bookDTO);
	}
	
	@PostMapping("/addBook")
	public ResponseEntity<?> addBook(@RequestBody AddBookDTO bookDTO) {
		
		Book book = new Book();
		
		book.setIsbn(bookDTO.getIsbn());
		book.setName(bookDTO.getName());
		book.setSerial(bookDTO.getSerialName());
		book.setDescription(bookDTO.getDescription());
		
		Set<Author> authors = new HashSet<>();
		
		for(Integer author : bookDTO.getAuthors()) {
			Author author2 = authorService.findAuthorByID(author);
			
			if(author2 != null) {
				authors.add(author2);
			} else {
				return ResponseEntity
						.badRequest()
						.body(new MessageResponse("Error: Author is not exist!"));
			}
		}
		
		Set<Category> categories = new HashSet<>();
		
		for(Integer category : bookDTO.getCategories()) {
			Category category2 = categoryService.findCategoryByID(category);
			
			if(category2 != null) {
				categories.add(category2);
			} else {
				return ResponseEntity
						.badRequest()
						.body(new MessageResponse("Error: Category is not exist!"));
			}
		}
		
		Set<Publisher> publishers = new HashSet<>();
		
		for(Integer publisher : bookDTO.getPublishers()) {
			Publisher publisher2 = publisherService.findPublisherByID(publisher);
			
			if(publisher2 != null) {
				publishers.add(publisher2);
			} else {
				return ResponseEntity
						.badRequest()
						.body(new MessageResponse("Error: Publisher is not exist!"));
			}
		}
		
		book.setAuthors(authors);
		book.setCategories(categories);
		book.setPublishers(publishers);
		
		bookService.createBook(book);
		
		return ResponseEntity.ok("Add book success");
	}
	
	@PutMapping("/updateBook/{id}")
	public ResponseEntity<?> updateBook(@PathVariable int id, @RequestBody AddBookDTO bookDTO) {
		
		Book book = bookService.findBookByID(id);
		
		if(book == null) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Book is not exist!"));
		}
		
		book.setIsbn(bookDTO.getIsbn());
		book.setName(bookDTO.getName());
		book.setSerial(bookDTO.getSerialName());
		book.setDescription(bookDTO.getDescription());
		
		Set<Author> authors = new HashSet<>();
		
		for(Integer author : bookDTO.getAuthors()) {
			Author author2 = authorService.findAuthorByID(author);
			
			if(author2 != null) {
				authors.add(author2);
			} else {
				return ResponseEntity
						.badRequest()
						.body(new MessageResponse("Error: Author is not exist!"));
			}
		}
		
		Set<Category> categories = new HashSet<>();
		
		for(Integer category : bookDTO.getCategories()) {
			Category category2 = categoryService.findCategoryByID(category);
			
			if(category2 != null) {
				categories.add(category2);
			} else {
				return ResponseEntity
						.badRequest()
						.body(new MessageResponse("Error: Category is not exist!"));
			}
		}
		
		Set<Publisher> publishers = new HashSet<>();
		
		for(Integer publisher : bookDTO.getPublishers()) {
			Publisher publisher2 = publisherService.findPublisherByID(publisher);
			
			if(publisher2 != null) {
				publishers.add(publisher2);
			} else {
				return ResponseEntity
						.badRequest()
						.body(new MessageResponse("Error: Publisher is not exist!"));
			}
		}
		
		book.setAuthors(authors);
		book.setCategories(categories);
		book.setPublishers(publishers);
		
		bookService.createBook(book);
		
		return ResponseEntity.ok("Update book success");
	}
}
