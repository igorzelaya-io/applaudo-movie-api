package com.applaudostudios.interview.customerEmail;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class CustomerEmail {

	@JsonProperty
	private String email;
	
	public CustomerEmail() {
		super();
	}
	
	public CustomerEmail(String customerEmail) {
		this.email = customerEmail;
	}
	
	public String getCustomerEmail() {
		return this.email;
	}
	
	public void setCustomerEmail(String customerEmail) {
		this.email = customerEmail;
	}
	
}
