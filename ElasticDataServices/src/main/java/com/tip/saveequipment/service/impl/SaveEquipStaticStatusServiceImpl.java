package com.tip.saveequipment.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.elastic.util.CommonUtil;
import com.tip.saveequipment.model.BaseResponse;
import com.tip.saveequipment.model.Form;
import com.tip.saveequipment.model.Modules;
import com.tip.saveequipment.model.SaveEquipDetailsRequest;
import com.tip.saveequipment.model.SaveEquipOtherRequest;
import com.tip.saveequipment.model.SaveEquipStaticRequest;
import com.tip.saveequipment.model.StaticModule;
import com.tip.saveequipment.repository.SaveEquipOtherRepository;
import com.tip.saveequipment.repository.SaveEquipStaticStatusRepository;
import com.tip.saveequipment.service.SaveEquipStaticStatusSerivce;

@Service
@Transactional
public class SaveEquipStaticStatusServiceImpl implements SaveEquipStaticStatusSerivce {

	@Autowired
	SaveEquipStaticStatusRepository saveEquipStaticStatusRepository;
	
	@Autowired
	SaveEquipOtherRepository saveEquipOtherRepository;
	
	SaveEquipStaticRequest saveEquipStaticRequest = new SaveEquipStaticRequest();
	
	SaveEquipOtherRequest saveEquipOtherRequest = new SaveEquipOtherRequest();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<BaseResponse> saveEquipmentStaticStatus(SaveEquipDetailsRequest saveEquipDetailsRequest, String appCd,
			String ssoId) {

		setSaveEquipStaticData(saveEquipDetailsRequest);
		Map<String, Object> resultMap =  saveEquipStaticStatusRepository.saveEquipmentStaticStatus(saveEquipStaticRequest, appCd, ssoId);
		Map<String, Object> resultMapOthers =  null;
		List<BaseResponse> saveEquipResponse = new ArrayList();
		List<Object> returnList = (List<Object>) resultMap.get("ResultSet");
		Iterator it = returnList.iterator();
		while(it.hasNext()){
			Map<String, Object> resultSetMap= (Map<String, Object>) it.next();
			BaseResponse baseResponse = new BaseResponse();
			baseResponse.setUnitNr((Integer) resultSetMap.get("Unit_Nr"));
			baseResponse.setErrorCd((String) resultSetMap.get("Error_Cd"));
			if(baseResponse.getErrorCd().equalsIgnoreCase("SUCCESS")){
				
				setSaveEquipOtherRequest(saveEquipDetailsRequest);
				resultMapOthers = saveEquipOtherRepository.saveEquipOther(saveEquipOtherRequest, appCd, ssoId);
			}else{
				saveEquipResponse.add(baseResponse);
			}
		}
		if(resultMapOthers !=null) {
			List<Object> returnListOthers = (List<Object>) resultMapOthers.get("ResultSet");
			Iterator itOthers = returnListOthers.iterator();
			while(itOthers.hasNext()){
				Map<String, Object> resultSetMap= (Map<String, Object>) itOthers.next();
				BaseResponse baseResponse = new BaseResponse();
				baseResponse.setUnitNr((Integer) resultSetMap.get("Unit_Nr"));
				baseResponse.setErrorCd((String) resultSetMap.get("Error_Cd"));
				
				saveEquipResponse.add(baseResponse);
			}
		}
		
		return saveEquipResponse;
	}
	

