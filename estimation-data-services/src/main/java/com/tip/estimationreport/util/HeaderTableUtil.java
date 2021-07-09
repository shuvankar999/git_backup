package com.tip.estimationreport.util;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lowagie.text.Element;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.tip.estimationreport.model.BaseModel;
import com.tip.estimationreport.model.BranchDetails;
import com.tip.estimationreport.model.CustomerDetails;
import com.tip.estimationreport.model.EstimationDetails;

@Component
public class HeaderTableUtil {
	
	@Autowired
	DataTypeConversionUtil dataTypeConversionUtil;
	
	StyleUtil styleUtil = new StyleUtil();
	
	
	public PdfPTable getHeaderTable(BaseModel bm, Map<String, String> labelMap){        
	    PdfPTable hTable = new PdfPTable(3);
	    PdfPTable mainHTable = new PdfPTable(1);
	    String [] padding = new String[]{"14L","7T","8B"};
	    StyleUtil headerTabTitleStyle = new StyleUtil(EstimationReportConstants.TABLETYPE_HEADER, EstimationReportConstants.CELLTYPE_COL_TITLE);
		StyleUtil headerTabNameStyle = new StyleUtil(EstimationReportConstants.TABLETYPE_HEADER, EstimationReportConstants.CELLTYPE_COL_NAME);
		StyleUtil headerTabValStyle = new StyleUtil(EstimationReportConstants.TABLETYPE_HEADER, EstimationReportConstants.CELLTYPE_COL_VALUE);
		
	    PdfPCell cellForRow1 = headerTabTitleStyle.createCell(labelMap.get("hbranchDetails"), Element.ALIGN_LEFT, padding);
		hTable.addCell(cellForRow1);
		
		cellForRow1 = headerTabTitleStyle.createCell(labelMap.get("hcustDetails"), Element.ALIGN_LEFT, padding);
		hTable.addCell(cellForRow1);
		
		cellForRow1 = headerTabTitleStyle.createCell(labelMap.get("hestnDetails"), Element.ALIGN_LEFT, padding);
		hTable.addCell(cellForRow1);

		padding = new String[]{"14L"};
		
		PdfPTable branchTable = getBranchDetailsTable(headerTabNameStyle, headerTabValStyle, padding, bm, labelMap);
		PdfPCell cellForRow2 = new PdfPCell(branchTable);
		cellForRow2.setBorder(Rectangle.NO_BORDER);
		hTable.addCell(cellForRow2);
		
		PdfPTable customerTable = getCustomerDetailsTable(headerTabNameStyle, headerTabValStyle, padding, bm, labelMap);
		cellForRow2 = new PdfPCell(customerTable);
		cellForRow2.setBorder(Rectangle.NO_BORDER);
		hTable.addCell(cellForRow2);
		
		PdfPTable estimationTable = getEstimationDetailsTable(headerTabNameStyle, headerTabValStyle, padding, bm, labelMap);
		cellForRow2 = new PdfPCell(estimationTable);
		cellForRow2.setBorder(Rectangle.NO_BORDER);
		hTable.addCell(cellForRow2);
		
		hTable.setTotalWidth(700);
		PdfPCell mainHeaderBodyCell = new PdfPCell(hTable);
		mainHeaderBodyCell.setPaddingLeft(20);
		mainHeaderBodyCell.setPaddingRight(20);
		mainHeaderBodyCell.setPaddingBottom(10);
		mainHeaderBodyCell.setBorder(Rectangle.BOTTOM);
		mainHeaderBodyCell.setBorderColor(styleUtil.HEADER_BORDER_COLOR);
		mainHTable.addCell(mainHeaderBodyCell);
		mainHTable.setWidthPercentage(100);
	    return mainHTable;
	}

