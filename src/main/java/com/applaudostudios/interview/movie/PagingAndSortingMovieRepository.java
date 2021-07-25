package com.applaudostudios.interview.movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagingAndSortingMovieRepository extends CrudRepository<Movie, String>{

	Iterable<Movie> findAll(Sort sort);
	
	Page<Movie> findAll(Pageable pageable);
	
}
