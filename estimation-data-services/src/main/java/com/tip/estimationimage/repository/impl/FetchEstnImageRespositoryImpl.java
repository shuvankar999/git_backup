package com.tip.estimationimage.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.tip.estimationimage.model.FetchEstnImageRequest;
import com.tip.estimationimage.repository.FetchEstnImageRespository;
import com.tip.util.DatatypeCommonUtility;
import com.tip.util.EstimationConstant;



	@Repository
	public class FetchEstnImageRespositoryImpl implements FetchEstnImageRespository, CallableStatementCreator{
		
		static final Logger logger = LoggerFactory.getLogger(FetchEstnImageRespositoryImpl.class);

		@Autowired
		private JdbcTemplate jdbcTemplate;
		
		private String procedureCall;
		
		FetchEstnImageRequest fetchEstnImageRequest;

		@Autowired
		public void setDataSource(DataSource ds) {
			jdbcTemplate = new JdbcTemplate(ds);
		}
		
		public void setFetchEstnImageRequest(FetchEstnImageRequest fetchEstnImageRequest){
			this.fetchEstnImageRequest = fetchEstnImageRequest;
		}
		
		
		@SuppressWarnings({"rawtypes", "unchecked"})
		@Override
		public Map<String, Object> getEstnImage(FetchEstnImageRequest fetchEstnImageRequest) {
			  Map<String, Object> resultMap = new HashMap<>();
		        try {
		        	SqlParameter estimationIdparam = new SqlParameter(Types.NUMERIC);
					SqlParameter estnworkorderIdparam = new SqlParameter(Types.INTEGER);
					SqlParameter estnWOTIdparam = new SqlParameter(Types.INTEGER);
				
					
		            List paramList = new ArrayList();
		            paramList.add(estimationIdparam);
					paramList.add(estnworkorderIdparam);
					paramList.add(estnWOTIdparam);
				
					FetchEstnImageRespositoryImpl fetchEstnImageRespositoryImpl = new FetchEstnImageRespositoryImpl();
					fetchEstnImageRespositoryImpl.fetchEstnImageRequest = fetchEstnImageRequest;
					fetchEstnImageRespositoryImpl.procedureCall = "{call " + EstimationConstant.PROC_FETCH_ESTN_IMAGES + "(?,?,?)}";
		            resultMap = jdbcTemplate.call((CallableStatementCreator) fetchEstnImageRespositoryImpl, paramList);
		        } catch (Exception e) {
		        	logger.error("Error Encountered in fetching Estimation Images " ,e);
		        }
		        return resultMap;
		    }
		
		
		@Override
		public CallableStatement createCallableStatement(Connection connection) throws SQLException {
			 connection.setAutoCommit(false);
		        CallableStatement callableStatement = connection.prepareCall(procedureCall);
		        callableStatement.setBigDecimal(1, fetchEstnImageRequest.getEstimationId());
		        DatatypeCommonUtility.checkNull(2, callableStatement,fetchEstnImageRequest.getEstnworkorderId());
		        DatatypeCommonUtility.checkNull(3, callableStatement,fetchEstnImageRequest.getEstnWOTId());        
		        connection.setAutoCommit(true);
		        return callableStatement;
		}
	
	}
