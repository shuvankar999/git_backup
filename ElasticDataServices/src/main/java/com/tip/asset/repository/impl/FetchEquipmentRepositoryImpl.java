package com.tip.asset.repository.impl;

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

import com.tip.asset.model.EquipmentDetailsRequest;
import com.tip.asset.repository.FetchEquipmentRepository;
import com.tip.asset.util.AssetConstants;
import com.tip.asset.util.DatatypeCommonUtility;

@Repository
public class FetchEquipmentRepositoryImpl implements FetchEquipmentRepository ,CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(FetchEquipmentRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	
	private EquipmentDetailsRequest equipmentDetailsRequest;

	@Autowired
	public void setDataSource(DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> fetchEquipmentDetails(EquipmentDetailsRequest equipmentDetailsRequest) {
		this.equipmentDetailsRequest = equipmentDetailsRequest;
		Map<String, Object> resultMap = null;
		try {
			SqlParameter assetNrSQLparam = new SqlParameter(Types.INTEGER);
			SqlParameter errorCdSQLparam = new SqlParameter(Types.VARCHAR);

			List paramList = new ArrayList();
			paramList.add(assetNrSQLparam);
			paramList.add(errorCdSQLparam);

			FetchEquipmentRepositoryImpl fetchEquipmentRepositoryImpl = new FetchEquipmentRepositoryImpl();
			fetchEquipmentRepositoryImpl.equipmentDetailsRequest = equipmentDetailsRequest;
			fetchEquipmentRepositoryImpl.procedureCall = "{call " + AssetConstants.PROC_FETCH_EQUIPMENT_DETAILS + "(?, ?)}";
			
			resultMap = jdbcTemplate.call(fetchEquipmentRepositoryImpl, paramList);
		} catch (Exception e) {
			logger.error("An error occurred while fetching equipment details : ", e);
		}
		return resultMap;
	}
	
	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);		
		DatatypeCommonUtility.checkNull(1, callableStatement, equipmentDetailsRequest.getAssetNr());
		callableStatement.setString(2, "NULL");
		connection.setAutoCommit(true);
		return callableStatement;
	}

	public void setEquipmentDetailsRequest(EquipmentDetailsRequest equipmentDetailsRequest) {
		this.equipmentDetailsRequest = equipmentDetailsRequest;
	}
}
