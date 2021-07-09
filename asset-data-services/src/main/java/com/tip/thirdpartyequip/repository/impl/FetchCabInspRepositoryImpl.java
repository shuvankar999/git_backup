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
import com.tip.thirdpartyequip.model.EquipmentCabRequest;
import com.tip.thirdpartyequip.repository.FetchCabInspRepository;

@Repository
public class FetchCabInspRepositoryImpl implements FetchCabInspRepository, CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(FetchCabInspRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;

	private EquipmentCabRequest equipmentCabRequest;

	@Autowired
	public void setDataSource(DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> getCabDetails(EquipmentCabRequest equipmentCabRequest) {
		Map<String, Object> resultMap = null;
		ObjectMapper mapper = new ObjectMapper();
		List paramList = new ArrayList();
		paramList = setSqlParmCabEquip();
		try {

			FetchCabInspRepositoryImpl fetchCabInspRepositoryImpl = new FetchCabInspRepositoryImpl();
			fetchCabInspRepositoryImpl.equipmentCabRequest = equipmentCabRequest;
			fetchCabInspRepositoryImpl.procedureCall = "{call " + AssetConstants.PROC_FETCH_EQUIP_CAB_DETAILS
					+ " (?,?,?)}";
			logger.info("Request JSON " + mapper.writeValueAsString(fetchCabInspRepositoryImpl.equipmentCabRequest));
			logger.info("Procedure calling..." + fetchCabInspRepositoryImpl.procedureCall);
			resultMap = jdbcTemplate.call(fetchCabInspRepositoryImpl, paramList);
		} catch (Exception e) {
			logger.error("An error occurred while fetching cab details: " + e);
		}
		return resultMap;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List setSqlParmCabEquip() {
		SqlParameter equipNrSQLparam = new SqlParameter(Types.INTEGER);
		SqlParameter langIdSQLparam = new SqlParameter(Types.INTEGER);
		SqlParameter errorCdSQLparam = new SqlParameter(Types.VARCHAR);

		List paramList = new ArrayList();
		paramList.add(equipNrSQLparam);
		paramList.add(langIdSQLparam);
		paramList.add(errorCdSQLparam);
		return paramList;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		DatatypeCommonUtility.checkNull(1, callableStatement, equipmentCabRequest.getEquipmentNr());
		DatatypeCommonUtility.checkNull(2, callableStatement, equipmentCabRequest.getLangId());
		callableStatement.setString(3, "NULL");
		connection.setAutoCommit(true);

		return callableStatement;
	}

}
