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

import com.tip.estimationfilter.repository.FetchProfileDataRepository;
import com.tip.util.EstimationConstant;


@Repository
public class FetchProfileDataRepositoryImpl implements FetchProfileDataRepository, CallableStatementCreator{

	static final Logger logger = LoggerFactory.getLogger(FetchProfileDataRepositoryImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	
	private String ssoId;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Object> fetchProfileData(String ssoId){

		Map<String, Object> resultMap = new HashMap();
		try {
			SqlParameter ssoIdNrSQLparam = new SqlParameter(Types.VARCHAR);
			
			List paramList = new ArrayList();
			paramList.add(ssoIdNrSQLparam);
			
			FetchProfileDataRepositoryImpl fetchProfileDataRepositoryImpl = new FetchProfileDataRepositoryImpl();
			fetchProfileDataRepositoryImpl.ssoId = ssoId;
			fetchProfileDataRepositoryImpl.procedureCall = "{call " + EstimationConstant.PROC_FETCH_EST_SEARCH_DEFAULT + "(?)}";
			resultMap = jdbcTemplate.call(fetchProfileDataRepositoryImpl, paramList);
			logger.info("Calling stored procedure..."+fetchProfileDataRepositoryImpl.procedureCall);
		} catch (Exception e) {
			logger.error("An error occurred while fetching branch list for profile : " + e);
			resultMap.put(EstimationConstant.ERROR_CD, e.getMessage());
		}
		return resultMap;
	}
	
	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);		
		callableStatement.setString(1, ssoId);
		connection.setAutoCommit(true);
		return callableStatement;
	}
}