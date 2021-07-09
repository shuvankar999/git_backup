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

import com.tip.estimation.model.ConversionRequest;
import com.tip.estimation.repository.ConvrsnToWpRepository;
import com.tip.util.EstimationConstant;


	@Repository
	public class ConvrsnToWpRepositoryImpl implements ConvrsnToWpRepository,CallableStatementCreator{
		
		static final Logger logger = LoggerFactory.getLogger(ConvrsnToWpRepositoryImpl.class);

		@Autowired
		private JdbcTemplate jdbcTemplate;

		private String procedureCall;

		private ConversionRequest conversionRequest;

		public void setFetchEstimationPopupDtailsRequest(ConversionRequest conversionRequest) {
			this.conversionRequest = conversionRequest;
		}

		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public Map<String, Object> getConvrsnDetails(ConversionRequest conversionRequest) {

			Map<String, Object> resultMap = null;
			try {
				SqlParameter estimationIdParam = new SqlParameter(Types.NUMERIC);
				SqlParameter ssoIdParam = new SqlParameter(Types.VARCHAR);
				
				List paramList = new ArrayList();
				paramList.add(estimationIdParam);
				paramList.add(ssoIdParam);
				ConvrsnToWpRepositoryImpl convrsnToWpRepositoryImpl = new ConvrsnToWpRepositoryImpl();
				convrsnToWpRepositoryImpl.conversionRequest = conversionRequest;
				convrsnToWpRepositoryImpl.procedureCall = "{call "
						+ EstimationConstant.PROC_CONVRSN_TO_WORKPACK_DETAILS + "(?,?)}";
				resultMap = jdbcTemplate.call(convrsnToWpRepositoryImpl, paramList);
			} catch (Exception e) {
				logger.error("An error occurred while fetching WorkPack conversion details: " , e);
			}
			return resultMap;
		}
		
		
		@Override
		public CallableStatement createCallableStatement(Connection connection) throws SQLException {
			
			connection.setAutoCommit(false);
			CallableStatement callableStatement = connection.prepareCall(procedureCall);
			callableStatement.setBigDecimal(1, conversionRequest.getEstimationId());
			callableStatement.setString(2, conversionRequest.getSsoId());
			connection.setAutoCommit(true);
			return callableStatement;
		}
	}
