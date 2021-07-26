package com.applaudostudios.interview.movie;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.applaudostudios.interview.exception.ResourceNotFoundException;

/**
 * Service used for Movie entity CRUD Operations.
 * @author igorzelaya
 */
@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepo;
	
	/**
	 * Find all movies available.
	 * @return
	 */
	public List<Movie> findAllMovies(){
		
		Iterable<Movie> iterable = () -> movieRepo.findAll().iterator();
		return StreamSupport
						.stream(iterable.spliterator(), false)
						.collect(Collectors.toList());
	}
	
	/**
	 * Finds all active movies.
	 * @return
	 */
	public List<Movie> findAllActiveMovies(){
		Iterable<Movie> iterable = () -> movieRepo.findAllActiveMovies().iterator();
		return StreamSupport
						.stream(iterable.spliterator(), false)
						.collect(Collectors.toList());
	}
	/**
	 * Finds a movie by its title ignoring case.\
	 * @param movieTitle
	 * @return
	 */
	public Optional<Movie> findMovieByTitle(String movieTitle){
		return movieRepo.findMovieByTitleIgnoreCase(movieTitle);
	}
	
	/**
	 * Get movie by its generated ID.
	 * @param movieId
	 * @return
	 */
	public Optional<Movie> getMovieById(String movieId){
		return movieRepo.findById(movieId);
	}
	
	/**
	 * Save and generate UUID for Movie entity.
	 * @param movieToSave
	 */
	public void saveMovie(Movie movieToSave){
		movieToSave = movieToSave.movieId(UUID.randomUUID().toString());
		movieToSave = movieToSave.movieStatus(MovieStatus.ACTIVE);
		movieRepo.save(movieToSave);
	}
	
	/**
	 * Save updated movie.
	 * @param movieToUpdate
	 */
	public void updateMovie(String movieId, Movie newMovie) {
		Movie retrievedMovie = this.getMovieById(movieId)
								   .orElseThrow(() -> new ResourceNotFoundException(Movie.class, "id", movieId));
		newMovie.setMovieId(movieId);
		retrievedMovie = newMovie;
		movieRepo.save(retrievedMovie);
	}
	
	/**
	 * Patch and save new movie.
	 * @param movieId
	 * @param newMovie
	 */
	public void patchMovie(String movieId, Movie newMovie) {
		Movie retrievedMovie = this.getMovieById(movieId)
								   .orElseThrow(() -> new ResourceNotFoundException(Movie.class, "id", movieId));
		retrievedMovie = retrievedMovie
									.title(newMovie.getTitle())
									.stock(newMovie.getStock())
									.rentalPrice(newMovie.getRentalPrice())
									.salePrice(newMovie.getSalePrice());
		if(newMovie.getDescription() != null) {
			retrievedMovie.setDescription(newMovie.getDescription());
		}
		movieRepo.save(retrievedMovie);
	}
	
	/**
	 * Delete movie by its ID.
	 * @param movieId
	 */
	public void deleteMovieById(String movieId) {
		movieRepo.deleteById(movieId);
	}
	
	/**
	 * Count number of movies available in database.
	 * @return
	 */
	public long countMovies() {
		return movieRepo.count();
	}
}
