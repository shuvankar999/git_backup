package com.tip.workorder.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConversion {

    static final Logger logger = LoggerFactory.getLogger(DateConversion.class);
    
    private DateConversion(){}
    public static java.sql.Date getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }

    public static java.sql.Date getsqlDate(Date dateVal) {
        if (null != dateVal) {
            return new java.sql.Date(dateVal.getDate());
        }
        return null;
    }

    //String to Date conversion for null

    public static java.sql.Date getsqlDate(String dateVal) {
        DateFormat formatter;
        formatter = new SimpleDateFormat("MMM DD yyyy");

        try {

            if (null == dateVal || ("").equalsIgnoreCase(dateVal)) {
                return null;
            } else {

                return new java.sql.Date(formatter.parse(dateVal).getDate());
            }
        } catch (ParseException e) {
            logger.error("An error occurred during string to sqlDate conversion: " + e);

        }
        return null;
    }

} 
