package com.hcl.analyzer.service;

import java.util.Optional;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.hcl.analyzer.dto.ResponseData;
import com.hcl.analyzer.entity.TransactionDetail;
import com.hcl.analyzer.repository.TransactionDetailRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ValidateServiceImpl implements ValidateService {

	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	private TransactionDetailRepository transactionDetailRepository;

	private static final String ACCOUNT_SID = "ACf73ecdd409fb";
	private static final String AUTH_ID = "d099b8077232a1";

	static {
		Twilio.init(ACCOUNT_SID, AUTH_ID);
	}

	@Override
	public ResponseData validate(int otp, long transactionId) {
		ResponseData response = new ResponseData();
		Optional<TransactionDetail> transactionDetails = transactionDetailRepository.findById(transactionId);
		if(transactionDetails.isPresent()) {
			TransactionDetail transactionDetail = transactionDetails.get();
			if(transactionDetail.getTransactionOtp()==otp) {
				transactionDetail.setTransactionStatus("Successful");
				transactionDetail.setTransactionOtp(0);
				transactionDetailRepository.save(transactionDetail);
				response.setMessage("Transaction Successful");
				response.setHttpStatus(HttpStatus.OK);
				return response;
			}else {
				response.setMessage("Invalid OTP");
				response.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
				return response;
			}
		}
		response.setMessage("Incorrect Transaction ID");
		response.setHttpStatus(HttpStatus.BAD_REQUEST);
		return response;
	}

	@Override
	public void sendNotification(TransactionDetail transactionDetail) {
		try {

			String msg = "Your OTP for transaction Id " + transactionDetail.getTransactionId() + " id : "
					+ transactionDetail.getTransactionOtp();

			MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);

			helper.setTo(transactionDetail.getCustomerDetails().getEmail());
			helper.setText(msg);
			helper.setSubject("Bank OTP Notification");
			sender.send(message);

			Message.creator(new PhoneNumber("+91" + transactionDetail.getCustomerDetails().getMobileNumber()), new PhoneNumber("FROM Number"), msg).create();
		} catch (Exception e) {
			log.error("Error: "+ e);
		}
	}

}
