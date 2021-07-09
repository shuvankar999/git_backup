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

import com.tip.estimation.model.HeaderData;
import com.tip.estimation.model.RebillDetails;
import com.tip.estimation.repository.ImmediateInvHeaderRepository;
import com.tip.estimation.util.DatatypeCommonUtility;
import com.tip.estimation.util.EstimationConstant;

@Repository
public class ImmediateInvHeaderRepositoryImpl implements ImmediateInvHeaderRepository, CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(ImmediateInvHeaderRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	private HeaderData headerData;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public RebillDetails saveRebillHeader(HeaderData headerData) {
		RebillDetails rebill = new RebillDetails();
		Map<String, Object> resultMap = null;
		try {
			SqlParameter estimationIdParam = new SqlParameter(Types.NUMERIC);
			SqlParameter intchKeyParam = new SqlParameter(Types.NUMERIC);
			SqlParameter tipAssetNrParam = new SqlParameter(Types.INTEGER);
			SqlParameter custNrParam = new SqlParameter(Types.INTEGER);
			SqlParameter insurerNrParam = new SqlParameter(Types.INTEGER);
			SqlParameter flagParam = new SqlParameter(Types.VARCHAR);
			SqlParameter incidentNrParam = new SqlParameter(Types.VARCHAR);
			SqlParameter ssoIdParam = new SqlParameter(Types.VARCHAR);

			List paramList = new ArrayList();

			paramList.add(estimationIdParam);
			paramList.add(intchKeyParam);
			paramList.add(tipAssetNrParam);
			paramList.add(custNrParam);
			paramList.add(insurerNrParam);
			paramList.add(flagParam);
			paramList.add(incidentNrParam);
			paramList.add(ssoIdParam);

			ImmediateInvHeaderRepositoryImpl immediateInvHeaderRepositoryImpl = new ImmediateInvHeaderRepositoryImpl();
			immediateInvHeaderRepositoryImpl.headerData = headerData;

			immediateInvHeaderRepositoryImpl.procedureCall = "{call "
					+ EstimationConstant.PROC_IMMEDIATE_INVC_SAVE_HEADER + " (?,?,?,?,?,?,?,?)}";

			resultMap = jdbcTemplate.call(immediateInvHeaderRepositoryImpl, paramList);

			if (null != resultMap) {
				for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
					if ("#result-set-1".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> rebillMap = lst.get(i);
							rebill.setErrorMsg((String) rebillMap.get("Msg"));
							rebill.setRebillNr((Integer) rebillMap.get("rebillId"));
							rebill.setEstimationId(headerData.getEstimationId());
							rebill.setSsoId(headerData.getSsoId());
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("An error occurred" + e);
		}

		return rebill;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setBigDecimal(1, headerData.getEstimationId());
		callableStatement.setBigDecimal(2, headerData.getIntchKey());
		DatatypeCommonUtility.checkNull(3, callableStatement, headerData.getTipAssetNr());
		DatatypeCommonUtility.checkNull(4, callableStatement, headerData.getCustNr());
		DatatypeCommonUtility.checkNull(5, callableStatement, headerData.getInsurerNr());
		callableStatement.setString(6, null);
		callableStatement.setString(7, headerData.getIncidentNr());
		callableStatement.setString(8, headerData.getSsoId());

		connection.setAutoCommit(true);
		return callableStatement;
	}
}
