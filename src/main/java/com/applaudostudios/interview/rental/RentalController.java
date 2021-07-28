package com.applaudostudios.interview.rental;

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
@RequestMapping(value = "/rentals")
public class RentalController {

	@Autowired
	private RentalService rentalService;
	
	@PostMapping
	public ResponseEntity<? extends Response<Rental>> postRental(@Valid @RequestBody RentalRequest rental){
		BaseResponse<Rental> rentalResponse = new BaseResponse<>();
		Rental postedRental = rentalService.saveRental(rental);
		return rentalResponse.createResponse(HttpStatus.OK, "Movie rented successfully", postedRental);
	}
	
}
