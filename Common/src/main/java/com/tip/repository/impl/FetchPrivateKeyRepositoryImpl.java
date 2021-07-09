package com.tip.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.tip.repository.FetchPrivateKeyRepository;

/**
 * @author Shuvankar Paul
 * Created on Jan 9, 2018
 * 
 */

@Repository
public class FetchPrivateKeyRepositoryImpl implements FetchPrivateKeyRepository, CallableStatementCreator{


	static final Logger logger = LoggerFactory.getLogger(FetchPrivateKeyRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private String procedureCall;
	
	private String ssoId;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Object> fetchKey(String ssoId) {

		this.ssoId = ssoId;
		Map<String, Object> resultMap = null;
		try {

			SqlParameter ssoIdSql = new SqlParameter(Types.VARCHAR);

			List paramList = new ArrayList();
			paramList.add(ssoIdSql);

			FetchPrivateKeyRepositoryImpl fetchPrivateKeyRepositoryImpl = new FetchPrivateKeyRepositoryImpl();
			fetchPrivateKeyRepositoryImpl.procedureCall = "{call Wshp_Get_Login_Keys(?)}";
			fetchPrivateKeyRepositoryImpl.ssoId = ssoId;
			logger.info("Request:-----"+fetchPrivateKeyRepositoryImpl.ssoId);
			logger.info("Calling store procedure..."+fetchPrivateKeyRepositoryImpl.procedureCall);
			resultMap = jdbcTemplate.call((CallableStatementCreator)fetchPrivateKeyRepositoryImpl, paramList);
		} catch (Exception e) {
			logger.error("An error occurred while fetching key from database" + e);
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
