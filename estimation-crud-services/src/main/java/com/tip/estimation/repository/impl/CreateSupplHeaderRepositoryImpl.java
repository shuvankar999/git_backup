package com.tip.estimation.repository.impl;

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

import com.tip.estimation.model.VersionObject;
import com.tip.estimation.repository.CreateSupplHeaderRepository;
import com.tip.estimation.util.DatatypeCommonUtility;
import com.tip.estimation.util.EstimationConstant;

@Repository
public class CreateSupplHeaderRepositoryImpl implements CreateSupplHeaderRepository, CallableStatementCreator{

	static final Logger logger = LoggerFactory.getLogger(CreateSupplHeaderRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	private String ssoId;
	private BigDecimal estimationId;
	private Integer supplementary;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public VersionObject saveHeaderSuppl(BigDecimal estimationId, String ssoId, Integer supplementary) {
		Map<String, Object> resultMap= null;		
		VersionObject versionObject = new VersionObject();
		try {
			SqlParameter estimationIdParam = new SqlParameter(Types.NUMERIC);
			SqlParameter supplParam = new SqlParameter(Types.INTEGER);
			SqlParameter versionParam = new SqlParameter(Types.INTEGER);
			SqlParameter ssoIdParam = new SqlParameter(Types.VARCHAR);
			
			
			List paramList = new ArrayList();

			paramList.add(estimationIdParam);
			paramList.add(supplParam);
			paramList.add(versionParam);
			paramList.add(ssoIdParam);
			
			
			CreateSupplHeaderRepositoryImpl createSupplHeaderRepositoryImpl = new CreateSupplHeaderRepositoryImpl();
			createSupplHeaderRepositoryImpl.ssoId = ssoId;
			createSupplHeaderRepositoryImpl.estimationId = estimationId;
			createSupplHeaderRepositoryImpl.supplementary = supplementary;

			createSupplHeaderRepositoryImpl.procedureCall = "{call "+ EstimationConstant.PROC_CREATE_SUPPL_HEADER + " (?,?,?,?)}";
		
		
		resultMap = jdbcTemplate.call(createSupplHeaderRepositoryImpl, paramList);
		
		if (null != resultMap) {
			for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
				if ("#result-set-1".equalsIgnoreCase(entry.getKey())) {
					List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
					for (int i = 0; i < lst.size(); i++) {
						Map<String, Object> versionMap = lst.get(i);
						versionObject.setVersion((Integer) versionMap.get("version"));
						versionObject.setSupplementary((Integer) versionMap.get("supplementary"));
						
					}
					
				}
			}
		}
		
		}catch (Exception e) {
			logger.error("An error occurred while saving suppl header " + e);
		
		}
		
		return versionObject;
	}
	
	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		Integer version = null;
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setBigDecimal(1, estimationId);
		DatatypeCommonUtility.checkNull(2, callableStatement, supplementary);
		DatatypeCommonUtility.checkNull(3, callableStatement, version);
		callableStatement.setString(4, ssoId);
		
		connection.setAutoCommit(true);
		return callableStatement;
		
	}
}
