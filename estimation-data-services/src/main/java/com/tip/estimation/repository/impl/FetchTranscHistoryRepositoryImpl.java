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

import com.tip.estimation.model.InvoiceRequest;
import com.tip.estimation.repository.FetchTranscHistoryRepository;
import com.tip.util.EstimationConstant;

@Repository
public class FetchTranscHistoryRepositoryImpl implements FetchTranscHistoryRepository, CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(FetchTranscHistoryRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	private InvoiceRequest invoiceRequest;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Object> getTransaction(InvoiceRequest invoiceRequest) {

		Map<String, Object> resultMap = null;
		
		try {

			SqlParameter estIdSqlParam = new SqlParameter(Types.DECIMAL);

			List paramList = new ArrayList();
			paramList.add(estIdSqlParam);

			FetchTranscHistoryRepositoryImpl fetchTranscHistoryRepositoryImpl = new FetchTranscHistoryRepositoryImpl();
			fetchTranscHistoryRepositoryImpl.invoiceRequest = invoiceRequest;
			fetchTranscHistoryRepositoryImpl.procedureCall = "{call " + EstimationConstant.PROC_ESTIMATION_TRANSACTION_HISTORY
					+ "(?)}";
			resultMap = jdbcTemplate.call((CallableStatementCreator) fetchTranscHistoryRepositoryImpl, paramList);

		} catch (Exception e) {
			logger.error("An error occurred: " + e);
		}

		return resultMap;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setBigDecimal(1, invoiceRequest.getEstimationId());
		connection.setAutoCommit(true);
		return callableStatement;
	}
}
