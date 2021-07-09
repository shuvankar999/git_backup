package com.tip.assetpdf.model;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import javax.servlet.http.HttpSession;


import com.lowagie.text.Chunk;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;



public class CommonUtil {
	
/*	public static void main(String[] args) {
		convertStringDateTimeToDate("2009-08-05 00:00:00.0");
	}*/
	public static String uri;
	
	public static Phrase addChunkToPhrase(String branchHeader,String customerHeader){
		FontFactory.register(uri+"/images/calibri.ttf");
        FontFactory.register(uri+"/images/calibrib.ttf");
        
		
		Font colHeaderFont = FontFactory.getFont("Calibri Bold", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 5);
//		Font colFont = FontFactory.getFont("Calibri", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 4);
		if(branchHeader.equals(customerHeader)){
			Phrase ph1 = new Phrase(branchHeader,colHeaderFont);
			return ph1;
		}else{
		Phrase ph1 = new Phrase();
		Chunk chunk1 = new Chunk(branchHeader,colHeaderFont);
		ph1.add(chunk1);
		ph1.add(Chunk.NEWLINE);
		Chunk chunk2 = new Chunk(customerHeader,colHeaderFont);
		ph1.add(chunk2);
		return ph1;
		}
	}
	public static String dateToStringFormatMMMddYYYY(Date value) {
		String datevalue = "";
		SimpleDateFormat dtFormat = new SimpleDateFormat("MMM dd yyyy");
		if (value != null) {
			datevalue = dtFormat.format(value);			
		}
		return datevalue;
	}
	public static Phrase addChunkToPhraseMainHeader(String branchHeader,String customerHeader){
		FontFactory.register(uri+"/images/calibri.ttf");
        FontFactory.register(uri+"/images/calibrib.ttf");
        
		
		Font colHeaderFont = FontFactory.getFont("Calibri Bold", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 5);
//		Font colFont = FontFactory.getFont("Calibri", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 4);
		if(branchHeader.equals(customerHeader)){
			Phrase ph1 = new Phrase(branchHeader,colHeaderFont);
			return ph1;
		}else{
		
		Phrase ph1 = new Phrase();
		Chunk chunk1 = new Chunk(branchHeader,colHeaderFont);
		ph1.add(chunk1);
		ph1.add(Chunk.NEWLINE);
		Chunk chunk2 = new Chunk(customerHeader,colHeaderFont);
		ph1.add(chunk2);
		return ph1;
		}
	}
	
	
	public static boolean checkNull(String val) {
		String value = val;
		boolean flag = false;

		if (value == null || "".equals(value)) {
			flag = false;
		} 
		else{
			flag = true;
		}
		return flag;
	}

	public static String getToDayDateDDMONYYYY() {
		Date date = new Date();
		String datevalue = "";
		SimpleDateFormat dtFormat = new SimpleDateFormat("dd-MMM-yy");
		datevalue = dtFormat.format(date);
		return datevalue;
	}
	
	
	public static String checkNullObject(Object val) {
		//Object value = val;
	//	boolean flag = false;
		
		if(val != null ){
			if("".equals(val.toString().trim())){
				return "";
			}
			return val.toString();
		}else {
			return "";
		}

		/*if (value == null || "".equals(value)) {
			return "";
		} else {
			return val.toString();
		}*/
	}
	
	
	
	
	public static String dateToStringFormat(Date value) {
		String datevalue = "";
		SimpleDateFormat dtFormat = new SimpleDateFormat("MM/dd/yyyy");
		if (value != null) {
			datevalue = dtFormat.format(value);
			
		}
		return datevalue;
	}
	public static String dateToString(Date value) {
		String datevalue = "";
		SimpleDateFormat dtFormat = new SimpleDateFormat("dd/MM/yyyy");
		if (value != null) {
			datevalue = dtFormat.format(value);
			
		}
		return datevalue;
	}
	
	
	public static Date stringToDate(String strdate) {
		Date obj = null;
		Locale locale = new Locale("English");
		// TimeZone gmtZone = TimeZone.getTimeZone(FolderConstants.GMT);
		SimpleDateFormat dtFormat = new SimpleDateFormat("MM/dd/yyyy", locale);
		try {
			// dtFormat.setTimeZone(gmtZone);
			if (strdate != null && !strdate.equals("")) {
				//LOG.info(" \n\n <<<<<  strdate   "+strdate );
				
				obj = dtFormat.parse(strdate);
				
				//LOG.info(" \n\n <<<<<  obj   "+obj );
				
			}
		} catch (ParseException e) {
			//LOG.info(e.getMessage());
		}
		return obj;
	}
	
