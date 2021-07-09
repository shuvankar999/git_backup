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
import com.tip.asset.util.DatatypeCommonUtility;
import com.tip.elastic.util.ElasticSearchConstant;
import com.tip.saveequipment.model.SaveEquipStaticRequest;
import com.tip.saveequipment.repository.SaveEquipStaticStatusRepository;

@Repository
public class SaveEquipStaticStatusRepositoryImpl implements SaveEquipStaticStatusRepository, CallableStatementCreator{

static final Logger logger = LoggerFactory.getLogger(SaveEquipStaticStatusRepositoryImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	private SaveEquipStaticRequest saveEquipStaticRequest;
	private String appCd;
	private String ssoId;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> saveEquipmentStaticStatus(SaveEquipStaticRequest saveEquipStaticRequest, String appCd,
			String ssoId) {


		Map<String, Object> resultMap = null;
		Map<String, Object> saveEquipStaticReturnMap = new HashMap();
		ObjectMapper mapper = new ObjectMapper();
		try {

			
			List paramList = setSqlParam();
			
			SaveEquipStaticStatusRepositoryImpl saveEquipStaticRepositoryImpl = new SaveEquipStaticStatusRepositoryImpl();
			saveEquipStaticRepositoryImpl.saveEquipStaticRequest = saveEquipStaticRequest;
			saveEquipStaticRepositoryImpl.appCd = appCd;
			saveEquipStaticRepositoryImpl.ssoId = ssoId;
			saveEquipStaticRepositoryImpl.procedureCall = "{call " + ElasticSearchConstant.PROC_SAVE_EQUIP_STATIC_STATUS + " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			String requestObject = mapper.writeValueAsString(saveEquipStaticRequest);
			logger.info("Request:----- "+requestObject+ "----App_Cd: "+appCd+"----Sso_Id: "+ssoId);
			logger.info("Calling Procedure..."+saveEquipStaticRepositoryImpl.procedureCall);
			resultMap = jdbcTemplate.call(saveEquipStaticRepositoryImpl, paramList);
			
		} catch (Exception e) {
			logger.error("An error occurred while saving equipment in static tab : " + e);
		}
		if(null!=resultMap){
			Iterator<Entry<String, Object>> it = resultMap.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry<String, Object> entry= it.next();
				String key = entry.getKey();
				logger.info("Key: "+entry.getKey());
				logger.info("Value: "+entry.getValue());
				if(("#result-set-1").equalsIgnoreCase(key)){
					saveEquipStaticReturnMap.put("ResultSet", entry.getValue());
				}
			}
		}
		return saveEquipStaticReturnMap;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List setSqlParam() {
		SqlParameter appCdSqlParam = new SqlParameter(Types.VARCHAR);
		SqlParameter unitNrSqlParam = new SqlParameter(Types.INTEGER);
		SqlParameter catagrIdSqlParam = new SqlParameter(Types.INTEGER);
		SqlParameter commentSqlParam = new SqlParameter(Types.VARCHAR);
		SqlParameter catagroupCdSqlParam = new SqlParameter(Types.VARCHAR);
		SqlParameter serialNrSqlParam = new SqlParameter(Types.VARCHAR);
		SqlParameter catagrCdSqlParam = new SqlParameter(Types.VARCHAR);
		SqlParameter grpCdSqlParam = new SqlParameter(Types.VARCHAR);
		SqlParameter manufSqlParam = new SqlParameter(Types.VARCHAR);
		SqlParameter modelYearSqlParam = new SqlParameter(Types.INTEGER);
		SqlParameter licNrSqlParam = new SqlParameter(Types.VARCHAR);
		SqlParameter licContryCdSqlParam = new SqlParameter(Types.VARCHAR);
		SqlParameter availableForSaleSqlParam = new SqlParameter(Types.VARCHAR);
		SqlParameter lienCdSqlParam = new SqlParameter(Types.VARCHAR);
		SqlParameter aquiredCompSqlParam = new SqlParameter(Types.VARCHAR);
		SqlParameter aquiredRefSqlParam = new SqlParameter(Types.VARCHAR);
		SqlParameter custRefSqlParam = new SqlParameter(Types.VARCHAR);
		SqlParameter branchNrSqlParam = new SqlParameter(Types.INTEGER);
		SqlParameter ssoUserIdSqlParam = new SqlParameter(Types.VARCHAR);
		SqlParameter statusComSqlParam = new SqlParameter(Types.VARCHAR);
		SqlParameter operationalStatusSqlParam = new SqlParameter(Types.VARCHAR);
		SqlParameter soldDateSqlParam = new SqlParameter(Types.VARCHAR);
		



		List paramList = new ArrayList();
		paramList.add(appCdSqlParam);
		paramList.add(unitNrSqlParam);
		paramList.add(catagrIdSqlParam);
		paramList.add(commentSqlParam);
		paramList.add(catagroupCdSqlParam);
		paramList.add(serialNrSqlParam);
		paramList.add(catagrCdSqlParam);
		paramList.add(grpCdSqlParam);
		paramList.add(manufSqlParam);
		paramList.add(modelYearSqlParam);
		paramList.add(licNrSqlParam);
		paramList.add(licContryCdSqlParam);
		paramList.add(availableForSaleSqlParam);
		paramList.add(lienCdSqlParam);
		paramList.add(aquiredCompSqlParam);
		paramList.add(aquiredRefSqlParam);
		paramList.add(custRefSqlParam);
		paramList.add(branchNrSqlParam);
		paramList.add(ssoUserIdSqlParam);
		paramList.add(statusComSqlParam);
		paramList.add(operationalStatusSqlParam);
		paramList.add(soldDateSqlParam);
		return paramList;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		
		/*Set values for every parameters*/
		
		
		callableStatement.setString(1, appCd);
		System.out.println(appCd);
		DatatypeCommonUtility.checkNull(2, callableStatement, saveEquipStaticRequest.getUnitNr());
		DatatypeCommonUtility.checkNull(3, callableStatement, saveEquipStaticRequest.getCatgrId());
		callableStatement.setString(4, saveEquipStaticRequest.getUnitComment());
		callableStatement.setString(5, saveEquipStaticRequest.getCatgrpCode());
		callableStatement.setString(6, saveEquipStaticRequest.getSerialNr());
		callableStatement.setString(7, saveEquipStaticRequest.getCategoryCd());
		callableStatement.setString(8, saveEquipStaticRequest.getGroupCd());
		callableStatement.setString(9, saveEquipStaticRequest.getManufacturer());
		DatatypeCommonUtility.checkNull(10, callableStatement, saveEquipStaticRequest.getModelYear());
		callableStatement.setString(11, saveEquipStaticRequest.getLicenceNr());
		callableStatement.setString(12, saveEquipStaticRequest.getLicenceCountryCd());
		callableStatement.setString(13, saveEquipStaticRequest.getAvailableForSaleInd());
		callableStatement.setString(14, saveEquipStaticRequest.getLienCd());
		callableStatement.setString(15, saveEquipStaticRequest.getAquiredCompany());
		callableStatement.setString(16, saveEquipStaticRequest.getAquiredRefrnc());
		callableStatement.setString(17, saveEquipStaticRequest.getCustomerRefrnc());
		DatatypeCommonUtility.checkNull(18, callableStatement, saveEquipStaticRequest.getBranchNr());
		callableStatement.setString(19, ssoId);
		callableStatement.setString(20, saveEquipStaticRequest.getStatusComment());
		callableStatement.setString(21, saveEquipStaticRequest.getOperationalStatus());
		callableStatement.setString(22, saveEquipStaticRequest.getSoldDate());
		System.out.println(ssoId);
		connection.setAutoCommit(true);
		return callableStatement;
	}

}
