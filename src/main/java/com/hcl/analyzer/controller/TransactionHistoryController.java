package com.hcl.analyzer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.analyzer.entity.TransactionDetail;
import com.hcl.analyzer.service.TransactionHistory;

@RestController
@RequestMapping("/finance/transaction")
public class TransactionHistoryController {

	@Autowired
	private TransactionHistory tranactionHistory;
	
	
	@GetMapping("/history")
	public ResponseEntity<List<TransactionDetail>> getTransactionHistory(@RequestParam Long customerId) {
		
		
		return new ResponseEntity<List<TransactionDetail>>(tranactionHistory.getTransactionHistory(customerId), HttpStatus.OK);

	}
		
	
}
