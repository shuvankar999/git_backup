package com.tip.estimation.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tip.estimation.model.EnrichConsumablesObject;
import com.tip.estimation.model.SaveEnrichDetails;
import com.tip.estimation.model.VersionObject;
import com.tip.estimation.repository.EnrichConsumablesRepository;
import com.tip.estimation.util.DatatypeCommonUtility;
import com.tip.estimation.util.EstimationConstant;


@Repository
public class EnrichConsumablesRepositoryImpl implements EnrichConsumablesRepository{

	static final Logger logger = LoggerFactory.getLogger(EnrichConsumablesRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	String msg;
	
	@Override
	public String saveEnrichDetails(List<EnrichConsumablesObject> enrichConsumablesObjectList,
			VersionObject versionObject,SaveEnrichDetails saveEnrichDetails) {
		
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"{call " + EstimationConstant.PROC_ENRICH_SAVE_CONSUMABLES + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			connection.setAutoCommit(false);
	
			for (EnrichConsumablesObject saveEnrichConsumablesObject : enrichConsumablesObjectList) {
				saveEnrichConsumablesObject.setShowHide(DatatypeCommonUtility.booleanConversion(saveEnrichConsumablesObject.getShowHide()));
				statement.setBigDecimal(1, saveEnrichDetails.getEstnHeaderList().getEstimationId());
				DatatypeCommonUtility.checkNull(2, statement, saveEnrichConsumablesObject.getConsmblegroupId());
				DatatypeCommonUtility.checkNull(3, statement, saveEnrichConsumablesObject.getConsmblesubGroupId());
				DatatypeCommonUtility.checkNull(4, statement, saveEnrichConsumablesObject.getConsmbleitemId());
				DatatypeCommonUtility.checkNull(5, statement, versionObject.getVersion());
				DatatypeCommonUtility.checkNull(6, statement, versionObject.getSupplementary());
				statement.setBigDecimal(7,  saveEnrichConsumablesObject.getMinAmount());
				statement.setBigDecimal(8,  saveEnrichConsumablesObject.getMaxAmount());
				statement.setBigDecimal(9,  saveEnrichConsumablesObject.getPercentage());
				statement.setBigDecimal(10,  saveEnrichConsumablesObject.getFee());
				statement.setBigDecimal(11, saveEnrichConsumablesObject.getCharges());
				statement.setString(12, saveEnrichConsumablesObject.getShowHide());
				statement.setString(13, saveEnrichConsumablesObject.getIsApproved());
				statement.setString(14, saveEnrichConsumablesObject.getIsRejected());
				DatatypeCommonUtility.checkNull(15, statement, saveEnrichConsumablesObject.getRejectedReason());
				statement.setString(16, saveEnrichDetails.getEstnHeaderList().getSsoId());
				
				statement.addBatch();
			}

			int[] resultArr = statement.executeBatch();
			connection.commit();
			logger.debug("Return array :" + Arrays.toString(resultArr));
			for (int i : resultArr) {
				if (i == 0) {
					msg ="FAILURE";
					logger.error("An Error occured while saving Enrich Consumables Details");
					break;
				} else {
					msg ="SUCCESS";
					logger.debug("Enrich Consumables details saved successfully");
				}
			}
			statement.close();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			StackTraceElement stackTraceElement = e.getStackTrace()[0];	
			logger.error("An exception occured while saving Enrich Consumables Details" + e);
					
			msg ="FAILURE";

		}
		return msg;
	
	}


	
}
