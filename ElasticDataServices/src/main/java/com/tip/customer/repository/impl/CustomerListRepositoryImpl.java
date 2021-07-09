package com.tip.customer.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.tip.asset.util.AssetConstants;
import com.tip.customer.model.CustomerListRequest;
import com.tip.customer.repository.CustomerListRepository;

@Repository
public class CustomerListRepositoryImpl implements CustomerListRepository, CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(CustomerListRepositoryImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	
	private CustomerListRequest customerListRequest;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Object> getCustomerList(CustomerListRequest customerListRequest) {

		Map<String, Object> resultMap = new HashMap();
		try {
			SqlParameter countryCodeSQLparam = new SqlParameter(Types.VARCHAR);
			
			List paramList = new ArrayList();
			paramList.add(countryCodeSQLparam);
			CustomerListRepositoryImpl custFetchFilterRepositoryImpl = new CustomerListRepositoryImpl();
			custFetchFilterRepositoryImpl.customerListRequest = customerListRequest;
			custFetchFilterRepositoryImpl.procedureCall = "{call " + AssetConstants.PROC_CUSTOMER_LIST_FETCH + "(?)}";
			resultMap = jdbcTemplate.call(custFetchFilterRepositoryImpl, paramList);
			logger.info("Calling stored procedure..."+custFetchFilterRepositoryImpl.procedureCall);
		} catch (Exception e) {
			logger.error("An error occurred: " + e);
		}
		return resultMap;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);		
		callableStatement.setString(1, customerListRequest.getCountryCd());
		connection.setAutoCommit(true);
		return callableStatement;
	}
}
