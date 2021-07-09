package com.tip.estimation.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.estimation.model.EstimationLines;
import com.tip.estimation.model.EstnBundleDetails;
import com.tip.estimation.model.EstnBundles;
import com.tip.estimation.model.EstnNotesObject;
import com.tip.estimation.model.EstnPartDetails;
import com.tip.estimation.model.EstnWorkOrderObject;
import com.tip.estimation.model.EstnWorkOrderTaskObject;
import com.tip.estimation.repository.FetchEstimationLinesRepository;
import com.tip.estimation.service.FetchEstimationLinesService;

@Service
public class FetchEstimationLinesServiceImpl implements FetchEstimationLinesService {

	static final Logger logger = LoggerFactory.getLogger(FetchEstimationLinesServiceImpl.class);

	@Autowired
	FetchEstimationLinesRepository fetchEstimationLinesRepository;
	
	public static final String ESTN_ID = "estimationId";
	public static final String ESTN_WO_ID = "estnWOId";
	public static final String ESTN_WOT_ID = "estnWOTId";
	public static final String SUPPLIER_ID = "supplierId";
	public static final String GRP_CD_DESC = "groupCdDesc";
	public static final String SHOW_HIDE = "showHide";
	public static final String BUNDLE_NAME = "bundleName";

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public EstimationLines fetchEstLines(EstimationLines estnLines) {

		Map<String, Object> returnMap = fetchEstimationLinesRepository.fetchEstLines(estnLines);
		EstimationLines estimationLines = new EstimationLines();
		List<EstnWorkOrderObject> workOrderResponseList = new ArrayList();
		List<EstnPartDetails> umPartDetailsResponseList = new ArrayList();
		List<EstnNotesObject> estnNotesObjectResponseList = new ArrayList();

		if (null != returnMap) {
			
			List<Map<String, Object>> workOrderList = (List<Map<String, Object>>) returnMap.get("#result-set-1");
			List<Map<String, Object>> workOrderTaskList =(List<Map<String, Object>>) returnMap.get("#result-set-2");
			List<Map<String, Object>> partsDetailsList = (List<Map<String, Object>>) returnMap.get("#result-set-3");
			List<Map<String, Object>> bundleDetailsList = (List<Map<String, Object>>) returnMap.get("#result-set-4");
			List<Map<String, Object>> estnNotesList = (List<Map<String, Object>>) returnMap.get("#result-set-5");
			
			setDataEstnWorkOrderList(workOrderList, workOrderResponseList,partsDetailsList, estimationLines);
			setDataEstnWorkOrderTaskList(workOrderTaskList, workOrderResponseList);
			setUnMatchedPartsList(partsDetailsList, umPartDetailsResponseList);
			setDataEstnBundleList(bundleDetailsList, estimationLines);
			setDataEstnNotes(estnNotesList, estnNotesObjectResponseList, estimationLines);
			
			
			estimationLines.setEstimationId((BigDecimal) estnLines.getEstimationId());
			estimationLines.setLangId((Integer) estnLines.getLangId());
			estimationLines.setEstnUnmatchedPartList(umPartDetailsResponseList);
			
		}
		return estimationLines;
	}

	

