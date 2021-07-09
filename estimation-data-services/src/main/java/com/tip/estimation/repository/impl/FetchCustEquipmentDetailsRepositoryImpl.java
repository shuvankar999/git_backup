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

import com.tip.estimation.model.FetchEquipmentRequest;
import com.tip.estimation.repository.FetchCustEquipmentDetailsRepository;
import com.tip.util.DatatypeCommonUtility;
import com.tip.util.EstimationConstant;

@Repository
public class FetchCustEquipmentDetailsRepositoryImpl implements FetchCustEquipmentDetailsRepository, CallableStatementCreator{

	static final Logger logger = LoggerFactory.getLogger(FetchCustEquipmentDetailsRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	
	private FetchEquipmentRequest fetchEquipmentRequest;
	

	public void setEquipmentDetails(FetchEquipmentRequest fetchEquipmentRequest) {
		this.fetchEquipmentRequest = fetchEquipmentRequest;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes"})
	@Override
	public Map<String, Object> getEquipmentDetails(FetchEquipmentRequest fetchEquipmentRequest) {
		Map<String, Object> resultMap = null;
		try {
			SqlParameter customerNr = new SqlParameter(Types.INTEGER);
			SqlParameter custCmpNr = new SqlParameter(Types.INTEGER);
			

			List paramList = new ArrayList();
			paramList.add(customerNr);
			paramList.add(custCmpNr);
			
			FetchCustEquipmentDetailsRepositoryImpl fetchCustEquipmentDetailsRepositoryImpl = new FetchCustEquipmentDetailsRepositoryImpl();
			fetchCustEquipmentDetailsRepositoryImpl.fetchEquipmentRequest = fetchEquipmentRequest;
			fetchCustEquipmentDetailsRepositoryImpl.procedureCall = "{call " + EstimationConstant.PROC_ESTIMATION_FETCH_CUST_EQUIPMENTS+ "(?,?)}";
			resultMap = jdbcTemplate.call((CallableStatementCreator) fetchCustEquipmentDetailsRepositoryImpl, paramList);

		} catch (Exception e) {
			logger.error("An error occurred while fetching the Equipment Details: " + e);
		}

		return resultMap;
	}
	
	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		DatatypeCommonUtility.checkNull(1, callableStatement, fetchEquipmentRequest.getCustomerNr());
		DatatypeCommonUtility.checkNull(2, callableStatement, fetchEquipmentRequest.getCustCmpNr());
		connection.setAutoCommit(true);
		return callableStatement;
	}
		
	

}
