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
import com.tip.inspection.model.InspectionItemDataObject;
import com.tip.inspection.model.InspectionItemResponseObject;
import com.tip.inspection.repository.SaveCiaInspItemRepository;
@PropertySource("ErrorCode.properties")
@Repository
public class SaveCiaInspItemRepositoryImpl implements SaveCiaInspItemRepository, CallableStatementCreator  {
	
	static final Logger logger = LoggerFactory.getLogger(SaveCiaInspItemRepositoryImpl.class);
	
	private String procedureCall;
	
	private InspectionItemDataObject inspectionItemDataObject;
	
	@Autowired
	private Environment env; 
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public void setInspectionItemDataObject(InspectionItemDataObject inspectionItemDataObject) {
		this.inspectionItemDataObject = inspectionItemDataObject;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public InspectionItemResponseObject saveCiaInsp(InspectionItemDataObject inspectionItemDataObject) {
		
		InspectionItemResponseObject  inspectionItemResponseObject = new InspectionItemResponseObject();
		
		Map<String, Object> resultMap = null;
		try {
			SqlParameter inspIdParam = new SqlParameter(Types.VARCHAR);
			SqlParameter inspCdParam = new SqlParameter(Types.VARCHAR);
			SqlParameter inspIndParam = new SqlParameter(Types.VARCHAR);
			SqlParameter inspDescParam = new SqlParameter(Types.VARCHAR);
			SqlParameter rejCdParam = new SqlParameter(Types.INTEGER);
			SqlParameter rejDescParam = new SqlParameter(Types.VARCHAR);

			List paramList = new ArrayList();
			paramList.add(inspIdParam);
			paramList.add(inspCdParam);
			paramList.add(inspIndParam);
			paramList.add(inspDescParam);
			paramList.add(rejCdParam);
			paramList.add(rejDescParam);
			
			SaveCiaInspItemRepositoryImpl saveCiaInspItemRepositoryImpl = new SaveCiaInspItemRepositoryImpl();
			saveCiaInspItemRepositoryImpl.inspectionItemDataObject = inspectionItemDataObject;
			saveCiaInspItemRepositoryImpl.procedureCall = "{call "
					+ InspectionCrudConstants.PROC_SAVE_INSP_ITEMS+ " (?,?,?,?,?,?)}";
			resultMap = jdbcTemplate.call(saveCiaInspItemRepositoryImpl, paramList);

			if (null != resultMap) {
				for (Map.Entry<String, Object> entry : resultMap.entrySet())
				{
					if ("#result-set-1".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> responseMap = lst.get(i);
							inspectionItemResponseObject.setInspId((BigDecimal) responseMap.get("Insp_Id"));
							inspectionItemResponseObject.setInspCd((String)responseMap.get("Insp_Cd"));
							inspectionItemResponseObject.setErrorCd((String)responseMap.get("Error_Cd"));
							String errorCode = responseMap.get("Lang_Cd").toString().concat(".").concat(inspectionItemResponseObject.getErrorCd());
							if(env.getProperty(errorCode) != null){
								inspectionItemResponseObject.setErrorDesc(env.getProperty(errorCode));
							}else{
								inspectionItemResponseObject.setErrorDesc((String) responseMap.get("Error_Cd"));	
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("An error occurred while saving Inspection item details: " + e);
		}
		return inspectionItemResponseObject;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setBigDecimal (1, inspectionItemDataObject.getInspId());
		callableStatement.setString(2, inspectionItemDataObject.getInspCd());
		callableStatement.setString(3, inspectionItemDataObject.getInspInd());
		callableStatement.setString(4, inspectionItemDataObject.getInspDesc());
		DatatypeCommonUtility.checkNull(5, callableStatement, inspectionItemDataObject.getRejCd());
		callableStatement.setString(6, inspectionItemDataObject.getRejDesc());
		connection.setAutoCommit(false);
		return callableStatement;
	}



}

	