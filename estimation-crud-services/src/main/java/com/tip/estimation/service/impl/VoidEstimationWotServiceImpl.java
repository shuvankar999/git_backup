package com.tip.estimation.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.estimation.model.VoidEstnWotRequest;
import com.tip.estimation.model.VoidEstnWotResponse;
import com.tip.estimation.repository.VoidEstimationWotRepository;
import com.tip.estimation.service.VoidEstimationWotService;

@Service
public class VoidEstimationWotServiceImpl implements VoidEstimationWotService{
	
	@Autowired
	VoidEstimationWotRepository voidEstimationWotRepository;

	@Override
	public VoidEstnWotResponse voidEstnWot(VoidEstnWotRequest voidEstnWotRequest) {
		VoidEstnWotResponse voidEstnWotResponse = new VoidEstnWotResponse();
		Map<String, Object> resultMap = voidEstimationWotRepository.voidEstnWot(voidEstnWotRequest);
		voidEstnWotResponse.setErrorCd((String) resultMap.get("errorCd"));
		return voidEstnWotResponse;
		
	}
		
	}


