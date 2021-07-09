package com.tip.equipmentdetails.service.impl;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.asset.model.ArrayOfAssetData;
import com.tip.asset.model.AssetSearchResponse;
import com.tip.asset.service.AssetDataForDynamicFiltersService;
import com.tip.asset.util.AssetConstants;
import com.tip.elasticsearch.model.EnterpriseSearchData;
import com.tip.equipmentdetails.model.DynamicFilterAssetSpec;
import com.tip.equipmentdetails.model.DynamicFilterRequest;
import com.tip.equipmentdetails.model.ExtraFilter;
import com.tip.equipmentdetails.model.FetchFilterDetails;
import com.tip.equipmentdetails.model.Filter;
import com.tip.equipmentdetails.model.FilterAssetSpec;
import com.tip.equipmentdetails.model.FilterForm;
import com.tip.equipmentdetails.model.FilterOption;
import com.tip.equipmentdetails.model.ListOfUnit;
import com.tip.equipmentdetails.model.MultiFilters;
import com.tip.equipmentdetails.model.OtherFilters;
import com.tip.equipmentdetails.model.SaveFilterForm;
import com.tip.equipmentdetails.model.SaveFilterFormRequest;
import com.tip.equipmentdetails.model.SaveProfileSettingsRequest;
import com.tip.equipmentdetails.model.SaveProfileSettingsResponse;
import com.tip.equipmentdetails.repository.SaveFieldLabelRepository;
import com.tip.equipmentdetails.repository.SaveFilterRepository;
import com.tip.equipmentdetails.service.FetchProfileDataService;
import com.tip.equipmentdetails.service.SaveAndProcessFilterService;

@Service
public class SaveAndProcessFilterServiceImpl implements SaveAndProcessFilterService{

	@Autowired
	SaveFilterRepository saveFilterRepository;
	
	@Autowired
	SaveFieldLabelRepository saveFieldLabelRepository;
	
	@Autowired
	AssetDataForDynamicFiltersService assetDataForDynamicFiltersService;
	
	
	@Autowired
	FetchProfileDataService fetchProfileDataService;
	
	@Override
	public EnterpriseSearchData processFilterDetails(FetchFilterDetails filterDetails,boolean saveFlag) throws UnknownHostException {
		EnterpriseSearchData enterpriseSearchData = new EnterpriseSearchData();		
		DynamicFilterRequest dynamicFilterRequest = new DynamicFilterRequest();
		DynamicFilterAssetSpec dynamicFilterAssetSpec = new DynamicFilterAssetSpec();
		SaveFilterFormRequest saveFilterFormRequest = new SaveFilterFormRequest();
		ListOfUnit listOfUnit = new ListOfUnit();
		
		//fetchProfileDataService.fetchProfileData(filterDetails.getSsoId(),dynamicFilterRequest);
		setMultiFilters(filterDetails, dynamicFilterRequest, saveFilterFormRequest);
		setOtherFilters(filterDetails, dynamicFilterRequest, dynamicFilterAssetSpec, saveFilterFormRequest);
		setExtraFilters(filterDetails, dynamicFilterRequest, saveFilterFormRequest);

		if(saveFlag){
			saveFilterRepository.saveFilterDetils(saveFilterFormRequest);
		}

		setSearchTextFilter(filterDetails, dynamicFilterRequest);
		
		ArrayOfAssetData arrayOfAssetIndexData = new ArrayOfAssetData();
		Map<Integer, AssetSearchResponse> assetDataMap;
		if (filterDetails.getIndexSize() != null && filterDetails.getIndexSize() > 0)
			assetDataMap = assetDataForDynamicFiltersService.dynamicFilterAsset(dynamicFilterRequest,
					dynamicFilterAssetSpec, listOfUnit, arrayOfAssetIndexData, filterDetails.getFromIndex(),
					filterDetails.getIndexSize());
		else
			assetDataMap = assetDataForDynamicFiltersService.dynamicFilterAsset(dynamicFilterRequest,
					dynamicFilterAssetSpec, listOfUnit, arrayOfAssetIndexData);
		enterpriseSearchData.setAssetCount(arrayOfAssetIndexData.getCount());
		enterpriseSearchData.setlArrayOfAssetData(setUnitNr(dynamicFilterRequest, dynamicFilterAssetSpec, assetDataMap, listOfUnit, arrayOfAssetIndexData));
		return enterpriseSearchData;
	}

