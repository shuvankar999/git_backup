package com.tip.fetchattention.repository.impl;

import com.tip.fetchattention.model.AttentionRequest;
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

public class FetchAttentionRepositoryImplTest {

    FetchAttentionRepositoryImpl fetchAttentionRepositoryImpl;
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
        fetchAttentionRepositoryImpl = new FetchAttentionRepositoryImpl();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(fetchAttentionRepositoryImpl, "jdbcTemplate", jdbcTemplate);
    }

    @Test
    public void testSetDataSource() {
        DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
        fetchAttentionRepositoryImpl.setDataSource(ds);
    }

    @Test
    public void fetchAttentionData() {
        try {
            AttentionRequest attentionRequest = new AttentionRequest();
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("#result-set-1", 1);
            resultMap.put("#result-set-2", 2);
            resultMap.put("Error_Cd", 3);
            Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
            Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenReturn(mockConnection);
            Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class))).thenReturn(resultMap);
            fetchAttentionRepositoryImpl.fetchAttentionData(attentionRequest);
            Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class))).thenThrow(new IncorrectResultSizeDataAccessException(1));
            try {
                fetchAttentionRepositoryImpl.fetchAttentionData(attentionRequest);
            } catch (Exception e) {
            }
            Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenThrow(new SQLException());
            fetchAttentionRepositoryImpl.fetchAttentionData(attentionRequest);
        } catch (Exception e) {
        }
    }

    @Test
    public void createCallableStatement() {
        Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
        AttentionRequest attentionRequest = new AttentionRequest();
        attentionRequest.setLangId(5);
        attentionRequest.setLangId(62);
        fetchAttentionRepositoryImpl.setAttentionRequest(attentionRequest);
        try {
            Mockito.when(mockConnection.prepareCall(null)).thenReturn(mockCallableStatement);
            fetchAttentionRepositoryImpl.createCallableStatement(mockConnection);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
