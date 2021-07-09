package com.tip.inspection.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.inspection.model.UserProfileRequest;
import com.tip.inspection.model.UserProfileResponse;
import com.tip.inspection.service.UserProfileService;

@RestController
@RequestMapping("/service/cia-crud-service/1.0/")
public class UserProfileController {
	@Autowired
	UserProfileService userProfileService;

	@RequestMapping(value = "/saveUserProfile", method = RequestMethod.POST)
	@ResponseBody
	public UserProfileResponse saveUserProfile(@RequestBody @Valid UserProfileRequest userProfileRequest) {
		return userProfileService.saveUserProfile(userProfileRequest);
	}
}
