package com.tip.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.model.AppsNavigationDataInput;
import com.tip.model.AppsNavigationDataModel;
import com.tip.model.FetchDefaultProfileInput;
import com.tip.model.ModuleDataAccessInput;
import com.tip.model.UpdateProfileInput;
import com.tip.service.FetchDefaultProfileService;

@RestController
public class ProfileSettings {
	
	@Autowired
	FetchDefaultProfileService fetchDefaultProfileService;
	
	 @RequestMapping(value="/fetchDefaultProfileSettings",method = RequestMethod.POST)
	 @ResponseBody
	 public HashMap fetchDefaultProfile(@RequestBody  FetchDefaultProfileInput fetchDefault) {
		 
		 HashMap result=new HashMap();
		 
		 try {
		 result=fetchDefaultProfileService.getDefaultProfileSettings(fetchDefault);
		 }catch(Exception e) {
			 System.out.println("exception occured while fetching default profile settings");
			 e.printStackTrace();
		 }
		 
		 return result;
		 
	 }
	 
	 
	 
	 @RequestMapping(value="/getModuleAccessData",method = RequestMethod.POST)
	 @ResponseBody
	 public HashMap getModuleAccessData(@RequestBody  ModuleDataAccessInput moduleDataInput) {
		 
		 HashMap result=new HashMap();
		 
		 try {
		 result=fetchDefaultProfileService.getModuleAccessData(moduleDataInput);
		 }catch(Exception e) {
			 System.out.println("exception occured while fetching default profile settings");
			 e.printStackTrace();
		 }
		 
		 return result;
		 
	 }
	 
	 
	 @RequestMapping(value="/updateProfile",method = RequestMethod.POST)
	 @ResponseBody
	 public HashMap updateProfile(@RequestBody  UpdateProfileInput updateProfileInput) {
		 
		 HashMap resultMap=new HashMap();
		 
		 try {
			 resultMap=fetchDefaultProfileService.updateProfile(updateProfileInput);
		 }catch(Exception e) {
			 System.out.println("exception occured while fetching default profile settings");
			 e.printStackTrace();
		 }
		 
		 return resultMap;
		 
	 }
	 
	 
	 
	 // Apps Navigation procedures start from here 
	 
	 @RequestMapping(value="/getAppsNavigationData",method = RequestMethod.POST)
	 @ResponseBody
	 public HashMap getAppsNavigationData(@RequestBody AppsNavigationDataModel appsNavigationDataModel) {
		 
		 HashMap result=new HashMap();
		 
		 try {
		 result=fetchDefaultProfileService.getAppsNavigationData(appsNavigationDataModel);
		 }catch(Exception e) {
			 System.out.println("exception occured while fetching default profile settings");
			 e.printStackTrace();
		 }
		 
		 return result;
		 
	 }
	 
	 
	 
	 
	 @RequestMapping(value="/updateAppsNavigationData",method = RequestMethod.POST)
	 @ResponseBody
	 public HashMap updateAppsNavigationData(@RequestBody AppsNavigationDataInput appsNavigationInput) {
		 
		 HashMap result=new HashMap();
		 
		 try {
		 result=fetchDefaultProfileService.updateAppsNavigationData(appsNavigationInput);
		 }catch(Exception e) {
			 System.out.println("exception occured while fetching default profile settings");
			 e.printStackTrace();
		 }
		 
		 return result;
		 
	 }
	 
	 
	 
	 @RequestMapping(value="/insertAppsNavigationData",method = RequestMethod.POST)
	 @ResponseBody
	 public HashMap insertAppsNavigationData(@RequestBody AppsNavigationDataInput appsNavigationInput) {
		 
		 HashMap result=new HashMap();
		 
		 try {
		 result=fetchDefaultProfileService.insertAppsNavigationData(appsNavigationInput);
		 }catch(Exception e) {
			 System.out.println("exception occured while fetching default profile settings");
			 e.printStackTrace();
		 }
		 
		 return result;
		 
	 }
	 
	 
	 
	 
	 // Apps Navigation methodes end here
	 
	 
}
