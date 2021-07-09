package com.tip.estimationreport.util;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lowagie.text.Element;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.tip.estimationreport.model.AdditionalPartDetails;
import com.tip.estimationreport.model.BundleDetails;
import com.tip.estimationreport.model.Bundles;
import com.tip.estimationreport.model.CustomJobs;
import com.tip.estimationreport.model.Fees;
import com.tip.estimationreport.model.LabourDetails;
import com.tip.estimationreport.model.OtherFees;
import com.tip.estimationreport.model.PartDetails;
import com.tip.estimationreport.model.TaskDetails;
import com.tip.estimationreport.model.TotalCharge;

public class BodyTableSummaryUtil extends BodyTableUtil{
	

	
	static final Logger logger = LoggerFactory.getLogger(BodyTableSummaryUtil.class);

	PdfPCell blankCell = new PdfPCell(new Phrase("	"));
	private String currency;
	private boolean inlineTaxDisplay;
	BodyTableSummaryUtil(String currency, boolean inlineTaxDisplay) {
		super(currency, inlineTaxDisplay);
		this.currency = currency;
		this.inlineTaxDisplay = inlineTaxDisplay;
	}
	
	public PdfPTable getLabourTable(List<LabourDetails> labourListData, Map<String, String> labelMap) {

		PdfPTable labourTable = new PdfPTable(1);
		labourTable.setSplitLate(false);
		PdfPCell labourBodyCell;
		for (LabourDetails labData : labourListData) {
			PdfPTable labourRowTable;
			if(inlineTaxDisplay) {
				labourRowTable = createTabWithWidth(4, new float[] { 5, 6, 2, 3 }, 700);
			}else {
				labourRowTable = createTabWithWidth(3, new float[] { 5, 8, 3 }, 700);
			}
			setCellData(labourRowTable, EstimationReportConstants.CELLTYPE_COL_NAME,
					labelMap.get("hlabourOrder")+" # " + labData.getLabourOrder(), Element.ALIGN_LEFT);

			setCellData(labourRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("hsupplierName"), Element.ALIGN_LEFT);
			if(inlineTaxDisplay)
				setCellData(labourRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("hvat"), Element.ALIGN_CENTER);
			
			setCellData(labourRowTable, EstimationReportConstants.CELLTYPE_COL_NAME,
					labelMap.get("htotCharge")+" + "+labelMap.get("hPrice"), Element.ALIGN_CENTER);

			setCellData(labourRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE,
					labData.getLoDescription(), Element.ALIGN_LEFT);
			setCellData(labourRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE,
					labData.getSupplierName(), Element.ALIGN_LEFT);
			if(inlineTaxDisplay)
				setCellData(labourRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE,
						currency+labData.getVatAmount(), Element.ALIGN_CENTER);
				
			setCellData(labourRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE,
					currency+labData.getTotalChargePrice(), Element.ALIGN_CENTER);

			labourBodyCell = new PdfPCell(labourRowTable);
			labourBodyCell.setPaddingLeft(10);
			labourBodyCell.setPaddingRight(20);
			labourBodyCell.setPaddingTop(10);
			labourBodyCell.setPaddingBottom(10);
			labourBodyCell.setBorder(Rectangle.NO_BORDER);
			labourBodyCell.setCellEvent(new RoundedCellEvent("TOP"));

			labourTable.addCell(labourBodyCell);

			PdfPTable taskTable = getTaskTable(labData.getTaskSummary(), labData.getLoTaskCount(), labelMap);

			labourBodyCell = new PdfPCell(taskTable);
			labourBodyCell.setPaddingLeft(10);
			labourBodyCell.setPaddingRight(10);
			labourBodyCell.setPaddingTop(8);
			labourBodyCell.setPaddingBottom(10);
			
			if(!labData.getListOfPart().isEmpty()){
				labourBodyCell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
				labourBodyCell.setBorderColor(StyleUtil.TITLE_FONT_COLOR);
				labourTable.addCell(labourBodyCell);
				
				PdfPTable partTab = new PdfPTable(1);
				PdfPCell partRowCell = new PdfPCell(getPartTable(labData.getPartSummary(),labelMap));
				partRowCell.setBorderColor(StyleUtil.TITLE_FONT_COLOR);
				partRowCell.setBackgroundColor(StyleUtil.HEADER_BORDER_COLOR);
				partRowCell.setPaddingBottom(10);
				partTab.addCell(partRowCell);
				
				labourBodyCell = new PdfPCell(partTab);
				labourBodyCell.setPaddingLeft(10);
				labourBodyCell.setPaddingRight(10);
				labourBodyCell.setPaddingBottom(10);
				labourBodyCell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
				labourBodyCell.setBorderColor(StyleUtil.TITLE_FONT_COLOR);
				labourTable.addCell(labourBodyCell);
			}else {
				labourBodyCell.setBorderColor(StyleUtil.TITLE_FONT_COLOR);
				labourTable.addCell(labourBodyCell);
			} 

			blankCell.setBorder(Rectangle.NO_BORDER);
			labourTable.addCell(blankCell);

		}

		return labourTable;
	}

