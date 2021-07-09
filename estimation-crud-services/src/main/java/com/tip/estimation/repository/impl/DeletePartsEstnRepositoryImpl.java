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

import com.tip.estimation.model.DeletePartsRequest;
import com.tip.estimation.repository.DeletePartsEstnRepository;
import com.tip.estimation.util.DatatypeCommonUtility;
import com.tip.estimation.util.EstimationConstant;

@Repository
public class DeletePartsEstnRepositoryImpl implements DeletePartsEstnRepository , CallableStatementCreator{
	
static final Logger logger = LoggerFactory.getLogger(DeletePartsEstnRepositoryImpl.class);

	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	private DeletePartsRequest deletePartsRequest;


	public void setSaveEstnOtherReadingRequest(DeletePartsRequest deletePartsRequest) {
		this.deletePartsRequest = deletePartsRequest;
	}
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> deleteParts(DeletePartsRequest deletePartsRequest) {
			
		Map<String, Object> resultMap= null;
		
		try {
			List<SqlParameter> paramList = new ArrayList();

            SqlParameter estimationIdParam = new SqlParameter(Types.DECIMAL);
		    SqlParameter estnWOIdParam = new SqlParameter(Types.INTEGER);
		    SqlParameter estnWOTIdParam = new SqlParameter(Types.INTEGER);
		    SqlParameter partNumberParam = new SqlParameter(Types.VARCHAR);
		    SqlParameter ssoIdParam = new SqlParameter(Types.VARCHAR);
		    
		    paramList.add(estimationIdParam);
		    paramList.add(estnWOIdParam);
		    paramList.add(estnWOTIdParam);
		    paramList.add(partNumberParam);
		    paramList.add(ssoIdParam);
			
		    DeletePartsEstnRepositoryImpl deletePartsEstnRepositoryImpl = new DeletePartsEstnRepositoryImpl();
		    deletePartsEstnRepositoryImpl.deletePartsRequest = deletePartsRequest;

		    deletePartsEstnRepositoryImpl.procedureCall = "{call "+ EstimationConstant.PROC_ESTIMATION_DELETE_PARTS + " (?,?,?,?,?)}";
		
		
		resultMap = jdbcTemplate.call(deletePartsEstnRepositoryImpl, paramList);
		}catch (Exception e) {
			logger.error("An error occurred while Deleting Estimation Parts: " + e);
			
		
		}
		
		return resultMap;
				
	}


	
	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		
		callableStatement.setBigDecimal(1, deletePartsRequest.getEstimationId());
		DatatypeCommonUtility.checkNull(2, callableStatement, deletePartsRequest.getEstnWOId());
		DatatypeCommonUtility.checkNull(3, callableStatement, deletePartsRequest.getEstnWOTId());
		callableStatement.setString(4, deletePartsRequest.getPartNumber());
		callableStatement.setString(5, deletePartsRequest.getSsoId());
		connection.setAutoCommit(true);
		return callableStatement;
	}
	

}
