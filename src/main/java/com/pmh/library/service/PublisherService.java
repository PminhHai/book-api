package com.pmh.library.service;

import java.util.List;

import com.pmh.library.dto.PublisherDTO;
import com.pmh.library.entity.Publisher;

public interface PublisherService {
	public List<PublisherDTO> findAllPublisher();
	
	public Publisher findPublisherByID(int id);
	
	public Publisher findPublisherByName(String name);
	
	public void createPublisher(Publisher publisher);
	
	public void updatePublisher(Publisher publisher);
	
	public void deletePublisher(int id);
}
