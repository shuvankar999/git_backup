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

import com.tip.estimation.model.EstnTyreAttributeRequest;
import com.tip.estimation.repository.FetchEstnTyreAtrbteRepository;
import com.tip.util.DatatypeCommonUtility;
import com.tip.util.EstimationConstant;

	@Repository
	public class FetchEstnTyreAtrbteRepositoryImpl implements FetchEstnTyreAtrbteRepository , CallableStatementCreator{
	
		
		static final Logger logger = LoggerFactory.getLogger(FetchEstnTyreAtrbteRepositoryImpl.class);
		
		@Autowired
		private JdbcTemplate jdbcTemplate;
	
		private String procedureCall;
		
		private EstnTyreAttributeRequest estnTyreAttributeRequest;
	
		public void setFetchTyreAttributeRequest(EstnTyreAttributeRequest estnTyreAttributeRequest) {
			this.estnTyreAttributeRequest = estnTyreAttributeRequest;
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public Map<String, Object> getTyreAttribute(EstnTyreAttributeRequest estnTyreAttributeRequest) {
			Map<String, Object> resultMap = null;
			 
			try {
				SqlParameter estimationIdParam = new SqlParameter(Types.NUMERIC);
				SqlParameter estnworkorderIdParam = new SqlParameter(Types.INTEGER);
				SqlParameter estnWOTIdParam = new SqlParameter(Types.INTEGER);
				SqlParameter langIdParam = new SqlParameter(Types.INTEGER);

				List paramList = new ArrayList();
				paramList.add(estimationIdParam);
				paramList.add(estnworkorderIdParam);
				paramList.add(estnWOTIdParam);
				paramList.add(langIdParam);

		FetchEstnTyreAtrbteRepositoryImpl fetchEstnTyreAtrbteRepositoryImpl = new FetchEstnTyreAtrbteRepositoryImpl();
			fetchEstnTyreAtrbteRepositoryImpl.estnTyreAttributeRequest = estnTyreAttributeRequest;
			fetchEstnTyreAtrbteRepositoryImpl.procedureCall = "{call "
						+ EstimationConstant.PROC_FETCH_ESTN_TYRE_ATTRIBUTE+ "(?,?,?,?)}";
				
			resultMap = jdbcTemplate.call((CallableStatementCreator) fetchEstnTyreAtrbteRepositoryImpl, paramList);
	
			} catch (Exception e) {
				logger.error("An error occurred while fetching the estimation Tyre Attributes: " + e);
			}

			return resultMap;
		}

		@Override
		public CallableStatement createCallableStatement(Connection connection) throws SQLException {
			connection.setAutoCommit(false);
			CallableStatement callableStatement = connection.prepareCall(procedureCall);
			callableStatement.setBigDecimal(1, estnTyreAttributeRequest.getEstimationId());
			DatatypeCommonUtility.checkNull(2, callableStatement, estnTyreAttributeRequest.getEstnworkorderId());
			DatatypeCommonUtility.checkNull(3, callableStatement, estnTyreAttributeRequest.getEstnWOTId());
			DatatypeCommonUtility.checkNull(4, callableStatement, estnTyreAttributeRequest.getLangId());
			connection.setAutoCommit(true);
			return callableStatement;
		}
	
	}
