package com.tip.estimation.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.estimation.model.Consumables;
import com.tip.estimation.model.HeaderData;
import com.tip.estimation.model.InvoiceRequest;
import com.tip.estimation.model.InvoiceResponse;
import com.tip.estimation.model.InvoiceTyreService;
import com.tip.estimation.model.Part;
import com.tip.estimation.model.Task;
import com.tip.estimation.model.Tyre;
import com.tip.estimation.model.Workorder;
import com.tip.estimation.repository.ImmediateInvoiceRepository;
import com.tip.estimation.service.ImmediateInvoiceService;

@Service
@Transactional
public class ImmediateInvoiceServiceImpl implements ImmediateInvoiceService {
	
	@Autowired
	ImmediateInvoiceRepository immediateInvoiceRepository;

	@SuppressWarnings("unchecked")
	@Override
	public InvoiceResponse getInvoiceData(InvoiceRequest invoiceRequest){

		InvoiceResponse baseResponse = new InvoiceResponse();
		Map<String, Object> resultMap = immediateInvoiceRepository.getInvoiceData(invoiceRequest);
		List<Object> headerDataList = (List<Object>) resultMap.get("HeaderList");
		List<Object> workorderListNoBundle = (List<Object>) resultMap.get("WoList");
		List<Object> taskList = (List<Object>) resultMap.get("WoTaskList");
		List<Object> partListNoBundle = (List<Object>) resultMap.get("PartList");
		List<Object> bundleList = (List<Object>) resultMap.get("BundleList");
		List<Object> partListAdditinalList = (List<Object>) resultMap.get("PartListAdditional");
		List<Object> additionalList = (List<Object>) resultMap.get("AdditionalList");
		List<Object> tyreSpecsList = (List<Object>) resultMap.get("TyreList");
		List<Object> tyreServiceList = (List<Object>) resultMap.get("TyreServicesList");
		List<Object> consumableList = (List<Object>) resultMap.get("ConsumableList");
		
		setHeaderData(baseResponse,headerDataList);
		setRegularWorkorderData(baseResponse, workorderListNoBundle, taskList, partListNoBundle, tyreSpecsList, tyreServiceList);
		setBundleWorkorder(baseResponse, bundleList);
		setConsumablesData(baseResponse, consumableList);
		setAdditionalWorkorderData(baseResponse, additionalList, partListAdditinalList);
		return baseResponse;
	}
	
	private void setConsumablesData(InvoiceResponse baseResponse, List<Object> consumableList) {
		if(!consumableList.isEmpty()) {
			Workorder workorderRegularConsum = new Workorder();
			workorderRegularConsum.setEstimationDetails("Consumables");
			consumableList.forEach(mapObj->{
				Consumables consumables = new Consumables();
				Map<String, Object> consumablesMap = (Map<String, Object>) mapObj;
				consumables.setConsumables((String) consumablesMap.get("consumables"));
				consumables.setConsmblegroupId((Integer) consumablesMap.get("consmblegroupId"));
				consumables.setConsmblesubGroupId((Integer) consumablesMap.get("consmblesubGroupId"));
				consumables.setConsmbleitemId((Integer) consumablesMap.get("consmbleitemId"));
				consumables.setFee((BigDecimal) consumablesMap.get("fee"));
				consumables.setCharges((BigDecimal) consumablesMap.get("charges"));
				consumables.setMinAmount((BigDecimal) consumablesMap.get("minAmount"));
				consumables.setMaxAmount((BigDecimal) consumablesMap.get("maxAmount"));
				consumables.setPercentage((BigDecimal) consumablesMap.get("percentage"));
				workorderRegularConsum.setType((String) consumablesMap.get("type"));
				workorderRegularConsum.getConsumablesList().add(consumables);
			});
			baseResponse.getRegular().add(workorderRegularConsum);
		}
	}

	private void setHeaderData(InvoiceResponse baseResponse, List<Object> headerDataList) {
		HeaderData headerData = new HeaderData();
		for(Object mapObj:headerDataList) {
			Map<String, Object> headerDetailsMap = (Map<String, Object>) mapObj;
			headerData.setTipAssetNr((Integer)headerDetailsMap.get("tipAssetNr"));
			headerData.setCurrency((String)headerDetailsMap.get("currency"));
			headerData.setVat((BigDecimal)headerDetailsMap.get("vat"));
			headerData.setVatRate((BigDecimal)headerDetailsMap.get("vatRate"));
			headerData.setEstimationId((BigDecimal)headerDetailsMap.get("estimationId"));
			baseResponse.setHeaderData(headerData);
		}
	}

