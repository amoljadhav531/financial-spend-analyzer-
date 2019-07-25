package com.hcl.analyzer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.analyzer.entity.TransactionDetail;
import com.hcl.analyzer.service.TransactionHistory;

@CrossOrigin
@RestController
@RequestMapping("/finance/transaction")
public class TransactionHistoryController {

	private static final Logger logger = LoggerFactory.getLogger(TransactionHistoryController.class);

	@Autowired
	private TransactionHistory tranactionHistory;

	@GetMapping("/history")
	public ResponseEntity<List<TransactionDetail>> getTransactionHistory(@RequestParam Long customerId) {
		logger.debug("entering into getTranactionHistory=================>>>>>>>>>>>>");
		return new ResponseEntity<List<TransactionDetail>>(tranactionHistory.getTransactionHistory(customerId),
				HttpStatus.OK);

	}

}
