package com.tip.repository.impl;

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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.tip.model.SessionRequest;
import com.tip.repository.ValidateSessionRepository;

@Repository
public class ValidateSessionRepositoryImpl implements ValidateSessionRepository {

	final static Logger logger = Logger.getLogger(ValidateSessionRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Map<String, Object> getValidateSessionDetails(SessionRequest sessionRequest) {
		Connection connection = null;
		Map<String, Object> resultMap = null;
		Map<String, Object> sessionResponsemap = new HashMap<String, Object>();
		try {

			connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());

			SqlParameter ssoIdSql = new SqlParameter(Types.VARCHAR);
			SqlParameter sessionIdSql = new SqlParameter(Types.VARCHAR);
			SqlParameter appCdSql = new SqlParameter(Types.VARCHAR);

			List paramList = new ArrayList();
			paramList.add(ssoIdSql);
			paramList.add(sessionIdSql);
			paramList.add(appCdSql);

			String procedureCall = "{call Wshp_Session_Validate(?,?,?)}";

			resultMap = jdbcTemplate.call(new CallableStatementCreator() {
				@Override
				public CallableStatement createCallableStatement(Connection connection) throws SQLException {
					connection.setAutoCommit(false);
					CallableStatement callableStatement = connection.prepareCall(procedureCall);
					callableStatement.setString(1, sessionRequest.getSsoId());
					callableStatement.setString(2, sessionRequest.getSessionId());
					callableStatement.setString(3, sessionRequest.getAppCd());

					connection.setAutoCommit(true);
					return callableStatement;

				}
			}, paramList);
		} catch (Exception e) {
			logger.error("An error occurred while validating session" + e);
		}

		sessionResponsemap.put("Validate_Seesion_Response", resultMap.get("#result-set-1"));

		return sessionResponsemap;
	}

}
