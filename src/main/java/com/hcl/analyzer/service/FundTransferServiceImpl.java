package com.hcl.analyzer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.analyzer.dto.FundTransferDTO;
import com.hcl.analyzer.repository.TransactionDetailRepository;

@Service
public class FundTransferServiceImpl implements FundTransferService {

	@Autowired
	TransactionDetailRepository transactionDetailRepository;

	public String fundTransfer(FundTransferDTO fundTransferDTO) {
		
		return null;
	}
	

	
}