	private void setAdditionalWorkorderData(InvoiceResponse baseResponse, List<Object> additionalList,
			List<Object> partListAdditinalList) {
		List<String> checkList = new ArrayList();
		for(Object mapObj:additionalList) {
			Workorder additionalEstimation = new Workorder();
			Map<String, Object> additionalEstDetailsMap = (Map<String, Object>) mapObj;
			if (!checkList.contains(additionalEstDetailsMap.get("groupCdDesc"))) {
				additionalEstimation.setEstimationDetails((String) additionalEstDetailsMap.get("groupCdDesc"));
				checkList.add((String) additionalEstDetailsMap.get("groupCdDesc"));
				additionalEstimation.setType((String) additionalEstDetailsMap.get("type"));
				for (Object mapObj1 : additionalList) {					
					Task task = new Task();
					Map<String, Object> taskDetailsMap = (Map<String, Object>) mapObj1;
					if (additionalEstimation.getEstimationDetails().equalsIgnoreCase((String) taskDetailsMap.get("groupCdDesc"))) {
						task.setTaskName(getDescription(taskDetailsMap));
						task.setEstWoId((Integer) taskDetailsMap.get("woNr"));
						task.setEstWotId((Integer) taskDetailsMap.get("wotNr"));
						task.setEstimatedCost((BigDecimal) taskDetailsMap.get("estimatedCost"));
						task.setActualCost((BigDecimal) taskDetailsMap.get("actualCost"));
						additionalEstimation.getTasks().add(task);
					}
				}
				baseResponse.getAdditional().add(additionalEstimation);
			}
		}
		setUnmatchedPartsData(baseResponse,partListAdditinalList, false);
	}

	private void setRegularWorkorderData(InvoiceResponse baseResponse, List<Object> workorderListNoBundle,
			List<Object> taskList, List<Object> partListNoBundle, List<Object> tyreSpecsList,
			List<Object> tyreServiceList) {
		for (Object mapObj : workorderListNoBundle) {
			Workorder workorderRegular = new Workorder();
			Map<String, Object> workorderDetailsMap = (Map<String, Object>) mapObj;
			workorderRegular.setEstWoId((Integer) workorderDetailsMap.get("estnWOId"));
			workorderRegular.setType((String) workorderDetailsMap.get("type"));
			workorderRegular.setEstimationDetails((String) workorderDetailsMap.get("groupCdDesc"));
			setTaskData(workorderRegular, taskList);
			setMatchedPartsData(workorderRegular, partListNoBundle);
			setTyreSpecsData(workorderRegular, tyreSpecsList);
			setTyreServicesData(workorderRegular, tyreServiceList);
			baseResponse.getRegular().add(workorderRegular);
		}
		setUnmatchedPartsData(baseResponse, partListNoBundle, true);
	}
	
	
	private void setUnmatchedPartsData(InvoiceResponse baseResponse, List<Object> partListNoBundle, boolean ragularType) {

		if (ragularType) {
			Workorder regularEstimation = new Workorder();
			for (Object mapObj : partListNoBundle) {
				Part partMatched = new Part();
				Map<String, Object> partDetails = (Map<String, Object>) mapObj;
				if (((Integer) partDetails.get("estnWOId")) == null) {
					partMatched.setEstWoId((Integer) partDetails.get("estnWOId"));
					partMatched.setEstWotId((Integer) partDetails.get("estnWOTId"));
					partMatched.setPartsNumber((String) partDetails.get("partNumber"));
					partMatched.setPartDesc((String) partDetails.get("partDesc"));
					partMatched.setActQty((Integer) partDetails.get("qty"));
					partMatched.setActualCost((BigDecimal) partDetails.get("actualCost"));
					regularEstimation.getParts().add(partMatched);
					regularEstimation.setEstimationDetails("Additional Parts");
					regularEstimation.setType((String) partDetails.get("type"));
				}
			}
			baseResponse.getRegular().add(regularEstimation);
		}
		else{
			Workorder additionalEstimation = new Workorder();
			for (Object mapObj : partListNoBundle) {
				Part partMatched = new Part();
				Map<String, Object> partDetails = (Map<String, Object>) mapObj;
				if (((Integer) partDetails.get("woNr")) == null) {
					partMatched.setEstWoId((Integer) partDetails.get("woNr"));
					partMatched.setEstWotId((Integer) partDetails.get("wotNr"));
					partMatched.setPartsNumber((String) partDetails.get("partNumber"));
					partMatched.setPartDesc((String) partDetails.get("partDesc"));
					partMatched.setActQty((Integer) partDetails.get("qty"));
					partMatched.setActualCost((BigDecimal) partDetails.get("actualCost"));
					additionalEstimation.getParts().add(partMatched);
					additionalEstimation.setEstimationDetails("Additional Parts");
					additionalEstimation.setType((String) partDetails.get("type"));
				}
				baseResponse.getAdditional().add(additionalEstimation);
			}
		}
	}

