package com.tip.estimation.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tip.estimation.model.PipelineRequest;
import com.tip.estimation.model.TransactionRequest;
import com.tip.estimation.repository.AddTransactionRepository;
import com.tip.estimation.util.DatatypeCommonUtility;
import com.tip.estimation.util.EstimationConstant;

@Repository
public class AddTransactionRepositoryImpl implements AddTransactionRepository, CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(AddTransactionRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	private TransactionRequest transactionRequest;


	@SuppressWarnings({ "unchecked", "rawtypes" })

	@Override
	public Map<String, Object> addTransaction(TransactionRequest transactionRequest){

		Map<String, Object> resultMap = null;
		try {

			AddTransactionRepositoryImpl addTransactionRepositoryImpl = new AddTransactionRepositoryImpl();
			addTransactionRepositoryImpl.transactionRequest = transactionRequest;
			addTransactionRepositoryImpl.procedureCall = "{call " + EstimationConstant.PROC_ESTIMATION_ADD_TRANSACTION
					+ "(?,?,?,?)}";
			resultMap = jdbcTemplate.call((CallableStatementCreator) addTransactionRepositoryImpl, new ArrayList());

		} catch (Exception e) {
			logger.error("An error occurred: " + e);
		}

		return resultMap;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setBigDecimal(1, transactionRequest.getEstimationId());
		callableStatement.setString(2, transactionRequest.getTransCd());
		DatatypeCommonUtility.checkNull(3, callableStatement, transactionRequest.getAmount());
		callableStatement.setString(4, transactionRequest.getSsoId());
		connection.setAutoCommit(true);
		return callableStatement;
	}
}
