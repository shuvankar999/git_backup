/**
 * 
 */
package com.tip.workorder.util;

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

}