	private void setSearchTextFilter(FetchFilterDetails filterDetails, DynamicFilterRequest dynamicFilterRequest){
		
		if(filterDetails.getSearchText()!=null && !filterDetails.getSearchText().isEmpty()){
			Filter filter = new Filter();
			filter.setOperation(AssetConstants.FILTER_SEARCH_TEXT_OPERATION);
			filter.setCondition(AssetConstants.FILTER_AND_CONDITION);
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
			filter.setOperation(AssetConstants.FILTER_MULTI_FILTER_OPERATION);
			filter.setCondition(AssetConstants.FILTER_AND_CONDITION);
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
				saveFilterForm.setIsAdvanced(filterDetails.getIsAdvanced());
				saveFilterFormRequest.getFilterForm().add(saveFilterForm);
			}
			
			if(filter.getElasticDbField().equalsIgnoreCase("unit_operational_status_desc"))
				dynamicFilterRequest.getFilter().add(filter);
			else if(!filter.getValueList().isEmpty())
				dynamicFilterRequest.getFilter().add(filter);
			
		}
	}
	
	private void setOtherFilters(FetchFilterDetails filterDetails, DynamicFilterRequest dynamicFilterRequest, DynamicFilterAssetSpec dynamicFilterAssetSpec,SaveFilterFormRequest saveFilterFormRequest) throws UnknownHostException{
		for(OtherFilters otherFilters : filterDetails.getFilterForms())
		{
			if (checkIntegerLength(otherFilters.getFilterCd()) < 3) {
				for (FilterForm filterForm : otherFilters.getFilterForm()) {
					Filter filter = new Filter();
					filter.setCondition(AssetConstants.FILTER_AND_CONDITION);
					filter.setElasticDbField(filterForm.getElasticDbColumn());
					if (filterForm.getRangeDown() != null && filterForm.getRangeUp() != null) {
						filter.setOperation(AssetConstants.FILTER_RANGE_OPERATION);
						filter.setRangeLt(filterForm.getRangeUp());
						filter.setRangeGt(filterForm.getRangeDown());
						filter.setIncludeLower(true);
						filter.setIncludeUpper(true);
					} else if (filterForm.getValue() != null && !filterForm.getValue().trim().isEmpty()) {
						filter.setOperation(AssetConstants.FILTER_LIKE_OPERATION);
						filter.setValue(filterForm.getValue());
					}

					if (filter.getOperation() != null)
						dynamicFilterRequest.getFilter().add(filter);

					SaveFilterForm saveFilterForm = new SaveFilterForm();
					saveFilterForm.setSsoId(filterDetails.getSsoId());
					saveFilterForm.setFiltergroupId(otherFilters.getFilterCd());
					saveFilterForm.setFilterId(filterForm.getLabelCd());
					saveFilterForm
							.setRangeup(filterForm.getRangeUp() != null ? filterForm.getRangeUp().toString() : null);
					saveFilterForm.setRangedown(
							filterForm.getRangeDown() != null ? filterForm.getRangeDown().toString() : null);
					saveFilterForm.setFiltervalue(filterForm.getValue());
					saveFilterForm.setIsAdvanced(filterDetails.getIsAdvanced());
					saveFilterFormRequest.getFilterForm().add(saveFilterForm);
				}
			}else if(checkIntegerLength(otherFilters.getFilterCd()) == 3){
				for (FilterForm filterForm : otherFilters.getFilterForm()) {
					
					setAssetSpecFilter(dynamicFilterAssetSpec,otherFilters,filterForm);
					
					SaveFilterForm saveFilterForm = new SaveFilterForm();
					saveFilterForm.setSsoId(filterDetails.getSsoId());
					saveFilterForm.setFiltergroupId(otherFilters.getFilterCd());
					saveFilterForm.setFilterId(filterForm.getLabelCd());
					saveFilterForm
							.setRangeup(filterForm.getRangeUp() != null ? filterForm.getRangeUp() : null);
					saveFilterForm.setRangedown(
							filterForm.getRangeDown() != null ? filterForm.getRangeDown() : null);
					saveFilterForm.setFiltervalue(filterForm.getValue());
					saveFilterForm.setIsAdvanced(filterDetails.getIsAdvanced());
					saveFilterFormRequest.getFilterForm().add(saveFilterForm);
				}
			}
		}
	}
	
	private void setExtraFilters(FetchFilterDetails filterDetails, DynamicFilterRequest dynamicFilterRequest,
			SaveFilterFormRequest saveFilterFormRequest) {
		for(ExtraFilter extraFilter: filterDetails.getExtraFiltersObjList()) {
			Filter filter = new Filter();
			filter.setCondition(AssetConstants.FILTER_AND_CONDITION);
			filter.setElasticDbField(extraFilter.getElasticDbColumn());
			if (extraFilter.getValue() != null && !extraFilter.getValue().trim().isEmpty()) {
				filter.setOperation(AssetConstants.FILTER_LIKE_OPERATION);
				filter.setValue(extraFilter.getValue());
			}
			
			if (filter.getOperation() != null)
				dynamicFilterRequest.getFilter().add(filter);
			
			SaveFilterForm saveFilterForm = new SaveFilterForm();
			saveFilterForm.setSsoId(filterDetails.getSsoId());
			saveFilterForm.setFiltergroupId(extraFilter.getFilterCd());
			saveFilterForm.setFilterId(extraFilter.getLabelCd());
			saveFilterForm.setFiltervalue(extraFilter.getValue());
			saveFilterForm.setIsAdvanced(filterDetails.getIsAdvanced());
			saveFilterFormRequest.getFilterForm().add(saveFilterForm);
		}
		
	}
	private ArrayOfAssetData setUnitNr(DynamicFilterRequest dynamicFilterRequest, DynamicFilterAssetSpec dynamicFilterAssetSpec, Map<Integer, AssetSearchResponse> assetDataMap, ListOfUnit listOfUnit, ArrayOfAssetData arrayOfAssetIndexData) throws UnknownHostException{
		
		ArrayOfAssetData arrayOfAssetDataResponse = new ArrayOfAssetData();
		if(dynamicFilterAssetSpec.getFilter()!=null && !dynamicFilterAssetSpec.getFilter().isEmpty() && listOfUnit.getCount()>0){
			List<AssetSearchResponse> listOfAssetSearchResponse = assetDataForDynamicFiltersService.dynamicFilterAssetSpecs(dynamicFilterRequest, dynamicFilterAssetSpec, listOfUnit, assetDataMap);
			arrayOfAssetDataResponse.setAssetDataItem(listOfAssetSearchResponse);
			arrayOfAssetDataResponse.setCount(Long.valueOf(listOfAssetSearchResponse.size()));
		}else{
			arrayOfAssetDataResponse.setAssetDataItem(arrayOfAssetIndexData.getAssetDataItem());
			arrayOfAssetDataResponse.setCount(Long.valueOf(arrayOfAssetIndexData.getAssetDataItem().size()));
		}
		
		return arrayOfAssetDataResponse;
	}
	
	private void setAssetSpecFilter(DynamicFilterAssetSpec dynamicFilterAssetSpec, OtherFilters otherFilters, FilterForm filterForm) throws UnknownHostException{
		
		FilterAssetSpec filterAssetSpec = new FilterAssetSpec();
		filterAssetSpec.setCondition(AssetConstants.FILTER_AND_CONDITION);
		filterAssetSpec.setFilterCd(otherFilters.getFilterCd());
		filterAssetSpec.setLabelCd(Integer.parseInt(filterForm.getLabelCd()));
		if (filterForm.getRangeDown() != null && filterForm.getRangeUp() != null && !filterForm.getRangeDown().isEmpty() && !filterForm.getRangeUp().isEmpty()) {
			filterAssetSpec.setOperation(AssetConstants.FILTER_RANGE_OPERATION_SPECS);
			filterAssetSpec.setRangeLt(filterForm.getRangeUp().toString());
			filterAssetSpec.setRangeGt(filterForm.getRangeDown().toString());
			filterAssetSpec.setIncludeLower(true);
			filterAssetSpec.setIncludeUpper(true);
		} else if (filterForm.getValue() != null && !filterForm.getValue().trim().isEmpty()) {
			filterAssetSpec.setOperation(AssetConstants.FILTER_LIKE_OPERATION_SPECS);
			filterAssetSpec.setValue(filterForm.getValue());
		}

		if (filterAssetSpec.getOperation() != null)
			dynamicFilterAssetSpec.getFilter().add(filterAssetSpec);
		
	}
	
	
	private int checkIntegerLength(Integer i){
		return Integer.toString(i).length();
	}
	
	
	@Override
	public boolean saveFilterFormDetils(FetchFilterDetails filterDetails) {
		SaveFilterFormRequest saveFilterFormRequest = new SaveFilterFormRequest();		
		for(OtherFilters otherFilters : filterDetails.getFilterForms())
		{
			for(FilterForm filterForm : otherFilters.getFilterForm()){
				SaveFilterForm saveFilterForm = new SaveFilterForm();
				saveFilterForm.setSsoId(filterDetails.getSsoId());
				saveFilterForm.setFiltergroupId(otherFilters.getFilterCd());
				saveFilterForm.setFilterId(filterForm.getLabelCd());
				saveFilterForm.setRangeup(filterForm.getRangeUp() != null ? filterForm.getRangeUp() : null);
				saveFilterForm.setRangedown(filterForm.getRangeDown() != null ? filterForm.getRangeDown() : null);
				saveFilterForm.setFiltervalue(filterForm.getValue());
				saveFilterFormRequest.getFilterForm().add(saveFilterForm);
			}
		}
		return saveFilterRepository.saveFilterDetils(saveFilterFormRequest);
	}
	
	@Override
	public boolean saveFilterDetils(FetchFilterDetails filterDetails) throws UnknownHostException {
		DynamicFilterRequest dynamicFilterRequest = new DynamicFilterRequest();
		DynamicFilterAssetSpec dynamicFilterAssetSpec = new DynamicFilterAssetSpec();
		SaveFilterFormRequest saveFilterFormRequest = new SaveFilterFormRequest();
		
		setMultiFilters(filterDetails, dynamicFilterRequest, saveFilterFormRequest);
		setOtherFilters(filterDetails, dynamicFilterRequest, dynamicFilterAssetSpec, saveFilterFormRequest);
		setExtraFilters(filterDetails, dynamicFilterRequest, saveFilterFormRequest);

		return saveFilterRepository.saveFilterDetils(saveFilterFormRequest);
	}
	
	@Override
	public SaveProfileSettingsResponse saveProfileSettings(SaveProfileSettingsRequest saveProfileSettingsRequest) throws UnknownHostException {
		SaveProfileSettingsResponse saveProfileSettingsResponse = new SaveProfileSettingsResponse();
		boolean flag1 = false;
		if(saveProfileSettingsRequest.getFetchFilterDetails().getSsoId() != null) {
			flag1 = saveFilterDetils(saveProfileSettingsRequest.getFetchFilterDetails());
		}
		if(flag1)
		{
			boolean flag2 = true;
			if(saveProfileSettingsRequest.getFieldLabelLst() != null && !saveProfileSettingsRequest.getFieldLabelLst().isEmpty()){
				flag2 = saveFieldLabelRepository.saveFieldLabelDetils(saveProfileSettingsRequest);
			}
			if(flag2) {
				saveProfileSettingsResponse.setErrorCd("SUCCESS");
			} else {
				saveProfileSettingsResponse.setErrorCd("FAILURE");
			}
		} else{
			saveProfileSettingsResponse.setErrorCd("FAILURE");
		}
		return saveProfileSettingsResponse;
	}
}
