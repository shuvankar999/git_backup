package com.tip.rplanner.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.tip.rplanner.model.WpRequest;
import com.tip.rplanner.repository.FetchRplanWpDetailsRepository;
import com.tip.workorder.util.DatatypeCommonUtility;
import com.tip.workorder.util.WorkOrderConstants;

@Repository
public class FetchRplanWpDetailsRepositoryImpl implements FetchRplanWpDetailsRepository, CallableStatementCreator{

	static final Logger logger = LoggerFactory.getLogger(FetchRplanWpDetailsRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	
	private WpRequest wpRequest;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> getWpdetails(WpRequest wpRequest) {
		
		this.wpRequest = wpRequest;
		Map<String, Object> resultMap = new HashMap(); 
		
		try {

			SqlParameter wpNrSQLparam = new SqlParameter(Types.DECIMAL);
			SqlParameter langIdSQLparam = new SqlParameter(Types.INTEGER);
			SqlParameter errorCdSQLparam = new SqlParameter(Types.VARCHAR);

			List paramList = new ArrayList();
			paramList.add(wpNrSQLparam);
			paramList.add(langIdSQLparam);
			paramList.add(errorCdSQLparam);

			FetchRplanWpDetailsRepositoryImpl fetchRplanWpDetailsRepositoryImpl = new FetchRplanWpDetailsRepositoryImpl();
			fetchRplanWpDetailsRepositoryImpl.wpRequest = wpRequest;
			fetchRplanWpDetailsRepositoryImpl.procedureCall = "{call "+ WorkOrderConstants.PROC_FETCH_WP_RPLAN +"(?, ?, ?)}";
			
			resultMap = jdbcTemplate.call(fetchRplanWpDetailsRepositoryImpl, paramList);
		} catch (Exception e) {
			logger.error("An error occurred while fetching wp details for popup: " + e);
		}
		
		return resultMap;
	}	
	
	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);		
		callableStatement.setBigDecimal(1, wpRequest.getWorkPackNr());
		DatatypeCommonUtility.checkNull(2, callableStatement, wpRequest.getLangId());
		callableStatement.setString(3, "NULL");
		connection.setAutoCommit(true);
		return callableStatement;
	}
}
