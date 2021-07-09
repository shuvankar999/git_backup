package com.tip.fetchinspectionreport.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.tip.fetchinspectionreport.model.EmailReportRequest;
import com.tip.fetchinspectionreport.repository.EmailReportRepository;
import com.tip.report.util.DatatypeCommonUtility;
import com.tip.report.util.InspectionReportConstant;

@Repository
public class EmailReportRepositoryImpl implements EmailReportRepository, CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(EmailReportRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	private EmailReportRequest emailReportRequest;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> sendEmail(EmailReportRequest emailReportRequest) {

		this.emailReportRequest = emailReportRequest;
		Map<String, Object> resultMap = null;
		Map<String, Object> inspectionReportResponseMap = new HashMap<>();
		try {
			SqlParameter workPackNrSQLparam = new SqlParameter(Types.DECIMAL);
			SqlParameter workOrderNrSQLParam = new SqlParameter(Types.INTEGER);
			SqlParameter workOrderTaskNrSQLParam = new SqlParameter(Types.INTEGER);
			SqlParameter langIdSQLParam = new SqlParameter(Types.INTEGER);
			SqlParameter ssoSQLparam = new SqlParameter(Types.VARCHAR);

			List paramList = new ArrayList();
			paramList.add(workPackNrSQLparam);
			paramList.add(workOrderNrSQLParam);
			paramList.add(workOrderTaskNrSQLParam);
			paramList.add(langIdSQLParam);
			paramList.add(ssoSQLparam);

			EmailReportRepositoryImpl emailReportRepositoryImpl = new EmailReportRepositoryImpl();
			emailReportRepositoryImpl.emailReportRequest = emailReportRequest;
			emailReportRepositoryImpl.procedureCall = "{call " + InspectionReportConstant.PROC_FETCH_EMAIL_DETAILS
					+ "(?, ?, ?, ?, ?)}";

			resultMap = jdbcTemplate.call(emailReportRepositoryImpl, paramList);

			for (Map.Entry<String, Object> entry : resultMap.entrySet())
			{
				if (("#result-set-1").equalsIgnoreCase(entry.getKey())) {
					inspectionReportResponseMap.put("emailDetailsMap", entry.getValue());
				}
			}

		} catch (Exception e) {
			StackTraceElement stackTraceElement = e.getStackTrace()[0];
			logger.error("An error occurred while fetching email details of inspection report: " + e);
			logger.error("At Line:" + stackTraceElement.getClassName() + ":" + stackTraceElement.getLineNumber());
		}
		return inspectionReportResponseMap;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setBigDecimal(1, emailReportRequest.getWorkPackNr());
		DatatypeCommonUtility.checkNull(2, callableStatement, emailReportRequest.getWorkOrderNr());
		DatatypeCommonUtility.checkNull(3, callableStatement, emailReportRequest.getWorkOrderTaskNr());
		DatatypeCommonUtility.checkNull(4, callableStatement, emailReportRequest.getLangId());
		callableStatement.setString(5, emailReportRequest.getSsoId());
		connection.setAutoCommit(true);
		return callableStatement;
	}

	public void setEmailReportRequest(EmailReportRequest emailReportRequest) {
		this.emailReportRequest = emailReportRequest;
	}
}
