package com.hcl.analyzer.service;

import com.hcl.analyzer.dto.ResponseData;
import com.hcl.analyzer.entity.TransactionDetail;

public interface ValidateService {
	
	public ResponseData validate(int otp, long transactionId);
	
	public void sendNotification(TransactionDetail transactionDetail);

}
