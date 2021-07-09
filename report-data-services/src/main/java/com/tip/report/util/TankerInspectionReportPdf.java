package com.tip.report.util;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.html.WebColors;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.tip.fetchinspectionreport.model.InspAllHeaders;
import com.tip.fetchinspectionreport.model.InspCheckObject;
import com.tip.fetchinspectionreport.model.InspSignObject;
import com.tip.fetchinspectionreport.model.ResponseObject;

public class TankerInspectionReportPdf {

static final Logger logger = LoggerFactory.getLogger(TankerInspectionReportPdf.class);

PdfCommonMethods pdfCommonMethods = new PdfCommonMethods();

private BaseFont base;
private String errorString = "An error occurred while generating inspection report";

public PdfPTable getHeaderTable(InspAllHeaders inspAllHeaders, Map<String, String> inspHeaderMap) {        
    PdfPTable hTable = new PdfPTable(3);
    try {
		hTable.setWidths(new int[]{1, 1, 1});
		hTable.setSplitLate(false);
	    PdfPCell cellOne = new PdfPCell(getHeaderTable1(inspAllHeaders,inspHeaderMap));
	    cellOne.setHorizontalAlignment(Element.ALIGN_RIGHT);
	    cellOne.setPaddingLeft(20f);
	    cellOne.setPaddingRight(2f);
	    cellOne.setPaddingTop(5f);
	    cellOne.setPaddingBottom(5f);
	    cellOne.setHorizontalAlignment(Element.ALIGN_RIGHT);
	    
	    PdfPCell cellTwo = new PdfPCell(getHeaderTable2(inspAllHeaders,inspHeaderMap));
	    cellTwo.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cellTwo.setPaddingLeft(2f);
	    cellTwo.setPaddingRight(2f);
	    cellTwo.setPaddingTop(5f);
	    cellTwo.setPaddingBottom(5f);
	    
	    PdfPCell cellThree = new PdfPCell(getHeaderTable3(inspAllHeaders,inspHeaderMap));
	    cellThree.setHorizontalAlignment(Element.ALIGN_RIGHT);
	    cellThree.setPaddingLeft(2f);
	    cellThree.setPaddingRight(20f);
	    cellThree.setPaddingTop(5f);
	    cellThree.setPaddingBottom(5f);
	    
	    PdfPCell cell4 = new PdfPCell(getHeaderTable4(inspAllHeaders,inspHeaderMap));
	    cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell4.setPaddingLeft(20f);
	    cell4.setPaddingRight(2f);
	    cell4.setPaddingBottom(5f);
	    
	    PdfPCell cell5 = new PdfPCell(new Phrase("     "));
	    cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell5.setPaddingLeft(20f);
	    cell5.setPaddingRight(2f);
	    cell5.setPaddingBottom(5f);
	    cell5.setColspan(2);

	    Color bodyBgColor = WebColors.getRGBColor("#babec3");
	    
	    cellOne.setBorder(Rectangle.TOP);
	    cellOne.setBackgroundColor(bodyBgColor);
	    cellOne.setBorderColor(Color.GRAY);
	    cellTwo.setBorder(Rectangle.TOP);
	    cellTwo.setBackgroundColor(bodyBgColor);
	    cellTwo.setBorderColor(Color.GRAY);
	    cellThree.setBorder(Rectangle.TOP);
	    cellThree.setBackgroundColor(bodyBgColor);
	    cellThree.setBorderColor(Color.GRAY);
	    cell4.setBorder(Rectangle.BOTTOM);
	    cell4.setBackgroundColor(bodyBgColor);
	    cell4.setBorderColor(Color.GRAY);
	    cell5.setBorder(Rectangle.BOTTOM);
	    cell5.setBackgroundColor(bodyBgColor);
	    cell5.setBorderColor(Color.GRAY);
	    
	    hTable.addCell(cellOne);
	    hTable.addCell(cellTwo);
	    hTable.addCell(cellThree);
	    hTable.addCell(cell4);
	    hTable.addCell(cell5);
	    hTable.setTotalWidth(PageSize.A4.getWidth());
	    hTable.setLockedWidth(true);
	    hTable.setWidthPercentage(100);
	    hTable.setSpacingAfter(33);
	} catch (DocumentException e) {
		logger.error(errorString , e);
	}
    return hTable;
}

public PdfPTable getBodyTable(InspAllHeaders inspAllHeaders, Map<String, String> inspHeaderMap,LinkedHashMap<String, 
		List<InspCheckObject>> inspCheckObjectMapTemp, List<Object> inspDefectlist, InspSignObject signObject) {            
    
    PdfPTable table = new PdfPTable(3);
    try {
		table.setWidths(new int[]{1, 1, 1});
		table.setSplitLate(false);
	    table.setTotalWidth(PageSize.A4.getWidth());
	    table.setLockedWidth(true);
	    table.setWidthPercentage(100);
	    table.setSpacingAfter(33);
	    
	    Font font = new Font(base, 8f, Font.NORMAL);
		font.setColor(Color.WHITE);
		
	    PdfPCell cellFour = new PdfPCell(new Phrase("", font));
	    PdfPCell cellFive = new PdfPCell(getIconTable(inspAllHeaders));
	    cellFive.setHorizontalAlignment(Element.ALIGN_JUSTIFIED_ALL);
	    PdfPCell cellSix = new PdfPCell(new Phrase("", font));
	    cellSix.setHorizontalAlignment(Element.ALIGN_RIGHT);
	    cellFour.setBorder(Rectangle.NO_BORDER);
	    cellFive.setBorder(Rectangle.NO_BORDER);
	    cellSix.setBorder(Rectangle.NO_BORDER);
	    
	    table.addCell(cellFour);
	    table.addCell(cellFive);
	    table.addCell(cellSix);

	    PdfPCell headerCell = new PdfPCell(new Phrase(inspAllHeaders.getInspTankReefSwap(), font));
	    Color headerColor = WebColors.getRGBColor("#444f5b");
	    headerCell.setBackgroundColor(headerColor);
	    PdfPTable inspectionHeadertable = new PdfPTable(1);
	    inspectionHeadertable.addCell(headerCell);
	    
	    PdfPCell cellSeven = new PdfPCell(inspectionHeadertable);
	    cellSeven.setBorder(Rectangle.NO_BORDER);
	    cellSeven.setBorder(Rectangle.LEFT);
	    cellSeven.setColspan(3);
	    cellSeven.setPaddingLeft(20f);
	    cellSeven.setPaddingRight(20f);
	    table.addCell(cellSeven);
	    
	    
	    PdfPCell bodyCell = new PdfPCell(getInspectionContentTable(inspCheckObjectMapTemp));
	    bodyCell.setBorderColor(Color.DARK_GRAY);
	    PdfPTable inspectionBodytable = new PdfPTable(1);
	    inspectionBodytable.addCell(bodyCell);
	   
	    PdfPCell cellEight = new PdfPCell(inspectionBodytable);
	    cellEight.setBorder(Rectangle.NO_BORDER);
	    cellEight.setBorder(Rectangle.LEFT);
	    cellEight.setColspan(3);
	    cellEight.setPaddingLeft(20f);
	    cellEight.setPaddingRight(20f);
	    table.addCell(cellEight);
	    
	    boolean roadFlag;
	    if("Y".equalsIgnoreCase(inspHeaderMap.get("RoadWorthy")))
	    {
	    	roadFlag = true;
	    }
	    else
	    {
	    	roadFlag = false;
	    }
	    PdfPCell signatureCell = new PdfPCell(getSignatureTable(inspAllHeaders,signObject,roadFlag));
	    signatureCell.setBorderColor(Color.GRAY);
	    PdfPTable inspectionSignTable = new PdfPTable(1);
	    inspectionSignTable.addCell(signatureCell);
	   
	    PdfPCell cellNine = new PdfPCell(inspectionSignTable);
	    cellNine.setBorder(Rectangle.NO_BORDER);
	    cellNine.setColspan(3);
	    cellNine.setPaddingTop(3f);
	    cellNine.setPaddingLeft(20f);
	    cellNine.setPaddingRight(20f);
	    table.addCell(cellNine);
	    
	    PdfPCell defectCell = new PdfPCell(getDefectTable(inspAllHeaders,font,headerColor,inspDefectlist));
	    defectCell.setBorderColor(Color.GRAY);
	    PdfPTable defectBodyTable = new PdfPTable(1);
	    defectBodyTable.addCell(defectCell);
	    defectBodyTable.setSplitLate(false);
	   
	    PdfPCell cellEleven = new PdfPCell(defectBodyTable);
	    cellEleven.setBorder(Rectangle.NO_BORDER);
	    cellEleven.setColspan(3);
	    cellEleven.setPaddingTop(5f);
	    cellEleven.setPaddingLeft(20f);
	    cellEleven.setPaddingRight(20f);
	    table.addCell(cellEleven);
	} catch (DocumentException e) {
		logger.error(errorString , e);
	}
    return table;
}

public PdfPTable getDefectTable(InspAllHeaders inspAllHeaders,Font hFont, Color headerColor,List<Object> inspDefectlist) {
	
	Color bodyBgColor = WebColors.getRGBColor("#babec3");
	Font font = new Font(base, 6f, Font.NORMAL);
    PdfPTable defectsBodyTable = new PdfPTable(4);
    try {
		defectsBodyTable.setWidths(new int[]{1, 17, 12, 5});
		if(!inspDefectlist.isEmpty())
	    {
	    	defectsBodyTable.setHeaderRows(1);
	    }
	    defectsBodyTable.setSplitLate(false);
	    
		PdfPCell hCell1 = new PdfPCell(new Phrase(inspAllHeaders.getNr(), hFont));
	    hCell1.setBackgroundColor(headerColor);
	    hCell1.setBorder(Rectangle.NO_BORDER);
	    PdfPCell hCell2 = new PdfPCell(new Phrase(inspAllHeaders.getDefectsHeader(), hFont));
	    hCell2.setBackgroundColor(headerColor);
	    hCell2.setBorder(Rectangle.NO_BORDER);
	    PdfPCell hCell3 = new PdfPCell(new Phrase(inspAllHeaders.getAction(), hFont));
	    hCell3.setBackgroundColor(headerColor);
	    hCell3.setBorder(Rectangle.NO_BORDER);
	    PdfPCell hCell4 = new PdfPCell(new Phrase(inspAllHeaders.getActionBy(), hFont));
	    hCell4.setBackgroundColor(headerColor);
	    hCell4.setBorder(Rectangle.NO_BORDER);
	    
	    defectsBodyTable.addCell(hCell1);
	    defectsBodyTable.addCell(hCell2);
	    defectsBodyTable.addCell(hCell3);
	    defectsBodyTable.addCell(hCell4);
	    
	    Iterator inspDefectlistIterator = inspDefectlist.iterator();
		while(inspDefectlistIterator.hasNext()){
			Map<String, Object> inspDefectMap = (Map<String, Object>) inspDefectlistIterator.next();	
			String nr = "";
			String defect = "";
			String action = "";
			String actionBy = "";
			if(inspDefectMap.get("nr") != null)
			{
				nr = inspDefectMap.get("nr").toString();
			}
			if(inspDefectMap.get("defect") != null)
			{
				defect = inspDefectMap.get("defect").toString();
			}
			if(inspDefectMap.get("action") != null)
			{
				action = inspDefectMap.get("action").toString();
			}
			if(inspDefectMap.get("actionBy") != null)
			{
				actionBy = inspDefectMap.get("actionBy").toString();
			}
		    PdfPCell bCell1 = new PdfPCell(new Phrase(nr, font));
			
			bCell1.setBackgroundColor(bodyBgColor);
		    bCell1.setBorder(Rectangle.RIGHT | Rectangle.TOP);
		    bCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		    
		    PdfPCell bCell2 = new PdfPCell(new Phrase(defect, font));
		    bCell2.setBackgroundColor(bodyBgColor);
		    bCell2.setBorder(Rectangle.RIGHT | Rectangle.TOP);
		    bCell2.setHorizontalAlignment(Element.ALIGN_LEFT);
		    
		    PdfPCell bCell3 = new PdfPCell(new Phrase(action, font));
		    bCell3.setBackgroundColor(bodyBgColor);
		    bCell3.setBorder(Rectangle.RIGHT | Rectangle.TOP);
		    bCell3.setHorizontalAlignment(Element.ALIGN_LEFT);
		    
		    PdfPCell bCell4 = new PdfPCell(new Phrase(actionBy, font));
		    bCell4.setBackgroundColor(bodyBgColor);
		    bCell4.setBorder(Rectangle.TOP);
		    bCell4.setHorizontalAlignment(Element.ALIGN_LEFT);    
		    
		    defectsBodyTable.addCell(bCell1);
		    defectsBodyTable.addCell(bCell2);
		    defectsBodyTable.addCell(bCell3);
		    defectsBodyTable.addCell(bCell4);
		}
	} catch (DocumentException e) {
		logger.error(errorString , e);
	}
	return defectsBodyTable;
}

/*public PdfPTable getSignatureTable(InspAllHeaders inspAllHeaders, InspSignObject signObject, boolean roadFlag) {
	PdfPTable table = new PdfPTable(2);
    try {
		table.setWidths(new int[]{1, 1});
		Font font = new Font(base, 6f, Font.BOLD);
		PdfPTable innertable1 = new PdfPTable(2);
		innertable1.setWidths(new int[]{1, 1});
	    	
		PdfPCell cellOne = new PdfPCell(new Phrase(signObject.getTipInspectorconfirmation(), font));
	    cellOne.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cellOne.setColspan(2);
	    cellOne.setBorder(Rectangle.NO_BORDER);
	    cellOne.setPaddingBottom(20);
	    
	    PdfPCell cellTwo;
	    if(signObject.getTipSignInspector() != null && !signObject.getTipSignInspector().isEmpty()){    	
	    	if(signObject.getTipSignInspector().contains("http"))
	    	{
	    		cellTwo = pdfCommonMethods.addInspHttpImage(signObject,cellOne);
	    	}
	    	else
	    	{
	    		cellTwo = pdfCommonMethods.addInspFileImage(signObject,cellOne);
	    	}
	    }
	    else{
	    	cellTwo = new PdfPCell(new Phrase(""));
	    	cellOne.setPaddingBottom(67);
	    }
	     
	    cellTwo.setHorizontalAlignment(Element.ALIGN_CENTER);
	    cellTwo.setColspan(2);
	    cellTwo.setPaddingBottom(22);
	    cellTwo.setBorder(Rectangle.NO_BORDER);
	                
	    PdfPCell cellThree = new PdfPCell(new Phrase(inspAllHeaders.getInspector() + " : " + signObject.getNameInspector(), font));
	    cellThree.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cellThree.setBorder(Rectangle.TOP);
	    
	    PdfPCell cellFour = new PdfPCell(new Phrase(inspAllHeaders.getDate() + " : " + signObject.getDateInspector(), font));
	    cellFour.setHorizontalAlignment(Element.ALIGN_RIGHT);
	    cellFour.setBorder(Rectangle.TOP);
	    if("".equals(signObject.getDateInspector().trim()))
	    {
	    	cellFour.setPaddingRight(50f);
	    }
	    
	    innertable1.addCell(cellOne);
	    innertable1.addCell(cellTwo);
	    innertable1.addCell(cellThree);
	    innertable1.addCell(cellFour);
	    
	    PdfPTable innertable2 = new PdfPTable(2);
	    innertable2.setWidths(new int[]{1, 1});
	    	
		PdfPCell cel1 = new PdfPCell(new Phrase(signObject.getTipSignedconfirmation(), font));
	    cel1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    cel1.setColspan(2);
	    cel1.setBorder(Rectangle.NO_BORDER);
	    
	    Image signImg2 = pdfCommonMethods.addRoadworthyImage(roadFlag,inspAllHeaders);
	    
	    PdfPCell cel2 = new PdfPCell(signImg2);
	    cel2.setHorizontalAlignment(Element.ALIGN_CENTER);
	    cel2.setColspan(2);
	    cel2.setPaddingTop(10);
	    cel2.setPaddingBottom(5);
	    cel2.setBorder(Rectangle.NO_BORDER);
	    
	    PdfPCell cel3;
	    if(signObject.getTipSignSigned() != null && !signObject.getTipSignSigned().isEmpty()){
	    	if(signObject.getTipSignSigned().contains("http"))
	    	{
	    		cel3 = pdfCommonMethods.addSignedHttpImage(signObject, cel2);
	    	}
	    	else
	    	{
	    		cel3 = pdfCommonMethods.addSignedFileImage(signObject, cel2);
	    	}       
	    }
	    else{
	    	cel3 = new PdfPCell(new Phrase(""));
	    	cel2.setPaddingBottom(35);
	    }
	    
	    cel3.setHorizontalAlignment(Element.ALIGN_CENTER);
	    cel3.setColspan(2);
	    cel3.setPaddingBottom(5);
	    cel3.setBorder(Rectangle.NO_BORDER);
	                
	    PdfPCell cel4 = new PdfPCell(new Phrase(inspAllHeaders.getSigned() + " : " + signObject.getNameSigned(), font));
	    cel4.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cel4.setBorder(Rectangle.TOP);
	    
	    PdfPCell cel5 = new PdfPCell(new Phrase(inspAllHeaders.getDate() + " : " + signObject.getDateSigned(), font));
	    cel5.setHorizontalAlignment(Element.ALIGN_RIGHT);
	    cel5.setBorder(Rectangle.TOP);
	    if("".equals(signObject.getDateSigned().trim()))
	    {
	    	cel5.setPaddingRight(50f);
	    }
	    
	    innertable2.addCell(cel1);
	    innertable2.addCell(cel2);
	    innertable2.addCell(cel3);
	    innertable2.addCell(cel4);
	    innertable2.addCell(cel5);    		
	    	
		PdfPCell cell1 = new PdfPCell(innertable1);
	    cell1.setBorder(Rectangle.NO_BORDER);
	    cell1.setPaddingLeft(2f);
	    cell1.setPaddingRight(10f);
	    cell1.setPaddingTop(2f);
	    cell1.setPaddingBottom(2f);
	    table.addCell(cell1);
	    
	    PdfPCell cell2 = new PdfPCell(innertable2);
	    cell2.setBorder(Rectangle.NO_BORDER);
	    cell2.setPaddingLeft(10f);
	    cell2.setPaddingRight(2f);
	    cell2.setPaddingTop(2f);
	    cell2.setPaddingBottom(2f);
	    table.addCell(cell2);
	} catch (DocumentException | IOException e1) {
		logger.error(errorString , e1);
	}
    return table;
}*/

public PdfPTable getSignatureTable(InspAllHeaders inspAllHeaders, InspSignObject signObject, boolean roadFlag) {
	PdfPTable table = new PdfPTable(4);
    try {
		table.setWidths(new int[]{1, 1, 1, 1});
		Font font = new Font(base, 6f, Font.BOLD);
		PdfPCell cellOne = new PdfPCell(new Phrase(signObject.getTipInspectorconfirmation(), font));
	    cellOne.setHorizontalAlignment(Element.ALIGN_CENTER);
	    cellOne.setColspan(2);
	    cellOne.setRowspan(2);
	    cellOne.setBorder(Rectangle.NO_BORDER);
	    
	    table.addCell(cellOne);
	    
	    PdfPCell cel1 = new PdfPCell(new Phrase(signObject.getTipSignedconfirmation(), font));
	    cel1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    cel1.setColspan(2);
	    cel1.setBorder(Rectangle.NO_BORDER);
	    
	    table.addCell(cel1);
	    
	    Image signImg2 = pdfCommonMethods.addRoadworthyImage(roadFlag,inspAllHeaders);
	    
	    PdfPCell cel2 = new PdfPCell(signImg2);
	    cel2.setHorizontalAlignment(Element.ALIGN_CENTER);
	    cel2.setColspan(2);
	    cel2.setPaddingTop(10f);
	    cel2.setBorder(Rectangle.NO_BORDER);
	    
	    table.addCell(cel2);
	    
	    PdfPCell cellTwo;
	    if(signObject.getTipSignInspector() != null && !signObject.getTipSignInspector().isEmpty()){
	    	if(signObject.getTipSignInspector().contains("http"))
	    	{
	    		cellTwo = pdfCommonMethods.addInspHttpImage(signObject,cellOne);
	    	}
	    	else
	    	{
	    		cellTwo = pdfCommonMethods.addInspFileImage(signObject,cellOne);
	    	}
	    }
	    else{
	    	cellTwo = new PdfPCell(new Phrase(""));
	    	cellOne.setPaddingBottom(67);
	    }
	     
	    cellTwo.setHorizontalAlignment(Element.ALIGN_CENTER);
	    cellTwo.setColspan(2);
	    cellTwo.setPaddingBottom(22);
	    cellTwo.setBorder(Rectangle.NO_BORDER);
	    
	    table.addCell(cellTwo);
	    
	    PdfPCell cel3;
	    if(signObject.getTipSignSigned() != null && !signObject.getTipSignSigned().isEmpty()){
	    	if(signObject.getTipSignSigned().contains("http"))
	    	{
	    		cel3 = pdfCommonMethods.addSignedHttpImage(signObject, cel2);
	    	}
	    	else
	    	{
	    		cel3 = pdfCommonMethods.addSignedFileImage(signObject, cel2);
	    	}       
	    }
	    else{
	    	cel3 = new PdfPCell(new Phrase(""));
	    	cel2.setPaddingBottom(35);
	    }
	    
	    cel3.setHorizontalAlignment(Element.ALIGN_CENTER);
	    cel3.setColspan(2);
	    cel3.setPaddingBottom(5);
	    cel3.setBorder(Rectangle.NO_BORDER);
	    
	    table.addCell(cel3);
	    
	    PdfPTable innertable1 = new PdfPTable(2);
		innertable1.setWidths(new int[]{1, 1});
	                
	    PdfPCell cellThree = new PdfPCell(new Phrase(inspAllHeaders.getInspector() + " : " + signObject.getNameInspector(), font));
	    cellThree.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cellThree.setBorder(Rectangle.TOP);
	    
	    PdfPCell cellFour = new PdfPCell(new Phrase(inspAllHeaders.getDate() + " : " + signObject.getDateInspector(), font));
	    cellFour.setHorizontalAlignment(Element.ALIGN_RIGHT);
	    cellFour.setBorder(Rectangle.TOP);
	    if("".equals(signObject.getDateInspector().trim()))
	    {
	    	cellFour.setPaddingRight(50f);
	    }
	    innertable1.addCell(cellThree);
	    innertable1.addCell(cellFour);
	    
	    PdfPCell cell1 = new PdfPCell(innertable1);
	    cell1.setBorder(Rectangle.NO_BORDER);
	    cell1.setPaddingLeft(2f);
	    cell1.setPaddingRight(10f);
	    cell1.setPaddingBottom(2f);
	    cell1.setColspan(2);
	    table.addCell(cell1);
	    
	    PdfPTable innertable2 = new PdfPTable(2);
	    innertable2.setWidths(new int[]{1, 1});
	    	                
	    PdfPCell cel4 = new PdfPCell(new Phrase(inspAllHeaders.getSigned() + " : " + signObject.getNameSigned(), font));
	    cel4.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cel4.setBorder(Rectangle.TOP);
	    cel4.setPaddingLeft(5f);
	    
	    PdfPCell cel5 = new PdfPCell(new Phrase(inspAllHeaders.getDate() + " : " + signObject.getDateSigned(), font));
	    cel5.setHorizontalAlignment(Element.ALIGN_RIGHT);
	    cel5.setBorder(Rectangle.TOP);
	    if("".equals(signObject.getDateSigned().trim()))
	    {
	    	cel5.setPaddingRight(50f);
	    }
	    
	    innertable2.addCell(cel4);
	    innertable2.addCell(cel5);
	    
	    PdfPCell cell2 = new PdfPCell(innertable2);
	    cell2.setBorder(Rectangle.NO_BORDER);
	    cell2.setPaddingLeft(10f);
	    cell2.setPaddingRight(2f);
	    cell2.setPaddingBottom(2f);
	    cell2.setColspan(2);
	    table.addCell(cell2);
	} catch (DocumentException | IOException e1) {
		logger.error(errorString , e1);
	}
    return table;
}

public PdfPTable getInspectionContentTable(LinkedHashMap<String, List<InspCheckObject>> inspCheckObjectMapTemp) {
	List<InspCheckObject> inspChecklistData = new ArrayList<>();
	Iterator it = inspCheckObjectMapTemp.entrySet().iterator();
    while (it.hasNext()) {
        Map.Entry pair = (Map.Entry)it.next();
        List<InspCheckObject> inspChecklistTemp = (List<InspCheckObject>) pair.getValue();
        InspCheckObject lInspCheckObject = new InspCheckObject();
        lInspCheckObject.setDescription(pair.getKey().toString() + "- (as applicable)");
        lInspCheckObject.setCheckListItem("");
        lInspCheckObject.setMaintDesc("");
        lInspCheckObject.setStatus("");
        inspChecklistData.add(lInspCheckObject);
        for(InspCheckObject inspCheckObject : inspChecklistTemp)
        {
        	inspChecklistData.add(inspCheckObject);
        }
    }
    float cnt= Float.parseFloat(String.valueOf(inspChecklistData.size()))/3;
    int count = (int) Math.ceil(cnt);

	PdfPTable table = new PdfPTable(3);
    try {
		table.setWidths(new int[]{1, 1, 1});
		for(int i=0;i<3;i++)
	    {    	
	    	int startIndex = 0;
	    	int endIndex = 0;
	    	if(i==0)
	    	{
	    		startIndex = 0;
	    		endIndex = count-1;
	    	}
	    	if(i==1)
	    	{
	    		startIndex = count;
	    		endIndex = (count*2)-1;
	    	}
	    	if(i==2)
	    	{
	    		startIndex = count*2;
	    		endIndex = inspChecklistData.size()-1;
	    	}
	    	
	    	PdfPTable innerTable = new PdfPTable(3);
	    	innerTable.setWidths(new int[]{1, 10, 1});
	    	for(int j=startIndex;j<=endIndex;j++)
	    	{
	    		pdfCommonMethods.addInspectionContent(inspChecklistData,j,innerTable,base);
	    	}   	
	    	
	    	PdfPCell cell1 = new PdfPCell(innerTable);
	        cell1.setBorder(Rectangle.NO_BORDER);
	        cell1.setPaddingLeft(2f);
	        cell1.setPaddingRight(2f);
	        cell1.setPaddingTop(2f);
	        cell1.setPaddingBottom(2f);
	        table.addCell(cell1);
	    }
	} catch (DocumentException | IOException e) {
		logger.error(errorString , e);
	}
    return table;
}

