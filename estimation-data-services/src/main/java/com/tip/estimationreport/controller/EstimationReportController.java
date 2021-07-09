package com.tip.estimationreport.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimationreport.model.BaseModel;
import com.tip.estimationreport.model.BundleDetails;
import com.tip.estimationreport.model.Bundles;
import com.tip.estimationreport.model.EstnReportRequest;
import com.tip.estimationreport.model.Fees;
import com.tip.estimationreport.model.LabourDetails;
import com.tip.estimationreport.model.OtherFees;
import com.tip.estimationreport.model.PartDetails;
import com.tip.estimationreport.model.ResponseObject;
import com.tip.estimationreport.model.TaskDetails;
import com.tip.estimationreport.model.TotalCharge;
import com.tip.estimationreport.model.Tyre;
import com.tip.estimationreport.service.EstimationReportService;
import com.tip.estimationreport.util.EstimationReportPdf;


@RestController
@RequestMapping("/service/estimation-data-service/1.0/")
public class EstimationReportController {
	
	@Autowired
	EstimationReportService estimationReportService;
	
	@RequestMapping(value = "estimationPdf", method = RequestMethod.POST, produces = "application/pdf")
	public void download(@RequestBody EstnReportRequest estnReportRequest, HttpServletResponse response)
					throws IOException {
		
		EstimationReportPdf estimationReportPdf = new EstimationReportPdf();
		//BaseModel bm = setDetails();
		BaseModel bm = new BaseModel();
		//setValues();
			//ResponseObject responseObject = estimationReportPdf.createPdf(bm);
			ResponseObject responseObject = estimationReportService.getPdf(estnReportRequest, bm);
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "inline;filename=" + responseObject.getFile().getName());
			IOUtils.copy(responseObject.getInputStream(), response.getOutputStream());
			responseObject.getOutputStream().close();
			responseObject.getInputStream().close();
	}