		private PdfPTable getBranchDetailsTable(StyleUtil colNameStyle, StyleUtil colValStyle, String[] padding, BaseModel bm, Map<String, String> labelMap) {

			BranchDetails bDetails = bm.getBranchDetails();
			PdfPTable branchDetailsTab = new PdfPTable(2);
			PdfPCell cell;
			PdfPCell blankCell = new PdfPCell(new Phrase("	", styleUtil.getFieldNameFont()));
			blankCell.setBorder(Rectangle.NO_BORDER);
			blankCell.setColspan(2);

			cell = colNameStyle.createCell(labelMap.get("hname")+":", Element.ALIGN_LEFT, padding);
			cell.setColspan(2);
			branchDetailsTab.addCell(cell);

			cell = colValStyle.createCell(bDetails.getBranchName(), Element.ALIGN_LEFT, padding);
			cell.setColspan(2);
			branchDetailsTab.addCell(cell);

			branchDetailsTab.addCell(blankCell);

			cell = colNameStyle.createCell(labelMap.get("hAddress")+":", Element.ALIGN_LEFT, padding);
			cell.setColspan(2);
			branchDetailsTab.addCell(cell);

			cell = colValStyle.createCell(bDetails.getBranchAddr(),
					Element.ALIGN_LEFT, padding);
			cell.setColspan(2);
			branchDetailsTab.addCell(cell);

			branchDetailsTab.addCell(blankCell);

			cell = colNameStyle.createCell(labelMap.get("hPhone")+":", Element.ALIGN_LEFT, padding);
			branchDetailsTab.addCell(cell);

			cell = colNameStyle.createCell(labelMap.get("hFax")+":", Element.ALIGN_LEFT, padding);
			branchDetailsTab.addCell(cell);

			cell = colValStyle.createCell(bDetails.getBranchPhone(), Element.ALIGN_LEFT, padding);
			branchDetailsTab.addCell(cell);

			cell = colValStyle.createCell(bDetails.getBranchFax(), Element.ALIGN_LEFT, padding);
			branchDetailsTab.addCell(cell);

			branchDetailsTab.setTotalWidth(183);

			return branchDetailsTab;

		}

		private PdfPTable getCustomerDetailsTable(StyleUtil colNameStyle, StyleUtil colValStyle, String[] padding, BaseModel bm, Map<String, String> labelMap) {

			CustomerDetails cDetails = bm.getCustomerDetails();
			PdfPTable custDetalisTab = new PdfPTable(2);

			PdfPCell cell;
			PdfPCell blankCell = new PdfPCell(new Phrase("	", styleUtil.getFieldNameFont()));
			blankCell.setBorder(Rectangle.NO_BORDER);
			blankCell.setColspan(2);

			cell = colNameStyle.createCell(labelMap.get("hname")+":", Element.ALIGN_LEFT, padding);
			cell.setColspan(2);
			custDetalisTab.addCell(cell);

			cell = colValStyle.createCell(cDetails.getCustName(), Element.ALIGN_LEFT, padding);
			cell.setColspan(2);
			custDetalisTab.addCell(cell);

			custDetalisTab.addCell(blankCell);

			cell = colNameStyle.createCell(labelMap.get("hAddress")+":", Element.ALIGN_LEFT, padding);
			cell.setColspan(2);
			custDetalisTab.addCell(cell);

			cell = colValStyle.createCell(cDetails.getCustAddr(), Element.ALIGN_LEFT,padding);
			cell.setColspan(2);
			custDetalisTab.addCell(cell);

			custDetalisTab.addCell(blankCell);

			cell = colNameStyle.createCell(labelMap.get("hPhone")+":", Element.ALIGN_LEFT, padding);
			custDetalisTab.addCell(cell);

			cell = colNameStyle.createCell(labelMap.get("hFax")+":", Element.ALIGN_LEFT, padding);
			custDetalisTab.addCell(cell);

			cell = colValStyle.createCell(cDetails.getCustPhone(), Element.ALIGN_LEFT, padding);
			custDetalisTab.addCell(cell);

			cell = colValStyle.createCell(cDetails.getCustFax(), Element.ALIGN_LEFT, padding);
			custDetalisTab.addCell(cell);

			custDetalisTab.addCell(blankCell);

			cell = colNameStyle.createCell(labelMap.get("hEmail")+":", Element.ALIGN_LEFT, padding);
			cell.setColspan(2);
			custDetalisTab.addCell(cell);

			cell = colValStyle.createCell(cDetails.getCustEmail(), Element.ALIGN_LEFT, padding);
			cell.setColspan(2);
			custDetalisTab.addCell(cell);

			custDetalisTab.setTotalWidth(183);

			return custDetalisTab;

		}


