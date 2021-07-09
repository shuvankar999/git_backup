package com.tip.supplier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.supplier.model.OperationalDetailsRequest;
import com.tip.supplier.model.OperationalDetailsResponse;
import com.tip.supplier.repository.OperationalDetailsRepository;
import com.tip.supplier.service.OperationalDetailsService;

@Service
@Transactional
public class OperationalDetailsServiceImpl implements OperationalDetailsService {

	@Autowired
	OperationalDetailsRepository operationalDetailsRepository;
	
	@Override
	public OperationalDetailsResponse fetchOperationalDetails(OperationalDetailsRequest operationalDetailsRequest)
	{
		return operationalDetailsRepository.fetchOperationalDetails(operationalDetailsRequest);
	}
}