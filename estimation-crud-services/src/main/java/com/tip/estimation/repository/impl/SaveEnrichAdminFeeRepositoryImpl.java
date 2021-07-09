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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tip.estimation.model.EnrichFeeObject;
import com.tip.estimation.model.SaveEnrichDetails;
import com.tip.estimation.model.VersionObject;
import com.tip.estimation.repository.SaveEnrichAdminFeeRepository;
import com.tip.estimation.util.DatatypeCommonUtility;
import com.tip.estimation.util.EstimationConstant;

@Repository
public class SaveEnrichAdminFeeRepositoryImpl implements SaveEnrichAdminFeeRepository {

	static final Logger logger = LoggerFactory.getLogger(SaveEnrichAdminFeeRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	String msg;

	@Override
	public String saveEnrichDetails(List<EnrichFeeObject> enrichFeeList, VersionObject versionObject,
			SaveEnrichDetails saveEnrichDetails) {

		Connection connection = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement statement = connection.prepareStatement("{call "
					+ EstimationConstant.PROC_ENRICH_SAVE_ADMIN_FEES + "(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			connection.setAutoCommit(false);

			for (EnrichFeeObject enrichFeeObject : enrichFeeList) {
				enrichFeeObject
						.setShowHide(DatatypeCommonUtility.booleanConversion(enrichFeeObject.getShowHide()));

				statement.setBigDecimal(1, saveEnrichDetails.getEstnHeaderList().getEstimationId());
				DatatypeCommonUtility.checkNull(2, statement, enrichFeeObject.getFeesgroupId());
				DatatypeCommonUtility.checkNull(3, statement, enrichFeeObject.getFeessubgroupId());
				DatatypeCommonUtility.checkNull(4, statement, enrichFeeObject.getFeesitemId());
				DatatypeCommonUtility.checkNull(5, statement, versionObject.getVersion());
				DatatypeCommonUtility.checkNull(6, statement, versionObject.getSupplementary());
				statement.setBigDecimal(7, enrichFeeObject.getFee());
				statement.setBigDecimal(8, enrichFeeObject.getCharges());
				statement.setString(9, enrichFeeObject.getShowHide());
				statement.setString(10, enrichFeeObject.getIsApproved());
				statement.setString(11, enrichFeeObject.getIsRejected());
				DatatypeCommonUtility.checkNull(12, statement, enrichFeeObject.getRejectedReason());
				statement.setString(13, saveEnrichDetails.getEstnHeaderList().getSsoId());
				statement.addBatch();
				
				logger.info("Batch added..."+mapper.writeValueAsString(enrichFeeObject));
			}

			int[] resultArr = statement.executeBatch();
			connection.commit();
			logger.debug("Return array :" + Arrays.toString(resultArr));
			for (int i : resultArr) {
				if (i == 0) {
					msg = "FAILURE";
					logger.error("An Error occured while saving admin fees Details");
					break;
				} else {
					msg = "SUCCESS";
					logger.debug("Enrich Admin Fees saved successfully");
				}
			}
			statement.close();
			connection.setAutoCommit(true);
		} catch (Exception e) {
			logger.error("An Error occured while saving Enrich Admin Fees" + e);
			msg = "FAILURE";

		}
		return msg;
	}

}