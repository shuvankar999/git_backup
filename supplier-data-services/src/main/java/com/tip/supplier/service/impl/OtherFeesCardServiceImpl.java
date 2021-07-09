package com.tip.supplier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.supplier.model.OtherFeesCardRequest;
import com.tip.supplier.model.OtherFeesCardResponse;
import com.tip.supplier.repository.OtherFeesCardRepository;
import com.tip.supplier.service.OtherFeesCardService;

@Service
@Transactional
public class OtherFeesCardServiceImpl implements OtherFeesCardService {

	@Autowired
	OtherFeesCardRepository otherFeesCardRepository;
	
	@Override
	public OtherFeesCardResponse fetchOtherFeesCard(OtherFeesCardRequest otherFeesCardRequest)
	{
		return otherFeesCardRepository.fetchOtherFeesCard(otherFeesCardRequest);
	}
}