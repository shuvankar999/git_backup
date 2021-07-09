package com.tip.technicianjob.service.impl;

import com.tip.technicianjob.repository.TechnicianJobDataRepository;
import com.tip.technicianjob.service.TechnicianJobDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


@Service
@Transactional
public class TechnicianJobDataServiceImpl implements TechnicianJobDataService {


    @Autowired
    TechnicianJobDataRepository technicianJobDataDAO;

    @Override
    public Map<String, Object> getTechJobDataList(String ssoId, String branchId) {
        return technicianJobDataDAO.getTechJobDataList(ssoId, branchId);
    }
}
