package com.hcl.analyzer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.analyzer.dto.ResponseData;
import com.hcl.analyzer.entity.TransactionDetail;
import com.hcl.analyzer.exception.ResourceNotFoundException;
import com.hcl.analyzer.repository.CustomerDetailsRepository;
import com.hcl.analyzer.repository.TransactionDetailRepository;

@Service
public class TransactionHistoryImpl implements TransactionHistory {

	@Autowired
	private CustomerDetailsRepository customerDetailRepository;

	@Autowired
	TransactionDetailRepository transactionDetailRepository;

	@Override
	public List<TransactionDetail> getTransactionHistory(Long customerId) {

		List<TransactionDetail> transactionDetails = transactionDetailRepository.findByCustomerDetails(customerId);
		if (transactionDetails.isEmpty()) {
			throw new ResourceNotFoundException("Please enter correct customer_id");
		} else {
			return transactionDetails;
		}

	}

}
