package com.tip.service;

import java.util.HashMap;

import com.tip.model.AppsNavigationDataInput;
import com.tip.model.AppsNavigationDataModel;
import com.tip.model.FetchDefaultProfileInput;
import com.tip.model.ModuleDataAccessInput;
import com.tip.model.UpdateProfileInput;

public interface FetchDefaultProfileService {

	HashMap getDefaultProfileSettings(FetchDefaultProfileInput fetchDefault);

	HashMap getModuleAccessData(ModuleDataAccessInput moduleDataInput);

	HashMap updateProfile(UpdateProfileInput updateProfileInput);
	
	HashMap updateAppsNavigationData(AppsNavigationDataInput appsNavigationInput);
	
	HashMap insertAppsNavigationData(AppsNavigationDataInput appsNavigationInput); 	
	
	
	
	HashMap getAppsNavigationData(AppsNavigationDataModel appsNavigationDataModel); 

}
