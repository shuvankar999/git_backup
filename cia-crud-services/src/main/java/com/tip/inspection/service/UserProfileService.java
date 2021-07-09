package com.tip.inspection.service;

import com.tip.inspection.model.UserProfileRequest;
import com.tip.inspection.model.UserProfileResponse;
@FunctionalInterface
public interface UserProfileService {

	public UserProfileResponse saveUserProfile(UserProfileRequest userProfileRequest);

}
