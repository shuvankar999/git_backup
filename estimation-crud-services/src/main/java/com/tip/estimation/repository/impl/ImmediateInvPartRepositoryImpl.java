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

import com.tip.estimation.model.Part;
import com.tip.estimation.model.RebillDetails;
import com.tip.estimation.repository.ImmediateInvPartRepository;
import com.tip.estimation.util.DatatypeCommonUtility;
import com.tip.estimation.util.EstimationConstant;

	@Repository
	public class ImmediateInvPartRepositoryImpl  implements ImmediateInvPartRepository{
	
	static final Logger logger = LoggerFactory.getLogger(ImmediateInvPartRepositoryImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int[] saveRebillParts(List<Part> listOfParts, RebillDetails rebillDetails) {
		
		Connection connection = null;
		PreparedStatement statement = null;
		int[] updateCount = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			statement = connection
					.prepareStatement("{call " + EstimationConstant.PROC_IMMEDIATE_INVC_SAVE_PARTS+ " (?,?,?,?,?,?,?,?)}");
			connection.setAutoCommit(false);
			for (Part part : listOfParts) {
				statement.setBigDecimal(1, rebillDetails.getEstimationId());
				DatatypeCommonUtility.checkNull(2, statement, rebillDetails.getRebillNr());
				statement.setString(3, part.getPartsNumber());
				statement.setString(4, part.getPartDesc());
				DatatypeCommonUtility.checkNull(5, statement, part.getEstWoId());
				DatatypeCommonUtility.checkNull(6, statement, part.getEstWotId());
				statement.setBigDecimal(7, part.getActualCost());
				statement.setString(8, rebillDetails.getSsoId());
				statement.addBatch();
				logger.info("\n EstimationId:" + rebillDetails.getEstimationId() + "\n RebillNId:"
						+ rebillDetails.getRebillNr() + "\n WoId:" + part.getEstWoId() + "\n WotId:"
						+ part.getEstWotId() + "\n EstQty:" + part.getEstQty() + "\n ActualCost:" + part.getActualCost()
						+ "\n Est details:" + "\n SsoId:" + rebillDetails.getSsoId());
				logger.info("Batch added...");

			}
			logger.info("Executing batch.."+EstimationConstant.PROC_IMMEDIATE_INVC_SAVE_PARTS);
			updateCount = statement.executeBatch();
			connection.commit();
			logger.info("Return array  :" + Arrays.toString(updateCount));
			statement.close();
			connection.setAutoCommit(true);
		} catch (Exception e) {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e1) {
					logger.error("An error occurred : " + e1);
				}
			}
			logger.error("An error occurred : " + e);
		}
		return updateCount;
	}
}
