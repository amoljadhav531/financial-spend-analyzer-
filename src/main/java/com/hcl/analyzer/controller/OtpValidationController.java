package com.hcl.analyzer.controller;

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

	@Autowired
	private ValidateService validateServiceImpl;

	@PostMapping("transaction/validate")
	public ResponseEntity<Object> validate(OtpValidationDto otpValidationDto) {
		ResponseData response = validateServiceImpl.validate(otpValidationDto.getOtp(),
				otpValidationDto.getTransactionId());
		return new ResponseEntity<>(response, response.getHttpStatus());
	}
}
