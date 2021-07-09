package com.tip.assetpdf.model;

import java.awt.Color;
import java.util.List;

import org.apache.log4j.Logger;


import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

public class PDFElementITextHelper extends PdfPageEventHelper{
	private static final Logger LOG = Logger.getLogger(PDFElementITextHelper.class);
	private String uri;	
	public BaseFont timesRoman;
	public Image draftImage;	
	private List<FullSpecPrintData> fullSpecPrint = null;
	private List<FullSpecPrintData> customerSpecPrint = null;
	private String headingCustomerPrint;
	private String unitNrFooter;
	private String catDesc;
	public String getCatDesc() {
		return catDesc;
	}

	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}

	public String getUnitNrFooter() {
		return unitNrFooter;
	}

	public void setUnitNrFooter(String unitNrFooter) {
		this.unitNrFooter = unitNrFooter;
	}

	public String getHeadingCustomerPrint() {
		return headingCustomerPrint;
	}

	public void setHeadingCustomerPrint(String headingCustomerPrint) {
		this.headingCustomerPrint = headingCustomerPrint;
	}

	public List<FullSpecPrintData> getCustomerSpecPrint() {
		return customerSpecPrint;
	}

	public void setCustomerSpecPrint(List<FullSpecPrintData> customerSpecPrint) {
		this.customerSpecPrint = customerSpecPrint;
	}

	public List<FullSpecPrintData> getFullSpecPrint() {
		return fullSpecPrint;
	}

	public void setFullSpecPrint(List<FullSpecPrintData> fullSpecPrint) {
		this.fullSpecPrint = fullSpecPrint;
	}

	public void onStartPage(PdfWriter writer, Document document){
		LOG.info("PDFElementITextHelper.onStartPage()");
		PdfContentByte cb = null;
		try{
			/*if(false){
				cb = writer.getDirectContentUnder();
				draftImage = Image.getInstance(uri+"/images/Copy.JPG");
				cb.addImage(draftImage, draftImage.scaledWidth()-10, 0,0, draftImage.scaledHeight()-10, 300, 720);
			}*/
			printHeaderFooter(writer,document);
		}catch(Exception ex){
			LOG.error("PDFElementITextHelper.onStartPage()"+ex.getMessage());
			
		}
	}

	public void onOpenDocument(PdfWriter writer, Document document){
		LOG.info("PDFElementITextHelper.onOpenDocument()");
		try{
			if(true){
				draftImage = Image.getInstance(uri+"/images/Copy.JPG");
			}			
			//template= writer.getDirectContent().createTemplate(100, 100);
			//template.setBoundingBox(new Rectangle(-20, -20, 100, 100));
			template= writer.getDirectContent().createTemplate(80, 80);
			template.setBoundingBox(new Rectangle(-20, -20, 80, 80));
			helvFont=BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI,BaseFont.NOT_EMBEDDED);
			System.out.println("helvFont :"+helvFont);
		}catch(Exception ex){
			LOG.error("PDFElementITextHelper.onOpenDocument()"+ex.getMessage());
		}
	}

	protected BaseFont helvFont;
	protected PdfTemplate template;
	
	
