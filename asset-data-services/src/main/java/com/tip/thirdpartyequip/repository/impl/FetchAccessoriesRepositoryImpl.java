package com.tip.thirdpartyequip.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tip.asset.util.AssetConstants;
import com.tip.asset.util.DatatypeCommonUtility;
import com.tip.thirdpartyequip.model.AccessoryRequest;
import com.tip.thirdpartyequip.repository.FetchAccessoriesRepository;

@Repository
public class FetchAccessoriesRepositoryImpl implements FetchAccessoriesRepository, CallableStatementCreator{

	static final Logger logger = LoggerFactory.getLogger(FetchAccessoriesRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	
	private AccessoryRequest accessoryRequest;

	@Autowired
	public void setDataSource(DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> fetchAccessories(AccessoryRequest accessoryRequest) {
		Map<String, Object> resultMap = null;
		ObjectMapper mapper = new ObjectMapper();
		List paramList = new ArrayList();
		try {

			FetchAccessoriesRepositoryImpl fetchAccessoriesRepositoryImpl = new FetchAccessoriesRepositoryImpl();
			fetchAccessoriesRepositoryImpl.accessoryRequest = accessoryRequest;
			
			if(accessoryRequest.getEquipmentNr()!=null && accessoryRequest.getEquipmentNr()>0){
				paramList = setSqlParmAccEquip();
				fetchAccessoriesRepositoryImpl.procedureCall = "{call " + AssetConstants.PROC_FETCH_EQUIP_ACC + " (?,?,?)}";
			}else{
				paramList = setSqlParmAccCd();
				fetchAccessoriesRepositoryImpl.procedureCall = "{call " + AssetConstants.PROC_FETCH_ACC_CODE + " (?)}";
			}
				logger.info("Request JSON "+mapper.writeValueAsString(fetchAccessoriesRepositoryImpl.accessoryRequest));
				logger.info("Procedure calling..."+fetchAccessoriesRepositoryImpl.procedureCall);
				resultMap = jdbcTemplate.call(fetchAccessoriesRepositoryImpl, paramList);
		} catch (Exception e) {
			logger.error("An error occurred while fetching accessories: "+e);
		}
		return resultMap;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List setSqlParmAccEquip() {
		SqlParameter equipNrSQLparam = new SqlParameter(Types.INTEGER);
		SqlParameter langIdSQLparam = new SqlParameter(Types.INTEGER);
		SqlParameter errorCdSQLparam = new SqlParameter(Types.VARCHAR);

		List paramList = new ArrayList();
		paramList.add(equipNrSQLparam);
		paramList.add(langIdSQLparam);
		paramList.add(errorCdSQLparam);
		return paramList;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List setSqlParmAccCd() {
		SqlParameter langIdSQLparam = new SqlParameter(Types.INTEGER);

		List paramList = new ArrayList();
		paramList.add(langIdSQLparam);
		return paramList;
	}
	
	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		if(procedureCall.contains("Wshp_Fetch_Equip_Accessories")){
			DatatypeCommonUtility.checkNull(1, callableStatement, accessoryRequest.getEquipmentNr());
			DatatypeCommonUtility.checkNull(2, callableStatement, accessoryRequest.getLangId());
			callableStatement.setString(3, "NULL");
			connection.setAutoCommit(true);
		}else{		
			DatatypeCommonUtility.checkNull(1, callableStatement, accessoryRequest.getLangId());
			connection.setAutoCommit(true);
		}
		return callableStatement;
	}

}
