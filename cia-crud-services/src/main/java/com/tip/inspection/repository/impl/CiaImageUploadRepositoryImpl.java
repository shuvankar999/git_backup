package com.tip.inspection.repository.impl;

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

import com.tip.inspection.main.DatatypeCommonUtility;
import com.tip.inspection.main.InspectionCrudConstants;
import com.tip.inspection.model.InspectionUploadImageRequest;
import com.tip.inspection.repository.CiaImageUploadRepository;

@Repository
public class CiaImageUploadRepositoryImpl implements CiaImageUploadRepository,CallableStatementCreator{
	
	static final Logger logger = LoggerFactory.getLogger(CiaImageUploadRepositoryImpl.class);

	private String procedureCall;
	
	private InspectionUploadImageRequest inspectionUploadImageRequest;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void setInspectionUploadImageRequest(InspectionUploadImageRequest inspectionUploadImageRequest) {
		this.inspectionUploadImageRequest = inspectionUploadImageRequest;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String uploadCiaImage(InspectionUploadImageRequest inspectionUploadImageRequest) {
		Map<String, Object> resultMap = null;
	
      String msg ="";

        try {

            SqlParameter appCdparam = new SqlParameter(Types.VARCHAR);
            SqlParameter inspIdparam = new SqlParameter(Types.DECIMAL);
            SqlParameter inspCdparam = new SqlParameter(Types.VARCHAR);
            SqlParameter inspTypeparam = new SqlParameter(Types.VARCHAR);
            SqlParameter damagelineNrparam = new SqlParameter(Types.INTEGER);
            SqlParameter damageDescparam = new SqlParameter(Types.VARCHAR);
            SqlParameter imgTypeparam = new SqlParameter(Types.VARCHAR);
            SqlParameter imgNameparam = new SqlParameter(Types.VARCHAR);
            SqlParameter imgCommentparam = new SqlParameter(Types.VARCHAR);
            SqlParameter imgCountparam = new SqlParameter(Types.INTEGER);
            SqlParameter imgPathparam = new SqlParameter(Types.VARCHAR);
            SqlParameter deviceserialNrparam = new SqlParameter(Types.VARCHAR);
            

            List paramList = new ArrayList();
            paramList.add(appCdparam);
            paramList.add(inspIdparam);
            paramList.add(inspCdparam);
            paramList.add(inspTypeparam);
            paramList.add(damagelineNrparam);
            paramList.add(damageDescparam);
            paramList.add(imgTypeparam);
            paramList.add(imgNameparam);
            paramList.add(imgCommentparam);
            paramList.add(imgCountparam);
            paramList.add(imgPathparam);
            paramList.add(deviceserialNrparam);
            
            CiaImageUploadRepositoryImpl estnUploadImageRepositoryImpl = new CiaImageUploadRepositoryImpl();
            estnUploadImageRepositoryImpl.inspectionUploadImageRequest = inspectionUploadImageRequest;
            estnUploadImageRepositoryImpl.procedureCall = "{call " + InspectionCrudConstants.PROC_SAVE_CIA_INSP_IMAGE + "(?,?,?,?,?,?,?,?,?,?,?,?)}";
                  
           resultMap = jdbcTemplate.call((CallableStatementCreator) estnUploadImageRepositoryImpl, paramList);
           
           
           if (null != resultMap) {
				for (Map.Entry<String, Object> entry : resultMap.entrySet())
				{
					if ("#result-set-1".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> imageMap = lst.get(i);
						if( (Integer) imageMap.get("Output") == 1){
							msg ="Success";
						}else{
							msg ="Failure";
						}	
						
						}
					}
				}
           }
              
	}catch (Exception e) {
		logger.error("An error occurred while uploading the Inspection image: " + e);
	}

	return msg;
	}
	
	
	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setString(1, inspectionUploadImageRequest.getAppCd());
		callableStatement.setBigDecimal(2, new BigDecimal(inspectionUploadImageRequest.getInspId()));
		callableStatement.setString(3, inspectionUploadImageRequest.getInspCd());
		callableStatement.setString(4, inspectionUploadImageRequest.getInspType());
		DatatypeCommonUtility.checkNull(5, callableStatement,Integer.parseInt(inspectionUploadImageRequest.getDamageLineNr()));
		callableStatement.setString(6,  inspectionUploadImageRequest.getDamageDescription());
		callableStatement.setString(7,  inspectionUploadImageRequest.getImgType());
		callableStatement.setString(8,  inspectionUploadImageRequest.getImgName());
		callableStatement.setString(9,  inspectionUploadImageRequest.getImgComment());
		DatatypeCommonUtility.checkNull(10, callableStatement,Integer.parseInt(inspectionUploadImageRequest.getImgCount()));
		callableStatement.setString(11,  inspectionUploadImageRequest.getImgPath());
		callableStatement.setString(12,  inspectionUploadImageRequest.getDeviceSerialNr());
		connection.setAutoCommit(true);
		return callableStatement;

}

	
}
