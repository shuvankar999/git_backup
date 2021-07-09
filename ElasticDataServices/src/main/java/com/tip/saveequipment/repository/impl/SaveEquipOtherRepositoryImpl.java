package com.tip.saveequipment.repository.impl;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tip.asset.util.AssetConstants;
import com.tip.asset.util.DatatypeCommonUtility;
import com.tip.elastic.util.ElasticSearchConstant;
import com.tip.saveequipment.model.SaveEquipOtherRequest;
import com.tip.saveequipment.repository.SaveEquipOtherRepository;

/**
 * @author Shuvankar Paul
 * Created on Dec 18, 2017
 * 
 */
@Repository
public class SaveEquipOtherRepositoryImpl implements SaveEquipOtherRepository, CallableStatementCreator{
	
static final Logger logger = LoggerFactory.getLogger(SaveEquipOtherRepositoryImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	private SaveEquipOtherRequest saveEquipOtherRequest;
	private String appCd;
	private String ssoId;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> saveEquipOther(SaveEquipOtherRequest saveEquipOtherRequest, String appCd,
			String ssoId) {
		
		this.saveEquipOtherRequest = saveEquipOtherRequest;
		
		Map<String, Object> resultMap = null;
		Map<String, Object> saveEquipOtherReturnMap = new HashMap();
		ObjectMapper mapper = new ObjectMapper();
		try {

			
			SqlParameter appCdSqlParam = new SqlParameter(Types.VARCHAR);
			SqlParameter unitNrSqlParam = new SqlParameter(Types.INTEGER);
			SqlParameter unitLastParkLocCdSqlParam = new SqlParameter(Types.VARCHAR);
			SqlParameter branchNrSqlParam = new SqlParameter(Types.INTEGER);
			SqlParameter ssoUserIdSqlParam = new SqlParameter(Types.VARCHAR);



			List paramList = new ArrayList();
			paramList.add(appCdSqlParam);
			paramList.add(unitNrSqlParam);
			paramList.add(unitLastParkLocCdSqlParam);
			paramList.add(branchNrSqlParam);
			paramList.add(ssoUserIdSqlParam);
			
			SaveEquipOtherRepositoryImpl saveEquipOtherRepositoryImpl = new SaveEquipOtherRepositoryImpl();
			saveEquipOtherRepositoryImpl.saveEquipOtherRequest = saveEquipOtherRequest;
			saveEquipOtherRepositoryImpl.appCd = appCd;
			saveEquipOtherRepositoryImpl.ssoId = ssoId;
			saveEquipOtherRepositoryImpl.procedureCall = "{call " + ElasticSearchConstant.PROC_SAVE_EQUIP_OTHER + " (?,?,?,?,?)}";
			String requestObject = mapper.writeValueAsString(saveEquipOtherRequest);
			logger.info("Request:----- "+requestObject+ "----App_Cd: "+appCd+"----Sso_Id: "+ssoId);
			logger.info("Calling Procedure..."+saveEquipOtherRepositoryImpl.procedureCall);
			resultMap = jdbcTemplate.call(saveEquipOtherRepositoryImpl, paramList);
			
		} catch (Exception e) {
			logger.error("An error occurred while saving static equipment details in other tab : " + e);
		}

		if(null!=resultMap){
			Iterator<Entry<String, Object>> it = resultMap.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry<String, Object> entry= it.next();
				String key = entry.getKey();
				logger.info("Key: "+entry.getKey());
				logger.info("Value: "+entry.getValue());
				if(("#result-set-1").equalsIgnoreCase(key)){
					saveEquipOtherReturnMap.put("ResultSet", entry.getValue());
				}
			}
		}
		
		return saveEquipOtherReturnMap;

	}

	
	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		
		/*Set values for every parameters*/
		
		
		callableStatement.setString(1, appCd);
		System.out.println(appCd);
		DatatypeCommonUtility.checkNull(2, callableStatement, saveEquipOtherRequest.getUnitNr());
		callableStatement.setString(3, saveEquipOtherRequest.getUnitLastParkLocCd());
		DatatypeCommonUtility.checkNull(4, callableStatement, saveEquipOtherRequest.getBranchNr());
		callableStatement.setString(5, ssoId);
		System.out.println(ssoId);
		connection.setAutoCommit(true);
		return callableStatement;
	}


}
