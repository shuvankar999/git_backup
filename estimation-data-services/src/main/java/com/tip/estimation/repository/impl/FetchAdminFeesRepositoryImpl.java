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

import com.tip.estimation.model.FetchAdminFeesRequest;
import com.tip.estimation.repository.FetchAdminFeesRepository;
import com.tip.util.DatatypeCommonUtility;
import com.tip.util.EstimationConstant;

@Repository
public class FetchAdminFeesRepositoryImpl implements FetchAdminFeesRepository ,CallableStatementCreator{
	
	static final Logger logger = LoggerFactory.getLogger(FetchAdminFeesRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;

	private FetchAdminFeesRequest fetchAdminFeesRequest;

	public void setFetchAdminFeesRequest(FetchAdminFeesRequest fetchAdminFeesRequest) {
		this.fetchAdminFeesRequest = fetchAdminFeesRequest;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Object> fetchAdminFees(FetchAdminFeesRequest fetchAdminFeesRequest) {
		Map<String, Object> resultMap = null;
		try {
			SqlParameter estimationIdParam = new SqlParameter(Types.DECIMAL);
			SqlParameter langIdParam = new SqlParameter(Types.INTEGER);
			
			List paramList = new ArrayList();
			paramList.add(estimationIdParam);
			paramList.add(langIdParam);
			
			FetchAdminFeesRepositoryImpl fetchAdminFeesRepositoryImpl = new FetchAdminFeesRepositoryImpl();
			fetchAdminFeesRepositoryImpl.fetchAdminFeesRequest = fetchAdminFeesRequest;
			fetchAdminFeesRepositoryImpl.procedureCall = "{call "
					+ EstimationConstant.PROC_ESTIMATION_FETCH_ADMIN_FEES + "(?,?)}";
			resultMap = jdbcTemplate.call(fetchAdminFeesRepositoryImpl, paramList);
		} catch (Exception e) {
			logger.error("An error occurred while fetching admin fees: " , e);
		}
		return resultMap;
	}
	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setBigDecimal(1, fetchAdminFeesRequest.getEstimationId());
		DatatypeCommonUtility.checkNull(2, callableStatement, fetchAdminFeesRequest.getLangId());
		connection.setAutoCommit(true);
		return callableStatement;
	}
	
}