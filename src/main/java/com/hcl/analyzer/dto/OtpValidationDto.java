package com.hcl.analyzer.dto;

import lombok.Data;

@Data
public class OtpValidationDto {
	
	private int otp;
	private long transactionId;
}
