package com.tip.workorder.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DateUtility {


    static final Logger LOGGER = LoggerFactory.getLogger(DateUtility.class.getName());

    public static java.sql.Date xmlGreogrianToSqlDate(XMLGregorianCalendar xmlDate) {

        if (null == xmlDate) {
            return null;
        }
        java.util.Date dt = xmlDate.toGregorianCalendar().getTime();

        return new java.sql.Date(dt.getTime());
    }


    public static java.sql.Date stringToSqlDate(String xmlDate) {

        if (null == xmlDate || xmlDate.isEmpty()) {
            return null;
        }


        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = null;
        java.sql.Date sqlDate = null;
        try {

            date = sdf.parse(xmlDate);
            sqlDate = new java.sql.Date(date.getTime());

        } catch (ParseException e) {
            LOGGER.error("Please check the dateFormat. Required format:	" + sdf.toPattern() + "\n current value:	" + xmlDate);
            LOGGER.error("An error occurred while parsing the date :" + e);
        }
        return sqlDate;
    }

    public static XMLGregorianCalendar sqlDateToXmlGregorian(java.sql.Timestamp sqlDt) {


        XMLGregorianCalendar cal = null;

        if (sqlDt != null) {

            Timestamp tstamp = sqlDt;
            tstamp.setNanos(123456789);

            LocalDateTime ldt = tstamp.toLocalDateTime();

            try {
                cal = DatatypeFactory.newInstance().newXMLGregorianCalendar();
                cal.setYear(ldt.getYear());
                cal.setMonth(ldt.getMonthValue());
                cal.setDay(ldt.getDayOfMonth());
                cal.setHour(ldt.getHour());
                cal.setMinute(ldt.getMinute());
                cal.setSecond(ldt.getSecond());
                cal.setFractionalSecond(new BigDecimal("0." + ldt.getNano()));
            } catch (DatatypeConfigurationException e) {

                LOGGER.error("An error occurred DateUtility:" + e);
            }

        }

        return cal;
    }


    public static XMLGregorianCalendar sqlDateToXmlGregorian(java.sql.Date sqlDt) {

        // sqlDt to java.util.Date
        java.util.Date javaDate;
        XMLGregorianCalendar xmlDate = null;

        if (sqlDt != null) {
            javaDate = new java.util.Date(sqlDt.getTime());

            // Create XML calendar
            GregorianCalendar c = new GregorianCalendar();
            c.setTime(javaDate);
            try {
                xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
            } catch (DatatypeConfigurationException e) {

                LOGGER.error("Exception occured " + e);
            }
        }


        return xmlDate;
    }


    public static String dateToString(java.util.Date date) {

        String dateString = null;
        if (null != date) {
            SimpleDateFormat mdyFormat = new SimpleDateFormat("dd-MMM-yyyy");

            // Format the date to Strings
            dateString = mdyFormat.format(date);
        }
        return dateString;
    }
    
    public static String changeFormatDate(String date) {

        if (null == date || date.isEmpty() || ("null").equalsIgnoreCase(date)) {
            return null;
        }
        
        SimpleDateFormat fromUser = new SimpleDateFormat("dd/mm/yyyy");
        SimpleDateFormat myFormat = new SimpleDateFormat("mm/dd/yyyy");
        String reformattedStr = null;
        try {

            reformattedStr = myFormat.format(fromUser.parse(date));
        } catch (ParseException e) {
        	 LOGGER.error("An error occurred DateUtility:" + e);
        }
        
        return reformattedStr;
    }
    
	
	public java.sql.Time stringToSqlTimestamp(String stringDate){
	
		java.sql.Time timeValue=null;
		
		if(null==stringDate || stringDate.isEmpty() || ("null").equalsIgnoreCase(stringDate)){
			return timeValue;
		}else{
			LOGGER.info("StringDate :"+stringDate);
			String stringDate2 = stringDate.concat(":00");
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			
			try {
				timeValue = new java.sql.Time(sdf.parse(stringDate2).getTime());
			} catch (ParseException e) {
				LOGGER.error("An error occurred during parsing the time value");
			}
			
			LOGGER.info("stringValue>>>>"+stringDate2);
			LOGGER.info("timeValue>>>"+timeValue);
			
			return timeValue;
		}
	}
	
	
}
