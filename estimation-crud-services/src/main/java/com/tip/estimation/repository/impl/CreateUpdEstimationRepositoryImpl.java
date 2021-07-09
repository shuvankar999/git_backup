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

import com.tip.estimation.model.EstimationRequest;
import com.tip.estimation.repository.CreateUpdEstimationRepository;
import com.tip.estimation.util.DatatypeCommonUtility;
import com.tip.estimation.util.EstimationConstant;

@Repository
public class CreateUpdEstimationRepositoryImpl implements CreateUpdEstimationRepository, CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(CreateUpdEstimationRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	private EstimationRequest estimationRequest;


	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Object> createEstimation(EstimationRequest estimationRequest){

		Map<String, Object> resultMap = null;
		try {

			CreateUpdEstimationRepositoryImpl customerSearchRepositoryImpl = new CreateUpdEstimationRepositoryImpl();
			customerSearchRepositoryImpl.estimationRequest = estimationRequest;
			customerSearchRepositoryImpl.procedureCall = "{call " + EstimationConstant.PROC_ESTIMATION_UPD_CREATE
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			resultMap = jdbcTemplate.call((CallableStatementCreator) customerSearchRepositoryImpl, new ArrayList());

		} catch (Exception e) {
			logger.error("An error occurred: " + e);
		}

		return resultMap;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setBigDecimal(1, estimationRequest.getEstimationId());
		DatatypeCommonUtility.checkNull(2, callableStatement, estimationRequest.getAssetNr());
		DatatypeCommonUtility.checkNull(3, callableStatement, estimationRequest.getEstnStatusId());
		callableStatement.setString(4, estimationRequest.getBranchId());
		DatatypeCommonUtility.checkNull(5, callableStatement, estimationRequest.getCompanyNr());
		callableStatement.setString(6, estimationRequest.getCustAssetRefNr());
		callableStatement.setString(7, estimationRequest.getAssetCatgry());
		callableStatement.setString(8, estimationRequest.getRegNr());
		callableStatement.setString(9, estimationRequest.getChasisNr());
		callableStatement.setBigDecimal(10, estimationRequest.getIntchKey());
		DatatypeCommonUtility.checkNull(11, callableStatement, estimationRequest.getCustNr());
		DatatypeCommonUtility.checkNull(12, callableStatement, estimationRequest.getCustCmpNr());
		callableStatement.setString(13, estimationRequest.getCustName());
		callableStatement.setString(14, estimationRequest.getContactName());
		callableStatement.setString(15, estimationRequest.getEmailId());
		callableStatement.setString(16, estimationRequest.getPhoneNr());
		callableStatement.setString(17, estimationRequest.getCustRefNr());
		callableStatement.setString(18, estimationRequest.getAssetLoc());
		callableStatement.setString(19, estimationRequest.getEstInspLoc());
		callableStatement.setString(20, estimationRequest.getEstnTitle());
		callableStatement.setString(21, estimationRequest.getCustNotes());
		callableStatement.setString(22, estimationRequest.getInternalNotes());
		callableStatement.setString(23, estimationRequest.getSsoId());
		connection.setAutoCommit(true);
		return callableStatement;
	}
}
