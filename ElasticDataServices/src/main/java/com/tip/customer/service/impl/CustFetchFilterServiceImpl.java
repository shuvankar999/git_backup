package com.tip.customer.service.impl;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.customer.repository.CustFetchFilterRepository;
import com.tip.customer.service.CustFetchFilterService;
import com.tip.customer.service.CustomerDataService;
import com.tip.elastic.util.ElasticSearchConstant;
import com.tip.equipmentdetails.model.DropOption;
import com.tip.equipmentdetails.model.FetchFilterDetails;
import com.tip.equipmentdetails.model.FetchFilterDetailsRequest;
import com.tip.equipmentdetails.model.FilterForm;
import com.tip.equipmentdetails.model.FilterOption;
import com.tip.equipmentdetails.model.MultiFilters;
import com.tip.equipmentdetails.model.OtherFilters;

@Service
@Transactional
public class CustFetchFilterServiceImpl implements CustFetchFilterService {
	
	
	 public static final Logger logger = LoggerFactory.getLogger(CustFetchFilterServiceImpl.class);

	@Autowired
	CustFetchFilterRepository custFetchFilterRepository;
	
	@Autowired
	CustomerDataService customerDataService;
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public FetchFilterDetails getFilterDetils(FetchFilterDetailsRequest fetchFilterDetailsRequest) {
		FetchFilterDetails fetchFilterDetails = new FetchFilterDetails();		
		Map<String, Object> filterReturnMap = custFetchFilterRepository.getFilterDetils(fetchFilterDetailsRequest);
		for (Map.Entry<String, Object> entry : filterReturnMap.entrySet())
		{
           if (("#result-set-1").equalsIgnoreCase(entry.getKey())) {
           	List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
           	for(int i=0;i<lst.size();i++)
           	{
           		Map<String, Object> lMultiFiltersMap = lst.get(i);
           		FilterOption filterOption = new FilterOption();
           		filterOption.setLabel((String)lMultiFiltersMap.get("label"));
           		filterOption.setLabelCd((String)lMultiFiltersMap.get("labelCd"));
           		fetchFilterDetails.setSsoId((String)lMultiFiltersMap.get("profileId"));
           		boolean selected;
           		if("true".equalsIgnoreCase((String)lMultiFiltersMap.get("selected"))) {
           			selected = true;
           		}
           		else {
           			selected = false;
           		}
           		filterOption.setSelected(selected);
           		if(!setExistingMultiFilter(fetchFilterDetails,lMultiFiltersMap,filterOption)) {
           			MultiFilters multiFilters = new MultiFilters();
               		multiFilters.setFilterLabel((String)lMultiFiltersMap.get("filterLabel"));
               		multiFilters.setFilterCd((Integer)lMultiFiltersMap.get("filterCd"));
               		multiFilters.setElasticDbColumn((String)lMultiFiltersMap.get("elasticDbColumn"));
               		multiFilters.getFilterOption().add(filterOption);
               		fetchFilterDetails.getFilterList().add(multiFilters);
           		}
           	}
           }
           else if (("#result-set-2").equalsIgnoreCase(entry.getKey())) {
           	List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
           	for(int i=0;i<lst.size();i++)
           	{
           		Map<String, Object> lOtherFiltersMap = lst.get(i);
           		FilterForm filterForm = new FilterForm();
           		filterForm.setLabel((String)lOtherFiltersMap.get("label"));
           		filterForm.setLabelCd((String)lOtherFiltersMap.get("labelCd"));
           		filterForm.setRangeDown((String)lOtherFiltersMap.get("rangeDown"));
           		filterForm.setRangeUp((String)lOtherFiltersMap.get("rangeUp"));
           		filterForm.setType((String)lOtherFiltersMap.get("type"));
        		if(((String)lOtherFiltersMap.get("type")).equalsIgnoreCase("selected")) {
    				List distinctListDropdown = new ArrayList<DropOption>();
					try {
						distinctListDropdown = customerDataService.getDistinctCustomerList((String)lOtherFiltersMap.get("elasticDbColumn"), ElasticSearchConstant.ENTITY_TYPE_CUSTOMER);
					} catch (UnknownHostException | InterruptedException | ExecutionException e) {
						logger.error("An error occurred during retrival of elastic data");
					}
    				filterForm.getDropDownOptions().addAll(distinctListDropdown);
    			}
           		filterForm.setValue((String)lOtherFiltersMap.get("value"));
           		if(!setExistingOtherFilter(fetchFilterDetails,lOtherFiltersMap,filterForm)) {
           			OtherFilters otherFilters = new OtherFilters();
           			otherFilters.setFilterLabel((String)lOtherFiltersMap.get("filterLabel"));
           			otherFilters.setFilterCd((Integer)lOtherFiltersMap.get("filterCd"));
           			otherFilters.getFilterForm().add(filterForm);
           			fetchFilterDetails.getFilterForms().add(otherFilters);
           		}
           		filterForm.setElasticDbColumn((String)lOtherFiltersMap.get("elasticDbColumn"));
           	}
           }
		}		
		return fetchFilterDetails;
	}
	
	private boolean setExistingOtherFilter(FetchFilterDetails fetchFilterDetails,
			Map<String, Object> lOtherFiltersMap, FilterForm filterForm) {
		boolean existFlag = false;
		for(OtherFilters otherFilters :fetchFilterDetails.getFilterForms())
		{
			if(otherFilters.getFilterCd().intValue() == (Integer)lOtherFiltersMap.get("filterCd") 
					&& otherFilters.getFilterLabel().equals((String)lOtherFiltersMap.get("filterLabel"))){
				existFlag = true;
				otherFilters.getFilterForm().add(filterForm);
				break;
			}
		}
		return existFlag;
	}

	private boolean setExistingMultiFilter(FetchFilterDetails fetchFilterDetails,
			Map<String, Object> lMultiFiltersMap, FilterOption filterOption){
		boolean existFlag = false;
		for(MultiFilters multiFilter :fetchFilterDetails.getFilterList())
		{
			if(multiFilter.getFilterCd().intValue() == (Integer)lMultiFiltersMap.get("filterCd")
					&& multiFilter.getFilterLabel().equals((String)lMultiFiltersMap.get("filterLabel"))){
				existFlag = true;
				multiFilter.getFilterOption().add(filterOption);
				break;
			}
		}
		return existFlag;
	}

}
