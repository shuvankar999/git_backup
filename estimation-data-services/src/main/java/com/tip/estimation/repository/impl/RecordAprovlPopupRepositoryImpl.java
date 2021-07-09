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

import com.tip.estimation.model.RecordAprovlPopupRequest;
import com.tip.estimation.repository.RecordAprovlPopupRepository;
import com.tip.util.EstimationConstant;

@Repository
public class RecordAprovlPopupRepositoryImpl implements RecordAprovlPopupRepository,CallableStatementCreator{
	
	static final Logger logger = LoggerFactory.getLogger(RecordAprovlPopupRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;

	private RecordAprovlPopupRequest recordAprovlPopupRequest;

	public void setRecordAprovlRequest(RecordAprovlPopupRequest recordAprovlPopupRequest) {
		this.recordAprovlPopupRequest = recordAprovlPopupRequest;
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Object> getPopupList(RecordAprovlPopupRequest recordAprovlPopupRequest) {
		
		Map<String, Object> resultMap = null;
		try {
			SqlParameter estimationIdParam = new SqlParameter(Types.NUMERIC);
			
			List paramList = new ArrayList();
			paramList.add(estimationIdParam);
			
			RecordAprovlPopupRepositoryImpl recordAprovlPopupRepositoryImpl = new RecordAprovlPopupRepositoryImpl();
			recordAprovlPopupRepositoryImpl.recordAprovlPopupRequest = recordAprovlPopupRequest;
			recordAprovlPopupRepositoryImpl.procedureCall = "{call "
					+ EstimationConstant.PROC_ESTN_RECORD_APPROVAL_POPUP + "(?)}";
			resultMap = jdbcTemplate.call(recordAprovlPopupRepositoryImpl, paramList);
		} catch (Exception e) {
			logger.error("An error occurred while fetching estimation popup lists: " , e);
		}
		return resultMap;
	}
	
	
	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setBigDecimal(1, recordAprovlPopupRequest.getEstimationId());
		connection.setAutoCommit(true);
		return callableStatement;
	}

}
