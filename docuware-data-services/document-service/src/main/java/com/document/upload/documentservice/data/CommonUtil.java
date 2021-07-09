package com.document.upload.documentservice.data;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;






public class CommonUtil {
	

	public static String uri;
//	public static void main(String args[]){
//		//System.out.println(convertToDDMMYYYY("2009-05-12 08:27:12.0"));
//		System.out.println("jgfghk : "+convertStringToFloat("1.00000"));
//	}
	
	
	public static long convertStringToLong(String value) {
		long returnValue = 0;
		try{	
			if(checkNull(value))
			{
				returnValue = Long.parseLong(value);
			}
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		System.out.println("returnValue"+returnValue);
		return returnValue;
	}
	
	public static String convertToDDMMYYYY(String date){
		String newDate1 = null;
		
		Date obj = null;
		if(!checkNull(date.trim())){
			return "NULL";
		}
		try {
			
			if(date.contains("-")){
				return date.substring(8,10)+"/"+date.substring(5,7)+"/"+date.substring(0,4)+" "+date.substring(11,13)+":"+date.substring(14,16)+":"+date.substring(17,19);
			} else if(date.contains("/")){
				return date;
			}
			obj = new Date(date);
		//	System.out.println("obj : "+obj);
			Locale locale = new Locale("English");
			SimpleDateFormat dtFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", locale);
			if (checkNull(date)) {
			//	System.out.println(" \n\n <<<<<  strdate   "+date );
				
				newDate1 = dtFormat.format(obj);
				
			//	System.out.println(" \n\n <<<<<  strdate   "+obj );
				
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return "NULL";
		}
	//	System.out.println("total Date : "+obj.toString());
		return obj.toString().substring(4,10)+ " "+obj.toString().substring(24,28)+" "+obj.toString().substring(11,19);
	//	return "";
	}

	public static String convertToMMMDDYYYY(String date){
		String newDate1 = null;
		Date obj = new Date(date);
		
		Locale locale = new Locale("English");
	
		if(! checkNull(date)){
			return "";
		}
		try {
	
			SimpleDateFormat dtFormat = new SimpleDateFormat("MMM dd yyyy", locale);
			if (checkNull(date)) {
				System.out.println(" \n\n <<<<<  strdate   "+date );
				
				newDate1 = dtFormat.format(obj);
				
				System.out.println(" \n\n <<<<<  strdate   "+obj );
				
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return "";
		}
		System.out.println("total Date : "+obj.toString());
		return obj.toString().substring(4,10)+ " "+obj.toString().substring(24,28)+" "+obj.toString().substring(11,19);
	//	return "";
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

	static SimpleDateFormat myFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	
	public static String dateToStringFormatMMDDYYYY(Date value) {
		String datevalue = "";
		SimpleDateFormat dtFormat = new SimpleDateFormat("MM/dd/yyyy");
		if (value != null || !"".equals(value)) {
			datevalue = dtFormat.format(value);
		}
		return datevalue;
	}
	
	public static String convertStringDateTimeToStringMMDDYYYY(String value) {

		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateToString = "";
		try {
			if (!("".equals(value)) || value != null) {
				Date myDate = new Date(myFormat.parse(value).getTime());
				dateToString = dateToStringFormatMMDDYYYY(myDate);
			}
		} catch (ParseException e) {

		}
		return dateToString;
	}
	
	public static String dateToStringFormat(String dateValue1) throws Exception {

		if (dateValue1 != null) {
			dateValue1 = dateValue1.trim();
		}

		if (dateValue1 == null || "".equals(dateValue1)) {
			return "";
		}

		Date value = new Date(myFormat.parse(dateValue1).getTime());
		String datevalue = "";
		SimpleDateFormat dtFormat = new SimpleDateFormat("dd/MM/yyyy");
		if (value != null) {
			datevalue = dtFormat.format(value);
		}
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
	}
		
		public static int convertStringToInt(String value) {
			int returnValue = 0;
			try{	
				if(checkNull(value))
				{
					returnValue = Integer.parseInt(value);
				}
			}catch(NumberFormatException e){
				
			}
			return returnValue;
		}
		public static float convertStringToFloat(String value) {
			float returnValue = 0.00f;
			try{
				returnValue = Float.parseFloat(value);
			}catch (Exception e) {
				return returnValue ;
			}
			return returnValue ;
		}
	


	
	
}
