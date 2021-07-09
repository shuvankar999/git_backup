package com.tip.assetimage.repository.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tip.assetimage.model.AssetImageAccRequest;
import com.tip.assetimage.repository.AssetImageAccRepository;
import com.tip.elastic.util.CommonUtil;
import com.tip.elastic.util.ElasticSearchConstant;

@Repository
public class AssetImageAccRepositoryImpl implements AssetImageAccRepository {

	static final Logger logger = LoggerFactory.getLogger(AssetImageAccRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}

	@Override
	public boolean updateImageLoc(AssetImageAccRequest assetImageAccRequest) {
		String msg;
		boolean flag = false;
		String query = setQuery(assetImageAccRequest);
		try {
			PreparedStatement psmnt;
			if (query.contains("update")) {
				psmnt = jdbcTemplate.getDataSource().getConnection().prepareStatement(query);
				psmnt.setString(1, assetImageAccRequest.getImageLoc());
				psmnt.setInt(2, assetImageAccRequest.getEquipmentNr());
				psmnt.setString(3, assetImageAccRequest.getCode());
				if (assetImageAccRequest.getImageType().equalsIgnoreCase(ElasticSearchConstant.ATTRIBUTE))
					psmnt.setString(4, assetImageAccRequest.getSeq());
			} else {
				psmnt = jdbcTemplate.getDataSource().getConnection().prepareStatement(query);

				psmnt.setInt(1, assetImageAccRequest.getEquipmentNr());
				psmnt.setString(2, assetImageAccRequest.getCode());
				psmnt.setString(3, assetImageAccRequest.getImageLoc());
				psmnt.setString(4, assetImageAccRequest.getSsoId());
				psmnt.setTimestamp(5, CommonUtil.getSysDateSql());
				psmnt.setString(6, assetImageAccRequest.getSsoId());
				psmnt.setTimestamp(7, CommonUtil.getSysDateSql());

			}

			int s = psmnt.executeUpdate();
			if (s > 0) {
				msg = "ImageLoc Updated successfully in db!";
				flag = true;
				logger.info(msg);
			} else {
				msg = "Locaiton is not updated in db";
				logger.info(msg);
			}
		} catch (SQLException e1) {
			msg = "Exception Occured In update image location";
			logger.error(msg, e1);
		}
		return flag;
	}

	private String setQuery(AssetImageAccRequest assetImageAccRequest) {
		String qr = "";
		String tableName = "";
		String attribQuery = "";
		String unitNrColName = "";
		String codeColName = "";
		String imgLocColName = "";

		if (assetImageAccRequest.getImageType().equalsIgnoreCase(ElasticSearchConstant.ACCESSORIES)) {
			tableName = ElasticSearchConstant.ACCESSORIES_TAB;
			imgLocColName = "Accssry_Image_Loc";
			unitNrColName = "TPU_Unit_Nr";
			codeColName = "Accssry_Cd";
		} else if (assetImageAccRequest.getImageType().equalsIgnoreCase(ElasticSearchConstant.CAB)) {
			tableName = ElasticSearchConstant.CAB_TAB;
			imgLocColName = "Cab_Image_Loc";
			unitNrColName = "Equipment_Nr";
			codeColName = "Cab_Insp_Cd";
		} else if (assetImageAccRequest.getImageType().equalsIgnoreCase(ElasticSearchConstant.ATTRIBUTE)) {
			tableName = ElasticSearchConstant.ATTRIBUTE_TAB;
			imgLocColName = "Accssry_Image_Loc";
			unitNrColName = "Equipment_Nr";
			codeColName = "Attrib_Cd";
			attribQuery = " and Seq = ?";
		}

		String countQuery = "select count(*)as RECORDCOUNT from " + tableName + " where " + unitNrColName + "= ? AND "
				+ codeColName + " = ?";
		if (checkExistence(assetImageAccRequest, countQuery)) {
			qr = "update " + tableName + " set " + imgLocColName + " = ? where " + unitNrColName + "  = ? and "
					+ codeColName + " = ?" + attribQuery;
		} else {
			qr = "insert into " + tableName + " ( " + unitNrColName + ", " + codeColName + ", " + imgLocColName
					+ ", Create_User, Create_Date, Maint_User, Maint_Date) values ( ?, ?, ?, ?, ?, ?, ? )";
		}

		return qr;
	}

	public boolean checkExistence(AssetImageAccRequest assetImageAccRequest, String countQuery) {
		boolean existFlag = false;
		int count = 0;
		try {
			PreparedStatement stmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(countQuery);
			stmt.setInt(1, assetImageAccRequest.getEquipmentNr());
			stmt.setString(2, assetImageAccRequest.getCode());
			ResultSet resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				count = resultSet.getInt("RECORDCOUNT");
			}
			if (count > 0)
				existFlag = true;
		} catch (Exception e) {
			logger.error("An error occurred while equipment photo count : ", e);
		}
		return existFlag;
	}
}