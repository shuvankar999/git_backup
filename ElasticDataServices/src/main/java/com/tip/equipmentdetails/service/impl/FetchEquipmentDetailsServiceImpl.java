package com.tip.equipmentdetails.service.impl;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.asset.controller.helper.AssetControllerHelper;
import com.tip.asset.model.DynamicPosVal;
import com.tip.asset.service.DynamicPosValService;
import com.tip.equipmentdetails.model.DynamicModule;
import com.tip.equipmentdetails.model.FetchEquipDetailsRequest;
import com.tip.equipmentdetails.model.FetchEquipDetailsResponse;
import com.tip.equipmentdetails.model.Form;
import com.tip.equipmentdetails.model.Modules;
import com.tip.equipmentdetails.model.Option;
import com.tip.equipmentdetails.model.StaticModule;
import com.tip.equipmentdetails.model.Status;
import com.tip.equipmentdetails.repository.FetchEquipmentDetailsRepository;
import com.tip.equipmentdetails.service.FetchEquipmentDetailsService;

@Service
public class FetchEquipmentDetailsServiceImpl implements FetchEquipmentDetailsService{
	
	 public static final Logger logger = LoggerFactory.getLogger(FetchEquipmentDetailsService.class);

	@Autowired
	FetchEquipmentDetailsRepository fetchEquipmentDetailsRepository;
	
	@Autowired
    AssetControllerHelper assetControllerHelper;
	
	@Autowired
    DynamicPosValService dynamicPosValService;
	
	public static final String STATIC_DATE = "Item_Date_Value";
	public static final String DYNAMIC_DATE = "Specitm_Date_Value";
	public static final String STATIC_MODULE = "Group_Short_Desc";
	public static final String DYNAMIC_MODULE = "Specgr_Shortn";
	public static final String VAL_TYPE = "Val_Type";
	Set<String> staticModuleNames;
	Set<String> dynamicModuleNames;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public FetchEquipDetailsResponse getEquipmentDetils(FetchEquipDetailsRequest fetchEquipDetailsRequest, boolean refreshFlag) {
		FetchEquipDetailsResponse fetchEquipDetailsResponse = new FetchEquipDetailsResponse();
		Modules modules = new Modules();
		staticModuleNames = new HashSet<>();
		dynamicModuleNames = new HashSet<>();
		
		Map<String, Object> equipReturnMap = fetchEquipmentDetailsRepository.getEquipmentDetils(fetchEquipDetailsRequest,refreshFlag);
		List<Object> staticTabList = (List<Object>) equipReturnMap.get("StaticTableData");
		Iterator staticTabIt = staticTabList.iterator();
		while(staticTabIt.hasNext()){
			Map<String, Object> staticTabMap = (Map<String, Object>) staticTabIt.next();
			Form form = setStaticFormData(staticTabMap);
			addStaticModule(modules, staticTabMap, form);
		}
		
		List<Map<String, Object>> dynamicTabList = (List<Map<String, Object>>) equipReturnMap.get("DynamicTableData");
		List<Map<String, String>> fielsList = new ArrayList<>();
		for(Map<String, Object> obj : dynamicTabList)
		{
			DynamicPosVal dynamicPosVal = new DynamicPosVal();
			dynamicPosVal.setSpecgrId((Integer)obj.get("Specgr_Id"));
			dynamicPosVal.setSpecitmSeq((Integer)obj.get("Specitm_Seq"));
			Map<String, String> fields = assetControllerHelper.setFilterForPosVals(dynamicPosVal);
			fielsList.add(fields);
		}
		Map<String,List<String>> mapOfDynamicPosVal = null;
		try {			
			mapOfDynamicPosVal = dynamicPosValService.getAllDynamicPosVals(fielsList);
		} catch (UnknownHostException e) {
			logger.error("Error Encountered while fetching Equipment Details : " + e.getMessage(), e);
		}
		
		for(Map<String, Object> dynamicTabMap : dynamicTabList)
		{
			Form form = setDynamicFormData(dynamicTabMap, mapOfDynamicPosVal);
			addDynamicModule(modules, dynamicTabMap, form);
		}		
		
		fetchEquipDetailsResponse.setModulesDet(modules);
		List<Object> statusList = (List<Object>) equipReturnMap.get("Status");
		Iterator statusListIt = statusList.iterator();
		while(statusListIt.hasNext()){
			Map<String, Object> statusListMap = (Map<String, Object>) statusListIt.next();	
			Status status = new Status();
			
			status.setUnitNr((Integer)statusListMap.get("Unit_Nr"));
			status.setErrorCd((String)statusListMap.get("Error_Cd"));
			status.setStatusId((Integer)statusListMap.get("Status_Id"));
			
			fetchEquipDetailsResponse.getStatusList().add(status);
		}
		
		return fetchEquipDetailsResponse;
	}
	
