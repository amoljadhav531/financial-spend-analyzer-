package com.hcl.analyzer.dto;

import java.time.LocalDate;

public class FundTransferDTO {

	private Long transactionId;
	private String customerId;
	private LocalDate transactionDate;
	private String transactionType;
	private Double transactionAmount;
	private String transactionStatus;
	private String transactionOtp;
	private Double availableBalance;
	
}
