package com.tip.fetchtechnician.repository.impl;

import com.tip.fetchtechnician.model.TechnicianDetailsRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TechnicianDetailsRepositoryImplTest {

    TechnicianDetailsRepositoryImpl technicianDetailsRepositoryImpl;
    @Mock
    DataSource dataSource;
    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private Connection mockConnection;
    @Mock
    private CallableStatement mockCallableStatement;

    @Before
    public void beforesetup() {
        technicianDetailsRepositoryImpl = new TechnicianDetailsRepositoryImpl();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(technicianDetailsRepositoryImpl, "jdbcTemplate", jdbcTemplate);
    }

    @Test
    public void testSetDataSource() {
        DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
        technicianDetailsRepositoryImpl.setDataSource(ds);
    }

    @Test
    public void getTechnicianDetails() {
        try {
            TechnicianDetailsRequest technicianDetailsRequest = new TechnicianDetailsRequest();
            technicianDetailsRequest.setBranchId("123");
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("#result-set-1", 1);
            resultMap.put("Error_Cd", 2);
            Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
            Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenReturn(mockConnection);
            Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class))).thenReturn(resultMap);
            technicianDetailsRepositoryImpl.getTechnicianDetails(technicianDetailsRequest);
            Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class))).thenThrow(new IncorrectResultSizeDataAccessException(1));
            try {
                technicianDetailsRepositoryImpl.getTechnicianDetails(technicianDetailsRequest);
            } catch (Exception e) {
            }
            Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenThrow(new SQLException());
            technicianDetailsRepositoryImpl.getTechnicianDetails(technicianDetailsRequest);
        } catch (Exception e) {
        }
    }

    @Test
    public void createCallableStatement() {
        Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
        TechnicianDetailsRequest technicianDetailsRequest = new TechnicianDetailsRequest();
        technicianDetailsRequest.setBranchId("5");
        technicianDetailsRepositoryImpl.setTechnicianDetailsRequest(technicianDetailsRequest);
        try {
            Mockito.when(mockConnection.prepareCall(null)).thenReturn(mockCallableStatement);
            technicianDetailsRepositoryImpl.createCallableStatement(mockConnection);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
