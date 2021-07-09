package com.tip.equipmentdetails.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tip.asset.util.AssetConstants;
import com.tip.equipmentdetails.model.FieldLabel;
import com.tip.equipmentdetails.model.SaveProfileSettingsRequest;
import com.tip.equipmentdetails.repository.SaveFieldLabelRepository;

@Repository
public class SaveFieldLabelRepositoryImpl implements SaveFieldLabelRepository{

	static final Logger logger = LoggerFactory.getLogger(SaveFieldLabelRepositoryImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean saveFieldLabelDetils(SaveProfileSettingsRequest saveProfileSettingsRequest) {		
		boolean saveFlag = false;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			statement = connection.prepareStatement("{call " + AssetConstants.PROC_SAVE_FIELD_LABEL + "(?,?,?,?)}");
			connection.setAutoCommit(false);
			for(FieldLabel fieldLabel : saveProfileSettingsRequest.getFieldLabelLst())
			{
				statement.setString(1, saveProfileSettingsRequest.getSsoId());
				statement.setString(2, fieldLabel.getFieldName());
				statement.setString(3, saveProfileSettingsRequest.getFieldApp());
				statement.setString(4, fieldLabel.getSelected());
				// Batch creation
				statement.addBatch();
			}
				
			int[] returnArr = statement.executeBatch();
			connection.commit();
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
					logger.error("An error occurred while saving profile settings: " , e1);
				}
			}			
			StackTraceElement stackTraceElement = e.getStackTrace()[0];
			logger.error("An error occurred while saving profile settings: " + e);
			logger.error("At Line: " + stackTraceElement.getClassName() + ": " + stackTraceElement.getLineNumber());
			saveFlag = false;
		}
		return saveFlag;
	}
}