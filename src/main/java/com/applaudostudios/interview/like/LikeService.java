package com.applaudostudios.interview.like;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.applaudostudios.interview.exception.ResourceNotFoundException;
import com.applaudostudios.interview.movie.Movie;
import com.applaudostudios.interview.movie.MovieService;
import com.applaudostudios.interview.movie.MovieStatus;

@Service
public class LikeService {

	@Autowired
	private LikeRepository likeRepository;
	
	@Autowired
	private MovieService movieSevice;
	
	public Optional<Like> findLikeByMovieId(String movieId) {
		return likeRepository.findLikeByMovieId(movieId);
	}
	
	public Like saveLike(LikeRequest likeRequest) {
		Optional<Like> like = this.findLikeByMovieId(likeRequest.getMovieId());
		if(like.isPresent()) {
			Like retrievedLike = like.get();
			retrievedLike = retrievedLike.likesAmount(retrievedLike.getLikesAmount() + 1)
										  .likesCustomerEmails(likeRequest.getCustomerEmail());
			this.likeRepository.save(retrievedLike);
			return retrievedLike;
		}
		Movie retrievedMovie = this.movieSevice.getMovieById(likeRequest.getMovieId())
											  .orElseThrow(() -> new ResourceNotFoundException(Movie.class, "id", likeRequest.getMovieId()));
		
		if(retrievedMovie.getMovieStatus().equals(MovieStatus.ACTIVE)) {
			Like newLike = new Like();
			newLike = newLike.likesAmount(0)
							  .likesCustomerEmails(likeRequest.getCustomerEmail())
							  .movie(retrievedMovie);
			this.likeRepository.save(newLike);
			return newLike;
		} 
		throw new ResourceNotFoundException(Movie.class, "id", likeRequest.getMovieId());
	}
	
	
}
