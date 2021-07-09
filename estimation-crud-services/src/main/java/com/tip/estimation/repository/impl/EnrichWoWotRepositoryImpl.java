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

import com.tip.estimation.model.EnrichWorkOrderObject;
import com.tip.estimation.model.EnrichWotObject;
import com.tip.estimation.model.SaveEnrichDetails;
import com.tip.estimation.model.VersionObject;
import com.tip.estimation.repository.EnrichWoWotRepository;
import com.tip.estimation.util.DatatypeCommonUtility;
import com.tip.estimation.util.EstimationConstant;

@Repository
public class EnrichWoWotRepositoryImpl implements EnrichWoWotRepository{

	
 static final Logger logger = LoggerFactory.getLogger(EnrichWoWotRepositoryImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	String msg;
	@Override
	public String saveEnrichDetails(List<EnrichWorkOrderObject> enrichWorkOrderList,VersionObject versionObject,SaveEnrichDetails saveEnrichDetails) {
		
		Connection connection = null;
		try {
			
			connection = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement statement = connection.prepareStatement("{call "+EstimationConstant.PROC_ENRICH_SAVE_WOWOT_DETAILS+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			connection.setAutoCommit(false);
			
			for(EnrichWorkOrderObject saveEnrichWorkOrderObject : enrichWorkOrderList){
				for(EnrichWotObject enrichWotObject : saveEnrichWorkOrderObject.getEstnWotList()){
					enrichWotObject.setShowHide(DatatypeCommonUtility.booleanConversion(enrichWotObject.getShowHide()));
					statement.setBigDecimal(1, saveEnrichWorkOrderObject.getEstimationId());
					DatatypeCommonUtility.checkNull(2, statement, saveEnrichWorkOrderObject.getEstnWOId());
					DatatypeCommonUtility.checkNull(3, statement, enrichWotObject.getEstnWOTId());
					DatatypeCommonUtility.checkNull(4, statement, versionObject.getVersion());
					DatatypeCommonUtility.checkNull(5, statement, versionObject.getSupplementary());
					DatatypeCommonUtility.checkNull(6, statement, saveEnrichWorkOrderObject.getSupplierId());
					statement.setString(7, saveEnrichWorkOrderObject.getLabourTime());					
					DatatypeCommonUtility.checkNull(8, statement, enrichWotObject.getSoldTime());
					DatatypeCommonUtility.checkNull(9, statement, enrichWotObject.getLabourRate());					
					statement.setString(10, enrichWotObject.getShowHide());
					statement.setString(11, saveEnrichWorkOrderObject.getLoCommentsCustomers());				
					statement.setString(12, saveEnrichWorkOrderObject.getLoCommentsInternal());
					statement.setString(13, enrichWotObject.getTaskComments());
					statement.setString(14, enrichWotObject.getIsApproved());
					statement.setString(15, enrichWotObject.getIsRejected());
					DatatypeCommonUtility.checkNull(16, statement, enrichWotObject.getRejectedReason());
					statement.setString(17, saveEnrichDetails.getEstnHeaderList().getSsoId());	
					statement.addBatch();
				}
			}
			int[] resultArr = statement.executeBatch();
			connection.commit();
			logger.debug("Return array :"+Arrays.toString(resultArr));
			for(int i:resultArr){
				if(i==0){
					msg ="FAILURE";
					logger.error("An error occured while saving Enrich WoWoT Details");
					break;
				}else{
					msg="SUCCESS";
					logger.debug("Enrich WoWOt details saved Successfully");
				}
			}
			statement.close();
			connection.setAutoCommit(true);
		} catch (SQLException e) {

			logger.error("An error occured while saving Enrich WoWoT Details" + e);
			StackTraceElement stackTraceElement = e.getStackTrace()[0];
			msg ="FAILURE";

		}
		return msg;
	}
}

