package com.tip.estimation.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.estimation.model.DeletePartsRequest;
import com.tip.estimation.repository.DeletePartsEstnRepository;
import com.tip.estimation.service.DeletePartsEstnService;

@Service
@Transactional
public class DeletePartsEstnServiceImpl implements DeletePartsEstnService{
	
	@Autowired
	DeletePartsEstnRepository deletePartsEstnRepository;
	
	@SuppressWarnings({"unchecked" })
	@Override
	public Object deleteParts(DeletePartsRequest deletePartsRequest) {
		Map<String, Object> resultMap= deletePartsEstnRepository.deleteParts(deletePartsRequest);
		List<Object> tempList = (List<Object>) resultMap.get("#result-set-1");
	
		return tempList.get(0);
	}
	
	

}
