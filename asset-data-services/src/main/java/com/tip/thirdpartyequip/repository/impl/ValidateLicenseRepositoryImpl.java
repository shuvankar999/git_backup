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
import com.tip.thirdpartyequip.model.ValidLicenseRequest;
import com.tip.thirdpartyequip.repository.ValidateLicenseRepository;

@Repository
public class ValidateLicenseRepositoryImpl implements ValidateLicenseRepository, CallableStatementCreator{

	static final Logger logger = LoggerFactory.getLogger(ValidateLicenseRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	
	private ValidLicenseRequest validLicenseRequest;

	@Autowired
	public void setDataSource(DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> validateLicenseNr(ValidLicenseRequest validLicenseRequest) {
		Map<String, Object> resultMap = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			SqlParameter equipNrSQLparam = new SqlParameter(Types.INTEGER);
			SqlParameter licCountryCodeSQLparam = new SqlParameter(Types.VARCHAR);
			SqlParameter licNrSQLparam = new SqlParameter(Types.VARCHAR);

			List paramList = new ArrayList();
			paramList.add(equipNrSQLparam);
			paramList.add(licCountryCodeSQLparam);
			paramList.add(licNrSQLparam);

			ValidateLicenseRepositoryImpl validateLicenseRepositoryImpl = new ValidateLicenseRepositoryImpl();
			validateLicenseRepositoryImpl.validLicenseRequest = validLicenseRequest;
			validateLicenseRepositoryImpl.procedureCall = "{call " + AssetConstants.PROC_VALIDATE_LICENSE_THIRD_PARTY + " (?,?,?)}";
			logger.info("Request JSON "+mapper.writeValueAsString(validateLicenseRepositoryImpl.validLicenseRequest));
			logger.info("Procedure calling..."+validateLicenseRepositoryImpl.procedureCall);
			resultMap = jdbcTemplate.call(validateLicenseRepositoryImpl, paramList);
		} catch (Exception e) {
			logger.error("An error occurred while validating license nr: "+e);
		}
		return resultMap;
	}
	
	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);		
		DatatypeCommonUtility.checkNull(1, callableStatement, validLicenseRequest.getEquipmentNr());
		callableStatement.setString(2, validLicenseRequest.getLicenceCountryCd());
		callableStatement.setString(3, validLicenseRequest.getLicenceNr());
		connection.setAutoCommit(true);
		return callableStatement;
	}

}
