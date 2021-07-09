package com.tip.fetchinspectionreport.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.tip.fetchinspectionreport.model.InspectionReportRequest;
import com.tip.fetchinspectionreport.repository.InspectionReportRepository;
import com.tip.report.util.DatatypeCommonUtility;
import com.tip.report.util.InspectionReportConstant;

@Repository
public class InspectionReportRepositoryImpl implements InspectionReportRepository, CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(InspectionReportRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	private InspectionReportRequest inspectionReportRequest;

	@Autowired
	public void setDataSource(DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> getInspectionReport(InspectionReportRequest inspectionReportRequest) {

		this.inspectionReportRequest = inspectionReportRequest;
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

			InspectionReportRepositoryImpl lInspectionReportRepositoryImpl = new InspectionReportRepositoryImpl();
			lInspectionReportRepositoryImpl.inspectionReportRequest = inspectionReportRequest;
			lInspectionReportRepositoryImpl.procedureCall = "{call "
					+ InspectionReportConstant.PROC_FETCH_INSPECTION_REPORT + "(?, ?, ?, ?, ?)}";

			resultMap = jdbcTemplate.call(lInspectionReportRepositoryImpl, paramList);

			for (Map.Entry<String, Object> entry : resultMap.entrySet())
			{
				String key = entry.getKey();
				if (("#result-set-1").equalsIgnoreCase(key)) {
					inspectionReportResponseMap.put("InspTranslist", entry.getValue());
				} else if (("#result-set-2").equalsIgnoreCase(key)) {
					inspectionReportResponseMap.put("InspHeaderlist", entry.getValue());
				} else if (("#result-set-3").equalsIgnoreCase(key)) {
					inspectionReportResponseMap.put("InspChecklist", entry.getValue());
				} else if (("#result-set-4").equalsIgnoreCase(key)) {
					inspectionReportResponseMap.put("InspDefectlist", entry.getValue());
				} else if (("#result-set-5").equalsIgnoreCase(key)) {
					inspectionReportResponseMap.put("InspTyrelist", entry.getValue());
				} else if (("#result-set-6").equalsIgnoreCase(key)) {
					inspectionReportResponseMap.put("InspBrakelist", entry.getValue());
				} else if (("#result-set-7").equalsIgnoreCase(key)) {
					inspectionReportResponseMap.put("Signlist", entry.getValue());
				}
			}
		} catch (Exception e) {
			StackTraceElement stackTraceElement = e.getStackTrace()[0];
			logger.error("An error occurred while fetching inspection report: " + e);
			logger.error("At Line:" + stackTraceElement.getClassName() + ":" + stackTraceElement.getLineNumber());
		}
		return inspectionReportResponseMap;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setBigDecimal(1, inspectionReportRequest.getWorkPackNr());
		DatatypeCommonUtility.checkNull(2, callableStatement, inspectionReportRequest.getWorkOrderNr());
		DatatypeCommonUtility.checkNull(3, callableStatement, inspectionReportRequest.getWorkOrderTaskNr());
		DatatypeCommonUtility.checkNull(4, callableStatement, inspectionReportRequest.getLangId());
		callableStatement.setString(5, inspectionReportRequest.getSsoId());
		connection.setAutoCommit(true);
		return callableStatement;
	}

	public void setInspectionReportRequest(InspectionReportRequest inspectionReportRequest) {
		this.inspectionReportRequest = inspectionReportRequest;
	}
}
