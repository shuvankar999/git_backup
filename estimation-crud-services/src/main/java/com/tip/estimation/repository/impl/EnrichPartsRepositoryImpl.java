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

import com.tip.estimation.model.EnrichPartsObject;
import com.tip.estimation.model.SaveEnrichDetails;
import com.tip.estimation.model.VersionObject;
import com.tip.estimation.repository.EnrichPartsRepository;
import com.tip.estimation.util.DatatypeCommonUtility;
import com.tip.estimation.util.EstimationConstant;

@Repository
public class EnrichPartsRepositoryImpl implements EnrichPartsRepository {

	static final Logger logger = LoggerFactory.getLogger(EnrichPartsRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	String msg;

	@Override
	public String saveEnrichDetails(List<EnrichPartsObject> enrichPartsObjectList, VersionObject versionObject,
			SaveEnrichDetails saveEnrichDetails) {

		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement statement = connection.prepareStatement("{call "
					+ EstimationConstant.PROC_ENRICH_SAVE_PARTS_DETAILS + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			connection.setAutoCommit(false);

			for (EnrichPartsObject saveEnrichPartsObject : enrichPartsObjectList) {
				saveEnrichPartsObject
						.setShowHide(DatatypeCommonUtility.booleanConversion(saveEnrichPartsObject.getShowHide()));

				statement.setBigDecimal(1, saveEnrichPartsObject.getEstimationId());
				DatatypeCommonUtility.checkNull(2, statement, saveEnrichPartsObject.getEstnWOId());
				DatatypeCommonUtility.checkNull(3, statement, saveEnrichPartsObject.getEstnWOTId());
				statement.setString(4, saveEnrichPartsObject.getPartNumber());
				statement.setString(5, saveEnrichPartsObject.getPartDesc());
				DatatypeCommonUtility.checkNull(6, statement, saveEnrichPartsObject.getQuantity());
				statement.setBigDecimal(7, saveEnrichPartsObject.getRetailPrice());
				statement.setBigDecimal(8, saveEnrichPartsObject.getCostToTip());
				DatatypeCommonUtility.checkNull(9, statement, saveEnrichPartsObject.getSupplierId());
				statement.setString(10, saveEnrichPartsObject.getCurrency());
				DatatypeCommonUtility.checkNull(11, statement, versionObject.getVersion());
				DatatypeCommonUtility.checkNull(12, statement, versionObject.getSupplementary());
				statement.setString(13, saveEnrichPartsObject.getIsApproved());
				statement.setString(14, saveEnrichPartsObject.getIsRejected());
				DatatypeCommonUtility.checkNull(15, statement, saveEnrichPartsObject.getRejectedReason());
				statement.setString(17, saveEnrichPartsObject.getShowHide());
				statement.setBigDecimal(16, saveEnrichPartsObject.getFixedPrice());
				statement.setString(18, saveEnrichDetails.getEstnHeaderList().getSsoId());
				statement.addBatch();
			}

			int[] resultArr = statement.executeBatch();
			connection.commit();
			logger.debug("Return array :" + Arrays.toString(resultArr));
			for (int i : resultArr) {
				if (i == 0) {
					msg = "FAILURE";
					logger.error("An Error occured while saving Enrich Parts Details");
					break;
				} else {
					msg = "SUCCESS";
					logger.debug("Enrich Parts Details saved successfully");
				}
			}
			statement.close();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			logger.error("An Error occured while saving Enrich Parts Details" + e);
			StackTraceElement stackTraceElement = e.getStackTrace()[0];
			msg = "FAILURE";

		}
		return msg;
	}

}