package com.hcl.analyzer.service;

import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.MailException;
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
	private ValidateServiceImpl validateServiceImpl;

	private TransactionDetail transactionDetail = new TransactionDetail();

	@Before
	public void setUp() {
		transactionDetail.setAvailableBalance(2000.0);
		transactionDetail.setTransactionAmount(200.0);
		transactionDetail.setTransactionDescription("Fun");
		transactionDetail.setTransactionId(1l);
		transactionDetail.setTransactionOtp(1111);
		transactionDetail.setTransactionStatus("Pending");
		transactionDetail.setTransactionType("Debit");
		transactionDetail.setTransactionDate(new Date(1995 - 04 - 04).toLocalDate());
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setEmail("amoljadhav531@gmail.com");
		customerDetails.setMobileNumber("7798228279");
		transactionDetail.setCustomerDetails(customerDetails);
	}

	@Test
	public void testValidateSuccess() {
		Mockito.when(transactionDetailRepository.findById(Mockito.anyLong()))
				.thenReturn(Optional.of(transactionDetail));
		Mockito.when(transactionDetailRepository.save(Mockito.any())).thenReturn(new TransactionDetail());
		ResponseData response = validateServiceImpl.validate(1111, 1);
		assertNotNull(response);
	}
	
	@Test
	public void testValidateForNegetive() {
		Mockito.when(transactionDetailRepository.findById(Mockito.anyLong()))
				.thenReturn(Optional.of(transactionDetail));
		ResponseData response = validateServiceImpl.validate(111, 1);
		assertNotNull(response);
	}
	
	@Test
	public void testValidateForNegetiveEmpty() {
		Mockito.when(transactionDetailRepository.findById(Mockito.anyLong()))
				.thenReturn(Optional.empty());
		ResponseData response = validateServiceImpl.validate(111, 1);
		assertNotNull(response);
	}
	
	/*
	 * @Test public void testSendNotification() throws MailException,
	 * MessagingException { Mockito.doNothing().when(sender).send(new
	 * MimeMessage(sender.createMimeMessage()));
	 * validateServiceImpl.sendNotification(transactionDetail);
	 * //assertNotNull(response); }
	 */
}

