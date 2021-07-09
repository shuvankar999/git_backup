package com.tip.workorder.util;

public class FloatConversion {
	private FloatConversion(){}
    public static float getFloat(String val) {
        if (null == val || "".equalsIgnoreCase(val)) {
            return java.sql.Types.NULL;
        }
        return Float.parseFloat(val);
    }

    public static float getFloat(Float val) {
        if (null == val) {
            return java.sql.Types.NULL;
        }
        return val;
    }
}
