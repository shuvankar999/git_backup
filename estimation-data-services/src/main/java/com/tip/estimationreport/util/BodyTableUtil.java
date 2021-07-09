package com.tip.estimationreport.util;

import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.tip.estimationreport.model.AdditionalPartDetails;
import com.tip.estimationreport.model.AdminFees;
import com.tip.estimationreport.model.BundleDetails;
import com.tip.estimationreport.model.Bundles;
import com.tip.estimationreport.model.CustomJobs;
import com.tip.estimationreport.model.Fees;
import com.tip.estimationreport.model.ImageDetails;
import com.tip.estimationreport.model.LabourDetails;
import com.tip.estimationreport.model.OtherFees;
import com.tip.estimationreport.model.PartDetails;
import com.tip.estimationreport.model.TaskDetails;
import com.tip.estimationreport.model.TotalCharge;
import com.tip.estimationreport.model.Tyre;

public class BodyTableUtil {
	
	static final Logger logger = LoggerFactory.getLogger(BodyTableUtil.class);

	PdfPCell blankCell = new PdfPCell(new Phrase("	"));
	private String currency;
	private boolean inlineTaxDisplay;
	BodyTableUtil(String currency, boolean inlineTaxDisplay){
		this.currency = currency;
		this.inlineTaxDisplay = inlineTaxDisplay;
	}
	public void setCellData(PdfPTable table, String cellType, String value, int align) {

		StyleUtil styleUtil = new StyleUtil(cellType);
		if (cellType.equalsIgnoreCase(EstimationReportConstants.CELLTYPE_COL_NAME)) {
			PdfPCell cell = styleUtil.createCell(value, align, new String[] { "12L", "7T" });
			//cell.setBorder(Rectangle.BOX);
			table.addCell(cell);
		} else if (cellType.equalsIgnoreCase(EstimationReportConstants.CELLTYPE_COL_VALUE)) {
			PdfPCell cell = styleUtil.createCell(value, align, new String[] { "12L" });
			//cell.setBorder(Rectangle.BOX);
			table.addCell(cell);
		}
	}
	
	public void setCellData(PdfPTable table, int colSpan, String cellType, String value, int align) {
		
		StyleUtil styleUtil = new StyleUtil(cellType);
		if (cellType.equalsIgnoreCase(EstimationReportConstants.CELLTYPE_COL_NAME)) {
			PdfPCell cell = styleUtil.createCell(value, align, colSpan, new String[] { "12L", "7T" });
			table.addCell(cell);
		} else if (cellType.equalsIgnoreCase(EstimationReportConstants.CELLTYPE_COL_VALUE)) {
			PdfPCell cell = styleUtil.createCell(value, align, colSpan, new String[] { "12L" });
			table.addCell(cell);
		}
	}
	
	public void setCellData(PdfPTable table, String cellType, String value, int align, String[] padding) {
		
		StyleUtil styleUtil = new StyleUtil(cellType);
		if (cellType.equalsIgnoreCase(EstimationReportConstants.CELLTYPE_COL_NAME)) {
			PdfPCell cell = styleUtil.createCell(value, align, padding);
			table.addCell(cell);
		} else if (cellType.equalsIgnoreCase(EstimationReportConstants.CELLTYPE_COL_VALUE)) {
			PdfPCell cell = styleUtil.createCell(value, align, padding);
			table.addCell(cell);
		}
	}


