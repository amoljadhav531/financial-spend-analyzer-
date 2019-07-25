package com.hcl.analyzer.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.analyzer.dto.CustomerDetailsDto;
import com.hcl.analyzer.entity.CustomerDetails;
import com.hcl.analyzer.service.CustomerDetailsService;


import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@Slf4j
@RestController
public class CustomerDetailsController {
	
	@Autowired
	CustomerDetailsService customerDetailsService;
	

	@PostMapping("customer/registration")
	public ResponseEntity<CustomerDetails> addCustomer(@Valid @RequestBody CustomerDetailsDto customerDetailsDto) {
		log.info("Creating new user profile " +customerDetailsDto.getCustomerName());
		return new ResponseEntity<>(customerDetailsService.addCustomer(customerDetailsDto), HttpStatus.OK);

	}

}
