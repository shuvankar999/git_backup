package com.tip.equipmentdetails.service.impl;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.asset.controller.helper.AssetControllerHelper;
import com.tip.asset.service.DynamicPosValService;
import com.tip.customer.service.CustomerDataService;
import com.tip.elastic.util.ElasticSearchConstant;
import com.tip.equipmentdetails.model.DropOption;
import com.tip.equipmentdetails.model.ExtraFilter;
import com.tip.equipmentdetails.model.FetchFieldLabel;
import com.tip.equipmentdetails.model.FetchFilterDetails;
import com.tip.equipmentdetails.model.FetchFilterDetailsRequest;
import com.tip.equipmentdetails.model.FilterForm;
import com.tip.equipmentdetails.model.FilterOption;
import com.tip.equipmentdetails.model.MultiFilters;
import com.tip.equipmentdetails.model.OtherFilters;
import com.tip.equipmentdetails.model.ViewDetails;
import com.tip.equipmentdetails.repository.FetchFilterDetailsRepository;
import com.tip.equipmentdetails.service.FetchFilterDetailsService;

@Service
public class FetchFilterDetailsServiceImpl implements FetchFilterDetailsService{
	
	 public static final Logger logger = LoggerFactory.getLogger(FetchFilterDetailsService.class);

	@Autowired
	FetchFilterDetailsRepository fetchFilterDetailsRepository;
	
	@Autowired
	CustomerDataService customerDataService;
	
	@Autowired
    AssetControllerHelper assetControllerHelper;
	
	@Autowired
    DynamicPosValService dynamicPosValService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public FetchFilterDetails getFilterDetils(FetchFilterDetailsRequest fetchFilterDetailsRequest) {
		FetchFilterDetails fetchFilterDetails = new FetchFilterDetails();		
		Map<String, Object> filterReturnMap = fetchFilterDetailsRepository.getFilterDetils(fetchFilterDetailsRequest);
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

        		Map<String, List<String>> mapOfDynamicPosVal = null;
				try {
					mapOfDynamicPosVal = getDropDownData(lst);
				} catch (UnknownHostException e) {
					logger.error("An error occured"+e);
				}
            	
            	for(int i=0;i<lst.size();i++)
            	{
            		Map<String, Object> lOtherFiltersMap = lst.get(i);
            		FilterForm filterForm = new FilterForm();
            		filterForm.setLabel((String)lOtherFiltersMap.get("label"));
            		filterForm.setLabelCd((String)lOtherFiltersMap.get("labelCd"));
            		filterForm.setRangeDown((String)lOtherFiltersMap.get("rangeDown"));
            		filterForm.setRangeUp((String)lOtherFiltersMap.get("rangeUp"));
            		filterForm.setType((String)lOtherFiltersMap.get("type"));
            		if("selected".equalsIgnoreCase((String)lOtherFiltersMap.get("type"))){
            			setDropDownData(mapOfDynamicPosVal, lOtherFiltersMap, filterForm);
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
            }else if (("#result-set-3").equalsIgnoreCase(entry.getKey())) {

            	List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
            	lst.forEach(extraFilterMap->{
            		try {
            			setExtraFilter(fetchFilterDetails,extraFilterMap);
					} catch (InterruptedException | ExecutionException e) {
						logger.error("An error occurred during customer data retrival from EDB : "+e);
					}
            	});
            }else if (("#result-set-4").equalsIgnoreCase(entry.getKey())) {

            	List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
            	lst.forEach(fieldLabelMap->{
            		try {
            			setFieldLabel(fetchFilterDetails,fieldLabelMap);
					} catch (InterruptedException | ExecutionException e) {
						logger.error("An error occurred during customer data retrival from EDB : "+e);
					}
            	});
            }else if (("#result-set-5").equalsIgnoreCase(entry.getKey())) {

            	List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
            	lst.forEach(viewDetMap->{
            		try {
            			setViewDetails(fetchFilterDetails,viewDetMap);
					} catch (InterruptedException | ExecutionException e) {
						logger.error("An error occurred during customer data retrival from EDB : "+e);
					}
            	});
            }
		}		
		return fetchFilterDetails;
	}
	
