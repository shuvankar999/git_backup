package com.tip.fetchwohistory.repository.impl;

import com.tip.fetchwohistory.model.WorkOrderRequest;
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
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FetchWoHistoryRepositoryImplTest {

    FetchWoHistoryRepositoryImpl fetchWoHistoryRepositoryImpl;
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
        fetchWoHistoryRepositoryImpl = new FetchWoHistoryRepositoryImpl();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(fetchWoHistoryRepositoryImpl, "jdbcTemplate", jdbcTemplate);
    }

    @Test
    public void getWoHistory() {
        try {
            WorkOrderRequest workOrderRequest = new WorkOrderRequest();
            workOrderRequest.setWorkPackNr(BigDecimal.ONE);
            workOrderRequest.setWpOpenedDate("01-02-2017");
            workOrderRequest.setGroupCd("26484");
            workOrderRequest.setUnitNr(55);
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("#result-set-1", 1);
            resultMap.put("Error_Cd", 2);
            Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
            Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenReturn(mockConnection);
            Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class))).thenReturn(resultMap);
            fetchWoHistoryRepositoryImpl.getWoHistory(workOrderRequest);
            Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class))).thenThrow(new IncorrectResultSizeDataAccessException(1));
            fetchWoHistoryRepositoryImpl.getWoHistory(workOrderRequest);
        } catch (Exception e) {
        }
    }

    @Test
    public void createCallableStatement() {
        Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
        WorkOrderRequest workOrderRequest = new WorkOrderRequest();
        workOrderRequest.setWorkPackNr(new BigDecimal("12"));
        workOrderRequest.setGroupCd("1234");
        workOrderRequest.setUnitNr(123);
        fetchWoHistoryRepositoryImpl.setWorkOrderRequest(workOrderRequest);
        try {
            Mockito.when(mockConnection.prepareCall(null)).thenReturn(mockCallableStatement);
            fetchWoHistoryRepositoryImpl.createCallableStatement(mockConnection);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
