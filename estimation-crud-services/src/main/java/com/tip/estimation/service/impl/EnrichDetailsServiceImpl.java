/*package com.tip.estimation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.estimation.model.EnrichPartsObject;
import com.tip.estimation.model.EnrichResponse;
import com.tip.estimation.model.EnrichWorkOrderObject;
import com.tip.estimation.model.SaveEnrichDetails;
import com.tip.estimation.model.VersionObject;
import com.tip.estimation.repository.EnrichConsumablesRepository;
import com.tip.estimation.repository.EnrichHeaderRepository;
import com.tip.estimation.repository.EnrichPartsRepository;
import com.tip.estimation.repository.EnrichTyreServiceRepository;
import com.tip.estimation.repository.EnrichTyreSpecsRepository;
import com.tip.estimation.repository.EnrichWoWotRepository;
import com.tip.estimation.service.EnrichDetailsService;


	@Service
	public class EnrichDetailsServiceImpl implements EnrichDetailsService{
		
		@Autowired
		EnrichHeaderRepository  enrichHeaderRepository;
		
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
		
		
		
		@Override
		public EnrichResponse saveEnrichDetails(SaveEnrichDetails saveEnrichDetails) {
			
			EnrichResponse enrichResponse = new EnrichResponse();
			List<String> enrichResponseList = new ArrayList();
			String errorMsg;
			List<EnrichPartsObject> listOfParts = new ArrayList();
			VersionObject versnObject = enrichHeaderRepository.saveEnrichDetails(saveEnrichDetails.getEstnHeaderList());
			
			
			
			
			if(saveEnrichDetails.getEstnHeaderList().getEstnWoList()!= null){
			errorMsg = enrichWoWotRepository.saveEnrichDetails(saveEnrichDetails.getEstnHeaderList().getEstnWoList(),versnObject, saveEnrichDetails);
			enrichResponseList.add(errorMsg);
			}
			
			for(EnrichWorkOrderObject enrichWoList : saveEnrichDetails.getEstnHeaderList().getEstnWoList()){
				listOfParts.addAll(enrichWoList.getEstnMatchedPartLists());
			}
			listOfParts.addAll(saveEnrichDetails.getEstnUnMatchedPartLists());
			
			if (listOfParts.size() > 0) {			
			errorMsg = enrichPartsRepository.saveEnrichDetails(listOfParts,versnObject, saveEnrichDetails);						
				enrichResponseList.add(errorMsg);
			}
				
			if (saveEnrichDetails.getTyreSpeclist()!= null) {
				errorMsg = enrichTyreSpecsRepository.saveEnrichDetails(saveEnrichDetails.getTyreSpeclist(),
						versnObject, saveEnrichDetails);
				enrichResponseList.add(errorMsg);
			}
			
			if (saveEnrichDetails.getTyreServicelist()!= null) {
				errorMsg = enrichTyreServiceRepository
						.saveEnrichDetails(saveEnrichDetails.getTyreServicelist(), versnObject, saveEnrichDetails);
				enrichResponseList.add(errorMsg);
			}
			
			if (saveEnrichDetails.getEstnConsumablesDetails()!= null) {
				errorMsg = enrichConsumablesRepository.saveEnrichDetails(saveEnrichDetails.getEstnConsumablesDetails(),
						versnObject, saveEnrichDetails);
				enrichResponseList.add(errorMsg);
			}
			
				if(enrichResponseList.contains("FAILURE")){					
					enrichResponse.setErrorCd("FAILURE");
				} else{
					enrichResponse.setErrorCd("SUCCESS");
					enrichResponse.setVersionObject(versnObject);
				}

				return enrichResponse;
			
	   }
		
		public static String checkValue(String value){
			String val;
			if(value.equals(Boolean.TRUE)){
				 val = "Y";
			}else{
				val = "N";
			}
			return val;
		}
	}
*/