	public PdfPTable getTaskTable(TaskDetails taskData, String loTaskCount, Map<String, String> labelMap) {
		
		PdfPTable taskTable = new PdfPTable(1);

		PdfPTable taskRowTable;
		if(inlineTaxDisplay) {
			taskRowTable = createTabWithWidth(4, new float[] { 8, 3, 2, 3 }, 600);
		}else {
			taskRowTable = createTabWithWidth(3, new float[] { 10, 3, 3 }, 600);
		}

		setCellData(taskRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("hTask"),
				Element.ALIGN_LEFT);
		setCellData(taskRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("hQty"),
				Element.ALIGN_CENTER);
		if(inlineTaxDisplay)
			setCellData(taskRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("hvat"), Element.ALIGN_CENTER);
		setCellData(taskRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("hlabourCharge"),
				Element.ALIGN_CENTER);

		setCellData(taskRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE,
				taskData.getLoDescription(), Element.ALIGN_LEFT);
		setCellData(taskRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE,
				loTaskCount, Element.ALIGN_CENTER);
		if(inlineTaxDisplay)
			setCellData(taskRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE,
					taskData.getVatAmount(), Element.ALIGN_CENTER);
		setCellData(taskRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE,
				taskData.getLabourCharge(), Element.ALIGN_CENTER);
		
		PdfPCell taskRowCell = new PdfPCell(taskRowTable);
		taskRowCell.setBorderColor(StyleUtil.TASK_BORDER_COLOR);
		taskRowCell.setBackgroundColor(StyleUtil.objTitleBgColor);
		taskRowCell.setPaddingBottom(10);
		taskTable.addCell(taskRowCell);
		
		return taskTable;
	}

	public PdfPTable getPartTable(PartDetails partData, Map<String, String> labelMap) {
		PdfPTable partRowTable;
		if(inlineTaxDisplay) {
			partRowTable = createTabWithWidth(4, new float[] { 8, 3, 2, 3 }, 600);
		}else {
			partRowTable = createTabWithWidth(3, new float[] { 10, 3, 3 }, 600);
		}
		

		setCellData(partRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, "", Element.ALIGN_LEFT);
		setCellData(partRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("hQty"), Element.ALIGN_CENTER);
		if(inlineTaxDisplay)
			setCellData(partRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("hvat"), Element.ALIGN_CENTER);
		setCellData(partRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("hPrice"), Element.ALIGN_CENTER);

		setCellData(partRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE, labelMap.get("hParts"), Element.ALIGN_LEFT);
		setCellData(partRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE, partData.getQuantity(), Element.ALIGN_CENTER);
		if(inlineTaxDisplay)
			setCellData(partRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE, partData.getVatAmount(), Element.ALIGN_CENTER);
		setCellData(partRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE, partData.getPrice(), Element.ALIGN_CENTER);
		
		return partRowTable;
	}

