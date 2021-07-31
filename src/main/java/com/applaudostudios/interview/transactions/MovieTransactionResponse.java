package com.applaudostudios.interview.transactions;

import java.util.List;

import com.applaudostudios.interview.rental.Rental;
import com.applaudostudios.interview.sale.Sale;

public class MovieTransactionResponse {

	private String movieId;
	
	private List<Rental> rentals;
	
	private List<Sale> sales;
	
	private Integer totalRevenue;
	
	private List<String> customers;
	
	public MovieTransactionResponse() {
		super();
	}

	public MovieTransactionResponse(String movieId, List<Rental> rentals, List<Sale> sales, Integer totalRevenue,
			List<String> customers) {
		super();
		this.movieId = movieId;
		this.rentals = rentals;
		this.sales = sales;
		this.totalRevenue = totalRevenue;
		this.customers = customers;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	public List<Sale> getSales() {
		return sales;
	}

	public void setSales(List<Sale> sales) {
		this.sales = sales;
	}

	public Integer getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(Integer totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public List<String> getCustomers() {
		return customers;
	}

	public void setCustomers(List<String> customers) {
		this.customers = customers;
	}
}
