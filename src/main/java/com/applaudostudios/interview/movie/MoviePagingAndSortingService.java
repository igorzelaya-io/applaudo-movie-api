package com.applaudostudios.interview.movie;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MoviePagingAndSortingService {

	@Autowired
	private MoviePagingAndSortingRepository pagingAndSortingRepository;
	
	
	public List<Movie> findAllByPage(int pageNumber, int pageSize){
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
		return pagingAndSortingRepository.findAll(pageable).getContent();
	}
	
	public List<Movie> findAllSorted(String fieldOrderSorting){
		String[] orderAndField = fieldOrderSorting.split(",");
		String field = orderAndField[0].trim();
		String order = orderAndField[1].trim();
		Sort sort = order.equals("asc") ? 
					Sort.by(field).ascending() : 
					Sort.by(field).descending();
		Iterable<Movie> iterable = () -> pagingAndSortingRepository.findAll(sort).iterator();
		return StreamSupport
						.stream(iterable.spliterator(), false)
						.collect(Collectors.toList());
	}
	
	public Page<Movie> findAllByPageSortedBy(int pageNumber, int pageSize, String sortedBy){
		String[] sortingOrderAndField = sortedBy.split(",");
		String field = sortingOrderAndField[0].trim();
		String order = sortingOrderAndField[1].trim();
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, 
											order.equals("asc") ? 
											Sort.by(field).ascending() : 
											Sort.by(field).descending());
		return pagingAndSortingRepository.findAll(pageable);
		
	}
	
	public QueryResponse findMoviesAndMetadata(int pageNumber, int pageSize, String sortedBy) {
		Page<Movie> moviePage = this.findAllByPageSortedBy(pageNumber, pageSize, sortedBy);
		QueryResponse query = new QueryResponse();
		query.setContent(moviePage.getContent());
		query.setSize(pageSize);
		query.setNumberOfElements(moviePage.getNumberOfElements());
		query.setTotalElements(moviePage.getContent().size());
		query.setTotalPages(moviePage.getTotalPages());
		query.setNumber(moviePage.getNumber());
		return query;
	}
}
