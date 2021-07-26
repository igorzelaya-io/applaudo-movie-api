package com.applaudostudios.interview.movie;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviePagingAndSortingRepository extends PagingAndSortingRepository<Movie, String>{

	Page<Movie> findAll(Pageable pageable);
	
}
