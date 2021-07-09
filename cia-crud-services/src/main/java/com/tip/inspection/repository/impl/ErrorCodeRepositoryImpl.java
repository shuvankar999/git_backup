package com.tip.inspection.repository.impl;

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

import com.tip.inspection.main.InspectionCrudConstants;
import com.tip.inspection.repository.ErrorCodeRepository;

@Repository
public class ErrorCodeRepositoryImpl implements ErrorCodeRepository, CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(ErrorCodeRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private String procedureCall;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> getErrorCode() {
		Map<String, Object> resultMap = null;
		try {

			SqlParameter langIdIdparam = new SqlParameter(Types.INTEGER);
			SqlParameter errorcdparam = new SqlParameter(Types.VARCHAR);

			List paramList = new ArrayList();
			
			paramList.add(langIdIdparam);
			paramList.add(errorcdparam);

			ErrorCodeRepositoryImpl errorCodeRepositoryImpl = new ErrorCodeRepositoryImpl();
			errorCodeRepositoryImpl.procedureCall = "{call " + InspectionCrudConstants.PROC_FETCH_CIA_EEROR_DESC
					+ " (?,?)}";
			resultMap = jdbcTemplate.call(errorCodeRepositoryImpl, paramList);
		} catch (Exception e) {
			logger.error("An error occurred while fetching user profile details: ", e);
		}
		return resultMap;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setInt(1, 0);
		callableStatement.setString(2, null);
		connection.setAutoCommit(true);
		return callableStatement;
	}

}
