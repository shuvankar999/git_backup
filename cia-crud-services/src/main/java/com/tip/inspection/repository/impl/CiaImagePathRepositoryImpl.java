package com.tip.inspection.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.tip.inspection.main.InspectionCrudConstants;
import com.tip.inspection.model.InspectionUploadImageRequest;
import com.tip.inspection.repository.CiaImagePathRepository;

@Repository
public class CiaImagePathRepositoryImpl implements CiaImagePathRepository, CallableStatementCreator  {
	
	static final Logger logger = LoggerFactory.getLogger(CiaImagePathRepositoryImpl.class);
	
	private String procedureCall;
	
	private InspectionUploadImageRequest inspectionUploadImageRequest;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void setInspectionUploadImageRequest(InspectionUploadImageRequest inspectionUploadImageRequest) {
		this.inspectionUploadImageRequest = inspectionUploadImageRequest;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String getImageLocation(InspectionUploadImageRequest inspectionUploadImageRequest) {
		
		String imagepath ="";
		
		Map<String, Object> resultMap = null;
		try {
			SqlParameter appCdParam = new SqlParameter(Types.VARCHAR);
			SqlParameter inspTypeParam = new SqlParameter(Types.VARCHAR);

			List paramList = new ArrayList();
			paramList.add(appCdParam);
			paramList.add(inspTypeParam);
			
			CiaImagePathRepositoryImpl ciaImagePathRepositoryImpl = new CiaImagePathRepositoryImpl();
			ciaImagePathRepositoryImpl.inspectionUploadImageRequest = inspectionUploadImageRequest;
			ciaImagePathRepositoryImpl.procedureCall = "{call "
					+ InspectionCrudConstants.PROC_GET_IMG_LOC+ " (?,?)}";
			resultMap = jdbcTemplate.call(ciaImagePathRepositoryImpl, paramList);
		} catch (Exception e) {
			logger.error("An error occurred getting path from server: " + e);
		}
			if (null != resultMap) {
				for (Map.Entry<String, Object> entry : resultMap.entrySet())
				{
					if ("#result-set-1".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
							Map<String, Object> responseMap = lst.get(0);
							imagepath = (String) responseMap.get("Img_Loc");
					}
				}
			}
		
		return imagepath;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setString(1, inspectionUploadImageRequest.getAppCd());
		callableStatement.setString(2, inspectionUploadImageRequest.getInspType());
		connection.setAutoCommit(true);
		return callableStatement;
	}



}

	