/*	private Map<String, Object> setValues() {
		
		Map<String, Object> labourMap = new HashMap();
		Map<String, Object> taskMap = new HashMap();
		Map<String, Object> partMap = new HashMap();
		Map<String, Object> tyreMap = new HashMap();
		
		tyreMap.put("Tyre Size", "12");
		tyreMap.put("Application", "Dive");
		tyreMap.put("Tyre Status", "New");
		tyreMap.put("Change Type", "Lost MM");
		tyreMap.put("Lost Mm", "10");
		tyreMap.put("Fees", "Ecotax");
		tyreMap.put("Tyre Casing Value", "Yes");
		tyreMap.put("Charges", "50.00");
		tyreMap.put("Brand", "Good-Year Dunlop");
		
		return null;
		
	}*/

	private BaseModel setDetails() {
		BaseModel bm = new BaseModel();

		/*List<LabourDetails> labourList = new ArrayList();		
		LabourDetails lab1 =  new LabourDetails();
		lab1.setLabourOrder("78505454584");
		lab1.setLoDescription("Brake");
		lab1.setLoTaskCount("2");
		lab1.setPartsCount("3");
		lab1.setSupplierName("Carrignton Workshop");
		lab1.setLabourTime("Day");
		lab1.setTotalChargePrice("£ 358.00");
		LabourDetails lab2 =  new LabourDetails();
		lab2.setLabourOrder("998844885");
		lab2.setLoDescription("Brake");
		lab2.setLoTaskCount("2");
		lab2.setPartsCount("3");
		lab2.setSupplierName("Carrignton Workshop");
		lab2.setLabourTime("Day");
		lab2.setTotalChargePrice("£ 658.00");
		LabourDetails labTyre3 =  new LabourDetails();
		labTyre3.setLabourOrder("41234560");
		labTyre3.setLoDescription("Tyre");
		labTyre3.setLoTaskCount("1");
		labTyre3.setSupplierName("Carrignton Workshop");
		labTyre3.setLabourTime("Day");
		labTyre3.setTotalChargePrice("£ 90.00");
		labTyre3.setTyresCount("1");
		
		
		
		TaskDetails taskDetails1 = new TaskDetails();
		taskDetails1.setLoTaskNr("01");
		taskDetails1.setTaskDescription("Brake - Drum - Braking Lining - Worn - Replace - L1 - Routine");
		taskDetails1.setTargetTime("70");
		taskDetails1.setLabourRate("50.00");
		taskDetails1.setLabourCharge("60.00");
		TaskDetails taskDetails2 = new TaskDetails();
		taskDetails2.setLoTaskNr("02");
		taskDetails2.setTaskDescription("Brake - Drum - Braking Lining - Worn - Replace - L2 - Routine");
		taskDetails2.setTargetTime("70");
		taskDetails2.setLabourRate("40.00");
		taskDetails2.setLabourCharge("50.00");
		TaskDetails taskTyre = new TaskDetails();
		taskTyre.setLoTaskNr("01");
		taskTyre.setTaskDescription("Tyre - Replace - L1");
		taskTyre.setTargetTime("60");
		taskTyre.setLabourRate("50.00");
		taskTyre.setLabourCharge("50.00");
		
		PartDetails partDetails1 = new PartDetails();
		partDetails1.setPartNr("CF 34995/TIP040072341524");
		partDetails1.setPartDescription("Brake Lining");
		partDetails1.setQuantity("4");
		partDetails1.setPrice("40.00");
		PartDetails partDetails2 = new PartDetails();
		partDetails2.setPartNr("CF 34995/TIP040072341625");
		partDetails2.setPartDescription("Brake Lining");
		partDetails2.setQuantity("4");
		partDetails2.setPrice("99.00");
		
		Tyre tyre = new Tyre();
		tyre.setTyreSize("12");
		tyre.setApplication("Dive");
		tyre.setTyreStatus("New");
		tyre.setChangeType("Lost MM");
		tyre.setLostMm("10");
		tyre.setFees("Ecotax");
		tyre.setTyreCasingVaue("Yes");
		tyre.setCharge("50.00");
		tyre.setBrand("Good-Year Dunlop");
		
		taskDetails1.getListOfPart().add(partDetails1);
		taskDetails1.getListOfPart().add(partDetails2);
		taskDetails2.getListOfPart().add(partDetails1);
		taskTyre.getListOfTyre().add(tyre);
		
		lab1.getListOfTask().add(taskDetails1);
		lab1.getListOfTask().add(taskDetails1);
		//lab1.getListOfTask().add(taskDetails2);
		lab2.getListOfTask().add(taskDetails1);
		lab2.getListOfTask().add(taskDetails2);
		labTyre3.getListOfTask().add(taskTyre);
		
		labourList.add(lab1);
		labourList.add(lab2);
		labourList.add(labTyre3);
		
		
		BundleDetails bundleDetailsSc = new BundleDetails();
		bundleDetailsSc.setGroupCdDsc("BRAKES");
		bundleDetailsSc.setSubGroup("BRAKE CHAMBER");
		bundleDetailsSc.setActionCd("Repla");
		
		
		BundleDetails bundleDetailsSc1 = new BundleDetails();
		bundleDetailsSc1.setGroupCdDsc("BRAKES");
		bundleDetailsSc1.setSubGroup("DRUM");
		bundleDetailsSc1.setActivity("Cam bush");
		bundleDetailsSc1.setActionCd("Repla");
		
		
		BundleDetails bundleDetailsCc = new BundleDetails();
		bundleDetailsCc.setGroupCdDsc("CHASSIS");
		bundleDetailsCc.setSubGroup("LANDING LEGS");
		bundleDetailsCc.setActivity("Mounting Bracket");
		
		BundleDetails bundleDetailsCc1 = new BundleDetails();
		bundleDetailsCc1.setGroupCdDsc("CHASSIS");
		bundleDetailsCc1.setSubGroup("BUFFERS");
		bundleDetailsCc1.setActivity("Roller Type");
		
		Bundles bundlesc = new Bundles();
		bundlesc.setBundleName("Special Check");
		bundlesc.setTotalPrice("£ 120.00");
		bundlesc.getListOfBundleDetails().add(bundleDetailsSc1);
		bundlesc.getListOfBundleDetails().add(bundleDetailsSc);
		
		Bundles bundlecc = new Bundles();
		bundlecc.setBundleName("Chassis Check");
		bundlecc.setTotalPrice("£ 100.00");
		bundlecc.getListOfBundleDetails().add(bundleDetailsCc);
		bundlecc.getListOfBundleDetails().add(bundleDetailsCc1);*/
		
		/*Fees fee1= new Fees();
		fee1.setFeesDesc("Consumables fee applied to labour");
		fee1.setCharges("99.00");
		Fees fee2= new Fees();
		fee2.setFeesDesc("Consumables fee applied to total");
		fee2.setCharges("25.00");
		Fees fee3= new Fees();
		fee3.setFeesDesc("Consumables fee applied to parts");
		fee3.setCharges("25.00");
		Fees fee4= new Fees();
		fee4.setFeesDesc("Consumables fee applied to bundle");
		fee4.setCharges("99.00");
		Fees fee5= new Fees();
		fee5.setFeesDesc("Environmental fee applied to parts");
		fee5.setCharges("99.00");
		OtherFees otherFees = new OtherFees();
		otherFees.setTotalPrice("£ 298.00");
		otherFees.getListOfFees().add(fee1);
		otherFees.getListOfFees().add(fee2);
		otherFees.getListOfFees().add(fee3);
		otherFees.getListOfFees().add(fee4);
		otherFees.getListOfFees().add(fee5);*/
		
		TotalCharge totalC = new TotalCharge();
		totalC.setSubTotal("910.00");
		totalC.setAfterConcession("850.00");
		totalC.setAfterVat("170.00");
		totalC.setTotal("1020.00");
		totalC.setVatPercentage("21%");
		
		//bm.getListOfLabour().addAll(labourList);
		//bm.getListOfUnMatchedParts().add(partDetails1);
		//bm.getListOfUnMatchedParts().add(partDetails1);
		//bm.getListOfUnMatchedParts().add(partDetails2);
		//bm.getListOfBundles().add(bundlecc);
		//bm.getListOfBundles().add(bundlesc);
		//bm.setOtherFees(otherFees);
		bm.setTotalCharges(totalC);
		return bm;
		
	}
}
