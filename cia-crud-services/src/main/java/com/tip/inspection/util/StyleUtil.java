package com.tip.inspection.util;

import java.awt.Color;
import java.io.IOException;
import java.util.Map.Entry;

import org.apache.pdfbox.io.IOUtils;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.html.WebColors;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class StyleUtil{

	
	private static String FONT_CALIBRI = "calibri";
	private static String FONT_TREBUCHET = "trebuchet";
	
	private static Color Insp_FONT_COLOR =  Color.BLACK;
	
	
	private static Color VAL_FONT_COLOR =  WebColors.getRGBColor("#434F5B");
	private static Color FIELD_TITLE_FONT_COLOR =  WebColors.getRGBColor("#828892");
	public static Color TITLE_FONT_COLOR =  WebColors.getRGBColor("#9DA1A6");
	
	
	public static Color mainBodyBgColor =  Color.WHITE;
	public static Color objTitleBgColor =  WebColors.getRGBColor("#F1F4F9");
	public static Color HEADER_BORDER_COLOR =  WebColors.getRGBColor("#E1E5EB");
	public static Color TASK_BORDER_COLOR =  WebColors.getRGBColor("#BABEC3");
	
	private String tabType;
	private String cellType;
	private Font font = createFont(12,FONT_TREBUCHET);
	private Font fontBold = createFont(12,FONT_TREBUCHET,Font.BOLD);
	
	public StyleUtil(){
	}
	
	public StyleUtil(String cellType){
		this.cellType = cellType;
		if(cellType.equalsIgnoreCase("ColName")){
			this.font = getFieldNameFont();
		}else if(cellType.equalsIgnoreCase("ColVal")){
			this.font = getFieldFont(8f);
		}
	}
	public StyleUtil(String tabType, String cellType){
		this.tabType = tabType;
		this.cellType = cellType;
		if(tabType.equalsIgnoreCase("Header")){
			if(cellType.equalsIgnoreCase("ColName")){
				this.font = getFieldNameFont();
			}else if(cellType.equalsIgnoreCase("ColVal")){
				this.font = getFieldFont();
			}else if(cellType.equalsIgnoreCase("Title")){
				this.font = getFieldFont(8f);
			}
		}else{
			if(cellType.equalsIgnoreCase("ColName")){
				this.font = getFieldNameFont();
			}else if(cellType.equalsIgnoreCase("ColVal")){
				this.font = getFieldFont(10f);
			}
		}
			
			
	}
	public StyleUtil(float fontSize, String fontFamily, int fontWeight){
		this.font = createFont(fontSize, fontFamily, fontWeight);
	}
	
	public Font getFieldFont(){
		Font font = createFont(7f, FONT_TREBUCHET, Font.BOLD);
		font.setColor(VAL_FONT_COLOR);
		return font;
	}
	
	public Font getFieldFont(float size){
		Font font = createFont(size,FONT_TREBUCHET);
		font.setColor(VAL_FONT_COLOR);
		return font;
	}
	public Font getFieldFont(float size, int fontWeight){
		Font font = createFont(size,FONT_TREBUCHET, fontWeight);
		font.setColor(VAL_FONT_COLOR);
		return font;
	}	
	public Font getFieldNameFont(){
		Font font = createFont(6f,FONT_TREBUCHET);
		font.setColor(TITLE_FONT_COLOR);
		return font;
	}
	
	public Font getFieldNameFont(float size){
		Font font = createFont(size, FONT_TREBUCHET);
		font.setColor(TITLE_FONT_COLOR);
		return font;
	}
	
	public Font getPdfTitleFont(){
		Font font = createFont(16f, FONT_CALIBRI);
		font.setColor(VAL_FONT_COLOR);
		return font;
	}
	
	public Font getPdfTitleFont(float size){
		Font font = createFont(size, FONT_CALIBRI);
		font.setColor(VAL_FONT_COLOR);
		return font;
	}
	
	private Font createFont(float fontSize,String fontFamily){
		
		Font font = new Font(getBaseFont(fontFamily), fontSize, Font.NORMAL);
		return font;
	}
	
	private Font createFont(float fontSize,String fontFamily, int fontWeight){
		
		Font font = new Font(getBaseFont(fontFamily), fontSize, fontWeight);
		return font;
	}
	
	
	private BaseFont getBaseFont(String fontFamily){
		byte[] bytes;
		BaseFont base = null;
		try {
			if(fontFamily.equalsIgnoreCase(FONT_CALIBRI)){
				bytes = IOUtils.toByteArray(Thread.currentThread().getContextClassLoader()
						.getResourceAsStream("calibril.ttf"));
				base = BaseFont.createFont("calibril.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED,
						false, bytes, null);	
			}else if(fontFamily.equalsIgnoreCase(FONT_TREBUCHET)){
				bytes = IOUtils.toByteArray(Thread.currentThread().getContextClassLoader()
						.getResourceAsStream("fonts/trebuchet_ms.ttf"));
				base = BaseFont.createFont("trebuchet_ms.ttf", BaseFont.WINANSI, 
						BaseFont.EMBEDDED, false, bytes, null);
			}
			
		} catch (IOException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return base;
	}
	
	public PdfPCell createCell(String value, int align, String[] padding ){

		
		/**Padding 
		 * 
		 * 
		 * Pass the padding argument value ended with direction initials
		 * examples.
		 * Left Padding 10: {"10L"}
		 * Top Padding 10 & Right Padding 12: {"10T","12R"}
		 * 
		 * 
		 */ 
		 
		
		PdfPCell cell = new PdfPCell(new Phrase(value, this.font));
		cell.setHorizontalAlignment(align);
		if(padding!=null) {
			for(String pad:padding){
				String val = pad.substring(0, pad.length()-1);
				if(pad.toUpperCase().contains("L"))
					cell.setPaddingLeft(Float.valueOf(val));
				else if(pad.toUpperCase().contains("R"))
					cell.setPaddingRight(Float.valueOf(val));
				else if(pad.toUpperCase().contains("T"))
					cell.setPaddingTop(Float.valueOf(val));
				else if(pad.toUpperCase().contains("B"))
					cell.setPaddingBottom(Float.valueOf(val));
				else
					cell.setPadding(Float.valueOf(pad));
			}
		}
		
		if(tabType!=null && tabType.equalsIgnoreCase("Header") && cellType.equalsIgnoreCase("Title")){
			cell.setBackgroundColor(objTitleBgColor);
			cell.setBorderColor(Color.WHITE);
		}else
			cell.setBorder(Rectangle.NO_BORDER);
		return cell;
	}
	
	public PdfPCell createCell(String value, int align, int colSpan, String[] padding ){

		/**
		 * Padding 
		 * 
		 * 
		 * Pass the padding argument value ended with direction initials
		 * examples.
		 * Left Padding 10: {"10L"}
		 * Top Padding 10 & Right Padding 12: {"10T","12R"}
		 * 
		 * 
		 * 
		 */
		PdfPCell cell = new PdfPCell(new Phrase(value, this.font));
		cell.setHorizontalAlignment(align);
		if(colSpan>0)
			cell.setColspan(colSpan);
		for(String pad:padding){
			String val = pad.substring(0, pad.length()-1);
			if(pad.toUpperCase().contains("L"))
				cell.setPaddingLeft(Float.valueOf(val));
			else if(pad.toUpperCase().contains("R"))
				cell.setPaddingRight(Float.valueOf(val));
			else if(pad.toUpperCase().contains("T"))
				cell.setPaddingTop(Float.valueOf(val));
			else if(pad.toUpperCase().contains("B"))
				cell.setPaddingBottom(Float.valueOf(val));
			else
				cell.setPadding(Float.valueOf(pad));
		}
		
		if(tabType!=null && tabType.equalsIgnoreCase("Header") && cellType.equalsIgnoreCase("Title")){
			cell.setBackgroundColor(objTitleBgColor);
			cell.setBorderColor(Color.WHITE);
		}else
			cell.setBorder(Rectangle.NO_BORDER);
		return cell;
	}
	
	public PdfPCell createCell(String value, int align){
		PdfPCell cell = new PdfPCell(new Phrase(value, this.font));
		cell.setHorizontalAlignment(align);
		cell.setBorder(Rectangle.NO_BORDER);
		return cell;
	}
	
	public PdfPCell createCell( Entry<String, String> entry, int align, int colSpan){
		
		PdfPCell labelCell = new PdfPCell(new Phrase(entry.getKey(), getFieldNameFont()));
		labelCell.setPaddingLeft(14f);
		labelCell.setPaddingTop(8f);
		labelCell.setHorizontalAlignment(align);
		labelCell.setBorder(Rectangle.NO_BORDER);
		PdfPCell valueCell = new PdfPCell(new Phrase(entry.getValue(), getFieldFont(10f)));
		valueCell.setPaddingLeft(14f);
		valueCell.setHorizontalAlignment(align);
		valueCell.setBorder(Rectangle.NO_BORDER);
		PdfPTable dataInnerTab = new PdfPTable(1);
		dataInnerTab.addCell(labelCell);
		dataInnerTab.addCell(valueCell);
		
		
		PdfPCell cell = new PdfPCell(dataInnerTab);
		cell.setHorizontalAlignment(align);
		if(colSpan>0)
			cell.setColspan(colSpan);
		/*for(String pad:padding){
			String val = pad.substring(0, pad.length()-1);
			if(pad.toUpperCase().contains("L"))
				cell.setPaddingLeft(Float.valueOf(val));
			else if(pad.toUpperCase().contains("R"))
				cell.setPaddingRight(Float.valueOf(val));
			else if(pad.toUpperCase().contains("T"))
				cell.setPaddingTop(Float.valueOf(val));
			else if(pad.toUpperCase().contains("B"))
				cell.setPaddingBottom(Float.valueOf(val));
			else
				cell.setPadding(Float.valueOf(pad));
		}*/
		
		if(tabType!=null && tabType.equalsIgnoreCase("Header") && cellType.equalsIgnoreCase("Title")){
			cell.setBackgroundColor(objTitleBgColor);
			cell.setBorderColor(Color.WHITE);
		}else
			cell.setBorder(Rectangle.NO_BORDER);
		return cell;
	}
	
	public Font getFont() {
		return font;
	}
	
	public Font getFontBold() {
		return fontBold;
	}
	
}
