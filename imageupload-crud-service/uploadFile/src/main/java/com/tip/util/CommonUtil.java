package com.tip.util;


import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class CommonUtil {
    private static final Logger LOG = Logger.getLogger(CommonUtil.class);

    public static void main(String args[]) {
        //System.out.println(convertToDDMMYYYY("2009-05-12 08:27:12.0"));
        System.out.println("jgfghk : " + convertStringToFloat("1.00000"));
        CommonUtil c = new CommonUtil();
        c.convertStringToLong("55003840340");
    }

    public static String convertToDDMMYYYY(String date) {
        String newDate1 = null;

        Date obj = null;
        if (!checkNull(date.trim())) {
            return "NULL";
        }
        try {

            if (date.contains("-")) {
                return date.substring(8, 10) + "/" + date.substring(5, 7) + "/" + date.substring(0, 4) + " " + date.substring(11, 13) + ":" + date.substring(14, 16) + ":" + date.substring(17, 19);
            } else if (date.contains("/")) {
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
            LOG.error("Error message.."+e.getMessage());
            return "NULL";
        }
        //	System.out.println("total Date : "+obj.toString());
        return obj.toString().substring(4, 10) + " " + obj.toString().substring(24, 28) + " " + obj.toString().substring(11, 19);
        //	return "";
    }

    public static String convertToMMMDDYYYY(String date) {
        String newDate1;
        Date obj = new Date(date);

        Locale locale = new Locale("English");

        if (!checkNull(date)) {
            return "";
        }
        try {

            SimpleDateFormat dtFormat = new SimpleDateFormat("MMM dd yyyy", locale);
            if (checkNull(date)) {
                System.out.println(" \n\n <<<<<  strdate   " + date);

                newDate1 = dtFormat.format(obj);

                System.out.println(" \n\n <<<<<  strdate   " + obj);

            }
        } catch (IllegalArgumentException e) {
            LOG.error("Error message.."+e.getMessage());
            return "";
        }
        System.out.println("total Date : " + obj.toString());
        return obj.toString().substring(4, 10) + " " + obj.toString().substring(24, 28) + " " + obj.toString().substring(11, 19);
        //	return "";
    }


    public static boolean checkNull(String val) {
        String value = val;
        boolean flag;

        if (value == null || "".equals(value)) {
            flag = false;
        } else {
            flag = true;
        }
        return flag;
    }


    public static String checkNullObject(Object val) {
        //Object value = val;
        //	boolean flag = false;

        if (val != null) {
            if ("".equals(val.toString().trim())) {
                return "";
            }
            return val.toString();
        } else {
            return "";
        }
    }

    public static int convertStringToInt(String value) {
        int returnValue = 0;
        try {
            if (checkNull(value)) {
                returnValue = Integer.parseInt(value);
            }
        } catch (NumberFormatException e) {

        }
        return returnValue;
    }

    public static BigDecimal convertStringToBigDecimal(String value) {
        BigDecimal returnValue = new BigDecimal("0");
        try {
            if (checkNull(value)) {
                returnValue = new BigDecimal(value);
            }
        } catch (NumberFormatException e) {

        }
        return returnValue;
    }


    public static long convertStringToLong(String value) {
        long returnValue = 0;
        try {
            if (checkNull(value)) {
                returnValue = Long.parseLong(value);
            }
        } catch (NumberFormatException e) {
            LOG.error("Error message.."+e.getMessage());
        }
        System.out.println("returnValue" + returnValue);
        return returnValue;
    }

    public static BigInteger convertStringToBigInt(String value) {
        BigInteger returnValue = null;
        try {
            if (checkNull(value)) {
                returnValue = new BigInteger(value);
            }
        } catch (NumberFormatException e) {
            LOG.error("Error message.."+e.getMessage());
        }
        System.out.println("returnValue" + returnValue);
        return returnValue;
    }


    public static float convertStringToFloat(String value) {
        float returnValue = 0.00f;
        try {
            returnValue = Float.parseFloat(value);
        } catch (Exception e) {
            return returnValue;
        }
        return returnValue;
    }


    public static boolean isNumber(String val) {
        //Object value = val;
        //	boolean flag = false;
        boolean flag = true;
        try {
            if (val != null) {
                if (!"".equals(val.trim())) {
                    Integer.parseInt(val);

                }

            }
        } catch (NumberFormatException nfe) {
            flag = false;
        }
        return flag;
    }

    public static String getImageLocation(SessionImpl sessionImpl, Session session, String appCode, String inspType) {
        String imageLocation = null;
        try {
            sessionImpl.connection().setAutoCommit(true);

            Query validateSessionQuery = session.getNamedQuery("imageLocation");
            validateSessionQuery.setParameter("appCd", CommonUtil
                    .checkNullObject(appCode));
            validateSessionQuery.setParameter("inspType", CommonUtil
                    .checkNullObject(inspType));
            imageLocation = (String) validateSessionQuery.uniqueResult();
            sessionImpl.connection().setAutoCommit(false);


        } catch (SQLException e) {
            LOG.error("Error message.."+e.getMessage());
        }
        return imageLocation;
    }


}
