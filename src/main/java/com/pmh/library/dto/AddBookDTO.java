package com.pmh.library.dto;

import java.util.Set;

public class AddBookDTO {
	
	private String isbn;
	
	private String name;
	
	private String serialName;
	
	private String description;
	
	private Set<Integer> authors;
	
	private Set<Integer> categories;
	
	private Set<Integer> publishers;

	public AddBookDTO() {
		super();
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

	public Set<Integer> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Integer> authors) {
		this.authors = authors;
	}

	public Set<Integer> getCategories() {
		return categories;
	}

	public void setCategories(Set<Integer> categories) {
		this.categories = categories;
	}

	public Set<Integer> getPublishers() {
		return publishers;
	}

	public void setPublishers(Set<Integer> publishers) {
		this.publishers = publishers;
	}
}
