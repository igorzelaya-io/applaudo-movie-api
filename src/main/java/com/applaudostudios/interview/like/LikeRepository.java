package com.applaudostudios.interview.like;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.applaudostudios.interview.movie.Movie;

@Repository
public interface LikeRepository extends CrudRepository<Like, String>{
	
	//@Query("SELECT l from Like l WHERE l.movie.movieId = :movieId")
	Optional<Like> findByMovie_MovieId(String movieId);

}
