package com.hcl.analyzer.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.hcl.analyzer.entity.TransactionDetail;
import com.hcl.analyzer.service.ValidateService;

@Component
public class TransectionEventListner implements ApplicationListener<TransectionEvent>{

	@Autowired
	private ValidateService validateServiceImpl;
	
	@Override
	public void onApplicationEvent(TransectionEvent event) {

		TransactionDetail transactionDetail = (TransactionDetail) event.getMessage();
		validateServiceImpl.sendNotification(transactionDetail);
	
	}

}
