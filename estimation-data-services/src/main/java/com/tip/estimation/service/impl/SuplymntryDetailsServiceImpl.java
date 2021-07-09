package com.tip.estimation.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.estimation.model.SuplyConsumablesObject;
import com.tip.estimation.model.SuplyHeaderObject;
import com.tip.estimation.model.SuplyLabourRatesObject;
import com.tip.estimation.model.SuplyPartsObject;
import com.tip.estimation.model.SuplyTyreLabourRatesObject;
import com.tip.estimation.model.SuplyTyreServiceObject;
import com.tip.estimation.model.SuplyTyreSpecsObject;
import com.tip.estimation.model.SuplyWoObject;
import com.tip.estimation.model.SuplyWotObject;
import com.tip.estimation.model.SuplymntryRequest;
import com.tip.estimation.model.SuplymntryResponse;
import com.tip.estimation.repository.SuplymntryDetailsRepository;
import com.tip.estimation.service.SuplymntryDetailsService;

@Service
public class SuplymntryDetailsServiceImpl implements SuplymntryDetailsService{
	
static final Logger logger = LoggerFactory.getLogger(FetchEstnEnrichDetailsServiceImpl.class);
	
	boolean flag;
	
	@Autowired
	SuplymntryDetailsRepository suplymntryDetailsRepository;
	
	public static final String ESTN_ID = "estimationId";
	public static final String ESTN_WO_ID = "estnWOId";
	public static final String ESTN_WOT_ID = "estnWOTId";
	public static final String SUPPLIER_ID = "supplierId";
	public static final String GRP_CD_DESC = "groupCdDesc";
	public static final String SHOW_HIDE = "showHide";
	public static final String CURRENCY = "currency";
	public static final String VERSION = "version";
	public static final String SUPPLEMENTARY = "supplementary";
	public static final String LABOUR_TIME = "labourTime";
	public static final String IS_APPROVED = "isApproved";
	public static final String IS_REJECTED = "isRejected";
	public static final String CHARGES = "charges";
	public static final String REJECTED = "rejectedReason";
	public static final String SSOID = "ssoId";
	public static final String BUNDLE_NAME = "bundleName";
	
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public SuplymntryResponse getSupplymntryDetails(SuplymntryRequest suplymntryRequest) {


		Map<String, Object> returnMap = suplymntryDetailsRepository.getSupplymntryDetails(suplymntryRequest);
		SuplymntryResponse suplymntryResponse = new SuplymntryResponse();
		
		SuplyHeaderObject suplyHeaderResponse = new SuplyHeaderObject();
		List<SuplyWoObject> suplyWoResponseList = new ArrayList();
		List<SuplyPartsObject> umPartsResponseList = new ArrayList();
		List<SuplyLabourRatesObject> suplyLabourRatesResponseList = new ArrayList();
		List<SuplyTyreLabourRatesObject> suplyTyreLabourRatesResponseList = new ArrayList();
		List<SuplyTyreSpecsObject> suplyTyreSpecResponseList = new ArrayList();
		List<SuplyTyreServiceObject> suplyTyreServiceResponseList = new ArrayList();
		List<SuplyConsumablesObject> suplyConsumablesResponseList = new ArrayList();
		
		
		
if (null != returnMap) {
			List<Map<String, Object>> headerDetails = (List<Map<String, Object>>) returnMap.get("#result-set-1");
			List<Map<String, Object>> woList = (List<Map<String, Object>>) returnMap.get("#result-set-2");
			List<Map<String, Object>> workOrderTaskList =(List<Map<String, Object>>) returnMap.get("#result-set-3");
			List<Map<String, Object>> partsList = (List<Map<String, Object>>) returnMap.get("#result-set-4");
			List<Map<String, Object>> labourRatesList = (List<Map<String, Object>>) returnMap.get("#result-set-5");
			List<Map<String, Object>> tyreLabourRates = (List<Map<String, Object>>) returnMap.get("#result-set-6");
			List<Map<String, Object>> consumableList = (List<Map<String, Object>>) returnMap.get("#result-set-7");
			List<Map<String, Object>> tyreSpecList = (List<Map<String, Object>>) returnMap.get("#result-set-8");
			List<Map<String, Object>> tyreServiceList = (List<Map<String, Object>>) returnMap.get("#result-set-9");
			
			
			setDataEstnHeaderList(headerDetails, suplyHeaderResponse);
			setDataEstnWorkOrderList(woList,partsList,suplyWoResponseList,  suplyHeaderResponse);			
			setDataEstnWorkOrderTaskList(workOrderTaskList, suplyWoResponseList);		
			setUnMatchedPartsList(partsList, umPartsResponseList);
			setDataEstnLabourRatesList(labourRatesList, suplyLabourRatesResponseList, suplyWoResponseList);
			setDataEtsnTyreLabourRatesList(tyreLabourRates, suplyTyreLabourRatesResponseList,suplyWoResponseList);
			setDataEstnConsumableList(consumableList, suplyConsumablesResponseList, suplymntryResponse);
			setDataEstnTyreSpecList(tyreSpecList, suplyTyreSpecResponseList, suplymntryResponse);
			setDataEstnTyreServiceList(tyreServiceList, suplyTyreServiceResponseList, suplymntryResponse);
		
		
			suplymntryResponse.setEstnHeaderList(suplyHeaderResponse);
	
			suplymntryResponse.getEstnUnMatchedPartLists().addAll(umPartsResponseList);
}

		return suplymntryResponse;
	}




