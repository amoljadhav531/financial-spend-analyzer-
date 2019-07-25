package com.hcl.analyzer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.analyzer.dto.CustomerDetailsDto;
import com.hcl.analyzer.entity.CustomerDetails;
import com.hcl.analyzer.repository.CustomerDetailsRepository;

@Service
public class CustomerDetailsService {
	
	@Autowired
	CustomerDetailsRepository customerDetailsRepository;
	
	public CustomerDetails addCustomer(CustomerDetailsDto customerDetailsDto)
	{
		
		CustomerDetails customerDetails=new CustomerDetails();
		
		customerDetails.setAccountBalance(0.00);
		customerDetails.setCustomerName(customerDetailsDto.getCustomerName());
		customerDetails.setDob(customerDetailsDto.getDob());
		customerDetails.setEmail(customerDetailsDto.getEmail());
		customerDetails.setGender(customerDetailsDto.getGender());
		customerDetails.setMobileNumber(customerDetailsDto.getMobileNumber());
		customerDetails.setPanNumber(customerDetailsDto.getPanNumber());
		
		return customerDetailsRepository.save(customerDetails);
		
	}

}
