package com.tip.estimation.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.tip.estimation.model.EquipSearchRequest;
import com.tip.estimation.repository.EquipSearchRepository;
import com.tip.util.EstimationConstant;

@Repository
public class EquipSearchRepositoryImpl implements EquipSearchRepository, CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(EquipSearchRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	private EquipSearchRequest equipSearchRequest;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Object> getEquipment(EquipSearchRequest equipSearchRequest) {

		Map<String, Object> resultMap = null;
		try {

			SqlParameter customer = new SqlParameter(Types.VARCHAR);

			List paramList = new ArrayList();
			paramList.add(customer);

			EquipSearchRepositoryImpl equipSearchRepositoryImpl = new EquipSearchRepositoryImpl();
			equipSearchRepositoryImpl.equipSearchRequest = equipSearchRequest;
			equipSearchRepositoryImpl.procedureCall = "{call " + EstimationConstant.PROC_ESTIMATION_EQUIP_SEARCH +"(?)}";
			resultMap = jdbcTemplate.call((CallableStatementCreator) equipSearchRepositoryImpl, paramList);

		} catch (Exception e) {
			logger.error("An error occurred: " + e);
		}

		return resultMap;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setString(1, equipSearchRequest.getAssetNr());
		connection.setAutoCommit(true);
		return callableStatement;
	}
}
