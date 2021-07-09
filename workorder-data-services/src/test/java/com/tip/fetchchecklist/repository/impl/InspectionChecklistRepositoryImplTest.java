package com.tip.fetchchecklist.repository.impl;

import com.tip.fetchchecklist.model.InspectionChecklistRequest;
import com.tip.fetchchecklist.model.MaintenanceInspectionRequest;
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
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InspectionChecklistRepositoryImplTest {

    InspectionChecklistRepositoryImpl inspectionChecklistRepositoryImpl;

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
        inspectionChecklistRepositoryImpl = new InspectionChecklistRepositoryImpl();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(inspectionChecklistRepositoryImpl, "jdbcTemplate", jdbcTemplate);
    }

    @Test
    public void testSetDataSource() {
        DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
        inspectionChecklistRepositoryImpl.setDataSource(ds);
    }

    @Test
    public void fetchInspectionPipelineData() {
        try {
            InspectionChecklistRequest inspectionChecklistRequest = new InspectionChecklistRequest();
            inspectionChecklistRequest.setWorkPackNr(BigDecimal.TEN);
            inspectionChecklistRequest.setWorkOrderNr(20);
            inspectionChecklistRequest.setWorkOrderTaskNr(10);
            inspectionChecklistRequest.setLanguageId(5);
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("#result-set-1", 1);
            resultMap.put("#result-set-2", 2);
            resultMap.put("#result-set-3", 3);
            resultMap.put("Error_Cd", 4);
            Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
            Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenReturn(mockConnection);
            Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class))).thenReturn(resultMap);
            inspectionChecklistRepositoryImpl.fetchInspectionPipelineData(inspectionChecklistRequest);
            Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class))).thenThrow(new IncorrectResultSizeDataAccessException(1));
            inspectionChecklistRepositoryImpl.fetchInspectionPipelineData(inspectionChecklistRequest);
        } catch (Exception e) {
        }
    }

    @Test
    public void fetchMaintenanceInspectionData() {
        try {
            MaintenanceInspectionRequest maintenanceInspectionRequest = new MaintenanceInspectionRequest();
            maintenanceInspectionRequest.setInspTypeCd("145");
            maintenanceInspectionRequest.setLangId(2);
            maintenanceInspectionRequest.setWorkPackNr(BigDecimal.TEN);
            maintenanceInspectionRequest.setWorkOrderNr(2);
            maintenanceInspectionRequest.setWorkOrderTaskNr(3);
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("#result-set-1", 1);
            resultMap.put("#result-set-2", 2);
            Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
            Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenReturn(mockConnection);
            Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class))).thenReturn(resultMap);
            inspectionChecklistRepositoryImpl.fetchMaintenanceInspectionData(maintenanceInspectionRequest);
            Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class))).thenThrow(new IncorrectResultSizeDataAccessException(1));
            inspectionChecklistRepositoryImpl.fetchMaintenanceInspectionData(maintenanceInspectionRequest);
        } catch (Exception e) {
        }
    }
}
