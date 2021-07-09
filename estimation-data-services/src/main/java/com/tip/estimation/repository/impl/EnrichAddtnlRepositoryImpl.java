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

import com.tip.estimation.model.EnrichAddtionalRequest;
import com.tip.estimation.repository.EnrichAddtnlRepository;
import com.tip.util.DatatypeCommonUtility;
import com.tip.util.EstimationConstant;

@Repository
public class EnrichAddtnlRepositoryImpl implements EnrichAddtnlRepository, CallableStatementCreator{
	
	static final Logger logger = LoggerFactory.getLogger(EnrichAddtnlRepositoryImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	private EnrichAddtionalRequest enrichAddtionalRequest;

	public void setAddtnlEnrichDetails(EnrichAddtionalRequest enrichAddtionalRequest) {
		this.enrichAddtionalRequest = enrichAddtionalRequest;
	}
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> fetchAddtnlEnrichDetails(EnrichAddtionalRequest enrichAddtionalRequest) {
		
		Map<String, Object> resultMap = null;
		try {
			SqlParameter estimationIdParam = new SqlParameter(Types.NUMERIC);
			SqlParameter langIdParam = new SqlParameter(Types.INTEGER);

			List paramList = new ArrayList();
			paramList.add(estimationIdParam);
			paramList.add(langIdParam);

			EnrichAddtnlRepositoryImpl enrichAddtnlRepositoryImpl = new EnrichAddtnlRepositoryImpl();
			enrichAddtnlRepositoryImpl.enrichAddtionalRequest = enrichAddtionalRequest;
			enrichAddtnlRepositoryImpl.procedureCall = "{call "
					+ EstimationConstant.PROC_FETCH_ESTN_ENRICH_ADDTIONAL_DETAILS + "(?,?)}";
			resultMap = jdbcTemplate.call((CallableStatementCreator) enrichAddtnlRepositoryImpl, paramList);
			
			
		} catch (Exception e) {
			logger.error("An error occurred while fetching the Estimation Enrich Addtional details: " + e);
		}

		return resultMap;
	}


	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setBigDecimal(1, enrichAddtionalRequest.getEstimationId());
		DatatypeCommonUtility.checkNull(2, callableStatement, enrichAddtionalRequest.getLangId());
		connection.setAutoCommit(true);
		return callableStatement;
	}
}
