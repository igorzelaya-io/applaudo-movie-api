package com.applaudostudios.interview.transactions;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.applaudostudios.interview.sale.Sale;

@Repository
public interface TransactionSaleRepository extends CrudRepository<Sale, String>{

		public List<Sale> findAllByMovieIdAndSaleDateBetween(String movieId, LocalDate startDate, LocalDate endDate);

}
