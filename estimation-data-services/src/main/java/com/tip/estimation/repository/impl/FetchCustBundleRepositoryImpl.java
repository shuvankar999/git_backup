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

import com.tip.estimation.model.FetchCustBundleRequest;
import com.tip.estimation.repository.FetchCustBundleRepository;
import com.tip.util.DatatypeCommonUtility;
import com.tip.util.EstimationConstant;

@Repository
public class FetchCustBundleRepositoryImpl implements FetchCustBundleRepository, CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(FetchCustEquipmentDetailsRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;

	private FetchCustBundleRequest fetchCustBundleRequest;

	public void setCustBundle(FetchCustBundleRequest fetchCustBundleRequest) {
		this.fetchCustBundleRequest = fetchCustBundleRequest;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Object> getCustBundle(FetchCustBundleRequest fetchCustBundleRequest) {
		Map<String, Object> resultMap = null;
		try {
			SqlParameter custNr = new SqlParameter(Types.INTEGER);
			SqlParameter custCmpNr = new SqlParameter(Types.INTEGER);
			SqlParameter langId = new SqlParameter(Types.INTEGER);
			SqlParameter erroroutParameter = new SqlParameter(Types.VARCHAR);

			List paramList = new ArrayList();
			paramList.add(custNr);
			paramList.add(custCmpNr);
			paramList.add(langId);
			paramList.add(erroroutParameter);

			FetchCustBundleRepositoryImpl fetchCustBundleRepositoryImpl = new FetchCustBundleRepositoryImpl();
			fetchCustBundleRepositoryImpl.fetchCustBundleRequest = fetchCustBundleRequest;
			fetchCustBundleRepositoryImpl.procedureCall = "{call "
					+ EstimationConstant.PROC_ESTIMATION_FETCH_CUST_BUNDLE + "(?,?,?,?)}";
			resultMap = jdbcTemplate.call((CallableStatementCreator) fetchCustBundleRepositoryImpl, paramList);

		} catch (Exception e) {
			logger.error("An error occurred while fetching the Customer Bundle: " + e);
		}

		return resultMap;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		DatatypeCommonUtility.checkNull(1, callableStatement, fetchCustBundleRequest.getCustNr());
		DatatypeCommonUtility.checkNull(2, callableStatement, fetchCustBundleRequest.getCustCmpNr());
		DatatypeCommonUtility.checkNull(3, callableStatement, fetchCustBundleRequest.getLangId());
		callableStatement.setString(4, "NULL");
		connection.setAutoCommit(true);
		return callableStatement;
	}

}
