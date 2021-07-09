package com.tip.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.tip.model.DeleteImageRequest;
import com.tip.repository.ImageDeteleRepository;
import com.tip.util.CommonUtil;

/**
 * @author Shuvankar Paul Created on Nov 10, 2017
 * 
 */
@Repository
public class ImageDeleteRepositoryImpl implements ImageDeteleRepository, CallableStatementCreator {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private DeleteImageRequest deleteImageRequest;
	private String procedureCall;

	private Logger logger = LoggerFactory.getLogger(ImageDeleteRepositoryImpl.class);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> removeImage(DeleteImageRequest deleteImageRequest) {

		Map<String, Object> resultMap = null;
		Map<String, Object> imageDeleteMap = new HashMap();
		try {

			SqlParameter workpacksql = new SqlParameter(Types.DECIMAL);
			SqlParameter workordersql = new SqlParameter(Types.INTEGER);
			SqlParameter workorderTasksql = new SqlParameter(Types.INTEGER);
			SqlParameter imgTypesql = new SqlParameter(Types.VARCHAR);
			SqlParameter imgNamesql = new SqlParameter(Types.VARCHAR);
			SqlOutParameter errorCdsqlOutParam = new SqlOutParameter("Error_Cd", Types.VARCHAR);

			List paramList = new ArrayList();
			paramList.add(workpacksql);
			paramList.add(workordersql);
			paramList.add(workorderTasksql);
			paramList.add(imgTypesql);
			paramList.add(imgNamesql);
			paramList.add(errorCdsqlOutParam);

			ImageDeleteRepositoryImpl imageDeleteRepositoryImpl = new ImageDeleteRepositoryImpl();
			imageDeleteRepositoryImpl.deleteImageRequest = deleteImageRequest;

			imageDeleteRepositoryImpl.procedureCall = "{call OPSwshp..Wshp_Delete_Images(?, ?, ?, ?, ?, ?)}";
			logger.info("Calling procedure..." + imageDeleteRepositoryImpl.procedureCall);
			resultMap = jdbcTemplate.call((CallableStatementCreator) imageDeleteRepositoryImpl, paramList);

			Iterator<Entry<String, Object>> it = resultMap.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, Object> entry = it.next();
				String key = entry.getKey();
				logger.info("Key>>>" + key);
				logger.info("Value>>>" + entry.getValue());
				if (("Error_Cd").equalsIgnoreCase(key)) {
					imageDeleteMap.put("Error_Cd", entry.getValue());
				}

			}
		} catch (Exception e) {
			logger.error("An error occurred while calling procedure to delete image details: " + e);
		}

		return imageDeleteMap;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setBigDecimal(1, CommonUtil.convertStringToBigDecimal(deleteImageRequest.getWorkPackNr()));
		callableStatement.setInt(2, CommonUtil.convertStringToInt(deleteImageRequest.getWorkOrderNr()));
		callableStatement.setInt(3, CommonUtil.convertStringToInt(deleteImageRequest.getWorkOrderTaskNr()));
		callableStatement.setString(4, deleteImageRequest.getImageType());
		callableStatement.setString(5, deleteImageRequest.getImageName());
		callableStatement.registerOutParameter(6, Types.VARCHAR);
		connection.setAutoCommit(true);
		return callableStatement;
	}

	public void setDeleteImageRequest(DeleteImageRequest deleteImageRequest) {
		this.deleteImageRequest = deleteImageRequest;
	}

}