	public Form setStaticFormData(Map<String, Object> staticTabMap)
	{
		Form form = new Form();
		form.setLabel((String)staticTabMap.get("Item_Desc"));
		form.setDbColumnName((String) staticTabMap.get("Item_Short_Desc"));
		if("B".equalsIgnoreCase((String)staticTabMap.get(STATIC_DATE))) {
			form.setMaxDate(new Date().toString());
			form.setMinDate("");
		} else if("A".equalsIgnoreCase((String)staticTabMap.get(STATIC_DATE))) {
			form.setMinDate(new Date().toString());
			form.setMaxDate("");
		} else if("D".equalsIgnoreCase((String)staticTabMap.get(STATIC_DATE)) || "S".equalsIgnoreCase((String)staticTabMap.get(STATIC_DATE))) {
			form.setMinDate("");
			form.setMaxDate("");
		}
		
		if("Y".equalsIgnoreCase((String)staticTabMap.get("Is_Enabled"))) {
		form.setDisabled(false);
		} else {
			form.setDisabled(true);
		}
		
		if((String)staticTabMap.get(VAL_TYPE) != null)
		{
			form.setType("select");
	       	form.setValuetype((String)staticTabMap.get(VAL_TYPE));
		}
		else
		{
			setStaticType(form, (String)staticTabMap.get("Item_Val_Type"));
	       	form.setValuetype((String)staticTabMap.get(VAL_TYPE));
		}
		
		form.setItemLimit((String)staticTabMap.get("Item_Limit"));
		form.setItemReq((String)staticTabMap.get("Item_Req"));
		form.setTextPosMin((Integer)staticTabMap.get("Item_Pos_Min"));
		form.setTextPosMax((Integer)staticTabMap.get("Item_Pos_Max"));
		form.setValRangeMin((Integer)staticTabMap.get("Item_Val_Min"));
		form.setValRangeMax((Integer)staticTabMap.get("Item_Val_Max"));
		form.setValue((String)staticTabMap.get("Unitsp_Value"));
		return form;
	}
	
	public void createNewStaticModule(Modules modules,Map<String, Object> staticTabMap,Form form){
		StaticModule staticModule = new StaticModule();
		staticModule.setModuleName((String)staticTabMap.get(STATIC_MODULE));
		staticModule.getForms().add(form);
		modules.getStaticModuleList().add(staticModule);
		staticModuleNames.add(staticModule.getModuleName());
	}
	
