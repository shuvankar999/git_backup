package com.tip.repository;

import java.util.HashMap;

import com.tip.model.AppsNavigationData;
import com.tip.model.AppsNavigationDataInput;
import com.tip.model.AppsNavigationDataModel;
import com.tip.model.FetchDefaultProfileInput;
import com.tip.model.ModuleDataAccessInput;
import com.tip.model.UpdateProfileInput;

public interface FetchDefaultProfileRepository {
	
		
	public HashMap getDefaultProfileSettings(FetchDefaultProfileInput fetchDefault);

	public HashMap getModuleAccessData(ModuleDataAccessInput moduleDataInput);

	public HashMap updateProfile(UpdateProfileInput updateProfileInput);
	
	public HashMap updateAppsNavigationData(AppsNavigationDataInput appsNavigationInput); 
	
	
	public HashMap insertAppsNavigationData(AppsNavigationDataInput appsNavigationInput); 
	
	
	public HashMap getAppsNavigationData(AppsNavigationDataModel appsNavigationDataModel); 

}
