package com.hcl.analyzer.service;

import static org.junit.Assert.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.hcl.analyzer.dto.FundTransferDTO;
import com.hcl.analyzer.entity.CustomerDetails;
import com.hcl.analyzer.entity.TransactionDetail;
import com.hcl.analyzer.exception.ResourceNotFoundException;
import com.hcl.analyzer.repository.CustomerDetailsRepository;
import com.hcl.analyzer.repository.TransactionDetailRepository;

@RunWith(MockitoJUnitRunner.class)
public class FundTransferServiceImplTest {

	FundTransferDTO fundTransferDTO;
	CustomerDetails customerDetails;
	TransactionDetail transactionDetail;
	List<TransactionDetail> list;
	
	@Mock
	CustomerDetailsRepository customerDetailsRepository;
	
	@Mock
	TransactionDetailRepository transactionDetailRepository;
	
	@InjectMocks
	FundTransferServiceImpl fundTransferServiceImpl;
	
	@Before
	public void setUp() throws Exception {
		fundTransferDTO = new FundTransferDTO();
		fundTransferDTO.setCustomerId(1);
		fundTransferDTO.setTransactionDescription("Jimmy Choo Shoes");
		fundTransferDTO.setTransactionType("DEBIT");
		fundTransferDTO.setTransferAmount(10000.0);
		
		transactionDetail = new TransactionDetail();
		transactionDetail.setAvailableBalance(100000.00);
		transactionDetail.setCustomerDetails(customerDetails);
		transactionDetail.setTransactionAmount(100.00);
		transactionDetail.setTransactionDate(LocalDate.now());
		transactionDetail.setTransactionDescription("Jimmy choo shoes");
		transactionDetail.setTransactionId(1L);
		transactionDetail.setTransactionOtp(1232);
		transactionDetail.setTransactionStatus("Pending");
		transactionDetail.setTransactionType("DEBIT");
		
		list = new ArrayList<TransactionDetail>();
		list.add(transactionDetail);
		
		customerDetails = new CustomerDetails();
		customerDetails.setAccountBalance(10000.00);
		customerDetails.setCustomerId(1);
		customerDetails.setCustomerName("Harsimar Kaur");
		customerDetails.setDob(new Date(1995-02-12).toLocalDate());
		customerDetails.setEmail("simarkaur1295@gmail.com");
		customerDetails.setGender("female");
		customerDetails.setMobileNumber("9422390512");
		customerDetails.setPanNumber("EHMPK0232F");
		customerDetails.setTransactionDetails(list);
		
	}

	@Test(expected = ResourceNotFoundException.class)
	public void testCustomerIDIncorrect() {
		fundTransferDTO.setCustomerId(9);
		fundTransferServiceImpl.fundTransfer(fundTransferDTO);
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void testZeroBalance() {
		customerDetails.setAccountBalance(0);
		fundTransferServiceImpl.fundTransfer(fundTransferDTO);
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void test() {
		customerDetails.setAccountBalance(10000000.0);;
		fundTransferServiceImpl.fundTransfer(fundTransferDTO);
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void testTransactionType() {
		fundTransferDTO.setTransactionType("Credittt");
		fundTransferServiceImpl.fundTransfer(fundTransferDTO);
	}

}
