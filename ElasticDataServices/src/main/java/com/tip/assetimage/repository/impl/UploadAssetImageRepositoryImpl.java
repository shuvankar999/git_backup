package com.tip.assetimage.repository.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tip.assetimage.model.AssetImageRequest;
import com.tip.assetimage.model.DeleteAssetImageRequest;
import com.tip.assetimage.repository.UploadAssetImageRepository;

@Repository
public class UploadAssetImageRepositoryImpl implements UploadAssetImageRepository {// ,CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(UploadAssetImageRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private String uploadTableName;
	
	/*private String procedureCall;
	
	AssetImageRequest assetImageRequest;*/

	@Autowired
	public void setDataSource(DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}
	
	@Override
	public String uploadImage(File image, AssetImageRequest assetImageRequest){
		String msg;
		FileInputStream fis = null;
		if("TIPAssets".equalsIgnoreCase(assetImageRequest.getAssetType()))
				uploadTableName = "OPSimg..Remarketing_Unit_Images";
		else
			uploadTableName = "OPSwshp..Wshp_TPU_Asset_Images";
		try {
			PreparedStatement psmnt;
			fis = new FileInputStream(image);
			
			String countQuery = "select count(*)as RECORDCOUNT from " + uploadTableName  +" where Unit_Nr = ? AND Image_Type = ?";
			if(checkImageExistence(assetImageRequest,countQuery))
			{
				psmnt = jdbcTemplate.getDataSource().getConnection().prepareStatement("update " + uploadTableName +
						" SET Picture = ? where Unit_Nr = ? AND Image_Type = ?");
				psmnt.setBinaryStream(1, (InputStream)fis, (int)(image.length()));
				psmnt.setInt(2,Integer.parseInt(assetImageRequest.getUnitNr()));
			}
			else
			{
				psmnt = jdbcTemplate.getDataSource().getConnection().prepareStatement("insert into " + uploadTableName +
						"(Unit_Nr,Picture,Image_Type) "+ "values(?,?,?)");
				psmnt.setInt(1,Integer.parseInt(assetImageRequest.getUnitNr()));
				psmnt.setBinaryStream(2, (InputStream)fis, (int)(image.length()));
			}
			psmnt.setString(3,assetImageRequest.getImageType());			
			int s = psmnt.executeUpdate();
			if(s>0) {
				msg = "Uploaded successfully !";
				logger.info(msg);
			}
			else {
				msg = "Upload image Unsuccessfull";
				logger.info(msg);
			}
			fis.close();
		}
		catch (SQLException | IOException e1) {
			msg = "Exception Occured In Upload image";
			logger.error(msg,e1);
			if(fis != null)
				try {
					fis.close();
				} catch (IOException e) {
					logger.error(msg,e);
				}
		}
		return msg;
	}
	
	public boolean checkImageExistence(AssetImageRequest assetImageRequest,String countQuery) {
		boolean existFlag = false;
		int count = 0;
		try {
			PreparedStatement stmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(countQuery);
			stmt.setInt(1, Integer.parseInt(assetImageRequest.getUnitNr()));
			stmt.setString(2, assetImageRequest.getImageType());
			ResultSet resultSet = stmt.executeQuery();
			if(resultSet.next())
			{
				count = resultSet.getInt("RECORDCOUNT");
			}
			if(count > 0)
				existFlag = true;
		} catch (Exception e) {
			logger.error("An error occurred while asset Image count : ", e);
		}
		return existFlag;
	}
	
	@Override
	public String deleteImage(DeleteAssetImageRequest deleteImageRequest){
		String msg;
		if("TIPAssets".equalsIgnoreCase(deleteImageRequest.getAssetType()))
				uploadTableName = "OPSimg..Remarketing_Unit_Images";
		else
			uploadTableName = "OPSwshp..Wshp_TPU_Asset_Images";
		try {
			PreparedStatement psmnt;
			psmnt = jdbcTemplate.getDataSource().getConnection().prepareStatement("DELETE FROM " + uploadTableName +
					" WHERE Unit_Nr = ? AND Image_Type = ? AND Id = ?");
			psmnt.setInt(1,Integer.parseInt(deleteImageRequest.getUnitNr()));
			psmnt.setString(2,deleteImageRequest.getImageType());
			psmnt.setInt(3,deleteImageRequest.getImageId());
			int s = psmnt.executeUpdate();
			if(s>0) {
				msg = "Deleted successfully !";
				logger.info(msg);
			}
			else {
				msg = "Delete image Unsuccessfull";
				logger.info(msg);
			}
		}
		catch (SQLException e1) {
			msg = "Exception Occured In Deleting image";
			logger.error(msg,e1);
		}
		return msg;
	}

    /*@Override
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
	}*/
}