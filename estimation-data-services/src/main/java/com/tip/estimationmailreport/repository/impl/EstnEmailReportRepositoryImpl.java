package com.tip.estimationmailreport.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.tip.estimationmailreport.model.EstnEmailReportRequest;
import com.tip.estimationmailreport.repository.EstnEmailReportRepository;
import com.tip.util.DatatypeCommonUtility;
import com.tip.util.EstimationConstant;

	@Repository
	public class EstnEmailReportRepositoryImpl implements EstnEmailReportRepository , CallableStatementCreator{
		
		static final Logger logger = LoggerFactory.getLogger(EstnEmailReportRepositoryImpl.class);

		@Autowired
		private JdbcTemplate jdbcTemplate;

		private String procedureCall;
		private EstnEmailReportRequest estnEmailReportRequest;
		
		public void setEmailReportRequest(EstnEmailReportRequest estnEmailReportRequest) {
			this.estnEmailReportRequest = estnEmailReportRequest;
		}
		
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public Map<String, Object> sendEmail(EstnEmailReportRequest estnEmailReportRequest) {
			
			Map<String, Object> resultMap = null;
			Map<String, Object> estnReportResponseMap = new HashMap<>();
			try {
				SqlParameter estimationIdSQLparam = new SqlParameter(Types.DECIMAL);
				SqlParameter versionSQLParam = new SqlParameter(Types.INTEGER);
				SqlParameter supplementarySQLParam = new SqlParameter(Types.INTEGER);
				SqlParameter langIdSQLParam = new SqlParameter(Types.INTEGER);
				

				List paramList = new ArrayList();
				paramList.add(estimationIdSQLparam);
				paramList.add(versionSQLParam);
				paramList.add(supplementarySQLParam);
				paramList.add(langIdSQLParam);
				

				EstnEmailReportRepositoryImpl estnEmailReportRepositoryImpl = new EstnEmailReportRepositoryImpl();
				estnEmailReportRepositoryImpl.estnEmailReportRequest = estnEmailReportRequest;
				estnEmailReportRepositoryImpl.procedureCall = "{call " + EstimationConstant.PROC_FETCH_ESTN_EMAIL_DETAILS
						+ "(?, ?, ?, ?)}";

				resultMap = jdbcTemplate.call(estnEmailReportRepositoryImpl, paramList);

				for (Map.Entry<String, Object> entry : resultMap.entrySet())
				{
					if (("#result-set-1").equalsIgnoreCase(entry.getKey())) {
						estnReportResponseMap.put("emailDetailsMap", entry.getValue());
					}
				}

			} catch (Exception e) {
				StackTraceElement stackTraceElement = e.getStackTrace()[0];
				logger.error("An error occurred while fetching email details of estimation report: " + e);
				logger.error("At Line:" + stackTraceElement.getClassName() + ":" + stackTraceElement.getLineNumber());
			}
			return estnReportResponseMap;
		}
		
		
		@Override
		public CallableStatement createCallableStatement(Connection connection) throws SQLException {
			
			connection.setAutoCommit(false);
			CallableStatement callableStatement = connection.prepareCall(procedureCall);
			callableStatement.setBigDecimal(1, estnEmailReportRequest.getEstimationId());
			DatatypeCommonUtility.checkNull(2, callableStatement, estnEmailReportRequest.getVersion());
			DatatypeCommonUtility.checkNull(3, callableStatement, estnEmailReportRequest.getSupplementary());
			DatatypeCommonUtility.checkNull(4, callableStatement, estnEmailReportRequest.getLangId());
			connection.setAutoCommit(true);
			return callableStatement;
		}
	
	}
