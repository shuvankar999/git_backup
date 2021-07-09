package com.tip.supplier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.supplier.model.LabourRatesRequest;
import com.tip.supplier.model.LabourRatesResponse;
import com.tip.supplier.repository.LabourRatesRepository;
import com.tip.supplier.service.LabourRatesService;

@Service
@Transactional
public class LabourRatesServiceImpl implements LabourRatesService {

	@Autowired
	LabourRatesRepository labourRatesRepository;

	@Override
	public LabourRatesResponse fetchLabourRates(LabourRatesRequest labourRatesRequest) {
		return labourRatesRepository.fetchLabourRates(labourRatesRequest);
	}
}