//	public void onOpenDocument(PdfWriter writer, Document arg1) {
//		try {
//			template= writer.getDirectContent().createTemplate(100, 100);
//			template.setBoundingBox(new Rectangle(-20, -20, 100, 100));
//			
//			helvFont=BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI,BaseFont.NOT_EMBEDDED);
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	public void onEndPage(PdfWriter writer, Document document) {
		PdfContentByte cb = writer.getDirectContent();
		cb.saveState();
		cb.restoreState();
		String text="";
		try {
//			text = writer.getPageNumber()+"/";
//			System.out.println("Text :"+text);
//			float textSize = helvFont.getWidthPoint(text, 8);
//			cb.beginText();
//			cb.setFontAndSize(helvFont, 8);
//	
//			float adjust = helvFont.getWidthPoint("0", 8);
//			
//			if(customerSpecPrint != null ){
//				
//				cb.setTextMatrix(document.left(), 6);
//				cb.showText("Unit: "+unitNrFooter);
//			}
//			
//			cb.setTextMatrix(document.right() - textSize - adjust, 6);
//			//cb.setTextMatrix(30,30);
//			
//			cb.showText(text);
//		
//			cb.endText();
//			cb.addTemplate(template, document.right() - adjust, 6);
//			
//			/*footerTable.setTotalWidth(page.width() - document.leftMargin() - document.rightMargin());
//			footerTable.writeSelectedRows(0,-1, 0, 6, document.leftMargin(), 20, writer.getDirectContent());*/
//			
//			//cb.addTemplate(template,35,30);
//			cb.restoreState();
		}catch (Exception e) {			
			e.printStackTrace();
		}
		
		
	}
	
	public void onCloseDocument(PdfWriter writer, Document document) {
//		////System.out.println("onCloseDocument");
//		//template.beginText();
//		template.setFontAndSize(helvFont, 8);
//		template.setTextMatrix(0,0);
//		/*HeaderFooter footer = new HeaderFooter(new Phrase("Page :                              test mark"), true);
//	       footer.setAlignment(Element.ALIGN_RIGHT);
//	       footer.disableBorderSide(3);
//	       document.setFooter(footer);*/
//		////System.out.println(String.valueOf(writer.getPageNumber() - 1));
//		//String text = writer.getPageNumber()+"/";
//		template.showText(String.valueOf(writer.getPageNumber() - 1));
//		template.endText();
	}
	
	public void printHeaderFooter(PdfWriter writer, Document document) throws Exception{
		LOG.info("PDFElementITextHelper.printHeaderFooter()");
		Rectangle page = null;
		Font colfont1 = null; 
		
		PdfContentByte cb = null;
		try{
			
			page = document.getPageSize();
			FontFactory.register(uri+"/images/calibri.ttf");	         
			colfont1 = FontFactory.getFont("Calibri", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 8);

			cb = writer.getDirectContent();
			PdfPTable headerTable = null;
			
			/***********************************Start of Print Invoice PDF***************************************/
			if(customerSpecPrint != null && customerSpecPrint.size() != 0){
				fullSpecPrint = null;
				headerTable = getCustomerPDF();

			}
			
			if(fullSpecPrint != null && fullSpecPrint.size() != 0){
				customerSpecPrint = null;
				
				headerTable = getFullSpecPDF();
			}
			
			headerTable.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
			headerTable.writeSelectedRows(0, -1, document.leftMargin(),document.getPageSize().getHeight(),writer.getDirectContent());
			
			//footer starting
			//Akhilesh Start
			PdfPTable footerTable = new PdfPTable(1);
			footerTable.getDefaultCell().setBorderWidth(0);
			footerTable.setWidthPercentage(100);
			
			
			PdfPTable legalTable = new PdfPTable(1);
			legalTable.setWidthPercentage(100);
			if(customerSpecPrint != null ){
			
				footerTable.addCell(new Phrase("Unit: "+unitNrFooter,colfont1));
			}
			
			LOG.info("test:>>>"+page.getWidth());
			
			//footerTable.addCell(new Phrase(" ",colfont1));
			//PdfContentByte cb = writer.getDirectContent();
			/*cb.saveState();
			String text="";
			//try {
				//text = writer.getPageNumber()+"/";					
				float textSize = helvFont.getWidthPoint(text, 8);					
				//test
				cb.beginText();
				cb.setFontAndSize(helvFont, 8);			
				float adjust = helvFont.getWidthPoint("0", 8);
				cb.setTextMatrix(20, 30);
				//cb.setFontAndSize(helvFont, 7);
				cb.setFontAndSize(helvFont, 8);
				cb.showText(text);
				cb.setTextMatrix(document.right() - textSize - adjust, 30);
				//cb.setTextMatrix(30,30);
				//cb.showText(text);				
				cb.endText();
				cb.addTemplate(template, document.right() - adjust, 30);
				//cb.addTemplate(template,35,30);
				cb.restoreState();*/
		//	footerTable.setTotalWidth(page.width() - document.leftMargin() - document.rightMargin());
			//footerTable.writeSelectedRows(0,-1, 0, 2, document.leftMargin(), 20, writer.getDirectContent());
			//Akhilesh End

			/*PdfPTable footerTable = new PdfPTable(1);
			footerTable.getDefaultCell().setBorderWidth(0);
			footerTable.setWidthPercentage(100);
			
			
			
			PdfPTable legalTable = new PdfPTable(1);
			legalTable.setWidthPercentage(100);
			if(customerSpecPrint != null ){
			
				footerTable.addCell(new Phrase("Unit: "+unitNrFooter,colfont1));				
				onEndPage(writer, document);
			//	PdfContentByte cb = writer.getDirectContent();
				cb.saveState();
				String text="";
				try {
					text = writer.getPageNumber()+"/";					
					float textSize = helvFont.getWidthPoint(text, 8);					
					//test
					cb.beginText();
					cb.setFontAndSize(helvFont, 8);			
					float adjust = helvFont.getWidthPoint("0", 8);
					cb.setTextMatrix(20, 30);
					//cb.setFontAndSize(helvFont, 7);
					cb.setFontAndSize(helvFont, 8);
					cb.showText("Unit: "+unitNrFooter);
					cb.setTextMatrix(document.right() - textSize - adjust, 30);
					//cb.setTextMatrix(30,30);
					//cb.showText(text);				
					cb.endText();
					cb.addTemplate(template, document.right() - adjust, 30);
					//cb.addTemplate(template,35,30);
					cb.restoreState();
				}catch (Exception e) {			
					e.printStackTrace();
				}
			}*/
		/*	footerTable.addCell(new Phrase(" ",colfont1));
			footerTable.setTotalWidth(page.width() - document.leftMargin() - document.rightMargin());
			footerTable.writeSelectedRows(0,-1, 0, 6, document.leftMargin(), 20, writer.getDirectContent());*/
			
		}catch(Exception ex){
			ex.printStackTrace();
			LOG.info("Excpetion : "+ex.getMessage());
			throw ex;
		}
		finally{
			page = null;
			colfont1 = null; 
			cb = null;
		}
	}

	private PdfPTable getCustomerPDF()throws Exception{
		LOG.info("Entered into getInvoicePDF");

		FontFactory.register(uri+"/images/calibri.ttf");
		FontFactory.register(uri+"/images/calibrib.ttf");

		Font colHeaderFont = FontFactory.getFont("Calibri Bold", 13);
		//Font colFont = FontFactory.getFont("Calibri", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 8);

		PdfPTable secondInfo = new PdfPTable(2);
		float[] columnWidths = new float[] {10, 70};
		secondInfo.setWidths(columnWidths);
		secondInfo.getDefaultCell().setBorder(0);
		secondInfo.setWidthPercentage(100);
		
		/*PdfPTable imgcell = new PdfPTable(1);
		//Image image = Image.getInstance(uri+"/images/Ge_logo.bmp");
		Image image = Image.getInstance("D:/EQSP_Images/Ge_logo.bmp");
		int subtitlewidthcell23[] = { 5,35, 20, 40};
		imgcell.setWidths(subtitlewidthcell23);
		imgcell.addCell(image);
		secondInfo.addCell(imgcell);*/
		
		
	//	Image image = Image.getInstance(uri+"/images/TIPLogo.png");
		Image image = Image.getInstance("http://dev.apps.tipeurope.com/applogo/TIPLogo.png");
		image.setDpi(30, 40);
		secondInfo.addCell(image);
		//PdfPCell logoCelll = new PdfPCell(image);
		//logoCelll.setFixedHeight(40);
		//secondInfo.addCell(logoCelll);
		
		PdfPCell headerCelll = new PdfPCell(new Phrase(CommonUtil.checkNullObject(headingCustomerPrint),colHeaderFont));
		headerCelll.setHorizontalAlignment(Element.ALIGN_CENTER);
		headerCelll.setVerticalAlignment(Element.ALIGN_MIDDLE);
		disableBorders(headerCelll);
		headerCelll.setBackgroundColor(Color.LIGHT_GRAY); 
		//headerCelll.setFixedHeight(40);
		secondInfo.addCell(headerCelll);
		
		
		
		return secondInfo;
	}

	private PdfPTable getFullSpecPDF() throws Exception{
		LOG.info("Entered into getInvoicePDF");

		FontFactory.register(uri+"/images/calibri.ttf");
		FontFactory.register(uri+"/images/calibrib.ttf");

		Font colfont1 = FontFactory.getFont("Calibri Bold", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 14);
		Font colfont2 = FontFactory.getFont("Calibri Bold", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 12);
		Font colFont = FontFactory.getFont("Calibri", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 8);

        PdfPTable headingTable = new PdfPTable(2);
        
        int[] width = {40,60};
        headingTable.setWidths(width);
        headingTable.setWidthPercentage(100);
        headingTable.getDefaultCell().setBorder(0);

		PdfPCell headerCelll = new PdfPCell(new Phrase("Specification Type:",colfont2));
		headerCelll.setHorizontalAlignment(Element.ALIGN_LEFT);
		disableBottomRightBorders(headerCelll);
		headingTable.addCell(headerCelll);
		
		PdfPCell headerCellr = new PdfPCell(new Phrase(catDesc,colfont2));
		headerCellr.setHorizontalAlignment(Element.ALIGN_LEFT);
		disableBottomLeftBorders(headerCellr);
		headingTable.addCell(headerCellr);
		
		PdfPCell headerCell2l = new PdfPCell(new Phrase("Printed:",colfont2));
		headerCell2l.setHorizontalAlignment(Element.ALIGN_LEFT);
		disableTopBottomRightBorders(headerCell2l);
		headingTable.addCell(headerCell2l);
		
		PdfPCell headerCell2r = new PdfPCell(new Phrase(CommonUtil.getToDayDateDDMONYYYY(),colfont2));
		headerCell2r.setHorizontalAlignment(Element.ALIGN_LEFT);
		disableTopBottomLeftBorders(headerCell2r);
		headingTable.addCell(headerCell2r);


		PdfPCell headerCell3l = new PdfPCell(new Phrase("Unit:",colfont2));
		headerCell3l.setHorizontalAlignment(Element.ALIGN_LEFT);
		disableTopRightBorders(headerCell3l);
		headingTable.addCell(headerCell3l);
        
		PdfPCell headerCell3r = new PdfPCell(new Phrase(getUnitNrFooter(),colfont2));
		headerCell3r.setHorizontalAlignment(Element.ALIGN_LEFT);
		//payCell.addElement(CommonUtil.addChunkToPhrase(branchHeader.getHeaderPaymentTo(),customerHeader.getHeaderPaymentTo()));
		disableTopLeftBorders(headerCell3r);
		headingTable.addCell(headerCell3r);

       

		return headingTable; 
	}

	public PdfPTable getTableContentLeft(String displayStr)throws Exception{
		LOG.info("PDFElementITextHelper.PdfPTable().....START");
		PdfPTable head = null;
		PdfPCell celltemp = null;
		Font colfont2 = null;
		try{
			head = new PdfPTable(1);
			head.setWidthPercentage(100);
			celltemp = null;
	
			FontFactory.register(uri+"/images/calibri.ttf");
			FontFactory.register(uri+"/images/calibrib.ttf");


			colfont2 = FontFactory.getFont("Calibri", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 8);

			celltemp=new PdfPCell(new Phrase(displayStr, colfont2));
			celltemp.setHorizontalAlignment(Element.ALIGN_LEFT);
			celltemp.setColspan(4);
			celltemp.setBorderWidth(0);
			head.addCell(celltemp); 
		}catch(Exception ex){
			LOG.error("Exception at PDFElementITextHelper.PdfPTable()....."+ex.getMessage());
			throw ex;
		}
		finally{
			LOG.info("PDFElementITextHelper.PdfPTable().....END");
			celltemp = null;
			colfont2 = null;
		}
		return head;
	}

	public PdfPTable getTableWeightGroup(String displayStrBold,String displayStr)throws Exception{
		PdfPTable head = null;
		PdfPCell celltemp = null;

		try{
			Phrase heading = new Phrase();
			heading.add(new Chunk(displayStrBold, new Font(Font.TIMES_ROMAN, 8f, Font.BOLD)));
			heading.add(new Chunk(displayStr, new Font(Font.TIMES_ROMAN, 8f)));
			head = new PdfPTable(1);
			head.setWidthPercentage(100);
			celltemp = null;

			celltemp.setHorizontalAlignment(Element.ALIGN_RIGHT);
			celltemp.setColspan(4);
			celltemp.setBorderWidth(0);
			head.addCell(celltemp); 
		}catch(Exception ex){
			throw ex;
		}
		finally{
			celltemp = null;

		}
		return head;
	}

	public PdfPTable getLineTable() throws Exception{
		PdfPTable lineTable = null;
		PdfPCell lineCell = null;
		Font colfont1 = null;
		try{
			FontFactory.register(uri+"/images/calibri.ttf");
			FontFactory.register(uri+"/images/calibrib.ttf");

			colfont1 = FontFactory.getFont("Calibri", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 8);


			lineTable = new PdfPTable(1);
			lineTable.setWidthPercentage(100);

			lineCell = new PdfPCell(new Phrase("",colfont1));
			lineCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			lineCell.setBorderWidth(0);
			lineTable.addCell(lineCell);

			lineCell = new PdfPCell(new Phrase("",colfont1));
			lineCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			lineCell.setBorderWidth(0);
			lineCell.setBorderWidthBottom(1f);
			lineTable.addCell(lineCell);

			lineCell = new PdfPCell(new Phrase("",colfont1));
			lineCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			lineCell.setBorderWidth(0);
			lineTable.addCell(lineCell);		

		}		
		catch(Exception ex){	
			LOG.info("Excpetion : "+ex.getMessage());
			throw ex;
		}finally
		{
			lineCell = null;
			colfont1 = null;
		}
		return lineTable;
	}


	public PdfPTable getLineSpace(int loop) throws Exception{
		PdfPTable lineTable = null;
		PdfPCell lineCell = null;
		Font colfont1 = null;
		try{
			FontFactory.register(uri+"/images/calibri.ttf");
			FontFactory.register(uri+"/images/calibrib.ttf");
			colfont1 = FontFactory.getFont("Calibri", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 8);
			lineTable = new PdfPTable(1);
			lineTable.setWidthPercentage(100);

			for(int counter = 0; counter<loop;counter++){
				lineCell = new PdfPCell(new Phrase("",colfont1));
				lineCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				lineCell.setBorderWidth(0);
				lineTable.addCell(lineCell);
			}
		}		
		catch(Exception ex){	
			throw ex;
		}finally
		{
			lineCell = null;
			colfont1 = null;
		}
		return lineTable;
	}


	private static void disableBorders(PdfPCell cell){
		cell.disableBorderSide(PdfPCell.LEFT);
		cell.disableBorderSide(PdfPCell.RIGHT);
		cell.disableBorderSide(PdfPCell.TOP);
		cell.disableBorderSide(PdfPCell.BOTTOM);
	}

	private static void disableBordersTopRightBottom(PdfPCell cell){
		cell.disableBorderSide(PdfPCell.RIGHT);
		cell.disableBorderSide(PdfPCell.TOP);
		cell.disableBorderSide(PdfPCell.BOTTOM);
	}

	private static void disableBordersTopLeftBottom(PdfPCell cell){
		cell.disableBorderSide(PdfPCell.LEFT);
		cell.disableBorderSide(PdfPCell.TOP);
		cell.disableBorderSide(PdfPCell.BOTTOM);
	}

	private static void disableBordersTopRight(PdfPCell cell){
		cell.disableBorderSide(PdfPCell.TOP);
		cell.disableBorderSide(PdfPCell.RIGHT);

	}

	private static void disableBordersTopLeftRight(PdfPCell cell){
		cell.disableBorderSide(PdfPCell.TOP);
		cell.disableBorderSide(PdfPCell.LEFT);
		cell.disableBorderSide(PdfPCell.RIGHT);
	}

	private static void disableBordersTopLeft(PdfPCell cell){
		cell.disableBorderSide(PdfPCell.LEFT);
		cell.disableBorderSide(PdfPCell.TOP);
	}

	private static void disableBordersBottomLeft(PdfPCell cell){
		cell.disableBorderSide(PdfPCell.BOTTOM);
		cell.disableBorderSide(PdfPCell.LEFT);

	}

	private static void disableBordersBottomRight(PdfPCell cell){
		cell.disableBorderSide(PdfPCell.BOTTOM);
		cell.disableBorderSide(PdfPCell.RIGHT);
	}

	private static void disableBordersLeftBottomRight(PdfPCell cell){
		cell.disableBorderSide(PdfPCell.BOTTOM);
		cell.disableBorderSide(PdfPCell.LEFT);
		cell.disableBorderSide(PdfPCell.RIGHT);
	}
	private static void disableBordersTopBottom(PdfPCell cell){
		cell.disableBorderSide(PdfPCell.BOTTOM);
		cell.disableBorderSide(PdfPCell.TOP);
	}
	

	
	private static void disableTopLeftRightBorders(PdfPCell cell)
	{
		cell.disableBorderSide(PdfPCell.LEFT);
		cell.disableBorderSide(PdfPCell.RIGHT);
		cell.disableBorderSide(PdfPCell.TOP);
	
	}
	private static void disableBottomRightBorders(PdfPCell cell)
	{
		cell.disableBorderSide(PdfPCell.BOTTOM);
		cell.disableBorderSide(PdfPCell.RIGHT);
	}
	private static void disableLeftRightBorders(PdfPCell cell)
	{
		cell.disableBorderSide(PdfPCell.LEFT);
		cell.disableBorderSide(PdfPCell.RIGHT);
	}
	private static void disableBottomLeftBorders(PdfPCell cell)
	{
		cell.disableBorderSide(PdfPCell.LEFT);
		cell.disableBorderSide(PdfPCell.BOTTOM);
	}
	private static void disableTopRightBorders(PdfPCell cell)
	{
		cell.disableBorderSide(PdfPCell.TOP);
		cell.disableBorderSide(PdfPCell.RIGHT);
	}
	private static void disableTopLeftBorders(PdfPCell cell)
	{
		cell.disableBorderSide(PdfPCell.TOP);
		cell.disableBorderSide(PdfPCell.LEFT);
	}
	private static void disableBottomBorders(PdfPCell cell)
	{
		cell.disableBorderSide(PdfPCell.BOTTOM);
	}
	private static void disableTopBorders(PdfPCell cell)
	{
		cell.disableBorderSide(PdfPCell.TOP);
	}
	private static void disableTopBottomBorders(PdfPCell cell)
	{
		cell.disableBorderSide(PdfPCell.TOP);
		cell.disableBorderSide(PdfPCell.BOTTOM);
	}
	private static void disableTopBottomLeftBorders(PdfPCell cell)
	{
		cell.disableBorderSide(PdfPCell.TOP);
		cell.disableBorderSide(PdfPCell.BOTTOM);
		cell.disableBorderSide(PdfPCell.LEFT);
	}
	private static void disableTopBottomRightBorders(PdfPCell cell)
	{
		cell.disableBorderSide(PdfPCell.TOP);
		cell.disableBorderSide(PdfPCell.BOTTOM);
		cell.disableBorderSide(PdfPCell.RIGHT);
	}





	
	
	
	
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}



	
}

