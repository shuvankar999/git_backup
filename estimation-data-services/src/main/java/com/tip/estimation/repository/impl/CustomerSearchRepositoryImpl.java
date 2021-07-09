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

import com.tip.estimation.model.CustomerSearchRequest;
import com.tip.estimation.repository.CustomerSearchRepository;
import com.tip.util.EstimationConstant;

@Repository
public class CustomerSearchRepositoryImpl implements CustomerSearchRepository, CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(CustomerSearchRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	private CustomerSearchRequest customerSearchRequest;

	public void setCustomer(CustomerSearchRequest customerSearchRequest) {
		this.customerSearchRequest = customerSearchRequest;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Object> getCustomer(CustomerSearchRequest customerSearchRequest) {

		Map<String, Object> resultMap = null;
		
		try {

			SqlParameter customer = new SqlParameter(Types.VARCHAR);
			SqlParameter appCd = new SqlParameter(Types.VARCHAR);
			
			SqlParameter erroroutParameter = new SqlParameter(Types.VARCHAR);

			List paramList = new ArrayList();
			paramList.add(customer);
			paramList.add(appCd);
			paramList.add(erroroutParameter);

			CustomerSearchRepositoryImpl customerSearchRepositoryImpl = new CustomerSearchRepositoryImpl();
			customerSearchRepositoryImpl.customerSearchRequest = customerSearchRequest;
			customerSearchRepositoryImpl.procedureCall = "{call " + EstimationConstant.PROC_ESTIMATION_CUST_SEARCH
					+ "(?,?,?)}";
			resultMap = jdbcTemplate.call((CallableStatementCreator) customerSearchRepositoryImpl, paramList);

		} catch (Exception e) {
			logger.error("An error occurred: " + e);
		}

		return resultMap;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setString(1, customerSearchRequest.getCustomer());
		callableStatement.setString(2, customerSearchRequest.getAppCd());
        callableStatement.setString(3, "NULL");
		connection.setAutoCommit(true);
		return callableStatement;
	}
}