	private void setDataEstnHeaderList(List<Map<String, Object>> headerDetails, SuplyHeaderObject suplyHeaderResponse) {
		for (int i = 0; i < headerDetails.size(); i++) {
			Map<String, Object> estnHeaderMap = headerDetails.get(i);
			
			suplyHeaderResponse.setEstimationId((BigDecimal) estnHeaderMap.get(ESTN_ID));
			suplyHeaderResponse.setEstnTitle((String) estnHeaderMap.get("estnTitle"));
			suplyHeaderResponse.setCustNotes((String) estnHeaderMap.get("custNotes"));
			suplyHeaderResponse.setInternalNotes((String) estnHeaderMap.get("internalNotes"));
			suplyHeaderResponse.setCurrency((String) estnHeaderMap.get(CURRENCY));
			suplyHeaderResponse.setVat((BigDecimal) estnHeaderMap.get("vat"));
			suplyHeaderResponse.setVatValue((BigDecimal) estnHeaderMap.get("vatRate"));			
			suplyHeaderResponse.setConcession((BigDecimal) estnHeaderMap.get("concession"));
			suplyHeaderResponse.setEstnStatusId((Integer) estnHeaderMap.get("estnStatusId"));
			suplyHeaderResponse.setEstnStatus((String) estnHeaderMap.get("estnStatus"));
			suplyHeaderResponse.setVersion((Integer) estnHeaderMap.get(VERSION));
			suplyHeaderResponse.setSupplementary((Integer) estnHeaderMap.get(SUPPLEMENTARY));
			suplyHeaderResponse.setWpNumber((BigDecimal) estnHeaderMap.get("wpNumber"));
			suplyHeaderResponse.setAssetNumber((Integer) estnHeaderMap.get("assetNumber"));
			suplyHeaderResponse.setCompanyNr((Integer) estnHeaderMap.get("companyNr"));
			suplyHeaderResponse.setShowHide(FetchEstnEnrichDetailsServiceImpl.checkValue(estnHeaderMap.get(SHOW_HIDE)));
			
		}
	}
	
		
	private void setDataEstnWorkOrderList(List<Map<String, Object>> woList, List<Map<String, Object>> partsList,
			List<SuplyWoObject> suplyWoResponseList, SuplyHeaderObject suplyHeaderResponse) {

			for (int i = 0; i < woList.size(); i++) {
				
				Map<String, Object> woObjectMap = woList.get(i);
				SuplyWoObject suplyWoObject = new SuplyWoObject(); 

				suplyWoObject.setEstimationId((BigDecimal) woObjectMap.get(ESTN_ID));
				suplyWoObject.setEstnWOId((Integer) woObjectMap.get(ESTN_WO_ID));
				suplyWoObject.setSupplierId((Integer) woObjectMap.get(SUPPLIER_ID));
				suplyWoObject.setSupplierName((String) woObjectMap.get("supplierName"));
				suplyWoObject.setGroupCd((String) woObjectMap.get("groupCd"));
				suplyWoObject.setGroupCdDesc((String) woObjectMap.get(GRP_CD_DESC));
				suplyWoObject.setLoTaskCount((Integer) woObjectMap.get("estnWotCnt"));
				suplyWoObject.setPartsCount((Integer) woObjectMap.get("estnPartsCnt"));
				suplyWoObject.setEstnTyreCnt((Integer) woObjectMap.get("estnTyreCnt"));
				suplyWoObject.setLoCommentsInternal((String) woObjectMap.get("woIntComm"));
				suplyWoObject.setLoCommentsCustomers((String) woObjectMap.get("woCustComm"));
				suplyWoObject.setTotTagetTime((Double) woObjectMap.get("totTagetTime"));
				suplyWoObject.setLabourTime((String) woObjectMap.get(LABOUR_TIME));

				setMatchedPartsList(partsList, suplyWoObject);
				
				suplyWoResponseList.add(suplyWoObject);					
			}
			suplyHeaderResponse.getEstnWoList().addAll(suplyWoResponseList);
		}
	

