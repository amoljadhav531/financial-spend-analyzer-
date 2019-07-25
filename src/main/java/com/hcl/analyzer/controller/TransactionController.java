package com.hcl.analyzer.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.analyzer.dto.MonthlyStatementDto;
import com.hcl.analyzer.service.TransactionHistoryService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	
	@Autowired
	private TransactionHistoryService transactionHistoryService;
	
	@GetMapping("/monthlyhistory/{customerId}/{month}")
	public ResponseEntity<MonthlyStatementDto> getMonthlyStatement(@PathVariable("customerId") @NotNull Long customerId, @PathVariable("month") @NotNull String monthAndYear){
		return new ResponseEntity<>(transactionHistoryService.getMonthlyStatement(customerId, monthAndYear), HttpStatus.ACCEPTED);
	}
}
