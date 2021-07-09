package com.tip.supplier.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.common.service.CommonService;
import com.tip.elastic.util.ElasticSearchConstant;
import com.tip.equipmentdetails.model.FetchFilterDetails;
import com.tip.equipmentdetails.model.FetchFilterDetailsRequest;
import com.tip.equipmentdetails.model.FilterForm;
import com.tip.equipmentdetails.model.FilterOption;
import com.tip.equipmentdetails.model.MultiFilters;
import com.tip.equipmentdetails.model.OtherFilters;
import com.tip.supplier.repository.SupplierFetchFilterRepository;
import com.tip.supplier.service.SupplierFetchFilterService;

@Service
@Transactional
public class SupplierFetchFilterServiceImpl implements SupplierFetchFilterService {
	
	
	 public static final Logger logger = LoggerFactory.getLogger(SupplierFetchFilterServiceImpl.class);

	@Autowired
	SupplierFetchFilterRepository supplierFetchFilterRepository;
	
	@Autowired
	CommonService commonService;
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public FetchFilterDetails getFilterDetils(FetchFilterDetailsRequest fetchFilterDetailsRequest) {
		FetchFilterDetails fetchFilterDetails = new FetchFilterDetails();		
		Map<String, Object> filterReturnMap = supplierFetchFilterRepository.getFilterDetils(fetchFilterDetailsRequest);
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
           		/*if(((String)lOtherFiltersMap.get("type")).equalsIgnoreCase("selected")) {
           			List distinctList = commonService.getDistinctList((String)lOtherFiltersMap.get("elasticDbColumn"), ElasticSearchConstant.MODULE_NAME_SUPPLIER);
           			filterForm.getDropDownOptions().addAll(distinctList);
           		}*/
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
