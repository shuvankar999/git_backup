package com.tip.supplier.repository.impl;

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

import com.tip.supplier.main.DatatypeCommonUtility;
import com.tip.supplier.main.SupplierDataConstants;
import com.tip.supplier.model.FetchJobRequest;
import com.tip.supplier.repository.FetchCustomisedJobRepository;

@Repository
public class FetchCustomisedJobRepositoryImpl implements FetchCustomisedJobRepository, CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(FetchCustomisedJobRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;

	private FetchJobRequest fetchJobRequest;

	public void setFetchJobRequest(FetchJobRequest fetchJobRequest) {
		this.fetchJobRequest = fetchJobRequest;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> fetchCustomisedJob(FetchJobRequest fetchJobRequest) {

		Map<String, Object> resultMap = null;
		try {
			SqlParameter supplierIdParam = new SqlParameter(Types.INTEGER);
			SqlParameter landIdParam = new SqlParameter(Types.INTEGER);
			SqlParameter ssoIdParam = new SqlParameter(Types.VARCHAR);
			SqlParameter errorCdParam = new SqlParameter(Types.VARCHAR);

			List paramList = new ArrayList();
			paramList.add(supplierIdParam);
			paramList.add(landIdParam);
			paramList.add(ssoIdParam);
			paramList.add(errorCdParam);

			FetchCustomisedJobRepositoryImpl fetchCustomisedJobRepositoryImpl = new FetchCustomisedJobRepositoryImpl();
			fetchCustomisedJobRepositoryImpl.fetchJobRequest = fetchJobRequest;
			fetchCustomisedJobRepositoryImpl.procedureCall = "{call " + SupplierDataConstants.PROC_FETCH_JOBS
					+ " (?,?,?,?)}";
			resultMap = jdbcTemplate.call(fetchCustomisedJobRepositoryImpl, paramList);

		} catch (Exception e) {
			logger.error("An error occurred while fetching supplier jobs: " + e);
		}
		return resultMap;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		DatatypeCommonUtility.checkNull(1, callableStatement, fetchJobRequest.getSupplierId());
		DatatypeCommonUtility.checkNull(2, callableStatement, fetchJobRequest.getLangId());
		callableStatement.setString(3, fetchJobRequest.getSsoId());
		callableStatement.setString(4, fetchJobRequest.getErrorCd());
		connection.setAutoCommit(true);
		return callableStatement;
	}
}
