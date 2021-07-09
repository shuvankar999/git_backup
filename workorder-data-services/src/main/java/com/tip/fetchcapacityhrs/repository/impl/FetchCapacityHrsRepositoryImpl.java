package com.tip.fetchcapacityhrs.repository.impl;

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

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.tip.fetchcapacityhrs.model.CapacityHrsRequest;
import com.tip.fetchcapacityhrs.repository.FetchCapacityHrsRepository;

@Repository
public class FetchCapacityHrsRepositoryImpl implements FetchCapacityHrsRepository,CallableStatementCreator{
	
	
	static final Logger logger = LoggerFactory.getLogger(FetchCapacityHrsRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String procedureCall;
    private CapacityHrsRequest capacityHrsRequest;

    public void setCapacityHrsRequest(CapacityHrsRequest capacityHrsRequest) {
        this.capacityHrsRequest = capacityHrsRequest;
    }

    @Autowired
    public void setDataSource(DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
    }


	@Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String, Object> getCapcityHrs(CapacityHrsRequest request) {

        Map<String, Object> resultMap = new HashMap();
        Map<String, Object> responseMap = new HashMap();
  
        try {

            SqlParameter branchIdSQLParam = new SqlParameter(Types.VARCHAR);
            SqlParameter periodTypeSQLParam = new SqlParameter(Types.VARCHAR);
            SqlParameter startdateSQLparam = new SqlParameter(Types.VARCHAR);
            SqlParameter enddateSQLparam = new SqlParameter(Types.VARCHAR);


			List paramList = new ArrayList();
            paramList.add(branchIdSQLParam);
            paramList.add(periodTypeSQLParam);
            paramList.add(startdateSQLparam);
            paramList.add(enddateSQLparam);

            FetchCapacityHrsRepositoryImpl lFetchCapacityHrsRepositoryImpl = new FetchCapacityHrsRepositoryImpl();
            lFetchCapacityHrsRepositoryImpl.procedureCall = "{call Wshp_Fetch_Capacity_Hours(?, ?, ?, ?)}";
            lFetchCapacityHrsRepositoryImpl.capacityHrsRequest = request;
            
            logger.info("Calling store procedure..."+"\n "+lFetchCapacityHrsRepositoryImpl.procedureCall);
            resultMap = jdbcTemplate.call((CallableStatementCreator) lFetchCapacityHrsRepositoryImpl, paramList);


            Iterator<Entry<String, Object>> it = resultMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> entry = it.next();
                String key = entry.getKey();
                Object value = entry.getValue();
                logger.info("Key: " + key);
                logger.info("Value: " + value);
                if (("#result-set-1").equalsIgnoreCase(key)) {
                	responseMap.put("Validationlist", entry.getValue());
                } else if (("#result-set-2").equalsIgnoreCase(key)) {
                	responseMap.put("CapacityHrs", entry.getValue());
                }
            }

        } catch (Exception e) {
            logger.error("An error occurred while fetching capacity hrs data: " + e);
        }
        return responseMap;
    }
	
	@Override
    public CallableStatement createCallableStatement(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall(procedureCall);
        callableStatement.setString(1, capacityHrsRequest.getBranchId());
        callableStatement.setString(2, capacityHrsRequest.getPeriodType());
        callableStatement.setString(3, capacityHrsRequest.getStartDate());
        callableStatement.setString(4, capacityHrsRequest.getEndDate());
        connection.setAutoCommit(true);
        return callableStatement;
    }

}
