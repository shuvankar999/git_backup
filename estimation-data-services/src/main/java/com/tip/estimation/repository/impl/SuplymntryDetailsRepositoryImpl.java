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

import com.tip.estimation.model.SuplymntryRequest;
import com.tip.estimation.repository.SuplymntryDetailsRepository;
import com.tip.util.DatatypeCommonUtility;
import com.tip.util.EstimationConstant;

@Repository
public class SuplymntryDetailsRepositoryImpl implements SuplymntryDetailsRepository,CallableStatementCreator{

	static final Logger logger = LoggerFactory.getLogger(SuplymntryDetailsRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	private SuplymntryRequest suplymntryRequest;

	public void setSupplymentaryDetails(SuplymntryRequest suplymntryRequest) {
		this.suplymntryRequest = suplymntryRequest;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> getSupplymntryDetails(SuplymntryRequest suplymntryRequest) {
			
		Map<String, Object> resultMap = null;
		
		try {
			SqlParameter estimationIdParam = new SqlParameter(Types.NUMERIC);
			SqlParameter versionParam = new SqlParameter(Types.INTEGER);
			SqlParameter supplementaryParam = new SqlParameter(Types.INTEGER);
			SqlParameter langIdParam = new SqlParameter(Types.INTEGER);
			

			List paramList = new ArrayList();
			paramList.add(estimationIdParam);
			paramList.add(versionParam);
			paramList.add(supplementaryParam);
			paramList.add(langIdParam);

			SuplymntryDetailsRepositoryImpl suplymntryDetailsRepositoryImpl = new SuplymntryDetailsRepositoryImpl();
			suplymntryDetailsRepositoryImpl.suplymntryRequest = suplymntryRequest;
			suplymntryDetailsRepositoryImpl.procedureCall = "{call "
					+ EstimationConstant.PROC_FETCH_ESTN_SUPPLYMENTARY_DETAILS + "(?,?,?,?)}";
			resultMap = jdbcTemplate.call((CallableStatementCreator) suplymntryDetailsRepositoryImpl, paramList);
			
			
		} catch (Exception e) {
			logger.error("An error occurred while fetching the Estimation Enrich details: " + e);
		}

		return resultMap;
	}
	
	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setBigDecimal(1, suplymntryRequest.getEstimationId());
		DatatypeCommonUtility.checkNull(2, callableStatement, suplymntryRequest.getVersion());
		DatatypeCommonUtility.checkNull(3, callableStatement, suplymntryRequest.getSupplementary());
		DatatypeCommonUtility.checkNull(4, callableStatement, suplymntryRequest.getLangId());
		connection.setAutoCommit(true);
		return callableStatement;
	}

}