	private void setTaskData(Workorder workorderRegular, List<Object> taskList) {
		for(Object mapObj:taskList) {
			Task task = new Task();
			Map<String, Object> taskDetailsMap = (Map<String, Object>) mapObj;
			if(workorderRegular.getEstWoId().equals(taskDetailsMap.get("estnWOId"))) {
				task.setEstWoId((Integer) taskDetailsMap.get("estnWOId"));
				task.setEstWotId((Integer) taskDetailsMap.get("estnWOTId"));
				task.setEstimatedCost((BigDecimal) taskDetailsMap.get("estimatedCost"));
				task.setActualCost((BigDecimal) taskDetailsMap.get("actualCost"));
				task.setTaskName(getDescription(taskDetailsMap));
				workorderRegular.getTasks().add(task);
			}
		}
	}
	
	private void setTyreSpecsData(Workorder workorder, List<Object> tyreSpecsList) {
		for(Object mapObj:tyreSpecsList) {
			Tyre tyreSpecs = new Tyre();
			Map<String, Object> tyreSpecsDetailsMap = (Map<String, Object>) mapObj;
			if(workorder.getEstWoId().equals(tyreSpecsDetailsMap.get("estnWOId"))) {
				tyreSpecs.setEstWoId((Integer) tyreSpecsDetailsMap.get("estnWOId"));
				tyreSpecs.setEstWotId((Integer) tyreSpecsDetailsMap.get("estnWOTId"));
				tyreSpecs.setTyreSize((String) tyreSpecsDetailsMap.get("tyreSize"));
				tyreSpecs.setApplication((Integer) tyreSpecsDetailsMap.get("application"));
				tyreSpecs.setBrand((String) tyreSpecsDetailsMap.get("tyreBrand"));
				tyreSpecs.setTyreStatus((String) tyreSpecsDetailsMap.get("tyreStatus"));
				tyreSpecs.setChargeType((String) tyreSpecsDetailsMap.get("chargeType"));
				tyreSpecs.setLostmm((Integer) tyreSpecsDetailsMap.get("lostMM"));
				tyreSpecs.setTyrePrice((BigDecimal) tyreSpecsDetailsMap.get("charges"));
				tyreSpecs.setTyreSpecgrpId((Integer) tyreSpecsDetailsMap.get("tyreSpecgrpId"));
				tyreSpecs.setTyreSpecsubgrpId((Integer) tyreSpecsDetailsMap.get("tyreSpecsubgrpId"));
				tyreSpecs.setTyreSpecItemId((Integer) tyreSpecsDetailsMap.get("tyreSpecItemId"));
				/*tyreSpecs.setFees((String) tyreSpecsDetailsMap.get("estnWOTId"));
				tyreSpecs.setFeesPrice((BigDecimal) tyreSpecsDetailsMap.get("estnWOTId"));
				tyreSpecs.setCashingValue((String) tyreSpecsDetailsMap.get("estnWOTId"));
				tyreSpecs.setCashingPrice((Integer) tyreSpecsDetailsMap.get("estnWOTId"));*/
				workorder.getTyres().add(tyreSpecs);
			}
		}
	}

