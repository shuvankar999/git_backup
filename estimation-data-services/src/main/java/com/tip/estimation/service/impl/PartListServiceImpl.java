package com.tip.estimation.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.estimation.model.PartListRequest;
import com.tip.estimation.model.PartListResponse;
import com.tip.estimation.model.PartsCountObject;
import com.tip.estimation.model.PartsResponseObject;
import com.tip.estimation.repository.PartListRepository;
import com.tip.estimation.service.PartListService;

@Service
public class PartListServiceImpl implements PartListService {

	static final Logger logger = LoggerFactory.getLogger(PartListServiceImpl.class);

	@Autowired
	PartListRepository partListRepository;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PartListResponse getPartList(PartListRequest partListRequest) {

		Map<String, Object> returnMap = partListRepository.getPartList(partListRequest);
		PartListResponse partListResponse = new PartListResponse();
		List<PartsCountObject> partsCountResponseList = new ArrayList<>();
		List<PartsResponseObject> partsDetailsResponseList = new ArrayList<>();

		if (null != returnMap) {
			for (Map.Entry<String, Object> entry : returnMap.entrySet()) {
				if ("#result-set-1".equalsIgnoreCase(entry.getKey())) {
					List<Map<String, Object>> countList = (List<Map<String, Object>>) entry.getValue();
					setPartsCountList(countList, partsCountResponseList);
				} else if ("#result-set-2".equalsIgnoreCase(entry.getKey())) {
					List<Map<String, Object>> partsDetailsList = (List<Map<String, Object>>) entry.getValue();
					setPartsDetailsList(partsDetailsList, partsDetailsResponseList);
				}
			}
		}
		partListResponse.getEstnPartsCountList().addAll(partsCountResponseList);
		partListResponse.getEstnPartsResponseList().addAll(partsDetailsResponseList);
		return partListResponse;
	}

	private void setPartsDetailsList(List<Map<String, Object>> partsDetailsList,
			List<PartsResponseObject> partsDetailsResponseList) {
		for (int i = 0; i < partsDetailsList.size(); i++) {
			Map<String, Object> partDetailsMap = partsDetailsList.get(i);
			PartsResponseObject partsResponseObject = new PartsResponseObject();
			partsResponseObject.setEstimationId((BigDecimal) partDetailsMap.get("estimationId"));
			partsResponseObject.setEstnWOId((Integer) partDetailsMap.get("estnWOId"));
			partsResponseObject.setEstnWOTId((Integer) partDetailsMap.get("estnWOTId"));
			partsResponseObject.setPartNumber((String) partDetailsMap.get("partNumber"));

			partsResponseObject.setPartDesc((String) partDetailsMap.get("partDesc"));
			partsResponseObject.setSupplierId((Integer) partDetailsMap.get("supplierId"));
			partsResponseObject.setQty((Integer) partDetailsMap.get("qty"));

			partsResponseObject.setCurrency((String) partDetailsMap.get("currency"));
			partsResponseObject.setCostToTip((BigDecimal) partDetailsMap.get("costToTip"));
			partsResponseObject.setDicount((BigDecimal) partDetailsMap.get("dicount"));

			partsResponseObject.setShowHide((String) partDetailsMap.get("showHide"));
			partsResponseObject.setRetailPrice((BigDecimal) partDetailsMap.get("retailPrice"));

			partsDetailsResponseList.add(partsResponseObject);
		}

	}

	private void setPartsCountList(List<Map<String, Object>> partCountList,
			List<PartsCountObject> partsCountResponseList) {
		for (int i = 0; i < partCountList.size(); i++) {
			Map<String, Object> partCountMap = partCountList.get(i);
			PartsCountObject partsCountObject = new PartsCountObject();
			partsCountObject.setEstimationId((BigDecimal) partCountMap.get("estimationId"));
			partsCountObject.setEstnWOId((Integer) partCountMap.get("estnWOId"));
			partsCountObject.setEstnWOTId((Integer) partCountMap.get("estnWOTId"));
			partsCountObject.setTotalParts((Integer) partCountMap.get("totalParts"));

			partsCountResponseList.add(partsCountObject);
		}
	}
}