	private void setDataEstnWorkOrderTaskList(List<Map<String, Object>> workOrderTaskList,
			List<SuplyWoObject> suplyWoResponseList) {

		for (SuplyWoObject suplyWoObject : suplyWoResponseList) {
			List<SuplyWotObject> suplyWotResponse = new ArrayList();
			for (int i = 0; i < workOrderTaskList.size(); i++) {
				Map<String, Object> workOrderTaskMap = workOrderTaskList.get(i);
				Integer woId = (Integer) workOrderTaskMap.get(ESTN_WO_ID);
				SuplyWotObject suplyWotObject = new SuplyWotObject();
				if (woId.equals(suplyWoObject.getEstnWOId())) {
					suplyWotObject.setEstimationId((BigDecimal) workOrderTaskMap.get(ESTN_ID));
					suplyWotObject.setEstnWOId((Integer) workOrderTaskMap.get(ESTN_WO_ID));
					suplyWotObject.setEstnWOTId((Integer) workOrderTaskMap.get(ESTN_WOT_ID));
					suplyWotObject.setGroupCd((String) workOrderTaskMap.get("groupCd"));
					suplyWotObject.setSubgroupCd((String) workOrderTaskMap.get("subgroupCd"));
					suplyWotObject.setActivityCd((String) workOrderTaskMap.get("activityCd"));
					suplyWotObject.setFailureCauseCd((String) workOrderTaskMap.get("failureCauseCd"));
					suplyWotObject.setActionCd((String) workOrderTaskMap.get("actionCd"));
					suplyWotObject.setPositionCd((String) workOrderTaskMap.get("positionCd"));
					suplyWotObject.setTargetTime((BigDecimal) workOrderTaskMap.get("targeTime"));
					suplyWotObject.setReasonCd((String) workOrderTaskMap.get("reasonCd"));
					suplyWotObject.setGroupCdDesc((String) workOrderTaskMap.get(GRP_CD_DESC));
					suplyWotObject.setSubGroup((String) workOrderTaskMap.get("subGroup"));
					suplyWotObject.setActivity((String) workOrderTaskMap.get("activity"));
					suplyWotObject.setFailureCause((String) workOrderTaskMap.get("failureCause"));
					suplyWotObject.setAction((String) workOrderTaskMap.get("action"));
					suplyWotObject.setReason((String) workOrderTaskMap.get("reason"));
					suplyWotObject.setPosition((String) workOrderTaskMap.get("position"));
					suplyWotObject.setSoldTime((Double) workOrderTaskMap.get("soldTime"));
					suplyWotObject.setLabourRate((BigDecimal) workOrderTaskMap.get("labourRate"));
					suplyWotObject.setLabourTime((String) workOrderTaskMap.get(LABOUR_TIME));
					suplyWotObject.setTaskname((String) workOrderTaskMap.get("taskComments"));
					suplyWotObject.setWshpWPNr((BigDecimal) workOrderTaskMap.get("wshpWPNr"));
					suplyWotObject.setWshpWONr((Integer) workOrderTaskMap.get("wshpWONr"));
					suplyWotObject.setWshpWOTNr((Integer) workOrderTaskMap.get("wshpWOTNr"));
					suplyWotObject.setIsApproved((String) workOrderTaskMap.get(IS_APPROVED));
					suplyWotObject.setIsRejected((String) workOrderTaskMap.get(IS_REJECTED));
					suplyWotObject.setShowHide(FetchEstnEnrichDetailsServiceImpl.checkValue(workOrderTaskMap.get("addAttribute")));
					suplyWotObject.setShowHide(FetchEstnEnrichDetailsServiceImpl.checkValue(workOrderTaskMap.get(SHOW_HIDE)));

					suplyWotResponse.add(suplyWotObject);
				}
			}
			suplyWoObject.getEstnWotList().addAll(suplyWotResponse);
		}
	}

