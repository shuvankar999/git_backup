package com.tip.fetchfridgedetails.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.fetchfridgedetails.model.FridgeDetailsRequest;
import com.tip.fetchfridgedetails.repository.FridgeDetailsRepository;
import com.tip.fetchfridgedetails.service.FridgeDetailsService;;

@Service
@Transactional
public class FridgeDetailsServiceImpl implements FridgeDetailsService {

	@Autowired
	FridgeDetailsRepository fridgeDetailsRepository;

	@Override
	public Map<String, Object> getFridgeDetails(FridgeDetailsRequest fridgeDetailsRequest) {

			return fridgeDetailsRepository.getFridgeDetails(fridgeDetailsRequest);
		}
}
