package com.tip.estimation.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.estimation.model.DiscountMarkup;
import com.tip.estimation.model.EnrichConsumablesObject;
import com.tip.estimation.model.EnrichTyreServiceObject;
import com.tip.estimation.model.EnrichTyreSpecsObject;
import com.tip.estimation.model.EstnBundleLists;
import com.tip.estimation.model.EstnBundleObject;
import com.tip.estimation.model.EstnFeeList;
import com.tip.estimation.model.EstnHeaderObject;
import com.tip.estimation.model.EstnLabourRatesObject;
import com.tip.estimation.model.EstnPartLists;
import com.tip.estimation.model.EstnWoListObject;
import com.tip.estimation.model.EstnWotListObject;
import com.tip.estimation.model.EtsnTyreLabourRatesObject;
import com.tip.estimation.model.FetchEnrichDetailsRequest;
import com.tip.estimation.model.FetchEnrichDetailsResponse;
import com.tip.estimation.model.ManagementFees;
import com.tip.estimation.repository.FetchEstnEnrichDetailsRepository;
import com.tip.estimation.service.FetchEstnEnrichDetailsService;

@Service
public class FetchEstnEnrichDetailsServiceImpl implements FetchEstnEnrichDetailsService{
	
	static final Logger logger = LoggerFactory.getLogger(FetchEstnEnrichDetailsServiceImpl.class);
	
	boolean flag;
	
