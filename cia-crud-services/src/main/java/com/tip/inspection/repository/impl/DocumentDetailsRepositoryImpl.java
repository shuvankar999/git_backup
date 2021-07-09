package com.tip.inspection.repository.impl;

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
import com.tip.inspection.model.CiaInspectionDocuware;
import com.tip.inspection.repository.DocumentDetailsRepository;

@PropertySource("ErrorCode.properties")
@Repository
public class DocumentDetailsRepositoryImpl implements DocumentDetailsRepository, CallableStatementCreator  {
	
	static final Logger logger = LoggerFactory.getLogger(DocumentDetailsRepositoryImpl.class);
	
	private String procedureCall;
		
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	CiaInspectionDocuware ciaInspectionDocuware;

	public void setCiaInspectionDocuware(CiaInspectionDocuware ciaInspectionDocuware) {
		this.ciaInspectionDocuware = ciaInspectionDocuware;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> saveDetails(CiaInspectionDocuware docInput) {
		Map<String, Object> resultMap = null;
		try {
			SqlParameter inspIdParam = new SqlParameter(Types.DECIMAL);
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
			SqlParameter docNameParam = new SqlParameter(Types.VARCHAR);
			SqlParameter docSatusParam = new SqlParameter(Types.INTEGER);
			SqlParameter docwareIdParam = new SqlParameter(Types.INTEGER);

			List paramList = new ArrayList();
			paramList.add(inspIdParam);
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
			paramList.add(docNameParam);
			paramList.add(docSatusParam);
			paramList.add(docwareIdParam);
			
			DocumentDetailsRepositoryImpl documentDetailsRepositoryImpl = new DocumentDetailsRepositoryImpl();
			documentDetailsRepositoryImpl.ciaInspectionDocuware = docInput;
			documentDetailsRepositoryImpl.procedureCall = "{call "
					+ InspectionCrudConstants.PROC_FETCH_SAVE_DOCUMENT+ " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			resultMap = jdbcTemplate.call(documentDetailsRepositoryImpl, paramList);

		} catch (Exception e) {
			logger.error("An error occurred while saving document details: " + e);
		}
		return resultMap;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setBigDecimal(1, ciaInspectionDocuware.getInspId());
		callableStatement.setString(2, ciaInspectionDocuware.getInspType());
		callableStatement.setBigDecimal(3, ciaInspectionDocuware.getCustomerNr());
		DatatypeCommonUtility.checkNull(4, callableStatement, ciaInspectionDocuware.getUnitNr());
		callableStatement.setString(5, ciaInspectionDocuware.getInspDate());
		callableStatement.setString(6, ciaInspectionDocuware.getInspLocation());
		callableStatement.setBigDecimal(7, ciaInspectionDocuware.getInspLocLongitude());
		callableStatement.setBigDecimal(8, ciaInspectionDocuware.getInspLocLatitude());
		callableStatement.setBigDecimal(9, ciaInspectionDocuware.getInspLocAltitude());
		callableStatement.setString(10, ciaInspectionDocuware.getInspDriverCompany());
		callableStatement.setString(11, ciaInspectionDocuware.getInspRemarks());
		callableStatement.setString(12, ciaInspectionDocuware.getUserEmail());
		callableStatement.setString(13, ciaInspectionDocuware.getTimeZoneOffset());
		callableStatement.setString(14, ciaInspectionDocuware.getDocname());
		DatatypeCommonUtility.checkNull(15, callableStatement, (ciaInspectionDocuware.getDocstatus()!= null ? Integer.parseInt(ciaInspectionDocuware.getDocstatus()) : null));
		DatatypeCommonUtility.checkNull(16, callableStatement, ciaInspectionDocuware.getDocuwarreDocId());
		connection.setAutoCommit(true);
		return callableStatement;
	}


}

	