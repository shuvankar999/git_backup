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

import com.tip.estimation.model.FetchEstnConsumablesRequest;
import com.tip.estimation.repository.FetchEstnConsumablesDetailsRepository;
import com.tip.util.DatatypeCommonUtility;
import com.tip.util.EstimationConstant;

@Repository
public class FetchEstnConsumablesDetailsRepositoryImpl implements FetchEstnConsumablesDetailsRepository , CallableStatementCreator{

	
	static final Logger logger = LoggerFactory.getLogger(FetchEstnConsumablesDetailsRepositoryImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private String procedureCall;
	
	private FetchEstnConsumablesRequest fetchEstnConsumablesRequest;

	public void setFetchEstnConsumablesRequest(FetchEstnConsumablesRequest fetchEstnConsumablesRequest) {
		this.fetchEstnConsumablesRequest = fetchEstnConsumablesRequest;
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> getConsumablesDetails(FetchEstnConsumablesRequest fetchEstnConsumablesRequest) {
		Map<String, Object> resultMap = null;
		try {
			SqlParameter estimationIdParam = new SqlParameter(Types.NUMERIC);
			SqlParameter langIdParam = new SqlParameter(Types.VARCHAR);
			

			List paramList = new ArrayList();
			paramList.add(estimationIdParam);
			paramList.add(langIdParam);
			

FetchEstnConsumablesDetailsRepositoryImpl fetchEstnConsumablesDetailsRepositoryImpl = new FetchEstnConsumablesDetailsRepositoryImpl();
fetchEstnConsumablesDetailsRepositoryImpl.fetchEstnConsumablesRequest = fetchEstnConsumablesRequest;
fetchEstnConsumablesDetailsRepositoryImpl.procedureCall = "{call "
					+ EstimationConstant.PROC_FETCH_ESTN_CONSUMABLES_DETAILS + " (?,?)}";
			
			
			resultMap = jdbcTemplate.call(fetchEstnConsumablesDetailsRepositoryImpl, paramList);
					
	} catch (Exception e) {
		logger.error("An error occurred while Validating Estn Part: " + e);
		
	}return resultMap;
		
	}
	
	
	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setBigDecimal(1, fetchEstnConsumablesRequest.getEstimationId());
		DatatypeCommonUtility.checkNull(2, callableStatement, fetchEstnConsumablesRequest.getLangId());
		connection.setAutoCommit(true);
		return callableStatement;
	}
}
