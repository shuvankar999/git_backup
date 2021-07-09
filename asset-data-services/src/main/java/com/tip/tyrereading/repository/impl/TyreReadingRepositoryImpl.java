package com.tip.tyrereading.repository.impl;

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
import com.tip.tyrereading.model.TyreReadingRequest;
import com.tip.tyrereading.model.TyreReadingResponse;
import com.tip.tyrereading.repository.TyreReadingRepository;


@Repository
public class TyreReadingRepositoryImpl implements TyreReadingRepository ,CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(TyreReadingRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private TyreReadingRequest tyreReadingRequest;
	private String procedureCall;

	@Autowired
	public void setDataSource(DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public TyreReadingResponse fetchTyreReadingDetails(TyreReadingRequest tyreReadingRequest) {
		this.tyreReadingRequest = tyreReadingRequest;

		TyreReadingResponse tyreReadingResponse = new TyreReadingResponse();
		Map<String, Object> tyreReadingMap = new HashMap<>();
		
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
			
			TyreReadingRepositoryImpl lTyreReadingRepositoryImpl = new TyreReadingRepositoryImpl();
			lTyreReadingRepositoryImpl.tyreReadingRequest = tyreReadingRequest;
			lTyreReadingRepositoryImpl.procedureCall = "{call " + AssetConstants.PROC_FETCH_TYRE_READING + "(?, ?, ?, ?, ?)}";

			resultMap = jdbcTemplate.call(lTyreReadingRepositoryImpl, paramList);
			if(resultMap!=null)
			{
				setResult(resultMap,tyreReadingMap);
			}
		} catch (Exception e) {
			StackTraceElement stackTraceElement = e.getStackTrace()[0];
			logger.error("An error occurred while fetching tyre reading details: " + e);
			logger.error("At Line: " + stackTraceElement.getClassName()+":" + stackTraceElement.getLineNumber());
			tyreReadingMap.put(AssetConstants.ERROR_CD, e.getMessage());
		}
		
		tyreReadingResponse.setTyreReadingMap(tyreReadingMap);
		return tyreReadingResponse;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);		
		callableStatement.setBigDecimal(1, tyreReadingRequest.getWorkPackNr());
		DatatypeCommonUtility.checkNull(2, callableStatement, tyreReadingRequest.getWorkOrderNr());
		DatatypeCommonUtility.checkNull(3, callableStatement, tyreReadingRequest.getWorkOrderTaskNr());
		DatatypeCommonUtility.checkNull(4, callableStatement, tyreReadingRequest.getLanguageId());
		callableStatement.setString(5, "NULL");
		connection.setAutoCommit(true);
		return callableStatement;
	}
	
	public void setTyreReadingRequest(TyreReadingRequest tyreReadingRequest) {
		this.tyreReadingRequest = tyreReadingRequest;
	}
	
	private void setResult(Map<String, Object> resultMap, Map<String, Object> tyreReadingMap){
		for (Map.Entry<String, Object> entry : resultMap.entrySet())
		{
			String key = entry.getKey();
			if (("#result-set-1").equalsIgnoreCase(key)) {
				tyreReadingMap.put("TyreFailureDetails", entry.getValue());
			}
			if (("#result-set-2").equalsIgnoreCase(key)) {
				tyreReadingMap.put("TyreReadingDetails", entry.getValue());
			}
			if (("#result-set-3").equalsIgnoreCase(key)) {
				tyreReadingMap.put("TyreCheckDetails", entry.getValue());
			}
			if (("#result-set-4").equalsIgnoreCase(key)) {
				tyreReadingMap.put("TyreImageDetails", entry.getValue());
			} else if (AssetConstants.ERROR_CD.equalsIgnoreCase(key)) {
				tyreReadingMap.put(AssetConstants.ERROR_CD, entry.getValue());
			}
		}
	}
}