	public PdfPTable getUnmatchedPartsTab(AdditionalPartDetails additionalPartDetails, Map<String, String> labelMap){
		
		PdfPTable unmatchedPartTab = new PdfPTable(1);
		unmatchedPartTab.setSplitLate(false);
		PdfPCell umPartBodyCell;
		PdfPTable unmatchedPartRowTab = new PdfPTable(3);
		if(inlineTaxDisplay) {
			unmatchedPartRowTab = createTabWithWidth(4, new float[] { 10, 2, 2, 2 }, 700);
		}else {
			unmatchedPartRowTab = createTabWithWidth(3, new float[] { 10,3,3 }, 700);
		}
		
		setCellData(unmatchedPartRowTab, EstimationReportConstants.CELLTYPE_COL_NAME, "", Element.ALIGN_LEFT);
		setCellData(unmatchedPartRowTab, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("hParts"), Element.ALIGN_CENTER);
		if(inlineTaxDisplay)
			setCellData(unmatchedPartRowTab, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("hvat"), Element.ALIGN_CENTER);
		
		setCellData(unmatchedPartRowTab, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("htotPrice"), Element.ALIGN_CENTER);
		
		setCellData(unmatchedPartRowTab, EstimationReportConstants.CELLTYPE_COL_VALUE,"Additional Parts", Element.ALIGN_LEFT);
		setCellData(unmatchedPartRowTab, EstimationReportConstants.CELLTYPE_COL_VALUE,additionalPartDetails.getPartsCount(), Element.ALIGN_CENTER);
		if(inlineTaxDisplay)
			setCellData(unmatchedPartRowTab, EstimationReportConstants.CELLTYPE_COL_VALUE,currency+additionalPartDetails.getTotalVat(), Element.ALIGN_CENTER);
		setCellData(unmatchedPartRowTab, EstimationReportConstants.CELLTYPE_COL_VALUE,currency+additionalPartDetails.getTotalPrice(), Element.ALIGN_CENTER);
		
		umPartBodyCell = new PdfPCell(unmatchedPartRowTab);
		umPartBodyCell.setPaddingLeft(10);
		umPartBodyCell.setPaddingRight(20);
		umPartBodyCell.setPaddingTop(10);
		umPartBodyCell.setPaddingBottom(10);
		//umPartBodyCell.setBorder(Rectangle.NO_BORDER);
		umPartBodyCell.setBorderColor(StyleUtil.TITLE_FONT_COLOR);
		//umPartBodyCell.setCellEvent(new RoundedCellEvent("TOP"));

		unmatchedPartTab.addCell(umPartBodyCell);
	
		blankCell.setBorder(Rectangle.NO_BORDER);
		//blankCell.setBorderColor(StyleUtil.HEADER_BORDER_COLOR);
		unmatchedPartTab.addCell(blankCell);
		
		return unmatchedPartTab;
		
	}
	public PdfPTable getCustomJobTab(CustomJobs customJobs, Map<String, String> labelMap){
		
		PdfPTable customJobsMainTab = new PdfPTable(1);
		customJobsMainTab.setSplitLate(false);
		PdfPCell customJobsMainCell;
		PdfPTable customJobHeaderRowTab;
		if(inlineTaxDisplay) {
			customJobHeaderRowTab = createTabWithWidth(3, new float[] { 10,3,3 }, 700);
		}else {
			customJobHeaderRowTab = createTabWithWidth(2, new float[] { 10,3}, 700);
		}
		
		
		setCellData(customJobHeaderRowTab, EstimationReportConstants.CELLTYPE_COL_NAME, "", Element.ALIGN_LEFT);
		if(inlineTaxDisplay)
			setCellData(customJobHeaderRowTab, EstimationReportConstants.CELLTYPE_COL_NAME,labelMap.get("hvat"), Element.ALIGN_CENTER);
		setCellData(customJobHeaderRowTab, EstimationReportConstants.CELLTYPE_COL_NAME,labelMap.get("htotPrice"), Element.ALIGN_CENTER);
		setCellData(customJobHeaderRowTab, EstimationReportConstants.CELLTYPE_COL_VALUE,labelMap.get("hcustJobs"), Element.ALIGN_LEFT);
		if(inlineTaxDisplay)
			setCellData(customJobHeaderRowTab, EstimationReportConstants.CELLTYPE_COL_VALUE,currency+customJobs.getVatAmount(), Element.ALIGN_CENTER);
		setCellData(customJobHeaderRowTab, EstimationReportConstants.CELLTYPE_COL_VALUE,currency+customJobs.getTotalPrice(), Element.ALIGN_CENTER);
		
		customJobsMainCell = new PdfPCell(customJobHeaderRowTab);
		customJobsMainCell.setPaddingLeft(10);
		customJobsMainCell.setPaddingRight(20);
		customJobsMainCell.setPaddingTop(10);
		customJobsMainCell.setPaddingBottom(10);
		customJobsMainCell.setBorder(Rectangle.NO_BORDER);
		customJobsMainCell.setCellEvent(new RoundedCellEvent("TOP"));
		customJobsMainTab.addCell(customJobsMainCell);
		
		PdfPTable bundlesTable = getBundleTab(customJobs.getListOfBundle());
		
		customJobsMainCell = new PdfPCell(bundlesTable);
		customJobsMainCell.setPaddingLeft(10);
		customJobsMainCell.setPaddingRight(20);
		customJobsMainCell.setPaddingTop(10);
		customJobsMainCell.setPaddingBottom(10);
		customJobsMainCell.setBorderColor(StyleUtil.TITLE_FONT_COLOR);
		customJobsMainTab.addCell(customJobsMainCell);
		return customJobsMainTab;
		
	}
	
