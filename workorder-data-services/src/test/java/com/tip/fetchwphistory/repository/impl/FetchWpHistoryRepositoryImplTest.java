package com.tip.fetchwphistory.repository.impl;

import com.tip.fetchwphistory.model.WorkPackRequest;
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

public class FetchWpHistoryRepositoryImplTest {

    FetchWpHistoryRepositoryImpl fetchWpHistoryRepositoryImpl;
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
        fetchWpHistoryRepositoryImpl = new FetchWpHistoryRepositoryImpl();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(fetchWpHistoryRepositoryImpl, "jdbcTemplate", jdbcTemplate);
    }

    @Test
    public void getWpHistory() {
        WorkPackRequest workPackRequest = new WorkPackRequest();
        workPackRequest.setWorkPackNr(BigDecimal.TEN);
        workPackRequest.setUnitNr(23);
        workPackRequest.setWpOpenedDate("01-02-2017");
        try {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("#result-set-1", 1);
            resultMap.put("Error_Cd", 2);
            Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
            Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenReturn(mockConnection);
            Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class))).thenReturn(resultMap);
            fetchWpHistoryRepositoryImpl.getWpHistory(workPackRequest);

            Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class))).thenThrow(new IncorrectResultSizeDataAccessException(1));
            fetchWpHistoryRepositoryImpl.getWpHistory(workPackRequest);
        } catch (Exception e) {
        }
    }

    @Test
    public void createCallableStatement() {
        Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
        WorkPackRequest workPackRequest = new WorkPackRequest();
        workPackRequest.setUnitNr(123);
        fetchWpHistoryRepositoryImpl.setWorkPackRequest(workPackRequest);
        try {
            Mockito.when(mockConnection.prepareCall(null)).thenReturn(mockCallableStatement);
            fetchWpHistoryRepositoryImpl.createCallableStatement(mockConnection);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
