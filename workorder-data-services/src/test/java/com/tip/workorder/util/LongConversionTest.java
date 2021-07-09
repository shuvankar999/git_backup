package com.tip.workorder.util;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

public class LongConversionTest {

    LongConversion longConversion;

    @Before
    public void beforesetup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getLong() {
        String val = null;
        longConversion.getLong(val);
        val = "";
        longConversion.getLong(val);
        val = "2";
        longConversion.getLong(val);
        Integer val1 = null;
        longConversion.getIntegerToLong(val1);
        val1 = 1;
        longConversion.getIntegerToLong(val1);
    }
}
