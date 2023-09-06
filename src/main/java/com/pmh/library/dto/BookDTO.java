package com.pmh.library.dto;

import java.util.List;

public class BookDTO {
	
	private int id;
	
	private String isbn;
	
	private String name;
	
	private String serialName;
	
	private String description;
	
	private List<NameDTO> authors;
	
	private List<NameDTO> categories;

	public BookDTO() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSerialName() {
		return serialName;
	}

	public void setSerialName(String serialName) {
		this.serialName = serialName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<NameDTO> getAuthors() {
		return authors;
	}

	public void setAuthors(List<NameDTO> authors) {
		this.authors = authors;
	}

	public List<NameDTO> getCategories() {
		return categories;
	}

	public void setCategories(List<NameDTO> categories) {
		this.categories = categories;
	}
		
}
