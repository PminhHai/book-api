package com.pmh.library.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmh.library.dto.PublisherDTO;
import com.pmh.library.entity.Publisher;
import com.pmh.library.repository.PublisherRepository;
import com.pmh.library.service.PublisherService;

@Service
public class PublisherServiceImpl implements PublisherService {
	
	@Autowired
	PublisherRepository publisherRepository;
	
	@Override
	public List<PublisherDTO> findAllPublisher() {
		
		List<Publisher> publishers = publisherRepository.findAll();
		
		List<PublisherDTO> publisherDTOs = new ArrayList<>();
		
		for(int i = 0; i < publishers.size(); i++) {
			PublisherDTO publisherDTO = new PublisherDTO();
			publisherDTO.setId(publishers.get(i).getId());
			publisherDTO.setName(publishers.get(i).getName());
			
			publisherDTOs.add(publisherDTO);
		}
		
		return publisherDTOs;
	}

	@Override
	public Publisher findPublisherByID(int id) {
		
		return publisherRepository.findById(id);
	}

	@Override
	public Publisher findPublisherByName(String name) {
		
		return publisherRepository.findByName(name);
	}

	@Override
	public void createPublisher(Publisher publisher) {
		publisherRepository.save(publisher);
		
	}

	@Override
	public void updatePublisher(Publisher publisher) {
		publisherRepository.save(publisher);
		
	}

	@Override
	public void deletePublisher(int id) {
		publisherRepository.deleteById(id);
		
	}

}
