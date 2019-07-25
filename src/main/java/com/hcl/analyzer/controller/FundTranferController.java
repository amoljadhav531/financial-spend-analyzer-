package com.hcl.analyzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.analyzer.dto.FundTransferDTO;
import com.hcl.analyzer.service.FundTransferService;

@RestController
public class FundTranferController {
	
	@Autowired
	private FundTransferService fundTransferService;
	
	 
	@PostMapping("/transfer") 
	public ResponseEntity<String> fundTransfer(FundTransferDTO fundTransferDTO) {
	   return new ResponseEntity<>(fundTransferService.fundTransfer(fundTransferDTO), HttpStatus.OK);
	}

}