 public PdfPTable getHeaderTable1(InspAllHeaders inspAllHeaders, Map<String, String> inspHeaderMap) {
	Font font = new Font(base, 7f, Font.NORMAL);
	font.setColor(Color.DARK_GRAY);
	Font font1 = new Font(base, 7f, Font.NORMAL);
    PdfPTable table = new PdfPTable(2);
    PdfPCell cellOne = new PdfPCell(new Phrase(inspAllHeaders.getTrailerNr() ,font));
    cellOne.setHorizontalAlignment(Element.ALIGN_RIGHT);
    cellOne.setPaddingTop(5f);
    cellOne.setPaddingBottom(5f);
    cellOne.setPaddingRight(10f);
    
    PdfPCell cellTwo = new PdfPCell(new Phrase(inspHeaderMap.get("trailerNr"), font1));
    cellTwo.setHorizontalAlignment(Element.ALIGN_LEFT);
    cellTwo.setPaddingTop(5f);
    cellTwo.setPaddingBottom(5f);
    
    PdfPCell cellThree = new PdfPCell(new Phrase(inspAllHeaders.getRegistrationNr() ,font));
    cellThree.setHorizontalAlignment(Element.ALIGN_RIGHT);
    cellThree.setPaddingTop(5f);
    cellThree.setPaddingBottom(5f);
    cellThree.setPaddingRight(10f);
    
    PdfPCell cellFour = new PdfPCell(new Phrase(inspHeaderMap.get("registrationNr"), font1));
    cellFour.setHorizontalAlignment(Element.ALIGN_LEFT);
    cellFour.setPaddingTop(5f);
    cellFour.setPaddingBottom(5f);
    
    PdfPCell cellFive = new PdfPCell(new Phrase(inspAllHeaders.getServiceType(),font));
    cellFive.setHorizontalAlignment(Element.ALIGN_RIGHT);
    cellFive.setPaddingTop(5f);
    cellFive.setPaddingBottom(5f);
    cellFive.setPaddingRight(10f);
    
    PdfPCell cellSix = new PdfPCell(new Phrase(inspHeaderMap.get("serviceType"), font1));
    cellSix.setHorizontalAlignment(Element.ALIGN_LEFT);
    cellSix.setPaddingTop(5f);
    cellSix.setPaddingBottom(5f);

    cellOne.setBorder(Rectangle.NO_BORDER);
    cellOne.setBackgroundColor(Color.WHITE);
    cellTwo.setBorder(Rectangle.NO_BORDER);
    cellTwo.setBackgroundColor(Color.WHITE);
    cellThree.setBorder(Rectangle.TOP);
    cellThree.setBorderColor(Color.GRAY);
    cellThree.setBackgroundColor(Color.WHITE);
    cellFour.setBorder(Rectangle.TOP);
    cellFour.setBorderColor(Color.GRAY);
    cellFour.setBackgroundColor(Color.WHITE);
    cellFive.setBorder(Rectangle.TOP);
    cellFive.setBorderColor(Color.GRAY);
    cellFive.setBackgroundColor(Color.WHITE);
    cellSix.setBorder(Rectangle.TOP);
    cellSix.setBorderColor(Color.GRAY);
    cellSix.setBackgroundColor(Color.WHITE);

    table.addCell(cellOne);
    table.addCell(cellTwo);
    table.addCell(cellThree);
    table.addCell(cellFour);
    table.addCell(cellFive);
    table.addCell(cellSix);
    return table;
}
 public PdfPTable getHeaderTable2(InspAllHeaders inspAllHeaders, Map<String, String> inspHeaderMap) {
		Font font = new Font(base, 7f, Font.NORMAL);
		font.setColor(Color.DARK_GRAY);
		Font font1 = new Font(base, 7f, Font.NORMAL);
		
        PdfPTable table = new PdfPTable(2);
        PdfPCell cellOne = new PdfPCell(new Phrase(inspAllHeaders.getChassis() ,font));
        cellOne.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cellOne.setPaddingTop(5f);
        cellOne.setPaddingBottom(5f);
        cellOne.setPaddingRight(10f);
        
        PdfPCell cellTwo = new PdfPCell(new Phrase(inspHeaderMap.get("chassis"), font1));
        cellTwo.setHorizontalAlignment(Element.ALIGN_LEFT);
        cellTwo.setPaddingTop(5f);
        cellTwo.setPaddingBottom(5f);
        
        PdfPCell cellThree = new PdfPCell(new Phrase(inspAllHeaders.getMileage() ,font));
        cellThree.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cellThree.setPaddingTop(5f);
        cellThree.setPaddingBottom(5f);
        cellThree.setPaddingRight(10f);
        
        PdfPCell cellFour = new PdfPCell(new Phrase(inspHeaderMap.get("mileage"), font1));
        cellFour.setHorizontalAlignment(Element.ALIGN_LEFT);
        cellFour.setPaddingTop(5f);
        cellFour.setPaddingBottom(5f);
        
        PdfPCell cellFive = new PdfPCell(new Phrase(inspAllHeaders.getMotDueDate() ,font));
        cellFive.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cellFive.setPaddingTop(5f);
        cellFive.setPaddingBottom(5f);
        cellFive.setPaddingRight(10f);
        
        PdfPCell cellSix = new PdfPCell(new Phrase(inspHeaderMap.get("motDueDate"), font1));
        cellSix.setHorizontalAlignment(Element.ALIGN_LEFT);
        cellSix.setPaddingTop(5f);
        cellSix.setPaddingBottom(5f);

        cellOne.setBorder(Rectangle.NO_BORDER);
        cellOne.setBackgroundColor(Color.WHITE);
        cellTwo.setBorder(Rectangle.NO_BORDER);
        cellTwo.setBackgroundColor(Color.WHITE);
        cellThree.setBorder(Rectangle.TOP);
        cellThree.setBorderColor(Color.GRAY);
        cellThree.setBackgroundColor(Color.WHITE);
        cellFour.setBorder(Rectangle.TOP);
        cellFour.setBorderColor(Color.GRAY);
        cellFour.setBackgroundColor(Color.WHITE);
        cellFive.setBorder(Rectangle.TOP);
        cellFive.setBorderColor(Color.GRAY);
        cellFive.setBackgroundColor(Color.WHITE);
        cellSix.setBorder(Rectangle.TOP);
        cellSix.setBorderColor(Color.GRAY);
        cellSix.setBackgroundColor(Color.WHITE);

        table.addCell(cellOne);
        table.addCell(cellTwo);
        table.addCell(cellThree);
        table.addCell(cellFour);
        table.addCell(cellFive);
        table.addCell(cellSix);
        return table;
    }
 public PdfPTable getHeaderTable3(InspAllHeaders inspAllHeaders, Map<String, String> inspHeaderMap) {
		Font font = new Font(base, 7f, Font.NORMAL);
		font.setColor(Color.DARK_GRAY);
		Font font1 = new Font(base, 7f, Font.NORMAL);
		
        PdfPTable table = new PdfPTable(2);
        PdfPCell cellOne = new PdfPCell(new Phrase(inspAllHeaders.getJobDate() ,font));
        cellOne.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cellOne.setPaddingTop(5f);
        cellOne.setPaddingBottom(5f);
        cellOne.setPaddingRight(10f);
        
        PdfPCell cellTwo = new PdfPCell(new Phrase(inspHeaderMap.get("jobDate"), font1));
        cellTwo.setHorizontalAlignment(Element.ALIGN_LEFT);
        cellTwo.setPaddingTop(5f);
        cellTwo.setPaddingBottom(5f);
        
        PdfPCell cellThree = new PdfPCell(new Phrase(inspAllHeaders.getJobSheetNr() ,font));
        cellThree.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cellThree.setPaddingTop(5f);
        cellThree.setPaddingBottom(5f);
        cellThree.setPaddingRight(10f);
        
        PdfPCell cellFour = new PdfPCell(new Phrase(inspHeaderMap.get("jobSheetNr"), font1));
        cellFour.setHorizontalAlignment(Element.ALIGN_LEFT);
        cellFour.setPaddingTop(5f);
        cellFour.setPaddingBottom(5f);
        
        PdfPCell cellFive = new PdfPCell(new Phrase(inspAllHeaders.getOperator() ,font));
        cellFive.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cellFive.setPaddingTop(5f);
        cellFive.setPaddingBottom(5f);
        cellFive.setPaddingRight(10f);
        
        PdfPCell cellSix = new PdfPCell(new Phrase(inspHeaderMap.get("operator"), font1));
        cellSix.setHorizontalAlignment(Element.ALIGN_LEFT);
        cellSix.setPaddingTop(5f);
        cellSix.setPaddingBottom(5f);

        cellOne.setBorder(Rectangle.NO_BORDER);
        cellOne.setBackgroundColor(Color.WHITE);
        cellTwo.setBorder(Rectangle.NO_BORDER);
        cellTwo.setBackgroundColor(Color.WHITE);
        cellThree.setBorder(Rectangle.TOP);
        cellThree.setBorderColor(Color.GRAY);
        cellThree.setBackgroundColor(Color.WHITE);
        cellFour.setBorder(Rectangle.TOP);
        cellFour.setBorderColor(Color.GRAY);
        cellFour.setBackgroundColor(Color.WHITE);
        cellFive.setBorder(Rectangle.TOP);
        cellFive.setBorderColor(Color.GRAY);
        cellFive.setBackgroundColor(Color.WHITE);
        cellSix.setBorder(Rectangle.TOP);
        cellSix.setBorderColor(Color.GRAY);
        cellSix.setBackgroundColor(Color.WHITE);

        table.addCell(cellOne);
        table.addCell(cellTwo);
        table.addCell(cellThree);
        table.addCell(cellFour);
        table.addCell(cellFive);
        table.addCell(cellSix);
        return table;
    }
 
