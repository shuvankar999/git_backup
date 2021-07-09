package com.tip.estimation.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.estimation.model.EnrichAddtionalRequest;
import com.tip.estimation.model.EnrichAddtionalResponse;
import com.tip.estimation.model.EnrichAddtnlPartsObject;
import com.tip.estimation.model.EnrichAddtnlWotObject;
import com.tip.estimation.model.EnrichLabourRatesObject;
import com.tip.estimation.model.EnrichTyreLabourRatesObject;
import com.tip.estimation.model.EnrichWoObject;
import com.tip.estimation.repository.EnrichAddtnlRepository;
import com.tip.estimation.service.EnrichAddtnlService;


@Service
public class EnrichAddtnlServiceImpl implements EnrichAddtnlService{

	static final Logger logger = LoggerFactory.getLogger(EnrichAddtnlServiceImpl.class);
	
	@Autowired
	EnrichAddtnlRepository enrichAddtnlRepository;
	
	public static final String ESTN_WO_ID = "woNr";
	public static final String ESTN_WOT_ID = "wotNr";
	public static final String SUPPLIER_ID = "supplierId";
	public static final String GRP_CD_DESC = "groupCdDesc";
	public static final String SHOW_HIDE = "showHide";
	public static final String CURRENCY = "currency";
	public static final String LABOUR_TIME = "labourTime";
	public static final String IS_APPROVED = "isApproved";
	public static final String IS_REJECTED = "isRejected";
	public static final String CHARGES = "charges";
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public EnrichAddtionalResponse fetchAddtnlEnrichDetails(EnrichAddtionalRequest enrichAddtionalRequest) {

		Map<String, Object> returnMap = enrichAddtnlRepository.fetchAddtnlEnrichDetails(enrichAddtionalRequest);
		
		EnrichAddtionalResponse enrichAddtionalResponse = new EnrichAddtionalResponse();
		
		List<EnrichWoObject> addtnlWoResponse = new ArrayList();
		List<EnrichAddtnlPartsObject> addtnlUnMatchPartResponse = new ArrayList();
		List<EnrichLabourRatesObject> addtnlLabourResponse = new ArrayList();
		List<EnrichTyreLabourRatesObject> addtnlTyreResponse = new ArrayList();
		List<EnrichAddtnlWotObject> addtnlWotResponse = new ArrayList();
		
		
if (null != returnMap) {
			List<Map<String, Object>> addtnlWoList = (List<Map<String, Object>>) returnMap.get("#result-set-1");
			List<Map<String, Object>> addtnlWotList = (List<Map<String, Object>>) returnMap.get("#result-set-2");
			List<Map<String, Object>> addtnlPartList =(List<Map<String, Object>>) returnMap.get("#result-set-3");
			List<Map<String, Object>> addtnlLabourRates = (List<Map<String, Object>>) returnMap.get("#result-set-4");
			List<Map<String, Object>> addtnlTyreRates = (List<Map<String, Object>>) returnMap.get("#result-set-5");
			
			
			setDataAddtnlWorkOrderList(addtnlWoList,addtnlPartList,addtnlWoResponse);
			setDataAddtnlWorkOrderTaskList(addtnlWotList, addtnlWoResponse);
			setAddtnlUnMatchedPartsList(addtnlPartList, addtnlUnMatchPartResponse);
			setAddtnlLabourRatesList(addtnlLabourRates, addtnlLabourResponse, addtnlWoResponse);
			setAddtnlTyreLabourRatesList(addtnlTyreRates, addtnlTyreResponse,addtnlWoResponse);
			
			enrichAddtionalResponse.getEstnWoList().addAll(addtnlWoResponse);
			enrichAddtionalResponse.getEstnUnMatchedPartLists().addAll(addtnlUnMatchPartResponse);
		
	}
	return enrichAddtionalResponse;
	
	}

