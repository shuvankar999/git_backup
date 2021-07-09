package com.tip.fetchwphistory.repository.impl;

import com.tip.fetchwphistory.model.WorkPackRequest;
import com.tip.fetchwphistory.repository.FetchWpHistoryRepository;
import com.tip.workorder.util.DatatypeCommonUtility;
import com.tip.workorder.util.DateUtility;
import com.tip.workorder.util.WorkOrderConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;
import java.util.Map.Entry;

@Repository

public class FetchWpHistoryRepositoryImpl implements FetchWpHistoryRepository, CallableStatementCreator {

    static final Logger logger = LoggerFactory.getLogger(FetchWpHistoryRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String procedureCall;
    private WorkPackRequest workPackRequest;

    public void setWorkPackRequest(WorkPackRequest workPackRequest) {
        this.workPackRequest = workPackRequest;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public Map<String, Object> getWpHistory(WorkPackRequest workPackRequest) {
        Map<String, Object> resultMap = null;
        Map<String, Object> wpHistorytmap = new HashMap();
        try {

            SqlParameter workPackNrSql = new SqlParameter(Types.DECIMAL);
            SqlParameter unitNrSql = new SqlParameter(Types.INTEGER);
            SqlParameter wpOpenedDateSql = new SqlParameter(Types.DATE);
            SqlOutParameter erroroutParameter = new SqlOutParameter(WorkOrderConstants.ERROR_CD, Types.VARCHAR);

            List paramList = new ArrayList();
            paramList.add(workPackNrSql);
            paramList.add(unitNrSql);
            paramList.add(wpOpenedDateSql);
            paramList.add(erroroutParameter);
            FetchWpHistoryRepositoryImpl lFetchWpHistoryRepositoryImpl = new FetchWpHistoryRepositoryImpl();
            lFetchWpHistoryRepositoryImpl.workPackRequest = workPackRequest;
            lFetchWpHistoryRepositoryImpl.procedureCall = "{call Wshp_ws_Get_Unit_WP_History(?,?,?,?)}";
            resultMap = jdbcTemplate.call((CallableStatementCreator) lFetchWpHistoryRepositoryImpl, paramList);
            Iterator<Entry<String, Object>> it = resultMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> entry = it.next();
                String key = entry.getKey();
                if (("#result-set-1").equalsIgnoreCase(key)) {
                    wpHistorytmap.put("WPhistory", entry.getValue());
                } else if (WorkOrderConstants.ERROR_CD.equalsIgnoreCase(key)) {
                    wpHistorytmap.put(WorkOrderConstants.ERROR_CD, entry.getValue());
                }
            }
        } catch (Exception e) {
            logger.error("An error occurred while fetchting WP History details." + e);
        }

        return wpHistorytmap;
    }

    @Override
    public CallableStatement createCallableStatement(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall(procedureCall);
        callableStatement.setBigDecimal(1, workPackRequest.getWorkPackNr());
        DatatypeCommonUtility.checkNull(2, callableStatement, workPackRequest.getUnitNr());
        callableStatement.setDate(3, DateUtility.stringToSqlDate(workPackRequest.getWpOpenedDate()));

        callableStatement.registerOutParameter(4, Types.VARCHAR);

        connection.setAutoCommit(true);
        return callableStatement;
    }

}
