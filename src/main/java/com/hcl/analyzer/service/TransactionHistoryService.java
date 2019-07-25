package com.hcl.analyzer.service;

import com.hcl.analyzer.dto.MonthlyStatementDto;

public interface TransactionHistoryService {
	
	public MonthlyStatementDto getMonthlyStatement(Long customerId, String month);

}
