package com.tip.technicianjob.service.impl;

import com.tip.technicianjob.repository.TechnicianJobDataRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

public class TechnicianJobDataServiceImplTest {

    TechnicianJobDataServiceImpl technicianJobDataServiceImpl;

    @Mock
    TechnicianJobDataRepository technicianJobDataDAO;

    @Before
    public void beforesetup() {
        technicianJobDataServiceImpl = new TechnicianJobDataServiceImpl();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(technicianJobDataServiceImpl, "technicianJobDataDAO", technicianJobDataDAO);
    }

    @Test
    public void getTechJobDataList() {
        String sso_id = "215";
        String branch_id = "3245";
        technicianJobDataServiceImpl.getTechJobDataList(sso_id, branch_id);
    }
}
