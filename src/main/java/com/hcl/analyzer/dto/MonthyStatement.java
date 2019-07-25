package com.hcl.analyzer.dto;

import lombok.Data;

@Data
public class MonthyStatement {
	
	private Double amount;
	
	private int month;

	private Double closingBalance;

	public MonthyStatement(Double amount, int month, Double closingBalance) {
		super();
		this.amount = amount;
		this.month = month;
		this.closingBalance = closingBalance;
	}
	
	
	
	

}
