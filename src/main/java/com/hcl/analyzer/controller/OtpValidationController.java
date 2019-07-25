package com.hcl.analyzer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.analyzer.dto.OtpValidationDto;
import com.hcl.analyzer.dto.ResponseData;
import com.hcl.analyzer.service.ValidateService;

@RestController
@RequestMapping("/finance")
@CrossOrigin
public class OtpValidationController {
	
	private static final Logger logger = LoggerFactory.getLogger(OtpValidationController.class);
	@Autowired
	private ValidateService validateServiceImpl;

	@PostMapping("transaction/validate")
	public ResponseEntity<Object> validate(OtpValidationDto otpValidationDto) {
		logger.debug("entering into validate method============>>>>>>>>>>>");
		ResponseData response = validateServiceImpl.validate(otpValidationDto.getOtp(),
				otpValidationDto.getTransactionId());
		logger.debug("existing  from validate============>>>>>>>>>>>");
		return new ResponseEntity<>(response, response.getHttpStatus());
	}
}
