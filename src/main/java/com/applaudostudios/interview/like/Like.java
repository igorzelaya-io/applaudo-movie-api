package com.applaudostudios.interview.like;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.applaudostudios.interview.customerEmail.CustomerEmail;
import com.applaudostudios.interview.movie.Movie;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@Table(name = "like")
@Entity
public class Like {

	@Id
	@Column(name = "like_id")
	@JsonProperty("likeId")
	private String likeId;
	
	@Column(name = "likes_amount")
	@JsonProperty("likesAmount")
	private Integer likesAmount;
	
//	@JsonProperty
//	@ElementCollection(fetch = FetchType.LAZY)
//	@CollectionTable(name = "like_customer_emails")
//	@AttributeOverrides({
//			@AttributeOverride(name = "email", column = @Column(name = "email"))
//	})
//	private List<CustomerEmail> likeCustomerEmails = new ArrayList<>();
	
	@OneToOne
	@JoinColumn(name = "movie_id")
	@JsonProperty("movie")
	private Movie movie;
	
	public Like() {
		super();
		this.likeId = UUID.randomUUID().toString();
	}

	public Like(String likeId, Integer likesAmount, List<CustomerEmail> likesCustomerEmails, Movie movie) {
		super();
		this.likeId = likeId;
		this.likesAmount = likesAmount;
		//this.likeCustomerEmails = likesCustomerEmails;
		this.movie = movie;
	}

	public String getLikeId() {
		return likeId;
	}

	public void setLikeId(String likeId) {
		this.likeId = likeId;
	}

	public Integer getLikesAmount() {
		return likesAmount;
	}

	public void setLikesAmount(Integer likesAmount) {
		this.likesAmount = likesAmount;
	}
	
	public Like likesAmount(Integer likesAmount) {
		this.setLikesAmount(likesAmount);
		return this;
	}

//	public List<CustomerEmail> getLikesCustomerEmails() {
//		return likeCustomerEmails;
//	}
//
//	public void setLikesCustomerEmails(List<CustomerEmail> likesCustomerEmails) {
//		this.likeCustomerEmails = likesCustomerEmails;
//	}
//	
//	public Like likesCustomerEmails(CustomerEmail customerEmail) {
//		this.likeCustomerEmails.add(customerEmail);
//		return this;
//	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	public Like movie(Movie movie) {
		this.setMovie(movie);
		return this;
	}

}
