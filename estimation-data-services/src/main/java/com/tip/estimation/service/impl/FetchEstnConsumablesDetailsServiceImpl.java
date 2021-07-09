package com.tip.estimation.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.estimation.model.EstnConsmbleObject;
import com.tip.estimation.model.FetchEstnConsumablesRequest;
import com.tip.estimation.model.FetchEstnConsumablesResponse;
import com.tip.estimation.repository.FetchEstnConsumablesDetailsRepository;
import com.tip.estimation.service.FetchEstnConsumablesDetailsService;

@Service
public class FetchEstnConsumablesDetailsServiceImpl implements FetchEstnConsumablesDetailsService{
	
	@Autowired
	FetchEstnConsumablesDetailsRepository fetchEstnConsumablesDetailsRepository;
	
	@SuppressWarnings("unchecked")
	@Override
	public FetchEstnConsumablesResponse getConsumablesDetails(FetchEstnConsumablesRequest fetchEstnConsumablesRequest) {

		Map<String, Object> resultMap = fetchEstnConsumablesDetailsRepository.getConsumablesDetails(fetchEstnConsumablesRequest);
		FetchEstnConsumablesResponse fetchEstnConsumablesResponse = new FetchEstnConsumablesResponse();
		List<Map<String, Object>> consumableList = (List<Map<String, Object>>) resultMap.get("#result-set-1");
		for (int i = 0; i < consumableList.size(); i++) {
			Map<String, Object> estnCnsumbleObjectMap = consumableList.get(i);

			EstnConsmbleObject estnConsmbleObject = new EstnConsmbleObject();

			estnConsmbleObject.setConsmblegroupId((Integer) estnCnsumbleObjectMap.get("consmblegroupId"));
			estnConsmbleObject.setConsmblesubGroupId((Integer) estnCnsumbleObjectMap.get("consmblesubGroupId"));
			estnConsmbleObject.setConsmbleitemId((Integer) estnCnsumbleObjectMap.get("consmbleitemId"));
			estnConsmbleObject.setMinAmount((BigDecimal) estnCnsumbleObjectMap.get("minAmount"));
			estnConsmbleObject.setMaxAmount((BigDecimal) estnCnsumbleObjectMap.get("maxAmount"));
			estnConsmbleObject.setPercentage((BigDecimal) estnCnsumbleObjectMap.get("percentage"));
			estnConsmbleObject.setFee((BigDecimal) estnCnsumbleObjectMap.get("fee"));
			estnConsmbleObject.setCharges((BigDecimal) estnCnsumbleObjectMap.get("charges"));
			estnConsmbleObject.setConsumables((String) estnCnsumbleObjectMap.get("consumables"));
			boolean flag = "Y".equalsIgnoreCase((String) estnCnsumbleObjectMap.get("showHide")) ? true : false;
			estnConsmbleObject.setShowHide(flag);
			estnConsmbleObject.setIsApproved((String) estnCnsumbleObjectMap.get("isApproved"));
			estnConsmbleObject.setIsRejected((String) estnCnsumbleObjectMap.get("isRejected"));
			fetchEstnConsumablesResponse.getEstnConsmblelist().add(estnConsmbleObject);

		}
		return fetchEstnConsumablesResponse;
	}
}
		
	
			


