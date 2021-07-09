package com.tip.inspection.repository.impl;

import java.math.BigDecimal;
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

import com.tip.inspection.main.InspectionCrudConstants;
import com.tip.inspection.repository.CiaImageCountRepository;

@Repository
public class CiaImageCountRepositoryImpl implements CiaImageCountRepository, CallableStatementCreator  {
	
	static final Logger logger = LoggerFactory.getLogger(CiaImageCountRepositoryImpl.class);
	
	private String procedureCall;
	
	private String inspId;
	
	public void setInspId(String inspId) {
		this.inspId = inspId;
	}

	private String inspType;
	
	public void setInspType(String inspType) {
		this.inspType = inspType;
	}

	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> fetchImageCount(String inspId , String inspType) {
		Map<String, Object> resultMap = null;
		try {
			SqlParameter inspIdParam = new SqlParameter(Types.DECIMAL);
			SqlParameter inspTypeParam = new SqlParameter(Types.VARCHAR);

			List paramList = new ArrayList();
			paramList.add(inspIdParam);
			paramList.add(inspTypeParam);
			
			CiaImageCountRepositoryImpl ciaImageCountRepositoryImpl = new CiaImageCountRepositoryImpl();
			ciaImageCountRepositoryImpl.inspId =  inspId;
			ciaImageCountRepositoryImpl.inspType = inspType;
			ciaImageCountRepositoryImpl.procedureCall = "{call "
					+ InspectionCrudConstants.PROC_GET_IMG_COUNT+ "(?,?)}";
			resultMap = jdbcTemplate.call(ciaImageCountRepositoryImpl, paramList);
		} catch (Exception e) {
			logger.error("An error occurred getting path from server: " + e);
		}
		
		return resultMap;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setBigDecimal(1, new BigDecimal(inspId));
		callableStatement.setString(2, inspType);
		connection.setAutoCommit(true);
		return callableStatement;
	}

}

	