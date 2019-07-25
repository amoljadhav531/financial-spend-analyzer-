package com.hcl.analyzer.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import javax.persistence.OneToMany;


import lombok.Data;

@Entity
@Data
public class CustomerDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long customerId;
	
	@Size(max = 20, min = 3, message = "{user.customerName.invalid}")
    @NotEmpty(message = "Please enter name")
	private String customerName;
	
	private LocalDate dob;
	
	@Size(max = 20, min = 3, message = "{user.mobile_number.invalid}")
    @NotEmpty(message = "Please enter Mobile Number")
	private String mobile_number;
	
	 @Email(message = "{user.email.invalid}")
	  @NotEmpty(message = "Please enter email")
	private String email;
	
	 @Size(max = 20, min = 3, message = "{user.gender.invalid}")
	    @NotEmpty(message = "Please enter Gender")
	private String gender;
	
	 @Size(max = 20, min = 3, message = "{user.panNumber.invalid}")
	    @NotEmpty(message = "Please enter panNumber")
	private String panNumber;
	
	private double accountBalance;
	
	@OneToMany(mappedBy = "customerDetails")
	private List<TransactionDetail> transactionDetails = new ArrayList<>();
}
