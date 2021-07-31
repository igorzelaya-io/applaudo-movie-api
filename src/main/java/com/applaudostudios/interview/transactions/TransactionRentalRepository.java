package com.applaudostudios.interview.transactions;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.applaudostudios.interview.rental.Rental;

@Repository
public interface TransactionRentalRepository extends CrudRepository<Rental, String>{
	
	public List<Rental> findAllByMovieIdAndRentalDateBetween(String movieId, LocalDate startDate, LocalDate endDate);
}