 public PdfPTable getHeaderTable4(InspAllHeaders inspAllHeaders, Map<String, String> inspHeaderMap) {
		Font font = new Font(base, 7f, Font.NORMAL);
		font.setColor(Color.DARK_GRAY);
		Font font1 = new Font(base, 7f, Font.NORMAL);
		
     PdfPTable table = new PdfPTable(2);
     PdfPCell cellOne = new PdfPCell(new Phrase(inspAllHeaders.getPressureTestDate() ,font));
     cellOne.setHorizontalAlignment(Element.ALIGN_RIGHT);
     cellOne.setPaddingTop(5f);
     cellOne.setPaddingBottom(5f);
     cellOne.setPaddingRight(10f);
     
     PdfPCell cellTwo = new PdfPCell(new Phrase(inspHeaderMap.get("PressureTestDate"), font1));
     cellTwo.setHorizontalAlignment(Element.ALIGN_LEFT);
     cellTwo.setPaddingTop(5f);
     cellTwo.setPaddingBottom(5f);

     cellOne.setBorder(Rectangle.NO_BORDER);
     cellOne.setBackgroundColor(Color.WHITE);
     cellTwo.setBorder(Rectangle.NO_BORDER);
     cellTwo.setBackgroundColor(Color.WHITE);

     table.addCell(cellOne);
     table.addCell(cellTwo);
     return table;
 }
  
