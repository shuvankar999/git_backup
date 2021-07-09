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
import com.tip.estimation.repository.AddPipelineRepository;
import com.tip.estimation.util.EstimationConstant;

@Repository
public class AddPipelineRepositoryImpl implements AddPipelineRepository, CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(AddPipelineRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	private PipelineRequest pipelineRequest;


	@SuppressWarnings({ "unchecked", "rawtypes" })

	@Override
	public Map<String, Object> addToPipeline(PipelineRequest pipelineRequest) {

		Map<String, Object> resultMap = null;
		try {

			AddPipelineRepositoryImpl addPipelineRepositoryImpl = new AddPipelineRepositoryImpl();
			addPipelineRepositoryImpl.pipelineRequest = pipelineRequest;
			addPipelineRepositoryImpl.procedureCall = "{call " + EstimationConstant.PROC_ESTIMATION_ADD_TO_PIPE
					+ "(?,?,?)}";
			resultMap = jdbcTemplate.call((CallableStatementCreator) addPipelineRepositoryImpl, new ArrayList());

		} catch (Exception e) {
			logger.error("An error occurred: " + e);
		}

		return resultMap;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setBigDecimal(1, pipelineRequest.getEstimationId());
		callableStatement.setString(2, pipelineRequest.getBranchId());
		callableStatement.setString(3, pipelineRequest.getSsoId());
		connection.setAutoCommit(true);
		return callableStatement;
	}
}
