package com.tip.fetchtechnician.service.impl;

import com.tip.fetchtechnician.model.TechnicianDetails;
import com.tip.fetchtechnician.model.TechnicianDetailsRequest;
import com.tip.fetchtechnician.repository.TechnicianDetailsRepository;
import com.tip.fetchtechnician.service.TechnicianDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

;


@Service
@Transactional
public class TechnicianDetailsServiceImpl implements TechnicianDetailsService {

    @Autowired
    TechnicianDetailsRepository technicianDetailsRepository;

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public Map<String, List<TechnicianDetails>> getTechnicianDetails(TechnicianDetailsRequest technicianDetailsRequest) {

        Map<String, Object> technicianListMap = technicianDetailsRepository.getTechnicianDetails(technicianDetailsRequest);

        List<Object> technicanList = (List<Object>) technicianListMap.get("TechnicianDetailsList");
        Iterator technicanListIterator = technicanList.iterator();

        Map<String, List<TechnicianDetails>> technicianListResponseMap = new HashMap();

        List<TechnicianDetails> technicanResponseList = new ArrayList();
        while (technicanListIterator.hasNext()) {

            Map<String, Object> technicanObjectMap = (Map<String, Object>) technicanListIterator.next();
            TechnicianDetails technicianDetails = new TechnicianDetails();
            technicianDetails.setTechnicianId((String) technicanObjectMap.get("Technician_Id"));
            technicianDetails.setTechnicianName((String) technicanObjectMap.get("Technician_Name"));
            technicianDetails.setTechnicianStatus((String) technicanObjectMap.get("Status"));
            technicianDetails.setWpCount((Integer) technicanObjectMap.get("wpCount"));
            technicanResponseList.add(technicianDetails);
        }

        technicianListResponseMap.put("TechnicianDetailsList", technicanResponseList);
        return technicianListResponseMap;

    }

}
