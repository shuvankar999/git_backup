package com.tip.inspection.repository.impl;

import com.tip.inspection.model.InspectionRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootConfiguration
public class InspectionRepositoryRepositoryImplTest {

    InspectionRepositoryRepositoryImpl inspectionRepositoryRepositoryImpl;
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
        inspectionRepositoryRepositoryImpl = new InspectionRepositoryRepositoryImpl();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(inspectionRepositoryRepositoryImpl, "jdbcTemplate", jdbcTemplate);
    }

    @Test
    public void testSetDataSource() {
        DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
        inspectionRepositoryRepositoryImpl.setDataSource(ds);
    }

    @Test
    public void fetchInspectionData() {
        InspectionRequest inspectionRequest = new InspectionRequest();
        inspectionRequest.setUnitNr("4");
        try {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("#result-set-1", 1);
            resultMap.put("Error_Cd", 2);
            Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
            Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenReturn(mockConnection);
            Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class))).thenReturn(resultMap);
            inspectionRepositoryRepositoryImpl.fetchInspectionData(inspectionRequest);
            Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class))).thenThrow(new IncorrectResultSizeDataAccessException(1));
            try {
                inspectionRepositoryRepositoryImpl.fetchInspectionData(inspectionRequest);
            } catch (Exception e) {
            }
            Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenThrow(new SQLException());
            inspectionRepositoryRepositoryImpl.fetchInspectionData(inspectionRequest);
        } catch (Exception e) {
        }
    }

    @Test
    public void createCallableStatement() {
        Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
        InspectionRequest inspectionRequest = new InspectionRequest();
        inspectionRequest.setUnitNr("123");
        inspectionRepositoryRepositoryImpl.setInspectionRequest(inspectionRequest);
        try {
            Mockito.when(mockConnection.prepareCall(null)).thenReturn(mockCallableStatement);
            inspectionRepositoryRepositoryImpl.createCallableStatement(mockConnection);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
