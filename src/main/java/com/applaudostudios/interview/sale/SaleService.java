package com.applaudostudios.interview.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.applaudostudios.interview.exception.ResourceNotFoundException;
import com.applaudostudios.interview.movie.Movie;
import com.applaudostudios.interview.movie.MovieService;
import com.applaudostudios.interview.movie.MovieStatus;

@Service
public class SaleService {

	@Autowired
	private SaleRepository saleRepository;
	
	@Autowired
	private MovieService movieService;
	
	public Sale saveSale(SaleRequest saleRequest) {
		Movie retrievedMovie = movieService.getMovieById(saleRequest.getMovieId())
											.orElseThrow(() -> new ResourceNotFoundException(Movie.class, "id", saleRequest.getMovieId()));
		if(retrievedMovie.getStock() > 0 && retrievedMovie.getMovieStatus().equals(MovieStatus.ACTIVE)) {
			Sale sale = new Sale();
			sale = sale.movie(retrievedMovie).saleCustomerEmail(saleRequest.getCustomerEmail());
			if(saleRequest.getPrice() == null) {
				sale.setSalePrice(retrievedMovie.getSalePrice());
			}
			retrievedMovie.setStock(retrievedMovie.getStock() - 1);
			movieService.updateMovie(retrievedMovie.getMovieId(), retrievedMovie);
			saleRepository.save(sale);
			return sale;
		}
		else if(retrievedMovie.getMovieStatus().equals(MovieStatus.ACTIVE)) {
			retrievedMovie.setMovieStatus(MovieStatus.INACTIVE);
			movieService.updateMovie(retrievedMovie.getMovieId(), retrievedMovie);
		}
		throw new RuntimeException("Movie not available");
	}


}
