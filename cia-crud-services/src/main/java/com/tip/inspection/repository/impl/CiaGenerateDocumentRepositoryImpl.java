package com.tip.inspection.repository.impl;

import java.math.BigDecimal;
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

import com.tip.inspection.main.InspectionCrudConstants;
import com.tip.inspection.repository.CiaGenerateDocumentRepository;

@Repository
public class CiaGenerateDocumentRepositoryImpl implements  CiaGenerateDocumentRepository , CallableStatementCreator{
static final Logger logger = LoggerFactory.getLogger(CiaImagePathRepositoryImpl.class);
	
	private String procedureCall;
	
	private BigDecimal inspCd;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getDocumentData(BigDecimal inspCd) {
		Map<String, Object> resultMap = null;
		Map<String, Object> resultOutMap = new HashMap<>();
		try {
			SqlParameter inspCdParam = new SqlParameter(Types.DECIMAL);

			@SuppressWarnings("rawtypes")
			List paramList = new ArrayList();
			paramList.add(inspCdParam);
			
			CiaGenerateDocumentRepositoryImpl ciaGenerateDocumentRepositoryImpl = new CiaGenerateDocumentRepositoryImpl();
			ciaGenerateDocumentRepositoryImpl.inspCd = inspCd;
			ciaGenerateDocumentRepositoryImpl.procedureCall = "{call "
					+ InspectionCrudConstants.PROC_GET_REPORT_DATA+ " (?)}";
			resultMap = jdbcTemplate.call(ciaGenerateDocumentRepositoryImpl, paramList);
			for (Map.Entry<String, Object> entry : resultMap.entrySet())
			{	
				if (("#result-set-4").equalsIgnoreCase(entry.getKey())) {
					resultOutMap.put("InspectionImageDataList", entry.getValue());
				}else if (("#result-set-5").equalsIgnoreCase(entry.getKey())) {
					resultOutMap.put("status", entry.getValue());
				}else if (("#result-set-1").equalsIgnoreCase(entry.getKey())) {
					resultOutMap.put("InspectionDetailsData", entry.getValue());
				}else if (("#result-set-2").equalsIgnoreCase(entry.getKey())) {
					resultOutMap.put("InspectionItemsList", entry.getValue());
				}else if (("#result-set-3").equalsIgnoreCase(entry.getKey())) {
					resultOutMap.put("InspectionDamageDataList", entry.getValue());
				}
			}
		
		} catch (Exception e) {
			logger.error("An error occurred getting path from server: " + e);
		}
		
		return resultOutMap;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setBigDecimal(1, inspCd);
		connection.setAutoCommit(true);
		return callableStatement;
	}

}
