package com.tip.estimation.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.tip.estimation.model.RebillDetails;
import com.tip.estimation.repository.ImmediateInvGenerateRepository;
import com.tip.estimation.util.EstimationConstant;

@Repository
public class ImmediateInvGenerateRepositoryImpl implements ImmediateInvGenerateRepository, CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(ImmediateInvGenerateRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	private RebillDetails rebillDetails;

	@SuppressWarnings({ "unchecked", "rawtypes"})
	@Override
	public Map<String, Object> generateInv(RebillDetails rebillDetails) {
		Map<String, Object> resultMap = null;
		Map<String, Object> returnMap = null;
		try {
			SqlParameter invoiceDateParam = new SqlParameter(Types.VARCHAR);
			SqlParameter estimationIdParam = new SqlParameter(Types.VARCHAR);

			List paramList = new ArrayList();

			paramList.add(invoiceDateParam);
			paramList.add(estimationIdParam);

			ImmediateInvGenerateRepositoryImpl immediateInvGenerateRepositoryImpl = new ImmediateInvGenerateRepositoryImpl();
			immediateInvGenerateRepositoryImpl.rebillDetails = rebillDetails;

			immediateInvGenerateRepositoryImpl.procedureCall = "{call " + EstimationConstant.PROC_IMMEDIATE_INVC_GENERATE
					+ " (?,?)}";

			logger.info("Executing stored proceudre..." + EstimationConstant.PROC_IMMEDIATE_INVC_GENERATE);
			resultMap = jdbcTemplate.call(immediateInvGenerateRepositoryImpl, paramList);

			if (null != resultMap) {
				for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
					if ("#result-set-2".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						returnMap = lst.get(0);
					}
				}
			}

		} catch (Exception e) {
			logger.error("An error occurred" + e);
			e.printStackTrace();

		}
		logger.info("Result object..."+returnMap);
		return returnMap;
	}
	
	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setString(1, getSystemDate());
		callableStatement.setString(2, rebillDetails.getEstimationId().toPlainString());
		connection.setAutoCommit(true);
		return callableStatement;
	}
	
	private String getSystemDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return formatter.format(date);
	}

}
