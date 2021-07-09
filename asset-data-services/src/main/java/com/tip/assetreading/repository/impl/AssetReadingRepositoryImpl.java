package com.tip.assetreading.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import com.tip.assetreading.model.AssetReadingRequest;
import com.tip.assetreading.model.AssetReadingResponse;
import com.tip.assetreading.repository.AssetReadingRepository;

@Repository
public class AssetReadingRepositoryImpl implements AssetReadingRepository ,CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(AssetReadingRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	
	private AssetReadingRequest assetReadingRequest;

	@Autowired
	public void setDataSource(DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public AssetReadingResponse fetchAssetReadingDetails(AssetReadingRequest assetReadingRequest) {

		this.assetReadingRequest = assetReadingRequest;
		AssetReadingResponse assetReadingResponse = new AssetReadingResponse();

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

			AssetReadingRepositoryImpl lAssetReadingRepositoryImpl = new AssetReadingRepositoryImpl();
			lAssetReadingRepositoryImpl.assetReadingRequest = assetReadingRequest;
			lAssetReadingRepositoryImpl.procedureCall = "{call " + AssetConstants.PROC_FETCH_ASSET_READING + "(?, ?, ?, ?, ?)}";
			
			resultMap = jdbcTemplate.call(lAssetReadingRepositoryImpl, paramList);
			if(resultMap!=null)
			{
				setResult(resultMap,assetReadingMap);
			}
		} catch (Exception e) {
			StackTraceElement stackTraceElement = e.getStackTrace()[0];
			logger.error("An error occurred while fetching asset reading details : " + e);
			logger.error("At Line: " + stackTraceElement.getClassName()+":" + stackTraceElement.getLineNumber());
			assetReadingMap.put(AssetConstants.ERROR_CD, e.getMessage());
		}

		assetReadingResponse.setAssetReadingMap(assetReadingMap);
		return assetReadingResponse;
	}
	
	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);		
		callableStatement.setBigDecimal(1, assetReadingRequest.getWorkPackNr());
		DatatypeCommonUtility.checkNull(2, callableStatement, assetReadingRequest.getWorkOrderNr());
		DatatypeCommonUtility.checkNull(3, callableStatement, assetReadingRequest.getWorkOrderTaskNr());
		DatatypeCommonUtility.checkNull(4, callableStatement, assetReadingRequest.getLanguageId());
		callableStatement.setString(5, "NULL");
		connection.setAutoCommit(true);
		return callableStatement;
	}

	public void setAssetReadingRequest(AssetReadingRequest assetReadingRequest) {
		this.assetReadingRequest = assetReadingRequest;
	}
	
	private void setResult(Map<String, Object> resultMap, Map<String, Object> assetReadingMap){
		for (Map.Entry<String, Object> entry : resultMap.entrySet())
		{
			String key = entry.getKey();
			if (("#result-set-1").equalsIgnoreCase(key)) {
				assetReadingMap.put("AssetReadingHeaderDetails", entry.getValue());
			} else if (("#result-set-2").equalsIgnoreCase(key)) {
				assetReadingMap.put("AssetReadingDetails", entry.getValue());
			} else if (("#result-set-3").equalsIgnoreCase(key)) {
				assetReadingMap.put("OperatorDetails", entry.getValue());
			} else if (AssetConstants.ERROR_CD.equalsIgnoreCase(key)) {
				assetReadingMap.put(AssetConstants.ERROR_CD, entry.getValue());
			}
		}
	}
}
