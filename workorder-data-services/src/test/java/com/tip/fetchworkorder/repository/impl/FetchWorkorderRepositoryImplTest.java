package com.tip.fetchworkorder.repository.impl;

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

public class FetchWorkorderRepositoryImplTest {

    FetchWorkorderRepositoryImpl fetchWorkorderRepositoryImpl;
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
        fetchWorkorderRepositoryImpl = new FetchWorkorderRepositoryImpl();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(fetchWorkorderRepositoryImpl, "jdbcTemplate", jdbcTemplate);
    }

    @Test
    public void getWPackWorderTaskList() {
        //long workpack_nr = 2l;
        BigDecimal workpack_nr = BigDecimal.TEN;
        int language_id = 1;
        try {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("#result-set-1", 1);
            resultMap.put("#result-set-2", 2);
            resultMap.put("#result-set-3", 3);
            resultMap.put("#result-set-4", 4);
            resultMap.put("#result-set-5", 5);
            Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
            Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenReturn(mockConnection);
            Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class))).thenReturn(resultMap);
            fetchWorkorderRepositoryImpl.getWPackWorderTaskList(workpack_nr, language_id);
            Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class))).thenThrow(new IncorrectResultSizeDataAccessException(1));
            fetchWorkorderRepositoryImpl.getWPackWorderTaskList(workpack_nr, language_id);
        } catch (Exception e) {
        }
    }

    @Test
    public void createCallableStatement() {
        Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
        fetchWorkorderRepositoryImpl.setLanguageId(123);
        fetchWorkorderRepositoryImpl.setWorkPackNr(new BigDecimal("12"));
        try {
            Mockito.when(mockConnection.prepareCall(null)).thenReturn(mockCallableStatement);
            fetchWorkorderRepositoryImpl.createCallableStatement(mockConnection);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
