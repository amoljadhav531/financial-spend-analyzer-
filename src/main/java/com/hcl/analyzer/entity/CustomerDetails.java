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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "customerId")
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