	@Autowired
	FetchEstnEnrichDetailsRepository fetchEstnEnrichDetailsRepository;
	
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
	public FetchEnrichDetailsResponse fetchEstEnrichDetails(FetchEnrichDetailsRequest fetchEnrichDetailsRequest) {
		Map<String, Object> returnMap = fetchEstnEnrichDetailsRepository.fetchEstEnrichDetails(fetchEnrichDetailsRequest);
		FetchEnrichDetailsResponse fetchEnrichDetailsResponse = new FetchEnrichDetailsResponse();
		
		EstnHeaderObject estnHeaderResponse = new EstnHeaderObject();
		List<EstnWoListObject> estnWoResponseList = new ArrayList();
		List<EstnPartLists> umEstnPartResponseList = new ArrayList();
		List<EstnLabourRatesObject> estnLabourRatesResponseList = new ArrayList();
		List<EtsnTyreLabourRatesObject> etsnTyreLabourRatesResponseList = new ArrayList();
		List<EnrichTyreSpecsObject> estnTyreSpecResponseList = new ArrayList();
		List<EnrichTyreServiceObject> estnTyreServiceResponseList = new ArrayList();
		List<EnrichConsumablesObject> estnConsumablesResponseList = new ArrayList();
		List<EstnFeeList> estnFeeResponseList = new ArrayList();
		
		
		if (null != returnMap) {
			List<Map<String, Object>> estnHeaderDetails = (List<Map<String, Object>>) returnMap.get("#result-set-1");
			List<Map<String, Object>> estnWoList = (List<Map<String, Object>>) returnMap.get("#result-set-2");
			List<Map<String, Object>> estnWorkOrderTaskList = (List<Map<String, Object>>) returnMap
					.get("#result-set-3");
			List<Map<String, Object>> estnSupplementary = (List<Map<String, Object>>) returnMap.get("#result-set-4");
			List<Map<String, Object>> estnPartLists = (List<Map<String, Object>>) returnMap.get("#result-set-5");
			List<Map<String, Object>> estnBundleLists = (List<Map<String, Object>>) returnMap.get("#result-set-6");
			List<Map<String, Object>> estnLabourRatesList = (List<Map<String, Object>>) returnMap.get("#result-set-7");
			List<Map<String, Object>> etsnTyreLabourRates = (List<Map<String, Object>>) returnMap.get("#result-set-8");
			List<Map<String, Object>> consumableList = (List<Map<String, Object>>) returnMap.get("#result-set-9");
			List<Map<String, Object>> tyreSpecList = (List<Map<String, Object>>) returnMap.get("#result-set-10");
			List<Map<String, Object>> tyreServiceList = (List<Map<String, Object>>) returnMap.get("#result-set-11");
			List<Map<String, Object>> estnFeeList = (List<Map<String, Object>>) returnMap.get("#result-set-12");
			List<Map<String, Object>> mangementFeeList = (List<Map<String, Object>>) returnMap.get("#result-set-13");
			List<Map<String, Object>> discountMarkupList = (List<Map<String, Object>>) returnMap
					.get("#result-set-14");

			setDataEstnHeaderList(estnHeaderDetails, estnHeaderResponse);
			setDataEstnWorkOrderList(estnWoList, estnPartLists, estnWoResponseList, estnHeaderResponse);

			setDataEstnWorkOrderTaskList(estnWorkOrderTaskList, estnWoResponseList);

			setUnMatchedPartsList(estnPartLists, umEstnPartResponseList);
			setDataEstnBundleList(estnBundleLists, fetchEnrichDetailsResponse);
			setDataEstnLabourRatesList(estnLabourRatesList, estnLabourRatesResponseList, estnWoResponseList);
			setDataEtsnTyreLabourRatesList(etsnTyreLabourRates, etsnTyreLabourRatesResponseList, estnWoResponseList);
			setDataEstnConsumableList(consumableList, estnConsumablesResponseList, fetchEnrichDetailsResponse);
			setDataEstnTyreSpecList(tyreSpecList, estnTyreSpecResponseList, fetchEnrichDetailsResponse);
			setDataEstnTyreServiceList(tyreServiceList, estnTyreServiceResponseList, fetchEnrichDetailsResponse);
			setDataEstnFeesDetails(estnFeeList, estnFeeResponseList, fetchEnrichDetailsResponse);
			setDataMngFees(mangementFeeList, fetchEnrichDetailsResponse);
			setDataDiscMarkup(discountMarkupList, fetchEnrichDetailsResponse);

			fetchEnrichDetailsResponse.setEstnHeaderList(estnHeaderResponse);

			fetchEnrichDetailsResponse.getEstnUnMatchedPartLists().addAll(umEstnPartResponseList);
		}

		return fetchEnrichDetailsResponse;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setDataDiscMarkup(List<Map<String, Object>> discountMarkupList,
			FetchEnrichDetailsResponse fetchEnrichDetailsResponse) {
		List<DiscountMarkup> listOfDiscMarkup = new ArrayList();
		discountMarkupList.forEach(mapObject->{
			DiscountMarkup discountMarkup = new DiscountMarkup();
			discountMarkup.setDscntMrkupDesc((String) mapObject.get("dscntMrkupDesc"));
			discountMarkup.setDscntMrkupGrpId((Integer) mapObject.get("dscntMrkupGrpId"));
			discountMarkup.setDscntMrkupItmId((Integer) mapObject.get("dscntMrkupItmId"));
			discountMarkup.setDscntMrkupSubGrpId((Integer) mapObject.get("dscntMrkupSubGrpId"));
			discountMarkup.setFee((BigDecimal) mapObject.get("fee"));
			discountMarkup.setFixedFee((BigDecimal) mapObject.get("fixedFee"));
			discountMarkup.setMaxfee((BigDecimal) mapObject.get("maxfee"));
			discountMarkup.setMinFee((BigDecimal) mapObject.get("minFee"));
			listOfDiscMarkup.add(discountMarkup);
		});
		fetchEnrichDetailsResponse.getDiscountMarkupList().addAll(listOfDiscMarkup);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setDataMngFees(List<Map<String, Object>> mangementFeeList,
			FetchEnrichDetailsResponse fetchEnrichDetailsResponse) {
		List<ManagementFees> listOfMngFees = new ArrayList();
		mangementFeeList.forEach(mapObject->{
			ManagementFees mFees = new ManagementFees();
			mFees.setFeePer((Integer) mapObject.get("feePer"));
			mFees.setFixedFee((BigDecimal) mapObject.get("fixedFee"));
			mFees.setMaxfee((BigDecimal) mapObject.get("maxfee"));
			mFees.setMinFee((BigDecimal) mapObject.get("minFee"));
			mFees.setTotEstAmtFrom((BigDecimal) mapObject.get("totEstAmtFrom"));
			mFees.setTotEstAmtTo((BigDecimal) mapObject.get("totEstAmtTo"));
			listOfMngFees.add(mFees);
		});
		fetchEnrichDetailsResponse.getMangementFeesList().addAll(listOfMngFees);
	}


	private void setDataEstnHeaderList(List<Map<String, Object>> estnHeaderList,
			EstnHeaderObject estnHeaderResponse) {
		for (int i = 0; i < estnHeaderList.size(); i++) {
			Map<String, Object> estnHeaderMap = estnHeaderList.get(i);
			
			estnHeaderResponse.setEstimationId((BigDecimal) estnHeaderMap.get(ESTN_ID));
			estnHeaderResponse.setEstnTitle((String) estnHeaderMap.get("estnTitle"));
			estnHeaderResponse.setCustNotes((String) estnHeaderMap.get("custNotes"));
			estnHeaderResponse.setInternalNotes((String) estnHeaderMap.get("internalNotes"));
			estnHeaderResponse.setCurrency((String) estnHeaderMap.get(CURRENCY));
			estnHeaderResponse.setVat((BigDecimal) estnHeaderMap.get("vat"));
			estnHeaderResponse.setVatValue((BigDecimal) estnHeaderMap.get("vatRate"));			
			estnHeaderResponse.setConcession((BigDecimal) estnHeaderMap.get("concession"));
			estnHeaderResponse.setEstnStatusId((Integer) estnHeaderMap.get("estnStatusId"));
			estnHeaderResponse.setEstnStatus((String) estnHeaderMap.get("estnStatus"));
			estnHeaderResponse.setVersion((Integer) estnHeaderMap.get(VERSION));
			estnHeaderResponse.setSupplementary((Integer) estnHeaderMap.get(SUPPLEMENTARY));
			estnHeaderResponse.setWpNumber((BigDecimal) estnHeaderMap.get("wpNumber"));
			estnHeaderResponse.setAssetNumber((Integer) estnHeaderMap.get("assetNumber"));
			estnHeaderResponse.setCompanyNr((Integer) estnHeaderMap.get("companyNr"));
			estnHeaderResponse.setShowHide(FetchEstnEnrichDetailsServiceImpl.checkValue(estnHeaderMap.get(SHOW_HIDE)));
			
		}
	}
	
		
	private void setDataEstnWorkOrderList(List<Map<String, Object>> estnWoList,
			List<Map<String, Object>> estnPartLists, List<EstnWoListObject> estnWoResponseList, EstnHeaderObject estnHeaderResponse) {

			for (int i = 0; i < estnWoList.size(); i++) {
				
				Map<String, Object> estnWoListObjectMap = estnWoList.get(i);
				EstnWoListObject estnWoListResponse = new EstnWoListObject(); 

				estnWoListResponse.setEstimationId((BigDecimal) estnWoListObjectMap.get(ESTN_ID));
				estnWoListResponse.setEstnWOId((Integer) estnWoListObjectMap.get(ESTN_WO_ID));
				estnWoListResponse.setSupplierId((Integer) estnWoListObjectMap.get(SUPPLIER_ID));
				estnWoListResponse.setSupplierName((String) estnWoListObjectMap.get("supplierName"));
				estnWoListResponse.setGroupCd((String) estnWoListObjectMap.get("groupCd"));
				estnWoListResponse.setGroupCdDesc((String) estnWoListObjectMap.get(GRP_CD_DESC));
				estnWoListResponse.setLoTaskCount((Integer) estnWoListObjectMap.get("estnWotCnt"));
				estnWoListResponse.setPartsCount((Integer) estnWoListObjectMap.get("estnPartsCnt"));
				estnWoListResponse.setEstnTyreCnt((Integer) estnWoListObjectMap.get("estnTyreCnt"));
				estnWoListResponse.setloCommentsInternal((String) estnWoListObjectMap.get("woIntComm"));
				estnWoListResponse.setLoCommentsCustomers((String) estnWoListObjectMap.get("woCustComm"));
				estnWoListResponse.setTotTagetTime((Double) estnWoListObjectMap.get("totTagetTime"));
				estnWoListResponse.setLabourTime((String) estnWoListObjectMap.get(LABOUR_TIME));

				setMatchedPartsList(estnPartLists, estnWoListResponse);
				
				estnWoResponseList.add(estnWoListResponse);					
			}
			estnHeaderResponse.getEstnWoList().addAll(estnWoResponseList);
		}
	


	private void setDataEstnWorkOrderTaskList(List<Map<String, Object>> estnWorkOrderTaskList, List<EstnWoListObject> estnWoResponseList) {

		for (EstnWoListObject estnWoListResponse : estnWoResponseList) {
			List<EstnWotListObject> estnWotResponseList = new ArrayList();
			for (int i = 0; i < estnWorkOrderTaskList.size(); i++) {
				Map<String, Object> estnWotListObjectMap = estnWorkOrderTaskList.get(i);
				Integer estnWoId = (Integer) estnWotListObjectMap.get(ESTN_WO_ID);
				EstnWotListObject estnWotResponse = new EstnWotListObject();
				if (estnWoId.equals(estnWoListResponse.getEstnWOId())) {
					estnWotResponse.setEstimationId((BigDecimal) estnWotListObjectMap.get(ESTN_ID));
					estnWotResponse.setEstnWOId((Integer) estnWotListObjectMap.get(ESTN_WO_ID));
					estnWotResponse.setEstnWOTId((Integer) estnWotListObjectMap.get(ESTN_WOT_ID));
					estnWotResponse.setGroupCd((String) estnWotListObjectMap.get("groupCd"));
					estnWotResponse.setSubgroupCd((String) estnWotListObjectMap.get("subgroupCd"));
					estnWotResponse.setActivityCd((String) estnWotListObjectMap.get("activityCd"));
					estnWotResponse.setFailureCauseCd((String) estnWotListObjectMap.get("failureCauseCd"));
					estnWotResponse.setActionCd((String) estnWotListObjectMap.get("actionCd"));
					estnWotResponse.setPositionCd((String) estnWotListObjectMap.get("positionCd"));
					estnWotResponse.setTargetTime((Object) estnWotListObjectMap.get("targetTime"));
					estnWotResponse.setReasonCd((String) estnWotListObjectMap.get("reasonCd"));
					estnWotResponse.setGroupCdDesc((String) estnWotListObjectMap.get(GRP_CD_DESC));
					estnWotResponse.setSubGroup((String) estnWotListObjectMap.get("subGroup"));
					estnWotResponse.setActivity((String) estnWotListObjectMap.get("activity"));
					estnWotResponse.setFailureCause((String) estnWotListObjectMap.get("failureCause"));
					estnWotResponse.setAction((String) estnWotListObjectMap.get("action"));
					estnWotResponse.setReason((String) estnWotListObjectMap.get("reason"));
					estnWotResponse.setPosition((String) estnWotListObjectMap.get("position"));
					estnWotResponse.setSoldTime((Double) estnWotListObjectMap.get("soldTime"));
					estnWotResponse.setLabourRate((BigDecimal) estnWotListObjectMap.get("labourRate"));
					estnWotResponse.setLabourTime((String) estnWotListObjectMap.get(LABOUR_TIME));
					estnWotResponse.setTaskComments((String) estnWotListObjectMap.get("taskComments"));
					estnWotResponse.setWshpWPNr((BigDecimal) estnWotListObjectMap.get("wshpWPNr"));
					estnWotResponse.setWshpWONr((Integer) estnWotListObjectMap.get("wshpWONr"));
					estnWotResponse.setWshpWOTNr((Integer) estnWotListObjectMap.get("wshpWOTNr"));
					estnWotResponse.setIsApproved((String) estnWotListObjectMap.get(IS_APPROVED));
					estnWotResponse.setIsRejected((String) estnWotListObjectMap.get(IS_REJECTED));
					estnWotResponse.setAttribute(FetchEstnEnrichDetailsServiceImpl.checkValue(estnWotListObjectMap.get("addAttribute")));
					estnWotResponse.setShowHide(FetchEstnEnrichDetailsServiceImpl.checkValue(estnWotListObjectMap.get(SHOW_HIDE)));

					estnWotResponseList.add(estnWotResponse);
				}

			}

			estnWoListResponse.getEstnWotList().addAll(estnWotResponseList);
		}
	}

private void setMatchedPartsList(List<Map<String, Object>> estnPartLists, EstnWoListObject estnWoListResponse) {
		
		for (Map<String, Object> partsMap : estnPartLists) {
			Integer estnWoIdPart = (Integer) partsMap.get(ESTN_WO_ID);
			
			EstnPartLists estnPartListsResponse = new EstnPartLists();
			if(estnWoIdPart!=null && !estnWoIdPart.equals(0)) {
				if (estnWoIdPart.equals(estnWoListResponse.getEstnWOId())) {
					estnPartListsResponse.setEstimationId((BigDecimal) partsMap.get(ESTN_ID));
					estnPartListsResponse.setPartNumber((String) partsMap.get("partNumber"));
					estnPartListsResponse.setPartDesc((String) partsMap.get("partDesc"));
					estnPartListsResponse.setSupplierId((Integer) partsMap.get(SUPPLIER_ID));
					estnPartListsResponse.setCurrency((String) partsMap.get(CURRENCY));
					estnPartListsResponse.setQuantity((Integer) partsMap.get("qty"));
					estnPartListsResponse.setCostToTip((BigDecimal) partsMap.get("costToTip"));
					estnPartListsResponse.setCostPlus((BigDecimal) partsMap.get("costPlus"));
					estnPartListsResponse.setRetailPrice((BigDecimal) partsMap.get("retailPrice"));
					estnPartListsResponse.setDicount((BigDecimal) partsMap.get("dicount"));
					estnPartListsResponse.setEstnWOId((Integer) partsMap.get(ESTN_WO_ID));
					estnPartListsResponse.setEstnWOTId((Integer) partsMap.get(ESTN_WOT_ID));
					estnPartListsResponse.setIsApproved((String) partsMap.get(IS_APPROVED));
					estnPartListsResponse.setIsRejected((String) partsMap.get(IS_REJECTED));
					estnPartListsResponse.setFixedPrice((BigDecimal) partsMap.get("fixedPrice"));
					estnPartListsResponse.setShowHide(FetchEstnEnrichDetailsServiceImpl.checkValue(partsMap.get(SHOW_HIDE)));

					estnWoListResponse.getEstnMatchedPartLists().add(estnPartListsResponse);

				}

			}
		}
	}

	private void setUnMatchedPartsList(List<Map<String, Object>> estnPartLists,
			List<EstnPartLists> umEstnPartResponseList){
			
		for (Map<String, Object> partsMap : estnPartLists) {
			Integer estnWoIdPart = (Integer) partsMap.get(ESTN_WO_ID);
			
				EstnPartLists estnPartListsResponse = new EstnPartLists();

				if (estnWoIdPart == null || estnWoIdPart == 0) {

					estnPartListsResponse.setEstimationId((BigDecimal) partsMap.get(ESTN_ID));
					estnPartListsResponse.setPartNumber((String) partsMap.get("partNumber"));
					estnPartListsResponse.setPartDesc((String) partsMap.get("partDesc"));
					estnPartListsResponse.setSupplierId((Integer) partsMap.get(SUPPLIER_ID));
					estnPartListsResponse.setCurrency((String) partsMap.get(CURRENCY));
					estnPartListsResponse.setQuantity((Integer) partsMap.get("qty"));
					estnPartListsResponse.setCostToTip((BigDecimal) partsMap.get("costToTip"));
					estnPartListsResponse.setCostPlus((BigDecimal) partsMap.get("costPlus"));
					estnPartListsResponse.setRetailPrice((BigDecimal) partsMap.get("retailPrice"));
					estnPartListsResponse.setDicount((BigDecimal) partsMap.get("dicount"));					
					estnPartListsResponse.setEstnWOId((Integer) partsMap.get(ESTN_WO_ID));
					estnPartListsResponse.setEstnWOTId((Integer) partsMap.get(ESTN_WOT_ID));
					estnPartListsResponse.setIsApproved((String) partsMap.get(IS_APPROVED));
					estnPartListsResponse.setIsRejected((String) partsMap.get(IS_REJECTED));
					estnPartListsResponse.setFixedPrice((BigDecimal) partsMap.get("fixedPrice"));
					estnPartListsResponse.setShowHide(FetchEstnEnrichDetailsServiceImpl.checkValue(partsMap.get(SHOW_HIDE)));
							
					umEstnPartResponseList.add(estnPartListsResponse);
			}
		}

	}

	private void setDataEstnBundleList(List<Map<String, Object>> estnBundleLists,
			FetchEnrichDetailsResponse fetchEnrichDetailsResponse) {
		List<String> checkList = new ArrayList();
		List<EstnBundleLists> listResponse = new ArrayList<EstnBundleLists>();
		for (int i = 0; i < estnBundleLists.size(); i++) {
			Map<String, Object> bundleMap = estnBundleLists.get(i);
			if (!checkList.contains(bundleMap.get(BUNDLE_NAME))) {
				EstnBundleLists estnBundleResponse =new EstnBundleLists();
				estnBundleResponse.setBundleName((String) bundleMap.get(BUNDLE_NAME));
				checkList.add((String) bundleMap.get(BUNDLE_NAME));
		estnBundleResponse.setShowHide(FetchEstnEnrichDetailsServiceImpl.checkValue(bundleMap.get(SHOW_HIDE)));				
				
		estnBundleResponse.setIsApproved((String) bundleMap.get(IS_APPROVED));
				estnBundleResponse.setIsRejected((String) bundleMap.get(IS_REJECTED));
				
				for (int j = 0; j < estnBundleLists.size(); j++) {
					Map<String, Object> bundleMap1 = estnBundleLists.get(j);
					EstnBundleObject estnBundleDetails = new EstnBundleObject();
					if (estnBundleResponse.getBundleName().equalsIgnoreCase((String) bundleMap1.get(BUNDLE_NAME))) {
						estnBundleDetails.setEstimationId((BigDecimal) bundleMap1.get(ESTN_ID));
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

						estnBundleResponse.getListOfBundle().add(estnBundleDetails);
						
					}

				}
				listResponse.add(estnBundleResponse);

				fetchEnrichDetailsResponse.getEstnBundleLists().addAll(listResponse);

			}
		}

	}
	

	private void setDataEstnConsumableList(List<Map<String, Object>> consumableList,
			List<EnrichConsumablesObject> estnConsumablesResponseList,
			FetchEnrichDetailsResponse fetchEnrichDetailsResponse) {
		
		for (int i = 0; i < consumableList.size(); i++) {
			Map<String, Object> estnCnsumbleObjectMap = consumableList.get(i);
			
			EnrichConsumablesObject enrichConsumablesObject = new EnrichConsumablesObject();
			enrichConsumablesObject.setEstimationId((BigDecimal) estnCnsumbleObjectMap.get(ESTN_ID));
			enrichConsumablesObject.setConsmblegroupId((Integer) estnCnsumbleObjectMap.get("consmblegroupId"));
			enrichConsumablesObject.setConsmblesubGroupId((Integer) estnCnsumbleObjectMap.get("consmblesubGroupId"));
			enrichConsumablesObject.setConsmbleitemId((Integer) estnCnsumbleObjectMap.get("consmbleitemId"));
			enrichConsumablesObject.setVersion((Integer) estnCnsumbleObjectMap.get(VERSION));
			enrichConsumablesObject.setSupplementary((Integer) estnCnsumbleObjectMap.get(SUPPLEMENTARY));
			enrichConsumablesObject.setMinAmount((BigDecimal) estnCnsumbleObjectMap.get("minAmount"));
			enrichConsumablesObject.setMaxAmount((BigDecimal) estnCnsumbleObjectMap.get("maxAmount"));
			enrichConsumablesObject.setPercentage((BigDecimal) estnCnsumbleObjectMap.get("percentage"));
			enrichConsumablesObject.setFee((BigDecimal) estnCnsumbleObjectMap.get("fee"));
			enrichConsumablesObject.setCharges((BigDecimal) estnCnsumbleObjectMap.get(CHARGES));			
			enrichConsumablesObject.setIsApproved((String) estnCnsumbleObjectMap.get(IS_APPROVED));
			enrichConsumablesObject.setIsRejected((String) estnCnsumbleObjectMap.get(IS_REJECTED));
			enrichConsumablesObject.setRejectedReason((Integer) estnCnsumbleObjectMap.get(REJECTED));
			enrichConsumablesObject.setSsoId((String) estnCnsumbleObjectMap.get(SSOID));
			enrichConsumablesObject.setConsumables((String) estnCnsumbleObjectMap.get("consumables"));
			enrichConsumablesObject.setShowHide(FetchEstnEnrichDetailsServiceImpl.checkValue(estnCnsumbleObjectMap.get(SHOW_HIDE)));
					
			estnConsumablesResponseList.add(enrichConsumablesObject);
			fetchEnrichDetailsResponse.setEstnConsumablesDetails(estnConsumablesResponseList);

		}

	}

	private void setDataEstnTyreSpecList(List<Map<String, Object>> tyreSpecList,
			List<EnrichTyreSpecsObject> estnTyreSpecResponseList,
			FetchEnrichDetailsResponse fetchEnrichDetailsResponse) {

		for (int i = 0; i < tyreSpecList.size(); i++) {
			Map<String, Object> estnTyreSpecObjectMap = tyreSpecList.get(i);
		
			EnrichTyreSpecsObject enrichTyreSpecsObject = new EnrichTyreSpecsObject();
			enrichTyreSpecsObject.setEstimationId((BigDecimal) estnTyreSpecObjectMap.get(ESTN_ID));
			enrichTyreSpecsObject.setEstnWOId((Integer) estnTyreSpecObjectMap.get(ESTN_WO_ID));
			enrichTyreSpecsObject.setEstnWOTId((Integer) estnTyreSpecObjectMap.get(ESTN_WOT_ID));
			enrichTyreSpecsObject.setTyreSpecgrpId((Integer) estnTyreSpecObjectMap.get("tyreSpecgrpId"));
			enrichTyreSpecsObject.setTyreSpecsubgrpId((Integer) estnTyreSpecObjectMap.get("tyreSpecsubgrpId"));
			enrichTyreSpecsObject.setTyreSpecItemId((Integer) estnTyreSpecObjectMap.get("tyreSpecItemId"));
			enrichTyreSpecsObject.setTyreSize((String) estnTyreSpecObjectMap.get("tyreSize"));
			enrichTyreSpecsObject.setApplication((Integer) estnTyreSpecObjectMap.get("application"));
			enrichTyreSpecsObject.setBrand((String) estnTyreSpecObjectMap.get("brand"));
			enrichTyreSpecsObject.setTyreStatus((String) estnTyreSpecObjectMap.get("tyreStatus"));
			enrichTyreSpecsObject.setChargeType((String) estnTyreSpecObjectMap.get("chargeType"));
			enrichTyreSpecsObject.setLostMM((Integer) estnTyreSpecObjectMap.get("lostMM"));
			enrichTyreSpecsObject.setVersion((Integer) estnTyreSpecObjectMap.get(VERSION));
			enrichTyreSpecsObject.setSupplementary((Integer) estnTyreSpecObjectMap.get(SUPPLEMENTARY));
			enrichTyreSpecsObject.setCharges((BigDecimal) estnTyreSpecObjectMap.get(CHARGES));			
			enrichTyreSpecsObject.setIsApproved((String) estnTyreSpecObjectMap.get(IS_APPROVED));
			enrichTyreSpecsObject.setIsRejected((String) estnTyreSpecObjectMap.get(IS_REJECTED));
			enrichTyreSpecsObject.setRejectedReason((Integer) estnTyreSpecObjectMap.get(REJECTED));
			enrichTyreSpecsObject.setSsoId((String) estnTyreSpecObjectMap.get(SSOID));
			
			enrichTyreSpecsObject.setShowHide(FetchEstnEnrichDetailsServiceImpl.checkValue(estnTyreSpecObjectMap.get(SHOW_HIDE)));

			estnTyreSpecResponseList.add(enrichTyreSpecsObject);
			fetchEnrichDetailsResponse.setTyreSpeclist(estnTyreSpecResponseList);

		}

	}

	private void setDataEstnTyreServiceList(List<Map<String, Object>> tyreServiceList,
			List<EnrichTyreServiceObject> estnTyreServiceResponseList,
			FetchEnrichDetailsResponse fetchEnrichDetailsResponse) {


		for (int i = 0; i < tyreServiceList.size(); i++) {
			Map<String, Object> estnTyreServiceObjectMap = tyreServiceList.get(i);
		
			EnrichTyreServiceObject enrichTyreServiceObject = new EnrichTyreServiceObject();
			
			enrichTyreServiceObject.setEstimationId((BigDecimal) estnTyreServiceObjectMap.get(ESTN_ID));
			enrichTyreServiceObject.setEstnWOId((Integer) estnTyreServiceObjectMap.get(ESTN_WO_ID));
			enrichTyreServiceObject.setEstnWOTId((Integer) estnTyreServiceObjectMap.get(ESTN_WOT_ID));
			enrichTyreServiceObject.setTyreServicegrpId((Integer) estnTyreServiceObjectMap.get("tyreServicegrpId"));
			enrichTyreServiceObject.setTyreServicesubgrpId((Integer) estnTyreServiceObjectMap.get("tyreServicesubgrpId"));
			enrichTyreServiceObject.setTyreServiceItemId((Integer) estnTyreServiceObjectMap.get("tyreServiceItemId"));
			enrichTyreServiceObject.setVersion((Integer) estnTyreServiceObjectMap.get(VERSION));
			enrichTyreServiceObject.setSupplementary((Integer) estnTyreServiceObjectMap.get(SUPPLEMENTARY));
			enrichTyreServiceObject.setCharges((Double) estnTyreServiceObjectMap.get(CHARGES));
			enrichTyreServiceObject.setIsApproved((String) estnTyreServiceObjectMap.get(IS_APPROVED));
			enrichTyreServiceObject.setIsRejected((String) estnTyreServiceObjectMap.get(IS_REJECTED));
			enrichTyreServiceObject.setRejectedReason((Integer) estnTyreServiceObjectMap.get(REJECTED));
			enrichTyreServiceObject.setSsoId((String) estnTyreServiceObjectMap.get(SSOID));
			enrichTyreServiceObject.setShowHide(FetchEstnEnrichDetailsServiceImpl.checkValue(estnTyreServiceObjectMap.get(SHOW_HIDE)));
			
			estnTyreServiceResponseList.add(enrichTyreServiceObject);
			fetchEnrichDetailsResponse.setTyreServicelist(estnTyreServiceResponseList);
		}
	}


	private void setDataEstnLabourRatesList(List<Map<String, Object>> estnLabourRatesList,
			List<EstnLabourRatesObject> estnLabourRatesResponseList, List<EstnWoListObject> estnWoResponseList) {
		
		for (EstnWoListObject estnWoListResponse : estnWoResponseList) {

			for (int i = 0; i < estnLabourRatesList.size(); i++) {
				Map<String, Object> etsnLabourRatesMap = estnLabourRatesList.get(i);
				EstnLabourRatesObject etsnLabourRatesResponse = new EstnLabourRatesObject();

				etsnLabourRatesResponse.setLabourTime((String) etsnLabourRatesMap.get(LABOUR_TIME));
				etsnLabourRatesResponse.setRate((BigDecimal) etsnLabourRatesMap.get("rate"));

				estnLabourRatesResponseList.add(etsnLabourRatesResponse);	
			}
			estnWoListResponse.getEstnLabourRatesList().addAll(estnLabourRatesResponseList);
		}
		
	}

	private void setDataEtsnTyreLabourRatesList(List<Map<String, Object>> etsnTyreLabourRates,
			List<EtsnTyreLabourRatesObject> etsnTyreLabourRatesResponseList,
			List<EstnWoListObject> estnWoResponseList) {

		for (EstnWoListObject estnWoListResponse : estnWoResponseList) {

			for (int i = 0; i < etsnTyreLabourRates.size(); i++) {
				Map<String, Object> etsnTyreLabourRatesMap = etsnTyreLabourRates.get(i);
				EtsnTyreLabourRatesObject etsnTyreLabourRatesResponse = new EtsnTyreLabourRatesObject();

				etsnTyreLabourRatesResponse.setLabourCd((String) etsnTyreLabourRatesMap.get("labourCd"));
				etsnTyreLabourRatesResponse.setTyreLabourTime((String) etsnTyreLabourRatesMap.get("tyreLabourTime"));
				etsnTyreLabourRatesResponse.setLabourTime((String) etsnTyreLabourRatesMap.get(LABOUR_TIME));
				etsnTyreLabourRatesResponse.setRate((BigDecimal) etsnTyreLabourRatesMap.get("rate"));

				etsnTyreLabourRatesResponseList.add(etsnTyreLabourRatesResponse);				
			}
			estnWoListResponse.getEtsnTyreLabourRatesList().addAll(etsnTyreLabourRatesResponseList);
		}
	}
	
	private void setDataEstnFeesDetails(List<Map<String, Object>> estnFeeList, List<EstnFeeList> estnFeeResponseList, FetchEnrichDetailsResponse fetchEnrichDetailsResponse) {
		for (int i = 0; i < estnFeeList.size(); i++) {
			Map<String, Object> estnFeeObjectMap = estnFeeList.get(i);
		
			EstnFeeList estnFeeObject = new EstnFeeList();
			
			estnFeeObject.setFeesgroupId((Integer) estnFeeObjectMap.get("feesgroupId"));
			estnFeeObject.setFeessubgroupId((Integer) estnFeeObjectMap.get("feessubgroupId"));
			estnFeeObject.setFeesitemId((Integer) estnFeeObjectMap.get("feesitemId"));
			estnFeeObject.setFeeDesc((String) estnFeeObjectMap.get("feeDesc"));
			estnFeeObject.setFee((Object) estnFeeObjectMap.get("fee"));
			estnFeeObject.setCharges((Object) estnFeeObjectMap.get(CHARGES));
			estnFeeObject.setIsApproved((String) estnFeeObjectMap.get(IS_APPROVED));
			estnFeeObject.setIsRejected((String) estnFeeObjectMap.get(IS_REJECTED));
			
			estnFeeObject.setShowHide(FetchEstnEnrichDetailsServiceImpl.checkValue(estnFeeObjectMap.get(SHOW_HIDE)));
			
			estnFeeResponseList.add(estnFeeObject);
			fetchEnrichDetailsResponse.setEstnFeeList(estnFeeResponseList);
		}
	}
		
	

	
	public static Boolean checkValue(Object value){
		Boolean flag = null;
		if(value!=null) {
			if("Y".equalsIgnoreCase(value.toString())){
				flag = true;
			}else{
				flag = false;
			}
		}
		return flag;
	}
}
