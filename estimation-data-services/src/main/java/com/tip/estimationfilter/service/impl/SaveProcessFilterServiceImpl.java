package com.tip.estimationfilter.service.impl;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.estimationfilter.model.ArrayOfEstimationData;
import com.tip.estimationfilter.model.DynamicFilterRequest;
import com.tip.estimationfilter.model.EnterpriseSearchDataEst;
import com.tip.estimationfilter.model.ExtraFilter;
import com.tip.estimationfilter.model.FetchFilterDetails;
import com.tip.estimationfilter.model.Filter;
import com.tip.estimationfilter.model.FilterForm;
import com.tip.estimationfilter.model.FilterOption;
import com.tip.estimationfilter.model.MultiFilters;
import com.tip.estimationfilter.model.OtherFilters;
import com.tip.estimationfilter.model.SaveFilterForm;
import com.tip.estimationfilter.model.SaveFilterFormRequest;
import com.tip.estimationfilter.repository.SaveFilterRepository;
import com.tip.estimationfilter.service.EstimationDynamicFiltersService;
import com.tip.estimationfilter.service.FetchProfileDataService;
import com.tip.estimationfilter.service.SaveProcessFilterService;
import com.tip.util.EstimationConstant;
	
@Service
public class SaveProcessFilterServiceImpl implements SaveProcessFilterService{

	@Autowired
	EstimationDynamicFiltersService estimationDynamicFiltersService;
	
	@Autowired
	SaveFilterRepository saveFilterRepository;
	
	@Autowired
	FetchProfileDataService fetchProfileDataService;
	
	@Override
	public EnterpriseSearchDataEst getFilterDetils(FetchFilterDetails fetchFilterDetails) {
		EnterpriseSearchDataEst enterpriseSearchDataEst = new EnterpriseSearchDataEst();	
		ArrayOfEstimationData lArrayOfEstimationData = new ArrayOfEstimationData();
		DynamicFilterRequest dynamicFilterRequest = new DynamicFilterRequest();
		SaveFilterFormRequest saveFilterFormRequest = new SaveFilterFormRequest();
		
		setMultiFilters(fetchFilterDetails, dynamicFilterRequest, saveFilterFormRequest);
		setOtherFilters(fetchFilterDetails, dynamicFilterRequest, saveFilterFormRequest);
		setExtraFilters(fetchFilterDetails, dynamicFilterRequest, saveFilterFormRequest);
		if(!saveFilterRepository.saveFilterDetils(saveFilterFormRequest)) {
			enterpriseSearchDataEst.setErrorMsg(EstimationConstant.EXCEPTION_OCCURRED_SAVE_FILTER);
		}
		
		setSearchTextFilter(fetchFilterDetails, dynamicFilterRequest);
		Long totalCount;
		try {
			totalCount = estimationDynamicFiltersService.getTotalCountOfTextSearch(dynamicFilterRequest);

			if(totalCount>0) {
				int fromIndex = fetchFilterDetails.getFromIndex();
				int size = fetchFilterDetails.getIndexSize();
				lArrayOfEstimationData = estimationDynamicFiltersService.getPaginatedEstimationData(dynamicFilterRequest, fromIndex , size);
			}
			enterpriseSearchDataEst.setTotalCount(totalCount);
			
		} catch (UnknownHostException e) {
			enterpriseSearchDataEst.setErrorMsg(EstimationConstant.EXCEPTION_OCCURRED);
		}
		enterpriseSearchDataEst.setlArrayOfEstimationData(lArrayOfEstimationData);
		return enterpriseSearchDataEst;
	}
	
	private void setSearchTextFilter(FetchFilterDetails filterDetails, DynamicFilterRequest dynamicFilterRequest){
		
		if(filterDetails.getSearchText()!=null && !filterDetails.getSearchText().isEmpty()){
			Filter filter = new Filter();
			filter.setOperation(EstimationConstant.FILTER_SEARCH_TEXT_OPERATION);
			filter.setCondition(EstimationConstant.FILTER_AND_CONDITION);
			filter.setValue(filterDetails.getSearchText());
			dynamicFilterRequest.getFilter().add(filter);
		}else{
			fetchProfileDataService.fetchProfileData(filterDetails.getSsoId(),dynamicFilterRequest);
		}
	}
	private void setMultiFilters(FetchFilterDetails filterDetails, DynamicFilterRequest dynamicFilterRequest, SaveFilterFormRequest saveFilterFormRequest){
		for(MultiFilters multiFilters : filterDetails.getFilterList())
		{
			Filter filter = new Filter();
			filter.setOperation(EstimationConstant.FILTER_MULTI_FILTER_OPERATION);
			filter.setCondition(EstimationConstant.FILTER_AND_CONDITION);
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
				filter.setCondition(EstimationConstant.FILTER_AND_CONDITION);
				filter.setElasticDbField(filterForm.getElasticDbColumn());
				if (filterForm.getRangeDown() != null && filterForm.getRangeUp() != null) {
					filter.setOperation(EstimationConstant.FILTER_RANGE_OPERATION);
					filter.setRangeLt(filterForm.getRangeUp());
					filter.setRangeGt(filterForm.getRangeDown());
					filter.setIncludeLower(true);
					filter.setIncludeUpper(true);
				} else if (filterForm.getValue() != null && !filterForm.getValue().trim().isEmpty()) {
					filter.setOperation(EstimationConstant.FILTER_EQUALS_OPERATION);
					filter.setValue(filterForm.getValue());
				}

				if (filter.getOperation() != null)
					dynamicFilterRequest.getFilter().add(filter);

				SaveFilterForm saveFilterForm = new SaveFilterForm();
				saveFilterForm.setSsoId(filterDetails.getSsoId());
				saveFilterForm.setFiltergroupId(otherFilters.getFilterCd());
				saveFilterForm.setFilterId(filterForm.getLabelCd());
				saveFilterForm.setRangeup(filterForm.getRangeUp() != null ? filterForm.getRangeUp() : null);
				saveFilterForm
						.setRangedown(filterForm.getRangeDown() != null ? filterForm.getRangeDown() : null);
				saveFilterForm.setFiltervalue(filterForm.getValue());
				saveFilterFormRequest.getFilterForm().add(saveFilterForm);
			}

		}
	}
	
	private void setExtraFilters(FetchFilterDetails filterDetails, DynamicFilterRequest dynamicFilterRequest,
			SaveFilterFormRequest saveFilterFormRequest) {
		for(ExtraFilter extraFilter: filterDetails.getExtraFiltersObjList()) {
			Filter filter = new Filter();
			filter.setCondition(EstimationConstant.FILTER_AND_CONDITION);
			filter.setElasticDbField(extraFilter.getElasticDbColumn());
			if (extraFilter.getValue() != null && !extraFilter.getValue().trim().isEmpty()) {
				filter.setOperation(EstimationConstant.FILTER_EQUALS_OPERATION);
				filter.setValue(extraFilter.getValue());
			}
			
			if (filter.getOperation() != null)
				dynamicFilterRequest.getFilter().add(filter);
			
			SaveFilterForm saveFilterForm = new SaveFilterForm();
			saveFilterForm.setSsoId(filterDetails.getSsoId());
			saveFilterForm.setFiltergroupId(extraFilter.getFilterCd());
			saveFilterForm.setFilterId(extraFilter.getLabelCd());
			saveFilterForm.setFiltervalue(extraFilter.getValue());
			saveFilterFormRequest.getFilterForm().add(saveFilterForm);
		}
		
	}

}
