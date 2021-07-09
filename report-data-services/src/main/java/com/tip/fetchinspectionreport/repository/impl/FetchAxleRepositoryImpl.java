package com.tip.fetchinspectionreport.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.tip.fetchinspectionreport.model.FetchAxleRequest;
import com.tip.fetchinspectionreport.repository.FetchAxleRepository;
import com.tip.report.util.InspectionReportConstant;

@Repository
public class FetchAxleRepositoryImpl implements FetchAxleRepository, CallableStatementCreator {

	static final Logger logger = Logger.getLogger(FetchAxleRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private FetchAxleRequest fetchAxleRequest;

	private String procedureCall;

	@Autowired
	public void setDataSource(DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object fetchNoOfAxleForAsset(FetchAxleRequest fetchAxleRequest) {
		this.fetchAxleRequest = fetchAxleRequest;
		Object noOfAxles = null;

		try {
			Map<String, Object> resultMap;

			SqlParameter unitNrSQLparam = new SqlParameter(Types.INTEGER);
			SqlParameter errorCdSQLparam = new SqlParameter(Types.VARCHAR);

			List paramList = new ArrayList();
			paramList.add(unitNrSQLparam);
			paramList.add(errorCdSQLparam);

			FetchAxleRepositoryImpl lFetchAxleRepositoryImpl = new FetchAxleRepositoryImpl();
			lFetchAxleRepositoryImpl.fetchAxleRequest = fetchAxleRequest;
			lFetchAxleRepositoryImpl.procedureCall = "{call " + InspectionReportConstant.PROC_FETCH_NO_OF_AXLES
					+ "(?, ?)}";

			resultMap = jdbcTemplate.call(lFetchAxleRepositoryImpl, paramList);

			for (Map.Entry<String, Object> entry : resultMap.entrySet())
			{
				if (("#result-set-1").equalsIgnoreCase(entry.getKey())){
				noOfAxles = getNoOfAxles((List<Map<String, String>>)entry.getValue());
				}
			}
		} catch (Exception e) {
			logger.error("An error occurred while fetching no of axle for asset : " + e);
		}
		return noOfAxles;
	}
	
	public Object getNoOfAxles(List<Map<String, String>> axleMapLst) throws SQLException {
		Object noOfAxles = null;
		if (axleMapLst.get(0).get("No_Of_Axles") != null) {
				noOfAxles = axleMapLst.get(0).get("No_Of_Axles");
		}
		return noOfAxles;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setDouble(1, fetchAxleRequest.getUnitNr());
		callableStatement.setString(2, "NULL");
		connection.setAutoCommit(true);
		return callableStatement;
	}

	public void setFetchAxleRequest(FetchAxleRequest fetchAxleRequest) {
		this.fetchAxleRequest = fetchAxleRequest;
	}
}
