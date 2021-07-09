package com.tip.fetchwpextrahrs.repository.impl;

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
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.tip.fetchwpextrahrs.model.ExtraHrsRequest;
import com.tip.fetchwpextrahrs.repository.FetchWpExtraHrsRepository;
import com.tip.workorder.util.CommonUtil;
import com.tip.workorder.util.DatatypeCommonUtility;
import com.tip.workorder.util.WorkOrderConstants;

@Repository
public class FetchWpExtraHrsRepositoryImpl implements FetchWpExtraHrsRepository, CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(FetchWpExtraHrsRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	private ExtraHrsRequest extraHrsRequest;

	public void setExtraHrsRequest(ExtraHrsRequest extraHrsRequest) {
		this.extraHrsRequest = extraHrsRequest;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> getWpExtraHrs(ExtraHrsRequest extraHrsRequest) {
		Map<String, Object> resultMap = null;
		Map<String, Object> responseMap = new HashMap<>();
		try {

			SqlParameter workPackNrSql = new SqlParameter(Types.DECIMAL);
			SqlParameter langIdSql = new SqlParameter(Types.INTEGER);
			SqlOutParameter erroroutParameter = new SqlOutParameter(WorkOrderConstants.ERROR_CD, Types.VARCHAR);

			List paramList = new ArrayList();
			paramList.add(workPackNrSql);
			paramList.add(langIdSql);
			paramList.add(erroroutParameter);

			FetchWpExtraHrsRepositoryImpl lFetchWpExtraHrsRepositoryImpl = new FetchWpExtraHrsRepositoryImpl();
			lFetchWpExtraHrsRepositoryImpl.extraHrsRequest = extraHrsRequest;
			lFetchWpExtraHrsRepositoryImpl.procedureCall = "{call " + WorkOrderConstants.PROC_FETCH_WP_EXTRA_HRS
					+ CommonUtil.getQuestionMarkValueByCount(3) + "}";
			resultMap = jdbcTemplate.call((CallableStatementCreator) lFetchWpExtraHrsRepositoryImpl, paramList);
			Iterator<Entry<String, Object>> it = resultMap.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, Object> entry = it.next();
				String key = entry.getKey();
				if (("#result-set-1").equalsIgnoreCase(key)) {
					responseMap.put("WPHeader", entry.getValue());
				} else if (("#result-set-2").equalsIgnoreCase(key)) {
					responseMap.put("WOTAddTimelist", entry.getValue());
				} else if (WorkOrderConstants.ERROR_CD.equalsIgnoreCase(key)) {
					responseMap.put(WorkOrderConstants.ERROR_CD, entry.getValue());
				}
			}
		} catch (Exception e) {
			logger.error("An error occurred while fetchting WP History details." + e);
		}

		return responseMap;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setBigDecimal(1, extraHrsRequest.getWorkPackNr());
		DatatypeCommonUtility.checkNull(2, callableStatement, extraHrsRequest.getLanguageId());

		callableStatement.registerOutParameter(3, Types.VARCHAR);

		connection.setAutoCommit(true);
		return callableStatement;
	}

}
