package com.tip.estimationreport.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.estimationreport.model.AdditionalPartDetails;
import com.tip.estimationreport.model.AdminFees;
import com.tip.estimationreport.model.BaseModel;
import com.tip.estimationreport.model.BranchDetails;
import com.tip.estimationreport.model.BundleDetails;
import com.tip.estimationreport.model.Bundles;
import com.tip.estimationreport.model.CustomJobs;
import com.tip.estimationreport.model.CustomerDetails;
import com.tip.estimationreport.model.EstimationDetails;
import com.tip.estimationreport.model.EstnReportRequest;
import com.tip.estimationreport.model.Fees;
import com.tip.estimationreport.model.ImageDetails;
import com.tip.estimationreport.model.LabourDetails;
import com.tip.estimationreport.model.OtherFees;
import com.tip.estimationreport.model.PartDetails;
import com.tip.estimationreport.model.ResponseObject;
import com.tip.estimationreport.model.TaskDetails;
import com.tip.estimationreport.model.TotalCalculate;
import com.tip.estimationreport.model.TotalCharge;
import com.tip.estimationreport.repository.EstimationReportRepository;
import com.tip.estimationreport.service.EstimationReportService;
import com.tip.estimationreport.util.DataTypeConversionUtil;
import com.tip.estimationreport.util.EstimationReportPdf;

@Service
public class EstimationReportServiceImpl implements EstimationReportService {
	
	@Autowired
	EstimationReportRepository estimationReportRepository;
	
	@Autowired
	DataTypeConversionUtil dataTypeConversionUtil;
	
	@Autowired
	EstimationReportPdf estimationReportPdf;
		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ResponseObject getPdf(EstnReportRequest estnReportRequest, BaseModel bm) {

		boolean isSummary = estnReportRequest.isSummary()!=null?estnReportRequest.isSummary():false;
		TotalCalculate totalCalculateObj = new TotalCalculate();
		Map<String, Object> resultMap = estimationReportRepository.getReportData(estnReportRequest);
		List headerDetailsDataList = (List) resultMap.get("headerDetailsData");
		List labourOrderDataList = (List) resultMap.get("labourOrderDataList");
		List labourOrderTaskDataList = (List) resultMap.get("labourOrderTaskDataList");
		List partsDataList = (List) resultMap.get("partsDataList");
		List bundleDataList = (List) resultMap.get("bundleDataList");
		List consumablesList = (List) resultMap.get("consumablesList");
		List imageList = (List) resultMap.get("imageList");
		List adminFeesList = (List) resultMap.get("adminFeesList");
		Map<String, String> labelMap = (Map<String, String>)((List) resultMap.get("hLabelList")).get(0);

		
		setHeaderDetailsData(bm, headerDetailsDataList, totalCalculateObj);
		setLabourOrderData(bm, labourOrderDataList, labourOrderTaskDataList, partsDataList, totalCalculateObj);
		bm.setAdditionalParts(setUnMatchedPartData(partsDataList, totalCalculateObj));
		setCustomJobs(bm, bundleDataList,totalCalculateObj);
		setConsumables(bm, consumablesList, totalCalculateObj, adminFeesList);
		setImageDetailsData(bm, imageList);
		calculateTotal(bm, totalCalculateObj);
		
		return estimationReportPdf.createPdf(bm, labelMap, isSummary);
	}

	private void setCustomJobs(BaseModel bm, List bundleDataList, TotalCalculate totalCalculateObj) {
		if(!bundleDataList.isEmpty()) {
			CustomJobs customJobs = new CustomJobs();
			setBundleData(customJobs, bundleDataList, totalCalculateObj);
			bm.setCustomJob(customJobs);
		}
	}

