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
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.tip.inspection.main.DatatypeCommonUtility;
import com.tip.inspection.main.InspectionCrudConstants;
import com.tip.inspection.model.InspectionHeaderDataObject;
import com.tip.inspection.model.InspectionHeaderResponseObject;
import com.tip.inspection.repository.SaveCiaInspHeaderRepository;
@PropertySource("ErrorCode.properties")
@Repository
public class SaveCiaInspHeaderRepositoryImpl implements SaveCiaInspHeaderRepository, CallableStatementCreator  {
	
	static final Logger logger = LoggerFactory.getLogger(SaveCiaInspHeaderRepositoryImpl.class);
	
	private String procedureCall;
	
	private InspectionHeaderDataObject inspectionHeaderDataObject;
	
	 @Autowired
	 private Environment env;  
	 
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void setInspectionHeaderDataObject(InspectionHeaderDataObject inspectionHeaderDataObject) {
		this.inspectionHeaderDataObject = inspectionHeaderDataObject;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public InspectionHeaderResponseObject saveCiaInsp(InspectionHeaderDataObject inspectionHeaderDataList) {
		
		InspectionHeaderResponseObject  inspectionHeaderResponseObject = new InspectionHeaderResponseObject();
		
		Map<String, Object> resultMap = null;
		try {
			SqlParameter deviceInspIdParam = new SqlParameter(Types.VARCHAR);
			SqlParameter inspTypeParam = new SqlParameter(Types.VARCHAR);
			SqlParameter customerNrParam = new SqlParameter(Types.DECIMAL);
			SqlParameter unitNrParam = new SqlParameter(Types.INTEGER);
			SqlParameter inspDateParam = new SqlParameter(Types.VARCHAR);
			SqlParameter locationParam = new SqlParameter(Types.VARCHAR);
			SqlParameter inspLocLongitudeParam = new SqlParameter(Types.DECIMAL);
			SqlParameter inspLocLatitudeParam = new SqlParameter(Types.DECIMAL);
			SqlParameter inspAltitudueParam = new SqlParameter(Types.DECIMAL);
			SqlParameter inspDriverCompanyParam = new SqlParameter(Types.VARCHAR);
			SqlParameter inspRemarksParam = new SqlParameter(Types.VARCHAR);
			SqlParameter userEmailParam = new SqlParameter(Types.VARCHAR);
			SqlParameter timeZoneOffsetParam = new SqlParameter(Types.VARCHAR);
			SqlOutParameter inspIdParam = new SqlOutParameter("Insp_Id",Types.INTEGER);
			SqlOutParameter rejCdParam = new SqlOutParameter("RejCd",Types.INTEGER);
			SqlOutParameter rejMesgParam = new SqlOutParameter("RejMsg",Types.VARCHAR);

			List paramList = new ArrayList();
			paramList.add(deviceInspIdParam);
			paramList.add(inspTypeParam);
			paramList.add(customerNrParam);
			paramList.add(unitNrParam);
			paramList.add(inspDateParam);
			paramList.add(locationParam);
			paramList.add(inspLocLongitudeParam);
			paramList.add(inspLocLatitudeParam);
			paramList.add(inspAltitudueParam);
			paramList.add(inspDriverCompanyParam);
			paramList.add(inspRemarksParam);
			paramList.add(userEmailParam);
			paramList.add(timeZoneOffsetParam);
			paramList.add(inspIdParam);
			paramList.add(rejCdParam);
			paramList.add(rejMesgParam);

			SaveCiaInspHeaderRepositoryImpl saveCiaInspHeaderRepositoryImpl = new SaveCiaInspHeaderRepositoryImpl();
			saveCiaInspHeaderRepositoryImpl.inspectionHeaderDataObject = inspectionHeaderDataList;
			saveCiaInspHeaderRepositoryImpl.procedureCall = "{call "
					+ InspectionCrudConstants.PROC_SAVE_INSP_HEADER+ " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			resultMap = jdbcTemplate.call(saveCiaInspHeaderRepositoryImpl, paramList);

			if (null != resultMap) {
				for (Map.Entry<String, Object> entry : resultMap.entrySet())
				{
					if ("#result-set-1".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> responseMap = lst.get(i);
							inspectionHeaderResponseObject.setInspId((BigDecimal) responseMap.get("Insp_Id"));
							inspectionHeaderResponseObject.setDeviceInspId((String)responseMap.get("Device_Insp_Id"));
							inspectionHeaderResponseObject.setErrorCd((String)responseMap.get("Error_Cd"));
							String errorCode = responseMap.get("Lang_Cd").toString().concat(".").concat(inspectionHeaderResponseObject.getErrorCd());
							if(env.getProperty(errorCode) != null){
								inspectionHeaderResponseObject.setErrorDesc(env.getProperty(errorCode));
							}else{
								inspectionHeaderResponseObject.setErrorDesc((String) responseMap.get("Error_Cd"));	
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("An error occurred while saving Inspection header details: " + e);
		}
		return inspectionHeaderResponseObject;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setString(1, inspectionHeaderDataObject.getDeviceInspId());
		callableStatement.setString(2, inspectionHeaderDataObject.getInspType());
		callableStatement.setBigDecimal(3, inspectionHeaderDataObject.getCustomerNr());
		DatatypeCommonUtility.checkNull(4, callableStatement, inspectionHeaderDataObject.getUnitNr());
		callableStatement.setString(5, inspectionHeaderDataObject.getInspDate());
		callableStatement.setString(6, inspectionHeaderDataObject.getInspLocation());
		callableStatement.setBigDecimal(7, inspectionHeaderDataObject.getInspLocLongitude());
		callableStatement.setBigDecimal(8, inspectionHeaderDataObject.getInspLocLatitude());
		callableStatement.setBigDecimal(9, inspectionHeaderDataObject.getInspLocAltitude());
		callableStatement.setString(10, inspectionHeaderDataObject.getInspDriverCompany());
		callableStatement.setString(11, inspectionHeaderDataObject.getInspRemarks());
		callableStatement.setString(12, inspectionHeaderDataObject.getUserEmail());
		callableStatement.setString(13, inspectionHeaderDataObject.getTimeZoneOffset());
		callableStatement.registerOutParameter(14, Types.INTEGER);
		callableStatement.registerOutParameter(15, Types.INTEGER);
		callableStatement.registerOutParameter(16, Types.VARCHAR);
		connection.setAutoCommit(true);
		return callableStatement;
	}


}

	