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

import com.pmh.library.dto.AddPublisherDTO;
import com.pmh.library.dto.PublisherDTO;
import com.pmh.library.entity.Publisher;
import com.pmh.library.repository.PublisherRepository;
import com.pmh.library.response.MessageResponse;
import com.pmh.library.service.PublisherService;

@RestController
@RequestMapping("/api/publisher")
public class PublisherController {
	
	@Autowired
	PublisherRepository publisherRepository;
	
	@Autowired
	PublisherService publisherService;
	
	@GetMapping("/allPublisher")
	public List<PublisherDTO> getAllPusblishers(){
		
		return publisherService.findAllPublisher();
	}
	
	@GetMapping("/findPublisherByID")
	public ResponseEntity<?> getPublisherById(@RequestParam int id) {
		Publisher publisher = publisherService.findPublisherByID(id);
		
		if(publisher == null) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Publisher is not exist!"));
		}
		
		PublisherDTO publisherDTO = new PublisherDTO();
		publisherDTO.setId(publisher.getId());
		publisherDTO.setName(publisher.getName());
		
		return ResponseEntity.ok(publisherDTO);
	}
	
	@PostMapping("/addPublisher")
	public ResponseEntity<?> addPulisher(@Valid @RequestBody AddPublisherDTO publisherDTO) {
		
		Publisher publisher = new Publisher();
		publisher.setName(publisherDTO.getName());
		
		publisherService.createPublisher(publisher);
		
		return ResponseEntity.ok(new MessageResponse("Add Publisher successfully!"));
	}
	
	@PutMapping("/updatePublisher/{id}")
	public ResponseEntity<?> updateAuthor(@PathVariable int id, @RequestBody AddPublisherDTO publisherDTO) {
		Publisher publisher = publisherService.findPublisherByID(id);
		
		if(publisher == null) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Publisher is not exist!"));
		}
		
		publisher.setName(publisherDTO.getName());

		publisherService.updatePublisher(publisher);
		
		return ResponseEntity.ok(new MessageResponse("Update Publisher successfully!"));
	}
}