	private void setDataEstnWorkOrderList(List<Map<String, Object>> workOrderList, List<EstnWorkOrderObject> workOrderResponseList, List<Map<String, Object>> partsDetailsList, EstimationLines estimationLines) {
		for (int i = 0; i < workOrderList.size(); i++) {
			Map<String, Object> workOrderObjectMap = workOrderList.get(i);
			EstnWorkOrderObject workOrderResponse = new EstnWorkOrderObject();
			workOrderResponse.setEstimationId((BigDecimal) workOrderObjectMap.get(ESTN_ID));
			workOrderResponse.setEstnWoId((Integer) workOrderObjectMap.get(ESTN_WO_ID));
			workOrderResponse.setSupplierId((Integer) workOrderObjectMap.get(SUPPLIER_ID));
			workOrderResponse.setSupplierName((String) workOrderObjectMap.get("supplierName"));
			workOrderResponse.setGroupCd((String) workOrderObjectMap.get("groupCd"));
			workOrderResponse.setGroupCdDesc((String) workOrderObjectMap.get(GRP_CD_DESC));
			workOrderResponse.setEstnWotCnt((Integer) workOrderObjectMap.get("estnWotCnt"));
			workOrderResponse.setEstnPartsCnt((Integer) workOrderObjectMap.get("estnPartsCnt"));
			workOrderResponse.setWoIntComm((String) workOrderObjectMap.get("woIntComm"));
			workOrderResponse.setWoCustComm((String) workOrderObjectMap.get("woCustComm"));
			workOrderResponse.setTotTagetTime((Double) workOrderObjectMap.get("totTagetTime"));
			
			setMatchedPartsList(partsDetailsList, workOrderResponse);
			workOrderResponseList.add(workOrderResponse);
			
		}
		estimationLines.setEstnWorkOrderList(workOrderResponseList);

	}

	
	private void setDataEstnWorkOrderTaskList(List<Map<String, Object>> workOrderTaskList,
			List<EstnWorkOrderObject> workOrderResponseList) {

		for (EstnWorkOrderObject workOrderResponse : workOrderResponseList) {
			List<EstnWorkOrderTaskObject> wotResponseList = new ArrayList();
			for (int i = 0; i < workOrderTaskList.size(); i++) {
				Map<String, Object> workOrderTaskObjectMap = workOrderTaskList.get(i);
				Integer estnWoId = (Integer) workOrderTaskObjectMap.get(ESTN_WO_ID);
				EstnWorkOrderTaskObject wotResponse = new EstnWorkOrderTaskObject();
				if (estnWoId.equals(workOrderResponse.getEstnWoId())) {
					wotResponse.setEstimationId((BigDecimal) workOrderTaskObjectMap.get(ESTN_ID));
					wotResponse.setEstnWoId((Integer) workOrderTaskObjectMap.get(ESTN_WO_ID));
					wotResponse.setEstnWotId((Integer) workOrderTaskObjectMap.get(ESTN_WOT_ID));
					wotResponse.setGroupCd((String) workOrderTaskObjectMap.get("groupCd"));
					wotResponse.setSubgroupCd((String) workOrderTaskObjectMap.get("subgroupCd"));
					wotResponse.setActivityCd((String) workOrderTaskObjectMap.get("activityCd"));
					wotResponse.setFailureCauseCd((String) workOrderTaskObjectMap.get("failureCauseCd"));
					wotResponse.setActionCd((String) workOrderTaskObjectMap.get("actionCd"));
					wotResponse.setPositionCd((String) workOrderTaskObjectMap.get("positionCd"));
					wotResponse.setTargetTime((Double) workOrderTaskObjectMap.get("targetTime"));
					wotResponse.setMaintenanceReason((String) workOrderTaskObjectMap.get("MaintenanceReason"));
					wotResponse.setGroupCdDesc((String) workOrderTaskObjectMap.get(GRP_CD_DESC));
					wotResponse.setSubgroup((String) workOrderTaskObjectMap.get("subgroup"));
					wotResponse.setActivity((String) workOrderTaskObjectMap.get("activity"));
					wotResponse.setFailureCause((String) workOrderTaskObjectMap.get("failureCause"));
					wotResponse.setAction((String) workOrderTaskObjectMap.get("action"));
					wotResponse.setReason((String) workOrderTaskObjectMap.get("reason"));
					wotResponse.setPosition((String) workOrderTaskObjectMap.get("position"));
					wotResponse.setSoldTime((Double) workOrderTaskObjectMap.get("soldTime"));
					wotResponse.setLabourRate((BigDecimal) workOrderTaskObjectMap.get("labourRate"));
					wotResponse.setLabourTime((String) workOrderTaskObjectMap.get("labourTime"));
					wotResponse.setShowHide((String) workOrderTaskObjectMap.get(SHOW_HIDE));
					wotResponse.setTaskComments((String) workOrderTaskObjectMap.get("taskComments"));
					wotResponse.setWshpWpNr((BigDecimal) workOrderTaskObjectMap.get("wshpWPNr"));
					wotResponse.setWshpWoNr((Integer) workOrderTaskObjectMap.get("wshpWONr"));
					wotResponse.setWshpWotNr((Integer) workOrderTaskObjectMap.get("wshpWOTNr"));
					
					wotResponseList.add(wotResponse);

				}
			}
			workOrderResponse.setEstnWorkOrderTaskList(wotResponseList);
		}

	}
	
	private void setMatchedPartsList(List<Map<String, Object>> partsDetailsList, EstnWorkOrderObject workOrderResponse) {

		for (Map<String, Object> partsMap : partsDetailsList) {
			Integer estnWoIdPart = (Integer) partsMap.get(ESTN_WO_ID);
			Integer estnWotIdPart = (Integer) partsMap.get(ESTN_WOT_ID);

			EstnPartDetails partDetailsResponse = new EstnPartDetails();
			if(estnWotIdPart!=null && !estnWotIdPart.equals(0)) {
				if (estnWoIdPart.equals(workOrderResponse.getEstnWoId())) {

					partDetailsResponse.setEstimationId((BigDecimal) partsMap.get(ESTN_ID));
					partDetailsResponse.setSupplierId((Integer) partsMap.get(SUPPLIER_ID));
					partDetailsResponse.setShowHide((String) partsMap.get(SHOW_HIDE));
					partDetailsResponse.setEstnWoId((Integer) partsMap.get(ESTN_WO_ID));
					partDetailsResponse.setEstnWotId((Integer) partsMap.get(ESTN_WOT_ID));
					partDetailsResponse.setPartNumber((String) partsMap.get("partNumber"));
					partDetailsResponse.setPartDesc((String) partsMap.get("partDesc"));
					partDetailsResponse.setCurrency((String) partsMap.get("currency"));
					partDetailsResponse.setQty((Integer) partsMap.get("qty"));
					partDetailsResponse.setCostToTip((BigDecimal) partsMap.get("costToTip"));
					partDetailsResponse.setRetailPrice((BigDecimal) partsMap.get("retailPrice"));
					partDetailsResponse.setDicount((BigDecimal) partsMap.get("dicount"));
			
					workOrderResponse.getEstnMatchedPartsList().add(partDetailsResponse);
				}
			}
		}

	}
	
