package com.tip.estimation.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tip.estimation.model.EnrichHeaderObject;
import com.tip.estimation.model.SaveSupplModel;
import com.tip.estimation.repository.SaveSupplementaryRepository;
import com.tip.estimation.util.DatatypeCommonUtility;
import com.tip.estimation.util.EstimationConstant;

@Repository
public class SaveSupplementaryRepositoryImpl implements SaveSupplementaryRepository, CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(SaveSupplementaryRepositoryImpl.class);

	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	//private SaveSupplModel saveSupplModel;
	private EnrichHeaderObject saveSupplModel;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	//public Map<String, Object> saveAllSuppl(SaveSupplModel saveSupplModel) {
	public Map<String, Object> saveHeaderSuppl(EnrichHeaderObject saveSupplModel){
		Map<String, Object> resultMap= null;
		
		try {
		SaveSupplementaryRepositoryImpl saveSupplementaryRepositoryImpl = new SaveSupplementaryRepositoryImpl();
		saveSupplementaryRepositoryImpl.saveSupplModel = saveSupplModel;

		saveSupplementaryRepositoryImpl.procedureCall = "{call "+ EstimationConstant.PROC_ESTIMATION_SAVE_SUPPL + " (?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		
		
		resultMap = jdbcTemplate.call(saveSupplementaryRepositoryImpl, new ArrayList());
		}catch (Exception e) {
			logger.error("An error occurred while saving data: " + e);
			
		
		}
		
		return resultMap;
				
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setBigDecimal(1, saveSupplModel.getEstimationId());
		callableStatement.setBigDecimal(2, saveSupplModel.getConcession());
		//DatatypeCommonUtility.checkNull(2, callableStatement, saveSupplModel.getConcession());
		callableStatement.setString(3, saveSupplModel.getCustNotes());
		callableStatement.setString(4, saveSupplModel.getInternalNotes());
		callableStatement.setString(5, saveSupplModel.getIsApproved());
		callableStatement.setString(6, saveSupplModel.getIsRejected());
		DatatypeCommonUtility.checkNull(7, callableStatement, saveSupplModel.getRejectedReason());
		callableStatement.setString(8, saveSupplModel.getShowHide());
/*		if(saveSupplModel.isShowHide()!=null)
			callableStatement.setString(8, saveSupplModel.isShowHide() ? "Y" : "N");
		else
			callableStatement.setString(8, null);*/
		DatatypeCommonUtility.checkNull(9, callableStatement, saveSupplModel.getSupplementary());
		DatatypeCommonUtility.checkNull(10, callableStatement, saveSupplModel.getVersion());
		callableStatement.setString(11,  saveSupplModel.getSaveAs());
		DatatypeCommonUtility.checkNull(12, callableStatement, saveSupplModel.getEstnStatusId());
		callableStatement.setString(13, saveSupplModel.getSsoId());
		connection.setAutoCommit(true);
		return callableStatement;

	}

}
