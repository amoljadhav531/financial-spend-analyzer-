package com.hcl.analyzer.service;

import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.analyzer.entity.CustomerDetails;
import com.hcl.analyzer.entity.TransactionDetail;
import com.hcl.analyzer.repository.TransactionDetailRepository;

@RunWith(MockitoJUnitRunner.class)
public class TransactionHistoryImplTest {

	@Mock
	TransactionDetailRepository transactionDetailRepository;

	@InjectMocks
	TransactionHistoryImpl transactionHistoryImpl;

	static List<TransactionDetail> transactionDetails2 = new ArrayList<TransactionDetail>();
	static TransactionDetail transactionDetails;

	static CustomerDetails customerDetails;

	@BeforeClass
	public static void setup() {

		customerDetails = new CustomerDetails();
		customerDetails.setAccountBalance(233444.0);
		customerDetails.setCustomerId(1L);
		customerDetails.setCustomerName("karan");
		customerDetails.setDob(new Date(2018 - 19 - 02).toLocalDate());
		customerDetails.setEmail("karan");
		customerDetails.setGender("m");
		customerDetails.setMobileNumber("4567890345");
		customerDetails.setPanNumber("bnhp");
//		customerDetails.setTransactionDetails(transactionDetails);

		transactionDetails = new TransactionDetail();
		transactionDetails.setAvailableBalance(2500.0);
		transactionDetails.setTransactionId(1L);
		transactionDetails.setTransactionAmount(2340.0);
		transactionDetails.setCustomerDetails(customerDetails);

		transactionDetails2.add(transactionDetails);
	}

	@Test
	public void getTransactionHistory() {

		Mockito.when(transactionDetailRepository.findByCustomerDetails(1L)).thenReturn(transactionDetails2);
		List<TransactionDetail> transactionDetails = transactionHistoryImpl.getTransactionHistory(1L);
		assertNotNull(transactionDetails);		

	}

}