	private void setDataAddtnlWorkOrderList(List<Map<String, Object>> addtnlWoList,
			List<Map<String, Object>> addtnlPartList, List<EnrichWoObject> addtnlWoResponse) {
		
		for (int i = 0; i < addtnlWoList.size(); i++) {
			
			Map<String, Object> workOrderObjectMap = addtnlWoList.get(i);
			EnrichWoObject enrichWoObject = new EnrichWoObject(); 

			enrichWoObject.setEstnWOId((Integer) workOrderObjectMap.get(ESTN_WO_ID));
			enrichWoObject.setSupplierId((Integer) workOrderObjectMap.get(SUPPLIER_ID));
			enrichWoObject.setSupplierName((String) workOrderObjectMap.get("supplierName"));
			enrichWoObject.setGroupCd((String) workOrderObjectMap.get("groupCd"));
			enrichWoObject.setGroupCdDesc((String) workOrderObjectMap.get(GRP_CD_DESC));
			enrichWoObject.setLoTaskCount((Integer) workOrderObjectMap.get("estnWotCnt"));
			enrichWoObject.setPartsCount((Integer) workOrderObjectMap.get("estnPartsCnt"));
			enrichWoObject.setEstnTyreCnt((Integer) workOrderObjectMap.get("estnTyreCnt"));
			enrichWoObject.setLoCommentsInternal((String) workOrderObjectMap.get("woIntComm"));
			enrichWoObject.setLoCommentsCustomers((String) workOrderObjectMap.get("woCustComm"));
			enrichWoObject.setTotTagetTime((BigDecimal) workOrderObjectMap.get("totTagetTime"));
			enrichWoObject.setLabourTime((String) workOrderObjectMap.get(LABOUR_TIME));

			setMatchedPartsList(addtnlPartList, enrichWoObject);
			
			addtnlWoResponse.add(enrichWoObject);					
		}
	}

	

