package com.hcl.analyzer.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class TransactionDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;
	
	private String customerId;

	private LocalDate transactionDate;
	
	private String transactionType;
	
	private Double transactionAmount;
	
	private String transactionStatus;
	
	private String transactionOtp;
	
	private Double availableBalance;
}
