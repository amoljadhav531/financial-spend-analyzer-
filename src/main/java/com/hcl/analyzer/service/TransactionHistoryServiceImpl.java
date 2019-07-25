package com.hcl.analyzer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.analyzer.entity.CustomerDetails;
import com.hcl.analyzer.entity.TransactionDetail;
import com.hcl.analyzer.exception.ResourceNotFoundException;
import com.hcl.analyzer.repository.CustomerDetailsRepository;

@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

	@Autowired
	private CustomerDetailsRepository customerDetailsRepository;

	@Override
	public List<TransactionDetail> getMonthlyStatement(Long customerId, String month) {
		Optional<CustomerDetails> customerDetails = customerDetailsRepository.findById(customerId);
		if (customerDetails.isPresent()) {

		}
		throw new ResourceNotFoundException("Invalid Customer Id");
	}

}
