package com.tip.fetchtechnician.service.impl;

import com.tip.fetchtechnician.model.TechnicianDetailsRequest;
import com.tip.fetchtechnician.repository.TechnicianDetailsRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

;

public class TechnicianDetailsServiceImplTest {

    TechnicianDetailsServiceImpl technicianDetailsServiceImpl;

    @Mock
    TechnicianDetailsRepository technicianDetailsRepository;

    @Before
    public void beforesetup() {
        technicianDetailsServiceImpl = new TechnicianDetailsServiceImpl();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(technicianDetailsServiceImpl, "technicianDetailsRepository", technicianDetailsRepository);
    }

    @Test
    public void getTechnicianDetails() {
        TechnicianDetailsRequest technicianDetailsRequest = new TechnicianDetailsRequest();
        Map<String, Object> technicianListMap = new HashMap<String, Object>(0);
        List<Object> technicanList = new ArrayList<Object>(0);
        Map<String, Object> technicanObjectMap = new HashMap<String, Object>(0);
        technicanObjectMap.put("Technician_Id", "12");
        technicanObjectMap.put("Technician_Name", "name");
        technicanObjectMap.put("Status", "Logged In");
        technicanList.add(technicanObjectMap);

        Map<String, Object> technicanObjectMap1 = new HashMap<String, Object>(0);
        technicanObjectMap1.put("Technician_Id", "12");
        technicanObjectMap1.put("Technician_Name", "name");
        technicanObjectMap1.put("Status", "Logged Out");
        technicanList.add(technicanObjectMap1);

        technicianListMap.put("TechnicianDetailsList", technicanList);
        Mockito.when(technicianDetailsRepository.getTechnicianDetails(technicianDetailsRequest)).thenReturn(technicianListMap);
        technicianDetailsServiceImpl.getTechnicianDetails(technicianDetailsRequest);
    }
}
