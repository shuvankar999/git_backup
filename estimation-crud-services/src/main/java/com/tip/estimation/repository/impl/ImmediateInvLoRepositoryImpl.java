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
import com.tip.estimation.model.RebillDetails;
import com.tip.estimation.model.Task;
import com.tip.estimation.repository.ImmediateInvLoRepository;
import com.tip.estimation.util.DatatypeCommonUtility;
import com.tip.estimation.util.EstimationConstant;

	@Repository
	public class ImmediateInvLoRepositoryImpl  implements ImmediateInvLoRepository{
	
	static final Logger logger = LoggerFactory.getLogger(ImmediateInvLoRepositoryImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int[] saveRebillLineLo(List<Task> listOfTasks, RebillDetails rebillDetails) {
		
		Connection connection = null;
		PreparedStatement statement = null;
		ObjectMapper mapper = new ObjectMapper();
		int[] updateCount = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			statement = connection
					.prepareStatement("{call " + EstimationConstant.PROC_IMMEDIATE_INVC_SAVE_LO+ " (?,?,?,?,?,?,?)}");
			connection.setAutoCommit(false);
			for (Task task : listOfTasks) {
				statement.setBigDecimal(1, rebillDetails.getEstimationId());
				DatatypeCommonUtility.checkNull(2, statement, rebillDetails.getRebillNr());
				DatatypeCommonUtility.checkNull(3, statement, task.getEstWoId());
				DatatypeCommonUtility.checkNull(4, statement, task.getEstWotId());
				statement.setBigDecimal(5, task.getActualCost());
				statement.setString(6, task.getTaskName());
				statement.setString(7, rebillDetails.getSsoId());
				statement.addBatch();

				logger.info("\n Labour Object:- \n " + mapper.writeValueAsString(task));
				logger.info("Batch added...");
			}
			logger.info("Rebill Details Object:- "+mapper.writeValueAsString(rebillDetails));
			logger.info("Executing batch.."+EstimationConstant.PROC_IMMEDIATE_INVC_SAVE_LO);
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
