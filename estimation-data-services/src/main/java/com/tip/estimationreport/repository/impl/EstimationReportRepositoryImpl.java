package com.tip.estimationreport.repository.impl;

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

import com.tip.estimationreport.model.EstnReportRequest;
import com.tip.estimationreport.repository.EstimationReportRepository;
import com.tip.util.DatatypeCommonUtility;
import com.tip.util.EstimationConstant;

	@Repository
	public class EstimationReportRepositoryImpl implements EstimationReportRepository , CallableStatementCreator{
		
		static final Logger logger = LoggerFactory.getLogger(EstimationReportRepositoryImpl.class);

		@Autowired
		private JdbcTemplate jdbcTemplate;

		private String procedureCall;
		private EstnReportRequest estnReportRequest;
		
		public void setEmailReportRequest(EstnReportRequest estnReportRequest) {
			this.estnReportRequest = estnReportRequest;
		}
		
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public Map<String, Object> getReportData(EstnReportRequest estnReportRequest) {
			
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
				

				EstimationReportRepositoryImpl estnReportRepositoryImpl = new EstimationReportRepositoryImpl();
				estnReportRepositoryImpl.estnReportRequest = estnReportRequest;
				estnReportRepositoryImpl.procedureCall = "{call " + EstimationConstant.PROC_FETCH_ESTIMATION_REPORT + "(?, ?, ?, ?)}";

				resultMap = jdbcTemplate.call(estnReportRepositoryImpl, paramList);

				for (Map.Entry<String, Object> entry : resultMap.entrySet())
				{
					if (("#result-set-1").equalsIgnoreCase(entry.getKey())) {
						estnReportResponseMap.put("headerDetailsData", entry.getValue());
					}else if (("#result-set-2").equalsIgnoreCase(entry.getKey())) {
						estnReportResponseMap.put("labourOrderDataList", entry.getValue());
					}else if (("#result-set-3").equalsIgnoreCase(entry.getKey())) {
						estnReportResponseMap.put("labourOrderTaskDataList", entry.getValue());
					}else if (("#result-set-4").equalsIgnoreCase(entry.getKey())) {
						estnReportResponseMap.put("partsDataList", entry.getValue());
					}else if (("#result-set-5").equalsIgnoreCase(entry.getKey())) {
						estnReportResponseMap.put("bundleDataList", entry.getValue());
					}else if (("#result-set-6").equalsIgnoreCase(entry.getKey())) {
						estnReportResponseMap.put("consumablesList", entry.getValue());
					}else if (("#result-set-7").equalsIgnoreCase(entry.getKey())) {
						estnReportResponseMap.put("imageList", entry.getValue());
					}else if (("#result-set-10").equalsIgnoreCase(entry.getKey())) {
						estnReportResponseMap.put("hLabelList", entry.getValue());
					}else if (("#result-set-11").equalsIgnoreCase(entry.getKey())) {
						estnReportResponseMap.put("adminFeesList", entry.getValue());
					}
				}

			} catch (Exception e) {
				logger.error("An error occurred while fetching Estimation report detail: " + e);
			}
			return estnReportResponseMap;
		}
		
		
		@Override
		public CallableStatement createCallableStatement(Connection connection) throws SQLException {
			
			connection.setAutoCommit(false);
			CallableStatement callableStatement = connection.prepareCall(procedureCall);
			callableStatement.setBigDecimal(1, estnReportRequest.getEstimationId());
			DatatypeCommonUtility.checkNull(2, callableStatement, estnReportRequest.getVersion());
			DatatypeCommonUtility.checkNull(3, callableStatement, estnReportRequest.getSupplementary());
			DatatypeCommonUtility.checkNull(4, callableStatement, estnReportRequest.getLangId());
			connection.setAutoCommit(true);
			return callableStatement;
		}
	
	}
