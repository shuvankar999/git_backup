package com.tip.workorder.util;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

public class IntegerConversionTest {

    IntegerConversion integerConversion;

    @Before
    public void beforesetup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getInt() {
        String val = null;
        integerConversion.getInt(val);
        val = "";
        integerConversion.getInt(val);
        val = "2";
        integerConversion.getInt(val);
        Integer val1 = null;
        integerConversion.getInt(val1);
        val1 = 1;
        integerConversion.getInt(val1);
    }
}
