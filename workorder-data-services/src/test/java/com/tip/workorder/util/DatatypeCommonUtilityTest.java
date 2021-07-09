package com.tip.workorder.util;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.CallableStatement;

public class DatatypeCommonUtilityTest {

    DatatypeCommonUtility datatypeCommonUtility;

    @Mock
    CallableStatement mockCallableStatement;

    @Before
    public void beforesetup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void checkNull() {
        int order = 1;
        try {
            Double value = 2.3;
            datatypeCommonUtility.checkNull(order, mockCallableStatement, value);
            datatypeCommonUtility.checkNull(order, mockCallableStatement, 2);
        } catch (Exception e) {
        }
    }
}
