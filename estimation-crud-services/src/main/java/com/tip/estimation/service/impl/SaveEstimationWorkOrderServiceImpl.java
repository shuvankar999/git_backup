package com.tip.estimation.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.estimation.model.SaveEstnWorkOrderRequest;
import com.tip.estimation.repository.SaveEstimationWorkOrderRepository;
import com.tip.estimation.service.SaveEstimationWorkOrderService;

@Service
@Transactional
public class SaveEstimationWorkOrderServiceImpl implements SaveEstimationWorkOrderService{
	
	@Autowired
	SaveEstimationWorkOrderRepository saveEstimationWorkOrderRepository;

	@Override
	public Map<String, Object> saveEstnWorkOrder(SaveEstnWorkOrderRequest saveEstnWorkOrderRequest) {
		return saveEstimationWorkOrderRepository.saveEstnWorkOrder(saveEstnWorkOrderRequest);
	}

}
