package com.tip.workorder.util;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtilityTest {

    DateUtility dateUtility;

    @Before
    public void beforesetup() {
        dateUtility = new DateUtility();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void xmlGreogrianToSqlDate() {
        XMLGregorianCalendar xmlDate = null;
        dateUtility.xmlGreogrianToSqlDate(xmlDate);
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(new Date());
        XMLGregorianCalendar xmlDate1;
        try {
            xmlDate1 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
            dateUtility.xmlGreogrianToSqlDate(xmlDate1);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void stringToSqlDate() {
        String xmlDate = null;
        dateUtility.stringToSqlDate(xmlDate);
        xmlDate = "";
        dateUtility.stringToSqlDate(xmlDate);
        xmlDate = "01-02-2017";
        dateUtility.stringToSqlDate(xmlDate);
        xmlDate = "01022017";
        try {
            dateUtility.stringToSqlDate(xmlDate);
        } catch (Exception e) {
        }
    }

    @Test
    public void sqlDateToXmlGregorian() {
        java.sql.Timestamp sqlDt = null;
        dateUtility.sqlDateToXmlGregorian(sqlDt);
        sqlDt = new Timestamp(46556456);
        dateUtility.sqlDateToXmlGregorian(sqlDt);
    }

    @Test
    public void sqlDateToXmlGregorian1() {
        java.sql.Date sqlDt = null;
        dateUtility.sqlDateToXmlGregorian(sqlDt);
        sqlDt = new java.sql.Date(112424);
        dateUtility.sqlDateToXmlGregorian(sqlDt);
    }
}
