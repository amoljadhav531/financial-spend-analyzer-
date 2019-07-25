package com.hcl.analyzer.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class CustomerDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long customerId;
	
	private String customerName;
	
	private LocalDate dob;
	
	private String mobileNumber;
	
	private String email;
	
	private String gender;
	
	private String panNumber;
	
	private double accountBalance;
	
	@OneToMany(mappedBy = "customerDetails")
	private List<TransactionDetail> transactionDetails = new ArrayList<>();
}
