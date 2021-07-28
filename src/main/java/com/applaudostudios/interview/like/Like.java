package com.applaudostudios.interview.like;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vladmihalcea.hibernate.type.json.JsonStringType;

@JsonSerialize
@Table(name = "like")
@Entity
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Like {

	@Id
	@Column(name = "movie_id")
	@JsonProperty("movieId")
	@NotBlank
	private String movieId;
	
	@Column(name = "likes_amount")
	@JsonProperty("likes")
	private Integer likes;
	
	@JsonProperty("customerEmail")
	@NotBlank
	@Transient
	private String customerEmail;
	
	@Type(type = "json")
	@Column(name = "likes_customer_emails", columnDefinition = "json")
	private String[] likesCustomerEmails;
	
	public Like() {
		super();
	}

	public Like(Integer likes, String customerEmail, String[] likesCustomerEmails) {
		super();
		this.likes = likes;
		this.customerEmail = customerEmail;
		this.likesCustomerEmails = likesCustomerEmails;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}
	
	public Like likes(Integer likes) {
		this.setLikes(likes);
		return this;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	
	public Like customerEmail(String customerEmail) {
		this.setCustomerEmail(customerEmail);
		return this;
	}

	public String[] getLikesCustomerEmails() {
		return likesCustomerEmails;
	}

	public void setLikesCustomerEmails(String[] likesCustomerEmails) {
		this.likesCustomerEmails = likesCustomerEmails;
	}
	
	public Like likesCustomerEmails(String customerEmail) {
		String[] customerEmails = this.getLikesCustomerEmails();
		customerEmails[customerEmails.length + 2] = customerEmail;
		return this; 
	}

}
