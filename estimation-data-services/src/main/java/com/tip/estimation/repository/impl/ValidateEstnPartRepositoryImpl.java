package com.tip.estimation.repository.impl;

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

import com.tip.estimation.model.ValidatePartRequest;
import com.tip.estimation.repository.ValidateEstnPartRepository;
import com.tip.util.EstimationConstant;

@Repository
public class ValidateEstnPartRepositoryImpl implements ValidateEstnPartRepository ,CallableStatementCreator{

	
	static final Logger logger = LoggerFactory.getLogger(ValidateEstnPartRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private String procedureCall;
	
	private ValidatePartRequest validatePartRequest;

	public void setValidatePartRequest(ValidatePartRequest validatePartRequest) {
		this.validatePartRequest = validatePartRequest;
	}

	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> validatePart(ValidatePartRequest validatePartRequest) {
		
		Map<String, Object> resultMap = null;
		try {
			SqlParameter estimationIdParam = new SqlParameter(Types.NUMERIC);
			SqlParameter scanPartNrParam = new SqlParameter(Types.VARCHAR);
			SqlParameter errorCdParam = new SqlParameter(Types.VARCHAR);

			List paramList = new ArrayList();
			paramList.add(estimationIdParam);
			paramList.add(scanPartNrParam);
			paramList.add(errorCdParam);

			ValidateEstnPartRepositoryImpl validateEstnPartRepositoryImpl = new ValidateEstnPartRepositoryImpl();
			validateEstnPartRepositoryImpl.validatePartRequest = validatePartRequest;
			validateEstnPartRepositoryImpl.procedureCall = "{call "
					+ EstimationConstant.PROC_VALIDATE_EST_PART + " (?,?,?)}";
			
			
			resultMap = jdbcTemplate.call(validateEstnPartRepositoryImpl, paramList);
					
	} catch (Exception e) {
		logger.error("An error occurred while Validating Estn Part: " + e);
		
	}return resultMap;
		
	}
	
	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setBigDecimal(1, validatePartRequest.getEstimationId());
		callableStatement.setString(2, validatePartRequest.getScanPartNr());
		callableStatement.setString(3, validatePartRequest.getErrorCd());
		connection.setAutoCommit(true);
		return callableStatement;
	}
}
