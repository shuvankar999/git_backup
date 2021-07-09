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

import com.tip.estimation.model.EnrichAddtnlPartsObject;
import com.tip.estimation.model.VersionObject;
import com.tip.estimation.repository.CreateSupplPartsRepository;
import com.tip.estimation.util.DatatypeCommonUtility;
import com.tip.estimation.util.EstimationConstant;

@Repository
public class CreateSupplPartsRepositoryImpl implements CreateSupplPartsRepository{
	
	static final Logger logger = LoggerFactory.getLogger(CreateSupplPartsRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	String msg;
	
	@Override
	public String savePartsSuppl(List<EnrichAddtnlPartsObject> partList, BigDecimal estimationId, VersionObject versionObject, String ssoId) {
		Connection connection = null;
		Integer rejectedReason = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"{call " + EstimationConstant.PROC_CREATE_SUPPL_PARTS+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			connection.setAutoCommit(false);
	
			for (EnrichAddtnlPartsObject enrichAddtnlPartsObject : partList) {
				enrichAddtnlPartsObject.setShowHide(DatatypeCommonUtility.booleanConversion(enrichAddtnlPartsObject.getShowHide()));

				DatatypeCommonUtility.checkNull(1, statement, versionObject.getSupplementary());
				statement.setBigDecimal(2, estimationId);
				DatatypeCommonUtility.checkNull(3, statement, versionObject.getVersion());
				DatatypeCommonUtility.checkNull(4, statement, enrichAddtnlPartsObject.getEstnWOId());
				DatatypeCommonUtility.checkNull(5, statement, enrichAddtnlPartsObject.getEstnWOTId());
				statement.setString(6, enrichAddtnlPartsObject.getPartNumber());
				statement.setString(7, enrichAddtnlPartsObject.getPartDesc());
				DatatypeCommonUtility.checkNull(8, statement, enrichAddtnlPartsObject.getQuantity());
				statement.setBigDecimal(9, enrichAddtnlPartsObject.getRetailPrice());
				statement.setBigDecimal(10, enrichAddtnlPartsObject.getDicount());
				statement.setBigDecimal(11, enrichAddtnlPartsObject.getCostToTip());
				DatatypeCommonUtility.checkNull(12, statement, enrichAddtnlPartsObject.getSupplierId());
				statement.setString(13, enrichAddtnlPartsObject.getCurrency());	
				statement.setString(14, enrichAddtnlPartsObject.getIsApproved());
				statement.setString(15, enrichAddtnlPartsObject.getIsRejected());
				DatatypeCommonUtility.checkNull(16, statement, rejectedReason);
				statement.setString(17, ssoId);
				statement.addBatch();
			}

			int[] resultArr = statement.executeBatch();
			connection.commit();
			logger.debug("Return array :" + Arrays.toString(resultArr));
			for (int i : resultArr) {
				if (i == 0) {
					msg ="FAILURE";					
					logger.error("An Error occured while saving Parts Details for supplementary");
					break;
				} else {
					msg ="SUCCESS";				
					logger.debug("Supplementary Parts Details saved successfully");
				}
			}
			statement.close();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			logger.error("An Error occured while saving Part Details for supplementary" + e);
			StackTraceElement stackTraceElement = e.getStackTrace()[0];			
			msg ="FAILURE";

		}
		return msg;
	}


	
}