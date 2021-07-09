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

import com.tip.estimation.model.FetchEstnPopupDetailsRequest;
import com.tip.estimation.repository.FetchPopupListRepository;
import com.tip.util.EstimationConstant;

@Repository
public class FetchPopupListRepositoryImpl implements FetchPopupListRepository,CallableStatementCreator{
	
	static final Logger logger = LoggerFactory.getLogger(FetchPopupListRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;

	private FetchEstnPopupDetailsRequest fetchEstnPopupDetailsRequest;

	public void setFetchEstimationPopupDtailsRequest(FetchEstnPopupDetailsRequest fetchEstnPopupDetailsRequest) {
		this.fetchEstnPopupDetailsRequest = fetchEstnPopupDetailsRequest;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String,Object> getPopupList(FetchEstnPopupDetailsRequest fetchEstnPopupDetailsRequest) {
		Map<String, Object> resultMap = null;
		try {
			SqlParameter estimationIdParam = new SqlParameter(Types.NUMERIC);
			
			List paramList = new ArrayList();
			paramList.add(estimationIdParam);
			
			FetchPopupListRepositoryImpl fetchPopupListRepositoryImpl = new FetchPopupListRepositoryImpl();
			fetchPopupListRepositoryImpl.fetchEstnPopupDetailsRequest = fetchEstnPopupDetailsRequest;
			fetchPopupListRepositoryImpl.procedureCall = "{call "
					+ EstimationConstant.PROC_ESTIMATION_FETCH_POPUP_LIST + "(?)}";
			resultMap = jdbcTemplate.call(fetchPopupListRepositoryImpl, paramList);
		} catch (Exception e) {
			logger.error("An error occurred while fetching estimation popup lists: " , e);
		}
		return resultMap;
	}
	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setBigDecimal(1, fetchEstnPopupDetailsRequest.getEstimationId());
		connection.setAutoCommit(true);
		return callableStatement;
	}
	
}