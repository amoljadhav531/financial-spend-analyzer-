package com.hcl.analyzer.service;

import java.util.List;

import com.hcl.analyzer.entity.TransactionDetail;

public interface TransactionHistoryService {
	
	public List<TransactionDetail> getMonthlyStatement(Long customerId, String month);

}
