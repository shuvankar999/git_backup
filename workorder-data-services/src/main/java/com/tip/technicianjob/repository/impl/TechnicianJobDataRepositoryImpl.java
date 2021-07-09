package com.tip.technicianjob.repository.impl;

import com.tip.technicianjob.repository.TechnicianJobDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;
import java.util.Map.Entry;

@Repository
public class TechnicianJobDataRepositoryImpl implements TechnicianJobDataRepository, CallableStatementCreator {

    static final Logger logger = LoggerFactory.getLogger(TechnicianJobDataRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String procedureCall;
    private String ssoId;
    private String branchId;

    public void setSsoId(String ssoId) {
        this.ssoId = ssoId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Map<String, Object> getTechJobDataList(String ssoId, String branchId) {

        Map<String, Object> resultMap = null;
        Map<String, Object> techjobdataListmap = new HashMap();
        try {

            SqlParameter ssoidsql = new SqlParameter(Types.VARCHAR);
            SqlParameter branchidsql = new SqlParameter(Types.VARCHAR);

            List paramList = new ArrayList();
            paramList.add(ssoidsql);
            paramList.add(branchidsql);
            TechnicianJobDataRepositoryImpl lTechnicianJobDataRepositoryImpl = new TechnicianJobDataRepositoryImpl();
            lTechnicianJobDataRepositoryImpl.ssoId = ssoId;
            lTechnicianJobDataRepositoryImpl.branchId = branchId;
            lTechnicianJobDataRepositoryImpl.procedureCall = "{call Wshp_TechnicianJobs_Data(?, ?)}";
            resultMap = jdbcTemplate.call((CallableStatementCreator) lTechnicianJobDataRepositoryImpl, paramList);

            Iterator<Entry<String, Object>> it = resultMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> entry = it.next();
                String key = entry.getKey();
                if (("#result-set-1").equalsIgnoreCase(key)) {
                    techjobdataListmap.put("TechQueueObjectlist", entry.getValue());
                } else if (("#result-set-2").equalsIgnoreCase(key)) {
                    techjobdataListmap.put("PoolJobsObjectlist", entry.getValue());
                } else if (("#result-set-3").equalsIgnoreCase(key)) {
                    techjobdataListmap.put("TechnicianEfficiencyObject", entry.getValue());
                }

            }
        } catch (Exception e) {
            logger.error("An error occurred while calling procedure to fetch technician job details: " + e);
        }

        return techjobdataListmap;
    }

    @Override
    public CallableStatement createCallableStatement(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall(procedureCall);
        callableStatement.setString(1, ssoId);
        callableStatement.setString(2, branchId);
        connection.setAutoCommit(true);
        return callableStatement;
    }

}
