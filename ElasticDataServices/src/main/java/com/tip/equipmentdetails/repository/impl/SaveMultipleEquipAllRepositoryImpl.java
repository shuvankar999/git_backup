package com.tip.equipmentdetails.repository.impl;

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

import com.tip.asset.util.AssetConstants;
import com.tip.equipmentdetails.model.MultiCopyResponse;
import com.tip.equipmentdetails.repository.SaveMultipleEquipAllRepository;

@Repository
public class SaveMultipleEquipAllRepositoryImpl implements SaveMultipleEquipAllRepository, CallableStatementCreator{

	static final Logger logger = LoggerFactory.getLogger(SaveMultipleEquipAllRepositoryImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	
	private Integer sourceEquipmentNr;
	private String[] finalStringArray;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public MultiCopyResponse updateAll(Integer sourceEquipmentNr, String[] finalStringArray) {

		Map<String, Object> resultMap = new HashMap();
		MultiCopyResponse multiCopyResponse = new MultiCopyResponse();
		try {
			SqlParameter srcUnitSqlparam = new SqlParameter(Types.INTEGER);
			
			List paramList = new ArrayList();
			paramList.add(srcUnitSqlparam);
			for(int i=0;i<50;i++) {
				paramList.add(new SqlParameter(Types.VARCHAR));
			}
			multiCopyResponse.setSourceEquipmentNr(sourceEquipmentNr);
			SaveMultipleEquipAllRepositoryImpl saveMultipleEquipAllRepositoryImpl = new SaveMultipleEquipAllRepositoryImpl();
			saveMultipleEquipAllRepositoryImpl.sourceEquipmentNr = sourceEquipmentNr;
			saveMultipleEquipAllRepositoryImpl.finalStringArray = finalStringArray;
			
			saveMultipleEquipAllRepositoryImpl.procedureCall = "{call " + AssetConstants.PROC_UNITS_COPY_MULTIPLE_ALL + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			logger.info("Calling stored procedure..."+saveMultipleEquipAllRepositoryImpl.procedureCall);
			resultMap = jdbcTemplate.call(saveMultipleEquipAllRepositoryImpl, paramList);
			multiCopyResponse.setErrorCd((String)((Map<String, Object>)((List)resultMap.get("#result-set-1")).get(0)).get("Ret_Value"));
			logger.info((String)((Map<String, Object>)((List)resultMap.get("#result-set-1")).get(0)).get("Ret_Value"));
		} catch (Exception e) {
			logger.error("An error occurred: " + e);
			//resultMap.put(AssetConstants.ERROR_CD, e.getMessage());
			//multiCopyResponse.setErrorCd("Exception Occurred");
			multiCopyResponse.setErrorCd("An error occurred: " + e.getMessage());
		}
		return multiCopyResponse;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);		
		callableStatement.setInt(1, sourceEquipmentNr);
		for(int i=0;i<50;i++) {
			if( finalStringArray[i]==null || finalStringArray[i].isEmpty() )
				callableStatement.setString(i+2, null);
			else
				callableStatement.setString(i+2, finalStringArray[i]);
		}
		connection.setAutoCommit(true);
		return callableStatement;
	}
}