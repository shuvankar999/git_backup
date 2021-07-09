package com.tip.asset.util;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class DatatypeCommonUtility {

	private DatatypeCommonUtility(){
		
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
}