	private void setTyreServicesData(Workorder workorder, List<Object> tyreServicesList) {
		for(Object mapObj:tyreServicesList) {
			InvoiceTyreService tyreService = new InvoiceTyreService();
			Map<String, Object> tyreServiceDetailsMap = (Map<String, Object>) mapObj;
			if(workorder.getEstWoId().equals(tyreServiceDetailsMap.get("estnWOId"))) {
				tyreService.setEstWoId((Integer) tyreServiceDetailsMap.get("estnWOId"));
				tyreService.setEstWotId((Integer) tyreServiceDetailsMap.get("estnWOTId"));
				tyreService.setTyreService((String) tyreServiceDetailsMap.get("tyreService"));
				tyreService.setCharges((BigDecimal) tyreServiceDetailsMap.get("charges"));
				tyreService.setTyreServicegrpId((Integer) tyreServiceDetailsMap.get("tyreServicegrpId"));
				tyreService.setTyreServicesubgrpId((Integer) tyreServiceDetailsMap.get("tyreServicesubgrpId"));
				tyreService.setTyreServiceItemId((Integer) tyreServiceDetailsMap.get("tyreServiceItemId"));
				workorder.getTyreServices().add(tyreService);
			}
		}
	}
	
	private void setMatchedPartsData(Workorder workorderRegular, List<Object> partListNoBundle) {
		for (Object mapObj : partListNoBundle) {
			Part partMatched = new Part();
			Map<String, Object> partDetails = (Map<String, Object>) mapObj;
			if (workorderRegular.getEstWoId().equals(partDetails.get("estnWOId"))) {
				partMatched.setEstWoId((Integer) partDetails.get("estnWOId"));
				partMatched.setEstWotId((Integer) partDetails.get("estnWOTId"));
				partMatched.setPartsNumber((String) partDetails.get("partNumber"));
				partMatched.setPartDesc((String) partDetails.get("partDesc"));
				partMatched.setActQty((Integer) partDetails.get("qty"));
				partMatched.setActualCost((BigDecimal) partDetails.get("actualCost"));
				workorderRegular.getParts().add(partMatched);
			}
		}

	}
	
	private void setBundleWorkorder(InvoiceResponse baseResponse, List<Object> bundleList) {
		List<String> checkList = new ArrayList();
		for(Object mapObj:bundleList) {
			Workorder workorderRegularBundle = new Workorder();
			Map<String, Object> workorderDetailsMap = (Map<String, Object>) mapObj;
			if (!checkList.contains(workorderDetailsMap.get("bundleName"))) {
				workorderRegularBundle.setEstimationDetails((String) workorderDetailsMap.get("bundleName"));
				workorderRegularBundle.setType((String) workorderDetailsMap.get("type"));
				if(workorderDetailsMap.get("fee")!=null) {
					workorderRegularBundle.setActualCost((BigDecimal) workorderDetailsMap.get("fee"));
				}
				checkList.add((String) workorderDetailsMap.get("bundleName"));
				int count = 0;
				for (Object mapObj1 : bundleList) {					
					Task task = new Task();
					Map<String, Object> taskDetailsMap = (Map<String, Object>) mapObj1;
					if (workorderRegularBundle.getEstimationDetails().equalsIgnoreCase((String) taskDetailsMap.get("bundleName"))) {
						count++;
						task.setTaskName(getDescription(taskDetailsMap));
						task.setTaskNumber(count);
						workorderRegularBundle.getTasks().add(task);
					}

				}
				baseResponse.getRegular().add(workorderRegularBundle);
			}
		}
	}
	
