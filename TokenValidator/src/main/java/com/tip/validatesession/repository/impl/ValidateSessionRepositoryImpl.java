package com.tip.validatesession.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
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

import com.tip.validatesession.model.SessionDetails;
import com.tip.validatesession.repository.ValidateSessionRepository;

@Repository
public class ValidateSessionRepositoryImpl implements ValidateSessionRepository, CallableStatementCreator{
	
	
	static final Logger logger = LoggerFactory.getLogger(ValidateSessionRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private String procedureCall;
	
	private SessionDetails sessionDetails;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Object> validateSessionDetails(SessionDetails sessionDetails) {

		this.sessionDetails = sessionDetails;
		
		Map<String, Object> resultMap = null;
		List<Object> sessionMessageList = null;
		try {

			SqlParameter ssoIdSql = new SqlParameter(Types.VARCHAR);
			SqlParameter sessionIdSql = new SqlParameter(Types.VARCHAR);
			SqlParameter appCdSql = new SqlParameter(Types.VARCHAR);

			List paramList = new ArrayList();
			paramList.add(ssoIdSql);
			paramList.add(sessionIdSql);
			paramList.add(appCdSql);

			ValidateSessionRepositoryImpl validateSessionRepositoryImpl = new ValidateSessionRepositoryImpl();
			validateSessionRepositoryImpl.procedureCall = "{call Wshp_Session_Validate(?,?,?)}";
			validateSessionRepositoryImpl.sessionDetails = sessionDetails;

			resultMap = jdbcTemplate.call(validateSessionRepositoryImpl, paramList);

			Iterator<Entry<String, Object>> it = resultMap.entrySet().iterator();

			while (it.hasNext()) {
				Map.Entry<String, Object> entry = it.next();
				String key = entry.getKey();
				
				System.out.println("Key>>>"+key+"\n value>>>"+entry.getValue());
				if (("#result-set-1").equalsIgnoreCase(key)) {
					sessionMessageList = (List<Object>) entry.getValue();
				}
			}
			
		} catch (Exception e) {
			logger.error("An error occurred while validating session" + e);
		}

		return sessionMessageList;
	}
	
	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setString(1, sessionDetails.getSsoId());
		callableStatement.setString(2, sessionDetails.getSessionId());
		callableStatement.setString(3, sessionDetails.getAppCd());

		connection.setAutoCommit(true);
		return callableStatement;

	}

	public void setSessionDetails(SessionDetails sessionDetails) {
		this.sessionDetails = sessionDetails;
	}

}
