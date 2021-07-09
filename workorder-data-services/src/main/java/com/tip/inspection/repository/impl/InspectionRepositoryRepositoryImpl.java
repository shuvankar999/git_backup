package com.tip.inspection.repository.impl;

import com.tip.inspection.model.InspectionRequest;
import com.tip.inspection.model.InspectionResponse;
import com.tip.inspection.repository.InspectionRepository;
import com.tip.workorder.util.WorkOrderConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;
import java.util.Map.Entry;

@Repository
public class InspectionRepositoryRepositoryImpl implements InspectionRepository, CallableStatementCreator {

    static final Logger logger = LoggerFactory.getLogger(InspectionRepositoryRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String procedureCall;
    private InspectionRequest inspectionRequest;

    @Autowired
    public void setDataSource(DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
    }

    public void setInspectionRequest(InspectionRequest inspectionRequest) {
        this.inspectionRequest = inspectionRequest;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public InspectionResponse fetchInspectionData(InspectionRequest inspectionRequest) {

        InspectionResponse inspectionResponse = new InspectionResponse();

        Map<String, Object> resultMap = null;

        Map<String, Object> inspectionMap = new HashMap();

        try {

            SqlParameter unitNrSQLparam = new SqlParameter(Types.INTEGER);
            SqlParameter errorCdSQLparam = new SqlParameter(Types.VARCHAR);

            List paramList = new ArrayList();
            paramList.add(unitNrSQLparam);
            paramList.add(errorCdSQLparam);
            InspectionRepositoryRepositoryImpl lInspectionRepositoryRepositoryImpl = new InspectionRepositoryRepositoryImpl();
            lInspectionRepositoryRepositoryImpl.inspectionRequest = inspectionRequest;
            lInspectionRepositoryRepositoryImpl.procedureCall = "{call " + WorkOrderConstants.PROC_FETCH_INSPECTION + "(?, ?)}";
            resultMap = jdbcTemplate.call((CallableStatementCreator) lInspectionRepositoryRepositoryImpl, paramList);

            Iterator<Entry<String, Object>> it = resultMap.entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry<String, Object> entry = it.next();
                String key = entry.getKey();
                Object value = entry.getValue();

                logger.info("Key: " + key);
                logger.info("Value: " + value);

                if (("#result-set-1").equalsIgnoreCase(key)) {
                    inspectionMap.put("InspectionDetails", entry.getValue());
                } else if (("Error_Cd").equalsIgnoreCase(key)) {
                    inspectionMap.put("Error_Cd", entry.getValue());
                }
            }

        } catch (Exception e) {
            logger.error("An error occurred while fetching inspection details: " + e);
        }

        inspectionResponse.setInspectionMap(inspectionMap);

        return inspectionResponse;
    }

    @Override
    public CallableStatement createCallableStatement(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall(procedureCall);
        callableStatement.setInt(1, Integer.parseInt(inspectionRequest.getUnitNr()));
        callableStatement.setString(2, "NULL");
        connection.setAutoCommit(true);
        return callableStatement;
    }
}
