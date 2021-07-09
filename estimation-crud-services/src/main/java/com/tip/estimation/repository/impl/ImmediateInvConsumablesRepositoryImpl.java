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
import com.tip.estimation.model.Consumables;
import com.tip.estimation.model.RebillDetails;
import com.tip.estimation.repository.ImmediateInvConsumablesRepository;
import com.tip.estimation.util.DatatypeCommonUtility;
import com.tip.estimation.util.EstimationConstant;

	@Repository
	public class ImmediateInvConsumablesRepositoryImpl  implements ImmediateInvConsumablesRepository{
	
	static final Logger logger = LoggerFactory.getLogger(ImmediateInvConsumablesRepositoryImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int[] saveRebillConsumables(List<Consumables> listOfConsumables, RebillDetails rebillDetails) {
		
		Connection connection = null;
		PreparedStatement statement = null;
		ObjectMapper mapper = new ObjectMapper();
		int[] updateCount = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			statement = connection
					.prepareStatement("{call " + EstimationConstant.PROC_IMMEDIATE_INVC_SAVE_CONSUMABLES+ " (?,?,?,?,?,?,?,?)}");
			connection.setAutoCommit(false);
			for (Consumables consumables : listOfConsumables) {
				statement.setBigDecimal(1, rebillDetails.getEstimationId());
				DatatypeCommonUtility.checkNull(2, statement, rebillDetails.getRebillNr());
				DatatypeCommonUtility.checkNull(3, statement, consumables.getConsmblegroupId());
				DatatypeCommonUtility.checkNull(4, statement, consumables.getConsmblesubGroupId());
				DatatypeCommonUtility.checkNull(5, statement, consumables.getConsmbleitemId());
				statement.setString(6, consumables.getConsumables());
				statement.setBigDecimal(7, consumables.getCharges());
				statement.setString(8, rebillDetails.getSsoId());
				statement.addBatch();

				logger.info("\n ConsumablesObj:- " + mapper.writeValueAsString(consumables));
				logger.info("Batch added...");
			}
			logger.info("Rebill details:- "+mapper.writeValueAsString(rebillDetails));
			logger.info("Executing batch.."+EstimationConstant.PROC_IMMEDIATE_INVC_SAVE_CONSUMABLES);
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
