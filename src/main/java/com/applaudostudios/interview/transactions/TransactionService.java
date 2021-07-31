package com.applaudostudios.interview.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

	@Autowired
	private TransactionRentalRepository transactionRentalRepository;
	
	
//	public MovieTransactionResponse getTransactionsFromMovie(String movieId, String startDate, String endDate) {
//		
//	}
	
}
