package com.tip.fetchwohistory.repository.impl;

import com.tip.fetchwohistory.model.WorkOrderRequest;
import com.tip.fetchwohistory.repository.FetchWoHistoryRepository;
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
public class FetchWoHistoryRepositoryImpl implements FetchWoHistoryRepository, CallableStatementCreator {

    static final Logger logger = LoggerFactory.getLogger(FetchWoHistoryRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String procedureCall;
    private WorkOrderRequest workOrderRequest;

    public void setWorkOrderRequest(WorkOrderRequest workOrderRequest) {
        this.workOrderRequest = workOrderRequest;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public Map<String, Object> getWoHistory(WorkOrderRequest workOrderRequest) {
        Map<String, Object> resultMap = null;
        Map<String, Object> wpHistorytmap = new HashMap();
        try {

            SqlParameter workPackNrSql = new SqlParameter(Types.DECIMAL);
            SqlParameter wpOpenedDateSql = new SqlParameter(Types.DATE);
            SqlParameter groupCdSql = new SqlParameter(Types.VARCHAR);
            SqlParameter unitNrSql = new SqlParameter(Types.INTEGER);
            SqlOutParameter erroroutParameter = new SqlOutParameter(WorkOrderConstants.ERROR_CD, Types.VARCHAR);

            List paramList = new ArrayList();
            paramList.add(workPackNrSql);
            paramList.add(wpOpenedDateSql);
            paramList.add(groupCdSql);
            paramList.add(unitNrSql);
            paramList.add(erroroutParameter);
            FetchWoHistoryRepositoryImpl lFetchWoHistoryRepositoryImpl = new FetchWoHistoryRepositoryImpl();
            lFetchWoHistoryRepositoryImpl.procedureCall = "{call Wshp_ws_Get_Unit_WO_History (?,?,?,?,?)}";
            lFetchWoHistoryRepositoryImpl.workOrderRequest = workOrderRequest;
            resultMap = jdbcTemplate.call((CallableStatementCreator) lFetchWoHistoryRepositoryImpl, paramList);


            Iterator<Entry<String, Object>> it = resultMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> entry = it.next();
                String key = entry.getKey();
                if (("#result-set-1").equalsIgnoreCase(key)) {
                    wpHistorytmap.put("WOhistory", entry.getValue());
                } else if (key.equalsIgnoreCase(WorkOrderConstants.ERROR_CD)) {
                    wpHistorytmap.put(WorkOrderConstants.ERROR_CD, entry.getValue());
                }
            }
        } catch (Exception e) {
            logger.error("An error occurred while fetchting WO History." + e);
        }

        return wpHistorytmap;
    }

    @Override
    public CallableStatement createCallableStatement(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall(procedureCall);
        callableStatement.setBigDecimal(1, workOrderRequest.getWorkPackNr());
        callableStatement.setDate(2, DateUtility.stringToSqlDate(workOrderRequest.getWpOpenedDate()));
        callableStatement.setString(3, workOrderRequest.getGroupCd());
        DatatypeCommonUtility.checkNull(4, callableStatement, workOrderRequest.getUnitNr());

        callableStatement.registerOutParameter(5, Types.VARCHAR);

        connection.setAutoCommit(true);
        return callableStatement;
    }
}
