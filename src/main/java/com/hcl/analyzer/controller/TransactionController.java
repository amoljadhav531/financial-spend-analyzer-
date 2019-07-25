package com.hcl.analyzer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/finance")
public class TransactionController {
	
	@GetMapping("/transaction/monthlyhistory")
	public ResponseEntity<?> getMonthlyStatement(@PathVariable Long customerId, @PathVariable String month){
		
		return null;
	}
}
