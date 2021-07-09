package com.tip.fetchaxle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.fetchaxle.model.FetchAxleRequest;
import com.tip.fetchaxle.model.FetchAxleResponse;
import com.tip.fetchaxle.repository.FetchAxleRepository;
import com.tip.fetchaxle.service.FetchAxleService;;



@Service
@Transactional
public class FetchAxleServiceImpl implements FetchAxleService{

	@Autowired
	FetchAxleRepository fetchAxleRepository;
	
	@Override
	public FetchAxleResponse  getNoOfAxlesForAsset(FetchAxleRequest fetchAxleRequest)
	{
		
		  return fetchAxleRepository.fetchNoOfAxleForAsset(fetchAxleRequest);
	}
	
}
