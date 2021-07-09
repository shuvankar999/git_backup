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

import com.tip.estimation.model.EstnBrakeReadingObject;
import com.tip.estimation.model.SaveEstnBrakeRdngRequest;
import com.tip.estimation.repository.SaveEstnBrakeReadingsRepository;
import com.tip.estimation.util.DatatypeCommonUtility;
import com.tip.estimation.util.EstimationConstant;

@Repository
public class SaveEstnBrakeReadingsRepositoryImpl implements SaveEstnBrakeReadingsRepository{
	
	static final Logger logger = LoggerFactory.getLogger(SaveEstnBrakeReadingsRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Object> saveBrakeReadings(SaveEstnBrakeRdngRequest saveEstnBrakeRdngRequest) {
		Map<String, Object> returnMap = new HashMap();
		Connection connection = null;
		try {
			
			connection = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"{call " + EstimationConstant.PROC_ESTIMATION_SAVE_BRAKE_READING + "(?,?,?,?,?)}");
			connection.setAutoCommit(false);
	
			for (EstnBrakeReadingObject estnBrakeReadingObject : saveEstnBrakeRdngRequest.getEstnBrakeReadingList()) {

				statement.setBigDecimal(1, estnBrakeReadingObject.getEstimationId());
				statement.setString(2, estnBrakeReadingObject.getBrakeCd());
				DatatypeCommonUtility.checkNull(3, statement, estnBrakeReadingObject.getLiningRemPer());
				DatatypeCommonUtility.checkNull(4, statement, estnBrakeReadingObject.getLiningRemMM());				
				statement.setString(5, estnBrakeReadingObject.getSsoId());

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
					returnMap.put(EstimationConstant.ERROR_CD, "COMMIT");
					logger.debug(EstimationConstant.ERROR_CD + ": SUCCESS");
				}
			}
			statement.close();
			connection.setAutoCommit(true);
		} catch (SQLException e) {

			logger.error("An error occured while saving the estimation Brake Reading" + e);
			StackTraceElement stackTraceElement = e.getStackTrace()[0];
			returnMap.put(EstimationConstant.ERROR_CD, "FAILURE");

		}
		return returnMap;
	
	}

}


