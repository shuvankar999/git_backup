/**
 * 
 */
package com.tip.elastic.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Shuvankar Paul
 * Created on Oct 25, 2017
 * 
 */
public class CommonUtil {

	
	private CommonUtil(){}
	
	public static String getQuestionMarkValueByCount(int i){		
		int p = i;		
		StringBuilder questionValue = new StringBuilder(" (");		
		while(p>0){
			questionValue = questionValue.append("?");
			if(p!=1){
				questionValue = questionValue.append(",");
			}
			p--;
		}		
		questionValue.append(") ");		
		return questionValue.toString();
	}
	
	public static Integer getStringToInteger(String s){
		Integer i = null;
		if(s==null || s.isEmpty())
			i = null;
		else
			i = Integer.valueOf(s);
		
		return i;
	}
	
	public static Timestamp getSysDateSql(){
		java.util.Date utilDate = new java.util.Date();
		return new Timestamp(utilDate.getTime());
	}

}
