package com.applaudostudios.interview.movie;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.applaudostudios.interview.exception.ResourceNotFoundException;
import com.applaudostudios.interview.response.BaseResponse;
import com.applaudostudios.interview.response.Response;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	@Autowired
	private MoviePagingAndSortingService pagingAndSortingService;
	
	@GetMapping
	public ResponseEntity<?> getMovie(@RequestParam(required = false, defaultValue = "false")boolean unavailable,
									  @RequestParam(required = false)Integer page,
									  @RequestParam(required = false)Integer size,
									  @RequestParam(required = false)String sort,
									  @RequestParam(required = false)String title){
		if(title != null) {
			Movie movieFound = movieService.findMovieByTitle(title)
											.orElseThrow(() -> new ResourceNotFoundException(Movie.class, "title", title));
			return new ResponseEntity<Movie>(movieFound, HttpStatus.OK);
		}
		else if(page != null) { 
			if(size == null) {
				size = 12;
			}
			if(sort == null) {
				sort = "title, asc";
			}
			QueryResponse queryResult = pagingAndSortingService.findMoviesAndMetadata(page, size, sort);
			BaseResponse<QueryResponse> queryResponse = new BaseResponse<>();
			return queryResponse.createResponse(HttpStatus.OK, "Movies retrieved successfully.", queryResult);
		}
		else if(sort != null) {
			List<Movie> movieSortedList = pagingAndSortingService.findAllSorted(sort);
			return new ResponseEntity<List<Movie>>(movieSortedList, HttpStatus.OK);
		}
		else if(unavailable == true) {
			List<Movie> movieList = new ArrayList<>();
			movieList = movieService.findAllMovies();
			return new ResponseEntity<List<Movie>>(movieList, HttpStatus.OK);
		}
		List<Movie> movieList = new ArrayList<>();
		movieList = movieService.findAllActiveMovies();
		return new ResponseEntity<List<Movie>>(movieList, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<? extends Response<Movie>> saveMovie(@Valid @RequestBody Movie movie){
		BaseResponse<Movie> movieResponse = new BaseResponse<>();
		movieService.saveMovie(movie);
		return movieResponse.createResponse(HttpStatus.CREATED, "Movie saved successfully", movie);
	}
	
	@PutMapping(value = "/{movieId}")
	public ResponseEntity<? extends Response<Movie>> updateMovie(@PathVariable String movieId,
																 @Valid @RequestBody(required = true)Movie newMovie){
		BaseResponse<Movie> movieResponse = new BaseResponse<>();
		movieService.updateMovie(movieId, newMovie);
		return movieResponse.createResponse(HttpStatus.OK, "Movie updated successfully", newMovie);
	}
	
	@PatchMapping(value = "/{movieId}")
	public ResponseEntity<? extends Response<Movie>> patchMovie(@PathVariable String movieId,
																@RequestBody @Valid Movie movie){
		movieService.patchMovie(movieId, movie);
		BaseResponse<Movie> movieResponse = new BaseResponse<>();
		return movieResponse.createResponse(HttpStatus.OK, "Movie updated successfully", movie);
	}
	
	
	@DeleteMapping(value = "/{movieId}")
	public ResponseEntity<? extends Response<String>> deleteMovie(@PathVariable String movieId){
		BaseResponse<String> movieResponse = new BaseResponse<>();
		movieService.deleteMovieById(movieId);
		return movieResponse.createResponse(HttpStatus.OK, new StringBuilder("Movie with ID: ").append(movieId).append(" was deleted.").toString(), movieId);
	} 
		
}