	private void setBundleData(CustomJobs custJobs, List bundleDataList, TotalCalculate totalCalculateObj) {
		List<String> checkList = new ArrayList();
		BigDecimal totalPrice = new BigDecimal(0);
		BigDecimal totalVat = new BigDecimal(0);
		for (Object mapObj : bundleDataList) {
			Bundles bundles = new Bundles();
			Map<String, Object> bundlesMap = (Map<String, Object>) mapObj;
			if (!checkList.contains(bundlesMap.get("bundleName"))) {
				bundles.setBundleName((String) bundlesMap.get("bundleName"));
				checkList.add((String) bundlesMap.get("bundleName"));
				BigDecimal bundleLvlTotalPrice = new BigDecimal(0);
				BigDecimal bundleLvlTotalVat = new BigDecimal(0);
				for (Object mapObj1 : bundleDataList) {
					BundleDetails bundle = new BundleDetails();
					Map<String, Object> bundlesMap1 = (Map<String, Object>) mapObj1;
					if (bundles.getBundleName()
							.equalsIgnoreCase((String) bundlesMap1.get("bundleName"))) {
						bundle.setBundleName((String) bundlesMap1.get("bundleName"));
						bundle.setGroupCdDsc((String) bundlesMap1.get("groupCdDesc"));
						bundle.setSubGroup((String) bundlesMap1.get("subGroup"));
						bundle.setActivity((String) bundlesMap1.get("activity"));
						bundle.setMaintenanceAction((String) bundlesMap1.get("maintenanceAction"));
						bundle.setManufacturer((String) bundlesMap1.get("manufacturer"));
						bundle.setSupplierPartNr((String) bundlesMap1.get("supplierPartNr"));
						bundle.setActionCd((String) bundlesMap1.get("actionCd"));
						bundle.setFees(dataTypeConversionUtil.castValue((BigDecimal) bundlesMap1.get("fee")));
						BigDecimal tempTotalPrice = ((BigDecimal) bundlesMap1.get("fee")) !=null? ((BigDecimal) bundlesMap1.get("fee")) : BigDecimal.ZERO;
						BigDecimal tempVatPrice = ((BigDecimal) bundlesMap1.get("vat")) !=null? ((BigDecimal) bundlesMap1.get("vat")) : BigDecimal.ZERO;
						bundleLvlTotalPrice = bundleLvlTotalPrice.add(tempTotalPrice);
						bundleLvlTotalVat = bundleLvlTotalVat.add(tempVatPrice);
						bundles.getListOfBundleDetails().add(bundle);
						
					}
					bundles.setTotalPrice(dataTypeConversionUtil.castValue(bundleLvlTotalPrice));
					bundles.setTotalVatAmount(dataTypeConversionUtil.castValue(bundleLvlTotalVat));
				}
				totalPrice = totalPrice.add(bundleLvlTotalPrice);
				totalVat = totalVat.add(bundleLvlTotalVat);
				custJobs.setTotalPrice(dataTypeConversionUtil.castValue(totalPrice));
				custJobs.setVatAmount(dataTypeConversionUtil.castValue(totalVat));
				custJobs.getListOfBundle().add(bundles);
			}
		}
		totalCalculateObj.setSubTotal(totalCalculateObj.getSubTotal().add(totalPrice));
		
	}

	private void setLabourOrderData(BaseModel bm, List labourOrderDataList, List labourOrderTaskDataList, List partsDataList, TotalCalculate totalCalculateObj) {
		for(Object mapObj : labourOrderDataList) {
			boolean showTask = false;
			boolean showPart = false;
			LabourDetails labourDetails = new LabourDetails();
			Map<String, Object> labourOrderMap = (Map<String, Object>) mapObj;
			labourDetails.setLabourOrder(dataTypeConversionUtil.castValue((Integer)labourOrderMap.get("estnWOId")));
			labourDetails.setLoDescription((String)labourOrderMap.get("groupCdDesc"));
			labourDetails.setSupplierName((String)labourOrderMap.get("supplierName"));
			labourDetails.setLabourTime((String)labourOrderMap.get("labourTime"));
			labourDetails.setCustomerComments((String)labourOrderMap.get("woCustComm"));
			BigDecimal totalCharge = ((BigDecimal)labourOrderMap.get("totalCharge")) == null ? BigDecimal.ZERO : (BigDecimal)labourOrderMap.get("totalCharge");
			BigDecimal price = ((BigDecimal)labourOrderMap.get("price")) == null ? BigDecimal.ZERO : (BigDecimal)labourOrderMap.get("price");
			labourDetails.setTotalChargePrice(dataTypeConversionUtil.castValue(totalCharge.add(price)));
			labourDetails.setVatAmount(dataTypeConversionUtil.castValue((BigDecimal)labourOrderMap.get("vat")));
			showTask = setLoTaskData(labourOrderTaskDataList, partsDataList, labourDetails);
			showPart = setMatchedPartData(partsDataList, labourDetails);
			if (showTask || showPart) {
				String taskCount = dataTypeConversionUtil.castValue(labourDetails.getListOfTask().isEmpty()?0:labourDetails.getListOfTask().size());
				String partCount = dataTypeConversionUtil.castValue(labourDetails.getListOfPart().isEmpty()?0:labourDetails.getListOfPart().size());
				labourDetails.setLoTaskCount(taskCount);
				labourDetails.setPartsCount(partCount);
				bm.getListOfLabour().add(labourDetails);
				totalCalculateObj.setSubTotal(totalCalculateObj.getSubTotal().add(totalCharge.add(price)));
			}
		}
		
	}