 public PdfPTable getIconTable(InspAllHeaders inspAllHeaders) {
		Font font = new Font(base, 6f, Font.NORMAL);
		font.setColor(Color.DARK_GRAY);

        PdfPTable table = new PdfPTable(6);
        try {
			table.setWidths(new int[]{1, 3, 1, 2, 1, 4});
			Image checkImg = Image.getInstance(HeaderFooterPageEvent.class.getResource("/Yes.png"));
	        checkImg.scaleAbsolute(8f, 8f);
	        PdfPCell cellOne = new PdfPCell(checkImg);
	        cellOne.setHorizontalAlignment(Element.ALIGN_RIGHT);
	        cellOne.setPaddingRight(5f);
	        cellOne.setPaddingTop(5f);
	        cellOne.setPaddingBottom(5f);
	        
	        PdfPCell cellTwo = new PdfPCell(new Phrase(inspAllHeaders.getServicable(), font));
	        cellTwo.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cellTwo.setPaddingTop(5f);
	        cellTwo.setPaddingBottom(5f);
	        
	        Image crossImg = Image.getInstance(HeaderFooterPageEvent.class.getResource("/No.png"));
	        crossImg.scaleAbsolute(8f, 8f);
	        PdfPCell cellThree = new PdfPCell(crossImg);
	        cellThree.setHorizontalAlignment(Element.ALIGN_RIGHT);
	        cellThree.setPaddingRight(5f);
	        cellThree.setPaddingTop(5f);
	        cellThree.setPaddingBottom(5f);
	        
	        PdfPCell cellFour = new PdfPCell(new Phrase(inspAllHeaders.getDefect(), font));
	        cellFour.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cellFour.setPaddingTop(5f);
	        cellFour.setPaddingBottom(5f);
	        
	        Image nAImg = Image.getInstance(HeaderFooterPageEvent.class.getResource("/NA.png"));
	        nAImg.scaleAbsolute(8f, 8f);
	        PdfPCell cellFive = new PdfPCell(nAImg);
	        cellFive.setHorizontalAlignment(Element.ALIGN_RIGHT);
	        cellFive.setPaddingRight(5f);
	        cellFive.setPaddingTop(5f);
	        cellFive.setPaddingBottom(5f);
	        
	        PdfPCell cellSix = new PdfPCell(new Phrase(inspAllHeaders.getNotApplicable(),font));
	        cellSix.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cellSix.setPaddingTop(5f);
	        cellSix.setPaddingBottom(5f);

	        cellOne.setBorder(Rectangle.NO_BORDER);
	        cellTwo.setBorder(Rectangle.NO_BORDER);
	        cellThree.setBorder(Rectangle.NO_BORDER);
	        cellFour.setBorder(Rectangle.NO_BORDER);
	        cellFive.setBorder(Rectangle.NO_BORDER);
	        cellSix.setBorder(Rectangle.NO_BORDER);

	        table.addCell(cellOne);
	        table.addCell(cellTwo);
	        table.addCell(cellThree);
	        table.addCell(cellFour);
	        table.addCell(cellFive);
	        table.addCell(cellSix);
	        table.setTotalWidth(PageSize.A4.getWidth()/2 - 20f);
	        table.setLockedWidth(true);
		} catch (DocumentException | IOException e) {
			logger.error(errorString , e);
		}
        return table;
    }

public ResponseObject createPdf(InspAllHeaders inspAllHeaders, Map<String, String> inspHeaderMap, LinkedHashMap<String, 
		List<InspCheckObject>> inspCheckObjectMapTemp, List<Object> inspDefectlist,InspSignObject signObject) {    
	ResponseObject responseObj = new ResponseObject();
	try {
		byte[] bytes = IOUtils.toByteArray(Thread.currentThread().getContextClassLoader()
	            .getResourceAsStream("trebuchet-ms-bold-596f552632220.ttf"));
	    base = BaseFont.createFont("trebuchet-ms-bold-596f552632220.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED, false, bytes, null);
	    
	Document document = new Document(PageSize.A4, 36, 36, 90, 36);
    //File file = new File("Trailer_Inspection_Report_"+inspHeaderMap.get("JobSheetNr")+"_"+inspAllHeaders.getServiceType()+".pdf");
	File file = new File(inspHeaderMap.get("ReportLocation")+".pdf");
    FileOutputStream fileOutputStream = new FileOutputStream(file);
    InputStream inputStream = new FileInputStream(file);
    PdfWriter writer = PdfWriter.getInstance(document, fileOutputStream);
    HeaderFooterPageEvent event = new HeaderFooterPageEvent(inspHeaderMap.get("TIPLogo"),inspHeaderMap.get("CustLogo"),inspHeaderMap.get("InspReportText"));
    writer.setPageEvent(event);
    document.open();
    document.setMargins(10, 10, 50, 12);
    
    PdfPTable hTable = getHeaderTable(inspAllHeaders,inspHeaderMap);
    hTable.writeSelectedRows(0, -1, 0,786, writer.getDirectContent());
    PdfPTable blankTable = new PdfPTable(1);
    PdfPCell blankCell = new PdfPCell(new Phrase("         "));
    float diff = hTable.getTotalHeight() - 83;
    blankCell.setPaddingTop(30f + diff);
    blankCell.setPaddingBottom(10f);
    blankCell.setBorder(Rectangle.NO_BORDER);
    blankTable.addCell(blankCell);
    blankTable.getDefaultCell().setBorder(0);
    document.add(blankTable);
    document.add(getBodyTable(inspAllHeaders,inspHeaderMap,inspCheckObjectMapTemp,inspDefectlist,signObject));
	document.close();
    responseObj.setFile(file);
    responseObj.setInputStream(inputStream);
    responseObj.setOutputStream(fileOutputStream);
	} catch (IOException | DocumentException e) {
		logger.error(errorString, e);
	}    
	return responseObj;
}
}
