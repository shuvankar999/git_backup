package com.tip.fetchwpvalidate.repository.impl;

import com.tip.fetchwpvalidate.model.FetchWPValidateRequest;
import com.tip.fetchwpvalidate.model.FetchWPValidateResponse;
import com.tip.fetchwpvalidate.repository.FetchWPValidateRepository;
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
public class FetchWPValidateRepositoryImpl implements FetchWPValidateRepository, CallableStatementCreator {

    static final Logger logger = LoggerFactory.getLogger(FetchWPValidateRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String procedureCall;
    private FetchWPValidateRequest fetchWPValidateRequest;

    @Autowired
    public void setDataSource(DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
    }

    public void setFetchWPValidateRequest(FetchWPValidateRequest fetchWPValidateRequest) {
        this.fetchWPValidateRequest = fetchWPValidateRequest;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public FetchWPValidateResponse fetchWPValidateData(FetchWPValidateRequest fetchWPValidateRequest) {

        FetchWPValidateResponse fetchWPValidateResponse = new FetchWPValidateResponse();


        Map<String, Object> resultMap = null;
        Map<String, Object> fetchWPValidateMap = new HashMap();
        try {

            SqlParameter workPackNrSQLparam = new SqlParameter(Types.FLOAT);
            SqlParameter branchIdSQLParam = new SqlParameter(Types.VARCHAR);
            SqlParameter errorCdSQLparam = new SqlParameter(Types.VARCHAR);

            List paramList = new ArrayList();
            paramList.add(workPackNrSQLparam);
            paramList.add(branchIdSQLParam);
            paramList.add(errorCdSQLparam);
            FetchWPValidateRepositoryImpl lFetchWPValidateRepositoryImpl = new FetchWPValidateRepositoryImpl();
            lFetchWPValidateRepositoryImpl.fetchWPValidateRequest = fetchWPValidateRequest;
            lFetchWPValidateRepositoryImpl.procedureCall = "{call Wshp_Fetch_WP_To_Be_Validate(?, ?, ?)}";
            resultMap = jdbcTemplate.call((CallableStatementCreator) lFetchWPValidateRepositoryImpl, paramList);

            Iterator<Entry<String, Object>> it = resultMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> entry = it.next();
                String key = entry.getKey();
                if (("#result-set-1").equalsIgnoreCase(key)) {
                    fetchWPValidateMap.put("WorkPackDetailsToValidate", entry.getValue());
                } else if (WorkOrderConstants.ERROR_CD.equalsIgnoreCase(key)) {
                    fetchWPValidateMap.put("Error_Cd", entry.getValue());
                }
            }
        } catch (Exception e) {
            logger.error("An error occurred while fetching WP to validate details." + e);
        }

        fetchWPValidateResponse.setFetchWPValidateMap(fetchWPValidateMap);
        return fetchWPValidateResponse;
    }

    @Override
    public CallableStatement createCallableStatement(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall(procedureCall);
        callableStatement.setString(1, fetchWPValidateRequest.getSsoId());
        callableStatement.setString(2, fetchWPValidateRequest.getBranchId());
        callableStatement.setString(3, "NULL");
        connection.setAutoCommit(true);
        return callableStatement;
    }
}
