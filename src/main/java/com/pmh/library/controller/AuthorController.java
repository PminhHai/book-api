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

import com.pmh.library.dto.AuthorAddDTO;
import com.pmh.library.dto.AuthorDTO;
import com.pmh.library.entity.Author;
import com.pmh.library.repository.AuthorRepository;
import com.pmh.library.response.MessageResponse;
import com.pmh.library.service.AuthorService;

@RestController
@RequestMapping("/api/author")
public class AuthorController {
	
	@Autowired
	AuthorService authorService;
	
	@Autowired
	AuthorRepository authorRepository;
	
	@GetMapping("/allAuthor")
	public List<AuthorDTO> getAllAuthors(){
		
		return authorService.findAllAuthor();
	}
	
	@GetMapping("/findAuthorByID")
	public ResponseEntity<?> getAuthorById(@RequestParam int id) {
		Author author = authorService.findAuthorByID(id);
		
		if(author == null) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Author is not exist!"));
		}
		
		AuthorDTO authorDTO = new AuthorDTO();
		authorDTO.setId(author.getId());
		authorDTO.setName(author.getName());
		authorDTO.setDescription(author.getDescription());
		
		return ResponseEntity.ok(authorDTO);
	}

	@PostMapping("/addAuthor")
	public ResponseEntity<?> addAuthor(@Valid @RequestBody AuthorAddDTO authorDTO) {
		
		Author author = new Author();
		author.setName(authorDTO.getName());
		author.setDescription(authorDTO.getDescription());
		
		authorRepository.save(author);
		
		return ResponseEntity.ok(new MessageResponse("Add author successfully!"));
	}
	
	@PutMapping("/updateAuthor/{id}")
	public ResponseEntity<?> updateAuthor(@PathVariable int id, @RequestBody AuthorAddDTO authorDTO) {
		Author author = authorService.findAuthorByID(id);
		
		if(author == null) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Author is not exist!"));
		}
		
		author.setName(authorDTO.getName());
		author.setDescription(authorDTO.getDescription());
		
		authorRepository.save(author);
		
		return ResponseEntity.ok(new MessageResponse("Update author successfully!"));
	}
}
