package com.tip.report.util;

import static org.mockito.Mockito.mock;

import java.sql.CallableStatement;
import java.sql.SQLException;

import org.junit.Test;

public class DatatypeCommonUtilityTest {
    CallableStatement callableStatement;

    @Test
    public void testCheckNull() throws SQLException {
        callableStatement = mock(CallableStatement.class);
        Double d = null;
        DatatypeCommonUtility.checkNull(2, callableStatement, d);
        DatatypeCommonUtility.checkNull(2, callableStatement, 0.5);
        Integer value = null;
        DatatypeCommonUtility.checkNull(2, callableStatement, value);
        DatatypeCommonUtility.checkNull(2, callableStatement, 0);
    }
}