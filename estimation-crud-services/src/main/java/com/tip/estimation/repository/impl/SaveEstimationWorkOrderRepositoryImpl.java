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

import com.tip.estimation.model.EstnWorkOrderObject;
import com.tip.estimation.model.EstnWorkOrderTaskObject;
import com.tip.estimation.model.SaveEstnWorkOrderRequest;
import com.tip.estimation.repository.SaveEstimationWorkOrderRepository;
import com.tip.estimation.util.DatatypeCommonUtility;
import com.tip.estimation.util.EstimationConstant;

@Repository
public class SaveEstimationWorkOrderRepositoryImpl implements SaveEstimationWorkOrderRepository{
	
static final Logger logger = LoggerFactory.getLogger(SaveEstimationWorkOrderRepositoryImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public Map<String, Object> saveEstnWorkOrder(SaveEstnWorkOrderRequest saveEstnWorkOrderRequest) {
		Map<String, Object> returnMap = new HashMap();
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement statement = connection.prepareStatement("{call "+EstimationConstant.PROC_ESTIMATION_SAVE_WO_WOT+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			connection.setAutoCommit(false);

			for(EstnWorkOrderObject estnWorkOrderObject : saveEstnWorkOrderRequest.getEstnWorkOrderList()){
				for(EstnWorkOrderTaskObject estnWorkOrderTaskObject : estnWorkOrderObject.getEstnWorkOrderTaskList()){
					statement.setBigDecimal(1, saveEstnWorkOrderRequest.getEstimationId());
					statement.setString(20, saveEstnWorkOrderRequest.getSsoId());
					DatatypeCommonUtility.checkNull(2, statement, estnWorkOrderObject.getEstnworkorderId());
					DatatypeCommonUtility.checkNull(3, statement, estnWorkOrderTaskObject.getEstnWOTId());
					statement.setString(4, estnWorkOrderObject.getGroupCd());
					statement.setString(5, estnWorkOrderTaskObject.getSubgroupCd());
					statement.setString(6, estnWorkOrderTaskObject.getActivity());
					statement.setString(7, estnWorkOrderTaskObject.getFailureCause());
					statement.setString(8, estnWorkOrderTaskObject.getAction());
					statement.setString(9, estnWorkOrderTaskObject.getPosition());					
					DatatypeCommonUtility.checkNull(10, statement, estnWorkOrderTaskObject.getTargetTime());
					statement.setString(11, estnWorkOrderTaskObject.getReason());
					statement.setString(12, estnWorkOrderTaskObject.getTaskComments());
					DatatypeCommonUtility.checkNull(13, statement, estnWorkOrderObject.getSupplierId());
					statement.setString(14, estnWorkOrderObject.getWoCustComments());				
					statement.setString(15, estnWorkOrderObject.getWoIntComments());
					statement.setString(16, estnWorkOrderTaskObject.getLabourTime());					
					DatatypeCommonUtility.checkNull(17, statement, estnWorkOrderTaskObject.getSoldTime());
					DatatypeCommonUtility.checkNull(18, statement, estnWorkOrderTaskObject.getLabourRate());					
					statement.setString(19, estnWorkOrderTaskObject.getShowHide());
								
					statement.addBatch();
				}
			}
			int[] resultArr = statement.executeBatch();
			connection.commit();
			logger.debug("Return array :"+Arrays.toString(resultArr));
			for(int i:resultArr){
				if(i==0){
					returnMap.put(EstimationConstant.ERROR_CD, "FAILURE");
					logger.error(EstimationConstant.ERROR_CD+": FAILURE");
					break;
				}else{
					returnMap.put(EstimationConstant.ERROR_CD, "SUCCESS");
					logger.debug(EstimationConstant.ERROR_CD+": SUCCESS");
				}
			}
			statement.close();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			StackTraceElement stackTraceElement = e.getStackTrace()[0];
			returnMap.put(EstimationConstant.ERROR_CD, "FAILURE");
			logger.error("error occured while saving WoWot list" + e);
		}
		return returnMap;
	}
}

