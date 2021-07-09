package com.tip.elasticsearch.controller.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tip.elasticsearch.model.Unit;

/**
 * Created by 398072
 */
@Component
public class ElasticControllerHelper {
    
    public static final Logger logger = LoggerFactory.getLogger(ElasticControllerHelper.class);
    
    public Map<String, String> setFilterForAsset(String unitNr) {
		Map<String, String> fields = new HashMap<>(0);
		if(unitNr != null)
		{
			fields.put("unit_nr", unitNr);
		}
		return fields;
	}
    
    public List<Map<String, String>> setFilterForDownloadAsset(List<Unit> list) {
    	List<Map<String, String>> fieldList = new ArrayList<>();
    	for(Unit unit : list)
    	{
    		Map<String, String> fields = setFilterForAsset(unit.getUnitNr());
    		fieldList.add(fields);
    	}
		return fieldList;
	}
    
    public Map<String, String> setBranchIdFilter(String branchNr) {
		Map<String, String> fields = new HashMap<>(0);
		if(branchNr !=null && !branchNr.isEmpty())
		{
			fields.put("unit_physical_branch_nr",branchNr);
		}
		return fields;
	}
}