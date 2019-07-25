package com.hcl.analyzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FundTranferController {
	
	@Autowired
	FundTransferService fundTransferService;
	
	

}
