package com.tip.fetchworkorder.repository.impl;

import com.tip.fetchworkorder.repository.FetchWorkorderRepository;
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

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;
import java.util.Map.Entry;

@Repository

public class FetchWorkorderRepositoryImpl implements FetchWorkorderRepository, CallableStatementCreator {

    static final Logger logger = LoggerFactory.getLogger(FetchWorkorderRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String procedureCall;
    private BigDecimal workPackNr;
    private int languageId;

    public void setWorkPackNr(BigDecimal workPackNr) {
        this.workPackNr = workPackNr;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public Map<String, Object> getWPackWorderTaskList(BigDecimal workPackNr, int languageId) {
        Map<String, Object> resultMap = null;
        Map<String, Object> wpWoWotListmap = new HashMap();
        try {

            SqlParameter ssoidsql = new SqlParameter(Types.NUMERIC);
            SqlParameter branchidsql = new SqlParameter(Types.INTEGER);

            SqlOutParameter erroroutParameter = new SqlOutParameter("Error_Cd", Types.VARCHAR);

            List paramList = new ArrayList();
            paramList.add(ssoidsql);
            paramList.add(branchidsql);
            paramList.add(erroroutParameter);
            FetchWorkorderRepositoryImpl lFetchWorkorderRepositoryImpl = new FetchWorkorderRepositoryImpl();
            lFetchWorkorderRepositoryImpl.languageId = languageId;
            lFetchWorkorderRepositoryImpl.workPackNr = workPackNr;
            lFetchWorkorderRepositoryImpl.procedureCall = "{call " + WorkOrderConstants.PROC_FETCH_WP_WO_WOT + "(?,?,?)}";
            resultMap = jdbcTemplate.call((CallableStatementCreator) lFetchWorkorderRepositoryImpl, paramList);

            Iterator<Entry<String, Object>> it = resultMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> entry = it.next();
                String key = entry.getKey();
                logger.info("Key>>>>>"+key+"\n value>>>>>"+entry.getValue());
                if (("#result-set-1").equalsIgnoreCase(key)) {
                    wpWoWotListmap.put("WPObjectlist", entry.getValue());
                } else if (("#result-set-2").equalsIgnoreCase(key)) {
                    wpWoWotListmap.put("WOObjectlist", entry.getValue());
                } else if (("#result-set-3").equalsIgnoreCase(key)) {
                    wpWoWotListmap.put("WOTObjectlist", entry.getValue());
                } else if (("#result-set-4").equalsIgnoreCase(key)) {
                    wpWoWotListmap.put("PartsObjectlist", entry.getValue());
                } else if (("#result-set-5").equalsIgnoreCase(key)) {
                    wpWoWotListmap.put("AddtimeObjectlist", entry.getValue());
                } else if (("#result-set-6").equalsIgnoreCase(key)) {
                    wpWoWotListmap.put("TechObjectlist", entry.getValue());
                } else if (("#result-set-7").equalsIgnoreCase(key)) {
                    wpWoWotListmap.put("TechCommentsObject", entry.getValue());
                }

            }

        } catch (Exception e) {
            logger.error("An error occurred while fetchting WO details." + e);
        }

        return wpWoWotListmap;
    }

    @Override
    public CallableStatement createCallableStatement(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall(procedureCall);
        callableStatement.setBigDecimal(1, workPackNr);
        DatatypeCommonUtility.checkNull(2, callableStatement, languageId);

        callableStatement.registerOutParameter(3, Types.VARCHAR);

        connection.setAutoCommit(true);
        return callableStatement;
    }
}
