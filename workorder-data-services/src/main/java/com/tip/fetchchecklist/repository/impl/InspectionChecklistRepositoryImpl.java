package com.tip.fetchchecklist.repository.impl;

import com.tip.fetchchecklist.model.InspectionChecklistRequest;
import com.tip.fetchchecklist.model.InspectionChecklistResponse;
import com.tip.fetchchecklist.model.MaintenanceInspectionRequest;
import com.tip.fetchchecklist.model.MaintenanceInspectionResponse;
import com.tip.fetchchecklist.repository.InspectionChecklistRepository;
import com.tip.workorder.util.DatatypeCommonUtility;
import com.tip.workorder.util.WorkOrderConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
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
public class InspectionChecklistRepositoryImpl implements InspectionChecklistRepository {

    static final Logger logger = LoggerFactory.getLogger(InspectionChecklistRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public InspectionChecklistResponse fetchInspectionPipelineData(InspectionChecklistRequest inspectionChecklistRequest) {
        InspectionChecklistResponse inspectionChecklistResponse = new InspectionChecklistResponse();

        final BigDecimal workPackNr = inspectionChecklistRequest.getWorkPackNr();
        final Integer workOrderNr = inspectionChecklistRequest.getWorkOrderNr();
        final Integer workOrderTaskNr = inspectionChecklistRequest.getWorkOrderTaskNr();
        final Integer languageId = inspectionChecklistRequest.getLanguageId();

        Map<String, Object> resultMap = null;

        Map<String, Object> inspectionChecklistMap = new HashMap();

        try {

            SqlParameter workPackNrSQLparam = new SqlParameter(Types.DECIMAL);
            SqlParameter workOrderNrSQLparam = new SqlParameter(Types.INTEGER);
            SqlParameter workOrderTaskNrSQLparam = new SqlParameter(Types.INTEGER);
            SqlParameter languageIdSQLparam = new SqlParameter(Types.INTEGER);
            SqlParameter errorCdSQLparam = new SqlParameter(Types.VARCHAR);

            List paramList = new ArrayList();
            paramList.add(workPackNrSQLparam);
            paramList.add(workOrderNrSQLparam);
            paramList.add(workOrderTaskNrSQLparam);
            paramList.add(languageIdSQLparam);
            paramList.add(errorCdSQLparam);

            final String procedureCall = "{call " + WorkOrderConstants.PROC_FETCH_INSPECTION_CHECKLIST + "(?, ?, ?, ?, ?)}";


            resultMap = jdbcTemplate.call(new CallableStatementCreator() {
                @Override
                public CallableStatement createCallableStatement(Connection connection) throws SQLException {
                    connection.setAutoCommit(false);
                    CallableStatement callableStatement = connection.prepareCall(procedureCall);
                    callableStatement.setBigDecimal(1, workPackNr);
                    DatatypeCommonUtility.checkNull(2, callableStatement, workOrderNr);
                    DatatypeCommonUtility.checkNull(3, callableStatement, workOrderTaskNr);
                    DatatypeCommonUtility.checkNull(4, callableStatement, languageId);
                    callableStatement.setString(5, "NULL");
                    connection.setAutoCommit(true);
                    return callableStatement;
                }
            }, paramList);

            Iterator<Entry<String, Object>> it = resultMap.entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry<String, Object> entry = it.next();
                String key = entry.getKey();

                if (("#result-set-1").equalsIgnoreCase(key)) {
                    inspectionChecklistMap.put("InspectionFailureDetails", entry.getValue());
                }
                if (("#result-set-2").equalsIgnoreCase(key)) {
                    inspectionChecklistMap.put("InspectionCheckDetails", entry.getValue());
                }
                if (("#result-set-3").equalsIgnoreCase(key)) {
                    inspectionChecklistMap.put("InspectionImageDetails", entry.getValue());
                } else if (WorkOrderConstants.ERROR_CD.equalsIgnoreCase(key)) {
                    inspectionChecklistMap.put(WorkOrderConstants.ERROR_CD, entry.getValue());
                }
            }

            inspectionChecklistResponse.setInspectionChecklistMap(inspectionChecklistMap);
        } catch (Exception e) {
            StackTraceElement stackTraceElement = e.getStackTrace()[0];
            logger.error("An error occurred while fetchting inspection checklist." + e);
            logger.error("At Line" + stackTraceElement.getClassName() + ":" + stackTraceElement.getLineNumber());
        }
        return inspectionChecklistResponse;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public MaintenanceInspectionResponse fetchMaintenanceInspectionData(MaintenanceInspectionRequest maintenanceInspectionRequest) {

        MaintenanceInspectionResponse maintenanceInspectionResponse = new MaintenanceInspectionResponse();

        Map<String, Object> resultMap = null;

        Map<String, Object> maintenanceInspectionMap = new HashMap();

        try {

            SqlParameter inspectionTypeCdSQLparam = new SqlParameter(Types.VARCHAR);
            SqlParameter langIdSQLparam = new SqlParameter(Types.INTEGER);
            SqlParameter wpNrSQLparam = new SqlParameter(Types.DECIMAL);
            SqlParameter woNrSQLparam = new SqlParameter(Types.INTEGER);
            SqlParameter woTaskNrSQLparam = new SqlParameter(Types.INTEGER);
            SqlOutParameter erroroutParameter = new SqlOutParameter("Error_Cd", Types.VARCHAR);

            List paramList = new ArrayList();
            paramList.add(inspectionTypeCdSQLparam);
            paramList.add(langIdSQLparam);
            paramList.add(wpNrSQLparam);
            paramList.add(woNrSQLparam);
            paramList.add(woTaskNrSQLparam);
            paramList.add(erroroutParameter);

            final String procedureCall = "{call " + WorkOrderConstants.PROC_FETCH_MAINTAINENCE_INSPECTION_CHECKLIST + "(?, ? ,?, ?, ?, ?)}";


            resultMap = jdbcTemplate.call(new CallableStatementCreator() {
                @Override
                public CallableStatement createCallableStatement(Connection connection) throws SQLException {
                    connection.setAutoCommit(false);
                    CallableStatement callableStatement = connection.prepareCall(procedureCall);
                    callableStatement.setString(1, maintenanceInspectionRequest.getInspTypeCd());
                    DatatypeCommonUtility.checkNull(2, callableStatement, maintenanceInspectionRequest.getLangId());
                    callableStatement.setBigDecimal(3, maintenanceInspectionRequest.getWorkPackNr());
                    DatatypeCommonUtility.checkNull(4, callableStatement, maintenanceInspectionRequest.getWorkOrderNr());
                    DatatypeCommonUtility.checkNull(5, callableStatement, maintenanceInspectionRequest.getWorkOrderTaskNr());
                    callableStatement.registerOutParameter(6, Types.VARCHAR);
                    connection.setAutoCommit(true);
                    return callableStatement;
                }
            }, paramList);

            Iterator<Entry<String, Object>> it = resultMap.entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry<String, Object> entry = it.next();
                String key = entry.getKey();
                if (("#result-set-1").equalsIgnoreCase(key)) {
                    maintenanceInspectionMap.put("MaintenanceInspectionDetails", entry.getValue());
                }
            }

        } catch (Exception e) {
            logger.error("An error occurred while fetchting fetchting Maintainence." + e);
        }
        maintenanceInspectionResponse.setMaintenanceInspectionMap(maintenanceInspectionMap);

        return maintenanceInspectionResponse;
    }

}