	private void setDataAddtnlWorkOrderTaskList(List<Map<String, Object>> addtnlWotList,
			List<EnrichWoObject> addtnlWoResponse) {
		
		for (EnrichWoObject enrichWoObject : addtnlWoResponse) {
			List<EnrichAddtnlWotObject> addtnlWotObjectResponse = new ArrayList();
			for (int i = 0; i < addtnlWotList.size(); i++) {
				Map<String, Object> enrichWotObjectMap = addtnlWotList.get(i);
				Integer woNr = (Integer) enrichWotObjectMap.get(ESTN_WO_ID);
				
				EnrichAddtnlWotObject enrichAddtnlWotObject = new EnrichAddtnlWotObject();
				if (woNr.equals(enrichWoObject.getEstnWOId())) {
			
					enrichAddtnlWotObject.setEstnWOId((Integer) enrichWotObjectMap.get(ESTN_WO_ID));
					enrichAddtnlWotObject.setEstnWOTId((Integer) enrichWotObjectMap.get(ESTN_WOT_ID));
					enrichAddtnlWotObject.setGroupCd((String) enrichWotObjectMap.get("groupCd"));
					enrichAddtnlWotObject.setSubgroupCd((String) enrichWotObjectMap.get("subgroupCd"));
					enrichAddtnlWotObject.setActivityCd((String) enrichWotObjectMap.get("activityCd"));
					enrichAddtnlWotObject.setFailureCauseCd((String) enrichWotObjectMap.get("failureCauseCd"));
					enrichAddtnlWotObject.setActionCd((String) enrichWotObjectMap.get("actionCd"));
					enrichAddtnlWotObject.setPositionCd((String) enrichWotObjectMap.get("positionCd"));
					enrichAddtnlWotObject.setTargetTime((BigDecimal) enrichWotObjectMap.get("targeTime"));
					enrichAddtnlWotObject.setReasonCd((String) enrichWotObjectMap.get("reasonCd"));
					enrichAddtnlWotObject.setGroupCdDesc((String) enrichWotObjectMap.get(GRP_CD_DESC));
					enrichAddtnlWotObject.setSubGroup((String) enrichWotObjectMap.get("subGroup"));
					enrichAddtnlWotObject.setActivity((String) enrichWotObjectMap.get("activity"));
					enrichAddtnlWotObject.setFailureCause((String) enrichWotObjectMap.get("failureCause"));
					enrichAddtnlWotObject.setAction((String) enrichWotObjectMap.get("action"));
					enrichAddtnlWotObject.setReason((String) enrichWotObjectMap.get("reason"));
					enrichAddtnlWotObject.setPosition((String) enrichWotObjectMap.get("position"));
					enrichAddtnlWotObject.setSoldTime((Double) enrichWotObjectMap.get("soldTime"));
					enrichAddtnlWotObject.setLabourRate((BigDecimal) enrichWotObjectMap.get("labourRate"));
					enrichAddtnlWotObject.setLabourTime((String) enrichWotObjectMap.get(LABOUR_TIME));
					enrichAddtnlWotObject.setTaskComments((String) enrichWotObjectMap.get("taskComments"));
					enrichAddtnlWotObject.setWshpWPNr((BigDecimal) enrichWotObjectMap.get("wshpWPNr"));
					enrichAddtnlWotObject.setWshpWONr((Integer) enrichWotObjectMap.get("wshpWONr"));
					enrichAddtnlWotObject.setWshpWOTNr((Integer) enrichWotObjectMap.get("wshpWOTNr"));
					enrichAddtnlWotObject.setIsApproved((String) enrichWotObjectMap.get(IS_APPROVED));
					enrichAddtnlWotObject.setIsRejected((String) enrichWotObjectMap.get(IS_REJECTED));	
					enrichAddtnlWotObject.setShowHide(EnrichAddtnlServiceImpl.checkValue(enrichWotObjectMap.get(SHOW_HIDE)));
					enrichAddtnlWotObject.setAttribute(EnrichAddtnlServiceImpl.checkValue(enrichWotObjectMap.get("addAttribute")));
					
					addtnlWotObjectResponse.add(enrichAddtnlWotObject);
				}
			}
			
			enrichWoObject.getEstnWotList().addAll(addtnlWotObjectResponse);
		}
	}
				
	
	private void setMatchedPartsList(List<Map<String, Object>> addtnlPartList, EnrichWoObject enrichWoObject) {
			
		for (Map<String, Object> partsMap : addtnlPartList) {
			Integer partWoNr = (Integer) partsMap.get(ESTN_WO_ID);
			
			EnrichAddtnlPartsObject enrichAddtnlPartsObject = new EnrichAddtnlPartsObject();
			if(partWoNr!=null && !partWoNr.equals(0)) {
				if (partWoNr.equals(enrichWoObject.getEstnWOId())) {
					
					enrichAddtnlPartsObject.setEstnWOId((Integer) partsMap.get(ESTN_WO_ID));
					enrichAddtnlPartsObject.setEstnWOTId((Integer) partsMap.get(ESTN_WOT_ID));
					enrichAddtnlPartsObject.setPartNumber((String) partsMap.get("partNumber"));
					enrichAddtnlPartsObject.setPartDesc((String) partsMap.get("partDesc"));
					enrichAddtnlPartsObject.setSupplierId((Integer) partsMap.get(SUPPLIER_ID));
					enrichAddtnlPartsObject.setCurrency((String) partsMap.get(CURRENCY));
					enrichAddtnlPartsObject.setQuantity((Integer) partsMap.get("quantity"));		
					enrichAddtnlPartsObject.setCostToTip((BigDecimal) partsMap.get("costToTip"));
					enrichAddtnlPartsObject.setCostPlus((BigDecimal) partsMap.get("costPlus"));
					enrichAddtnlPartsObject.setRetailPrice((BigDecimal) partsMap.get("retailPrice"));
					enrichAddtnlPartsObject.setDicount((BigDecimal) partsMap.get("dicount"));		
					enrichAddtnlPartsObject.setIsApproved((String) partsMap.get(IS_APPROVED));
					enrichAddtnlPartsObject.setIsRejected((String) partsMap.get(IS_REJECTED));
					enrichAddtnlPartsObject.setFixedPrice((BigDecimal) partsMap.get("fixedPrice"));
					
					enrichAddtnlPartsObject.setShowHide(EnrichAddtnlServiceImpl.checkValue(partsMap.get(SHOW_HIDE)));
					
					enrichWoObject.getEstnMatchedPartLists().add(enrichAddtnlPartsObject);
				}

			}
		}
	}
		
	
	
	
	private void setAddtnlUnMatchedPartsList(List<Map<String, Object>> addtnlPartList,
			List<EnrichAddtnlPartsObject> addtnlUnMatchPartResponse) {
		
		for (Map<String, Object> partsMap : addtnlPartList) {
			Integer partWoNr = (Integer) partsMap.get(ESTN_WO_ID);
			
			EnrichAddtnlPartsObject enrichAddtnlPartsObject = new EnrichAddtnlPartsObject();
			
			if (partWoNr == null || partWoNr == 0) {
				
				enrichAddtnlPartsObject.setEstnWOId((Integer) partsMap.get(ESTN_WO_ID));
				enrichAddtnlPartsObject.setEstnWOTId((Integer) partsMap.get(ESTN_WOT_ID));
				enrichAddtnlPartsObject.setPartNumber((String) partsMap.get("partNumber"));
				enrichAddtnlPartsObject.setPartDesc((String) partsMap.get("partDesc"));
				enrichAddtnlPartsObject.setSupplierId((Integer) partsMap.get(SUPPLIER_ID));
				enrichAddtnlPartsObject.setCurrency((String) partsMap.get(CURRENCY));
				enrichAddtnlPartsObject.setQuantity((Integer) partsMap.get("quantity"));		
				enrichAddtnlPartsObject.setCostToTip((BigDecimal) partsMap.get("costToTip"));
				enrichAddtnlPartsObject.setCostPlus((BigDecimal) partsMap.get("costPlus"));
				enrichAddtnlPartsObject.setRetailPrice((BigDecimal) partsMap.get("retailPrice"));
				enrichAddtnlPartsObject.setDicount((BigDecimal) partsMap.get("dicount"));		
				enrichAddtnlPartsObject.setIsApproved((String) partsMap.get(IS_APPROVED));
				enrichAddtnlPartsObject.setIsRejected((String) partsMap.get(IS_REJECTED));
				enrichAddtnlPartsObject.setFixedPrice((BigDecimal) partsMap.get("fixedPrice"));
				
				enrichAddtnlPartsObject.setShowHide(EnrichAddtnlServiceImpl.checkValue(partsMap.get(SHOW_HIDE)));
				
				addtnlUnMatchPartResponse.add(enrichAddtnlPartsObject);
				
			}
			}
	}
		
	

