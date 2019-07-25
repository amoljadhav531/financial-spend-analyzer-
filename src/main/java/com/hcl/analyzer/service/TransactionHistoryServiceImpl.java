package com.hcl.analyzer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.analyzer.dto.MonthlyStatementDto;
import com.hcl.analyzer.entity.CustomerDetails;
import com.hcl.analyzer.entity.MonthyStatement;
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
	public List<MonthlyStatementDto> getMonthlyStatement(Long customerId) {
		Optional<CustomerDetails> customerDetails = customerDetailsRepository.findById(customerId);
		if (customerDetails.isPresent()) {
			List<MonthyStatement> creditList = transactionDetailRepository.findByMonthAndYear(customerDetails.get(),
					"Credit");
			List<MonthyStatement> debitList = transactionDetailRepository.findByMonthAndYear(customerDetails.get(),
					"Debit");

			List<MonthlyStatementDto> monthlyStatementDtos = new ArrayList<MonthlyStatementDto>();

			double closingBalance = customerDetails.get().getAccountBalance();

			MonthlyStatementDto monthlyStatementDto;
			for (MonthyStatement monthyStatement : creditList) {
				monthlyStatementDto = new MonthlyStatementDto();

				monthlyStatementDto.setMonth(String.valueOf(monthyStatement.getMonth()));
				monthlyStatementDto.setCustomerId(customerId);
				monthlyStatementDto.setTotalIncoming(monthyStatement.getAmount());
				monthlyStatementDto.setClosingBalance(closingBalance + monthyStatement.getClosingBalance());
				monthlyStatementDtos.add(monthlyStatementDto);
			}

			List<MonthlyStatementDto> tempList = new ArrayList<MonthlyStatementDto>();
			tempList.addAll(monthlyStatementDtos);
			//Collections.copy(tempList, monthlyStatementDtos);
			
			for (MonthyStatement statement : debitList) {
				for (int i = 0; i < tempList.size(); i++) {
					if (String.valueOf(statement.getMonth()).equals(tempList.get(i).getMonth())) {
						monthlyStatementDtos.get(i).setTotalOutgoing(statement.getAmount());
						monthlyStatementDtos.get(i).setClosingBalance(tempList.get(i).getClosingBalance() - statement.getAmount());
						break;
					}else {
						MonthlyStatementDto statementDto = new MonthlyStatementDto();
						statementDto.setCustomerId(customerId);
						statementDto.setMonth(String.valueOf(statement.getMonth()));
						statementDto.setTotalOutgoing(statement.getAmount());
						monthlyStatementDtos.get(i).setClosingBalance(closingBalance - statement.getAmount());
						monthlyStatementDtos.add(statementDto);
					}
				}
			}

			return monthlyStatementDtos;

		}
		throw new ResourceNotFoundException("Invalid Customer Id");
	}

}
