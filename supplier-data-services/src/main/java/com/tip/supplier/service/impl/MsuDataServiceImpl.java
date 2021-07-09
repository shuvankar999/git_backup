package com.tip.supplier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.supplier.model.MsuDataRequest;
import com.tip.supplier.model.MsuDataResponse;
import com.tip.supplier.repository.MsuDataRepository;
import com.tip.supplier.service.MsuDataService;

@Service
@Transactional
public class MsuDataServiceImpl implements MsuDataService {

	@Autowired
	MsuDataRepository msuDataRepository;

	@Override
	public MsuDataResponse fetchMsuData(MsuDataRequest msuDataRequest) {
		return msuDataRepository.fetchMsuData(msuDataRequest);
	}
}