	/*private String getDescription(Map<String, Object> dataMap) {
	
		String description = "";
		String hyphen = " - ";
		if((String)dataMap.get("groupCdDesc")!=null)
			if(!((String)dataMap.get("groupCdDesc")).equalsIgnoreCase("NULL"))
				description = (String)dataMap.get("groupCdDesc");
		if((String)dataMap.get("subGroup")!=null)
			if(!((String)dataMap.get("subGroup")).equalsIgnoreCase("NULL"))
				description = description+hyphen+(String)dataMap.get("subGroup");
		if((String)dataMap.get("activity")!=null)
			if(!((String)dataMap.get("activity")).equalsIgnoreCase("NULL"))
				description = description+hyphen+(String)dataMap.get("activity");
		if((String)dataMap.get("actionCd")!=null)
			if(!((String)dataMap.get("actionCd")).equalsIgnoreCase("NULL"))
				description = description+hyphen+(String)dataMap.get("actionCd");
		if((String)dataMap.get("failureCause")!=null)
			if(((String)dataMap.get("failureCause")).equalsIgnoreCase("NULL"))
				description = description+hyphen+(String)dataMap.get("failureCause");
		if((String)dataMap.get("action")!=null)
			if(!((String)dataMap.get("action")).equalsIgnoreCase("NULL"))
				description = description+hyphen+(String)dataMap.get("action");
		if((String)dataMap.get("maintenanceAction")!=null)
			if(!((String)dataMap.get("maintenanceAction")).equalsIgnoreCase("NULL"))
				description = description+hyphen+(String)dataMap.get("maintenanceAction");
		if((String)dataMap.get("manufacturer")!=null)
				if(!((String)dataMap.get("manufacturer")).equalsIgnoreCase("NULL"))
			description = description+hyphen+(String)dataMap.get("manufacturer");
		if((String)dataMap.get("position")!=null)
			if(!((String)dataMap.get("position")).equalsIgnoreCase("NULL"))
				description = description+hyphen+(String)dataMap.get("position");
		if((String)dataMap.get("reason")!=null)
			if(!((String)dataMap.get("reason")).equalsIgnoreCase("NULL"))
				description = description+hyphen+(String)dataMap.get("reason");
		if((String)dataMap.get("supplierPartNr")!=null)
			if(!((String)dataMap.get("supplierPartNr")).equalsIgnoreCase("NULL"))
				description = description+hyphen+(String)dataMap.get("supplierPartNr");
		
		return description;
	}*/

	
	private String getDescription(Map<String, Object> dataMap) {
		
		String description = "";
		String hyphen = " - ";
		if ((String) dataMap.get("groupCdDesc") != null
				&& !((String) dataMap.get("groupCdDesc")).equalsIgnoreCase("NULL"))
			description = (String) dataMap.get("groupCdDesc");
		if ((String) dataMap.get("subGroup") != null && !((String) dataMap.get("subGroup")).equalsIgnoreCase("NULL"))
			description = description + hyphen + (String) dataMap.get("subGroup");
		if ((String) dataMap.get("activity") != null && !((String) dataMap.get("activity")).equalsIgnoreCase("NULL"))
			description = description + hyphen + (String) dataMap.get("activity");
		if ((String) dataMap.get("actionCd") != null && !((String) dataMap.get("actionCd")).equalsIgnoreCase("NULL"))
			description = description + hyphen + (String) dataMap.get("actionCd");
		if ((String) dataMap.get("failureCause") != null
				&& ((String) dataMap.get("failureCause")).equalsIgnoreCase("NULL"))
			description = description + hyphen + (String) dataMap.get("failureCause");
		if ((String) dataMap.get("action") != null && !((String) dataMap.get("action")).equalsIgnoreCase("NULL"))
			description = description + hyphen + (String) dataMap.get("action");
		if ((String) dataMap.get("maintenanceAction") != null
				&& !((String) dataMap.get("maintenanceAction")).equalsIgnoreCase("NULL"))
			description = description + hyphen + (String) dataMap.get("maintenanceAction");
		if ((String) dataMap.get("manufacturer") != null
				&& !((String) dataMap.get("manufacturer")).equalsIgnoreCase("NULL"))
			description = description + hyphen + (String) dataMap.get("manufacturer");
		if ((String) dataMap.get("position") != null && !((String) dataMap.get("position")).equalsIgnoreCase("NULL"))
			description = description + hyphen + (String) dataMap.get("position");
		if ((String) dataMap.get("reason") != null && !((String) dataMap.get("reason")).equalsIgnoreCase("NULL"))
			description = description + hyphen + (String) dataMap.get("reason");
		if ((String) dataMap.get("supplierPartNr") != null
				&& !((String) dataMap.get("supplierPartNr")).equalsIgnoreCase("NULL"))
			description = description + hyphen + (String) dataMap.get("supplierPartNr");
		
		return description;
	}
}
