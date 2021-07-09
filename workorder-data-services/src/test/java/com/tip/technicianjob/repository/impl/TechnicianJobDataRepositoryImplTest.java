package com.tip.technicianjob.repository.impl;

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

public class TechnicianJobDataRepositoryImplTest {

    TechnicianJobDataRepositoryImpl technicianJobDataRepositoryImpl;
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
        technicianJobDataRepositoryImpl = new TechnicianJobDataRepositoryImpl();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(technicianJobDataRepositoryImpl, "jdbcTemplate", jdbcTemplate);
    }

    @Test
    public void getTechJobDataList() {
        String sso_id = "215";
        String branch_id = "3245";
        try {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("#result-set-1", 1);
            resultMap.put("#result-set-2", 2);
            resultMap.put("#result-set-3", 3);
            Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
            Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenReturn(mockConnection);
            Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class))).thenReturn(resultMap);
            technicianJobDataRepositoryImpl.getTechJobDataList(sso_id, branch_id);

            Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class))).thenThrow(new IncorrectResultSizeDataAccessException(1));
            technicianJobDataRepositoryImpl.getTechJobDataList(sso_id, branch_id);
        } catch (Exception e) {
        }
    }

    @Test
    public void createCallableStatement() {
        Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
        technicianJobDataRepositoryImpl.setBranchId("123");
        technicianJobDataRepositoryImpl.setSsoId("123454");
        try {
            Mockito.when(mockConnection.prepareCall(null)).thenReturn(mockCallableStatement);
            technicianJobDataRepositoryImpl.createCallableStatement(mockConnection);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
