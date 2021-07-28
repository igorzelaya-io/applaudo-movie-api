package com.applaudostudios.interview.like;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class LikeRequest {
	
	@JsonProperty("movieId")
	@NotBlank
	private String movieId;
	
	@JsonProperty("customerEmail")
	@NotBlank
	private String customerEmail;
	
	public LikeRequest(){
		super();
	}

	public LikeRequest(String movieId, String customerEmail) {
		super();
		this.movieId = movieId;
		this.customerEmail = customerEmail;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	
}
