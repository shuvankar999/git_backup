package com.tip.resourceplanner.repository.impl;

import com.tip.resourceplanner.model.AssignedWPDetails;
import com.tip.resourceplanner.model.ResourcePlannerRequest;
import com.tip.resourceplanner.model.ResourcePlannerResponse;
import com.tip.resourceplanner.model.TechnicianDetails;
import com.tip.resourceplanner.repository.ResourcePlannerRepository;
import com.tip.workorder.util.DateUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;
import java.util.Map.Entry;

@Repository
public class ResourcePlannerRepositoryImpl implements ResourcePlannerRepository, CallableStatementCreator {

    static final Logger logger = LoggerFactory.getLogger(ResourcePlannerRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String procedureCall;
    private ResourcePlannerRequest resourcePlannerRequest;

    @Autowired
    public void setDataSource(DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
    }

    public void setResourcePlannerRequest(ResourcePlannerRequest resourcePlannerRequest) {
        this.resourcePlannerRequest = resourcePlannerRequest;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public ResourcePlannerResponse fetchResourcePlannerData(ResourcePlannerRequest resourcePlannerRequest) {

        ResourcePlannerResponse resourcePlannerResponse = new ResourcePlannerResponse();

        Map<String, Object> resultMap = null;

        ArrayList<HashMap<String, Object>> technicianListMap = new ArrayList();
        ArrayList<HashMap<String, Object>> assignedWPListMap = new ArrayList();

        ArrayList<TechnicianDetails> technicianList = new ArrayList();
        ArrayList<AssignedWPDetails> assignedWPList = new ArrayList();

        List<TechnicianDetails> finalTechnicianDetailsList = new ArrayList();

        Map<String, Object> resourcePlannerMap = new HashMap();

        try {

            SqlParameter branchIdSQLparam = new SqlParameter(Types.VARCHAR);
            SqlParameter errorCdSQLparam = new SqlParameter(Types.VARCHAR);

            List paramList = new ArrayList();
            paramList.add(branchIdSQLparam);
            paramList.add(errorCdSQLparam);
            ResourcePlannerRepositoryImpl lResourcePlannerRepositoryImpl = new ResourcePlannerRepositoryImpl();
            lResourcePlannerRepositoryImpl.resourcePlannerRequest = resourcePlannerRequest;
            lResourcePlannerRepositoryImpl.procedureCall = "{call Wshp_Resrc_Planner_Job_List(?, ?)}";
            resultMap = jdbcTemplate.call((CallableStatementCreator) lResourcePlannerRepositoryImpl, paramList);

            Iterator<Entry<String, Object>> it = resultMap.entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry<String, Object> entry = it.next();
                String key = entry.getKey();

                if (("#result-set-1").equalsIgnoreCase(key)) {
                    technicianListMap = (ArrayList<HashMap<String, Object>>) entry.getValue();

                } else if (("#result-set-2").equalsIgnoreCase(key)) {
                    assignedWPListMap = (ArrayList<HashMap<String, Object>>) entry.getValue();
                } else if (("#result-set-3").equalsIgnoreCase(key)) {
                    resourcePlannerMap.put("NoOfWorkpacksToValidate", entry.getValue());
                } else if (("Error_Cd").equalsIgnoreCase(key)) {
                    resourcePlannerMap.put("Error_Cd", entry.getValue());
                }
            }

        } catch (Exception e) {
            logger.error("An error occurred while fetching resource pipeline details: " + e);
        }


        Iterator<HashMap<String, Object>> technicianListMapIterator = technicianListMap.iterator();
        Iterator<HashMap<String, Object>> assignedWPMapIterator = assignedWPListMap.iterator();

        while (technicianListMapIterator.hasNext()) {
            TechnicianDetails tempTechnicianDetails = new TechnicianDetails();
            Map<String, Object> tempTechnicianListMap = technicianListMapIterator.next();
            tempTechnicianDetails.setTechnicianId((String) tempTechnicianListMap.get("Technician_Id"));
            tempTechnicianDetails.setTechnicianName((String) tempTechnicianListMap.get("Technician_Name"));
            tempTechnicianDetails.setTechnicianStatus((String) tempTechnicianListMap.get("Status"));
            technicianList.add(tempTechnicianDetails);
        }

        while (assignedWPMapIterator.hasNext()) {
            AssignedWPDetails tempAssignedWPDetails = new AssignedWPDetails();
            Map<String, Object> tempAssignedWPListMap = assignedWPMapIterator.next();

            BigDecimal workPackNrBD = (BigDecimal) tempAssignedWPListMap.get("Work_Pack_Nr");

            tempAssignedWPDetails.setWorkPackNr(workPackNrBD.doubleValue());
            tempAssignedWPDetails.setTechnicianId((String) tempAssignedWPListMap.get("Technician_Id"));
            tempAssignedWPDetails.setGroups((String) tempAssignedWPListMap.get("Groups"));
            tempAssignedWPDetails.setAssetNumber((Integer) tempAssignedWPListMap.get("Asset_Number"));
            tempAssignedWPDetails.setCustomerReferenceNr((String) tempAssignedWPListMap.get("Customer_Reference_Nr"));
            tempAssignedWPDetails.setRegistrationNr((String) tempAssignedWPListMap.get("Registration_Nr"));
            tempAssignedWPDetails.setRequiredDoneDate(DateUtility.dateToString((Date) tempAssignedWPListMap.get("Require_Done_Date")));
            tempAssignedWPDetails.setTotalGuideTime((Double) tempAssignedWPListMap.get("Total_Guide_Time"));
            tempAssignedWPDetails.setCustomerNr((Integer) tempAssignedWPListMap.get("Customer_Nr"));
            tempAssignedWPDetails.setCustomerName((String) tempAssignedWPListMap.get("Customer_Name"));
            tempAssignedWPDetails.setChassisNumber((String) tempAssignedWPListMap.get("Chassis_Number"));
            tempAssignedWPDetails.setWorkOrderCnt((Integer) tempAssignedWPListMap.get("WO_Cnt"));
            tempAssignedWPDetails.setDescription((String) tempAssignedWPListMap.get("Description"));

            tempAssignedWPDetails.setSupplierId(0);
            if (tempAssignedWPListMap.get("Supplier_Id") != null) {
                tempAssignedWPDetails.setSupplierId((Integer) tempAssignedWPListMap.get("Supplier_Id"));
            }

            tempAssignedWPDetails.setDriverWaiting((String) tempAssignedWPListMap.get("Driver_Waiting"));
            tempAssignedWPDetails.setWorkPackStatusId((Integer) tempAssignedWPListMap.get("Work_Pack_Status_Id"));
            tempAssignedWPDetails.setWorkPackStatus((String) tempAssignedWPListMap.get("WP_Status"));

            assignedWPList.add(tempAssignedWPDetails);
        }

        Iterator<TechnicianDetails> techDetailsItr = technicianList.iterator();

        while (techDetailsItr.hasNext()) {
            List<AssignedWPDetails> tempAWPList = new ArrayList();

            TechnicianDetails tempTD = techDetailsItr.next();

            String currentTechId = tempTD.getTechnicianId();

            Iterator<AssignedWPDetails> assignedWPDetailsItr = assignedWPList.iterator();
            while (assignedWPDetailsItr.hasNext()) {
                AssignedWPDetails tempWP = assignedWPDetailsItr.next();

                String assignedWPTechID = tempWP.getTechnicianId();
                if (currentTechId != null && currentTechId.trim().equalsIgnoreCase(assignedWPTechID.trim())) {
                    tempAWPList.add(tempWP);
                }
            }

            tempTD.setAssignedWPDetailsList(tempAWPList);
            finalTechnicianDetailsList.add(tempTD);

        }
        resourcePlannerMap.put("TechnicianDetailsList", finalTechnicianDetailsList);
        resourcePlannerResponse.setResourcePlannerMap(resourcePlannerMap);

        return resourcePlannerResponse;
    }

    @Override
    public CallableStatement createCallableStatement(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall(procedureCall);
        callableStatement.setString(1, resourcePlannerRequest.getBranchId());
        callableStatement.setString(2, "NULL");
        connection.setAutoCommit(true);
        return callableStatement;
    }
}