		private void setMatchedPartsList(List<Map<String, Object>> partsList, SuplyWoObject suplyWoObject){
		
			for (Map<String, Object> partsMap : partsList) {
				Integer woIdPart = (Integer) partsMap.get(ESTN_WO_ID);
				
				SuplyPartsObject suplyPartsObject = new SuplyPartsObject();
				if(woIdPart!=null && !woIdPart.equals(0)) {
					if (woIdPart.equals(suplyWoObject.getEstnWOId())) {
						suplyPartsObject.setEstimationId((BigDecimal) partsMap.get(ESTN_ID));
						suplyPartsObject.setPartNo((String) partsMap.get("partNumber"));
						suplyPartsObject.setPartDesc((String) partsMap.get("partDesc"));
						suplyPartsObject.setSupplierId((Integer) partsMap.get(SUPPLIER_ID));
						suplyPartsObject.setCurrency((String) partsMap.get(CURRENCY));
						suplyPartsObject.setQuantity((Integer) partsMap.get("qty"));
						suplyPartsObject.setCostToTip((BigDecimal) partsMap.get("costToTip"));
						suplyPartsObject.setCostPlus((BigDecimal) partsMap.get("costPlus"));
						suplyPartsObject.setRetailPrice((BigDecimal) partsMap.get("retailPrice"));
						suplyPartsObject.setDicount((BigDecimal) partsMap.get("dicount"));
						suplyPartsObject.setEstnWOId((Integer) partsMap.get(ESTN_WO_ID));
						suplyPartsObject.setEstnWOTId((Integer) partsMap.get(ESTN_WOT_ID));
						suplyPartsObject.setIsApproved((String) partsMap.get(IS_APPROVED));
						suplyPartsObject.setIsRejected((String) partsMap.get(IS_REJECTED));
						suplyPartsObject.setFixedPrice((BigDecimal) partsMap.get("fixedPrice"));
						suplyPartsObject.setShowHide(FetchEstnEnrichDetailsServiceImpl.checkValue(partsMap.get(SHOW_HIDE)));
	
						suplyWoObject.getEstnMatchedPartLists().add(suplyPartsObject);
	
					}
	
				}
			}
		}

private void setUnMatchedPartsList(List<Map<String, Object>> partsList,
		List<SuplyPartsObject> umPartsResponseList){
			
		for (Map<String, Object> partsMap : partsList) {
			Integer woIdPart = (Integer) partsMap.get(ESTN_WO_ID);
			
			SuplyPartsObject suplyPartsObject = new SuplyPartsObject();

				if (woIdPart == null || woIdPart == 0) {

					suplyPartsObject.setEstimationId((BigDecimal) partsMap.get(ESTN_ID));
					suplyPartsObject.setPartNo((String) partsMap.get("partNumber"));
					suplyPartsObject.setPartDesc((String) partsMap.get("partDesc"));
					suplyPartsObject.setSupplierId((Integer) partsMap.get(SUPPLIER_ID));
					suplyPartsObject.setCurrency((String) partsMap.get(CURRENCY));
					suplyPartsObject.setQuantity((Integer) partsMap.get("qty"));
					suplyPartsObject.setCostToTip((BigDecimal) partsMap.get("costToTip"));
					suplyPartsObject.setCostPlus((BigDecimal) partsMap.get("costPlus"));
					suplyPartsObject.setRetailPrice((BigDecimal) partsMap.get("retailPrice"));
					suplyPartsObject.setDicount((BigDecimal) partsMap.get("dicount"));					
					suplyPartsObject.setEstnWOId((Integer) partsMap.get(ESTN_WO_ID));
					suplyPartsObject.setEstnWOTId((Integer) partsMap.get(ESTN_WOT_ID));
					suplyPartsObject.setIsApproved((String) partsMap.get(IS_APPROVED));
					suplyPartsObject.setIsRejected((String) partsMap.get(IS_REJECTED));
					suplyPartsObject.setFixedPrice((BigDecimal) partsMap.get("fixedPrice"));
					suplyPartsObject.setShowHide(FetchEstnEnrichDetailsServiceImpl.checkValue(partsMap.get(SHOW_HIDE)));
							
					umPartsResponseList.add(suplyPartsObject);
			}
		}

	}


	

