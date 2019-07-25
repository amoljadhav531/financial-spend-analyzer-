package com.hcl.analyzer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.analyzer.dto.MonthlyStatementDto;
import com.hcl.analyzer.entity.CustomerDetails;
import com.hcl.analyzer.entity.TransactionDetail;
import com.hcl.analyzer.exception.InvalidInputException;
import com.hcl.analyzer.exception.ResourceNotFoundException;
import com.hcl.analyzer.repository.CustomerDetailsRepository;
import com.hcl.analyzer.repository.TransactionDetailRepository;

@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

	@Autowired
	private CustomerDetailsRepository customerDetailsRepository;
	
	@Autowired
	private TransactionDetailRepository transactionDetailRepository;

	@Override
	public MonthlyStatementDto getMonthlyStatement(Long customerId, String month) {
		String[] splitMonth =  month.split("-");
		if(splitMonth.length != 2 && !splitMonth[0].matches("0[0-9]|1[0-2]") && !splitMonth[1].matches("\\d{4}")) {
			throw new InvalidInputException("Invalid Month & Year. Please provide input in mentioned format: 02-2019");
		}
		Optional<CustomerDetails> customerDetails = customerDetailsRepository.findById(customerId);
		if (customerDetails.isPresent()) {
			List<TransactionDetail> list = transactionDetailRepository.findByMonthAndYear(customerId, splitMonth[0], splitMonth[1]);
			if(list == null || list.isEmpty()) {
				throw new ResourceNotFoundException("Transactions Not available");
			}
			MonthlyStatementDto monthlyStatementDto = new MonthlyStatementDto();
			double incoming = 0;
			double outgoing = 0;
			for(TransactionDetail transactionDetail : list) {
				if(transactionDetail.getTransactionType().equalsIgnoreCase("Credit")) {
					incoming = incoming + transactionDetail.getTransactionAmount();
				}else {
					outgoing = outgoing + transactionDetail.getTransactionAmount();
				}
				monthlyStatementDto.setCustomerId(customerId);
				monthlyStatementDto.setMonth(month);
				monthlyStatementDto.setTotalIncoming(incoming);
				monthlyStatementDto.setTotalOutgoing(outgoing);
				monthlyStatementDto.setClosingBalance(0.0);
			}
			
			
			return monthlyStatementDto;

		}
		throw new ResourceNotFoundException("Invalid Customer Id");
	}

}
