package com.tip.assetimage.repository.impl;

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

import com.tip.asset.util.AssetConstants;
import com.tip.assetimage.model.AssetImageRequest;
import com.tip.assetimage.repository.AssetImageLocationRepository;

@Repository
public class AssetImageLocationRepositoryImpl implements AssetImageLocationRepository ,CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(AssetImageLocationRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	String procedureCall;
	
	AssetImageRequest assetImageRequest;

	@Autowired
	public void setDataSource(DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public String getImageLocation(AssetImageRequest assetImageRequest) {

		String serverLocation = "";
        Map<String, Object> resultMap = new HashMap<>();
        try {
        	SqlParameter appCdSQLparam = new SqlParameter(Types.VARCHAR);
			SqlParameter inspTypeSQLparam = new SqlParameter(Types.VARCHAR);
			
            List paramList = new ArrayList();
            paramList.add(appCdSQLparam);
			paramList.add(inspTypeSQLparam);

            AssetImageLocationRepositoryImpl sssetImageLocationRepositoryImpl = new AssetImageLocationRepositoryImpl();
            sssetImageLocationRepositoryImpl.assetImageRequest = assetImageRequest;
            sssetImageLocationRepositoryImpl.procedureCall = "{call " + AssetConstants.PROC_FETCH_ASSET_IMAGE_REMOTE_LOCATION + "(?,?)}";
            resultMap = jdbcTemplate.call((CallableStatementCreator) sssetImageLocationRepositoryImpl, paramList);
        } catch (Exception e) {
        	logger.error("Error Encountered in fetching Server Location for Asset Images " ,e);
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
        callableStatement.setString(1, assetImageRequest.getAppCd());
        callableStatement.setString(2, "NULL");
        connection.setAutoCommit(true);
        return callableStatement;
    }

	public void setAssetImageRequest(AssetImageRequest assetImageRequest) {
		this.assetImageRequest = assetImageRequest;
	}
}