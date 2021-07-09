package com.tip.estimation.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tip.estimation.model.SaveEstnPartDetailsRequest;
import com.tip.estimation.model.SaveEstnPartsList;
import com.tip.estimation.repository.SaveEstnPartsListRepository;
import com.tip.estimation.util.DatatypeCommonUtility;
import com.tip.estimation.util.EstimationConstant;

@Repository
public class SaveEstnPartsListRepositoryImpl implements SaveEstnPartsListRepository{
	
	static final Logger logger = LoggerFactory.getLogger(SaveEstnPartsListRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })	
	@Override
	public Map<String, Object> saveEstnPartDetails(SaveEstnPartDetailsRequest saveEstnPartDetailsRequest) {
		Map<String, Object> returnMap = new HashMap();
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"{call " + EstimationConstant.PROC_ESTIMATION_SAVE_PARTS_DETAILS+ "(?,?,?,?,?,?,?,?,?,?,?)}");
			connection.setAutoCommit(false);
	
			for (SaveEstnPartsList saveEstnPartsList : saveEstnPartDetailsRequest.getEstnPartsDetailsList()) {

				statement.setBigDecimal(1, saveEstnPartsList.getEstimationId());
				DatatypeCommonUtility.checkNull(2, statement, saveEstnPartsList.getEstnWOId());
				DatatypeCommonUtility.checkNull(3, statement, saveEstnPartsList.getEstnWOTId());
				statement.setString(4, saveEstnPartsList.getPartNumber());
				statement.setString(5, saveEstnPartsList.getPartDescription());

				DatatypeCommonUtility.checkNull(6, statement, saveEstnPartsList.getQty());
				statement.setBigDecimal(7, saveEstnPartsList.getOeListPrice());
				statement.setBigDecimal(8, saveEstnPartsList.getCostPrice());
				DatatypeCommonUtility.checkNull(9, statement, saveEstnPartsList.getSupplierId());
				statement.setString(10, saveEstnPartsList.getCurrency());
				statement.setString(11, saveEstnPartsList.getSsoId());

				statement.addBatch();
			}

			int[] resultArr = statement.executeBatch();
			connection.commit();
			logger.debug("Return array :" + Arrays.toString(resultArr));
			for (int i : resultArr) {
				if (i == 0) {
					returnMap.put(EstimationConstant.ERROR_CD, "ROLLBACK");
					logger.error(EstimationConstant.ERROR_CD + ": FAILURE");
					break;
				} else {
					returnMap.put(EstimationConstant.ERROR_CD, "SUCCESS");
					logger.debug(EstimationConstant.ERROR_CD + ": SUCCESS");
				}
			}
			statement.close();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			
			logger.error("An error occured while saving estimation Parts details"+e);
			returnMap.put(EstimationConstant.ERROR_CD, "FAILURE");

		}
		return returnMap;
	
	}
}



