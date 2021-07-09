package com.tip.equipmentdetails.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.asset.util.AssetConstants;
import com.tip.equipmentdetails.model.DynamicFilterRequest;
import com.tip.equipmentdetails.model.Filter;
import com.tip.equipmentdetails.repository.FetchProfileDataRepository;
import com.tip.equipmentdetails.service.FetchFilterDetailsService;
import com.tip.equipmentdetails.service.FetchProfileDataService;

@Service
public class FetchProfileDataServiceImpl implements FetchProfileDataService{
	
	 public static final Logger logger = LoggerFactory.getLogger(FetchFilterDetailsService.class);

	@Autowired
	FetchProfileDataRepository fetchProfileDataRepository;
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public void fetchProfileData(String ssoId, DynamicFilterRequest dynamicFilterRequest){	
		Map<String, Object> resultMap = fetchProfileDataRepository.fetchProfileData(ssoId);
		for (Map.Entry<String, Object> entry : resultMap.entrySet())
		{
            if (("#result-set-1").equalsIgnoreCase(entry.getKey())) {
            	List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
        		
        		Filter filter = new Filter();
    			filter.setOperation(AssetConstants.FILTER_MULTI_FILTER_OPERATION);
    			filter.setCondition(AssetConstants.FILTER_AND_CONDITION);
    			filter.setElasticDbField("unit_physical_branch_nr");
            	for(int i=0;i<lst.size();i++)
            	{
            		Map<String, Object> branchListMap = lst.get(i);
        			filter.getValueList().add(Integer.toString((Integer)branchListMap.get("branchNr")));
            	}
    			if(!filter.getValueList().isEmpty())
    				dynamicFilterRequest.getFilter().add(filter);
            	
            }	
        			
		}
	}
}
