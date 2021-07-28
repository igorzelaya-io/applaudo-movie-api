package com.applaudostudios.interview.rental;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.applaudostudios.interview.movie.Movie;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@Entity
@Table(name = "rental")
public class Rental {

	@Id
	@Column(name = "rental_id")
	@JsonProperty("rentalId")
	private String rentalId;
	
	@JsonProperty("rentalPrice")
	@Column(name = "rental_price")
	private BigDecimal rentalPrice;
	
	@JsonProperty("rentalCustomerEmail")
	@Column(name = "rental_customer_email")
	private String rentalCustomerEmail;
	
	@ManyToOne
	@JoinColumn(name = "movie_id")
	@JsonProperty
	private Movie movie;
	
	public Rental() {
		super();
		this.rentalId = UUID.randomUUID().toString();
	}
	
	public Rental(BigDecimal rentalPrice, String rentalCustomerEmail, Movie movie) {
		this.rentalCustomerEmail = rentalCustomerEmail;
		this.rentalPrice = rentalPrice;
		this.movie = movie;
	}

	public String getRentalId() {
		return rentalId;
	}

	public void setRentalId(String rentalId) {
		this.rentalId = rentalId;
	}
	
	public Rental rentalId(String rentalId) {
		this.setRentalId(rentalId);
		return this;
	}

	public BigDecimal getRentalPrice() {
		return rentalPrice;
	}

	public void setRentalPrice(BigDecimal rentalPrice) {
		this.rentalPrice = rentalPrice;
	}
	
	public Rental rentalPrice(BigDecimal rentalPrice) {
		this.setRentalPrice(rentalPrice);
		return this;
	}

	public String getRentalCustomerEmail() {
		return rentalCustomerEmail;
	}

	public void setRentalCustomerEmail(String rentalCustomerEmail) {
		this.rentalCustomerEmail = rentalCustomerEmail;
	}
	
	public Rental rentalCustomerEmail(String rentalCustomerEmail) {
		this.setRentalCustomerEmail(rentalCustomerEmail);
		return this;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	public Rental movie(Movie movie) {
		this.setMovie(movie);
		return this;
	}

}
