package com.hcl.analyzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.analyzer.service.TransactionHistoryService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	
	@Autowired
	private TransactionHistoryService transactionHistoryService;
	
	@GetMapping("/monthlyhistory")
	public ResponseEntity<?> getMonthlyStatement(@PathVariable Long customerId, @PathVariable String month){
		
		return null;
	}
}
