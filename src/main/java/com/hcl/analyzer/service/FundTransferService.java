package com.hcl.analyzer.service;

import com.hcl.analyzer.dto.FundTransferDTO;
import com.hcl.analyzer.dto.ResponseData;

public interface FundTransferService {

	public ResponseData fundTransfer(FundTransferDTO fundTransferDTO);	
	
}
