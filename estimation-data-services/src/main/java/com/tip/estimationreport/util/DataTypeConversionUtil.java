package com.tip.estimationreport.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

@Component
public class DataTypeConversionUtil {

	public Integer castValue(String str) {
		if(str.isEmpty() || str==null)
			return 0;
		else
			return Integer.valueOf(str);
	}
	
	public String castValue(Integer i) {
		
		if(i==null)
			return "";
		else
			return String.valueOf(i);
	}
	
	public String castValue(Float f) {
		
		if(f==null)
			return "";
		else
			return String.valueOf(f);
	}
	
	public String castValue(Double d) {
		
		if(d==null)
			return "";
		else
			return String.format("%.2f",d);
	}
	
	public String castValue(BigDecimal bd) {
		
		if(bd==null)
			return "";
		else {
			return String.valueOf(bd.setScale(2, RoundingMode.FLOOR));
		}
			
	}	
}
