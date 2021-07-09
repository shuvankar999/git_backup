package com.tip.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.model.AppsNavigationDataInput;
import com.tip.model.AppsNavigationDataModel;
import com.tip.model.FetchDefaultProfileInput;
import com.tip.model.ModuleDataAccessInput;
import com.tip.model.UpdateProfileInput;
import com.tip.repository.FetchDefaultProfileRepository;
import com.tip.service.FetchDefaultProfileService;

@Service
@Transactional
public class FetchDefaultProfileServiceImpl implements FetchDefaultProfileService{
	
	
	@Autowired
	FetchDefaultProfileRepository fetchDefaultProfileRepository;
	
	public HashMap getDefaultProfileSettings(FetchDefaultProfileInput fetchDefault) {
		
		HashMap result=new HashMap();
		
		try {
			
			result=fetchDefaultProfileRepository.getDefaultProfileSettings(fetchDefault);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	

	@Override
	public HashMap getModuleAccessData(ModuleDataAccessInput moduleDataInput) {
HashMap result=new HashMap();
		
		try {
			
			result=fetchDefaultProfileRepository.getModuleAccessData(moduleDataInput);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public HashMap updateProfile(UpdateProfileInput updateProfileInput) {
HashMap result=new HashMap();
		
		try {
			
			result=fetchDefaultProfileRepository.updateProfile(updateProfileInput);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	
public HashMap getAppsNavigationData(AppsNavigationDataModel appsNavigationDataModel) {
		
		HashMap result=new HashMap();
		
		try {
			
			result=fetchDefaultProfileRepository.getAppsNavigationData(appsNavigationDataModel);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	

	@Override
public HashMap updateAppsNavigationData(AppsNavigationDataInput appsNavigationInput) {
HashMap result=new HashMap();
	
	try {
		
		result=fetchDefaultProfileRepository.updateAppsNavigationData(appsNavigationInput);
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	return result;
}
	
	@Override
	public HashMap insertAppsNavigationData(AppsNavigationDataInput appsNavigationInput) {
	HashMap result=new HashMap();
		
		try {
			
			result=fetchDefaultProfileRepository.insertAppsNavigationData(appsNavigationInput);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	



	
}
