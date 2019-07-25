package com.hcl.analyzer.service;

import java.util.List;

import com.hcl.analyzer.dto.MonthlyStatementDto;

public interface TransactionHistoryService {
	
	public List<MonthlyStatementDto> getMonthlyStatement(Long customerId);

}
