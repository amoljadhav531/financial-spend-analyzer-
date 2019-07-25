package com.hcl.analyzer.service;

import java.util.List;

import com.hcl.analyzer.entity.TransactionDetail;

public interface TransactionHistory {

	List<TransactionDetail> getTransactionHistory(Long customerId);

}
