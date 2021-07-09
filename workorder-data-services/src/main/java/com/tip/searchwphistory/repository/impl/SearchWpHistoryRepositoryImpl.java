package com.tip.searchwphistory.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.tip.searchwphistory.model.SearchRange;
import com.tip.searchwphistory.repository.SearchWpHistoryRepository;
import com.tip.workorder.util.DateUtility;
import com.tip.workorder.util.WorkOrderConstants;

@Repository
public class SearchWpHistoryRepositoryImpl implements SearchWpHistoryRepository, CallableStatementCreator {

    static final Logger logger = LoggerFactory.getLogger(SearchWpHistoryRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String procedureCall;
    private SearchRange searchRange;

    public void setSearchRange(SearchRange searchRange) {
        this.searchRange = searchRange;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public Map<String, Object> getWpHistory(SearchRange searchRange) {
        Map<String, Object> resultMap = null;
        Map<String, Object> wpHistorytmap = new HashMap();
        try {

            SqlParameter branchIdSql = new SqlParameter(Types.VARCHAR);
            SqlParameter fromDateSql = new SqlParameter(Types.VARCHAR);
            SqlParameter toDateSql = new SqlParameter(Types.DATE);
            SqlOutParameter erroroutParameter = new SqlOutParameter(WorkOrderConstants.ERROR_CD, Types.VARCHAR);

            List paramList = new ArrayList();
            paramList.add(branchIdSql);
            paramList.add(fromDateSql);
            paramList.add(toDateSql);
            paramList.add(erroroutParameter);
            
            SearchWpHistoryRepositoryImpl searchWpHistoryRepositoryImpl = new SearchWpHistoryRepositoryImpl();
            searchWpHistoryRepositoryImpl.searchRange = searchRange;
            searchWpHistoryRepositoryImpl.procedureCall = "{call Wshp_WorkPack_Search_History(?,?,?,?)}";
            resultMap = jdbcTemplate.call((CallableStatementCreator) searchWpHistoryRepositoryImpl, paramList);
            Iterator<Entry<String, Object>> it = resultMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> entry = it.next();
                String key = entry.getKey();
                logger.info("Key>>>>"+key+"\n Value>>>>"+entry.getValue());
                if (("#result-set-1").equalsIgnoreCase(key)) {
                    wpHistorytmap.put("WPObject", entry.getValue());
                } else if (WorkOrderConstants.ERROR_CD.equalsIgnoreCase(key)) {
                    wpHistorytmap.put(WorkOrderConstants.ERROR_CD, entry.getValue());
                }
            }
        } catch (Exception e) {
            logger.error("An error occurred while searching WP History details." + e);
        }

        return wpHistorytmap;
    }

    @Override
    public CallableStatement createCallableStatement(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall(procedureCall);
        callableStatement.setString(1, searchRange.getBranchId());
        callableStatement.setString(2, DateUtility.changeFormatDate(searchRange.getFromDate()));
        callableStatement.setString(3, DateUtility.changeFormatDate(searchRange.getToDate()));

        callableStatement.registerOutParameter(4, Types.VARCHAR);

        connection.setAutoCommit(true);
        return callableStatement;
    }

}
