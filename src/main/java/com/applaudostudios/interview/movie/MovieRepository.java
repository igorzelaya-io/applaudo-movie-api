package com.applaudostudios.interview.movie;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface used for Movie Entity CRUD Operations.
 * @author igorzelaya
 */
@Repository
public interface MovieRepository extends CrudRepository<Movie, String>{
	
	
	
}
