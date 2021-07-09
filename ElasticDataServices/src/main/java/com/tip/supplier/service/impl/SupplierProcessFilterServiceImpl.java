package com.tip.supplier.service.impl;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.elastic.util.ElasticSearchConstant;
import com.tip.elasticsearch.model.EnterpriseSearchData;
import com.tip.equipmentdetails.model.DynamicFilterRequest;
import com.tip.equipmentdetails.model.FetchFilterDetails;
import com.tip.equipmentdetails.model.Filter;
import com.tip.equipmentdetails.model.FilterForm;
import com.tip.equipmentdetails.model.FilterOption;
import com.tip.equipmentdetails.model.MultiFilters;
import com.tip.equipmentdetails.model.OtherFilters;
import com.tip.equipmentdetails.model.SaveFilterForm;
import com.tip.equipmentdetails.model.SaveFilterFormRequest;
import com.tip.supplier.model.ArrayOfSupplierData;
import com.tip.supplier.repository.SupplierSaveFilterRepository;
import com.tip.supplier.service.SupplierDynamicFiltersService;
import com.tip.supplier.service.SupplierProcessFilterService;

	
@Service
public class SupplierProcessFilterServiceImpl implements SupplierProcessFilterService{

	@Autowired
	SupplierDynamicFiltersService supplierDynamicFiltersService;
	
	@Autowired
	SupplierSaveFilterRepository saveFilterRepository;
	
	/*@Autowired
	FetchProfileDataService fetchProfileDataService;*/
	
	@Override
	public EnterpriseSearchData processFilterDetails(FetchFilterDetails filterDetails){
		EnterpriseSearchData enterpriseSearchData = new EnterpriseSearchData();	
		ArrayOfSupplierData lArrayOfSupplierData = new ArrayOfSupplierData();
		DynamicFilterRequest dynamicFilterRequest = new DynamicFilterRequest();
		SaveFilterFormRequest saveFilterFormRequest = new SaveFilterFormRequest();
		
		setMultiFilters(filterDetails, dynamicFilterRequest, saveFilterFormRequest);
		setOtherFilters(filterDetails, dynamicFilterRequest, saveFilterFormRequest);
		if(!saveFilterRepository.saveFilterDetils(saveFilterFormRequest)) {
			enterpriseSearchData.setErrorMsg(ElasticSearchConstant.EXCEPTION_OCCURRED_SAVE_FILTER);
		}
		
		setSearchTextFilter(filterDetails, dynamicFilterRequest);
		Long totalCount;
		try {
			totalCount = supplierDynamicFiltersService.getTotalCountOfTextSearch(dynamicFilterRequest);

			if(totalCount>0) {
				int fromIndex = filterDetails.getFromIndex();
				int size = filterDetails.getIndexSize();
				lArrayOfSupplierData = supplierDynamicFiltersService.getPaginatedEstimationData(dynamicFilterRequest, fromIndex , size);
			}
			enterpriseSearchData.setSupplierCount(totalCount);
			
		} catch (UnknownHostException e) {
			enterpriseSearchData.setErrorMsg(ElasticSearchConstant.EXCEPTION_OCCURRED);
		}
		enterpriseSearchData.setlArrayOfSupplierData(lArrayOfSupplierData);
		return enterpriseSearchData;
	}
	
	private void setSearchTextFilter(FetchFilterDetails filterDetails, DynamicFilterRequest dynamicFilterRequest){
		
		if(filterDetails.getSearchText()!=null && !filterDetails.getSearchText().isEmpty()){
			Filter filter = new Filter();
			filter.setOperation(ElasticSearchConstant.FILTER_SEARCH_TEXT_OPERATION);
			filter.setCondition(ElasticSearchConstant.FILTER_AND_CONDITION);
			filter.setValue(filterDetails.getSearchText());
			dynamicFilterRequest.getFilter().add(filter);
		}else{
			//fetchProfileDataService.fetchProfileData(filterDetails.getSsoId(),dynamicFilterRequest);
		}
	}
	private void setMultiFilters(FetchFilterDetails filterDetails, DynamicFilterRequest dynamicFilterRequest, SaveFilterFormRequest saveFilterFormRequest){
		for(MultiFilters multiFilters : filterDetails.getFilterList())
		{
			Filter filter = new Filter();
			filter.setOperation(ElasticSearchConstant.FILTER_MULTI_FILTER_OPERATION);
			filter.setCondition(ElasticSearchConstant.FILTER_AND_CONDITION);
			filter.setElasticDbField(multiFilters.getElasticDbColumn());
			for(FilterOption filterOption : multiFilters.getFilterOption()){
				SaveFilterForm saveFilterForm = new SaveFilterForm();
				if(filterOption.isSelected()){
					filter.getValueList().add(filterOption.getLabel());
					saveFilterForm.setFilterSelected("true");
				}else{
					saveFilterForm.setFilterSelected("false");

				}
				saveFilterForm.setSsoId(filterDetails.getSsoId());
				saveFilterForm.setFiltergroupId(multiFilters.getFilterCd());
				saveFilterForm.setFilterId(filterOption.getLabelCd());
				saveFilterForm.setFiltervalue(filterOption.getLabel());
				saveFilterFormRequest.getFilterForm().add(saveFilterForm);
			}
			if(!filter.getValueList().isEmpty())
				dynamicFilterRequest.getFilter().add(filter);
		}
	}
	
	private void setOtherFilters(FetchFilterDetails filterDetails, DynamicFilterRequest dynamicFilterRequest, SaveFilterFormRequest saveFilterFormRequest){
		for(OtherFilters otherFilters : filterDetails.getFilterForms())
		{
			for (FilterForm filterForm : otherFilters.getFilterForm()) {
				Filter filter = new Filter();
				filter.setCondition(ElasticSearchConstant.FILTER_AND_CONDITION);
				filter.setElasticDbField(filterForm.getElasticDbColumn());
				if (filterForm.getRangeDown() != null && filterForm.getRangeUp() != null) {
					filter.setOperation(ElasticSearchConstant.FILTER_RANGE_OPERATION);
					filter.setRangeLt(filterForm.getRangeUp());
					filter.setRangeGt(filterForm.getRangeDown());
					filter.setIncludeLower(true);
					filter.setIncludeUpper(true);
				} else if (filterForm.getValue() != null && !filterForm.getValue().trim().isEmpty()) {
					filter.setOperation(ElasticSearchConstant.FILTER_EQUALS_OPERATION);
					filter.setValue(filterForm.getValue());
				}

				if (filter.getOperation() != null)
					dynamicFilterRequest.getFilter().add(filter);

				SaveFilterForm saveFilterForm = new SaveFilterForm();
				saveFilterForm.setSsoId(filterDetails.getSsoId());
				saveFilterForm.setFiltergroupId(otherFilters.getFilterCd());
				saveFilterForm.setFilterId(filterForm.getLabelCd());
				saveFilterForm.setRangeup(filterForm.getRangeUp() != null ? filterForm.getRangeUp().toString() : null);
				saveFilterForm
						.setRangedown(filterForm.getRangeDown() != null ? filterForm.getRangeDown().toString() : null);
				saveFilterForm.setFiltervalue(filterForm.getValue());
				saveFilterFormRequest.getFilterForm().add(saveFilterForm);
			}

		}
	}

}
