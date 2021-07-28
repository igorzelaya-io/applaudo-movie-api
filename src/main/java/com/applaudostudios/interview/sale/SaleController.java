package com.applaudostudios.interview.sale;

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
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService saleService;
	
	@PostMapping
	public ResponseEntity<? extends Response<Sale>> saveSale(@Valid @RequestBody SaleRequest sale){
		BaseResponse<Sale> saleResponse = new BaseResponse<>();
		Sale postedSale = saleService.saveSale(sale);
		return saleResponse.createResponse(HttpStatus.CREATED, "Sale posted successfully", postedSale);
	}
	
}
