package com.applaudostudios.interview.movie;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * DTO used to represent Movie entity.
 * @author igorzelaya
 *
 */
@JsonSerialize
@Entity
@Table(name = "movie")
public class Movie {
	
	@JsonProperty("movieId")
	@Column(name = "movie_id", nullable = false, length = 36)
	@Id
	private String movieId;
	
	@JsonProperty("title")
	@Column(name = "movie_title", nullable = false, length = 64)
	@NotBlank
	private String title;
	
	@JsonProperty("description")
	@Column(name = "movie_description", nullable = true, length = 64)
	private String description;
	
	@JsonProperty("stock")
	@Column(name = "movie_stock", nullable = false)
	@NotNull
	private Integer stock;
	
	@JsonProperty("rentalPrice")
	@Column(name = "movie_rental_price", nullable = false)
	@NotNull
	private Integer rentalPrice;
	
	@JsonProperty("salePrice")
	@Column(name = "movie_sale_price", nullable = false)
	@NotNull
	private Integer salePrice;
	
	@Column(name = "movie_status", nullable = false)
	@JsonIgnore
	@Enumerated(EnumType.ORDINAL)
	private MovieStatus movieStatus;
	
	@Transient
	private boolean available = true;
	
	//MOVIE STATUS
	
	public Movie() {
		super();
		this.movieId = UUID.randomUUID().toString();
	}

	public Movie(String title, String description, Integer stock, Integer rentalPrice, Integer salePrice, MovieStatus status) {
		super();
		this.title = title;
		this.description = description;
		this.stock = stock;
		this.rentalPrice = rentalPrice;
		this.salePrice = salePrice;
		this.movieStatus = status;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	
	public Movie movieId(String movieId) {
		setMovieId(movieId);
		return this;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Movie title(String title) {
		setTitle(title);
		return this;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Movie description(String description) {
		setDescription(description);
		return this;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	public Movie stock(Integer stock) {
		setStock(stock);
		return this;
	}

	public Integer getRentalPrice() {
		return rentalPrice;
	}

	public void setRentalPrice(Integer rentalPrice) {
		this.rentalPrice = rentalPrice;
	}
	
	public Movie rentalPrice(Integer rentalPrice) {
		setRentalPrice(rentalPrice);
		return this;
	}

	public Integer getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Integer salePrice) {
		this.salePrice = salePrice;
	}
	
	public Movie salePrice(Integer salePrice) {
		setSalePrice(salePrice);
		return this;
	}
	
	public MovieStatus getMovieStatus() {
		return this.movieStatus;
	}
	
	public void setMovieStatus(MovieStatus movieStatus) {
		this.movieStatus = movieStatus;
	}
	
	public Movie movieStatus(MovieStatus movieStatus) {
		setMovieStatus(movieStatus);
		return this;
	}
	
	public boolean isAvailable() {
		return this.available;
	}
	
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	public Movie available(boolean available) {
		setAvailable(available);
		return this;
	}
}