	public PdfPTable getLabourTable(List<LabourDetails> labourListData, Map<String, String> labelMap) {

		PdfPTable labourTable = new PdfPTable(1);
		labourTable.setSplitLate(false);
		PdfPCell labourBodyCell;
		for (LabourDetails labData : labourListData) {
			PdfPTable labourRowTable;
			if(inlineTaxDisplay) {
				labourRowTable = createTabWithWidth(7, new float[] { 2.5f, 1, 1.5f, 4, 2, 2, 3 }, 700);
			}else {
				labourRowTable = createTabWithWidth(6, new float[] { 2.5f, 1, 1.5f, 4, 4, 3 }, 700);
			}
			setCellData(labourRowTable, EstimationReportConstants.CELLTYPE_COL_NAME,
					labelMap.get("hlabourOrder")+" # " + labData.getLabourOrder(), Element.ALIGN_LEFT);
			setCellData(labourRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("hloTask"), Element.ALIGN_LEFT);

			if (labData.getPartsCount()!=null && !labData.getPartsCount().isEmpty())
				setCellData(labourRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("hParts"), Element.ALIGN_LEFT);
			else if (labData.getTyresCount()!=null && !labData.getTyresCount().isEmpty())
				setCellData(labourRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, "Tyre(s)", Element.ALIGN_LEFT);
			else
				setCellData(labourRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("hParts"), Element.ALIGN_LEFT);

			setCellData(labourRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("hsupplierName"), Element.ALIGN_LEFT);
			setCellData(labourRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("hlabourTime"), Element.ALIGN_CENTER);
			if(inlineTaxDisplay)
				setCellData(labourRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("hvat"), Element.ALIGN_CENTER);
			
			setCellData(labourRowTable, EstimationReportConstants.CELLTYPE_COL_NAME,
					labelMap.get("htotCharge")+" + "+labelMap.get("hPrice"), Element.ALIGN_CENTER);

			setCellData(labourRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE,
					labData.getLoDescription(), Element.ALIGN_LEFT);
			setCellData(labourRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE,
					labData.getLoTaskCount(), Element.ALIGN_LEFT);
			if (labData.getPartsCount()!=null && !labData.getPartsCount().isEmpty())
				setCellData(labourRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE,
						labData.getPartsCount(), Element.ALIGN_LEFT);
			else if (labData.getTyresCount()!=null &&!labData.getTyresCount().isEmpty())
				setCellData(labourRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE,
						labData.getTyresCount(), Element.ALIGN_LEFT);
			else
				setCellData(labourRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE,
						"", Element.ALIGN_LEFT);
			setCellData(labourRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE,
					labData.getSupplierName(), Element.ALIGN_LEFT);
			setCellData(labourRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE,
					labData.getLabourTime(), Element.ALIGN_CENTER);
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

			PdfPTable taskTable = getTaskTable(labData.getListOfTask(), labelMap);

			labourBodyCell = new PdfPCell(taskTable);
			labourBodyCell.setPaddingLeft(10);
			labourBodyCell.setPaddingRight(10);
			labourBodyCell.setPaddingTop(8);
			labourBodyCell.setPaddingBottom(10);
			//labourBodyCell.setBorderColor(StyleUtil.TITLE_FONT_COLOR);
			
			if(!labData.getListOfPart().isEmpty()){
				labourBodyCell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
				labourBodyCell.setBorderColor(StyleUtil.TITLE_FONT_COLOR);
				labourTable.addCell(labourBodyCell);
				
				PdfPTable partTab = new PdfPTable(1);
				for (PartDetails partData : labData.getListOfPart()) {
					PdfPCell partRowCell = new PdfPCell(getPartTable(partData,labelMap));
					partRowCell.setBorderColor(StyleUtil.TITLE_FONT_COLOR);
					partRowCell.setBackgroundColor(StyleUtil.HEADER_BORDER_COLOR);
					partRowCell.setPaddingBottom(10);
					partTab.addCell(partRowCell);
				}
				
				labourBodyCell = new PdfPCell(partTab);
				labourBodyCell.setPaddingLeft(10);
				labourBodyCell.setPaddingRight(10);
				labourBodyCell.setPaddingBottom(10);
				if(labData.getCustomerComments() != null && !labData.getCustomerComments().trim().isEmpty()) {
					labourBodyCell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
					labourBodyCell.setBorderColor(StyleUtil.TITLE_FONT_COLOR);
					labourTable.addCell(labourBodyCell);
					labourTable.addCell(setLabourComment(labelMap, labData));
				}else {
					labourBodyCell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					labourBodyCell.setBorderColor(StyleUtil.TITLE_FONT_COLOR);
					labourTable.addCell(labourBodyCell);
				}
			} else{
				// labourBodyCell.setPaddingBottom(10);
				if(labData.getCustomerComments() != null && !labData.getCustomerComments().trim().isEmpty()) {
					labourBodyCell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
					labourBodyCell.setBorderColor(StyleUtil.TITLE_FONT_COLOR);
					labourTable.addCell(labourBodyCell);
					labourTable.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.RIGHT);
					labourTable.getDefaultCell().setBorderColor(StyleUtil.TITLE_FONT_COLOR);
					labourTable.addCell(setLabourComment(labelMap, labData));
				}else {
					labourBodyCell.setBorderColor(StyleUtil.TITLE_FONT_COLOR);
					labourTable.addCell(labourBodyCell);
				}
			}
			
			blankCell.setBorder(Rectangle.NO_BORDER);
			// blankCell.setBorderColor(Color.RED);
			labourTable.addCell(blankCell);
		}