	private void setFieldLabel(FetchFilterDetails fetchFilterDetails, Map<String, Object> fieldLabelMap) throws InterruptedException, ExecutionException {
		FetchFieldLabel fetchFieldLabel = new FetchFieldLabel();
		fetchFieldLabel.setProfileId((String)fieldLabelMap.get("profileId"));
		fetchFieldLabel.setAppCd((String)fieldLabelMap.get("appCd"));
		fetchFieldLabel.setFieldName((String)fieldLabelMap.get("fieldName"));
		if(fieldLabelMap.get("selected") != null)
		{
			if("TRUE".equalsIgnoreCase((String) fieldLabelMap.get("selected"))) {
				fetchFieldLabel.setSelected(true);
			} else {
				fetchFieldLabel.setSelected(false);
			}
		}
		fetchFilterDetails.getFieldLabelList().add(fetchFieldLabel);
	}
	
	private void setViewDetails(FetchFilterDetails fetchFilterDetails, Map<String, Object> viewDetMap) throws InterruptedException, ExecutionException {
		ViewDetails viewDetails = new ViewDetails();
		viewDetails.setProfileId((String)viewDetMap.get("profileId"));
		viewDetails.setAppCd((String)viewDetMap.get("appCd"));
		viewDetails.setFieldName((String)viewDetMap.get("fieldName"));
		if(viewDetMap.get("selected") != null)
		{
			if("TRUE".equalsIgnoreCase((String) viewDetMap.get("selected"))) {
				viewDetails.setSelected(true);
			} else {
				viewDetails.setSelected(false);
			}
		}
		fetchFilterDetails.getViewDetailsList().add(viewDetails);
	}

	private void setExtraFilter(FetchFilterDetails fetchFilterDetails, Map<String, Object> lOtherFiltersMap) throws InterruptedException, ExecutionException {
		
		try {
			
			ExtraFilter extraFilter = new ExtraFilter();
			extraFilter.setFilterLabel((String)lOtherFiltersMap.get("filterLabel"));
			extraFilter.setFilterCd((Integer)lOtherFiltersMap.get("filterCd"));
			extraFilter.setLabel((String)lOtherFiltersMap.get("label"));
			extraFilter.setLabelCd((String)lOtherFiltersMap.get("labelCd"));
			extraFilter.setType((String)lOtherFiltersMap.get("type"));
			if(((String)lOtherFiltersMap.get("type")).equalsIgnoreCase("selected")) {
				List distinctListDropdown = customerDataService.getDistinctCustomerList((String)lOtherFiltersMap.get("elasticDbColumn"), ElasticSearchConstant.ENTITY_TYPE_EQUIP);
				extraFilter.getDropDownOptions().addAll(distinctListDropdown);
			}
			extraFilter.setValue((String)lOtherFiltersMap.get("value"));
			extraFilter.setElasticDbColumn((String)lOtherFiltersMap.get("elasticDbColumn"));
			fetchFilterDetails.getExtraFiltersObjList().add(extraFilter);
		} catch (UnknownHostException e) {
			logger.error("An error occurred during customer data retrival from EDB : "+e);
			e.printStackTrace();
		}
	}

	private void setDropDownData(Map<String, List<String>> mapOfDynamicPosVal, Map<String, Object> lOtherFiltersMap,
			FilterForm filterForm) {
		List<String> dynamicPosValLst = mapOfDynamicPosVal.get((Integer)lOtherFiltersMap.get("filterCd") + "@@@" + (String)lOtherFiltersMap.get("labelCd"));
		if(dynamicPosValLst != null && !dynamicPosValLst.isEmpty())
		{
			dynamicPosValLst.forEach(posVal->{
				DropOption option = new DropOption();
				option.setName(posVal);
				option.setValue(posVal);
				filterForm.getDropDownOptions().add(option);
			});
		}
	}

	private Map<String, List<String>> getDropDownData(List<Map<String, Object>> lst) throws UnknownHostException {
		List<Map<String, String>> fieldsList = new ArrayList<>();
		lst.forEach(mapObj->{
			if("selected".equalsIgnoreCase((String)mapObj.get("type"))) {
				Map<String, String> fields = new HashMap();
				fields.put("specgr_id", ((Integer)mapObj.get("filterCd")).toString());
				fields.put("specitm_seq", ((String)mapObj.get("labelCd")));
				fieldsList.add(fields);
			}
		});
		return dynamicPosValService.getAllDynamicPosVals(fieldsList);
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
