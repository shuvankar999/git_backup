package com.tip.supplier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.supplier.model.PartsAgreementRequest;
import com.tip.supplier.model.PartsAgreementResponse;
import com.tip.supplier.repository.PartsAgreementRepository;
import com.tip.supplier.service.PartsAgreementService;

@Service
@Transactional
public class PartsAgreementServiceImpl implements PartsAgreementService {

	@Autowired
	PartsAgreementRepository partsAgreementRepository;
	
	@Override
	public PartsAgreementResponse fetchPartsAgreement(PartsAgreementRequest partsAgreementRequest)
	{
		return partsAgreementRepository.fetchPartsAgreement(partsAgreementRequest);
	}
}