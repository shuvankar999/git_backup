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

import com.tip.estimation.model.EnrichTyreServiceObject;
import com.tip.estimation.model.SaveEnrichDetails;
import com.tip.estimation.model.VersionObject;
import com.tip.estimation.repository.EnrichTyreServiceRepository;
import com.tip.estimation.util.DatatypeCommonUtility;
import com.tip.estimation.util.EstimationConstant;

@Repository
public class EnrichTyreServiceRepositoryImpl implements EnrichTyreServiceRepository{


	static final Logger logger = LoggerFactory.getLogger(EnrichTyreServiceRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	String msg;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String saveEnrichDetails(List<EnrichTyreServiceObject> enrichTyreServiceObjectList,
			VersionObject versionObject,SaveEnrichDetails saveEnrichDetails) {
			
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"{call " + EstimationConstant.PROC_ENRICH_SAVE_TYRE_SRVCS + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			connection.setAutoCommit(false);
	
			for (EnrichTyreServiceObject saveEnrichTyreService : enrichTyreServiceObjectList) {
				saveEnrichTyreService.setShowHide(DatatypeCommonUtility.booleanConversion(saveEnrichTyreService.getShowHide()));
				statement.setBigDecimal(1, saveEnrichDetails.getEstnHeaderList().getEstimationId());
				DatatypeCommonUtility.checkNull(2, statement, saveEnrichTyreService.getEstnWOId());
				DatatypeCommonUtility.checkNull(3, statement, saveEnrichTyreService.getEstnWOTId());
				DatatypeCommonUtility.checkNull(4, statement, saveEnrichTyreService.getTyreServicegrpId());
				DatatypeCommonUtility.checkNull(5, statement, saveEnrichTyreService.getTyreServicesubgrpId());
				DatatypeCommonUtility.checkNull(6, statement, saveEnrichTyreService.getTyreServiceItemId());
				DatatypeCommonUtility.checkNull(7, statement, versionObject.getVersion());
				DatatypeCommonUtility.checkNull(8, statement, versionObject.getSupplementary());				
				statement.setBigDecimal(9,  saveEnrichTyreService.getCharges());
				statement.setString(10, saveEnrichTyreService.getShowHide());
				statement.setString(11, saveEnrichTyreService.getIsApproved());
				statement.setString(12, saveEnrichTyreService.getIsRejected());
				DatatypeCommonUtility.checkNull(13, statement, saveEnrichTyreService.getRejectedReason());
				statement.setString(14, saveEnrichDetails.getEstnHeaderList().getSsoId());
							
				statement.addBatch();
			}

			int[] resultArr = statement.executeBatch();
			connection.commit();
			logger.debug("Return array :" + Arrays.toString(resultArr));
			for (int i : resultArr) {
				if (i == 0) {
					msg ="FAILURE";
					logger.error("An Error Occured while saving Enrich Tyre Service Details");
					break;
				} else {
					msg ="SUCCESS";
					logger.debug("Enrich Tyre Service Details saved successfully");
				}
			}
			statement.close();
			connection.setAutoCommit(true);
		} catch (SQLException e) {

			logger.error("An Error Occured while saving Enrich Tyre Service Details" + e);
			StackTraceElement stackTraceElement = e.getStackTrace()[0];
			msg ="FAILURE";

		}

		return msg;	
	}


	

}
