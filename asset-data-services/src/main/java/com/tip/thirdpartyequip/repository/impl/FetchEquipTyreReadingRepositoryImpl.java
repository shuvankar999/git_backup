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
import com.tip.thirdpartyequip.model.EquipTyreReadingRequest;
import com.tip.thirdpartyequip.repository.FetchEquipTyreReadingRepository;

@Repository
public class FetchEquipTyreReadingRepositoryImpl implements FetchEquipTyreReadingRepository, CallableStatementCreator{

	static final Logger logger = LoggerFactory.getLogger(FetchEquipTyreReadingRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	
	private EquipTyreReadingRequest equipTyreReadingRequest;

	@Autowired
	public void setDataSource(DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> fetchEquipTyreReading(EquipTyreReadingRequest equipTyreReadingRequest) {
		Map<String, Object> resultMap = null;
		try {
			SqlParameter equipNrSQLparam = new SqlParameter(Types.INTEGER);
			SqlParameter langIdSQLparam = new SqlParameter(Types.INTEGER);

			List paramList = new ArrayList();
			paramList.add(equipNrSQLparam);
			paramList.add(langIdSQLparam);

			FetchEquipTyreReadingRepositoryImpl fetchEquipTyreReadingRepositoryImpl = new FetchEquipTyreReadingRepositoryImpl();
			fetchEquipTyreReadingRepositoryImpl.equipTyreReadingRequest = equipTyreReadingRequest;
			fetchEquipTyreReadingRepositoryImpl.procedureCall = "{call " + AssetConstants.PROC_FETCH_EQUIPMENT_TYRE_READING_THIRD_PARTY + " (?,?)}";
			
			resultMap = jdbcTemplate.call(fetchEquipTyreReadingRepositoryImpl, paramList);
		} catch (Exception e) {
			logger.error("An error occurred while fetching equipment tyre reading: "+e);
		}
		return resultMap;
	}
	
	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);		
		DatatypeCommonUtility.checkNull(1, callableStatement, equipTyreReadingRequest.getEquipmentNr());
		DatatypeCommonUtility.checkNull(2, callableStatement, equipTyreReadingRequest.getLangId());
		connection.setAutoCommit(true);
		return callableStatement;
	}
}
