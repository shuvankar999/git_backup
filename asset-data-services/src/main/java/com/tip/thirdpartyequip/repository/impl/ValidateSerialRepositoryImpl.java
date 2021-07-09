package com.tip.thirdpartyequip.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tip.asset.util.AssetConstants;
import com.tip.asset.util.DatatypeCommonUtility;
import com.tip.thirdpartyequip.model.EquipTyreReadingRequest;
import com.tip.thirdpartyequip.model.ValidSerialNrRequest;
import com.tip.thirdpartyequip.repository.ValidateSerialRepository;

@Repository
public class ValidateSerialRepositoryImpl implements ValidateSerialRepository, CallableStatementCreator{

	static final Logger logger = LoggerFactory.getLogger(ValidateSerialRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	
	private ValidSerialNrRequest validSerialNrRequest;

	@Autowired
	public void setDataSource(DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> validateSerialNr(ValidSerialNrRequest validSerialNrRequest) {
		Map<String, Object> resultMap = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			SqlParameter equipNrSQLparam = new SqlParameter(Types.INTEGER);
			SqlParameter serialSQLparam = new SqlParameter(Types.VARCHAR);

			List paramList = new ArrayList();
			paramList.add(equipNrSQLparam);
			paramList.add(serialSQLparam);

			ValidateSerialRepositoryImpl validateSerialRepositoryImpl = new ValidateSerialRepositoryImpl();
			validateSerialRepositoryImpl.validSerialNrRequest = validSerialNrRequest;
			validateSerialRepositoryImpl.procedureCall = "{call " + AssetConstants.PROC_VALIDATE_SERIALNR_THIRD_PARTY + " (?,?)}";
			logger.info("Request JSON "+mapper.writeValueAsString(validateSerialRepositoryImpl.validSerialNrRequest));
			logger.info("Procedure calling..."+validateSerialRepositoryImpl.procedureCall);
			resultMap = jdbcTemplate.call(validateSerialRepositoryImpl, paramList);
		} catch (Exception e) {
			logger.error("An error occurred while validating serial nr: "+e);
		}
		return resultMap;
	}
	
	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);		
		DatatypeCommonUtility.checkNull(1, callableStatement, validSerialNrRequest.getEquipmentNr());
		callableStatement.setString(2, validSerialNrRequest.getSerialNr());
		connection.setAutoCommit(true);
		return callableStatement;
	}
}
