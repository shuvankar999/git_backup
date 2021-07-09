package com.tip.inspection.repository;

import com.tip.inspection.model.UserProfileObject;
import com.tip.inspection.model.UserProfileStatusObject;

public interface UserProfileRepository {

	public UserProfileStatusObject saveUserProfile(UserProfileObject userProfileObject);
	public String fetchCIAprivateKey(String ssoId);
}