	private boolean setLoTaskData(List labourOrderTaskDataList, List partsDataList, LabourDetails labourDetails) {
		TaskDetails taskSummary = new TaskDetails();
		Double totalLabourCharge = new Double(0);
		boolean flagShow = false;
		for(Object taskMapObj : labourOrderTaskDataList) {
			TaskDetails taskDetails = new TaskDetails();
			Map<String, Object> labourOrderTaskMap = (Map<String, Object>) taskMapObj;
			String showHide = (String) labourOrderTaskMap.get("showHide")!=null?(String) labourOrderTaskMap.get("showHide"):"";
			if("N".equalsIgnoreCase(showHide)) {
				taskDetails.setLoNr(dataTypeConversionUtil.castValue((Integer)labourOrderTaskMap.get("estnWOId")));
				if(taskDetails.getLoNr().equalsIgnoreCase(labourDetails.getLabourOrder())) {
					String tDescription = "";
					String hyphen = " - ";
					if((String)labourOrderTaskMap.get("groupCdDesc")!=null)
						tDescription = (String)labourOrderTaskMap.get("groupCdDesc");
					if((String)labourOrderTaskMap.get("subGroup")!=null)
						tDescription = tDescription+hyphen+(String)labourOrderTaskMap.get("subGroup");
					if((String)labourOrderTaskMap.get("activity")!=null)
						tDescription = tDescription+hyphen+(String)labourOrderTaskMap.get("activity");
					if((String)labourOrderTaskMap.get("failureCause")!=null)
						tDescription = tDescription+hyphen+(String)labourOrderTaskMap.get("failureCause");
					if((String)labourOrderTaskMap.get("action")!=null)
						tDescription = tDescription+hyphen+(String)labourOrderTaskMap.get("action");
					if((String)labourOrderTaskMap.get("position")!=null)
						tDescription = tDescription+hyphen+(String)labourOrderTaskMap.get("position");
					if((String)labourOrderTaskMap.get("reason")!=null)
						tDescription = tDescription+hyphen+(String)labourOrderTaskMap.get("reason");
					taskDetails.setLoDescription(labourDetails.getLoDescription());
					taskDetails.setTaskDescription(tDescription);
					taskDetails.setLoTaskNr(dataTypeConversionUtil.castValue((Integer)labourOrderTaskMap.get("estnWOTId")));
					taskDetails.setTargetTime(dataTypeConversionUtil.castValue((Double)labourOrderTaskMap.get("targetTime")));
					taskDetails.setLabourRate(dataTypeConversionUtil.castValue((BigDecimal)labourOrderTaskMap.get("labourRate")));
					taskDetails.setVatAmount(dataTypeConversionUtil.castValue((BigDecimal)labourOrderTaskMap.get("vat")));
					taskSummary.setVatAmount(dataTypeConversionUtil.castValue((BigDecimal)labourOrderTaskMap.get("vat")));
					taskDetails.setLabourCharge(dataTypeConversionUtil.castValue((Double)labourOrderTaskMap.get("labourCharge")));
					totalLabourCharge = totalLabourCharge + ((Double)labourOrderTaskMap.get("labourCharge")==null?0:(Double)labourOrderTaskMap.get("labourCharge"));
					taskDetails.setTaskComments((String)labourOrderTaskMap.get("taskComments"));
					labourDetails.getListOfTask().add(taskDetails);
					flagShow = true;
				}
			}
			
		}
		taskSummary.setLabourCharge(dataTypeConversionUtil.castValue(totalLabourCharge));
		taskSummary.setLoDescription(labourDetails.getLoDescription());
		labourDetails.setTaskSummary(taskSummary);
		return flagShow;
	}

