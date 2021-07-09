package com.tip.resourcepipeline.service.impl;

import com.tip.resourcepipeline.model.PipelineDetails;
import com.tip.resourcepipeline.model.ResourcePipelineRequest;
import com.tip.resourcepipeline.model.ResourcePipelineResponse;
import com.tip.resourcepipeline.repository.ResourcePipelineRepository;
import com.tip.resourcepipeline.service.ResourcePipelineService;
import com.tip.workorder.util.DateUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class ResourcePipelineServiceImpl implements ResourcePipelineService {

    @Autowired
    ResourcePipelineRepository resourcePipelineRepository;

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public ResourcePipelineResponse getResourcePipelineDetails(ResourcePipelineRequest resourcePipelineRequest) {

        ResourcePipelineResponse resourcePipelineResponse = new ResourcePipelineResponse();


        Map<String, Object> resourcePipelineMap = resourcePipelineRepository.fetchResourcePipelineData(resourcePipelineRequest);
        List<Object> pipelineList = (List<Object>) resourcePipelineMap.get("PipelineDetails");
        Iterator pipelineListIterator = pipelineList.iterator();
        while (pipelineListIterator.hasNext()) {

            PipelineDetails pipelineDetails = new PipelineDetails();

            Map<String, Object> pipelineObjecttMap = (Map<String, Object>) pipelineListIterator.next();
            pipelineDetails.setAssetNumber((Integer) pipelineObjecttMap.get("Asset_Number"));
            pipelineDetails.setCustomerName((String) pipelineObjecttMap.get("Customer_Reference_Nr"));
            pipelineDetails.setRegistrationNr((String) pipelineObjecttMap.get("Registration_Nr"));
            pipelineDetails.setWorkPackNr((BigDecimal) pipelineObjecttMap.get("Work_Pack_Nr"));
            pipelineDetails.setRequiredDoneDate((String) DateUtility.dateToString((Date) pipelineObjecttMap.get("Required_Done_Date")));
            pipelineDetails.setTotalGuideTime((Double) pipelineObjecttMap.get("Total_Guide_Time"));
            pipelineDetails.setCustomerNr((Integer) pipelineObjecttMap.get("Customer_Nr"));
            pipelineDetails.setCustomerName((String) pipelineObjecttMap.get("Customer_Name"));
            pipelineDetails.setChassisNumber((String) pipelineObjecttMap.get("Chassis_Number"));
            pipelineDetails.setWorkOrderCnt((Integer) pipelineObjecttMap.get("WO_Cnt"));
            pipelineDetails.setDescription((String) pipelineObjecttMap.get("Description"));
            pipelineDetails.setSupplierId((Integer) pipelineObjecttMap.get("Supplier_Id"));
            pipelineDetails.setDriverWaiting((String) pipelineObjecttMap.get("Driver_Waiting"));
            pipelineDetails.setWorkPackStatusId((Integer) pipelineObjecttMap.get("Work_Pack_Status_Id"));
            pipelineDetails.setWorkPackStatus((String) pipelineObjecttMap.get("WP_Status"));
            pipelineDetails.setWorkPackPriority((Integer) pipelineObjecttMap.get("WP_Priority"));
            pipelineDetails.setWorkDate((String) DateUtility.dateToString((Date)pipelineObjecttMap.get("Work_Date")));
            pipelineDetails.setUnitCatgrpCode((String) pipelineObjecttMap.get("Unit_Catgrp_Code"));
            pipelineDetails.setSupplierName((String) pipelineObjecttMap.get("Supplier_Name"));


            resourcePipelineResponse.getPipelineDetails().add(pipelineDetails);
        }


        return resourcePipelineResponse;
    }

}
