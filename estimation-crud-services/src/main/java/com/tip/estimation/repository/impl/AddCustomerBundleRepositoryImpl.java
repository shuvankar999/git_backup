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

import com.tip.estimation.model.CustomerBundleObj;
import com.tip.estimation.repository.AddCustomerBundleRepository;
import com.tip.estimation.util.DatatypeCommonUtility;
import com.tip.estimation.util.EstimationConstant;

	@Repository
	public class AddCustomerBundleRepositoryImpl  implements AddCustomerBundleRepository{
	
	static final Logger logger = LoggerFactory.getLogger(AddCustomerBundleRepositoryImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int[] addCustBundle(List<CustomerBundleObj> custBundleRequest) {
		
		Connection connection = null;
		PreparedStatement statement = null;
		int[] updateCount = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			statement = connection
					.prepareStatement("{call " + EstimationConstant.PROC_ESTIMATION_ADD_CUSTOMER_BUNDLE+ " (?,?,?,?,?,?,?,?,?,?)}");
			connection.setAutoCommit(false);
			for (CustomerBundleObj bundle : custBundleRequest) {
					statement.setBigDecimal(1, bundle.getEstimationId());
					statement.setString(2, bundle.getBundleName());
					statement.setString(3, bundle.getDnaCode());
					statement.setString(4, bundle.getMaintenanceAction());
					statement.setString(5, bundle.getManufacturerId());
					statement.setString(6, bundle.getManufacturer());
					statement.setString(7, bundle.getSupplierPartNr());
					DatatypeCommonUtility.checkNull(8, statement, bundle.getFee());
					statement.setString(9, bundle.getSsoId());
					statement.setString(10, bundle.getErrorCd());
					statement.addBatch();
			}
			logger.info("Executing batch.."+EstimationConstant.PROC_ESTIMATION_ADD_CUSTOMER_BUNDLE);
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
					logger.error("An error occurred while adding Customer Bundle details : " + e1);
				}
			}
			logger.error("An error occurred while adding Customer Bundle details : " + e);
		}

		return updateCount;
	}
		


}
