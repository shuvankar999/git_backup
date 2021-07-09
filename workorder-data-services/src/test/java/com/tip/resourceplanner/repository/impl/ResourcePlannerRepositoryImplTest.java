package com.tip.resourceplanner.repository.impl;

import com.tip.resourceplanner.model.ResourcePlannerRequest;
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
import java.util.*;

public class ResourcePlannerRepositoryImplTest {

    ResourcePlannerRepositoryImpl resourcePlannerRepositoryImpl;
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
        resourcePlannerRepositoryImpl = new ResourcePlannerRepositoryImpl();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(resourcePlannerRepositoryImpl, "jdbcTemplate", jdbcTemplate);
    }

    @Test
    public void testSetDataSource() {
        DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
        resourcePlannerRepositoryImpl.setDataSource(ds);
    }

    @Test
    public void fetchResourcePlannerData() {
        ResourcePlannerRequest resourcePlannerRequest = new ResourcePlannerRequest();
        resourcePlannerRequest.setBranchId("15191");
        try {
            Map<String, Object> resultMap = new HashMap<String, Object>();

            ArrayList<HashMap<String, Object>> technicianListMap = new ArrayList<HashMap<String, Object>>();
            HashMap<String, Object> tempTechnicianListMap = new HashMap<String, Object>(0);
            tempTechnicianListMap.put("Technician_Id", "41564");
            tempTechnicianListMap.put("Technician_Name", "Atul");
            tempTechnicianListMap.put("Status", "Y");
            technicianListMap.add(tempTechnicianListMap);
            HashMap<String, Object> tempTechnicianListMap1 = new HashMap<String, Object>(0);
            tempTechnicianListMap1.put("Technician_Id", "115656");
            tempTechnicianListMap1.put("Technician_Name", "Atul");
            tempTechnicianListMap1.put("Status", "Y");
            technicianListMap.add(tempTechnicianListMap1);
            HashMap<String, Object> tempTechnicianListMap2 = new HashMap<String, Object>(0);
            tempTechnicianListMap2.put("Technician_Id", null);
            tempTechnicianListMap2.put("Technician_Name", "Atul");
            tempTechnicianListMap2.put("Status", "Y");
            technicianListMap.add(tempTechnicianListMap2);
            ArrayList<HashMap<String, Object>> assignedWPListMap = new ArrayList<HashMap<String, Object>>();
            HashMap<String, Object> tempAssignedWPListMap = new HashMap<String, Object>(0);
            tempAssignedWPListMap.put("Work_Pack_Nr", BigDecimal.TEN);
            tempAssignedWPListMap.put("Technician_Id", "115656");
            tempAssignedWPListMap.put("Groups", "A");
            tempAssignedWPListMap.put("Asset_Number", 6454);
            tempAssignedWPListMap.put("Customer_Reference_Nr", "CR34642");
            tempAssignedWPListMap.put("Registration_Nr", "RG646");
            tempAssignedWPListMap.put("Require_Done_Date", new Date());
            tempAssignedWPListMap.put("Total_Guide_Time", 2.4);
            tempAssignedWPListMap.put("Customer_Nr", 81);
            tempAssignedWPListMap.put("Customer_Name", "Vijay");
            tempAssignedWPListMap.put("Chassis_Number", "2");
            tempAssignedWPListMap.put("WO_Cnt", 12);
            tempAssignedWPListMap.put("Description", "Description");
            tempAssignedWPListMap.put("Supplier_Id", null);
            tempAssignedWPListMap.put("Driver_Waiting", "Y");
            tempAssignedWPListMap.put("Work_Pack_Status_Id", 22);
            tempAssignedWPListMap.put("WP_Status", "Y");
            assignedWPListMap.add(tempAssignedWPListMap);
            HashMap<String, Object> tempAssignedWPListMap1 = new HashMap<String, Object>(0);
            tempAssignedWPListMap1.put("Work_Pack_Nr", BigDecimal.TEN);
            tempAssignedWPListMap1.put("Technician_Id", "115656");
            tempAssignedWPListMap1.put("Groups", "A");
            tempAssignedWPListMap1.put("Asset_Number", 6454);
            tempAssignedWPListMap1.put("Customer_Reference_Nr", "CR34642");
            tempAssignedWPListMap1.put("Registration_Nr", "RG646");
            tempAssignedWPListMap1.put("Require_Done_Date", new Date());
            tempAssignedWPListMap1.put("Total_Guide_Time", 2.4);
            tempAssignedWPListMap1.put("Customer_Nr", 81);
            tempAssignedWPListMap1.put("Customer_Name", "Vijay");
            tempAssignedWPListMap1.put("Chassis_Number", "2");
            tempAssignedWPListMap1.put("WO_Cnt", 12);
            tempAssignedWPListMap1.put("Description", "Description");
            tempAssignedWPListMap1.put("Supplier_Id", 186223);
            tempAssignedWPListMap1.put("Driver_Waiting", "Y");
            tempAssignedWPListMap1.put("Work_Pack_Status_Id", 22);
            tempAssignedWPListMap1.put("WP_Status", "Y");
            assignedWPListMap.add(tempAssignedWPListMap1);

            resultMap.put("#result-set-1", technicianListMap);
            resultMap.put("#result-set-2", assignedWPListMap);
            resultMap.put("#result-set-3", 3);
            resultMap.put("Error_Cd", 4);
            Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
            Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenReturn(mockConnection);
            Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class))).thenReturn(resultMap);
            resourcePlannerRepositoryImpl.fetchResourcePlannerData(resourcePlannerRequest);
            Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class))).thenThrow(new IncorrectResultSizeDataAccessException(1));
            try {
                resourcePlannerRepositoryImpl.fetchResourcePlannerData(resourcePlannerRequest);
            } catch (Exception e) {
            }
            Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenThrow(new SQLException());
            resourcePlannerRepositoryImpl.fetchResourcePlannerData(resourcePlannerRequest);
        } catch (Exception e) {
        }
    }

    @Test
    public void createCallableStatement() {
        Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
        ResourcePlannerRequest resourcePlannerRequest = new ResourcePlannerRequest();
        resourcePlannerRequest.setBranchId("123");
        resourcePlannerRepositoryImpl.setResourcePlannerRequest(resourcePlannerRequest);
        try {
            Mockito.when(mockConnection.prepareCall(null)).thenReturn(mockCallableStatement);
            resourcePlannerRepositoryImpl.createCallableStatement(mockConnection);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