	@SuppressWarnings("rawtypes")
	public void setSaveEquipStaticData(SaveEquipDetailsRequest saveEquipDetailsRequest){
		
		
		Modules modules = saveEquipDetailsRequest.getModulesDet();
		List<StaticModule> staticModuleList= modules.getStaticModuleList();
		Iterator itStaticModuleList = staticModuleList.iterator();
		while(itStaticModuleList.hasNext()){
			StaticModule staticModule =  (StaticModule) itStaticModuleList.next();
			List<Form> formList = staticModule.getForms();
			Iterator itFormList = formList.iterator();
			
			while(itFormList.hasNext()){
				Form form = (Form) itFormList.next();
				String dbColumnName = form.getDbColumnName();
				String value = form.getValue();
				setAssetDetails(dbColumnName, value);
				setUnitOther(dbColumnName, value);
				setOther(dbColumnName, value);
				setOperationalStatus(dbColumnName, value);
			}
		}
	}
	public void setAssetDetails(String dbColumnName, String value){
		

		if("Unit_Nr".equalsIgnoreCase(dbColumnName)){
			saveEquipStaticRequest.setUnitNr(CommonUtil.getStringToInteger(value));
		}else if("Catgr_Id".equalsIgnoreCase(dbColumnName)){
			saveEquipStaticRequest.setCatgrId(CommonUtil.getStringToInteger(value));
		}else if("Unit_Catgrp_Code".equalsIgnoreCase(dbColumnName)){
			saveEquipStaticRequest.setCatgrpCode(value);
		}else if("Unit_Serial_Nr".equalsIgnoreCase(dbColumnName)){
			saveEquipStaticRequest.setSerialNr(value);
		}else if("Unit_Manufacturer".equalsIgnoreCase(dbColumnName)){
			saveEquipStaticRequest.setManufacturer(value);
		}else if("Unit_Licence_Nr".equalsIgnoreCase(dbColumnName)){
			saveEquipStaticRequest.setLicenceNr(value);
		}else if("Unit_Licence_Country_Cd".equalsIgnoreCase(dbColumnName)){
			saveEquipStaticRequest.setLicenceCountryCd(value);
		}else if("Unit_Model_Year".equalsIgnoreCase(dbColumnName)){
			saveEquipStaticRequest.setModelYear(CommonUtil.getStringToInteger(value));
		}else if("Unit_Operational_Status".equalsIgnoreCase(dbColumnName)){
			saveEquipStaticRequest.setOperationalStatus(value);
		}
	}
	
	public void setUnitOther(String dbColumnName, String value){
		if("Unit_Comment".equalsIgnoreCase(dbColumnName)){
			saveEquipStaticRequest.setUnitComment(value);
		}else if("Unit_Lien_Cd".equalsIgnoreCase(dbColumnName)){
			saveEquipStaticRequest.setLienCd(value);
		}else if("Unit_Aquired_Company".equalsIgnoreCase(dbColumnName)){
			saveEquipStaticRequest.setAquiredCompany(value);
		}else if("Unit_Aquired_Refrnc".equalsIgnoreCase(dbColumnName)){
			saveEquipStaticRequest.setAquiredRefrnc(value);
		}else if("Unit_Customer_Refrnc".equalsIgnoreCase(dbColumnName)){
			saveEquipStaticRequest.setCustomerRefrnc(value);
		}
	}
	
	
	public void setOther(String dbColumnName, String value){
		if("Unit_Category_Cd".equalsIgnoreCase(dbColumnName)){
			saveEquipStaticRequest.setCategoryCd(value);
		}else if("Unit_Group_Cd".equalsIgnoreCase(dbColumnName)){
			saveEquipStaticRequest.setGroupCd(value);
		}else if("Status_Comment".equalsIgnoreCase(dbColumnName)){
			saveEquipStaticRequest.setStatusComment(value);
		}else if("Unit_Sold_Date".equalsIgnoreCase(dbColumnName)){
			saveEquipStaticRequest.setSoldDate(value);
		}
	}
	
	public void setOperationalStatus(String dbColumnName, String value){
		if("Unit_Available_For_Sale_Ind".equalsIgnoreCase(dbColumnName)){
			saveEquipStaticRequest.setAvailableForSaleInd(value);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void setSaveEquipOtherRequest(SaveEquipDetailsRequest saveEquipDetailsRequest){
		
		Modules modules = saveEquipDetailsRequest.getModulesDet();
		List<StaticModule> staticModuleList= modules.getStaticModuleList();
		Iterator itStaticModuleList = staticModuleList.iterator();
		while(itStaticModuleList.hasNext()){
			StaticModule staticModule =  (StaticModule) itStaticModuleList.next();
			List<Form> formList = staticModule.getForms();
			Iterator itFormList = formList.iterator();
			while(itFormList.hasNext()){
				Form form = (Form) itFormList.next();
				String dbColumnName = form.getDbColumnName();
				String value = form.getValue();
				if("Unit_Nr".equalsIgnoreCase(dbColumnName)){
					saveEquipOtherRequest.setUnitNr(CommonUtil.getStringToInteger(value));
				}else if("Unit_Last_Park_Loc_Cd".equalsIgnoreCase(dbColumnName)){
					saveEquipOtherRequest.setUnitLastParkLocCd(value);
				}
			}
		}
	}

}