	private boolean setMatchedPartData(List partsDataList, LabourDetails labourDetails) {
		PartDetails partSummary = new PartDetails();
		int qty = 0;
		boolean flasgShow = false;
		BigDecimal totalPrice = BigDecimal.ZERO;
		for (Object partsMapObj : partsDataList) {
			PartDetails part = new PartDetails();
			Map<String, Object> partsMap = (Map<String, Object>) partsMapObj;
			String showHide = (String) partsMap.get("showHide")!=null?(String) partsMap.get("showHide"):"";
			if ("N".equalsIgnoreCase(showHide)) {
				part.setLoNr(dataTypeConversionUtil.castValue((Integer) partsMap.get("estnWOId")));
				part.setLoTaskNr(dataTypeConversionUtil.castValue((Integer) partsMap.get("estnWOTId")));
				if (part.getLoNr().equalsIgnoreCase(labourDetails.getLabourOrder())) {
					part.setPartDescription((String) partsMap.get("partDesc"));
					part.setPartNr((String) partsMap.get("partNumber"));
					part.setQuantity(dataTypeConversionUtil.castValue((Integer) partsMap.get("qty")));
					qty = qty + (Integer) partsMap.get("qty");
					part.setVatAmount(dataTypeConversionUtil.castValue((BigDecimal) partsMap.get("vat")));
					partSummary.setVatAmount(dataTypeConversionUtil.castValue((BigDecimal) partsMap.get("vat")));
					part.setPrice(dataTypeConversionUtil.castValue((BigDecimal) partsMap.get("soldPrice")));
					totalPrice = totalPrice
							.add(partsMap.get("soldPrice") == null ? BigDecimal.ZERO : (BigDecimal) partsMap.get("soldPrice"));
					labourDetails.getListOfPart().add(part);
					flasgShow = true;
				}
			}
		}
		partSummary.setQuantity(dataTypeConversionUtil.castValue(qty));
		partSummary.setPrice(dataTypeConversionUtil.castValue(totalPrice));
		labourDetails.setPartSummary(partSummary);
		return flasgShow;
	}

	private AdditionalPartDetails setUnMatchedPartData(List partsDataList, TotalCalculate totalCalculateObj) {
		AdditionalPartDetails additionalPartDetails = new AdditionalPartDetails();
		List umParts = new ArrayList();
		BigDecimal partTotalPrice = BigDecimal.ZERO;
		BigDecimal partTotalVat = BigDecimal.ZERO;
		for (Object partsMapObj : partsDataList) {
			PartDetails part = new PartDetails();
			Map<String, Object> partsMap = (Map<String, Object>) partsMapObj;
			String showHide = (String) partsMap.get("showHide") != null ? (String) partsMap.get("showHide") : "";
			if ("N".equalsIgnoreCase(showHide)) {
				part.setLoNr(dataTypeConversionUtil.castValue((Integer) partsMap.get("estnWOId")));
				part.setLoTaskNr(dataTypeConversionUtil.castValue((Integer) partsMap.get("estnWOTId")));
				if ((Integer) partsMap.get("estnWOId") == null && (Integer) partsMap.get("estnWOTId") == null) {
					part.setPartDescription((String) partsMap.get("partDesc"));
					part.setPartNr((String) partsMap.get("partNumber"));
					part.setQuantity(dataTypeConversionUtil.castValue((Integer) partsMap.get("qty")));
					part.setPrice(dataTypeConversionUtil.castValue((BigDecimal) partsMap.get("soldPrice")));
					part.setVatAmount(dataTypeConversionUtil.castValue((BigDecimal) partsMap.get("vat")));
					partTotalPrice = partTotalPrice.add(((BigDecimal) partsMap.get("soldPrice")) == null ? BigDecimal.ZERO
							: (BigDecimal) partsMap.get("soldPrice"));
					partTotalVat = partTotalVat.add(((BigDecimal) partsMap.get("vat")) == null ? BigDecimal.ZERO
							: (BigDecimal) partsMap.get("vat"));
					umParts.add(part);
				}
			}
		}
		additionalPartDetails.setPartsCount(dataTypeConversionUtil.castValue(umParts.size()));
		additionalPartDetails.setTotalPrice(dataTypeConversionUtil.castValue(partTotalPrice));
		totalCalculateObj.setSubTotal(totalCalculateObj.getSubTotal().add(partTotalPrice));
		additionalPartDetails.setTotalVat(dataTypeConversionUtil.castValue(partTotalVat));
		additionalPartDetails.getListOfUnMatchedParts().addAll(umParts);
		return additionalPartDetails;
	}

