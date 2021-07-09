package com.tip.supplier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.supplier.model.AllCapabilitiesRequest;
import com.tip.supplier.model.AllCapabilitiesResponse;
import com.tip.supplier.repository.AllCapabilitiesRepository;
import com.tip.supplier.service.AllCapabilitiesService;


@Service
@Transactional
public class AllCapabilitiesServiceImpl implements AllCapabilitiesService {

	@Autowired
	AllCapabilitiesRepository allCapabilitiesRepository;
	
	@Override
	public AllCapabilitiesResponse fetchAllCapabilities(AllCapabilitiesRequest allCapabilitiesRequest)
	{
		return allCapabilitiesRepository.fetchAllCapabilities(allCapabilitiesRequest);
	}
}