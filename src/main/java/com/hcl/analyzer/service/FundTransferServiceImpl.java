package com.hcl.analyzer.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.analyzer.dto.FundTransferDTO;
import com.hcl.analyzer.dto.ResponseData;
import com.hcl.analyzer.entity.TransactionDetail;
import com.hcl.analyzer.repository.TransactionDetailRepository;

@Service
public class FundTransferServiceImpl implements FundTransferService {

	@Autowired
	TransactionDetailRepository transactionDetailRepository;

	public ResponseData fundTransfer(FundTransferDTO fundTransferDTO) {
		
		TransactionDetail transactionDetail = new TransactionDetail();
		//transactionDetail.setAvailableBalance(fundTransferDTO.get);
		transactionDetail.setTransactionAmount(fundTransferDTO.getTransferAmount());
		transactionDetail.setTransactionDate(LocalDate.now());
		transactionDetail.setCustomerId(fundTransferDTO.getCustomerId());
		transactionDetail.setTransactionOtp("9898");
		transactionDetail.setTransactionStatus("Pending");
		transactionDetail.setTransactionType(fundTransferDTO.getTransactionType());
		
		ResponseData responseData = new ResponseData();
		//responseData.setHttpStatus(HttpStatus.OK);
		//responseData.setMessage("");
		
		//transactionDetailRepository.findById(fundTransferDTO.)
		
		return responseData;
	}
	

	
}
