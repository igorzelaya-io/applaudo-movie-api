package com.applaudostudios.interview.like;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.applaudostudios.interview.movie.Movie;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vladmihalcea.hibernate.type.json.JsonStringType;

@JsonSerialize
@Table(name = "like")
@Entity
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Like {

	@Id
	@Column(name = "like_id")
	@JsonProperty("likeId")
	private String likeId;
	
	@Column(name = "likes_amount")
	@JsonProperty("likes")
	private Integer likesAmount;
	
	@Type(type = "json")
	@Column(name = "likes_customer_emails", columnDefinition = "json")
	private String[] likesCustomerEmails;
	
	@ManyToOne
	@JoinColumn(name = "movie_id")
	@JsonProperty("movie")
	private Movie movie;
	
	public Like() {
		super();
		this.likeId = UUID.randomUUID().toString();
	}

	public Like(String likeId, Integer likesAmount, String[] likesCustomerEmails, Movie movie) {
		super();
		this.likeId = likeId;
		this.likesAmount = likesAmount;
		this.likesCustomerEmails = likesCustomerEmails;
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

	public String[] getLikesCustomerEmails() {
		return likesCustomerEmails;
	}

	public void setLikesCustomerEmails(String[] likesCustomerEmails) {
		this.likesCustomerEmails = likesCustomerEmails;
	}
	
	public Like likesCustomerEmails(String customerEmail) {
		this.likesCustomerEmails[this.likesCustomerEmails.length + 2] = customerEmail;
		return this;
	}

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
