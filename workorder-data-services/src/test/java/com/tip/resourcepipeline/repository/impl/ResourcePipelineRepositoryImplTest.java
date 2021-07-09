package com.tip.resourcepipeline.repository.impl;

import com.tip.resourcepipeline.model.ResourcePipelineRequest;
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

public class ResourcePipelineRepositoryImplTest {

    ResourcePipelineRepositoryImpl resourcePipelineRepositoryImpl;
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
        resourcePipelineRepositoryImpl = new ResourcePipelineRepositoryImpl();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(resourcePipelineRepositoryImpl, "jdbcTemplate", jdbcTemplate);
    }

    @Test
    public void testSetDataSource() {
        DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
        resourcePipelineRepositoryImpl.setDataSource(ds);
    }

    @Test
    public void fetchResourcePipelineData() {
        ResourcePipelineRequest resourcePipelineRequest = new ResourcePipelineRequest();
        resourcePipelineRequest.setBranchId("4984");
        try {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("#result-set-1", 1);
            resultMap.put("Error_Cd", 2);
            Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
            Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenReturn(mockConnection);
            Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class))).thenReturn(resultMap);
            resourcePipelineRepositoryImpl.fetchResourcePipelineData(resourcePipelineRequest);
            Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class))).thenThrow(new IncorrectResultSizeDataAccessException(1));

            try {
                resourcePipelineRepositoryImpl.fetchResourcePipelineData(resourcePipelineRequest);
            } catch (Exception e) {
            }
            Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenThrow(new SQLException());
            resourcePipelineRepositoryImpl.fetchResourcePipelineData(resourcePipelineRequest);
        } catch (Exception e) {
        }
    }

    @Test
    public void createCallableStatement() {
        Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
        ResourcePipelineRequest resourcePipelineRequest = new ResourcePipelineRequest();
        resourcePipelineRequest.setBranchId("123");
        resourcePipelineRepositoryImpl.setResourcePipelineRequest(resourcePipelineRequest);
        try {
            Mockito.when(mockConnection.prepareCall(null)).thenReturn(mockCallableStatement);
            resourcePipelineRepositoryImpl.createCallableStatement(mockConnection);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
