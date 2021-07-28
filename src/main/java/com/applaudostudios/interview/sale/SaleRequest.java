package com.applaudostudios.interview.sale;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class SaleRequest {
	
	@JsonProperty("movieId")
	@NotBlank
	private String movieId;
	
	@JsonProperty("customerEmail")
	@NotBlank
	private String customerEmail;
	
	@JsonProperty
	private BigDecimal price;
	
	public SaleRequest() {
		super();
	}
	
	public SaleRequest(String movieId, String customerEmail, BigDecimal price) {
		this.movieId = movieId;
		this.customerEmail = customerEmail;
		this.price = price;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
