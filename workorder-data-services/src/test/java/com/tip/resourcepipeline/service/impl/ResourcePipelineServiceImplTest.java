package com.tip.resourcepipeline.service.impl;

import com.tip.resourcepipeline.model.ResourcePipelineRequest;
import com.tip.resourcepipeline.repository.ResourcePipelineRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

;

public class ResourcePipelineServiceImplTest {

    ResourcePipelineServiceImpl resourcePipelineServiceImpl;

    @Mock
    ResourcePipelineRepository resourcePipelineRepository;

    @Before
    public void beforesetup() {
        resourcePipelineServiceImpl = new ResourcePipelineServiceImpl();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(resourcePipelineServiceImpl, "resourcePipelineRepository", resourcePipelineRepository);
    }

    @Test
    public void getResourcePipelineDetails() {
        ResourcePipelineRequest resourcePipelineRequest = new ResourcePipelineRequest();
        Map<String, Object> resourcePipelineMap = new HashMap<String, Object>(0);
        List<Object> pipelineList = new ArrayList<Object>(0);
        Map<String, Object> pipelineObjecttMap = new HashMap<String, Object>(0);
        pipelineObjecttMap.put("Asset_Number", 12);
        pipelineObjecttMap.put("Customer_Reference_Nr", "56156");
        pipelineObjecttMap.put("Registration_Nr", "3551");
        pipelineObjecttMap.put("Work_Pack_Nr", BigDecimal.TEN);
        java.sql.Timestamp sqlDt = new Timestamp(46556456);
        pipelineObjecttMap.put("Require_Done_Date", sqlDt);
        pipelineObjecttMap.put("Total_Guide_Time", 2.3);
        pipelineObjecttMap.put("Customer_Nr", 62);
        pipelineObjecttMap.put("Customer_Name", "Rahul");
        pipelineObjecttMap.put("Chassis_Number", "3551");
        pipelineObjecttMap.put("WO_Cnt", 2);
        pipelineObjecttMap.put("Description", "Description");
        pipelineObjecttMap.put("Supplier_Id", 18522);
        pipelineObjecttMap.put("Driver_Waiting", "Y");
        pipelineObjecttMap.put("Work_Pack_Status_Id", 6648);
        pipelineObjecttMap.put("WP_Status", "Y");
        pipelineList.add(pipelineObjecttMap);
        resourcePipelineMap.put("PipelineDetails", pipelineList);
        Mockito.when(resourcePipelineRepository.fetchResourcePipelineData(resourcePipelineRequest)).thenReturn(resourcePipelineMap);
        resourcePipelineServiceImpl.getResourcePipelineDetails(resourcePipelineRequest);
    }
}
