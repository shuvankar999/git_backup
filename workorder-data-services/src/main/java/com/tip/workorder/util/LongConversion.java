package com.tip.workorder.util;

public class LongConversion {
	private LongConversion(){}
    public static long getLong(String val) {
        if (null == val || "".equalsIgnoreCase(val)) {
            return java.sql.Types.NULL;
        }
        return Long.parseLong(val);
    }

    public static long getIntegerToLong(Integer val) {
        if (null == val) {
            return java.sql.Types.NULL;
        }
        return Long.parseLong(val.toString());
    }
}