		private void setDataEstnConsumableList(List<Map<String, Object>> consumableList,
				List<SuplyConsumablesObject> suplyConsumablesResponseList, SuplymntryResponse suplymntryResponse) {
		
			for (int i = 0; i < consumableList.size(); i++) {
				Map<String, Object> cnsumbleObjectMap = consumableList.get(i);
			
				SuplyConsumablesObject consumablesObject = new SuplyConsumablesObject();
				consumablesObject.setEstimationId((BigDecimal) cnsumbleObjectMap.get(ESTN_ID));
				consumablesObject.setConsmblegroupId((Integer) cnsumbleObjectMap.get("consmblegroupId"));
				consumablesObject.setConsmblesubGroupId((Integer) cnsumbleObjectMap.get("consmblesubGroupId"));
				consumablesObject.setConsmbleitemId((Integer) cnsumbleObjectMap.get("consmbleitemId"));
				consumablesObject.setVersion((Integer) cnsumbleObjectMap.get(VERSION));
				consumablesObject.setSupplementary((Integer) cnsumbleObjectMap.get(SUPPLEMENTARY));
				consumablesObject.setMinAmount((BigDecimal) cnsumbleObjectMap.get("minAmount"));
				consumablesObject.setMaxAmount((BigDecimal) cnsumbleObjectMap.get("maxAmount"));
				consumablesObject.setPercentage((BigDecimal) cnsumbleObjectMap.get("percentage"));
				consumablesObject.setFee((BigDecimal) cnsumbleObjectMap.get("fee"));
				consumablesObject.setCharges((BigDecimal) cnsumbleObjectMap.get(CHARGES));			
				consumablesObject.setIsApproved((String) cnsumbleObjectMap.get(IS_APPROVED));
				consumablesObject.setIsRejected((String) cnsumbleObjectMap.get(IS_REJECTED));
				consumablesObject.setRejectedReason((Integer) cnsumbleObjectMap.get(REJECTED));
				consumablesObject.setSsoId((String) cnsumbleObjectMap.get(SSOID));
				consumablesObject.setConsumables((String) cnsumbleObjectMap.get("consumables"));
				consumablesObject.setShowHide(FetchEstnEnrichDetailsServiceImpl.checkValue(cnsumbleObjectMap.get(SHOW_HIDE)));
						
				suplyConsumablesResponseList.add(consumablesObject);
				suplymntryResponse.getEstnConsumablesDetails().addAll(suplyConsumablesResponseList);
	
			}

	}

