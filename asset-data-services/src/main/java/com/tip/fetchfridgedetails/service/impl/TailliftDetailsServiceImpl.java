package com.tip.fetchfridgedetails.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.fetchfridgedetails.model.FridgeDetailsRequest;
import com.tip.fetchfridgedetails.repository.TailliftDetailsRepository;
import com.tip.fetchfridgedetails.service.TailliftDetailsService;;

@Service
@Transactional
public class TailliftDetailsServiceImpl implements TailliftDetailsService {

	@Autowired
	TailliftDetailsRepository tailliftDetailsRepository;

	@Override
	public Map<String, Object> getTailliftDetails(FridgeDetailsRequest fridgeDetailsRequest) {

			return tailliftDetailsRepository.getTailliftDetails(fridgeDetailsRequest);
		}
}
