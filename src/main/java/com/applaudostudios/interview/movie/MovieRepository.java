package com.applaudostudios.interview.movie;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface used for Movie Entity CRUD Operations.
 * @author igorzelaya
 */
@Repository
public interface MovieRepository extends CrudRepository<Movie, String>{
	
	@Query(value = "SELECT * FROM applaudostudios.movie WHERE movie_status = 0",
			nativeQuery = true)
	Iterable<Movie> findAllActiveMovies();
	
	Optional<Movie> findMovieByTitleIgnoreCase(String title);
}