	private void setDataEstnTyreSpecList(List<Map<String, Object>> tyreSpecList,
			List<SuplyTyreSpecsObject> suplyTyreSpecResponseList, SuplymntryResponse suplymntryResponse) {

		for (int i = 0; i < tyreSpecList.size(); i++) {
			Map<String, Object> tyreSpecObjectMap = tyreSpecList.get(i);
		
			SuplyTyreSpecsObject suplyTyreSpecsObject = new SuplyTyreSpecsObject();
			
			suplyTyreSpecsObject.setEstimationId((BigDecimal) tyreSpecObjectMap.get(ESTN_ID));
			suplyTyreSpecsObject.setEstnWOId((Integer) tyreSpecObjectMap.get(ESTN_WO_ID));
			suplyTyreSpecsObject.setEstnWOTId((Integer) tyreSpecObjectMap.get(ESTN_WOT_ID));
			suplyTyreSpecsObject.setTyreSpecgrpId((Integer) tyreSpecObjectMap.get("tyreSpecgrpId"));
			suplyTyreSpecsObject.setTyreSpecsubgrpId((Integer) tyreSpecObjectMap.get("tyreSpecsubgrpId"));
			suplyTyreSpecsObject.setTyreSpecItemId((Integer) tyreSpecObjectMap.get("tyreSpecItemId"));
			suplyTyreSpecsObject.setTyreSize((String) tyreSpecObjectMap.get("tyreSize"));
			suplyTyreSpecsObject.setApplication((Integer) tyreSpecObjectMap.get("application"));
			suplyTyreSpecsObject.setBrand((String) tyreSpecObjectMap.get("brand"));
			suplyTyreSpecsObject.setTyreStatus((String) tyreSpecObjectMap.get("tyreStatus"));
			suplyTyreSpecsObject.setChargeType((String) tyreSpecObjectMap.get("chargeType"));
			suplyTyreSpecsObject.setLostMM((Integer) tyreSpecObjectMap.get("lostMM"));
			suplyTyreSpecsObject.setVersion((Integer) tyreSpecObjectMap.get(VERSION));
			suplyTyreSpecsObject.setSupplementary((Integer) tyreSpecObjectMap.get(SUPPLEMENTARY));
			suplyTyreSpecsObject.setCharges((BigDecimal) tyreSpecObjectMap.get(CHARGES));			
			suplyTyreSpecsObject.setIsApproved((String) tyreSpecObjectMap.get(IS_APPROVED));
			suplyTyreSpecsObject.setIsRejected((String) tyreSpecObjectMap.get(IS_REJECTED));
			suplyTyreSpecsObject.setRejectedReason((Integer) tyreSpecObjectMap.get(REJECTED));
			suplyTyreSpecsObject.setSsoId((String) tyreSpecObjectMap.get(SSOID));
			
			suplyTyreSpecsObject.setShowHide(FetchEstnEnrichDetailsServiceImpl.checkValue(tyreSpecObjectMap.get(SHOW_HIDE)));

			suplyTyreSpecResponseList.add(suplyTyreSpecsObject);
			suplymntryResponse.getTyreSpeclist().addAll(suplyTyreSpecResponseList);

		}

	}

