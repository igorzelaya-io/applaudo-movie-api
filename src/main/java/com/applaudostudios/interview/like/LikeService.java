package com.applaudostudios.interview.like;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

	@Autowired
	private LikeRepository likeRepository;
	
	public Optional<Like> findLikeByMovieId(String movieId) {
		return likeRepository.findById(movieId);
	}
	
	public Like saveLike(Like likeToSave) {
		Optional<Like> like = this.findLikeByMovieId(likeToSave.getMovieId());
		if(like.isPresent()) {
			Like retrievedLike = like.get();
			retrievedLike = retrievedLike.likes(retrievedLike.getLikes() + 1)
										 .likesCustomerEmails(likeToSave.getCustomerEmail());
			this.likeRepository.save(retrievedLike);
			return retrievedLike;
		}
		likeToSave.likes(0).likesCustomerEmails(likeToSave.getCustomerEmail());
		this.likeRepository.save(likeToSave);
		return likeToSave; 
	}
	
	
}
