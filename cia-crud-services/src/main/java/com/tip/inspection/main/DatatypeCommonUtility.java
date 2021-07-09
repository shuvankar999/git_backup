package com.tip.inspection.main;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatatypeCommonUtility {

	
	private DatatypeCommonUtility(){	
	
		
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
	public static String dateToStringFormatMMDDYYYY(Date value) {
		String datevalue = "";
		SimpleDateFormat dtFormat = new SimpleDateFormat("MM/dd/yyyy");
		if (value != null || !"".equals(value)) {
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
	
	public static void checkNull(int order, CallableStatement callableStatement, Double value)
			throws SQLException {
		if(null == value){
			callableStatement.setNull(order, java.sql.Types.DOUBLE); 
		}else{
			callableStatement.setDouble(order, value);
		}
	}
	public static void checkNull(int order, CallableStatement callableStatement, Integer value)
			throws SQLException {
		if(null == value){
			callableStatement.setNull(order, java.sql.Types.INTEGER); 
		}else{
			callableStatement.setInt(order, value);
		}
	}
	public static void checkNull(int order, CallableStatement callableStatement, Float value)
			throws SQLException {
		if(null == value){
			callableStatement.setNull(order, java.sql.Types.FLOAT); 
		}else{
			callableStatement.setFloat(order, value);
		}
	}
	
	public static void checkNull(int order, PreparedStatement statement, Double value)
			throws SQLException {
		if(null == value){
			statement.setNull(order, java.sql.Types.DOUBLE); 
		}else{
			statement.setDouble(order, value);
		}
	}
	public static void checkNull(int order, PreparedStatement statement, Integer value)
			throws SQLException {
		if(null == value){
			statement.setNull(order, java.sql.Types.INTEGER); 
		}else{
			statement.setInt(order, value);
		}
	}
	public static void checkNull(int order, PreparedStatement statement, Float value)
			throws SQLException {
		if(null == value){
			statement.setNull(order, java.sql.Types.FLOAT); 
		}else{
			statement.setFloat(order, value);
		}
	}
	
	public static void checkNull(int order, CallableStatement callableStatement, BigDecimal value)
			throws SQLException {
		if(null == value){
			callableStatement.setNull(order, java.sql.Types.INTEGER); 
		}else{
			callableStatement.setBigDecimal(order, value);
		}
	}
}