		private PdfPTable getEstimationDetailsTable(StyleUtil colNameStyle, StyleUtil colValStyle, String[] padding, BaseModel bm, Map<String, String> labelMap) {

			EstimationDetails eDetails = bm.getEstimationDetails();
			
			PdfPTable estDetailsTab = new PdfPTable(2);

			PdfPCell cell;
			PdfPCell blankCell = new PdfPCell(new Phrase("	", styleUtil.getFieldNameFont()));
			blankCell.setBorder(Rectangle.NO_BORDER);
			blankCell.setColspan(2);

			cell = colNameStyle.createCell(labelMap.get("hestnNo")+":", Element.ALIGN_LEFT, padding);
			estDetailsTab.addCell(cell);

			cell = colNameStyle.createCell(labelMap.get("hDate")+":", Element.ALIGN_LEFT, padding);
			estDetailsTab.addCell(cell);

			cell = colValStyle.createCell(eDetails.getEstNr(), Element.ALIGN_LEFT, padding);
			estDetailsTab.addCell(cell);

			cell = colValStyle.createCell(eDetails.getEstDate(), Element.ALIGN_LEFT, padding);
			estDetailsTab.addCell(cell);

			estDetailsTab.addCell(blankCell);
			
			cell = colNameStyle.createCell("Version:", Element.ALIGN_LEFT, padding);
			estDetailsTab.addCell(cell);

			cell = colNameStyle.createCell("Supplementary:", Element.ALIGN_LEFT, padding);
			estDetailsTab.addCell(cell);

			String version = dataTypeConversionUtil.castValue(bm.getVersion());
			cell = colValStyle.createCell(version.isEmpty()?"1":version, Element.ALIGN_LEFT, padding);
			estDetailsTab.addCell(cell);

			String suppl = dataTypeConversionUtil.castValue(bm.getSupplementary());
			cell = colValStyle.createCell(suppl.isEmpty()?"NA":suppl, Element.ALIGN_LEFT, padding);
			estDetailsTab.addCell(cell);

			estDetailsTab.addCell(blankCell);

			cell = colNameStyle.createCell(labelMap.get("htipTrailerNo")+":", Element.ALIGN_LEFT, padding);
			estDetailsTab.addCell(cell);

			cell = colNameStyle.createCell(labelMap.get("hcustRef")+":", Element.ALIGN_LEFT, padding);
			estDetailsTab.addCell(cell);

			cell = colValStyle.createCell(eDetails.getTipTrailerNr(), Element.ALIGN_LEFT, padding);
			estDetailsTab.addCell(cell);

			cell = colValStyle.createCell(eDetails.getCustRef(), Element.ALIGN_LEFT, padding);
			estDetailsTab.addCell(cell);

			estDetailsTab.addCell(blankCell);

			cell = colNameStyle.createCell(labelMap.get("hregNr")+":", Element.ALIGN_LEFT, padding);
			estDetailsTab.addCell(cell);

			cell = colNameStyle.createCell(labelMap.get("hestnBy")+":", Element.ALIGN_LEFT, padding);
			estDetailsTab.addCell(cell);

			cell = colValStyle.createCell(eDetails.getRegNr(), Element.ALIGN_LEFT, padding);
			estDetailsTab.addCell(cell);

			cell = colValStyle.createCell(eDetails.getEstBy(), Element.ALIGN_LEFT, padding);
			estDetailsTab.addCell(cell);

			estDetailsTab.addCell(blankCell);

			cell = colNameStyle.createCell(labelMap.get("hBranch"), Element.ALIGN_LEFT, padding);
			estDetailsTab.addCell(cell);

			cell = colNameStyle.createCell(labelMap.get("hTotal"), Element.ALIGN_LEFT, padding);
			estDetailsTab.addCell(cell);

			cell = colValStyle.createCell(eDetails.getBranch(), Element.ALIGN_LEFT, padding);
			estDetailsTab.addCell(cell);

			cell = colValStyle.createCell(bm.getTotalCharges().getTotal(), Element.ALIGN_LEFT, padding);
			estDetailsTab.addCell(cell);

			estDetailsTab.addCell(blankCell);

			estDetailsTab.setTotalWidth(183);

			return estDetailsTab;

		}
}
