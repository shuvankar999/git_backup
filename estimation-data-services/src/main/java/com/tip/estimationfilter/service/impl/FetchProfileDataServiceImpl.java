package com.tip.estimationfilter.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.estimationfilter.model.DynamicFilterRequest;
import com.tip.estimationfilter.model.Filter;
import com.tip.estimationfilter.repository.FetchProfileDataRepository;
import com.tip.estimationfilter.service.FetchProfileDataService;
import com.tip.util.EstimationConstant;


@Service
public class FetchProfileDataServiceImpl implements FetchProfileDataService{
	
	 public static final Logger logger = LoggerFactory.getLogger(FetchProfileDataServiceImpl.class);

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
    			filter.setOperation(EstimationConstant.FILTER_MULTI_FILTER_OPERATION);
    			filter.setCondition(EstimationConstant.FILTER_AND_CONDITION);
    			filter.setElasticDbField("branch_id");
            	for(int i=0;i<lst.size();i++)
            	{
            		Map<String, Object> branchListMap = lst.get(i);
        			filter.getValueList().add((String)branchListMap.get("branchId"));
            	}
    			if(!filter.getValueList().isEmpty())
    				dynamicFilterRequest.getFilter().add(filter);
            	
            }	
        			
		}
	}
}
