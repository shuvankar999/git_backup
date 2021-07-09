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

import com.tip.estimation.model.FetchReadingsRequest;
import com.tip.estimation.repository.FetchEstnReadingsRepository;
import com.tip.util.DatatypeCommonUtility;
import com.tip.util.EstimationConstant;

@Repository
public class FetchEstnReadingsRepositoryImpl implements FetchEstnReadingsRepository,CallableStatementCreator {
	
	static final Logger logger = LoggerFactory.getLogger(FetchEstnReadingsRepositoryImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	private FetchReadingsRequest fetchReadingsRequest;

	public void setFetchListsRequest(FetchReadingsRequest fetchReadingsRequest) {
		this.fetchReadingsRequest = fetchReadingsRequest;
	}

	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> getReadings(FetchReadingsRequest fetchReadingsRequest) {
		Map<String, Object> resultMap = null;
		 
		try {
			SqlParameter estimationId = new SqlParameter(Types.NUMERIC);
			SqlParameter langId = new SqlParameter(Types.INTEGER);

			List paramList = new ArrayList();
			paramList.add(estimationId);
			paramList.add(langId);

			FetchEstnReadingsRepositoryImpl fetchEstnReadingsRepositoryImpl = new FetchEstnReadingsRepositoryImpl();
			fetchEstnReadingsRepositoryImpl.fetchReadingsRequest = fetchReadingsRequest;
			fetchEstnReadingsRepositoryImpl.procedureCall = "{call "
					+ EstimationConstant.PROC_FETCH_ESTIMATION_READINGS+ "(?,?)}";
			resultMap = jdbcTemplate.call((CallableStatementCreator) fetchEstnReadingsRepositoryImpl, paramList);
			
			
		} catch (Exception e) {
			logger.error("An error occurred while fetching the estimation Readings: " + e);
		}

		return resultMap;
	}

		
	
	
	
	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setBigDecimal(1, fetchReadingsRequest.getEstimationId());
		DatatypeCommonUtility.checkNull(2, callableStatement, fetchReadingsRequest.getLangId());
		connection.setAutoCommit(true);
		return callableStatement;
	}

}
