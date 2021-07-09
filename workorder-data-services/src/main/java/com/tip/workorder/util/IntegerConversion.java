package com.tip.workorder.util;

public class IntegerConversion {
	private IntegerConversion(){}
    public static Integer getInt(String val) {
        if (null == val || "".equalsIgnoreCase(val)) {
            return java.sql.Types.NULL;
        }
        return Integer.parseInt(val);
    }


    public static Integer getInt(Integer val) {
        if (null == val) {
            return java.sql.Types.NULL;
        }
        return val;
    }
}