	private void setConsumables(BaseModel bm, List listOfConsumables, TotalCalculate totalCalculateObj, List adminFeesList) {
		boolean otherFeesHideFlag = false;
		OtherFees otherFees = new OtherFees();
		BigDecimal total = BigDecimal.ZERO;
		for(Object mapObj: listOfConsumables) {
			Fees fees = new Fees();
			Map<String, Object> othersFeesDataMap = (Map<String, Object>) mapObj;
			String showHide = (String) othersFeesDataMap.get("showHide") != null ? (String) othersFeesDataMap.get("showHide") : "";
			if ("N".equalsIgnoreCase(showHide)) {
				fees.setFeesDesc((String) othersFeesDataMap.get("consumables"));
				fees.setCharges(dataTypeConversionUtil.castValue((BigDecimal) othersFeesDataMap.get("charges")));
				fees.setVatAmount(dataTypeConversionUtil.castValue((BigDecimal) othersFeesDataMap.get("vat")));
				otherFees.setVatAmount(dataTypeConversionUtil.castValue((BigDecimal) othersFeesDataMap.get("vat")));
				total = total.add((BigDecimal) othersFeesDataMap.get("charges"));
				otherFees.getListOfFees().add(fees);
				otherFeesHideFlag = true;
			}
		}
		
		for(Object mapObjAdmin: adminFeesList){
			AdminFees adminFee = new AdminFees();
			Map<String, Object> adminFeesDataMap = (Map<String, Object>) mapObjAdmin;
			String showHide = (String) adminFeesDataMap.get("showHide") != null ? (String) adminFeesDataMap.get("showHide") : "";
			if ("N".equalsIgnoreCase(showHide)) {
				adminFee.setFeesDesc((String) adminFeesDataMap.get("feeDesc"));
				adminFee.setCharges(dataTypeConversionUtil.castValue((BigDecimal) adminFeesDataMap.get("charges")));
				adminFee.setVatAmount(dataTypeConversionUtil.castValue((BigDecimal) adminFeesDataMap.get("vat")));
				otherFees.setVatAmount(dataTypeConversionUtil.castValue((BigDecimal) adminFeesDataMap.get("vat")));
				total = total.add((BigDecimal) adminFeesDataMap.get("charges"));
				otherFees.getListOfAdminFees().add(adminFee);
				otherFeesHideFlag = true;
			}
		}
		if(otherFeesHideFlag) {
			totalCalculateObj.setSubTotal(totalCalculateObj.getSubTotal().add(total));
			otherFees.setTotalPrice(dataTypeConversionUtil.castValue(total));
			bm.setOtherFees(otherFees);
		}
	}
	private void setHeaderDetailsData(BaseModel bm, List headerDetailsDataList, TotalCalculate totalCalculateObj) {
		
		for(Object mapObj : headerDetailsDataList) {
			Map<String, Object> headerDetailsDataMap = (Map<String, Object>) mapObj;
			BranchDetails bDetails = new BranchDetails();
			CustomerDetails cDetails = new CustomerDetails();
			EstimationDetails eDetails = new EstimationDetails();
			bDetails.setBranchName((String)headerDetailsDataMap.get("branchName"));
			bDetails.setBranchPhone((String)headerDetailsDataMap.get("branchPhone"));
			bDetails.setBranchFax((String)headerDetailsDataMap.get("branchFax"));
			String addrStr = (String)headerDetailsDataMap.get("branchAddr");
			addrStr = addrStr !=null?addrStr.replaceAll("(\\r|\\n|\\t)", " ") : "";
			bDetails.setBranchAddr(addrStr);
			
			cDetails.setCustName((String)headerDetailsDataMap.get("custName"));
			addrStr = (String)headerDetailsDataMap.get("custAddr");
			addrStr =  addrStr !=null?addrStr.replaceAll("(\\r|\\n|\\t)", " ") : "";
			cDetails.setCustAddr(addrStr);
			cDetails.setCustPhone((String)headerDetailsDataMap.get("custPhone"));
			cDetails.setCustFax((String)headerDetailsDataMap.get("custFax"));
			cDetails.setCustEmail((String)headerDetailsDataMap.get("custEmail"));
			
			eDetails.setEstNr((String)headerDetailsDataMap.get("estimationNr"));
			eDetails.setTipTrailerNr(dataTypeConversionUtil.castValue((Integer)headerDetailsDataMap.get("tipAssetNr")));
			eDetails.setCustRef((String)headerDetailsDataMap.get("custAssetNr"));
			eDetails.setRegNr((String)headerDetailsDataMap.get("regNr"));
			eDetails.setEstBy((String)headerDetailsDataMap.get("estimatedBy"));
			eDetails.setBranch(dataTypeConversionUtil.castValue((Integer)headerDetailsDataMap.get("branchNr")));
			//eDetails.setTotal(dataTypeConversionUtil.castValue((Integer)headerDetailsDataMap.get("total")));
			
			bm.setLogoUrl((String)headerDetailsDataMap.get("tipLogo"));
			bm.setReportLoc((String)headerDetailsDataMap.get("ReportLocation"));
			bm.setCurrency((String)headerDetailsDataMap.get("currency")+" ");
			bm.setNotes((String)headerDetailsDataMap.get("internalNotes"));
			bm.setComments((String)headerDetailsDataMap.get("custNotes"));
			bm.setCountryCd((String)headerDetailsDataMap.get("countryCd"));
			
			totalCalculateObj.setConsession((BigDecimal)headerDetailsDataMap.get("concession"));
			totalCalculateObj.setVatRate((BigDecimal)headerDetailsDataMap.get("vatRate"));
			
			bm.setBranchDetails(bDetails);
			bm.setCustomerDetails(cDetails);
			bm.setEstimationDetails(eDetails);
			bm.setVersion((Integer)headerDetailsDataMap.get("version"));
			bm.setSupplementary((Integer)headerDetailsDataMap.get("supplementary"));
			//bm.setTotalCharges(totalCharge);
		}
	}
	
