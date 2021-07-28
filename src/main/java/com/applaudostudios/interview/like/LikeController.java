package com.applaudostudios.interview.like;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.applaudostudios.interview.response.BaseResponse;
import com.applaudostudios.interview.response.Response;

@RestController
@RequestMapping(value = "/likes")
public class LikeController {
	
	@Autowired
	private LikeService likeService;
	
	@PostMapping
	public ResponseEntity<? extends Response<Like>> postLike(@Valid @RequestBody(required = true)LikeRequest like){
		Like savedLike = this.likeService.saveLike(like);
		BaseResponse<Like> likeResponse = new BaseResponse<>();
		return likeResponse.createResponse(HttpStatus.CREATED, "Movie was liked successfully.", savedLike);
	}

}
