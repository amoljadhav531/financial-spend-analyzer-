package com.hcl.analyzer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.analyzer.dto.FundTransferDTO;
import com.hcl.analyzer.service.FundTransferService;

import lombok.extern.slf4j.Slf4j;


@CrossOrigin
@Slf4j
@RestController
public class FundTranferController {
	
	private static final Logger logger = LoggerFactory.getLogger(FundTranferController.class);
	@Autowired
	private FundTransferService fundTransferService;
	 
	@PostMapping("/transfer") 
	public ResponseEntity<Object> fundTransfer(@RequestBody FundTransferDTO fundTransferDTO) {
		logger.debug("entering into fundTransfer==========>>>>>>>>>>>>>>");
		log.info("Creating New transactions for customer id" +fundTransferDTO.getCustomerId());
		return new ResponseEntity<>(fundTransferService.fundTransfer(fundTransferDTO), HttpStatus.OK);
	}

}
