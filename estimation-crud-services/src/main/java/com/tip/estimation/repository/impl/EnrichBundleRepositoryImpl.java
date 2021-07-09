package com.tip.estimation.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tip.estimation.model.BundleHeader;
import com.tip.estimation.model.BundleObject;
import com.tip.estimation.model.SaveEnrichDetails;
import com.tip.estimation.model.VersionObject;
import com.tip.estimation.repository.EnrichBundleRepository;
import com.tip.estimation.util.DatatypeCommonUtility;
import com.tip.estimation.util.EstimationConstant;

@Repository
public class EnrichBundleRepositoryImpl implements EnrichBundleRepository{

	
 static final Logger logger = LoggerFactory.getLogger(EnrichBundleRepositoryImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	String msg;
	@Override
	public String saveBundleDetails(SaveEnrichDetails saveEnrichDetails, VersionObject versionObject) {
		
		Connection connection = null;
		try {
			
			connection = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement statement = connection.prepareStatement("{call "+EstimationConstant.PROC_ENRICH_SAVE_BUNDLE_DETAILS+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			connection.setAutoCommit(false);
			
			for(BundleHeader bundleHead : saveEnrichDetails.getEstnBundleLists()){
				for(BundleObject bundleObject : bundleHead.getListOfBundle()){
					bundleHead.setShowHide(DatatypeCommonUtility.booleanConversion(bundleHead.getShowHide()));
					statement.setBigDecimal(1, bundleObject.getEstimationId());
					statement.setString(2, bundleObject.getBundleName());
					statement.setString(3, bundleObject.getDnaCode());
					statement.setString(4, bundleObject.getMaintenanceAction());
					statement.setString(5, bundleObject.getManufacturerId());
					statement.setString(6, bundleObject.getManufacturer());
					statement.setString(7, bundleObject.getSupplierPartNr());
					statement.setBigDecimal(8,  bundleObject.getFee());
					DatatypeCommonUtility.checkNull(9, statement, versionObject.getVersion());
					DatatypeCommonUtility.checkNull(10, statement, versionObject.getSupplementary());				
					statement.setString(11, bundleHead.getShowHide());
					statement.setString(12, bundleHead.getIsApproved());
					statement.setString(13, bundleHead.getIsRejected());
					DatatypeCommonUtility.checkNull(14, statement, bundleObject.getRejectedReason());
					statement.setString(15, saveEnrichDetails.getEstnHeaderList().getSsoId());	
					statement.addBatch();
				}
			}
			int[] resultArr = statement.executeBatch();
			connection.commit();
			logger.debug("Return array :"+Arrays.toString(resultArr));
			for(int i:resultArr){
				if(i==0){
					msg ="FAILURE";
					logger.error("An error occured while saving Enrich bundle Details");
					break;
				}else{
					msg="SUCCESS";
					logger.debug("Enrich bundle details saved Successfully");
				}
			}
			statement.close();
			connection.setAutoCommit(true);
		} catch (SQLException e) {

			logger.error("An error occured while saving Enrich bundle Details" + e);
			StackTraceElement stackTraceElement = e.getStackTrace()[0];
			msg ="FAILURE";

		}
		return msg;
	}
}

