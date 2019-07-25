package com.hcl.analyzer.dto;

import lombok.Data;

@Data
public class MonthlyStatementDto {

	private Long customerId;

	private String month;

	private Double totalIncoming;

	private Double totalOutgoing;

	private Double closingBalance;

}
