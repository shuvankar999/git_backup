package com.tip.estimationfilter.repository.impl;

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

import com.tip.estimationfilter.model.FetchFilterDetailsRequest;
import com.tip.estimationfilter.repository.FetchFilterRepository;
import com.tip.util.DatatypeCommonUtility;
import com.tip.util.EstimationConstant;

@Repository
public class FetchFilterRepositoryImpl implements FetchFilterRepository, CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(FetchFilterRepositoryImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	
	private FetchFilterDetailsRequest fetchFilterDetailsRequest;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Object> getFilterDetils(FetchFilterDetailsRequest fetchFilterDetailsRequest) {

		Map<String, Object> resultMap = new HashMap();
		try {
			SqlParameter ssoIdNrSQLparam = new SqlParameter(Types.VARCHAR);
			SqlParameter langIdNrSQLparam = new SqlParameter(Types.INTEGER);
			
			List paramList = new ArrayList();
			paramList.add(ssoIdNrSQLparam);
			paramList.add(langIdNrSQLparam);
			FetchFilterRepositoryImpl fetchFilterRepositoryImpl = new FetchFilterRepositoryImpl();
			fetchFilterRepositoryImpl.fetchFilterDetailsRequest = fetchFilterDetailsRequest;
			fetchFilterRepositoryImpl.procedureCall = "{call " + EstimationConstant.PROC_ESTIMATION_FETCH_FILTER_DETAILS + "(?,?)}";
			resultMap = jdbcTemplate.call(fetchFilterRepositoryImpl, paramList);
			logger.info("Calling stored procedure..."+fetchFilterRepositoryImpl.procedureCall);
		} catch (Exception e) {
			StackTraceElement stackTraceElement = e.getStackTrace()[0];
			logger.error("An error occurred while fetching filter details : " + e);
			logger.error("At Line: " + stackTraceElement.getClassName()+":" + stackTraceElement.getLineNumber());
			resultMap.put(EstimationConstant.ERROR_CD, e.getMessage());
		}
		return resultMap;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);		
		callableStatement.setString(1, fetchFilterDetailsRequest.getSsoId());
		DatatypeCommonUtility.checkNull(2, callableStatement, fetchFilterDetailsRequest.getLangId());
		connection.setAutoCommit(true);
		return callableStatement;
	}

	public void setFetchFilterDetailsRequest(FetchFilterDetailsRequest fetchFilterDetailsRequest) {
		this.fetchFilterDetailsRequest = fetchFilterDetailsRequest;
	}
}
