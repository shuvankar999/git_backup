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
import com.tip.estimation.model.InvoiceTyreService;
import com.tip.estimation.model.RebillDetails;
import com.tip.estimation.model.Tyre;
import com.tip.estimation.repository.ImmediateInvTyreRepository;
import com.tip.estimation.util.DatatypeCommonUtility;
import com.tip.estimation.util.EstimationConstant;

	@Repository
	public class ImmediateInvTyreRepositoryImpl  implements ImmediateInvTyreRepository{
	
	static final Logger logger = LoggerFactory.getLogger(ImmediateInvTyreRepositoryImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int[] saveRebillTyreSpecs(List<Tyre> listOfTyreSpecs, RebillDetails rebillDetails) {
		
		Connection connection = null;
		PreparedStatement statement = null;
		int[] updateCount = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			statement = connection
					.prepareStatement("{call " + EstimationConstant.PROC_IMMEDIATE_INVC_SAVE_TYRE_SPECS+ " (?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			connection.setAutoCommit(false);
			ObjectMapper mapper = new ObjectMapper();
			for (Tyre tyreSpecs : listOfTyreSpecs) {
				statement.setBigDecimal(1, rebillDetails.getEstimationId());
				DatatypeCommonUtility.checkNull(2, statement, rebillDetails.getRebillNr());
				DatatypeCommonUtility.checkNull(3, statement, tyreSpecs.getEstWoId());
				DatatypeCommonUtility.checkNull(4, statement, tyreSpecs.getEstWotId());
				DatatypeCommonUtility.checkNull(5, statement, tyreSpecs.getTyreSpecgrpId());
				DatatypeCommonUtility.checkNull(6, statement, tyreSpecs.getTyreSpecsubgrpId());
				DatatypeCommonUtility.checkNull(7, statement, tyreSpecs.getTyreSpecItemId());
				statement.setString(8, tyreSpecs.getTyreSize());
				statement.setString(9, tyreSpecs.getBrand());
				statement.setString(10, tyreSpecs.getTyreStatus());
				statement.setString(11, tyreSpecs.getChargeType());
				statement.setBigDecimal(12, tyreSpecs.getTyrePrice());
				statement.setString(13, rebillDetails.getSsoId());
				statement.addBatch();
				
				logger.info("\n TyreSpecsObject:- \n " + mapper.writeValueAsString(tyreSpecs));
				logger.info("Batch added...");

			}
			logger.info("Executing batch.."+EstimationConstant.PROC_IMMEDIATE_INVC_SAVE_TYRE_SPECS);
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

	@Override
	public int[] saveRebillTyreServices(List<InvoiceTyreService> listOfTyreServices, RebillDetails rebillDetails) {
		
		Connection connection = null;
		PreparedStatement statement = null;
		int[] updateCount = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			statement = connection
					.prepareStatement("{call " + EstimationConstant.PROC_IMMEDIATE_INVC_SAVE_TYRE_SERVICES+ " (?,?,?,?,?,?,?,?,?,?)}");
			connection.setAutoCommit(false);
			ObjectMapper mapper = new ObjectMapper();
			for (InvoiceTyreService tyreServices : listOfTyreServices) {
				statement.setBigDecimal(1, rebillDetails.getEstimationId());
				DatatypeCommonUtility.checkNull(2, statement, rebillDetails.getRebillNr());
				DatatypeCommonUtility.checkNull(3, statement, tyreServices.getEstWoId());
				DatatypeCommonUtility.checkNull(4, statement, tyreServices.getEstWotId());
				DatatypeCommonUtility.checkNull(5, statement, tyreServices.getTyreServicegrpId());
				DatatypeCommonUtility.checkNull(6, statement, tyreServices.getTyreServicesubgrpId());
				DatatypeCommonUtility.checkNull(7, statement, tyreServices.getTyreServiceItemId());
				statement.setString(8, tyreServices.getTyreService());
				statement.setBigDecimal(9, tyreServices.getCharges());
				statement.setString(10, rebillDetails.getSsoId());
				statement.addBatch();
				
				logger.info("\n TyreServiceObject:- \n " + mapper.writeValueAsString(tyreServices));
				logger.info("Batch added...");

			}
			logger.info("Executing batch.."+EstimationConstant.PROC_IMMEDIATE_INVC_SAVE_TYRE_SERVICES);
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
