package com.hcl.analyzer.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CustomerDetailsDto {

private String customerName;
	
	private LocalDate dob;
	
	private String mobile_number;
	
	private String email;
	
	private String gender;
	
	private String panNumber;
	
	
	
}
