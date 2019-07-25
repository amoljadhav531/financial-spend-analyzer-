package com.hcl.analyzer.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.analyzer.dto.FundTransferDTO;
import com.hcl.analyzer.dto.ResponseData;
import com.hcl.analyzer.entity.CustomerDetails;
import com.hcl.analyzer.entity.TransactionDetail;
import com.hcl.analyzer.event.TransectionEvent;
import com.hcl.analyzer.exception.ResourceNotFoundException;
import com.hcl.analyzer.repository.CustomerDetailsRepository;
import com.hcl.analyzer.repository.TransactionDetailRepository;

@Service
public class FundTransferServiceImpl implements FundTransferService {

	@Autowired
	TransactionDetailRepository transactionDetailRepository;

	@Autowired
	CustomerDetailsRepository customerDetailsRepository;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	

	public ResponseData fundTransfer(FundTransferDTO fundTransferDTO) {

		TransactionDetail transactionDetail = new TransactionDetail();

		CustomerDetails customerDetails = customerDetailsRepository.findByCustomerId(fundTransferDTO.getCustomerId());

		if (null == customerDetails)
			throw new ResourceNotFoundException("Customer id: " + fundTransferDTO.getCustomerId()
					+ " is not found. Please enter the valid Customer Id");

		if (fundTransferDTO.getTransactionType().equalsIgnoreCase("CREDIT")) {
			transactionDetail
					.setAvailableBalance(customerDetails.getAccountBalance() + fundTransferDTO.getTransferAmount());
			customerDetails
					.setAccountBalance(customerDetails.getAccountBalance() + fundTransferDTO.getTransferAmount());
		} else if (fundTransferDTO.getTransactionType().equalsIgnoreCase("DEBIT"))
			if (customerDetails.getAccountBalance() <= 0)
				throw new ResourceNotFoundException("Your account has zero balance. Please credit some amount.");
			else if (customerDetails.getAccountBalance() < fundTransferDTO.getTransferAmount())
				throw new ResourceNotFoundException("Your account doesn't have enough balance. You have "
						+ customerDetails.getAccountBalance() + " balance only.");
			else {
				transactionDetail
						.setAvailableBalance(customerDetails.getAccountBalance() - fundTransferDTO.getTransferAmount());
				customerDetails
						.setAccountBalance(customerDetails.getAccountBalance() - fundTransferDTO.getTransferAmount());
			}
		else
			throw new ResourceNotFoundException("Please enter valid transaction type (CREDIT/DEBIT).");

		transactionDetail.setTransactionAmount(fundTransferDTO.getTransferAmount());
		transactionDetail.setTransactionDate(LocalDate.now());
		transactionDetail.setCustomerDetails(customerDetails);
		transactionDetail.setTransactionOtp(generateOtp());
		transactionDetail.setTransactionStatus("Pending");
		transactionDetail.setTransactionType(fundTransferDTO.getTransactionType());
		transactionDetail.setTransactionDescription(fundTransferDTO.getTransactionDescription());

		TransactionDetail details = transactionDetailRepository.save(transactionDetail);
		applicationEventPublisher.publishEvent(new TransectionEvent(details));

		ResponseData responseData = new ResponseData();
		responseData.setHttpStatus(HttpStatus.OK);
		responseData.setMessage("OTP is being sent on your mobile number and on email id");

		return responseData;
	}

	private int generateOtp() {
		return (int) (Math.random() * 1000 + 33000L);
	}

}
