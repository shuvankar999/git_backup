package com.tip.estimation.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.estimation.model.EnrichPartsObject;
import com.tip.estimation.model.EnrichResponse;
import com.tip.estimation.model.EnrichWorkOrderObject;
import com.tip.estimation.model.SaveEnrichDetails;
import com.tip.estimation.model.VersionObject;
import com.tip.estimation.repository.EnrichBundleRepository;
import com.tip.estimation.repository.EnrichConsumablesRepository;
import com.tip.estimation.repository.EnrichHeaderRepository;
import com.tip.estimation.repository.EnrichPartsRepository;
import com.tip.estimation.repository.EnrichTyreServiceRepository;
import com.tip.estimation.repository.EnrichTyreSpecsRepository;
import com.tip.estimation.repository.EnrichWoWotRepository;
import com.tip.estimation.repository.SaveEnrichAdminFeeRepository;
import com.tip.estimation.service.SaveEnrichOtherDetailsService;

@Service
public class SaveEnrichOtherDetailsServiceImpl implements SaveEnrichOtherDetailsService {

	@Autowired
	EnrichHeaderRepository enrichHeaderRepository;

	@Autowired
	EnrichWoWotRepository enrichWoWotRepository;

	@Autowired
	EnrichPartsRepository enrichPartsRepository;

	@Autowired
	EnrichTyreSpecsRepository enrichTyreSpecsRepository;

	@Autowired
	EnrichTyreServiceRepository enrichTyreServiceRepository;

	@Autowired
	EnrichConsumablesRepository enrichConsumablesRepository;
	
	@Autowired
	EnrichBundleRepository enrichBundleRepository;
	
	@Autowired
	SaveEnrichAdminFeeRepository saveEnrichAdminFeeRepository;

	@Override
	public EnrichResponse saveDetails(SaveEnrichDetails saveEnrichDetails, VersionObject versnObject) {
		EnrichResponse enrichResponse = new EnrichResponse();
		Map<String, String> enrichResponseMap = new HashMap();
		//List<String> enrichResponseList = new ArrayList();
		
		saveWorkorder(saveEnrichDetails, versnObject, enrichResponseMap);
		saveParts(saveEnrichDetails, versnObject, enrichResponseMap);
		saveTyreSpec(saveEnrichDetails, versnObject, enrichResponseMap);
		saveTyreService(saveEnrichDetails, versnObject, enrichResponseMap);
		saveConsumables(saveEnrichDetails, versnObject, enrichResponseMap);
		saveBundles(saveEnrichDetails, versnObject, enrichResponseMap);
		saveAdminFee(saveEnrichDetails, versnObject, enrichResponseMap);
		enrichResponseMap.forEach((k,v)->{
			if(v.equalsIgnoreCase("FAILURE")) {
				enrichResponse.setErrorCd("FAILURE - while saving "+k);
			}else{
				enrichResponse.setErrorCd("SUCCESS");
				enrichResponse.setVersionObject(versnObject);
			}
				
		});
		/*if (enrichResponseList.contains("FAILURE")) {
			enrichResponse.setErrorCd("FAILURE");
		} else {
			enrichResponse.setErrorCd("SUCCESS");
			enrichResponse.setVersionObject(versnObject);
		}*/

		return enrichResponse;
	}

	private void saveBundles(SaveEnrichDetails saveEnrichDetails, VersionObject versnObject,
			Map<String, String> enrichResponseMap) {
		String errorMsg;
		if (saveEnrichDetails.getEstnBundleLists() != null && !saveEnrichDetails.getEstnBundleLists().isEmpty() ) {
			errorMsg = enrichBundleRepository.saveBundleDetails(saveEnrichDetails, versnObject);
			enrichResponseMap.put("Bundles", errorMsg);
		}
	}

	private void saveConsumables(SaveEnrichDetails saveEnrichDetails, VersionObject versnObject,
			Map<String, String> enrichResponseMap) {
		String errorMsg;
		if (saveEnrichDetails.getEstnConsumablesDetails() != null && !saveEnrichDetails.getEstnConsumablesDetails().isEmpty()) {
			errorMsg = enrichConsumablesRepository.saveEnrichDetails(saveEnrichDetails.getEstnConsumablesDetails(),
					versnObject, saveEnrichDetails);
			enrichResponseMap.put("Consumables", errorMsg);
		}
	}

	private void saveTyreService(SaveEnrichDetails saveEnrichDetails, VersionObject versnObject,
			Map<String, String> enrichResponseMap) {
		String errorMsg;
		if (saveEnrichDetails.getTyreServicelist() != null && !saveEnrichDetails.getTyreServicelist().isEmpty()) {
			errorMsg = enrichTyreServiceRepository.saveEnrichDetails(saveEnrichDetails.getTyreServicelist(),
					versnObject, saveEnrichDetails);
			enrichResponseMap.put("Tyreservice", errorMsg);
		}
	}

	private void saveTyreSpec(SaveEnrichDetails saveEnrichDetails, VersionObject versnObject,
			Map<String, String> enrichResponseMap) {
		String errorMsg;
		if (saveEnrichDetails.getTyreSpeclist() != null && !saveEnrichDetails.getTyreSpeclist().isEmpty()) {
			errorMsg = enrichTyreSpecsRepository.saveEnrichDetails(saveEnrichDetails.getTyreSpeclist(), versnObject,
					saveEnrichDetails);
			enrichResponseMap.put("Tyrespecs", errorMsg);
		}
	}

	private void saveParts(SaveEnrichDetails saveEnrichDetails, VersionObject versnObject,
			Map<String, String> enrichResponseMap) {
		String errorMsg;
		List<EnrichPartsObject> listOfParts = new ArrayList();
		for (EnrichWorkOrderObject enrichWoList : saveEnrichDetails.getEstnHeaderList().getEstnWoList()) {
			listOfParts.addAll(enrichWoList.getEstnMatchedPartLists());
		}
		listOfParts.addAll(saveEnrichDetails.getEstnUnMatchedPartLists());

		if (listOfParts.size() > 0) {
			errorMsg = enrichPartsRepository.saveEnrichDetails(listOfParts, versnObject, saveEnrichDetails);
			enrichResponseMap.put("Parts", errorMsg);
		}
	}

	private void saveWorkorder(SaveEnrichDetails saveEnrichDetails, VersionObject versnObject,
			Map<String, String> enrichResponseMap) {
		String errorMsg;
		if (saveEnrichDetails.getEstnHeaderList().getEstnWoList() != null && !saveEnrichDetails.getEstnHeaderList().getEstnWoList().isEmpty()) {
			errorMsg = enrichWoWotRepository.saveEnrichDetails(saveEnrichDetails.getEstnHeaderList().getEstnWoList(),
					versnObject, saveEnrichDetails);
			enrichResponseMap.put("LabourOrders", errorMsg);
		}
	}
	private void saveAdminFee(SaveEnrichDetails saveEnrichDetails, VersionObject versnObject,
			Map<String, String> enrichResponseMap) {
		String errorMsg;
		if (saveEnrichDetails.getEstnFeeList() != null && !saveEnrichDetails.getEstnFeeList().isEmpty()) {
			errorMsg = saveEnrichAdminFeeRepository.saveEnrichDetails(saveEnrichDetails.getEstnFeeList(), versnObject,
					saveEnrichDetails);
			enrichResponseMap.put("AdminFees", errorMsg);
		}
	}

}
