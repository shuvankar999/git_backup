package com.tip.fetchtechnician.repository.impl;


import com.tip.fetchtechnician.model.TechnicianDetailsRequest;
import com.tip.fetchtechnician.repository.TechnicianDetailsRepository;
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
public class TechnicianDetailsRepositoryImpl implements TechnicianDetailsRepository, CallableStatementCreator {

    static final Logger logger = LoggerFactory.getLogger(TechnicianDetailsRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String procedureCall;
    private TechnicianDetailsRequest technicianDetailsRequest;

    @Autowired
    public void setDataSource(DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
    }

    public void setTechnicianDetailsRequest(TechnicianDetailsRequest technicianDetailsRequest) {
        this.technicianDetailsRequest = technicianDetailsRequest;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Map<String, Object> getTechnicianDetails(TechnicianDetailsRequest technicianDetailsRequest) {


        Map<String, Object> resultMap = null;

        Map<String, Object> technicianListMap = new HashMap();
        try {

            SqlParameter branchIdSQLparam = new SqlParameter(Types.VARCHAR);
            SqlParameter errorCdSQLparam = new SqlParameter(Types.VARCHAR);

            List paramList = new ArrayList();
            paramList.add(branchIdSQLparam);
            paramList.add(errorCdSQLparam);
            TechnicianDetailsRepositoryImpl lTechnicianDetailsRepositoryImpl = new TechnicianDetailsRepositoryImpl();
            lTechnicianDetailsRepositoryImpl.procedureCall = "{call Wshp_Resrc_Planner_Job_List(?, ?)}";
            lTechnicianDetailsRepositoryImpl.technicianDetailsRequest = technicianDetailsRequest;
            resultMap = jdbcTemplate.call((CallableStatementCreator) lTechnicianDetailsRepositoryImpl, paramList);

            Iterator<Entry<String, Object>> it = resultMap.entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry<String, Object> entry = it.next();
                String key = entry.getKey();

                if (("#result-set-1").equalsIgnoreCase(key)) {
                    technicianListMap.put("TechnicianDetailsList", entry.getValue());
                } else if (("Error_Cd").equalsIgnoreCase(key)) {
                    technicianListMap.put("Error_Cd", entry.getValue());
                }
            }

        } catch (Exception e) {
            logger.error("An error occurred while fetching resource pipeline details: " + e);
        }

        return technicianListMap;
    }

    @Override
    public CallableStatement createCallableStatement(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall(procedureCall);
        callableStatement.setString(1, technicianDetailsRequest.getBranchId());
        callableStatement.registerOutParameter(2, Types.VARCHAR);
        connection.setAutoCommit(true);
        return callableStatement;
    }


}