	private void setAddtnlLabourRatesList(List<Map<String, Object>> addtnlLabourRates,
			List<EnrichLabourRatesObject> addtnlLabourResponse, List<EnrichWoObject> addtnlWoResponse) {
		

		for (EnrichWoObject enrichWoObject : addtnlWoResponse) {

			for (int i = 0; i < addtnlLabourRates.size(); i++) {
				Map<String, Object> etsnLabourRatesMap = addtnlLabourRates.get(i);
				EnrichLabourRatesObject enrichLabourRatesObject = new EnrichLabourRatesObject();

				enrichLabourRatesObject.setLabourTime((String) etsnLabourRatesMap.get(LABOUR_TIME));
				enrichLabourRatesObject.setRate((BigDecimal) etsnLabourRatesMap.get("rate"));

				addtnlLabourResponse.add(enrichLabourRatesObject);	
			}
			enrichWoObject.getEstnLabourRatesList().addAll(addtnlLabourResponse);
		}
		
	}
		
	

	private void setAddtnlTyreLabourRatesList(List<Map<String, Object>> addtnlTyreRates,
			List<EnrichTyreLabourRatesObject> addtnlTyreResponse, List<EnrichWoObject> addtnlWoResponse) {
		
		for (EnrichWoObject enrichWoObject : addtnlWoResponse) {

			for (int i = 0; i < addtnlTyreRates.size(); i++) {
				Map<String, Object> etsnTyreLabourRatesMap = addtnlTyreRates.get(i);
				EnrichTyreLabourRatesObject enrichTyreLabourRatesObject = new EnrichTyreLabourRatesObject();

				enrichTyreLabourRatesObject.setLabourCd((String) etsnTyreLabourRatesMap.get("labourCd"));
				enrichTyreLabourRatesObject.setTyreLabourTime((String) etsnTyreLabourRatesMap.get("tyreLabourTime"));
				enrichTyreLabourRatesObject.setLabourTime((String) etsnTyreLabourRatesMap.get(LABOUR_TIME));
				enrichTyreLabourRatesObject.setRate((BigDecimal) etsnTyreLabourRatesMap.get("rate"));

				addtnlTyreResponse.add(enrichTyreLabourRatesObject);				
			}
			enrichWoObject.getEtsnTyreLabourRatesList().addAll(addtnlTyreResponse);
		}
	}

	
	public static boolean checkValue(Object value){
		boolean flag;
		if("Y".equalsIgnoreCase(value.toString())){
			flag = true;
		}else{
			flag = false;
		}
		return flag;
	}
	
}
