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

import com.tip.estimation.model.EstimationLines;
import com.tip.estimation.repository.FetchEstimationLinesRepository;
import com.tip.util.DatatypeCommonUtility;
import com.tip.util.EstimationConstant;

@Repository
public class FetchEstimationLinesRepositoryImpl implements FetchEstimationLinesRepository,CallableStatementCreator{

	static final Logger logger = LoggerFactory.getLogger(FetchEstimationLinesRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	private EstimationLines estnLines;

	public void setFetchEstnLines(EstimationLines estnLines) {
		this.estnLines = estnLines;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> fetchEstLines(EstimationLines estnLines) {
		Map<String, Object> resultMap = null;
		
		try {
			SqlParameter estimationId = new SqlParameter(Types.NUMERIC);
			SqlParameter langId = new SqlParameter(Types.INTEGER);

			List paramList = new ArrayList();
			paramList.add(estimationId);
			paramList.add(langId);

			FetchEstimationLinesRepositoryImpl fetchEstimationLinesRepositoryImpl = new FetchEstimationLinesRepositoryImpl();
			fetchEstimationLinesRepositoryImpl.estnLines = estnLines;
			fetchEstimationLinesRepositoryImpl.procedureCall = "{call "
					+ EstimationConstant.PROC_FETCH_ESTIMATION_LINES + "(?,?)}";
			resultMap = jdbcTemplate.call((CallableStatementCreator) fetchEstimationLinesRepositoryImpl, paramList);
			
			
		} catch (Exception e) {
			logger.error("An error occurred while fetching the Estimation Lines: " + e);
		}

		return resultMap;
	}

	
	
	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setBigDecimal(1, estnLines.getEstimationId());
		DatatypeCommonUtility.checkNull(2, callableStatement, estnLines.getLangId());
		connection.setAutoCommit(true);
		return callableStatement;
	}


}
