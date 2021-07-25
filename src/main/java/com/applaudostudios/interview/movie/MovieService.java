package com.applaudostudios.interview.movie;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service used for Movie entity CRUD Operations.
 * @author igorzelaya
 */
@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepo;
	
	public Iterable<Movie> findAllMovies(){
		return movieRepo.findAll();
	}
	
	public Optional<Movie> getMovieById(String movieId){
		return movieRepo.findById(movieId);
	}
	
	public void saveMovie(Movie movieToSave){
		movieToSave = movieToSave.movieId(UUID.randomUUID().toString());
		movieToSave = movieToSave.movieStatus(MovieStatus.ACTIVE);
		movieRepo.save(movieToSave);
	}
	
	public void updateMovie(Movie movieToUpdate) {
		movieRepo.save(movieToUpdate);
	}
	
	public void deleteMovieById(String movieId) {
		movieRepo.deleteById(movieId);
	}
}