	public Form setDynamicFormData(Map<String, Object> dynamicTabMap, Map<String,List<String>> mapOfDynamicPosVal)
	{
		Form form = new Form();
		form.setLabel((String)dynamicTabMap.get("Specitm_Descr"));		
		if("B".equalsIgnoreCase((String)dynamicTabMap.get(DYNAMIC_DATE))) {
			form.setMaxDate(new Date().toString());
			form.setMinDate("");
		} else if("A".equalsIgnoreCase((String)dynamicTabMap.get(DYNAMIC_DATE))) {
			form.setMinDate(new Date().toString());
			form.setMaxDate("");
		} else if("D".equalsIgnoreCase((String)dynamicTabMap.get(DYNAMIC_DATE)) || "S".equalsIgnoreCase((String)dynamicTabMap.get(DYNAMIC_DATE))) {
			form.setMinDate("");
			form.setMaxDate("");
		}
		
		if("Y".equalsIgnoreCase((String)dynamicTabMap.get("Is_Enabled"))) {
		form.setDisabled(false);
		} else {
			form.setDisabled(true);
		}
		
		List<String> dynamicPosValLst = mapOfDynamicPosVal.get((Integer)dynamicTabMap.get("Specgr_Id") + "@@@" + (Integer)dynamicTabMap.get("Specitm_Seq"));
		if(dynamicPosValLst != null && !dynamicPosValLst.isEmpty())
		{
			form.setType("select");
			form.setValuetype("self");
			for(String posVal : dynamicPosValLst){
				Option option = new Option();
				option.setName(posVal);
				option.setValue(posVal);
				form.getOptions().add(option);
			}
		}
		else
		{
			setStaticType(form, (String)dynamicTabMap.get("Specitm_Val_Type"));
			form.setValuetype("");
		}
		form.setSpecitmSeq((Integer)dynamicTabMap.get("Specitm_Seq"));
		form.setItemLimit((String)dynamicTabMap.get("Specitm_Limit"));
		form.setItemReq((String)dynamicTabMap.get("Specitm_Req"));
		form.setTextPosMin((Integer)dynamicTabMap.get("Specitm_Pos_Min"));
		form.setTextPosMax((Integer)dynamicTabMap.get("Specitm_Pos_Max"));
		form.setValRangeMin((Integer)dynamicTabMap.get("Specitm_Val_Min"));
		form.setValRangeMax((Integer)dynamicTabMap.get("Specitm_Val_Max"));
		form.setValue((String)dynamicTabMap.get("Unitsp_Value"));
		return form;
	}
	
	public void setStaticType(Form form, String type){
		if("T".equalsIgnoreCase(type))
   		{
   			form.setType("text");
   		} else if("N".equalsIgnoreCase(type))
   		{
   			form.setType("numeric");
   		} else if("B".equalsIgnoreCase(type))
   		{
   			form.setType("yes/no");
   		} else if("X".equalsIgnoreCase(type))
   		{
   			form.setType("none");
   		} else if("D".equalsIgnoreCase(type))
   		{
   			form.setType("date");
   		} else if("A".equalsIgnoreCase(type))
   		{
   			form.setType("textarea");
   		}
	}
	
	public void createNewDynamicModule(Modules modules,Map<String, Object> dynamicTabMap,Form form){
		DynamicModule dynamicModule = new DynamicModule();
		dynamicModule.setModuleName((String)dynamicTabMap.get(DYNAMIC_MODULE));
		dynamicModule.getForms().add(form);
		dynamicModule.setSpecgrId((Integer)dynamicTabMap.get("Specgr_Id"));
		modules.getDynamicModuleList().add(dynamicModule);
		dynamicModuleNames.add(dynamicModule.getModuleName());
	}
	
	public void addStaticModule(Modules modules,Map<String, Object> staticTabMap,Form form){
		if(staticModuleNames.contains((String)staticTabMap.get(STATIC_MODULE))) {
			for(StaticModule module :  modules.getStaticModuleList())
			{
				if(module.getModuleName().equalsIgnoreCase((String)staticTabMap.get(STATIC_MODULE)))
				{
					module.getForms().add(form);
					break;
				}
			}
		}
		else {
			createNewStaticModule(modules, staticTabMap, form);
		}
	}
	
	public void addDynamicModule(Modules modules,Map<String, Object> dynamicTabMap,Form form){
		if(dynamicModuleNames.contains((String)dynamicTabMap.get(DYNAMIC_MODULE))) {
			for(DynamicModule module :  modules.getDynamicModuleList())
			{
				if(module.getModuleName().equalsIgnoreCase((String)dynamicTabMap.get(DYNAMIC_MODULE)))
				{
					module.getForms().add(form);
					break;
				}
			}			
		}
		else {
			createNewDynamicModule(modules, dynamicTabMap, form);
		}
	}
}
