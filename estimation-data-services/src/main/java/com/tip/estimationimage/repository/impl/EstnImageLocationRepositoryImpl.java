package com.tip.estimationimage.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.tip.estimationimage.model.EstnImageRequest;
import com.tip.estimationimage.repository.EstnImageLocationRepository;
import com.tip.util.EstimationConstant;

@Repository
public class EstnImageLocationRepositoryImpl implements EstnImageLocationRepository , CallableStatementCreator{

	static final Logger logger = LoggerFactory.getLogger(EstnImageLocationRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	String procedureCall;
	
	EstnImageRequest estnImageRequest;

	@Autowired
	public void setDataSource(DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}
	
	public void setAssetImageRequest(EstnImageRequest estnImageRequest) {
		this.estnImageRequest = estnImageRequest;
	}
	
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public String getImageLocation(EstnImageRequest estnImageRequest) {
		String serverLocation = "";
        Map<String, Object> resultMap = new HashMap<>();
        try {
        	SqlParameter appCdSQLparam = new SqlParameter(Types.VARCHAR);
			SqlParameter inspTypeSQLparam = new SqlParameter(Types.VARCHAR);
			
            List paramList = new ArrayList();
            paramList.add(appCdSQLparam);
			paramList.add(inspTypeSQLparam);

			EstnImageLocationRepositoryImpl estImageLocationRepositoryImpl = new EstnImageLocationRepositoryImpl();
			estImageLocationRepositoryImpl.estnImageRequest = estnImageRequest;
			estImageLocationRepositoryImpl.procedureCall = "{call " + EstimationConstant.PROC_FETCH_ESTN_IMAGE_REMOTE_LOCATION + "(?,?)}";
            resultMap = jdbcTemplate.call((CallableStatementCreator) estImageLocationRepositoryImpl, paramList);
        } catch (Exception e) {
        	logger.error("Error Encountered in fetching Server Location for Estimation Images " ,e);
        }

        if (null != resultMap) {
			for (Map.Entry<String, Object> entry : resultMap.entrySet())
			{
				if("#result-set-1".equalsIgnoreCase(entry.getKey()))
                {
					List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						Map<String, Object> equipmentDetailsMap = lst.get(0);
						serverLocation = (String) equipmentDetailsMap.get("Img_Loc");
                }
			}
		}
        return serverLocation;
	}

	
	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall(procedureCall);
        callableStatement.setString(1, estnImageRequest.getAppCd());
        callableStatement.setString(2, "NULL");
        connection.setAutoCommit(true);
        return callableStatement;
}
}
