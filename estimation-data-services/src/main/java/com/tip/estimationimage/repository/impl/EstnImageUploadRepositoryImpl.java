package com.tip.estimationimage.repository.impl;

import java.math.BigDecimal;
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

import com.tip.estimationimage.model.EstnImageRequest;
import com.tip.estimationimage.repository.EstnImageUploadRepository;
import com.tip.util.DatatypeCommonUtility;
import com.tip.util.EstimationConstant;

@Repository
public class EstnImageUploadRepositoryImpl implements EstnImageUploadRepository,CallableStatementCreator{
	
	static final Logger logger = LoggerFactory.getLogger(EstnImageUploadRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	
	private EstnImageRequest estnImageRequest;

	
	public void setEstnImageRequest(EstnImageRequest estnImageRequest) {
		this.estnImageRequest = estnImageRequest;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String uploadEstnImage(EstnImageRequest estnImageRequest) {
		Map<String, Object> resultMap = null;
	
      String msg ="";

        try {

            SqlParameter estimationIdparam = new SqlParameter(Types.NUMERIC);
            SqlParameter estnworkorderIdparam = new SqlParameter(Types.INTEGER);
            SqlParameter estnWotIdparam = new SqlParameter(Types.INTEGER);
            SqlParameter photoTypeparam = new SqlParameter(Types.VARCHAR);
            SqlParameter photoNameparam = new SqlParameter(Types.VARCHAR);
            SqlParameter ssoIdparam = new SqlParameter(Types.VARCHAR);
            SqlParameter appCdparam = new SqlParameter(Types.VARCHAR);
            

            List paramList = new ArrayList();
            paramList.add(estimationIdparam);
            paramList.add(estnworkorderIdparam);
            paramList.add(estnWotIdparam);
            paramList.add(photoTypeparam);
            paramList.add(photoNameparam);
            paramList.add(ssoIdparam);
            paramList.add(appCdparam);
            
            EstnImageUploadRepositoryImpl estnUploadImageRepositoryImpl = new EstnImageUploadRepositoryImpl();
            estnUploadImageRepositoryImpl.estnImageRequest = estnImageRequest;
            estnUploadImageRepositoryImpl.procedureCall = "{call " + EstimationConstant.PROC_UPLOAD_ESTN_IMAGE + "(?,?,?,?,?,?,?)}";
                  
           resultMap = jdbcTemplate.call((CallableStatementCreator) estnUploadImageRepositoryImpl, paramList);
           
           
           if (null != resultMap) {
				for (Map.Entry<String, Object> entry : resultMap.entrySet())
				{
					if ("#result-set-1".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> imageMap = lst.get(i);
						msg = (String) imageMap.get("errorCd");							
						}
					}
				}
           }
              
	}catch (Exception e) {
		logger.error("An error occurred while uploading the Estimation image: " + e);
	}

	return msg;
	}
	
	
	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setBigDecimal(1, new BigDecimal(estnImageRequest.getEstimationId()));
		DatatypeCommonUtility.checkNull(2, callableStatement,Integer.parseInt(estnImageRequest.getEstnworkorderId()));
		DatatypeCommonUtility.checkNull(3, callableStatement,Integer.parseInt(estnImageRequest.getEstnWotId()));
		callableStatement.setString(4, estnImageRequest.getPhotoType());
		callableStatement.setString(5,  estnImageRequest.getPhotoName());
		callableStatement.setString(6,  estnImageRequest.getSsoId());
		callableStatement.setString(7,  estnImageRequest.getAppCd());
		connection.setAutoCommit(true);
		return callableStatement;

}
}
