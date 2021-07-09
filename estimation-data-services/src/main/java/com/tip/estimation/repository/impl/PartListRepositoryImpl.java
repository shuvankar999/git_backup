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

import com.tip.estimation.model.PartListRequest;
import com.tip.estimation.repository.PartListRepository;
import com.tip.util.DatatypeCommonUtility;
import com.tip.util.EstimationConstant;

@Repository
public class PartListRepositoryImpl implements PartListRepository, CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(PartListRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private String procedureCall;
	
	private PartListRequest partListRequest;

	public void setPartListRequest(PartListRequest partListRequest) {
		this.partListRequest = partListRequest;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> getPartList(PartListRequest partListRequest) {
		Map<String, Object> resultMap = null;
		try {

			SqlParameter estIdparam = new SqlParameter(Types.DECIMAL);
			SqlParameter langIdparam = new SqlParameter(Types.INTEGER);

			List paramList = new ArrayList();

			paramList.add(estIdparam);
			paramList.add(langIdparam);

			PartListRepositoryImpl partListRepositoryImpl = new PartListRepositoryImpl();
			partListRepositoryImpl.partListRequest = partListRequest;
			partListRepositoryImpl.procedureCall = "{call " + EstimationConstant.PROC_FETCH_PARTS_LIST
					+ " (?,?)}";
			resultMap = jdbcTemplate.call(partListRepositoryImpl, paramList);
		} catch (Exception e) {
			logger.error("An error occurred while fetching estimation part lists: ", e);
		}
		return resultMap;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setBigDecimal(1, partListRequest.getEstimationId());
		DatatypeCommonUtility.checkNull(2, callableStatement, partListRequest.getLangId());
	
		connection.setAutoCommit(true);
		return callableStatement;
	}
}
