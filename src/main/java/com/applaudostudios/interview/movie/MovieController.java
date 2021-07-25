package com.applaudostudios.interview.movie;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping
	public ResponseEntity<?> getMovie(@Valid @RequestParam(required = false)String movieId){
		if(movieId == null) {
			Iterable<Movie> iterable = () -> movieService.findAllMovies().iterator();
			List<Movie> movieList = StreamSupport
											.stream(iterable.spliterator(), false)
											.collect(Collectors.toList());
			BaseResponse<List<Movie>> movieResponse = new BaseResponse<>();
			
			if(movieList.isEmpty()) {
				return movieResponse.createResponse(HttpStatus.NO_CONTENT, "No Movies available", movieList);
			}
			return movieResponse.createResponse(HttpStatus.OK, "Movies retrieved successfully", movieList);
		}
		Movie retrievedMovie = movieService.getMovieById(movieId)
											.orElseThrow(() -> new ResourceNotFoundException(Movie.class, "id", movieId));
		
		BaseResponse<Movie> movieResponse = new BaseResponse<>();
		return movieResponse.createResponse(HttpStatus.OK, "Movie retrieved successfully", retrievedMovie);
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
		Movie movieRetrieved = movieService.getMovieById(movieId)
											.orElseThrow(() -> new ResourceNotFoundException(Movie.class, "id", movieId));
		
		movieRetrieved.setTitle(newMovie.getTitle());
		if(newMovie.getDescription() != null) {
			movieRetrieved.setDescription(newMovie.getDescription());
		}
		movieRetrieved.setStock(newMovie.getStock());
		movieRetrieved.setRentalPrice(newMovie.getRentalPrice());
		movieRetrieved.setSalePrice(newMovie.getSalePrice());
		movieService.updateMovie(movieRetrieved);
		return movieResponse.createResponse(HttpStatus.OK, "Movie updated successfully", movieRetrieved);
	}
	
	//PatchMapping
	
	
	@DeleteMapping(value = "/{movieId}")
	public ResponseEntity<? extends Response<String>> deleteMovie(@PathVariable String movieId){
		BaseResponse<String> movieResponse = new BaseResponse<>();
		movieService.deleteMovieById(movieId);
		return movieResponse.createResponse(HttpStatus.OK, new StringBuilder("Movie with ID: ").append(movieId).append(" was deleted.").toString(), movieId);
	} 
		
}
