package com.applaudostudios.interview.rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.applaudostudios.interview.exception.ResourceNotFoundException;
import com.applaudostudios.interview.movie.Movie;
import com.applaudostudios.interview.movie.MovieService;
import com.applaudostudios.interview.movie.MovieStatus;

@Service
public class RentalService {

	@Autowired
	private RentalRepository rentalRepository;
	
	@Autowired
	private MovieService movieService;
	
	public Rental saveRental(RentalRequest rentalRequest) {
		Movie retrievedMovie = movieService.getMovieById(rentalRequest.getMovieId())
											.orElseThrow(() -> new ResourceNotFoundException(Movie.class, "id", rentalRequest.getMovieId()));
		if(retrievedMovie.getStock() > 0 && retrievedMovie.getMovieStatus().equals(MovieStatus.ACTIVE)) {
			Rental rental = new Rental();
			rental = rental.movie(retrievedMovie)
							.rentalCustomerEmail(rentalRequest.getCustomerEmail());
			if(rentalRequest.getPrice() == null) {
				rental.setRentalPrice(retrievedMovie.getRentalPrice());
			}
			retrievedMovie.setStock(retrievedMovie.getStock() - 1);
			movieService.updateMovie(retrievedMovie.getMovieId(), retrievedMovie);
			rentalRepository.save(rental);
			return rental;
		}
		else if(retrievedMovie.getMovieStatus().equals(MovieStatus.ACTIVE)) {
			retrievedMovie.setMovieStatus(MovieStatus.INACTIVE);
			movieService.updateMovie(retrievedMovie.getMovieId(), retrievedMovie);
		}
		throw new RuntimeException("Movie is not available");
		
	}
	
}
