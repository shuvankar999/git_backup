package com.tip.equipmentdetails.repository.impl;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.tip.asset.util.AssetConstants;
import com.tip.asset.util.DatatypeCommonUtility;
import com.tip.equipmentdetails.model.FetchEquipDetailsRequest;
import com.tip.equipmentdetails.repository.FetchEquipmentDetailsRepository;

@Repository
public class FetchEquipmentDetailsRepositoryImpl implements FetchEquipmentDetailsRepository, CallableStatementCreator{

	static final Logger logger = LoggerFactory.getLogger(FetchEquipmentDetailsRepositoryImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	
	private FetchEquipDetailsRequest fetchEquipDetailsRequest;
	
	private boolean refreshFlag;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Object> getEquipmentDetils(FetchEquipDetailsRequest fetchEquipDetailsRequest, boolean refreshFlag) {

		Map<String, Object> equipReturnMap = new HashMap();
		Map<String, Object> resultMap = new HashMap();
		try {
			SqlParameter appCdSQLparam = new SqlParameter(Types.VARCHAR);
			SqlParameter unitNrSQLparam = new SqlParameter(Types.INTEGER);
			SqlParameter ssoIdNrSQLparam = new SqlParameter(Types.VARCHAR);
			SqlParameter languIdSQLparam = new SqlParameter(Types.INTEGER);

			List paramList = new ArrayList();
			paramList.add(appCdSQLparam);
			paramList.add(unitNrSQLparam);			

			FetchEquipmentDetailsRepositoryImpl fetchEquipmentDetailsRepositoryImpl = new FetchEquipmentDetailsRepositoryImpl();
			fetchEquipmentDetailsRepositoryImpl.refreshFlag = refreshFlag;
			fetchEquipmentDetailsRepositoryImpl.fetchEquipDetailsRequest = fetchEquipDetailsRequest;
			if(refreshFlag)
			{
				SqlParameter catgIdSQLparam = new SqlParameter(Types.INTEGER);
				paramList.add(catgIdSQLparam);
				fetchEquipmentDetailsRepositoryImpl.procedureCall = "{call " + AssetConstants.PROC_FETCH_REFRESH_EQUIP_DETAILS + "(?, ?, ?, ?, ?)}";
			}
			else
			{
				fetchEquipmentDetailsRepositoryImpl.procedureCall = "{call " + AssetConstants.PROC_FETCH_EQUIP_DETAILS + "(?, ?, ?, ?)}";
			}
			paramList.add(ssoIdNrSQLparam);
			paramList.add(languIdSQLparam);
			resultMap = jdbcTemplate.call(fetchEquipmentDetailsRepositoryImpl, paramList);
			logger.info("Calling stored procedure..."+fetchEquipmentDetailsRepositoryImpl.procedureCall);

			for (Map.Entry<String, Object> entry : resultMap.entrySet())
			{
				String key = entry.getKey();
				if (("#result-set-1").equalsIgnoreCase(key)) {
					equipReturnMap.put("StaticTableData", entry.getValue());
				} else if (("#result-set-2").equalsIgnoreCase(key)) {
					equipReturnMap.put("DynamicTableData", entry.getValue());
				} else if (("#result-set-3").equalsIgnoreCase(key)) {
					equipReturnMap.put("Status", entry.getValue());
				}
			}
		} catch (Exception e) {
			StackTraceElement stackTraceElement = e.getStackTrace()[0];
			logger.error("An error occurred while fetching equipment details : " + e);
			logger.error("At Line: " + stackTraceElement.getClassName()+":" + stackTraceElement.getLineNumber());
			resultMap.put(AssetConstants.ERROR_CD, e.getMessage());
		}
		return equipReturnMap;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);		
		callableStatement.setString(1, fetchEquipDetailsRequest.getAppCd());
		DatatypeCommonUtility.checkNull(2, callableStatement, fetchEquipDetailsRequest.getUnitNr());
		if(refreshFlag){
			DatatypeCommonUtility.checkNull(3, callableStatement, fetchEquipDetailsRequest.getCatgrId());
			callableStatement.setString(4, fetchEquipDetailsRequest.getSsoId());
			DatatypeCommonUtility.checkNull(5, callableStatement, fetchEquipDetailsRequest.getLanguageId());
		} else {
			callableStatement.setString(3, fetchEquipDetailsRequest.getSsoId());
			DatatypeCommonUtility.checkNull(4, callableStatement, fetchEquipDetailsRequest.getLanguageId());
		}		
		connection.setAutoCommit(true);
		return callableStatement;
	}

	public void setFetchEquipDetailsRequest(FetchEquipDetailsRequest fetchEquipDetailsRequest) {
		this.fetchEquipDetailsRequest = fetchEquipDetailsRequest;
	}

	public void setRefreshFlag(boolean refreshFlag) {
		this.refreshFlag = refreshFlag;
	}
}
