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
import com.tip.asset.util.DatatypeCommonUtility;
import com.tip.assetimage.model.AssetImageRequest;
import com.tip.assetimage.repository.AssetImageRepository;

@Repository
public class AssetImageRepositoryImpl implements AssetImageRepository ,CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(AssetImageRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private String procedureCall;
	
	AssetImageRequest assetImageRequest;

	@Autowired
	public void setDataSource(DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}

	/*@Override
	public ResultSet getTipAssetDashboardImage(AssetImageRequest assetImageRequest) {
		ResultSet resultSet = null;
		try {
			String sql = "select Picture,Image_Type,Unit_Nr,Id from OPSimg..Remarketing_Unit_Images where Unit_Nr = ?";
			PreparedStatement stmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(assetImageRequest.getUnitNr()));
			
			String sql = AssetConstants.QUERY_TO_FETCH_TIP_ASSET_IMAGES;
			PreparedStatement stmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(assetImageRequest.getUnitNr()));
			stmt.setInt(2, Integer.parseInt(assetImageRequest.getLangId()));
			resultSet = stmt.executeQuery();
		} catch (Exception e) {
			logger.error("An error occurred while fetching asset Images : ", e);
		}
		return resultSet;
	}
	
	@Override
	public ResultSet getTpAssetDashboardImage(AssetImageRequest assetImageRequest) {
		ResultSet resultSet = null;
		try {
			String sql = "select Picture,Image_Type,Unit_Nr,Id from OPSimg..Remarketing_Unit_Images where Unit_Nr = ?";
			PreparedStatement stmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(assetImageRequest.getUnitNr()));
			
			String sql = AssetConstants.QUERY_TO_FETCH_TP_ASSET_IMAGES;
			PreparedStatement stmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(assetImageRequest.getUnitNr()));
			stmt.setInt(2, Integer.parseInt(assetImageRequest.getLangId()));
			resultSet = stmt.executeQuery();
		} catch (Exception e) {
			logger.error("An error occurred while fetching asset Images : ", e);
		}
		return resultSet;
	}*/
	
	

	
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
    public Map<String, Object> getAssetDashboardImage(AssetImageRequest assetImageRequest) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
        	SqlParameter appCdSQLparam = new SqlParameter(Types.VARCHAR);
			SqlParameter unitNrSQLparam = new SqlParameter(Types.INTEGER);
			SqlParameter ssoIdSQLparam = new SqlParameter(Types.VARCHAR);
			SqlParameter langIdSQLparam = new SqlParameter(Types.INTEGER);
			
            List paramList = new ArrayList();
            paramList.add(appCdSQLparam);
			paramList.add(unitNrSQLparam);
			paramList.add(ssoIdSQLparam);
			paramList.add(langIdSQLparam);

			AssetImageRepositoryImpl assetImageRepositoryImpl = new AssetImageRepositoryImpl();
            assetImageRepositoryImpl.assetImageRequest = assetImageRequest;
            assetImageRepositoryImpl.procedureCall = "{call " + AssetConstants.PROC_FETCH_ASSET_IMAGES + "(?,?,?,?)}";
            resultMap = jdbcTemplate.call((CallableStatementCreator) assetImageRepositoryImpl, paramList);
        } catch (Exception e) {
        	logger.error("Error Encountered in fetching Asset Images " ,e);
        }
        return resultMap;
    }

    @Override
    public CallableStatement createCallableStatement(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall(procedureCall);
        callableStatement.setString(1, assetImageRequest.getAppCd());
        DatatypeCommonUtility.checkNull(2, callableStatement, Integer.parseInt(assetImageRequest.getUnitNr()));
        callableStatement.setString(3, assetImageRequest.getSsoId());
        callableStatement.setNull(4, java.sql.Types.INTEGER);
        connection.setAutoCommit(true);
        return callableStatement;
    }

	public void setAssetImageRequest(AssetImageRequest assetImageRequest) {
		this.assetImageRequest = assetImageRequest;
	}
}