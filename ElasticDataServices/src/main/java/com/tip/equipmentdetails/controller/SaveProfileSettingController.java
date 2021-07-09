package com.tip.equipmentdetails.controller;

import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.equipmentdetails.model.SaveProfileSettingsRequest;
import com.tip.equipmentdetails.model.SaveProfileSettingsResponse;
import com.tip.equipmentdetails.service.SaveAndProcessFilterService;

@RestController
@RequestMapping("/service/elastic-data-service/1.0/")
public class SaveProfileSettingController {
	
	public static final Logger logger = LoggerFactory.getLogger(SaveProfileSettingController.class);
	
	@Autowired
	SaveAndProcessFilterService saveAndProcessFilterService;
	
	@RequestMapping(value = "saveProfileSettings", method = RequestMethod.POST)
	@ResponseBody
	public SaveProfileSettingsResponse saveProfileSettings(@RequestBody SaveProfileSettingsRequest saveProfileSettingsRequest) {
		SaveProfileSettingsResponse saveProfileSettingsResponse = new SaveProfileSettingsResponse();
		try {
			saveProfileSettingsResponse = saveAndProcessFilterService.saveProfileSettings(saveProfileSettingsRequest);
		} catch (UnknownHostException e) {
			saveProfileSettingsResponse.setErrorCd("FAILURE");
			logger.error("Exception Occured in Save Profile Settings", e);
		}
		return saveProfileSettingsResponse;
	}
}