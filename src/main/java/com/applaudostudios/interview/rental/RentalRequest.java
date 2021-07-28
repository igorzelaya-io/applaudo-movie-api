package com.applaudostudios.interview.rental;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class RentalRequest {
	
	@JsonProperty("customerEmail")
	@NotBlank
	private String customerEmail;
	
	@JsonProperty("movieId")
	@NotBlank
	private String movieId;
	
	@JsonProperty("price")
	private BigDecimal price;
	
	public RentalRequest() {
		super();
	}

	public RentalRequest(String customerEmail, String movieId, BigDecimal price) {
		super();
		this.customerEmail = customerEmail;
		this.movieId = movieId;
		this.price = price;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}
