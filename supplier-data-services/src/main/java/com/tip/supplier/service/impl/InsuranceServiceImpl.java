package com.tip.supplier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.supplier.model.InsuranceRequest;
import com.tip.supplier.model.InsuranceResponse;
import com.tip.supplier.repository.InsuranceRepository;
import com.tip.supplier.service.InsuranceService;

@Service
@Transactional
public class InsuranceServiceImpl implements InsuranceService {

	@Autowired
	InsuranceRepository insuranceRepository;

	@Override
	public InsuranceResponse fetchInsuranceDetails(InsuranceRequest insuranceRequest) {
		return insuranceRepository.fetchInsuranceDetails(insuranceRequest);
	}
}