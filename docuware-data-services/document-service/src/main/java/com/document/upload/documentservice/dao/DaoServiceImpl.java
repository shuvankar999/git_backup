/**
 * This program is proprietary material of a confidential nature of GE Equipment
 * Services. No copies of this program or parts of this program may be made
 * without written consent of GE Equipment Services.
 * 
 * @version 		: 1.0
 * @author 		: Tata Consultancy Services Ltd. 
 * Class 		: DaoServiceImpl
 * Description 		: Implements DaoService.Dao class for Login and Audit utility
 * Creation Date 	: 25-July-2008
 * History 		:
 * 
 * Date Modified by Description
 * ------------------------------------------------------------------------------------------------------
 * dd-mmm-yyyy <Name> [Multiline / Precise description]
 * 
 */
package com.document.upload.documentservice.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.document.upload.documentservice.data.CommonUtil;
import com.document.upload.documentservice.data.DatatypeCommonUtility;
import com.document.upload.documentservice.data.DocumentConstants;
import com.document.upload.documentservice.data.SupplierDocUploadInput;



@Repository
public class DaoServiceImpl implements DaoService,CallableStatementCreator {

    /** Logger for this class and subclasses */
    private static final Log logger = LogFactory.getLog(DaoServiceImpl.class);
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    @Autowired
	private JdbcTemplate jdbcTemplate;
    
    private String procedureCall;

	private SupplierDocUploadInput feeRequestObject;
	
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String insertDocumentData(SupplierDocUploadInput input) {
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
			try {
			DaoServiceImpl daoServiceImpl = new DaoServiceImpl();
			daoServiceImpl.feeRequestObject = input;
			daoServiceImpl.procedureCall = "{call " + DocumentConstants.PROC_INSERT_DOC_RECORD + " (?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			resultMap = jdbcTemplate.call(daoServiceImpl, paramList);
			}catch(Exception e) {
				e.printStackTrace();
			}
			if (null != resultMap) {
				System.out.println("inside result map");
				for (Map.Entry<String, Object> entry : resultMap.entrySet())
				{
					System.out.println("inside iterating");
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

   
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setInt(1, Integer.parseInt(feeRequestObject.getSupplierId()));
		callableStatement.setString(2, feeRequestObject.getDocType());
		callableStatement.setInt(3, Integer.parseInt(feeRequestObject.getInsuranceTypeId()));
		callableStatement.setString(4, feeRequestObject.getInsuranceTypedesc());
		if(!CommonUtil.checkNull(feeRequestObject.getInsuranceAmount())) {
			callableStatement.setDouble(5, 0);
		}else {
		callableStatement.setDouble(5, Double.parseDouble(feeRequestObject.getInsuranceAmount()));
		}
		//callableStatement.setDouble(5, Double.parseDouble(feeRequestObject.getInsuranceAmount()));
		if(CommonUtil.checkNullObject(feeRequestObject.getExpiryDate()).equalsIgnoreCase("")) {
			callableStatement.setDate(6,  null);
		}else {
		try {
			System.out.println("date :"+feeRequestObject.getExpiryDate());
		//	System.out.println("date string:"+CommonUtil.convertStringDateTimeToStringMMDDYYYY(feeRequestObject.getExpiryDate()));
		//	System.out.println("date parse:"+dateFormat.parse(CommonUtil.convertStringDateTimeToStringMMDDYYYY(feeRequestObject.getExpiryDate())).getTime());
		//	System.out.println("date parse New:"+dateFormat.parse(CommonUtil.convertStringDateTimeToStringMMDDYYYY(feeRequestObject.getExpiryDate())));
			System.out.println("new Dates :"+new java.sql.Date(dateFormat.parse(feeRequestObject.getExpiryDate()).getTime()));
			callableStatement.setDate(6,  new java.sql.Date(dateFormat.parse(feeRequestObject.getExpiryDate()).getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		callableStatement.setString(7, feeRequestObject.getContractType());
		if(!CommonUtil.checkNull(feeRequestObject.getSelfApprovalLimit())) {
			callableStatement.setDouble(8, 0);
		}else {
		callableStatement.setDouble(8, Double.parseDouble(feeRequestObject.getSelfApprovalLimit()));
		}
		if(CommonUtil.checkNullObject(feeRequestObject.getContractStartDate()).equalsIgnoreCase("")) {
			callableStatement.setDate(9,  null);
		}else {
		try {
			callableStatement.setDate(9, new java.sql.Date(dateFormat.parse((feeRequestObject.getContractStartDate())).getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		}
		if(CommonUtil.checkNullObject(feeRequestObject.getContractExpireDate()).equalsIgnoreCase("")) {
			callableStatement.setDate(10,  null);
		}else {
		try {
			callableStatement.setDate(10, new java.sql.Date(dateFormat.parse((feeRequestObject.getContractExpireDate())).getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		callableStatement.setString(11, feeRequestObject.getDocName());
		callableStatement.setInt(12, Integer.parseInt(feeRequestObject.getDocStatus()));
		callableStatement.setInt(13, Integer.parseInt(feeRequestObject.getDocId()));
		callableStatement.setString(14, feeRequestObject.getUserId());	
	
		connection.setAutoCommit(true);
		return callableStatement;
	}
	
    /** ********************* Audit START ******************************* */

   
	
   
}
