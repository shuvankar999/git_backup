package com.tip.inspection.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;


import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.tip.inspection.model.BaseModel;

@Component
public class BodyUtil {

	StyleUtil styleUtil = new StyleUtil();
	
	public PdfPTable getBodyTable(BaseModel bm){
		
		PdfPTable mainBodyTab =  new PdfPTable(1);
	/*	
		mainBodyTab.addCell(createTabRow("Customer Number & Name", bm.getCustomerDetails().getCustomerNameAndNumber()));
		mainBodyTab.addCell(createTabRow("Equipment Number", bm.getAssetDetails().getEquipmentNumberHeader()));
		mainBodyTab.addCell(createTabRow("Customer Equipment Reference Number", bm.getAssetDetails().getCustomerEquipmentRefNumber()));
		mainBodyTab.addCell(createTabRow("Equipment License Number", bm.getAssetDetails().getEquipmentLicenseNumber()));
		mainBodyTab.addCell(createTabRow("Equipment Type", bm.getAssetDetails().getEquipmentType()));
		mainBodyTab.addCell(createTabRow("Inspected Date", bm.getInspectionDetails().getInspectedDate()));
		mainBodyTab.addCell(createTabRow("Inspection Type", bm.getInspectionDetails().getInspectedType()));
		mainBodyTab.addCell(createTabRow("Longitude", bm.getInspectionDetails().getLongitude()));
		mainBodyTab.addCell(createTabRow("Latitude", bm.getInspectionDetails().getLatitude()));
		mainBodyTab.addCell(createTabRow("Inspected By", bm.getInspectionPerformedBy().getInspectedBy()));
		mainBodyTab.addCell(createTabRow("Email Address", bm.getInspectionPerformedBy().getEmailAddress()));
		mainBodyTab.addCell(createTabRow("Phone Number", bm.getInspectionPerformedBy().getPhoneNumber()));
		mainBodyTab.addCell(createTabRow("Home Location", bm.getInspectionPerformedBy().getHomeLocation()));
		mainBodyTab.addCell(createTabRow("Working For", bm.getInspectionPerformedBy().getWorkingFor()));*/
		return mainBodyTab;   
		
	}
	
	public PdfPTable getBodyTable(Map<String,Map<String,String>> bmMap){
		
		PdfPTable mainBodyTab =  new PdfPTable(2);
		for(Map.Entry<String, Map<String,String>> entrySet : bmMap.entrySet()) {
			Map<String,String> map = entrySet.getValue();
			for(Map.Entry<String,String> mapEntry : map.entrySet()) {
					mainBodyTab.addCell(createTabRow(mapEntry.getKey(), mapEntry.getValue()));
			//	mainBodyTab.addCell(createTabRow(1,mapEntry.getKey(), mapEntry.getValue()));
			}
		}
		return mainBodyTab;   
		
	}	
	
	
	private PdfPTable createTabRow(String lable, String value) {
		PdfPTable rowTab =  new PdfPTable(2);
		PdfPCell cell = new PdfPCell(new Phrase(lable, styleUtil.getFont()));
		rowTab.addCell(cell);
		cell = new PdfPCell(new Phrase(value, styleUtil.getFont()));
		rowTab.addCell(cell);
		return rowTab;
	}
}
