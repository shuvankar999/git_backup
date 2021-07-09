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

import com.tip.asset.util.AssetConstants;
import com.tip.asset.util.DatatypeCommonUtility;
import com.tip.thirdpartyequip.model.EquipmentReadingRequest;
import com.tip.thirdpartyequip.repository.FetchEquipReadingRepository;

@Repository
public class FetchEquipReadingRepositoryImpl implements FetchEquipReadingRepository, CallableStatementCreator{

	static final Logger logger = LoggerFactory.getLogger(FetchEquipReadingRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	
	private EquipmentReadingRequest equipmentReadingRequest;

	@Autowired
	public void setDataSource(DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> fetchEquipReading(EquipmentReadingRequest equipmentReadingRequest) {
		Map<String, Object> resultMap = null;
		try {
			SqlParameter equipNrSQLparam = new SqlParameter(Types.INTEGER);

			List paramList = new ArrayList();
			paramList.add(equipNrSQLparam);

			FetchEquipReadingRepositoryImpl fetchEquipReadingRepositoryImpl = new FetchEquipReadingRepositoryImpl();
			fetchEquipReadingRepositoryImpl.equipmentReadingRequest = equipmentReadingRequest;
			fetchEquipReadingRepositoryImpl.procedureCall = "{call " + AssetConstants.PROC_FETCH_EQUIPMENT_READING_THIRD_PARTY + "(?)}";
			
			resultMap = jdbcTemplate.call(fetchEquipReadingRepositoryImpl, paramList);
		} catch (Exception e) {
			logger.error("An error occurred while fetching equipment reading: "+e);
		}
		return resultMap;
	}
	
	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);		
		DatatypeCommonUtility.checkNull(1, callableStatement, equipmentReadingRequest.getEquipmentNr());
		connection.setAutoCommit(true);
		return callableStatement;
	}
}
