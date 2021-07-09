package com.document.upload.documentservice.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.document.upload.documentservice.data.CommonUtil;
import com.document.upload.documentservice.data.DocumentConstants;
import com.document.upload.documentservice.data.SupplierDocUploadInput;

@Repository
public class updateDocumentDaoImpl implements UpdateDocData {
    /** Logger for this class and subclasses */
    private static final Log logger = LogFactory.getLog(DaoServiceImpl.class);
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
    
    @Autowired
	private JdbcTemplate jdbcTemplate;
    
    private String procedureCall;

	private SupplierDocUploadInput feeRequestObject;
	
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String updateDocumentData(SupplierDocUploadInput input) {
    	System.out.println("insert documentd data ");
	//	FeeCardsStatus feeCardsStatus = new FeeCardsStatus();
    	String errorCd=null;
		Map<String, Object> resultMap = null;

			SqlParameter supplierId = new SqlParameter(Types.VARCHAR);
			SqlParameter docType = new SqlParameter(Types.VARCHAR);
			SqlParameter insuranceTypeId = new SqlParameter(Types.VARCHAR);
			SqlParameter insuranceTypeDesc = new SqlParameter(Types.VARCHAR);
			SqlParameter insuranceAmount = new SqlParameter(Types.VARCHAR);
			SqlParameter expiryDate = new SqlParameter(Types.DATE);
			SqlParameter contractType = new SqlParameter(Types.VARCHAR);
			SqlParameter selfApprovalLimit = new SqlParameter(Types.VARCHAR);
			SqlParameter contractStartDate = new SqlParameter(Types.DATE);
			SqlParameter contractEndDate = new SqlParameter(Types.DATE);
			SqlParameter docName = new SqlParameter(Types.VARCHAR);
			SqlParameter docStatus = new SqlParameter(Types.VARCHAR);
			SqlParameter docId = new SqlParameter(Types.VARCHAR);
			SqlParameter userId = new SqlParameter(Types.VARCHAR);
		
			List paramList = new ArrayList();
			
			paramList.add(supplierId);
			paramList.add(docType);
			paramList.add(insuranceTypeId);
			paramList.add(insuranceTypeDesc);
			paramList.add(insuranceAmount);
			paramList.add(expiryDate);
			paramList.add(contractType);
			paramList.add(selfApprovalLimit);
			paramList.add(contractStartDate);
			paramList.add(contractEndDate);
			paramList.add(docName);
			paramList.add(docStatus);
			paramList.add(docId);
			paramList.add(userId);
			
			System.out.println("DocumentConstants.PROC_INSERT_DOC_RECORD :"+DocumentConstants.PROC_INSERT_DOC_RECORD);
			
			updateDocumentDaoImpl daoServiceImpl = new updateDocumentDaoImpl();
			daoServiceImpl.feeRequestObject = input;
			daoServiceImpl.procedureCall = "{call " + DocumentConstants.PROC_INSERT_DOC_RECORD + " (?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			resultMap = jdbcTemplate.call((CallableStatementCreator)daoServiceImpl, paramList);

			if (null != resultMap) {
				for (Map.Entry<String, Object> entry : resultMap.entrySet())
				{
					if ("#result-set-1".equalsIgnoreCase(entry.getKey())) {
						System.out.println("inside error code");
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							System.out.println("inside for loop ");
							Map<String, Object> statusMap = lst.get(i);
							errorCd=(String) statusMap.get("Error_Cd");
							System.out.println("Error Code :"+errorCd);
						}
					}
				}
			}
		
		return errorCd;
	}

   
	public CallableStatement createCallableStatement(Connection connection) throws SQLException, ParseException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setString(1, CommonUtil.checkNullObject(feeRequestObject.getSupplierId()));
		callableStatement.setString(2, CommonUtil.checkNullObject(feeRequestObject.getDocType()));
		callableStatement.setString(3, CommonUtil.checkNullObject(feeRequestObject.getInsuranceTypeId()));
		callableStatement.setString(4, CommonUtil.checkNullObject(feeRequestObject.getInsuranceTypedesc()));
		callableStatement.setString(5, CommonUtil.checkNullObject(feeRequestObject.getInsuranceAmount()));
		callableStatement.setDate(6,  new java.sql.Date(dateFormat.parse(feeRequestObject.getExpiryDate()).getTime()));
		callableStatement.setString(7, CommonUtil.checkNullObject(feeRequestObject.getContractType()));
		callableStatement.setString(8, CommonUtil.checkNullObject(feeRequestObject.getSelfApprovalLimit()));
		callableStatement.setDate(9, new java.sql.Date(dateFormat.parse(feeRequestObject.getContractStartDate()).getTime())); 
		callableStatement.setDate(10, new java.sql.Date(dateFormat.parse(feeRequestObject.getContractExpireDate()).getTime()));
		callableStatement.setString(11, CommonUtil.checkNullObject((feeRequestObject.getDocName())));
		callableStatement.setString(12, CommonUtil.checkNullObject(feeRequestObject.getDocStatus()));
		callableStatement.setString(13, CommonUtil.checkNullObject(feeRequestObject.getDocId()));
		callableStatement.setString(14, CommonUtil.checkNullObject(feeRequestObject.getUserId()));	
	
		connection.setAutoCommit(true);
		return callableStatement;
	}
	
    /** ********************* Audit START ******************************* */

   
}
