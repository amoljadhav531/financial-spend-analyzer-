package com.hcl.analyzer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.analyzer.dto.FundTransferDTO;
import com.hcl.analyzer.dto.ResponseData;
import com.hcl.analyzer.repository.TransactionDetailRepository;

@Service
public class FundTransferServiceImpl implements FundTransferService {

	@Autowired
	TransactionDetailRepository transactionDetailRepository;

	public ResponseData fundTransfer(FundTransferDTO fundTransferDTO) {
		
		ResponseData responseData = new ResponseData();
		//responseData.setHttpStatus(HttpStatus.OK);
		//responseData.setMessage("");
		return responseData;
	}
	

	
}
