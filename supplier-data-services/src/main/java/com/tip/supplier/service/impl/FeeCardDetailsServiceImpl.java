package com.tip.supplier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.supplier.model.FeeCardDetailsRequest;
import com.tip.supplier.model.FeeCardDetailsResponse;
import com.tip.supplier.repository.FeeCardDetailsRepository;
import com.tip.supplier.service.FeeCardDetailsService;

@Service
@Transactional
public class FeeCardDetailsServiceImpl implements FeeCardDetailsService {

	@Autowired
	FeeCardDetailsRepository feeCardDetailsRepository;
	
	@Override
	public FeeCardDetailsResponse fetchFeeCardDetails(FeeCardDetailsRequest feeCardDetailsRequest)
	{
		return feeCardDetailsRepository.fetchFeeCardDetails(feeCardDetailsRequest);
	}
}