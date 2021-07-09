package com.tip.resourceplanner.service.impl;

import com.tip.resourceplanner.model.ResourcePlannerRequest;
import com.tip.resourceplanner.model.ResourcePlannerResponse;
import com.tip.resourceplanner.repository.ResourcePlannerRepository;
import com.tip.resourceplanner.service.ResourcePlannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResourcePlannerServiceImpl implements ResourcePlannerService {

    @Autowired
    ResourcePlannerRepository resourcePlannerRepository;
    
    @Override
    public ResourcePlannerResponse getResourcePlannerDetails(ResourcePlannerRequest resourcePlannerRequest) {

        return resourcePlannerRepository.fetchResourcePlannerData(resourcePlannerRequest);
    }

}
