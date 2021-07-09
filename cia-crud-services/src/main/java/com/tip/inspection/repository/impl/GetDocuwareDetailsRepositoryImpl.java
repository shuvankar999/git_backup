package com.tip.inspection.repository.impl;

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

import com.tip.inspection.main.InspectionCrudConstants;
import com.tip.inspection.repository.GetDocuwareDetailsRepository;

@Repository
public class GetDocuwareDetailsRepositoryImpl implements GetDocuwareDetailsRepository, CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(GetDocuwareDetailsRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	
	private String appCd;
	
	public void setAppCd(String appCd) {
		this.appCd = appCd;
	}

	public String getAppCd() {
		return appCd;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> fetchDocuwareDetails(String appCd) {
		Map<String, Object> resultMap = null;
		try {

			SqlParameter appCdparam = new SqlParameter(Types.VARCHAR);

			List paramList = new ArrayList();

			paramList.add(appCdparam);

			GetDocuwareDetailsRepositoryImpl docuwareDetailsRepositoryImpl = new GetDocuwareDetailsRepositoryImpl();
			docuwareDetailsRepositoryImpl.appCd= appCd;
			docuwareDetailsRepositoryImpl.procedureCall = "{call " + InspectionCrudConstants.PROC_FETCH_DOCUWARE_DETAILS
					+ " (?)}";
			resultMap = jdbcTemplate.call(docuwareDetailsRepositoryImpl, paramList);
		} catch (Exception e) {
			logger.error("An error occurred while Docuware details: ", e);
		}
		return resultMap;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setString(1, appCd);
		connection.setAutoCommit(true);
		return callableStatement;
	}

}
