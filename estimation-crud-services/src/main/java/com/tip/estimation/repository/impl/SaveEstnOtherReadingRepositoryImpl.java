package com.tip.estimation.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tip.estimation.model.SaveEstnOtherReadingRequest;
import com.tip.estimation.repository.SaveEstnOtherReadingRepository;
import com.tip.estimation.util.DatatypeCommonUtility;
import com.tip.estimation.util.EstimationConstant;

@Repository
public class SaveEstnOtherReadingRepositoryImpl implements SaveEstnOtherReadingRepository, CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(SaveEstnOtherReadingRepositoryImpl.class);

	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	private SaveEstnOtherReadingRequest saveEstnOtherReadingRequest;


	public void setSaveEstnOtherReadingRequest(SaveEstnOtherReadingRequest saveEstnOtherReadingRequest) {
		this.saveEstnOtherReadingRequest = saveEstnOtherReadingRequest;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> saveEstnOtherReading(SaveEstnOtherReadingRequest saveEstnOtherReadingRequest) {
		Map<String, Object> resultMap= null;
		
		try {
		SaveEstnOtherReadingRepositoryImpl saveEstnOtherReadingRepositoryImpl = new SaveEstnOtherReadingRepositoryImpl();
		saveEstnOtherReadingRepositoryImpl.saveEstnOtherReadingRequest = saveEstnOtherReadingRequest;

		saveEstnOtherReadingRepositoryImpl.procedureCall = "{call "+ EstimationConstant.PROC_ESTIMATION_SAVE_OTHER_READING + " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		
		
		resultMap = jdbcTemplate.call(saveEstnOtherReadingRepositoryImpl, new ArrayList());
		}catch (Exception e) {
			logger.error("An error occurred while saving Estimation Readings: " + e);
			
		
		}
		
		return resultMap;
				
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setBigDecimal(1, saveEstnOtherReadingRequest.getEstimationId());
		DatatypeCommonUtility.checkNull(2, callableStatement, saveEstnOtherReadingRequest.getHubReading());
		DatatypeCommonUtility.checkNull(3, callableStatement,saveEstnOtherReadingRequest.getElectricHours());
		DatatypeCommonUtility.checkNull(4, callableStatement, saveEstnOtherReadingRequest.getDeiselHours());
		DatatypeCommonUtility.checkNull(5, callableStatement, saveEstnOtherReadingRequest.getFuel());
		callableStatement.setString(6, saveEstnOtherReadingRequest.getLoaded());
		DatatypeCommonUtility.checkNull(7, callableStatement, saveEstnOtherReadingRequest.getFuelCapaicty());
		callableStatement.setString(8, saveEstnOtherReadingRequest.getFuelMeterBroken());
		callableStatement.setString(9, saveEstnOtherReadingRequest.getOperatorName());
		callableStatement.setString(10,  saveEstnOtherReadingRequest.getNoHubodometer());
		callableStatement.setString(11,  saveEstnOtherReadingRequest.getHubodometerBroken());
		callableStatement.setString(12, saveEstnOtherReadingRequest.getMotDocDate());
		callableStatement.setString(13, saveEstnOtherReadingRequest.getFridgeManufacturer());
		callableStatement.setString(14, saveEstnOtherReadingRequest.getFridgeModel());
		callableStatement.setString(15, saveEstnOtherReadingRequest.getFridgeSerialNr());
		callableStatement.setString(16, saveEstnOtherReadingRequest.getMonthOfManufacturer());
		DatatypeCommonUtility.checkNull(17, callableStatement, saveEstnOtherReadingRequest.getYearOfManufacturer());
		callableStatement.setString(18, saveEstnOtherReadingRequest.getPressureTestDate());
		callableStatement.setString(19, saveEstnOtherReadingRequest.getTailliftManufacturer());
		callableStatement.setString(20, saveEstnOtherReadingRequest.getTailliftModel());
		callableStatement.setString(21, saveEstnOtherReadingRequest.getTailliftSerialNr());
		callableStatement.setString(22, saveEstnOtherReadingRequest.getSsoId());
		callableStatement.registerOutParameter(23, Types.VARCHAR);
		connection.setAutoCommit(true);
		return callableStatement;

	}

}
