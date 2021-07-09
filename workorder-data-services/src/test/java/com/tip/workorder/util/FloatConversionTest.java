package com.tip.workorder.util;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

public class FloatConversionTest {

    FloatConversion floatConversion;

    @Before
    public void beforesetup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getFloat() {
        String val = null;
        floatConversion.getFloat(val);
        val = "";
        floatConversion.getFloat(val);
        val = "2";
        floatConversion.getFloat(val);
        Float val1 = null;
        floatConversion.getFloat(val1);
        val1 = 1f;
        floatConversion.getFloat(val1);
    }
}