	private void setDataEstnTyreServiceList(List<Map<String, Object>> tyreServiceList,
			List<SuplyTyreServiceObject> suplyTyreServiceResponseList, SuplymntryResponse suplymntryResponse) {


		for (int i = 0; i < tyreServiceList.size(); i++) {
			Map<String, Object> suplyTyreServiceObjectMap = tyreServiceList.get(i);
		
			SuplyTyreServiceObject suplyTyreServiceObject = new SuplyTyreServiceObject();
			
			suplyTyreServiceObject.setEstimationId((BigDecimal) suplyTyreServiceObjectMap.get(ESTN_ID));
			suplyTyreServiceObject.setEstnWOId((Integer) suplyTyreServiceObjectMap.get(ESTN_WO_ID));
			suplyTyreServiceObject.setEstnWOTId((Integer) suplyTyreServiceObjectMap.get(ESTN_WOT_ID));
			suplyTyreServiceObject.setTyreServicegrpId((Integer) suplyTyreServiceObjectMap.get("tyreServicegrpId"));
			suplyTyreServiceObject.setTyreServicesubgrpId((Integer) suplyTyreServiceObjectMap.get("tyreServicesubgrpId"));
			suplyTyreServiceObject.setTyreServiceItemId((Integer) suplyTyreServiceObjectMap.get("tyreServiceItemId"));
			suplyTyreServiceObject.setVersion((Integer) suplyTyreServiceObjectMap.get(VERSION));
			suplyTyreServiceObject.setSupplementary((Integer) suplyTyreServiceObjectMap.get(SUPPLEMENTARY));
			suplyTyreServiceObject.setCharges((Double) suplyTyreServiceObjectMap.get(CHARGES));
			suplyTyreServiceObject.setIsApproved((String) suplyTyreServiceObjectMap.get(IS_APPROVED));
			suplyTyreServiceObject.setIsRejected((String) suplyTyreServiceObjectMap.get(IS_REJECTED));
			suplyTyreServiceObject.setRejectedReason((Integer) suplyTyreServiceObjectMap.get(REJECTED));
			suplyTyreServiceObject.setSsoId((String) suplyTyreServiceObjectMap.get(SSOID));
			suplyTyreServiceObject.setShowHide(FetchEstnEnrichDetailsServiceImpl.checkValue(suplyTyreServiceObjectMap.get(SHOW_HIDE)));
			
			suplyTyreServiceResponseList.add(suplyTyreServiceObject);
			suplymntryResponse.getTyreServicelist().addAll(suplyTyreServiceResponseList);
		}
	}


	private void setDataEstnLabourRatesList(List<Map<String, Object>> labourRatesList,
			List<SuplyLabourRatesObject> suplyLabourRatesResponseList, List<SuplyWoObject> suplyWoResponseList) {
		
		for (SuplyWoObject suplyWoObject : suplyWoResponseList) {

			for (int i = 0; i < labourRatesList.size(); i++) {
				Map<String, Object> labourRatesMap = labourRatesList.get(i);
				SuplyLabourRatesObject suplyLabourRatesObject = new SuplyLabourRatesObject();

				suplyLabourRatesObject.setLabourTime((String) labourRatesMap.get(LABOUR_TIME));
				suplyLabourRatesObject.setRate((BigDecimal) labourRatesMap.get("rate"));

				suplyLabourRatesResponseList.add(suplyLabourRatesObject);	
			}
			suplyWoObject.getEstnLabourRatesList().addAll(suplyLabourRatesResponseList);
		}
		
	}

	private void setDataEtsnTyreLabourRatesList(List<Map<String, Object>> tyreLabourRates,
			List<SuplyTyreLabourRatesObject> suplyTyreLabourRatesResponseList,
			List<SuplyWoObject> suplyWoResponseList) {

		for (SuplyWoObject suplyWoObject : suplyWoResponseList) {

			for (int i = 0; i < tyreLabourRates.size(); i++) {
				Map<String, Object> tyreLabourRatesMap = tyreLabourRates.get(i);
				SuplyTyreLabourRatesObject suplyTyreLabourRatesObject = new SuplyTyreLabourRatesObject();

				suplyTyreLabourRatesObject.setLabourCd((String) tyreLabourRatesMap.get("labourCd"));
				suplyTyreLabourRatesObject.setTyreLabourTime((String) tyreLabourRatesMap.get("tyreLabourTime"));
				suplyTyreLabourRatesObject.setLabourTime((String) tyreLabourRatesMap.get(LABOUR_TIME));
				suplyTyreLabourRatesObject.setRate((BigDecimal) tyreLabourRatesMap.get("rate"));

				suplyTyreLabourRatesResponseList.add(suplyTyreLabourRatesObject);				
			}
			suplyWoObject.getEtsnTyreLabourRatesList().addAll(suplyTyreLabourRatesResponseList);
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
