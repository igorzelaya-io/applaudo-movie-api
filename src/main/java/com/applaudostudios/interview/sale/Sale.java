package com.applaudostudios.interview.sale;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.applaudostudios.interview.movie.Movie;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Class used to represent a Sale
 * @author igorzelaya
 *
 */
@JsonSerialize
@Entity
@Table(name = "sale")
public class Sale {

	@Id
	@Column(name = "sale_id", nullable = false, length = 36)
	@JsonProperty("saleId")
	private String saleId;
	
	@Column(name = "sale_customer_email", nullable = false)
	@JsonProperty(value = "saleCustomerEmail")
	@NotBlank
	private String saleCustomerEmail;
	
	@JsonProperty(value = "price")
	@Column(name = "sale_price")
	private BigDecimal salePrice;
	
	@JsonProperty
	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;
	
	public Sale() {
		super();
		this.saleId = UUID.randomUUID().toString();
	}
	
	public Sale(String customerEmail, BigDecimal price, Movie movie) {
		this.saleCustomerEmail = customerEmail;
		this.salePrice = price; 
		this.movie = movie;
	}

	public String getSaleId() {
		return this.saleId;
	}

	public void setSaleId(String id) {
		this.saleId = id;
	}

	public String getSaleCustomerEmail() {
		return saleCustomerEmail;
	}

	public void setSaleCustomerEmail(String customerEmail) {
		this.saleCustomerEmail = customerEmail;
	}
	
	public Sale saleCustomerEmail(String customerEmail) {
		this.setSaleCustomerEmail(customerEmail);
		return this;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal price) {
		this.salePrice = price;
	}
	
	public Sale salePrice(BigDecimal price) {
		this.setSalePrice(price);
		return this;
	}
	
	public Movie getMovie() {
		return this.movie; 
	}
	
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	public Sale movie(Movie movie) {
		this.setMovie(movie);
		return this;
	}
}