	private void setUnMatchedPartsList(List<Map<String, Object>> partsDetailsList, List<EstnPartDetails> umPartDetailsResponseList) {
		
		for (Map<String, Object> partsMap : partsDetailsList) {
			Integer estnWotIdPart = (Integer) partsMap.get(ESTN_WOT_ID);

			EstnPartDetails partDetailsResponse = new EstnPartDetails();

			if(estnWotIdPart==null || estnWotIdPart==0){
				
				partDetailsResponse.setEstimationId((BigDecimal) partsMap.get(ESTN_ID));
				partDetailsResponse.setSupplierId((Integer) partsMap.get(SUPPLIER_ID));
				partDetailsResponse.setShowHide((String) partsMap.get(SHOW_HIDE));
				partDetailsResponse.setEstnWoId((Integer) partsMap.get(ESTN_WO_ID));
				partDetailsResponse.setEstnWotId((Integer) partsMap.get(ESTN_WOT_ID));
				partDetailsResponse.setPartNumber((String) partsMap.get("partNumber"));
				partDetailsResponse.setPartDesc((String) partsMap.get("partDesc"));
				partDetailsResponse.setCurrency((String) partsMap.get("currency"));
				partDetailsResponse.setQty((Integer) partsMap.get("qty"));
				partDetailsResponse.setCostToTip((BigDecimal) partsMap.get("costToTip"));
				partDetailsResponse.setRetailPrice((BigDecimal) partsMap.get("retailPrice"));
				partDetailsResponse.setDicount((BigDecimal) partsMap.get("dicount"));
				umPartDetailsResponseList.add(partDetailsResponse);
			
			}
		}

	}
	

	private void setDataEstnBundleList(List<Map<String, Object>> bundleDetailsList, EstimationLines estimationLines) {
		List<String> checkList = new ArrayList();
		for (int i = 0; i < bundleDetailsList.size(); i++) {
			Map<String, Object> bundleMap = bundleDetailsList.get(i);
			if (!checkList.contains(bundleMap.get(BUNDLE_NAME))) {
				EstnBundles bundleDetailsResponse = new EstnBundles();
				bundleDetailsResponse.setEstnbundleName((String) bundleMap.get(BUNDLE_NAME));
				checkList.add((String) bundleMap.get(BUNDLE_NAME));
				for (int j = 0; j < bundleDetailsList.size(); j++) {
					Map<String, Object> bundleMap1 = bundleDetailsList.get(j);
					EstnBundleDetails estnBundleDetails = new EstnBundleDetails();
					if (bundleDetailsResponse.getEstnbundleName()
							.equalsIgnoreCase((String) bundleMap1.get(BUNDLE_NAME))) {
						estnBundleDetails.setEstimationId((BigDecimal) bundleMap1.get("estimationId"));
						estnBundleDetails.setBundleName((String) bundleMap1.get(BUNDLE_NAME));
						estnBundleDetails.setDnaCode((String) bundleMap1.get("dnaCode"));
						estnBundleDetails.setGroupCdDesc((String) bundleMap1.get(GRP_CD_DESC));
						estnBundleDetails.setSubGroup((String) bundleMap1.get("subGroup"));
						estnBundleDetails.setActivity((String) bundleMap1.get("activity"));
						estnBundleDetails.setActionCd((String) bundleMap1.get("actionCd"));
						estnBundleDetails.setMaintenanceAction((String) bundleMap1.get("maintenanceAction"));
						estnBundleDetails.setManufacturerId((String) bundleMap1.get("manufacturerId"));
						estnBundleDetails.setManufacturer((String) bundleMap1.get("manufacturer"));
						estnBundleDetails.setSupplierPartNr((String) bundleMap1.get("supplierPartNr"));

						if (bundleMap1.get("fee") != null) {
							estnBundleDetails.setFee((BigDecimal) bundleMap1.get("fee"));
						}

						bundleDetailsResponse.getListOfBundle().add(estnBundleDetails);
					}

				}

				estimationLines.getEstnBundleList().add(bundleDetailsResponse);
			}
		}

	}
	
	
	private void setDataEstnNotes(List<Map<String, Object>> estnNotesList,List<EstnNotesObject> estnNotesObjectResponseList, EstimationLines estimationLines) {
		for (int i = 0; i < estnNotesList.size(); i++) {
			Map<String, Object> estnNotesObjectMap = estnNotesList.get(i);
			
			EstnNotesObject estnNotesObject = new EstnNotesObject();
			estnNotesObject.setEstimationtitle((String) estnNotesObjectMap.get("estimationtitle"));
			estnNotesObject.setNotesForCust((String) estnNotesObjectMap.get("notesForCust"));
			estnNotesObject.setNotesForInt((String) estnNotesObjectMap.get("notesForInt"));
			
			estnNotesObjectResponseList.add(estnNotesObject);
			estimationLines.setEstnNotesObjectList(estnNotesObjectResponseList);
			
	}
}
}
