package com.tip.fetchfridgedetails.repository.impl;

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
import com.tip.asset.util.CommonUtil;
import com.tip.asset.util.DatatypeCommonUtility;
import com.tip.fetchfridgedetails.model.FridgeDetailsRequest;
import com.tip.fetchfridgedetails.repository.TailliftDetailsRepository;

@Repository
public class TailliftDetailsRepositoryImpl implements TailliftDetailsRepository,CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(TailliftDetailsRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	
	private FridgeDetailsRequest fridgeDetailsRequest;

	@Autowired
	public void setDataSource(DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> getTailliftDetails(FridgeDetailsRequest fridgeDetailsRequest) {
		this.fridgeDetailsRequest = fridgeDetailsRequest;
		Map<String, Object> assetReadingMap = new HashMap<>();
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

			TailliftDetailsRepositoryImpl lTailliftDetailsRepositoryImpl = new TailliftDetailsRepositoryImpl();
			lTailliftDetailsRepositoryImpl.fridgeDetailsRequest = fridgeDetailsRequest;
			lTailliftDetailsRepositoryImpl.procedureCall = "{call " + AssetConstants.PROC_FETCH_ASSET_TAILLIFT_DETAILS + CommonUtil.getQuestionMarkValueByCount(5)+"}";
			
			resultMap = jdbcTemplate.call(lTailliftDetailsRepositoryImpl, paramList);

			logger.info("Calling Stored procedure..."+lTailliftDetailsRepositoryImpl.procedureCall);
			
			if(resultMap!=null)
			{
				setResult(resultMap,assetReadingMap);
			}
		} catch (Exception e) {
			StackTraceElement stackTraceElement = e.getStackTrace()[0];
			logger.error("An error occurred while fetching asset taillift details : " + e);
			logger.error("At Line: " + stackTraceElement.getClassName()+":" + stackTraceElement.getLineNumber());
			assetReadingMap.put(AssetConstants.ERROR_CD, e.getMessage());
		}
		return assetReadingMap;
	}
	
	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);		
		callableStatement.setBigDecimal(1, fridgeDetailsRequest.getWorkPackNr());
		DatatypeCommonUtility.checkNull(2, callableStatement, fridgeDetailsRequest.getWorkOrderNr());
		DatatypeCommonUtility.checkNull(3, callableStatement, fridgeDetailsRequest.getWorkOrderTaskNr());
		DatatypeCommonUtility.checkNull(4, callableStatement, fridgeDetailsRequest.getLangId());
		callableStatement.setString(5, "NULL");
		connection.setAutoCommit(true);
		return callableStatement;
	}

	public void setFridgeDetailsRequest(FridgeDetailsRequest fridgeDetailsRequest) {
		this.fridgeDetailsRequest = fridgeDetailsRequest;
	}

	private void setResult(Map<String, Object> resultMap, Map<String, Object> assetReadingMap){
		for (Map.Entry<String, Object> entry : resultMap.entrySet())
		{
			String key = entry.getKey();
			logger.info("key>>>"+key);
			logger.info("value>>>"+entry.getValue());
			if (("#result-set-1").equalsIgnoreCase(key)) {
				assetReadingMap.put("AssetTailliftObject", entry.getValue());
			}
		}
	}
}
