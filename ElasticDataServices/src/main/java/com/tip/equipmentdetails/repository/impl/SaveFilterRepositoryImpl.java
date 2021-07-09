package com.tip.equipmentdetails.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tip.asset.util.AssetConstants;
import com.tip.elastic.util.DatatypeCommonUtility;
import com.tip.equipmentdetails.model.SaveFilterForm;
import com.tip.equipmentdetails.model.SaveFilterFormRequest;
import com.tip.equipmentdetails.repository.SaveFilterRepository;

@Repository
public class SaveFilterRepositoryImpl implements SaveFilterRepository{

	static final Logger logger = LoggerFactory.getLogger(SaveFilterRepositoryImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean saveFilterDetils(SaveFilterFormRequest saveFilterFormRequest) {		
		boolean saveFlag = true;
		Connection connection = null;
		Map<String, Object> saveFeeMap = new HashMap();
		PreparedStatement statement = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			statement = connection.prepareStatement("{call " + AssetConstants.PROC_SAVE_FILTER_FORM_DETAILS + "(?,?,?,?,?,?,?,?)}");
			connection.setAutoCommit(false);
			for(SaveFilterForm saveFilterForm : saveFilterFormRequest.getFilterForm())
			{
				statement.setString(1, saveFilterForm.getSsoId());
				DatatypeCommonUtility.checkNull(2, statement, saveFilterForm.getFiltergroupId());
				statement.setString(3, saveFilterForm.getFilterId());
				statement.setString(4, saveFilterForm.getRangeup());
				statement.setString(5, saveFilterForm.getRangedown());
				statement.setString(6, saveFilterForm.getFiltervalue());
				statement.setString(7, saveFilterForm.getFilterSelected());
				statement.setString(8, saveFilterForm.getIsAdvanced());
				// Batch creation
				statement.addBatch();
			}
				
			int[] returnArr = statement.executeBatch();
			connection.commit();
			logger.debug("Return array  :" + Arrays.toString(returnArr));
			for (int i : returnArr) {
				if (i == 1) {
					logger.debug(AssetConstants.ERROR_CODE + ": SUCCESS");
					saveFlag = true;
				} else {
					saveFlag = false;
					logger.error(AssetConstants.ERROR_CODE + ": FAILURE");
					break;
				}
			}
			statement.close();
			connection.setAutoCommit(true);
		} catch (Exception e) {
			if(statement != null){
				try {
					statement.close();
				} catch (SQLException e1) {
					logger.error("An error occurred while saving customer fee: " , e1);
				}
			}			
			StackTraceElement stackTraceElement = e.getStackTrace()[0];
			saveFeeMap.put(AssetConstants.ERROR_CODE, "FAILURE");
			logger.error("An error occurred while saving filter details: " + e);
			logger.error("At Line: " + stackTraceElement.getClassName() + ": " + stackTraceElement.getLineNumber());
			saveFlag = false;
		}	
		return saveFlag;
	}
}