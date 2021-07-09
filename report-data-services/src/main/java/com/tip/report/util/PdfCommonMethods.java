package com.tip.report.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.html.WebColors;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.tip.fetchinspectionreport.model.InspAllHeaders;
import com.tip.fetchinspectionreport.model.InspCheckObject;
import com.tip.fetchinspectionreport.model.InspSignObject;

@ComponentScan
public class PdfCommonMethods {
	
	static final Logger logger = LoggerFactory.getLogger(PdfCommonMethods.class);
	
	PdfPCell addInspHttpImage(InspSignObject signObject, PdfPCell cellOne){
		PdfPCell cellTwo;
		try{
			URL url = new URL(signObject.getTipSignInspector());
			Image signImg1 = Image.getInstance(url);
			signImg1.scaleAbsolute(70f, 25f);
	        cellTwo = new PdfPCell(signImg1);
			}catch(Exception e){
				cellTwo = new PdfPCell(new Phrase(""));
		    	cellOne.setPaddingBottom(67);
		    	logger.info("Inspector Signature Image not found ", e);
			}
		return cellTwo;
	}

	PdfPCell addSignedHttpImage(InspSignObject signObject, PdfPCell cel2){
		PdfPCell cel3;
		try{
			URL url = new URL(signObject.getTipSignSigned());    		
			Image signImg3 = Image.getInstance(url);
			signImg3.scaleAbsolute(70f, 25f);
	        cel3 = new PdfPCell(signImg3);
			}catch(Exception e){
				cel3 = new PdfPCell(new Phrase(""));
		    	cel2.setPaddingBottom(35);
		    	logger.info("Signature image not found ", e);
			}
		return cel3;
	}

	PdfPCell addInspFileImage(InspSignObject signObject, PdfPCell cellOne) throws BadElementException, IOException{
		PdfPCell cellTwo;
		File f = new File(signObject.getTipSignInspector());
		if(f.exists()){
			Image signImg1 = Image.getInstance(signObject.getTipSignInspector());
			signImg1.scaleAbsolute(70f, 25f);
	        cellTwo = new PdfPCell(signImg1);
		}
		else
		{
			cellTwo = new PdfPCell(new Phrase(""));
	    	cellOne.setPaddingBottom(67);
		} 
		return cellTwo;
	}

	PdfPCell addSignedFileImage(InspSignObject signObject, PdfPCell cel2) throws BadElementException, IOException{
		PdfPCell cel3;
		File f = new File(signObject.getTipSignSigned());
		if(f.exists()){
		Image signImg3 = Image.getInstance(signObject.getTipSignSigned());
		signImg3.scaleAbsolute(70f, 25f);
	    cel3 = new PdfPCell(signImg3);
		}
		else
		{
			cel3 = new PdfPCell(new Phrase(""));
	    	cel2.setPaddingBottom(35);
		}
		return cel3;
	}

	Image addRoadworthyImage(boolean roadFlag, InspAllHeaders inspAllHeaders) throws IOException, BadElementException{
		Image signImg2;
	    if(roadFlag)
	    {
	    	final BufferedImage image = ImageIO.read(TankerInspectionReportPdf.class.getResource("/Roadworthy.png"));
	        Graphics g = image.getGraphics();
	        g.setFont(new java.awt.Font("Trebuchet MS", Font.BOLD, 20));
	        g.drawString(inspAllHeaders.getRoadWorthy(), 85, 40);
	        g.dispose();
	        ImageIO.write(image, "png", new File("Roadworthy.png"));
	        signImg2 = Image.getInstance("Roadworthy.png");
	    	signImg2.scaleAbsolute(100f, 25f);
	    }
	    else
	    {
	    	final BufferedImage image = ImageIO.read(TankerInspectionReportPdf.class.getResource("/NewNotRoadWorthy.png"));
	        Graphics g = image.getGraphics();
	        g.setFont(new java.awt.Font("Trebuchet MS", Font.BOLD, 20));
	        g.drawString(inspAllHeaders.getRoadWorthy(), 80, 40);
	        g.dispose();
	        ImageIO.write(image, "png", new File("NotRoadWorthy.png"));
	        signImg2 = Image.getInstance("NotRoadWorthy.png");
	    	signImg2.scaleAbsolute(120f, 25f);
	    }
		return signImg2;
	}
	
	 void addInspectionContent(List<InspCheckObject> inspChecklistData, int j, PdfPTable innerTable, BaseFont base) throws BadElementException, IOException {
		 Color bodyBgColor = WebColors.getRGBColor("#babec3");
			Font font = new Font(base, 6f, Font.NORMAL);
			Font boldFont = new Font(base, 7f, Font.BOLD);
		 if(inspChecklistData.get(j).getMaintDesc() != null && inspChecklistData.get(j).getMaintDesc().isEmpty())
			{    		
	         PdfPCell headCell = new PdfPCell(new Phrase(inspChecklistData.get(j).getDescription(), boldFont));
	     	headCell.setHorizontalAlignment(Element.ALIGN_LEFT);
	         headCell.setColspan(3);
	         headCell.setBackgroundColor(bodyBgColor);
	         headCell.setBorderColor(Color.GRAY);
	         headCell.setPaddingBottom(2f);
	         innerTable.addCell(headCell);
			}
			else
			{
	 		PdfPCell cellOne = new PdfPCell(new Phrase(inspChecklistData.get(j).getCheckListItem(), font));
	         cellOne.setHorizontalAlignment(Element.ALIGN_CENTER);
	         cellOne.setBorderColor(Color.GRAY);
	         cellOne.setPaddingBottom(1f);
	         PdfPCell cellTwo = new PdfPCell(new Phrase(inspChecklistData.get(j).getDescription(), font));
	         cellTwo.setHorizontalAlignment(Element.ALIGN_LEFT);
	         cellTwo.setBorderColor(Color.GRAY);
	         cellTwo.setPaddingBottom(1f);
	         Image checkImg;
	         if("P".equalsIgnoreCase(inspChecklistData.get(j).getStatus().trim()))
	         {
	         	checkImg = Image.getInstance(HeaderFooterPageEvent.class.getResource("/Yes.png"));
	         	checkImg.scaleAbsolute(8f, 8f);
	         }
	         else if("NA".equalsIgnoreCase(inspChecklistData.get(j).getStatus().trim()))
	         {
	         	checkImg = Image.getInstance(HeaderFooterPageEvent.class.getResource("/NA.png"));
	         	checkImg.scaleAbsolute(8f, 8f);
	         }
	         else
	         {
	         	checkImg = Image.getInstance(HeaderFooterPageEvent.class.getResource("/No.png"));
	         	checkImg.scaleAbsolute(8f, 8f);
	         }
	         PdfPCell cellThree = new PdfPCell(checkImg);
	         cellThree.setBorderColor(Color.GRAY);
	         cellThree.setPaddingTop(2f);
	         cellThree.setPaddingBottom(1f);
	         cellThree.setHorizontalAlignment(Element.ALIGN_CENTER);	            
	         innerTable.addCell(cellOne);
	         innerTable.addCell(cellTwo);
	         innerTable.addCell(cellThree);
			}
	}
}
