package com.document.upload.documentservice.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
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

import com.document.upload.documentservice.data.DocuwareDetails;

@Repository
public class GetDocuwareDetailsImpl implements GetDocuwareDetails,CallableStatementCreator {

	    /** Logger for this class and subclasses */
	    private static final Log logger = LogFactory.getLog(DaoServiceImpl.class);
	  //  SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
	    
	    @Autowired
		private JdbcTemplate jdbcTemplate;
	    
	    private String procedureCall;

	private String appCd;
		
	    @SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public DocuwareDetails getDocuwareDetails(String appCd) {
	    	
	    	DocuwareDetails dd=new DocuwareDetails();
	    	System.out.println("insert documentd data ");
		//	FeeCardsStatus feeCardsStatus = new FeeCardsStatus();
	    	String errorCd=null;
			Map<String, Object> resultMap = null;

			SqlParameter appCdparam = new SqlParameter(Types.VARCHAR);
//				SqlParameter docType = new SqlParameter(Types.VARCHAR);
//				SqlParameter insuranceTypeId = new SqlParameter(Types.VARCHAR);
//				SqlParameter insuranceTypeDesc = new SqlParameter(Types.VARCHAR);
//				SqlParameter insuranceAmount = new SqlParameter(Types.VARCHAR);
//				SqlParameter expiryDate = new SqlParameter(Types.DATE);
//				SqlParameter contractType = new SqlParameter(Types.VARCHAR);
//				SqlParameter selfApprovalLimit = new SqlParameter(Types.VARCHAR);
//				SqlParameter contractStartDate = new SqlParameter(Types.DATE);
//				SqlParameter contractEndDate = new SqlParameter(Types.DATE);
//				SqlParameter docName = new SqlParameter(Types.VARCHAR);
//				SqlParameter docStatus = new SqlParameter(Types.VARCHAR);
//				SqlParameter docId = new SqlParameter(Types.VARCHAR);
//				SqlParameter userId = new SqlParameter(Types.VARCHAR);
			
				List paramList = new ArrayList();
				System.out.println("app code :"+appCd);
				paramList.add(appCdparam);
//				paramList.add(docType);
//				paramList.add(insuranceTypeId);
//				paramList.add(insuranceTypeDesc);
//				paramList.add(insuranceAmount);
//				paramList.add(expiryDate);
//				paramList.add(contractType);
//				paramList.add(selfApprovalLimit);
//				paramList.add(contractStartDate);
//				paramList.add(contractEndDate);
//				paramList.add(docName);
//				paramList.add(docStatus);
//				paramList.add(docId);
//				paramList.add(userId);
				
			//	System.out.println("DocumentConstants.PROC_INSERT_DOC_RECORD :"+DocumentConstants.PROC_INSERT_DOC_RECORD);
				try {
					GetDocuwareDetailsImpl daoServiceImpl = new GetDocuwareDetailsImpl();
				daoServiceImpl.appCd = appCd;
				daoServiceImpl.procedureCall = "{call OPSmaint..get_Docuware_Details(?)}";
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
								dd.setDocuwareIp((String)statusMap.get("Docu_IP"));
								dd.setDocUser((String)statusMap.get("Docu_User"));
								dd.setDocPwd((String)statusMap.get("Docu_PWD"));
								dd.setDocuwarePort((String)statusMap.get("Docu_Port"));
								dd.setDocOrg((String)statusMap.get("Docu_Org"));	
								dd.setDocuCabinate((String)statusMap.get("Docu_Cabinate"));	
								System.out.println("doc Ip :"+dd.getDocuwareIp()+""+dd.getDocPwd()+""+dd.getDocUser()+""+dd.getDocOrg()+""+dd.getDocuCabinate());
							//	errorCd=(String) statusMap.get("Error_Cd");
							//	System.out.println("Error Code :"+errorCd);
							}
						}
					}
				}
			
			return dd;
		}

	   
		public CallableStatement createCallableStatement(Connection connection) throws SQLException {
			connection.setAutoCommit(false);
			CallableStatement callableStatement = connection.prepareCall(procedureCall);
			callableStatement.setString(1, appCd);
			connection.setAutoCommit(true);
			return callableStatement;
		}
		
	    /** ********************* Audit START ******************************* */

	   
		
	   
	


}
