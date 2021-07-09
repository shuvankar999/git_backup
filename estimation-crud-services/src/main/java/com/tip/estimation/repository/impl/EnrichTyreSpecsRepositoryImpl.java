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

import com.tip.estimation.model.EnrichTyreSpecsObject;
import com.tip.estimation.model.SaveEnrichDetails;
import com.tip.estimation.model.VersionObject;
import com.tip.estimation.repository.EnrichTyreSpecsRepository;
import com.tip.estimation.util.DatatypeCommonUtility;
import com.tip.estimation.util.EstimationConstant;

	@Repository
	public class EnrichTyreSpecsRepositoryImpl implements EnrichTyreSpecsRepository{

		static final Logger logger = LoggerFactory.getLogger(EnrichTyreSpecsRepositoryImpl.class);

		@Autowired
		private JdbcTemplate jdbcTemplate;
		
		String msg;
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public String saveEnrichDetails(List<EnrichTyreSpecsObject> enrichTyreSpecsObjectList,
				VersionObject versionObject,SaveEnrichDetails saveEnrichDetails) {
						
			Connection connection = null;
			try {
				connection = jdbcTemplate.getDataSource().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"{call " + EstimationConstant.PROC_ENRICH_SAVE_TYRE_SPECS + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				connection.setAutoCommit(false);
		
				for (EnrichTyreSpecsObject saveEnrichTyreSpecsObject : enrichTyreSpecsObjectList) {
					saveEnrichTyreSpecsObject.setShowHide(DatatypeCommonUtility.booleanConversion(saveEnrichTyreSpecsObject.getShowHide()));
					statement.setBigDecimal(1, saveEnrichDetails.getEstnHeaderList().getEstimationId());
					DatatypeCommonUtility.checkNull(2, statement, saveEnrichTyreSpecsObject.getEstnWOId());
					DatatypeCommonUtility.checkNull(3, statement, saveEnrichTyreSpecsObject.getEstnWOTId());
					DatatypeCommonUtility.checkNull(4, statement, saveEnrichTyreSpecsObject.getTyreSpecgrpId());
					DatatypeCommonUtility.checkNull(5, statement, saveEnrichTyreSpecsObject.getTyreSpecsubgrpId());

					DatatypeCommonUtility.checkNull(6, statement, saveEnrichTyreSpecsObject.getTyreSpecItemId());
					statement.setString(7, saveEnrichTyreSpecsObject.getTyreSize());
					DatatypeCommonUtility.checkNull(8, statement, saveEnrichTyreSpecsObject.getApplication());
					statement.setString(9, saveEnrichTyreSpecsObject.getBrand());
					statement.setString(10, saveEnrichTyreSpecsObject.getTyreStatus());
					statement.setString(11, saveEnrichTyreSpecsObject.getChargeType());
					DatatypeCommonUtility.checkNull(12, statement, saveEnrichTyreSpecsObject.getLostMM());
					DatatypeCommonUtility.checkNull(13, statement, versionObject.getVersion());
					DatatypeCommonUtility.checkNull(14, statement, versionObject.getSupplementary());
					statement.setBigDecimal(15, saveEnrichTyreSpecsObject.getCharges());
					statement.setString(16, saveEnrichTyreSpecsObject.getShowHide());
					statement.setString(17, saveEnrichTyreSpecsObject.getIsApproved());
					statement.setString(18, saveEnrichTyreSpecsObject.getIsRejected());
					DatatypeCommonUtility.checkNull(19, statement, saveEnrichTyreSpecsObject.getRejectedReason());
				
					statement.setString(20, saveEnrichDetails.getEstnHeaderList().getSsoId());

					statement.addBatch();
				}

				int[] resultArr = statement.executeBatch();
				connection.commit();
				logger.debug("Return array :" + Arrays.toString(resultArr));
				for (int i : resultArr) {
					if (i == 0) {
						msg ="FAILURE";
						logger.error("An Error occured while saving Enrich Tyre Specs Details");
						break;
					} else {
						msg ="SUCCESS";
						logger.error(" Enrich Tyre Specs Details saved successfully");
					}
				}
				statement.close();
				connection.setAutoCommit(true);
			} catch (SQLException e) {

				logger.error("An Error occured while saving Enrich Tyre Specs Details" + e);
				StackTraceElement stackTraceElement = e.getStackTrace()[0];
				msg ="FAILURE";

			}
			return msg;
		}



	}
