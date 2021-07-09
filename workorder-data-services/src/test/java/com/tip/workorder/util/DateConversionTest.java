package com.tip.workorder.util;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.Date;

public class DateConversionTest {

    DateConversion dateConversion;

    @Before
    public void beforesetup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getsqlDate() {
        Date dateVal = null;
        dateConversion.getsqlDate(dateVal);
        dateVal = new Date();
        dateConversion.getsqlDate(dateVal);
    }

    @Test
    public void getsqlDate1() {
        String dateVal = null;
        dateConversion.getsqlDate(dateVal);
        dateVal = "";
        dateConversion.getsqlDate(dateVal);
        dateVal = "Jun 02 2017";
        dateConversion.getsqlDate(dateVal);
        dateVal = "01022017";
        dateConversion.getsqlDate(dateVal);
    }
} 
