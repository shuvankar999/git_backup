package com.tip.estimationfilter.repository.impl;

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

import com.tip.estimationfilter.model.SaveFilterForm;
import com.tip.estimationfilter.model.SaveFilterFormRequest;
import com.tip.estimationfilter.repository.SaveFilterRepository;
import com.tip.util.DatatypeCommonUtility;
import com.tip.util.EstimationConstant;

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
		PreparedStatement statement = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			statement = connection.prepareStatement("{call " + EstimationConstant.PROC_ESTIMATION_SAVE_FILTER + "(?,?,?,?,?,?,?)}");
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
				// Batch creation
				statement.addBatch();
			}
				
			int[] returnArr = statement.executeBatch();
			connection.commit();
			logger.debug("Return array  :" + Arrays.toString(returnArr));
			for (int i : returnArr) {
				if (i == 1) {
					logger.debug(EstimationConstant.ERROR_CD + ": SUCCESS");
					saveFlag = true;
				} else {
					saveFlag = false;
					logger.error(EstimationConstant.ERROR_CD + ": FAILURE");
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
			saveFlag = false;
			logger.error("An error occurred while saving filter details: " + e);
		}	
		return saveFlag;
	}

}