	public static Date convertStringToDate(String strdate) {
		Date obj = null;
		
		SimpleDateFormat dtFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			
			if (strdate != null && !strdate.equals("")) {
			
				
				obj = dtFormat.parse(strdate);
				
				
				
			}
		} catch (ParseException e) {
			
		}
		return obj;
	}
	
//	public static HttpSession getSession(boolean check){
//		 HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(check);
//		 return session;
//	}
	
	
	public static String convertStringDateTimeToString(String value){
		 SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String dateToString = null;
		 
		    try {
				Date myDate = new Date(myFormat.parse(value).getTime());
				//LOG.info("myDate "+myDate);
				dateToString = dateToStringFormat(myDate);
				//LOG.info("correct format dateToString ---->"+dateToString);
				////LOG.info(">>>>  "+stringToDate(dateToStringFormat(myDate)));
			} catch (ParseException e) {
				
				System.out.println(e.getMessage());
			} 
		return dateToString;
	}
	
	
	public static Date convertStringDateTimeToDate(String value){
		 SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 String dateToString = null;
		 Date date = null;
		 
		    try {
				Date myDate = new Date(myFormat.parse(value).getTime());
				//LOG.info("myDate "+myDate);
				dateToString = dateToStringFormat(myDate);
				//LOG.info("correct format dateToString ---->"+dateToString);
				myFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
				//LOG.info(">>>>  "+stringToDate(dateToStringFormat(myDate)));
				date = stringToDate(dateToStringFormat(myDate));
				//LOG.info("????  date  "+date);
			} catch (ParseException e) {
				
				System.out.println(e.getMessage());
			} 
		return date;
	}
	
	
	public static Double roundToDigits(double temp1){
		BigDecimal roundfinalPrice = new BigDecimal(temp1).setScale(2,BigDecimal.ROUND_HALF_UP);
		Double doublePrice= new Double(roundfinalPrice.doubleValue());
		//LOG.info("doublePrice----->"+doublePrice);
		return doublePrice;
	}
	
	
	
	public static Float roundToDigitsFloat(float temp1){
		BigDecimal roundfinalPrice = new BigDecimal(temp1).setScale(2,BigDecimal.ROUND_HALF_UP);
		Float floatPrice= new Float(roundfinalPrice.floatValue());
		//LOG.info("doublePrice----->"+floatPrice);
		return floatPrice;
	}
	
//	public static String getRequestParameter(String param){
//		String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(param);
//	return value;
//	}
//	
	


	public String[] frameArrayString(List<UnitNrData> a)
	{
		int len = a.size();
		int remin = len%1000;
		int quotent =len/1000;
		
		System.out.println(remin);
		System.out.println(quotent);
		StringBuffer sb = new StringBuffer();
		String[] abc = new String[50];
		
		for(int i=0,j=0;i<len;i++){
			UnitNrData unitNrData =  a.get(i);
			if(i==0){
				sb.append(unitNrData.getUnit_Nr()+",");
				if(len == 1){				
					abc[0] = sb.toString();
				}
				continue;
			} else {
				if( i % 1000 !=0 ){
					sb.append(unitNrData.getUnit_Nr()+",");
				}else if(i%1000 == 0){
					abc[j]= sb.toString();
					j++;
					sb = new StringBuffer();
					sb.append(unitNrData.getUnit_Nr()+",");
				}
				
				if(len<=1000 && len == i+1){
					abc[j]= sb.toString();
				}
				
				if(remin>0 && len == i+1){
					abc[j]= sb.toString();
				}
				
				if((len%1000) == 0 && len == i+1 ){
					abc[j]= sb.toString();
				}
			}
		}
	/*	for(int k=0;k<50;k++){
			System.out.println(	">>>"+k+">>>"+abc[k]);
		}*/
		return abc;
	}
	
	
}
