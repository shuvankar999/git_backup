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

import com.tip.estimation.model.VersionDetailsRequest;
import com.tip.estimation.repository.EstnVersnDetailsRepository;
import com.tip.util.EstimationConstant;

	@Repository
	public class EstnVersnDetailsRepositoryImpl implements EstnVersnDetailsRepository, CallableStatementCreator{
		
		static final Logger logger = LoggerFactory.getLogger(EstnVersnDetailsRepositoryImpl.class);

		@Autowired
		private JdbcTemplate jdbcTemplate;
		
		private String procedureCall;
		
		private VersionDetailsRequest versionDetailsRequest;

		public void setValidatePartRequest(VersionDetailsRequest versionDetailsRequest) {
			this.versionDetailsRequest = versionDetailsRequest;
		}

		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public Map<String, Object> getVersnDetails(VersionDetailsRequest versionDetailsRequest) {
			Map<String, Object> resultMap = null;
			try {
				SqlParameter estimationIdParam = new SqlParameter(Types.NUMERIC);
				SqlParameter errorCdParam = new SqlParameter(Types.VARCHAR);

				List paramList = new ArrayList();
				paramList.add(estimationIdParam);				
				paramList.add(errorCdParam);

				EstnVersnDetailsRepositoryImpl estnVersnDetailsRepositoryImpl = new EstnVersnDetailsRepositoryImpl();
				estnVersnDetailsRepositoryImpl.versionDetailsRequest = versionDetailsRequest;
				estnVersnDetailsRepositoryImpl.procedureCall = "{call "
						+ EstimationConstant.PROC_FETCH_VERSION_DETAILS + " (?,?)}";
				
				
				resultMap = jdbcTemplate.call(estnVersnDetailsRepositoryImpl, paramList);
						
		} catch (Exception e) {
			logger.error("An error occurred while fetching version details: " + e);
			
		}
			return resultMap;
		}
		
		
		@Override
		public CallableStatement createCallableStatement(Connection connection) throws SQLException {
			connection.setAutoCommit(false);
			CallableStatement callableStatement = connection.prepareCall(procedureCall);
			callableStatement.setBigDecimal(1, versionDetailsRequest.getEstimationId());
			callableStatement.setString(2, versionDetailsRequest.getErrorCd());
			connection.setAutoCommit(true);
			return callableStatement;
		}
		

	
	}
