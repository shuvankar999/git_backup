package com.tip.fetchaxle.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.tip.asset.util.AssetConstants;
import com.tip.fetchaxle.model.FetchAxleRequest;
import com.tip.fetchaxle.model.FetchAxleResponse;
import com.tip.fetchaxle.repository.FetchAxleRepository;

@Repository
public class FetchAxleRepositoryImpl implements FetchAxleRepository ,CallableStatementCreator {

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
	public FetchAxleResponse fetchNoOfAxleForAsset(FetchAxleRequest fetchAxleRequest) {
		this.fetchAxleRequest = fetchAxleRequest;

		FetchAxleResponse fetchAxleResponse = new FetchAxleResponse();

		try {
			Map<String, Object> resultMap;

			Map<String, Object> fetchAxleMap = new HashMap<>();

			SqlParameter unitNrSQLparam = new SqlParameter(Types.INTEGER);
			SqlParameter errorCdSQLparam = new SqlParameter(Types.VARCHAR);

			List paramList = new ArrayList();
			paramList.add(unitNrSQLparam);
			paramList.add(errorCdSQLparam);
			
			FetchAxleRepositoryImpl lFetchAxleRepositoryImpl = new FetchAxleRepositoryImpl();
			lFetchAxleRepositoryImpl.fetchAxleRequest = fetchAxleRequest;
			lFetchAxleRepositoryImpl.procedureCall = "{call " + AssetConstants.PROC_FETCH_NO_OF_AXLES + "(?, ?)}";

			resultMap = jdbcTemplate.call(lFetchAxleRepositoryImpl, paramList);
			if(resultMap!=null)
			{
				setResult(resultMap,fetchAxleMap);
			}
			fetchAxleResponse.setFetchAxleMap(fetchAxleMap);
		} catch (Exception e) {
			logger.error("An error occurred while fetching no of axle for asset : " + e);
		}
		return fetchAxleResponse;
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
	
	private void setResult(Map<String, Object> resultMap, Map<String, Object> fetchAxleMap){
		for (Map.Entry<String, Object> entry : resultMap.entrySet())
		{
			String key = entry.getKey();
			if (("#result-set-1").equalsIgnoreCase(key)) {
				fetchAxleMap.put("AxleDetails", entry.getValue());
			} else if (("Error_Cd").equalsIgnoreCase(key)) {
				fetchAxleMap.put("Error_Cd", entry.getValue());
			}
		}
	}
}
