package com.tip.supplier.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tip.elastic.util.DatatypeCommonUtility;
import com.tip.elastic.util.ElasticSearchConstant;
import com.tip.equipmentdetails.model.SaveFilterForm;
import com.tip.equipmentdetails.model.SaveFilterFormRequest;
import com.tip.supplier.repository.SupplierSaveFilterRepository;

@Repository
public class SupplierSaveFilterRepositoryImpl implements SupplierSaveFilterRepository{
	
static final Logger logger = LoggerFactory.getLogger(SupplierSaveFilterRepositoryImpl.class);
	
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
			statement = connection.prepareStatement("{call " + ElasticSearchConstant.PROC_SUPPLIER_SAVE_FILTER + "(?,?,?,?,?,?,?)}");
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
					logger.debug(ElasticSearchConstant.ERROR_CD + ": SUCCESS");
					saveFlag = true;
				} else {
					saveFlag = false;
					logger.error(ElasticSearchConstant.ERROR_CD + ": FAILURE");
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
