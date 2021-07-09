package com.tip.estimation.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.estimation.model.FeeObject;
import com.tip.estimation.model.FetchAdminFeesRequest;
import com.tip.estimation.model.FetchAdminFeesResponse;
import com.tip.estimation.repository.FetchAdminFeesRepository;
import com.tip.estimation.service.FetchAdminFeesService;

@Service
public class FetchAdminFeesServiceImpl implements FetchAdminFeesService {
	
	@Autowired
	FetchAdminFeesRepository fetchAdminFeesRepository;
	
	@SuppressWarnings("unchecked")
	@Override
	public FetchAdminFeesResponse fetchAdminFees(FetchAdminFeesRequest fetchAdminFeesRequest) {

		Map<String, Object> resultMap = fetchAdminFeesRepository.fetchAdminFees(fetchAdminFeesRequest);
		FetchAdminFeesResponse fetchAdminFeesResponse = new FetchAdminFeesResponse();
		List<Map<String, Object>> feesList = (List<Map<String, Object>>) resultMap.get("#result-set-1");
		for (int i = 0; i < feesList.size(); i++) {
			Map<String, Object> feeMap = feesList.get(i);

			FeeObject feeObject = new FeeObject();

			feeObject.setFeesgroupId((Integer) feeMap.get("feesgroupId"));
			feeObject.setFeessubgroupId((Integer) feeMap.get("feessubgroupId"));
			feeObject.setFeesitemId((Integer) feeMap.get("feesitemId"));
			feeObject.setFeeDesc((String) feeMap.get("feeDesc"));
			feeObject.setFee((BigDecimal) feeMap.get("fee"));
			boolean flag = "Y".equalsIgnoreCase((String) feeMap.get("showHide")) ? true : false;
			feeObject.setShowHide(flag);
			feeObject.setIsApproved((String) feeMap.get("isApproved"));
			feeObject.setIsRejected((String) feeMap.get("isRejected"));
			fetchAdminFeesResponse.getAdminFeesResponseList().add(feeObject);

		}
		return fetchAdminFeesResponse;
	}
}
		
	
			