package com.hcl.analyzer.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.config.CustomRepositoryImplementationDetector;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.analyzer.dto.FundTransferDTO;
import com.hcl.analyzer.dto.ResponseData;
import com.hcl.analyzer.entity.CustomerDetails;
import com.hcl.analyzer.entity.TransactionDetail;
import com.hcl.analyzer.exception.ResourceNotFoundException;
import com.hcl.analyzer.repository.CustomerDetailsRepository;
import com.hcl.analyzer.repository.TransactionDetailRepository;

@Service
public class FundTransferServiceImpl implements FundTransferService {

	@Autowired
	TransactionDetailRepository transactionDetailRepository;

	@Autowired
	CustomerDetailsRepository customerDetailsRepository;
	
	public ResponseData fundTransfer(FundTransferDTO fundTransferDTO) {
		
		TransactionDetail transactionDetail = new TransactionDetail();
		
		CustomerDetails customerDetails = customerDetailsRepository.findByCustomerId(fundTransferDTO.getCustomerId());
		
		if(fundTransferDTO.getTransactionType().equalsIgnoreCase("CREDIT"))
			transactionDetail.setAvailableBalance(customerDetails.getAccountBalance()+fundTransferDTO.getTransferAmount());
		else
			if(fundTransferDTO.getTransactionType().equalsIgnoreCase("DEBIT"))
				if(customerDetails.getAccountBalance()<=0)
					throw new ResourceNotFoundException("Your account has zero balance. Please credit some amount.");
				else
					if(customerDetails.getAccountBalance()<fundTransferDTO.getTransferAmount())
						throw new ResourceNotFoundException("Your account doesn't have enough balance. You have "+customerDetails.getAccountBalance()+" balance only.");
					else
						transactionDetail.setAvailableBalance(customerDetails.getAccountBalance()-fundTransferDTO.getTransferAmount());
		transactionDetail.setTransactionAmount(fundTransferDTO.getTransferAmount());
		transactionDetail.setTransactionDate(LocalDate.now());
		transactionDetail.setCustomerId(fundTransferDTO.getCustomerId());
		transactionDetail.setTransactionOtp(9898);
		transactionDetail.setTransactionStatus("Pending");
		transactionDetail.setTransactionType(fundTransferDTO.getTransactionType());
		
		transactionDetailRepository.save(transactionDetail);
		
		ResponseData responseData = new ResponseData();
		responseData.setHttpStatus(HttpStatus.OK);
		responseData.setMessage("");
		
		return responseData;
	}
	

	
}
