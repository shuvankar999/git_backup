package com.tip.brakereading.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.tip.asset.util.AssetConstants;
import com.tip.asset.util.DatatypeCommonUtility;
import com.tip.brakereading.model.BrakeReadingRequest;
import com.tip.brakereading.model.BrakeReadingResponse;
import com.tip.brakereading.repository.BrakeReadingRepository;

@Repository
public class BrakeReadingRepositoryImpl implements BrakeReadingRepository ,CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(BrakeReadingRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private BrakeReadingRequest brakeReadingRequest;

	private String procedureCall;

	@Autowired
	public void setDataSource(DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	
	@Override
	public BrakeReadingResponse fetchBrakeReadingDetails(BrakeReadingRequest brakeReadingRequest) {

		this.brakeReadingRequest = brakeReadingRequest;
		BrakeReadingResponse brakeReadingResponse = new BrakeReadingResponse();

		Map<String, Object> brakeReadingMap = new HashMap<>();

		try {
			Map<String, Object> resultMap;

			SqlParameter workPackNrSQLparam = new SqlParameter(Types.DECIMAL);
			SqlParameter workOrderNrSQLparam = new SqlParameter(Types.INTEGER);
			SqlParameter workOrderTaskNrSQLparam = new SqlParameter(Types.INTEGER);
			SqlParameter languageIdSQLparam = new SqlParameter(Types.INTEGER);
			SqlParameter errorCdSQLparam = new SqlParameter(Types.VARCHAR);

			List paramList = new ArrayList();
			paramList.add(workPackNrSQLparam);
			paramList.add(workOrderNrSQLparam);
			paramList.add(workOrderTaskNrSQLparam);
			paramList.add(languageIdSQLparam);
			paramList.add(errorCdSQLparam);
			
			BrakeReadingRepositoryImpl lBrakeReadingRepositoryImpl = new BrakeReadingRepositoryImpl();
			lBrakeReadingRepositoryImpl.brakeReadingRequest = brakeReadingRequest;
			lBrakeReadingRepositoryImpl.procedureCall = "{call " + AssetConstants.PROC_FETCH_BRAKE_INSP_READING + "(?, ?, ?, ?, ?)}";

			resultMap = jdbcTemplate.call(lBrakeReadingRepositoryImpl, paramList);
			if(resultMap!=null)
			{
				setResult(resultMap,brakeReadingMap);
			}
		} catch (Exception e) {
			StackTraceElement stackTraceElement = e.getStackTrace()[0];
			logger.error("An error occurred while fetching brake reading details : " + e);
			logger.error("At Line: " + stackTraceElement.getClassName()+":" + stackTraceElement.getLineNumber());
			brakeReadingMap.put(AssetConstants.ERROR_CD, e.getMessage());
		}
		
		brakeReadingResponse.setBrakeReadingMap(brakeReadingMap);
		return brakeReadingResponse;
	}
	
	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);		
		callableStatement.setBigDecimal(1, brakeReadingRequest.getWorkPackNr());
		DatatypeCommonUtility.checkNull(2, callableStatement, brakeReadingRequest.getWorkOrderNr());
		DatatypeCommonUtility.checkNull(3, callableStatement, brakeReadingRequest.getWorkOrderTaskNr());
		DatatypeCommonUtility.checkNull(4, callableStatement, brakeReadingRequest.getLanguageId());
		callableStatement.setString(5, "NULL");
		connection.setAutoCommit(true);
		return callableStatement;
	}

	public void setBrakeReadingRequest(BrakeReadingRequest brakeReadingRequest) {
		this.brakeReadingRequest = brakeReadingRequest;
	}
	
	private void setResult(Map<String, Object> resultMap, Map<String, Object> brakeReadingMap){
		for (Map.Entry<String, Object> entry : resultMap.entrySet())
		{
			String key = entry.getKey();
			if (("#result-set-1").equalsIgnoreCase(key)) {
				brakeReadingMap.put("BrakeFailureDetails", entry.getValue());
			} else if (("#result-set-2").equalsIgnoreCase(key)) {
				brakeReadingMap.put("BrakeChecklist", entry.getValue());
			} else if (("#result-set-3").equalsIgnoreCase(key)) {
				brakeReadingMap.put("BrakeReading", entry.getValue());
			} else if (("#result-set-4").equalsIgnoreCase(key)) {
				brakeReadingMap.put("BrakeImage", entry.getValue());
			} else if (AssetConstants.ERROR_CD.equalsIgnoreCase(key)) {
				brakeReadingMap.put(AssetConstants.ERROR_CD, entry.getValue());
			}
		}
	}
}
