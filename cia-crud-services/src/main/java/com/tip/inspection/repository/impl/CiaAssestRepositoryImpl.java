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
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.tip.inspection.main.DatatypeCommonUtility;
import com.tip.inspection.main.InspectionCrudConstants;
import com.tip.inspection.model.AddCiaAssestRequest;
import com.tip.inspection.repository.CiaAssestRepository;

@Repository
public class CiaAssestRepositoryImpl implements CiaAssestRepository, CallableStatementCreator  {
	
	static final Logger logger = LoggerFactory.getLogger(CiaAssestRepositoryImpl.class);
	
	private String procedureCall;
	
	private AddCiaAssestRequest addCiaAssestRequest;

	public void setAddCiaAssestRequest(AddCiaAssestRequest addCiaAssestRequest) {
		this.addCiaAssestRequest = addCiaAssestRequest;
	}

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> saveCiaAssest(AddCiaAssestRequest addCiaAssestRequest) {
		
		//UserProfileStatusObject userProfileStatusObject = new UserProfileStatusObject();
		
		Map<String, Object> resultMap = null;
		try {
			SqlParameter equipNrSqlParam = new SqlParameter(Types.VARCHAR);
			SqlParameter branchNrSqlParam = new SqlParameter(Types.INTEGER);
			SqlParameter companyNrSqlParam = new SqlParameter(Types.INTEGER);
			SqlParameter lCountryCdSqlParam = new SqlParameter(Types.VARCHAR);
			SqlParameter licenceNrSqlParam = new SqlParameter(Types.VARCHAR);
			SqlParameter custEquipRefNrSqlParam = new SqlParameter(Types.VARCHAR);
			SqlParameter serialNrSqlParam = new SqlParameter(Types.VARCHAR);
			SqlParameter manufSqlParam = new SqlParameter(Types.VARCHAR);
			SqlParameter subManufacturerSqlParam = new SqlParameter(Types.VARCHAR);
			SqlParameter equipTypeSqlParam = new SqlParameter(Types.VARCHAR);
			SqlParameter noOfAxlesSqlParam = new SqlParameter(Types.INTEGER);
			SqlParameter customerNrSqlParam = new SqlParameter(Types.INTEGER);
			SqlParameter singleTyreSqlParam = new SqlParameter(Types.VARCHAR);
			SqlParameter twinTyreSqlParam = new SqlParameter(Types.VARCHAR);
			SqlParameter catGrpCodeSqlParam = new SqlParameter(Types.VARCHAR);
			SqlParameter equipmentStatusSqlParam = new SqlParameter(Types.INTEGER);
			SqlParameter userEmailSqlParam = new SqlParameter(Types.VARCHAR);
			
			List paramList = new ArrayList();
			paramList.add(equipNrSqlParam);
			paramList.add(branchNrSqlParam);
			paramList.add(companyNrSqlParam);
			paramList.add(lCountryCdSqlParam);
			paramList.add(licenceNrSqlParam);
			paramList.add(custEquipRefNrSqlParam);
			paramList.add(serialNrSqlParam);
			paramList.add(manufSqlParam);
			paramList.add(subManufacturerSqlParam);
			paramList.add(equipTypeSqlParam);
			paramList.add(noOfAxlesSqlParam);
			paramList.add(customerNrSqlParam);
			paramList.add(singleTyreSqlParam);
			paramList.add(twinTyreSqlParam);
			paramList.add(catGrpCodeSqlParam);
			paramList.add(equipmentStatusSqlParam);
			paramList.add(userEmailSqlParam);

			CiaAssestRepositoryImpl ciaAssestRepositoryImpl = new CiaAssestRepositoryImpl();
			ciaAssestRepositoryImpl.addCiaAssestRequest = addCiaAssestRequest;
			ciaAssestRepositoryImpl.procedureCall = "{call "
					+ InspectionCrudConstants.PROC_SAVE_CIA_ASSET+ " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			resultMap = jdbcTemplate.call(ciaAssestRepositoryImpl, paramList);

			
		} catch (Exception e) {
			logger.error("An error occurred while saving Cia assest details: " + e);
		}
		return resultMap;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);

		callableStatement.setString(1, addCiaAssestRequest.getEquipmentNr());
		DatatypeCommonUtility.checkNull(2, callableStatement, addCiaAssestRequest.getBranchNr());
		DatatypeCommonUtility.checkNull(3, callableStatement, addCiaAssestRequest.getCompanyNr());
		callableStatement.setString(4, addCiaAssestRequest.getLicenceCountryCd());
		callableStatement.setString(5, addCiaAssestRequest.getLicenceNr());
		callableStatement.setString(6, addCiaAssestRequest.getCustEquipRefNr());
		callableStatement.setString(7, addCiaAssestRequest.getSerialNr());
		callableStatement.setString(8, addCiaAssestRequest.getEquipManufacturer());
		callableStatement.setString(9, addCiaAssestRequest.getSubManufacturer());
		callableStatement.setString(10, addCiaAssestRequest.getEquipType());
		DatatypeCommonUtility.checkNull(11, callableStatement, addCiaAssestRequest.getNoOfAxles());
		DatatypeCommonUtility.checkNull(12, callableStatement, addCiaAssestRequest.getCustomerNr());
		callableStatement.setString(13, addCiaAssestRequest.getSingleTyre());
		callableStatement.setString(14, addCiaAssestRequest.getTwinTyre());
		callableStatement.setString(15, addCiaAssestRequest.getCatGrpCode());
		DatatypeCommonUtility.checkNull(16, callableStatement, addCiaAssestRequest.getEquipmentStatus());
		callableStatement.setString(17, addCiaAssestRequest.getUserEmail());
		connection.setAutoCommit(true);
		return callableStatement;
	}

	
}

	