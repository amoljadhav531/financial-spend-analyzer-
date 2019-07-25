package com.hcl.analyzer.service;

import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import com.hcl.analyzer.dto.ResponseData;
import com.hcl.analyzer.entity.CustomerDetails;
import com.hcl.analyzer.entity.TransactionDetail;
import com.hcl.analyzer.repository.TransactionDetailRepository;

@RunWith(MockitoJUnitRunner.class)
public class ValidateServiceImplTest {
	
	@Mock
	private JavaMailSender sender;
	
	@Mock
	private TransactionDetailRepository transactionDetailRepository;
	
	@InjectMocks
	private ValidateService validateServiceImpl;
	
	private TransactionDetail transactionDetail = null;
	
	
	@Before
	public void setUp() {
		transactionDetail.setAvailableBalance(2000.0);
		transactionDetail.setTransactionAmount(200.0);
		transactionDetail.setTransactionDescription("Fun");
		transactionDetail.setTransactionId(1l);
		transactionDetail.setTransactionOtp(1111);
		transactionDetail.setTransactionStatus("Pending");
		transactionDetail.setTransactionType("Debit");
		transactionDetail.setTransactionDate(new Date(1995-04-04).toLocalDate());
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setEmail("amoljadhav531@gmail.com");
		customerDetails.setMobileNumber("7798228279");
		transactionDetail.setCustomerDetails(customerDetails);
	}

	@Test
	public void testValidateSuccess() {
		Mockito.when(transactionDetailRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(transactionDetail));
		Mockito.when(transactionDetailRepository.save(Mockito.any())).thenReturn(new TransactionDetail());
		ResponseData response = validateServiceImpl.validate(1111, 1);
		assertNotNull(response);
	}
}
