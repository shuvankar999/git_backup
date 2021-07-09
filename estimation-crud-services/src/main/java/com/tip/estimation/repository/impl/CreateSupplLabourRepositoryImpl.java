package com.tip.estimation.repository.impl;

import java.math.BigDecimal;
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

import com.tip.estimation.model.EnrichAddtnlWotObject;
import com.tip.estimation.model.EnrichWoObject;
import com.tip.estimation.model.VersionObject;
import com.tip.estimation.repository.CreateSupplLabourRepository;
import com.tip.estimation.util.DatatypeCommonUtility;
import com.tip.estimation.util.EstimationConstant;

@Repository
public class CreateSupplLabourRepositoryImpl implements CreateSupplLabourRepository{

	
 static final Logger logger = LoggerFactory.getLogger(CreateSupplLabourRepositoryImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	String msg;
	@Override
	public String saveLabourSuppl(List<EnrichWoObject> enrichWoObjectList,BigDecimal estimationId, VersionObject versionObject, String ssoId) {
		
		Connection connection = null;
		try {
			Integer rejectedReason = null;
			connection = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement statement = connection.prepareStatement("{call "+EstimationConstant.PROC_CREATE_SUPPL_LABOUR+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			connection.setAutoCommit(false);
			
			for(EnrichWoObject enrichWoObject : enrichWoObjectList){
				for(EnrichAddtnlWotObject enrichWotObject : enrichWoObject.getEstnWotList()){
					enrichWotObject.setShowHide(DatatypeCommonUtility.booleanConversion(enrichWotObject.getShowHide()));
					statement.setBigDecimal(1, estimationId);
					DatatypeCommonUtility.checkNull(2, statement, enrichWotObject.getEstnWOId());
					DatatypeCommonUtility.checkNull(3, statement, enrichWotObject.getEstnWOTId());
					DatatypeCommonUtility.checkNull(4, statement, versionObject.getVersion());
					DatatypeCommonUtility.checkNull(5, statement, versionObject.getSupplementary());
					statement.setString(6, enrichWotObject.getGroupCd());
					statement.setString(7, enrichWotObject.getSubgroupCd());
					statement.setString(8, enrichWotObject.getActivity());
					statement.setString(9, enrichWotObject.getFailureCause());
					statement.setString(10, enrichWotObject.getAction());
					statement.setString(11, enrichWotObject.getPosition());
					statement.setBigDecimal(12, enrichWotObject.getTargetTime());
					statement.setString(13, enrichWotObject.getReason());
					statement.setString(14, enrichWotObject.getTaskComments());
					DatatypeCommonUtility.checkNull(15, statement, enrichWoObject.getSupplierId());
					statement.setString(16, enrichWotObject.getIsApproved());
					statement.setString(17, enrichWotObject.getIsRejected());
					DatatypeCommonUtility.checkNull(18, statement, rejectedReason);
					statement.setString(19, enrichWotObject.getLabourTime());
					DatatypeCommonUtility.checkNull(20, statement, enrichWotObject.getSoldTime());
					statement.setBigDecimal(21, enrichWotObject.getLabourRate());
					statement.setString(22, enrichWotObject.getShowHide());
					statement.setString(23, ssoId);
					statement.setBigDecimal(24, enrichWotObject.getWshpWPNr());
					DatatypeCommonUtility.checkNull(25, statement, enrichWotObject.getWshpWONr());
					DatatypeCommonUtility.checkNull(26, statement, enrichWotObject.getWshpWOTNr());
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

