package com.tip.estimation.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.tip.estimation.model.VoidEstnWotRequest;
import com.tip.estimation.model.VoidEstnWotResponse;
import com.tip.estimation.repository.VoidEstimationWotRepository;
import com.tip.estimation.util.DatatypeCommonUtility;
import com.tip.estimation.util.EstimationConstant;

@Repository
public class VoidEstimationWotRepositoryImpl implements VoidEstimationWotRepository, CallableStatementCreator {
	
	VoidEstnWotResponse voidEstnWotResponse = new VoidEstnWotResponse();
	
	static final Logger logger = LoggerFactory.getLogger(VoidEstimationWotRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	private VoidEstnWotRequest voidEstnWotRequest;
	
	public void setVoidEstnWotRequest(VoidEstnWotRequest voidEstnWotRequest) {
		this.voidEstnWotRequest = voidEstnWotRequest;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Object> voidEstnWot(VoidEstnWotRequest voidEstnWotRequest) {
		 Map<String, Object> resultMap = new HashMap<>();
		 
	        try {
	        	
	        	List<SqlParameter> paramList = new ArrayList();

	            SqlParameter estimationIdParam = new SqlParameter(Types.DECIMAL);
			    SqlParameter estnworkorderIdParam = new SqlParameter(Types.INTEGER);
			    SqlParameter estnWOTIdParam = new SqlParameter(Types.INTEGER);
			    SqlParameter ssoIdParam = new SqlParameter(Types.VARCHAR);
			    
			    paramList.add(estimationIdParam);
			    paramList.add(estnworkorderIdParam);
			    paramList.add(estnWOTIdParam);
			    paramList.add(ssoIdParam);
		
			    
			    VoidEstimationWotRepositoryImpl voidEstimationWotRepositoryImpl = new VoidEstimationWotRepositoryImpl();
			    voidEstimationWotRepositoryImpl.voidEstnWotRequest = voidEstnWotRequest;
			    voidEstimationWotRepositoryImpl.procedureCall = "{call " + EstimationConstant.PROC_ESTIMATION_VOID_WOT + "(?,?,?,?)}";
			  
			    
			    resultMap = jdbcTemplate.call(voidEstimationWotRepositoryImpl,paramList);
				
			    setResponse(resultMap);
				
	        } catch (Exception e) {
	        	logger.error("Error Encountered " ,e);
	        }
	        
		return resultMap;
	}
	
	
	private void setResponse(Map<String, Object> resultMap) {
		for (Map.Entry<String, Object> entry : resultMap.entrySet())
		{
			if("#result-set-1".equalsIgnoreCase(entry.getKey()))
	        {
	        	List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
				for(int i=0;i < lst.size();i++)
	        	{
	        		Map<String, Object> voidMap = lst.get(i);
	        		resultMap.putAll(voidMap);
	        	}
	        }
		}
	
	}
	
	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		
		callableStatement.setBigDecimal(1, voidEstnWotRequest.getEstimationId());
		DatatypeCommonUtility.checkNull(2, callableStatement, voidEstnWotRequest.getEstnworkorderId());
		DatatypeCommonUtility.checkNull(3, callableStatement, voidEstnWotRequest.getEstnWOTId());
		callableStatement.setString(4, voidEstnWotRequest.getSsoId());
		connection.setAutoCommit(true);
		return callableStatement;
	}

}
