package com.tip.equipmentdetails.service;

import java.net.UnknownHostException;

import com.tip.elasticsearch.model.EnterpriseSearchData;
import com.tip.equipmentdetails.model.FetchFilterDetails;
import com.tip.equipmentdetails.model.SaveProfileSettingsRequest;
import com.tip.equipmentdetails.model.SaveProfileSettingsResponse;

public interface SaveAndProcessFilterService {

	public EnterpriseSearchData processFilterDetails(FetchFilterDetails filterDetails,boolean saveFlag) throws UnknownHostException;
	public boolean saveFilterFormDetils(FetchFilterDetails filterDetails);
	public boolean saveFilterDetils(FetchFilterDetails filterDetails) throws UnknownHostException;
	public SaveProfileSettingsResponse saveProfileSettings(SaveProfileSettingsRequest saveProfileSettingsRequest) throws UnknownHostException;
}