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
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tip.model.Keys;
import com.tip.repository.GenerateKeyRepository;

/**
 * @author Shuvankar Paul
 * Created on Jan 9, 2018
 * 
 */

@Repository
public class GenerateKeyRepositoryImpl implements GenerateKeyRepository, CallableStatementCreator{


	static final Logger logger = LoggerFactory.getLogger(GenerateKeyRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private String procedureCall;
	
	private Keys keys;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Object> saveKey(Keys keys) {

		this.keys = keys;
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> resultMap = null;
		try {

			SqlParameter ssoIdSql = new SqlParameter(Types.VARCHAR);
			SqlParameter publicKSql = new SqlParameter(Types.VARCHAR);
			SqlParameter privateKSql = new SqlParameter(Types.VARCHAR);
			SqlOutParameter erroroutParameter = new SqlOutParameter("Error_Cd", Types.VARCHAR);

			List paramList = new ArrayList();
			paramList.add(ssoIdSql);
			paramList.add(publicKSql);
			paramList.add(privateKSql);
			paramList.add(erroroutParameter);

			GenerateKeyRepositoryImpl generateKeyRepositoryImpl = new GenerateKeyRepositoryImpl();
			generateKeyRepositoryImpl.procedureCall = "{call Wshp_Save_Login_Keys(?,?,?,?)}";
			generateKeyRepositoryImpl.keys = keys;
			String requestObject = mapper.writeValueAsString(generateKeyRepositoryImpl.keys);
			logger.info("Request:----- \n"+requestObject);
			logger.info("Calling store procedure..."+generateKeyRepositoryImpl.procedureCall);
			resultMap = jdbcTemplate.call((CallableStatementCreator)generateKeyRepositoryImpl, paramList);
		} catch (Exception e) {
			logger.error("An error occurred while saving key" + e);
		}

		return resultMap;
	}
	
	
	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setString(1, keys.getSsoId());
		callableStatement.setString(2, keys.getPublicK());
		callableStatement.setString(3, keys.getPrivateK());
		callableStatement.registerOutParameter(4, Types.VARCHAR);
		

		connection.setAutoCommit(true);
		return callableStatement;

	}

}
