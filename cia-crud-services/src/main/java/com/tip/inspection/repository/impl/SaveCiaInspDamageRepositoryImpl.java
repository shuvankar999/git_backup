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
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.tip.inspection.main.DatatypeCommonUtility;
import com.tip.inspection.main.InspectionCrudConstants;
import com.tip.inspection.model.InspectionDamageDataObject;
import com.tip.inspection.model.InspectionDamageResponseObject;
import com.tip.inspection.repository.SaveCiaInspDamageRepository;

@PropertySource("ErrorCode.properties")
@Repository
public class SaveCiaInspDamageRepositoryImpl implements SaveCiaInspDamageRepository, CallableStatementCreator  {
	
	static final Logger logger = LoggerFactory.getLogger(SaveCiaInspDamageRepositoryImpl.class);
	
	private String procedureCall;
	
	private InspectionDamageDataObject inspectionDamageDataObject;
	
	@Autowired
	private Environment env; 
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	public void setInspectionDamageDataObject(InspectionDamageDataObject inspectionDamageDataObject) {
		this.inspectionDamageDataObject = inspectionDamageDataObject;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public InspectionDamageResponseObject saveCiaInsp(InspectionDamageDataObject inspectionDamageDataObject) {

		
		InspectionDamageResponseObject  inspectionDamageResponseObject = new InspectionDamageResponseObject();
		
		Map<String, Object> resultMap = null;
		try {
			SqlParameter inspIdParam = new SqlParameter(Types.DECIMAL);
			SqlParameter unitNrParam = new SqlParameter(Types.INTEGER);
			SqlParameter lineNrParam = new SqlParameter(Types.INTEGER);
			SqlParameter inspCdParam = new SqlParameter(Types.VARCHAR);
			SqlParameter dnaCdParam = new SqlParameter(Types.VARCHAR);
			SqlParameter descParam = new SqlParameter(Types.VARCHAR);
			SqlParameter deviceSerialNrParam = new SqlParameter(Types.VARCHAR);
			SqlParameter damageTypeParam = new SqlParameter(Types.VARCHAR);

			List paramList = new ArrayList();
			paramList.add(inspIdParam);
			paramList.add(unitNrParam);
			paramList.add(lineNrParam);
			paramList.add(inspCdParam);
			paramList.add(dnaCdParam);
			paramList.add(descParam);
			paramList.add(deviceSerialNrParam);
			paramList.add(damageTypeParam);
			
			SaveCiaInspDamageRepositoryImpl saveCiaInspDamageRepositoryImpl = new SaveCiaInspDamageRepositoryImpl();
			saveCiaInspDamageRepositoryImpl.inspectionDamageDataObject = inspectionDamageDataObject;
			saveCiaInspDamageRepositoryImpl.procedureCall = "{call "
					+ InspectionCrudConstants.PROC_SAVE_INSP_DAMAGE+ " (?,?,?,?,?,?,?,?)}";
			resultMap = jdbcTemplate.call(saveCiaInspDamageRepositoryImpl, paramList);

			if (null != resultMap) {
				for (Map.Entry<String, Object> entry : resultMap.entrySet())
				{
					if ("#result-set-1".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> responseMap = lst.get(i);
							inspectionDamageResponseObject.setInspId((BigDecimal) responseMap.get("Insp_Id"));
							inspectionDamageResponseObject.setLineNr((Integer)responseMap.get("Line_Nr"));
							inspectionDamageResponseObject.setErrorCd((String)responseMap.get("Error_Cd"));
							String errorCode = responseMap.get("Lang_Cd").toString().concat(".").concat(inspectionDamageResponseObject.getErrorCd());
							if(env.getProperty(errorCode) != null){
								inspectionDamageResponseObject.setErrorDesc(env.getProperty(errorCode));
							}else{
								inspectionDamageResponseObject.setErrorDesc((String) responseMap.get("Error_Cd"));	
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("An error occurred while saving Inspection damage details: " + e);
		}
		return inspectionDamageResponseObject;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setBigDecimal(1, inspectionDamageDataObject.getInspId());
		DatatypeCommonUtility.checkNull(2, callableStatement, inspectionDamageDataObject.getUnitNr());
		DatatypeCommonUtility.checkNull(3, callableStatement, inspectionDamageDataObject.getLineNr());
		callableStatement.setString(4, inspectionDamageDataObject.getInspCd());
		callableStatement.setString(5, inspectionDamageDataObject.getDnaCd());
		callableStatement.setString(6, inspectionDamageDataObject.getDescription());
		callableStatement.setString(7, inspectionDamageDataObject.getDeviceSerialNr());
		callableStatement.setString(8, inspectionDamageDataObject.getDamageType());
		connection.setAutoCommit(true);
		return callableStatement;
	}

}

	