	public PdfPTable getBundleTab(List<Bundles> bundleList){
		
		PdfPTable bundlesMainTab = new PdfPTable(1);
		bundlesMainTab.setSplitLate(false);
		PdfPCell bundleBodyCell;
		PdfPTable bundleDataTab = new PdfPTable(1);
		int count= 1;
		for (Bundles bundels : bundleList) {
			//count++;
			PdfPCell  bundleDataCell;
			if(bundleList.size()==count)
				bundleDataCell = new PdfPCell(getBundleHeader(bundels.getBundleName(),bundels.getTotalPrice(),true));
			else
				bundleDataCell = new PdfPCell(getBundleHeader(bundels.getBundleName(),bundels.getTotalPrice(),false));
			
			bundleDataCell.setBorder(Rectangle.NO_BORDER);
			bundleDataTab.addCell(bundleDataCell);
			count++;
		}
		bundleBodyCell = new PdfPCell(bundleDataTab);
		bundleBodyCell.setBorderColor(StyleUtil.TITLE_FONT_COLOR);
		bundleBodyCell.setBackgroundColor(StyleUtil.objTitleBgColor);
		bundlesMainTab.addCell(bundleBodyCell);
		return bundlesMainTab;
		
	}

	public PdfPTable getBundleHeader(String bundleName, String price, boolean flagLastIndex) {

		StyleUtil styleUtilValue = new StyleUtil(EstimationReportConstants.CELLTYPE_COL_VALUE);
		
		String [] padding = flagLastIndex?new String[] { "12L", "7T", "7B"}:new String[] { "12L", "7T"};
		PdfPTable bundleDetRowTab = createTabWithWidth(2, new float[] { 14f, 2f }, 600);
		bundleDetRowTab.addCell(styleUtilValue.createCell(bundleName, Element.ALIGN_LEFT, padding));
		bundleDetRowTab.addCell(styleUtilValue.createCell(price, Element.ALIGN_LEFT, padding));
		
		return bundleDetRowTab;
		
	}
	