		return labourTable;
	}
	private PdfPTable setLabourComment(Map<String, String> labelMap,LabourDetails labData) {
		PdfPTable table = new PdfPTable(1);
		setCellData(table, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("hComments"),
				Element.ALIGN_LEFT);
		setCellData(table, EstimationReportConstants.CELLTYPE_COL_VALUE, labData.getCustomerComments(),
				Element.ALIGN_LEFT);
		return table;
	}

	public PdfPTable getTaskTable(List<TaskDetails> listOfTaskData, Map<String, String> labelMap) {
		PdfPTable taskTable = new PdfPTable(1);
		for (TaskDetails taskData : listOfTaskData) {
			PdfPTable taskRowTable;
			if(inlineTaxDisplay) {
				taskRowTable = createTabWithWidth(5, new float[] { 8, 2, 2, 2, 2 }, 600);
			}else {
				taskRowTable = createTabWithWidth(4, new float[] { 8, 2, 3, 3 }, 600);
			}

			setCellData(taskRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("hTask") + taskData.getLoTaskNr(),
					Element.ALIGN_LEFT);
			setCellData(taskRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("hsoldTime"),
					Element.ALIGN_CENTER);
			setCellData(taskRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("hlabourRate"), Element.ALIGN_CENTER);
			if(inlineTaxDisplay)
				setCellData(taskRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("hvat"), Element.ALIGN_CENTER);
			setCellData(taskRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("hlabourCharge"),
					Element.ALIGN_CENTER);

			setCellData(taskRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE,
					taskData.getTaskDescription(), Element.ALIGN_LEFT);
			setCellData(taskRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE,
					taskData.getTargetTime(), Element.ALIGN_CENTER);
			setCellData(taskRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE,
					taskData.getLabourRate(), Element.ALIGN_CENTER);
			if(inlineTaxDisplay)
				setCellData(taskRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE,
						taskData.getVatAmount(), Element.ALIGN_CENTER);
			setCellData(taskRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE,
					taskData.getLabourCharge(), Element.ALIGN_CENTER);
			int colspan = 4;
			if(inlineTaxDisplay) {
				colspan = 5;
			}
			
			if(taskData.getTaskComments()!=null&&!taskData.getTaskComments().isEmpty()) {
				setCellData(taskRowTable, colspan, EstimationReportConstants.CELLTYPE_COL_NAME,
						labelMap.get("hComments"), Element.ALIGN_LEFT);
				setCellData(taskRowTable, colspan, EstimationReportConstants.CELLTYPE_COL_VALUE,
						taskData.getTaskComments(), Element.ALIGN_LEFT);	
			}
			
			PdfPCell taskRowCell = new PdfPCell(taskRowTable);
			taskRowCell.setBorderColor(StyleUtil.TASK_BORDER_COLOR);
			taskRowCell.setBackgroundColor(StyleUtil.objTitleBgColor);
			taskRowCell.setPaddingBottom(10);
			taskTable.addCell(taskRowCell);
			
			if(!taskData.getListOfTyre().isEmpty()){
				for (Tyre tyre : taskData.getListOfTyre()) {
					taskRowCell = new PdfPCell(getTyreTab(tyre,labelMap));
					taskRowCell.setBorderColor(StyleUtil.TITLE_FONT_COLOR);
					taskRowCell.setBackgroundColor(StyleUtil.HEADER_BORDER_COLOR);
					taskRowCell.setPaddingBottom(10);
					taskTable.addCell(taskRowCell);
				}
			}
		}
		return taskTable;
	}

	public PdfPTable getPartTable(PartDetails partData, Map<String, String> labelMap) {
		PdfPTable partRowTable;
		if(inlineTaxDisplay) {
			partRowTable = createTabWithWidth(5, new float[] { 5, 5, 2, 2, 2 }, 600);
		}else {
			partRowTable = createTabWithWidth(4, new float[] { 5, 5,3, 3 }, 600);
		}
		

		setCellData(partRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("hpartNr"), Element.ALIGN_LEFT);
		setCellData(partRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("hpartDesc"), Element.ALIGN_LEFT);
		setCellData(partRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("hQty"), Element.ALIGN_CENTER);
		if(inlineTaxDisplay)
			setCellData(partRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("hvat"), Element.ALIGN_CENTER);
		setCellData(partRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("hPrice"), Element.ALIGN_CENTER);

		setCellData(partRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE, partData.getPartNr(), Element.ALIGN_LEFT);
		setCellData(partRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE,
				partData.getPartDescription(), Element.ALIGN_LEFT);
		setCellData(partRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE, partData.getQuantity(), Element.ALIGN_CENTER);
		if(inlineTaxDisplay)
			setCellData(partRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE, partData.getVatAmount(), Element.ALIGN_CENTER);
		setCellData(partRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE, partData.getPrice(), Element.ALIGN_CENTER);
		
		return partRowTable;
	}
	
	public PdfPTable getTyreTab(Tyre tyre, Map<String, String> labelMap) {
		PdfPTable tyreRowTable;
		if(inlineTaxDisplay) {
			tyreRowTable = createTabWithWidth(4, new float[] { 3, 4, 6, 3 }, 600);
			/*tyreRowTable = new PdfPTable(4);
			try {
				tyreRowTable.setTotalWidth(600);
				tyreRowTable.setWidths(new float[] { 3, 4, 6, 3 });
			} catch (DocumentException e) {
				e.printStackTrace();
			}*/
		}else {
			tyreRowTable = createTabWithWidth(4, new float[] { 3, 4, 6, 3 }, 600);
			/*tyreRowTable = new PdfPTable(4);
			try {
				tyreRowTable.setTotalWidth(600);
				tyreRowTable.setWidths(new float[] { 3, 4, 6, 3 });
			} catch (DocumentException e) {
				e.printStackTrace();
			}*/
		}

		setCellData(tyreRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("htyreSize"), Element.ALIGN_LEFT);
		setCellData(tyreRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("hApp"), Element.ALIGN_LEFT);
		setCellData(tyreRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("hBrand"), Element.ALIGN_LEFT);
		setCellData(tyreRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("hchargeType"), Element.ALIGN_CENTER);

		setCellData(tyreRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE, tyre.getTyreSize(), Element.ALIGN_LEFT);
		setCellData(tyreRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE,
				tyre.getApplication(), Element.ALIGN_LEFT);
		setCellData(tyreRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE, tyre.getBrand(), Element.ALIGN_LEFT);
		setCellData(tyreRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE, tyre.getCharge(), Element.ALIGN_CENTER);
		

		setCellData(tyreRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("htyreStatus"), Element.ALIGN_LEFT);
		setCellData(tyreRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("hchargeType"), Element.ALIGN_LEFT);
		setCellData(tyreRowTable, 2, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("hlostMM"), Element.ALIGN_LEFT);
		setCellData(tyreRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE, tyre.getTyreStatus(), Element.ALIGN_LEFT);
		setCellData(tyreRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE, tyre.getChangeType(), Element.ALIGN_LEFT);
		setCellData(tyreRowTable, 2, EstimationReportConstants.CELLTYPE_COL_VALUE, tyre.getLostMm(), Element.ALIGN_LEFT);
		

		setCellData(tyreRowTable, 4, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("hFees"), Element.ALIGN_LEFT);
		setCellData(tyreRowTable, 4, EstimationReportConstants.CELLTYPE_COL_VALUE, tyre.getFees(), Element.ALIGN_LEFT);
		

		setCellData(tyreRowTable, 4, EstimationReportConstants.CELLTYPE_COL_NAME, "Tyre Casing Value", Element.ALIGN_LEFT);
		setCellData(tyreRowTable, 3, EstimationReportConstants.CELLTYPE_COL_VALUE, tyre.getTyreCasingVaue(), Element.ALIGN_LEFT);
		setCellData(tyreRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE, tyre.getCharge(), Element.ALIGN_CENTER);
		
		
		return tyreRowTable;
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
		umPartBodyCell.setBorder(Rectangle.NO_BORDER);
		umPartBodyCell.setCellEvent(new RoundedCellEvent("TOP"));

		unmatchedPartTab.addCell(umPartBodyCell);

		PdfPTable umPartRowTab = new PdfPTable(1);
		PdfPCell  umPartRowCell;
		for (PartDetails partData : additionalPartDetails.getListOfUnMatchedParts()) {
			umPartRowCell = new PdfPCell(getPartTable(partData,labelMap));
			umPartRowCell.setBorderColor(StyleUtil.TITLE_FONT_COLOR);
			umPartRowCell.setBackgroundColor(StyleUtil.HEADER_BORDER_COLOR);
			umPartRowCell.setPaddingBottom(10);
			umPartRowTab.addCell(umPartRowCell);
		}

		umPartBodyCell = new PdfPCell(umPartRowTab);
		umPartBodyCell.setPaddingLeft(10);
		umPartBodyCell.setPaddingRight(10);
		umPartBodyCell.setPaddingTop(8);
		umPartBodyCell.setPaddingBottom(10);
		umPartBodyCell.setBorderColor(StyleUtil.TITLE_FONT_COLOR);
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
		for (Bundles bundels : bundleList) {	
			PdfPTable bundleDetTab = new PdfPTable(1);
			PdfPCell  bundleDetRowCell;
			bundleDetRowCell = new PdfPCell(getBundleHeader(bundels.getBundleName(),bundels.getTotalPrice()));
			bundleDetRowCell.setBorderColor(StyleUtil.TITLE_FONT_COLOR);
			bundleDetRowCell.setBackgroundColor(StyleUtil.objTitleBgColor);
			//bundleDetRowCell.setPaddingBottom(10);
			bundleDetTab.addCell(bundleDetRowCell);
			int taskCount = 0;
			for (BundleDetails bundleDetails : bundels.getListOfBundleDetails()) {
				taskCount++;
				bundleDetRowCell = new PdfPCell(getBundleDetailsTab(bundleDetails,String.format("%02d", taskCount)));
				bundleDetRowCell.setBorderColor(StyleUtil.TITLE_FONT_COLOR);
				bundleDetRowCell.setBackgroundColor(StyleUtil.objTitleBgColor);
				bundleDetRowCell.setPaddingBottom(10);
				bundleDetTab.addCell(bundleDetRowCell);
			}
	
			bundleBodyCell = new PdfPCell(bundleDetTab);
			bundlesMainTab.addCell(bundleBodyCell);

			blankCell.setBorder(Rectangle.NO_BORDER);
			// blankCell.setBorderColor(Color.RED);
			bundlesMainTab.addCell(blankCell);
		}
		
		return bundlesMainTab;
		
	}
	
	private PdfPTable getBundleDetailsTab(BundleDetails bundleDetails, String taskNo) {
		
		String bDescription = "";
		String arrow = "  >  ";
		StyleUtil styleUtilLable = new StyleUtil(EstimationReportConstants.CELLTYPE_COL_NAME);
		StyleUtil styleUtilValue = new StyleUtil(EstimationReportConstants.CELLTYPE_COL_VALUE);
		PdfPTable bundleDetRowTab = createTabWithWidth(2, new float[] { 1.5f, 14.5f }, 600);
		if(bundleDetails.getGroupCdDsc()!=null)
			if(!bundleDetails.getGroupCdDsc().trim().isEmpty())
				bDescription = bundleDetails.getGroupCdDsc();
		if(bundleDetails.getSubGroup()!=null)
			if(!bundleDetails.getSubGroup().trim().isEmpty())
				bDescription = bDescription+arrow+bundleDetails.getSubGroup();
		if(bundleDetails.getActivity()!=null)
			if(!bundleDetails.getActivity().trim().isEmpty())
				bDescription = bDescription+arrow+bundleDetails.getActivity();
		if(bundleDetails.getActionCd()!=null)
			if(!bundleDetails.getActionCd().trim().isEmpty())
				bDescription = bDescription+arrow+bundleDetails.getActionCd();
		if(bundleDetails.getMaintenanceAction()!=null)
			if(!bundleDetails.getMaintenanceAction().trim().isEmpty())
				bDescription = bDescription+arrow+bundleDetails.getMaintenanceAction();
		if(bundleDetails.getManufacturer()!=null)
			if(!bundleDetails.getManufacturer().trim().isEmpty())
				bDescription = bDescription+arrow+bundleDetails.getManufacturer();
		if(bundleDetails.getSupplierPartNr()!=null)
			if(!bundleDetails.getSupplierPartNr().trim().isEmpty())
				bDescription = bDescription+arrow+bundleDetails.getSupplierPartNr();
		
		bundleDetRowTab.addCell(styleUtilLable.createCell("Task No "+taskNo, Element.ALIGN_LEFT, new String[] { "12L", "8T" }));
		bundleDetRowTab.addCell(styleUtilValue.createCell(bDescription, Element.ALIGN_LEFT, new String[] { "12L", "7T" }));
		
		return bundleDetRowTab;
		
	}
	
	
	public PdfPTable getBundleHeader(String bundleName, String price) {

		StyleUtil styleUtilValue = new StyleUtil(EstimationReportConstants.CELLTYPE_COL_VALUE);
		String [] padding = new String[] { "12L", "7T","7B"};
		PdfPTable bundleDetRowTab = createTabWithWidth(2, new float[] { 14f, 2f }, 600);
		bundleDetRowTab.addCell(styleUtilValue.createCell(bundleName, Element.ALIGN_LEFT, padding));
		bundleDetRowTab.addCell(styleUtilValue.createCell(price, Element.ALIGN_LEFT, padding));
		
		return bundleDetRowTab;
		
	}

	public PdfPTable getOtherFeesTab(OtherFees otherFees, Map<String, String> labelMap) {
		
		PdfPTable otherFeesTab = new PdfPTable(1);
		otherFeesTab.setSplitLate(false);
		PdfPCell otherFeesCell;
		PdfPTable otherFeesHeaderTab;
		if(inlineTaxDisplay) {
			otherFeesHeaderTab = createTabWithWidth(3, new float[] { 12,2,2 }, 700);
		}else {
			otherFeesHeaderTab = createTabWithWidth(2, new float[] { 13,3 }, 700);
		}
		
		setCellData(otherFeesHeaderTab, EstimationReportConstants.CELLTYPE_COL_NAME, "", Element.ALIGN_LEFT);
		if(inlineTaxDisplay)
			setCellData(otherFeesHeaderTab, EstimationReportConstants.CELLTYPE_COL_NAME,labelMap.get("hvat"), Element.ALIGN_CENTER);
		setCellData(otherFeesHeaderTab, EstimationReportConstants.CELLTYPE_COL_NAME, labelMap.get("htotPrice"), Element.ALIGN_CENTER);
		
		setCellData(otherFeesHeaderTab, EstimationReportConstants.CELLTYPE_COL_VALUE,labelMap.get("hotherFees"), Element.ALIGN_LEFT);
		if(inlineTaxDisplay)
			setCellData(otherFeesHeaderTab, EstimationReportConstants.CELLTYPE_COL_VALUE,currency+otherFees.getVatAmount(), Element.ALIGN_CENTER);
		setCellData(otherFeesHeaderTab, EstimationReportConstants.CELLTYPE_COL_VALUE,currency+otherFees.getTotalPrice(), Element.ALIGN_CENTER);
		
		otherFeesCell = new PdfPCell(otherFeesHeaderTab);
		otherFeesCell.setPaddingLeft(10);
		otherFeesCell.setPaddingRight(20);
		otherFeesCell.setPaddingTop(10);
		otherFeesCell.setPaddingBottom(10);
		otherFeesCell.setBorder(Rectangle.NO_BORDER);
		otherFeesCell.setCellEvent(new RoundedCellEvent("TOP"));

		otherFeesTab.addCell(otherFeesCell);

		PdfPTable otherFeesBodyTab = new PdfPTable(1);
		otherFeesBodyTab.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		
		PdfPTable feesRowTab = new PdfPTable(1);
		PdfPCell  feesRowCell;
		for (Fees fees : otherFees.getListOfFees()) {
			feesRowCell = new PdfPCell(getFeesTab(fees,labelMap));
			feesRowCell.setBorderColor(StyleUtil.TITLE_FONT_COLOR);
			feesRowCell.setBackgroundColor(StyleUtil.HEADER_BORDER_COLOR);
			feesRowCell.setPaddingBottom(10);
			feesRowTab.addCell(feesRowCell);
		}
		otherFeesBodyTab.addCell(feesRowTab);
		
/*		otherFeesCell = new PdfPCell(feesRowTab);
		otherFeesCell.setPaddingLeft(10);
		otherFeesCell.setPaddingRight(10);
		otherFeesCell.setPaddingTop(8);
		otherFeesCell.setPaddingBottom(10);
		otherFeesCell.setBorderColor(StyleUtil.TITLE_FONT_COLOR);
		otherFeesCell.setBackgroundColor(StyleUtil.objTitleBgColor);
		otherFeesTab.addCell(feesRowTab);
		
		blankCell.setBorder(Rectangle.NO_BORDER);
		otherFeesTab.addCell(blankCell);*/
		
		PdfPTable adminFeesRowTab = new PdfPTable(1);
		PdfPCell  adminFeesRowCell;
		for (AdminFees fees : otherFees.getListOfAdminFees()) {
			adminFeesRowCell = new PdfPCell(getAdminFees(fees,labelMap));
			adminFeesRowCell.setBorderColor(StyleUtil.TITLE_FONT_COLOR);
			adminFeesRowCell.setBackgroundColor(StyleUtil.HEADER_BORDER_COLOR);
			adminFeesRowCell.setPaddingBottom(10);
			adminFeesRowTab.addCell(adminFeesRowCell);
		}
		
		otherFeesBodyTab.addCell(adminFeesRowTab);

		otherFeesCell = new PdfPCell(otherFeesBodyTab);
		otherFeesCell.setPaddingLeft(10);
		otherFeesCell.setPaddingRight(10);
		otherFeesCell.setPaddingTop(8);
		otherFeesCell.setPaddingBottom(10);
		otherFeesCell.setBorderColor(StyleUtil.TITLE_FONT_COLOR);
		otherFeesCell.setBackgroundColor(StyleUtil.objTitleBgColor);
		otherFeesTab.addCell(otherFeesCell);
		
		blankCell.setBorder(Rectangle.NO_BORDER);
		otherFeesTab.addCell(blankCell);
		return otherFeesTab;
	}

	private PdfPTable getFeesTab(Fees fees, Map<String, String> labelMap) {
		PdfPTable feesRowTable;
		if(inlineTaxDisplay) {
			feesRowTable = createTabWithWidth(3, new float[] { 12,2,2 }, 700);
		}else {
			feesRowTable = createTabWithWidth(2, new float[] { 13,3 }, 700);
		}
		

		setCellData(feesRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, "", Element.ALIGN_LEFT);
		if(inlineTaxDisplay)
			setCellData(feesRowTable, EstimationReportConstants.CELLTYPE_COL_NAME,labelMap.get("hvat"), Element.ALIGN_CENTER);
		setCellData(feesRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, "Charges", Element.ALIGN_LEFT);

		setCellData(feesRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE, fees.getFeesDesc(), Element.ALIGN_LEFT);
		if(inlineTaxDisplay)
			setCellData(feesRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE,fees.getVatAmount(), Element.ALIGN_CENTER);
		setCellData(feesRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE, fees.getCharges(), Element.ALIGN_LEFT);
		return feesRowTable;
	}
	
	private PdfPTable getAdminFees(AdminFees fees, Map<String, String> labelMap) {
		PdfPTable feesRowTable;
		if(inlineTaxDisplay) {
			feesRowTable = createTabWithWidth(3, new float[] { 12,2,2 }, 700);
		}else {
			feesRowTable = createTabWithWidth(2, new float[] { 13,3 }, 700);
		}
		

		setCellData(feesRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, "", Element.ALIGN_LEFT);
		if(inlineTaxDisplay)
			setCellData(feesRowTable, EstimationReportConstants.CELLTYPE_COL_NAME,labelMap.get("hvat"), Element.ALIGN_CENTER);
		setCellData(feesRowTable, EstimationReportConstants.CELLTYPE_COL_NAME, "Charges", Element.ALIGN_LEFT);

		setCellData(feesRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE, fees.getFeesDesc(), Element.ALIGN_LEFT);
		if(inlineTaxDisplay)
			setCellData(feesRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE,fees.getVatAmount(), Element.ALIGN_CENTER);
		setCellData(feesRowTable, EstimationReportConstants.CELLTYPE_COL_VALUE, fees.getCharges(), Element.ALIGN_LEFT);
		return feesRowTable;
	}
	
	public void getBodyTotalCell(PdfPTable mainBTable, TotalCharge totalCharges, Map<String, String> labelMap) {
		PdfPTable totalTab = createTabWithWidth(2, new float[] { 13,3 }, 700);
		StyleUtil styleUtil =  new StyleUtil();
		PdfPCell totalTabCell = styleUtil.createCell(labelMap.get("hsubTot"), Element.ALIGN_RIGHT, EstimationReportConstants.CELLTYPE_COL_NAME, EstimationReportConstants.LABLETYPE_SUBTOATL);
		totalTab.addCell(totalTabCell);
		totalTabCell = styleUtil.createCell(currency+totalCharges.getSubTotal(), Element.ALIGN_RIGHT, EstimationReportConstants.CELLTYPE_COL_VALUE, EstimationReportConstants.LABLETYPE_SUBTOATL);
		totalTab.addCell(totalTabCell);
		totalTabCell = styleUtil.createCell(labelMap.get("htotAftrConcsn"),Element.ALIGN_RIGHT, EstimationReportConstants.CELLTYPE_COL_NAME, EstimationReportConstants.LABLETYPE_SUBTOATL);
		totalTab.addCell(totalTabCell);
		totalTabCell = styleUtil.createCell(currency+totalCharges.getAfterConcession(),Element.ALIGN_RIGHT, EstimationReportConstants.CELLTYPE_COL_VALUE, EstimationReportConstants.LABLETYPE_SUBTOATL);
		totalTab.addCell(totalTabCell);
		totalTabCell = styleUtil.createCell(labelMap.get("hvat")+" ("+totalCharges.getVatPercentage()+")", Element.ALIGN_RIGHT, EstimationReportConstants.CELLTYPE_COL_NAME, EstimationReportConstants.LABLETYPE_VAT);
		totalTab.addCell(totalTabCell);
		totalTabCell = styleUtil.createCell(totalCharges.getAfterVat(), Element.ALIGN_RIGHT, EstimationReportConstants.CELLTYPE_COL_VALUE, EstimationReportConstants.LABLETYPE_VAT);
		totalTab.addCell(totalTabCell);
		
		PdfPCell mainBodyCell = new PdfPCell(totalTab);
		mainBodyCell.setPaddingLeft(20);
		mainBodyCell.setPaddingRight(20);
		mainBodyCell.setPaddingTop(10);
		mainBodyCell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
		mainBodyCell.setBorderColor(StyleUtil.HEADER_BORDER_COLOR);
		mainBodyCell.setBorderWidthTop(0.5f);
		mainBodyCell.setBorderWidthBottom(1.5f);
		mainBTable.addCell(mainBodyCell);
		
		totalTab = createTabWithWidth(2, new float[] { 13,3 }, 700);
		totalTabCell = styleUtil.createCell(labelMap.get("hTotal"), Element.ALIGN_RIGHT, EstimationReportConstants.CELLTYPE_COL_NAME, EstimationReportConstants.LABLETYPE_TOTAL);
		totalTab.addCell(totalTabCell);
		totalTabCell = styleUtil.createCell(currency+totalCharges.getTotal(), Element.ALIGN_RIGHT, EstimationReportConstants.CELLTYPE_COL_VALUE, EstimationReportConstants.LABLETYPE_TOTAL);
		totalTab.addCell(totalTabCell);
		mainBodyCell = new PdfPCell(totalTab);
		mainBodyCell.setPaddingLeft(20);
		mainBodyCell.setPaddingRight(20);
		mainBodyCell.setPaddingTop(10);
		mainBodyCell.setBorder(Rectangle.BOTTOM);
		mainBodyCell.setBorderColor(StyleUtil.HEADER_BORDER_COLOR);
		mainBodyCell.setBorderWidthBottom(0.5f);
		mainBTable.addCell(mainBodyCell);

		mainBTable.setSplitLate(false);
		mainBTable.setWidthPercentage(100);
	}

	public PdfPTable getCommentsNotesTab(String notes, String label) {
		StyleUtil styleUtil = new StyleUtil(EstimationReportConstants.CELLTYPE_COL_VALUE);;
		PdfPTable notesTab = new PdfPTable(1);
		notesTab.setTotalWidth(700);
		
		PdfPCell notesCell ;
		notesCell = styleUtil.createCell(label, Element.ALIGN_LEFT , 0, new String[] { "5B" });
		notesTab.addCell(notesCell);
		
		styleUtil = new StyleUtil(EstimationReportConstants.CELLTYPE_COL_VALUE);
		notesCell = styleUtil.createCell(notes,Element.ALIGN_LEFT , 0, new String[] { "14L" });
		notesCell.setPaddingLeft(10);
		notesCell.setPaddingRight(20);
		notesCell.setPaddingTop(10);
		notesCell.setPaddingBottom(10);
		/*notesCell.setBorder(Rectangle.BOX);
		notesCell.setBorderColor(StyleUtil.TITLE_FONT_COLOR);*/
		notesCell.setBorder(Rectangle.NO_BORDER);
		notesCell.setCellEvent(new RoundedCellEvent("TOP"));
		notesTab.addCell(notesCell);
		
		return notesTab;
	}
	public PdfPTable getPhotosTab(List<ImageDetails> listOfImages) {
		int imageCount = listOfImages.size();
		StyleUtil styleUtil = new StyleUtil(EstimationReportConstants.CELLTYPE_COL_VALUE);
		PdfPTable photoMainTab = new PdfPTable(1);
		photoMainTab.setTotalWidth(700);
		PdfPCell photoMainCell ;
		PdfPTable photoTab= createTabWithWidth(4, new float[] { 2, 2, 2, 2}, 700);
		PdfPCell photoLableCell = styleUtil.createCell("Photos", Element.ALIGN_LEFT, 4, new String[] { "8L", "10B" });
		photoLableCell.setBorder(Rectangle.NO_BORDER);
		photoTab.addCell(photoLableCell);
		
		int count = 0;
		for(ImageDetails imgData:listOfImages) {
			count++;
			PdfPCell photoCell = new PdfPCell(new Phrase(""));			
			try {
				photoCell = new PdfPCell(setPhoto(imgData));
				photoCell.setBorder(Rectangle.NO_BORDER);
				photoCell.setPaddingLeft(8);
				photoCell.setPaddingRight(5);
				photoTab.addCell(photoCell);
			} catch (DocumentException | IOException e) {
				logger.error("An error occurred while fetching photo "+e);
			}
			
			int remainder = count%4;
			if(count==imageCount) {
				switch (remainder) {
				case 1:
					photoCell = new PdfPCell();
					photoCell.setColspan(3);
					photoCell.setBorder(Rectangle.NO_BORDER);
					photoTab.addCell(photoCell);
					break;
				case 2:
					photoCell = new PdfPCell();
					photoCell.setColspan(2);
					photoCell.setBorder(Rectangle.NO_BORDER);
					photoTab.addCell(photoCell);
					break;
				case 3:
					photoCell.setBorder(Rectangle.NO_BORDER);
					photoTab.addCell(photoCell);
					break;
				default:
					break;
				}
			}/*else
				photoTab.addCell(photoCell);*/
		}

		photoMainCell = new PdfPCell(photoTab);
		photoMainCell.setPaddingLeft(10);
		photoMainCell.setPaddingRight(20);
		photoMainCell.setPaddingTop(10);
		photoMainCell.setPaddingBottom(10);
		photoMainCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		photoMainCell.setBorder(Rectangle.NO_BORDER);
		photoMainCell.setCellEvent(new RoundedCellEvent("TOP"));
		photoMainTab.addCell(photoMainCell);
		return photoMainTab;
	}
	
	private PdfPTable setPhoto(ImageDetails imgData) throws DocumentException, IOException {
		PdfPTable singlePhotoTab = new PdfPTable(1);
		StyleUtil styleUtil = new StyleUtil(EstimationReportConstants.CELLTYPE_COL_NAME);
		singlePhotoTab.setTotalWidth(130);
		singlePhotoTab.getDefaultCell().setFixedHeight(80f);
		singlePhotoTab.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell photoDetailsCell;// = styleUtil.createCell(imgData.getPhotoName(), Element.ALIGN_LEFT, null);
		//singlePhotoTab.addCell(photoDetailsCell);
		if (imgData.getPhotoLoc() != null && !imgData.getPhotoLoc().isEmpty()) {
			Image photo;
			if (imgData.getPhotoLoc().contains("http")) {
				try {
					URL url = new URL(imgData.getPhotoLoc());
					photo = Image.getInstance(url);
					singlePhotoTab.addCell(photo);
				} catch (Exception e) {
					singlePhotoTab.addCell(new Phrase(""));
					logger.info("Logo path is not url", e);
				}
			} else {
				URL imageURL = BodyTableUtil.class.getResource(imgData.getPhotoLoc());
				try {
					photo = Image.getInstance(imageURL);
					singlePhotoTab.addCell(photo);
				} catch (BadElementException | IOException e) {
					logger.info("Error occurred while fetching photo ", e);
				}
			}
		} else {
			singlePhotoTab.addCell(new Phrase(""));
		}
		photoDetailsCell = styleUtil.createCell("Labour Order #"+imgData.getEstWoId()+" - "+imgData.getPhotoType(), Element.ALIGN_LEFT,null);
		singlePhotoTab.addCell(photoDetailsCell);
		photoDetailsCell = styleUtil.createCell("Task "+imgData.getEstWoTaskId(), Element.ALIGN_LEFT, new String[] { "13B" });
		singlePhotoTab.addCell(photoDetailsCell);
		return singlePhotoTab;
	}
	
	public PdfPTable createTabWithWidth(int colCount, float[] widths, float totalWidth) {
		PdfPTable table = new PdfPTable(colCount);
		try {
			table.setTotalWidth(totalWidth);
			table.setWidths(widths);
		} catch (DocumentException e) {
			logger.error("An error occurred :"+e);
		}
		return table;
	}
}
