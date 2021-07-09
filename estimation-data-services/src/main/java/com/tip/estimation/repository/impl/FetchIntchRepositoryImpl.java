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

import com.tip.estimation.model.EstRequest;
import com.tip.estimation.repository.FetchIntchRepository;
import com.tip.util.DatatypeCommonUtility;
import com.tip.util.EstimationConstant;

@Repository
public class FetchIntchRepositoryImpl implements FetchIntchRepository, CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(FetchIntchRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	private EstRequest estRequest;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Object> getIntchDetails(EstRequest estRequest) {

		Map<String, Object> resultMap = null;
		
		try {

			SqlParameter customer = new SqlParameter(Types.VARCHAR);
			SqlParameter appCd = new SqlParameter(Types.VARCHAR);
			SqlParameter erroroutParameter = new SqlParameter(Types.VARCHAR);

			List paramList = new ArrayList();
			paramList.add(customer);
			paramList.add(appCd);
			paramList.add(erroroutParameter);

			FetchIntchRepositoryImpl fetchIntchRepositoryImpl = new FetchIntchRepositoryImpl();
			fetchIntchRepositoryImpl.estRequest = estRequest;
			fetchIntchRepositoryImpl.procedureCall = "{call " + EstimationConstant.PROC_ESTIMATION_FETCH_INTCH_LIST
					+ "(?,?)}";
			resultMap = jdbcTemplate.call((CallableStatementCreator) fetchIntchRepositoryImpl, paramList);

		} catch (Exception e) {
			logger.error("An error occurred: " + e);
		}

		return resultMap;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		DatatypeCommonUtility.checkNull(1, callableStatement, estRequest.getAssetNr());
        callableStatement.setString(2, "NULL");
		connection.setAutoCommit(true);
		return callableStatement;
	}
}