	public PdfPTable getOtherFeesTab(OtherFees otherFees, Map<String, String> labelMap) {
		
		PdfPTable otherFeesTab = new PdfPTable(1);
		otherFeesTab.setSplitLate(false);
		PdfPCell otherFeesBodyCell;
		PdfPTable otherFeesRowTab;
		if(inlineTaxDisplay) {
			otherFeesRowTab = createTabWithWidth(3, new float[] { 12,2,2 }, 700);
		}else {
			otherFeesRowTab = createTabWithWidth(2, new float[] { 13,3 }, 700);
		}
		
		setCellData(otherFeesRowTab, EstimationReportConstants.CELLTYPE_COL_NAME, "", Element.ALIGN_LEFT);
		if(inlineTaxDisplay)
			setCellData(otherFeesRowTab, EstimationReportConstants.CELLTYPE_COL_NAME,labelMap.get("hvat"), Element.ALIGN_CENTER);
		setCellData(otherFeesRowTab, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("htotPrice"), Element.ALIGN_CENTER);
		
		setCellData(otherFeesRowTab, EstimationReportConstants.CELLTYPE_COL_VALUE,labelMap.get("hotherFees"), Element.ALIGN_LEFT);
		if(inlineTaxDisplay)
			setCellData(otherFeesRowTab, EstimationReportConstants.CELLTYPE_COL_VALUE,currency+otherFees.getVatAmount(), Element.ALIGN_CENTER);
		setCellData(otherFeesRowTab, EstimationReportConstants.CELLTYPE_COL_VALUE,currency+otherFees.getTotalPrice(), Element.ALIGN_CENTER);
		
		otherFeesBodyCell = new PdfPCell(otherFeesRowTab);
		otherFeesBodyCell.setPaddingLeft(10);
		otherFeesBodyCell.setPaddingRight(20);
		otherFeesBodyCell.setPaddingTop(10);
		otherFeesBodyCell.setPaddingBottom(10);
		otherFeesBodyCell.setBorder(Rectangle.NO_BORDER);
		otherFeesBodyCell.setCellEvent(new RoundedCellEvent("TOP"));

		otherFeesTab.addCell(otherFeesBodyCell);

		PdfPTable feesRowTab = new PdfPTable(1);
		/*PdfPCell  feesRowCell;
		for (Fees fees : otherFees.getListOfFees()) {
			feesRowCell = new PdfPCell(getFeesTab(fees,labelMap));
			feesRowCell.setBorderColor(StyleUtil.TITLE_FONT_COLOR);
			feesRowCell.setBackgroundColor(StyleUtil.HEADER_BORDER_COLOR);
			feesRowCell.setPaddin
			feesRowTab.addCell(feesRowCell);
		}*/

		otherFeesBodyCell = new PdfPCell(getFeesTab(otherFees.getListOfFees(),labelMap));
		otherFeesBodyCell.setPaddingLeft(10);
		otherFeesBodyCell.setPaddingRight(10);
		otherFeesBodyCell.setPaddingTop(8);
		otherFeesBodyCell.setPaddingBottom(10);
		otherFeesBodyCell.setBorderColor(StyleUtil.TITLE_FONT_COLOR);
		otherFeesBodyCell.setBackgroundColor(StyleUtil.objTitleBgColor);
		otherFeesTab.addCell(otherFeesBodyCell);

		blankCell.setBorder(Rectangle.NO_BORDER);
		otherFeesTab.addCell(blankCell);
		return otherFeesTab;
	}
	
	public PdfPTable getFeesTab(List<Fees> listOfFees, Map<String, String> labelMap) {
		PdfPTable feesRowTable;
		String [] paddingCustom =  new String[] { "12L", "10B" };
		if(inlineTaxDisplay) {
			feesRowTable = createTabWithWidth(3, new float[] { 12,2,2 }, 700);
		}else {
			feesRowTable = createTabWithWidth(2, new float[] { 13,3 }, 700);
		}
		
		setCellData(feesRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, "", Element.ALIGN_LEFT);
		if(inlineTaxDisplay)
			setCellData(feesRowTable, EstimationReportConstants.CELLTYPE_COL_NAME,labelMap.get("hvat"), Element.ALIGN_CENTER);
		setCellData(feesRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, "Charges", Element.ALIGN_LEFT);
		for(Fees fees: listOfFees) {
			setCellData(feesRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE, fees.getFeesDesc(), Element.ALIGN_LEFT, paddingCustom);
			if(inlineTaxDisplay)
				setCellData(feesRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE,fees.getVatAmount(), Element.ALIGN_CENTER, paddingCustom);
			setCellData(feesRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE, fees.getCharges(), Element.ALIGN_LEFT, paddingCustom);
		}
		
		return feesRowTable;
	}

}