	private void setImageDetailsData(BaseModel bm, List imageList) {
		for(Object mapObj : imageList) {
			Map<String, Object> imageDataMap = (Map<String, Object>) mapObj;
			ImageDetails imgData = new ImageDetails();
			imgData.setEstWoId(dataTypeConversionUtil.castValue((Integer)imageDataMap.get("estnWOId")));
			imgData.setEstWoTaskId(dataTypeConversionUtil.castValue((Integer)imageDataMap.get("estnWOTId")));
			imgData.setPhotoType((String)imageDataMap.get("photoType"));
			imgData.setPhotoLoc((String)imageDataMap.get("photoLoc"));
			imgData.setPhotoName((String)imageDataMap.get("photoName"));
			bm.getListOfImages().add(imgData);
		}
	}
	
	private void calculateTotal(BaseModel bm, TotalCalculate totalCalculate) {
		
		TotalCharge totalCharges = new TotalCharge();
		BigDecimal totalAfterCon;
		BigDecimal vatAmount = null;
		BigDecimal total = null;
		if(totalCalculate.getConsession()!=null)
			totalAfterCon = totalCalculate.getSubTotal().subtract(totalCalculate.getConsession());
		else
			totalAfterCon = totalCalculate.getSubTotal();
		
		if(totalCalculate.getVatRate()!=null) {
			vatAmount = totalAfterCon.multiply(totalCalculate.getVatRate());
			total = totalAfterCon.add(vatAmount);
		}else {
			total = totalAfterCon;
		}
		
		totalCharges.setSubTotal(dataTypeConversionUtil.castValue(totalCalculate.getSubTotal()));
		totalCharges.setAfterConcession(dataTypeConversionUtil.castValue(totalAfterCon));
		totalCharges.setAfterVat(dataTypeConversionUtil.castValue(vatAmount));
		totalCharges.setVatPercentage(dataTypeConversionUtil.castValue(totalCalculate.getVatRate()));
		totalCharges.setTotal(dataTypeConversionUtil.castValue(total));
		bm.setTotalCharges(totalCharges);
	}
}
