package com.tip.fetchwpvalidate.repository.impl;

import com.tip.fetchwpvalidate.model.FetchWPValidateRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
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

public class FetchWPValidateRepositoryImplTest {

    FetchWPValidateRepositoryImpl fetchWPValidateRepositoryImpl;
    @Mock
    DataSource dataSource;
    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private Connection mockConnection;
    @Mock
    private CallableStatement mockCallableStatement;

    @Autowired
    public void setDataSource(DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
    }

    @Before
    public void beforesetup() {
        fetchWPValidateRepositoryImpl = new FetchWPValidateRepositoryImpl();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(fetchWPValidateRepositoryImpl, "jdbcTemplate", jdbcTemplate);
    }

    @Test
    public void testSetDataSource() {
        DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
        fetchWPValidateRepositoryImpl.setDataSource(ds);
    }

    @Test
    public void fetchWPValidateData() {
        FetchWPValidateRequest fetchWPValidateRequest = new FetchWPValidateRequest();
        fetchWPValidateRequest.setSsoId("1561");
        fetchWPValidateRequest.setBranchId("51456");
        try {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("#result-set-1", 1);
            resultMap.put("Error_Cd", 2);
            Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
            Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenReturn(mockConnection);
            Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class))).thenReturn(resultMap);
            fetchWPValidateRepositoryImpl.fetchWPValidateData(fetchWPValidateRequest);
            Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class))).thenThrow(new IncorrectResultSizeDataAccessException(1));
            try {
                fetchWPValidateRepositoryImpl.fetchWPValidateData(fetchWPValidateRequest);
            } catch (Exception e) {
            }
            Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenThrow(new SQLException());
            fetchWPValidateRepositoryImpl.fetchWPValidateData(fetchWPValidateRequest);
        } catch (Exception e) {
        }
    }

    @Test
    public void createCallableStatement() {
        Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
        FetchWPValidateRequest fetchWPValidateRequest = new FetchWPValidateRequest();
        fetchWPValidateRequest.setBranchId("123");
        fetchWPValidateRequest.setSsoId("123");
        fetchWPValidateRepositoryImpl.setFetchWPValidateRequest(fetchWPValidateRequest);
        try {
            Mockito.when(mockConnection.prepareCall(null)).thenReturn(mockCallableStatement);
            fetchWPValidateRepositoryImpl.createCallableStatement(mockConnection);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
