package com.tip.util;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatatypeCommonUtility {

    public static void checkNull(int order, CallableStatement callableStatement, Double value)
            throws SQLException {
        if (null == value) {
            callableStatement.setNull(order, java.sql.Types.DOUBLE);
        } else {
            callableStatement.setDouble(order, value);
        }
    }

    public static void checkNull(int order, CallableStatement callableStatement, Integer value)
            throws SQLException {
        if (null == value) {
            callableStatement.setNull(order, java.sql.Types.INTEGER);
        } else {
            callableStatement.setInt(order, value);
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

}
