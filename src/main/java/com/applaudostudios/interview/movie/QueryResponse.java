package com.applaudostudios.interview.movie;

import java.util.List;

public class QueryResponse {

	private int size;
	
	private int numberOfElements;
	
	private int totalElements;
	
	private int totalPages;
	
	private int number;
	
	private List<Movie> content;

	public QueryResponse(int size, int numberOfElements, int totalElements, int totalPages, int number,
			List<Movie> content) {
		super();
		this.size = size;
		this.numberOfElements = numberOfElements;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.number = number;
		this.content = content;
	}
	
	public QueryResponse() {
		super();
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	public int getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(int totalElements) {
		this.totalElements = totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<Movie> getContent() {
		return content;
	}

	public void setContent(List<Movie> content) {
		this.content = content;
	}
	
}
