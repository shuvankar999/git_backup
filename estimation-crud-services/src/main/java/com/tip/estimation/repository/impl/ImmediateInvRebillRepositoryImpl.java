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

import com.tip.estimation.model.RebillDetails;
import com.tip.estimation.repository.ImmediateInvRebillRepository;
import com.tip.estimation.util.DatatypeCommonUtility;
import com.tip.estimation.util.EstimationConstant;

	@Repository
	public class ImmediateInvRebillRepositoryImpl  implements ImmediateInvRebillRepository, CallableStatementCreator{
	
	static final Logger logger = LoggerFactory.getLogger(ImmediateInvRebillRepositoryImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	private RebillDetails rebillDetails;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Object> saveRebillSave(RebillDetails rebillDetails) {
		Map<String, Object> resultMap= null;
		Map<String, Object> returnMap= null;
		try {
			SqlParameter estimationIdParam = new SqlParameter(Types.NUMERIC);
			SqlParameter rebilIdParam = new SqlParameter(Types.INTEGER);
			SqlParameter ssoIdParam = new SqlParameter(Types.VARCHAR);
			
			
			List paramList = new ArrayList();

			paramList.add(estimationIdParam);
			paramList.add(rebilIdParam);
			paramList.add(ssoIdParam);
			
			
			ImmediateInvRebillRepositoryImpl immediateInvHeaderRepositoryImpl = new ImmediateInvRebillRepositoryImpl();
			immediateInvHeaderRepositoryImpl.rebillDetails = rebillDetails;

			immediateInvHeaderRepositoryImpl.procedureCall = "{call "+ EstimationConstant.PROC_IMMEDIATE_INVC_SAVE_REBILL + " (?,?,?)}";
		
			logger.info("Executing procedure..."+EstimationConstant.PROC_IMMEDIATE_INVC_SAVE_REBILL);
		resultMap = jdbcTemplate.call(immediateInvHeaderRepositoryImpl, paramList);
		
		if (null != resultMap) {
			for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
				if ("#result-set-1".equalsIgnoreCase(entry.getKey())) {
					List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
					returnMap = lst.get(0);					
				}
			}
		}
		
		}catch (Exception e) {
			logger.error("An error occurred" + e);
			
		
		}
		logger.info("Result object..."+returnMap);
		return returnMap;
	}
	
	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setBigDecimal(1, rebillDetails.getEstimationId());
		DatatypeCommonUtility.checkNull(2, callableStatement, rebillDetails.getRebillNr());
		callableStatement.setString(3, rebillDetails.getSsoId());		
		connection.setAutoCommit(true);
		return callableStatement;
		
	}

}
