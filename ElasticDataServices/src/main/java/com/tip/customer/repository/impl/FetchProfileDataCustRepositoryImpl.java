package com.tip.customer.repository.impl;

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

import com.tip.customer.repository.FetchProfileDataCustRepository;
import com.tip.elastic.util.ElasticSearchConstant;

@Repository
public class FetchProfileDataCustRepositoryImpl implements FetchProfileDataCustRepository, CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(FetchProfileDataCustRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	
	private String ssoId;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Integer> getDefaultProfileData(String ssoId){

		this.ssoId = ssoId;

		Map<String, Object> resultMap = null;
		List<Integer> listOfGroupNr = new ArrayList();
		try {

			
			SqlParameter ssoUserIdSqlParam = new SqlParameter(Types.VARCHAR);

			List paramList = new ArrayList();
			paramList.add(ssoUserIdSqlParam);

			FetchProfileDataCustRepositoryImpl fetchProfileDataCustRepositoryImpl = new FetchProfileDataCustRepositoryImpl();
			fetchProfileDataCustRepositoryImpl.ssoId = ssoId;
			fetchProfileDataCustRepositoryImpl.procedureCall = "{call " + ElasticSearchConstant.PROC_CUSTOMER_SEARCH_DEFAULT + " (?)}";
			resultMap = jdbcTemplate.call(fetchProfileDataCustRepositoryImpl, paramList);

		} catch (Exception e) {
			logger.error("An error occurred" + e);
		}

		if (null != resultMap) {
			for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
				if (("#result-set-1").equalsIgnoreCase(entry.getKey())) {
					for(Map<String, Object> mapObj : (List<Map<String, Object>>) entry.getValue()) {
						listOfGroupNr.add((Integer)mapObj.get("groupNrs"));
					}
				}
			}
		}

		return listOfGroupNr;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setString(1, ssoId);
		connection.setAutoCommit(true);
		return callableStatement;
	}

}
