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

import com.tip.estimation.model.EstRequest;
import com.tip.estimation.repository.FetchOpenEstimationRepository;
import com.tip.util.DatatypeCommonUtility;
import com.tip.util.EstimationConstant;

@Repository
public class FetchOpenEstimationRepositoryImpl implements FetchOpenEstimationRepository, CallableStatementCreator{

	static final Logger logger = LoggerFactory.getLogger(FetchOpenEstimationRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	
	private EstRequest estRequest;
	
	@SuppressWarnings({ "unchecked", "rawtypes"})
	@Override
	public Map<String, Object> getOpenEstimation(EstRequest estRequest) {
		Map<String, Object> resultMap = null;
		try {
			SqlParameter assetNr = new SqlParameter(Types.INTEGER);
			

			List paramList = new ArrayList();
			paramList.add(assetNr);
			
			FetchOpenEstimationRepositoryImpl fetchOpenEstimationRepositoryImpl = new FetchOpenEstimationRepositoryImpl();
			fetchOpenEstimationRepositoryImpl.estRequest = estRequest;
			fetchOpenEstimationRepositoryImpl.procedureCall = "{call " + EstimationConstant.PROC_ESTIMATION_FETCH_OPEN+ "(?)}";
			resultMap = jdbcTemplate.call((CallableStatementCreator) fetchOpenEstimationRepositoryImpl, paramList);

		} catch (Exception e) {
			logger.error("An error occurred while fetching the Equipment Details: " + e);
		}

		return resultMap;
	}
	
	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		DatatypeCommonUtility.checkNull(1, callableStatement, estRequest.getAssetNr());
		connection.setAutoCommit(true);
		return callableStatement;
	}	

}
