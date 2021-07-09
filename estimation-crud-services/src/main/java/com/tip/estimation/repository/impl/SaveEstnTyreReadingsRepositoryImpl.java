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

import com.tip.estimation.model.EstnTyreReadingObject;
import com.tip.estimation.model.SaveEstnTyreRdngRequest;
import com.tip.estimation.repository.SaveEstnTyreReadingsRepository;
import com.tip.estimation.util.DatatypeCommonUtility;
import com.tip.estimation.util.EstimationConstant;

@Repository
public class SaveEstnTyreReadingsRepositoryImpl implements SaveEstnTyreReadingsRepository {

	static final Logger logger = LoggerFactory.getLogger(SaveEstnTyreReadingsRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Object> saveTyreReadings(SaveEstnTyreRdngRequest saveEstnTyreRdngRequest) {
		Map<String, Object> returnMap = new HashMap();
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"{call " + EstimationConstant.PROC_ESTIMATION_SAVE_TYRE_READING + "(?,?,?,?,?,?,?,?,?,?)}");
			connection.setAutoCommit(false);
	
			for (EstnTyreReadingObject estnTyreReadingObject : saveEstnTyreRdngRequest.getEstnTyreReadingList()) {

				statement.setBigDecimal(1, estnTyreReadingObject.getEstimationId());
				statement.setString(2, estnTyreReadingObject.getTyreCd());
				DatatypeCommonUtility.checkNull(3, statement, estnTyreReadingObject.getTyreDepth());
				DatatypeCommonUtility.checkNull(4, statement, estnTyreReadingObject.getTyrePsi());
				DatatypeCommonUtility.checkNull(5, statement, estnTyreReadingObject.getTyreBar());

				statement.setString(6, estnTyreReadingObject.getTyreMake());
				statement.setString(7, estnTyreReadingObject.getTyreSerialNr());
				statement.setString(8, estnTyreReadingObject.getTyreSize());
				statement.setString(9, estnTyreReadingObject.getApplication());
				statement.setString(10, estnTyreReadingObject.getSsoId());

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

			logger.error("An error occured while saving the estimation Tyre Readings" + e);
			returnMap.put(EstimationConstant.ERROR_CD, "FAILURE");

		}
		return returnMap;
	
	}

}
