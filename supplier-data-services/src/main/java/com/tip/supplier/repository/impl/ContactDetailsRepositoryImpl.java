package com.tip.supplier.repository.impl;

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

import com.tip.supplier.main.DatatypeCommonUtility;
import com.tip.supplier.main.SupplierDataConstants;
import com.tip.supplier.model.ContactDetailsRequest;
import com.tip.supplier.model.ContactDetailsResponse;
import com.tip.supplier.model.ContactObject;
import com.tip.supplier.repository.ContactDetailsRepository;

@Repository
public class ContactDetailsRepositoryImpl implements ContactDetailsRepository, CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(ContactDetailsRepositoryImpl.class);

	@Autowired	
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;

	private ContactDetailsRequest contactDetailsRequest;
	
	public void setContactDetailsRequest(ContactDetailsRequest contactDetailsRequest) {
		this.contactDetailsRequest = contactDetailsRequest;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ContactDetailsResponse fetchContactDetails(ContactDetailsRequest contactDetailsRequest) {
		ContactDetailsResponse contactDetailsResponse = new ContactDetailsResponse();
	
		List<ContactObject> contactDetailsResponseList = new ArrayList<>();
	
		Map<String, Object> resultMap = null;
		try {
			SqlParameter supplierIdParam = new SqlParameter(Types.INTEGER);
			SqlParameter landIdParam = new SqlParameter(Types.INTEGER);
		    SqlParameter ssoIdParam = new SqlParameter(Types.VARCHAR);
		    SqlParameter errorCdParam = new SqlParameter(Types.VARCHAR);

			List paramList = new ArrayList();
			paramList.add(supplierIdParam);
			paramList.add(landIdParam);
			paramList.add(ssoIdParam);
			paramList.add(errorCdParam);

			ContactDetailsRepositoryImpl contactDetailsRepositoryImpl = new ContactDetailsRepositoryImpl();
			contactDetailsRepositoryImpl.contactDetailsRequest = contactDetailsRequest;
			contactDetailsRepositoryImpl.procedureCall = "{call " + SupplierDataConstants.PROC_FETCH_SUPPLIER_CONTACT_DETAILS
					+ " (?,?,?,?)}";
			resultMap = jdbcTemplate.call(contactDetailsRepositoryImpl, paramList);

			if (null != resultMap) {
				for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
					if ("#result-set-1".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> contactMap = lst.get(i);
							ContactObject contactObject = new ContactObject();
							contactObject.setSupplierId((Integer)contactMap.get("Supplier_Id"));
							contactObject.setContactId((Integer)contactMap.get("Contact_Id"));
							contactObject.setSubGroupId((Integer)contactMap.get("Sub_Group_Id"));
							contactObject.setSubGroupDesc((String)contactMap.get("Sub_Group_Desc"));
							contactObject.setFirstName((String)contactMap.get("First Name"));
							contactObject.setLastName((String)contactMap.get("Last Name"));
							contactObject.setRole((String)contactMap.get("Role"));
							contactObject.setGetRemoteEnabled((String)contactMap.get("GET Remote Enabled"));
							contactObject.setTeleDialingCode((String)contactMap.get("Tele Dailing Cd"));
							contactObject.setTelephoneNumber((String)contactMap.get("Telephone Number"));
							contactObject.setAddTeleDialingCode((String)contactMap.get("Additional Tel Dailing Cd"));
							contactObject.setAdditionalTelephone((String)contactMap.get("Additional Telephone"));
							contactObject.setMobDialingCode((String)contactMap.get("Mobile Dailing Cd"));
							contactObject.setMobileNumber((String)contactMap.get("Mobile Nr"));
							contactObject.setFaxDialingCode((String)contactMap.get("Fax Dailing Cd"));
							contactObject.setFaxNumber((String)contactMap.get("Fax Number"));
							contactObject.setEmailId((String)contactMap.get("Email ID"));
							
							contactObject.setComments((String)contactMap.get("Comments"));
							contactObject.setLastUpdatedDate((String)contactMap.get("Last Updated Date").toString());
							contactObject.setPrimaryCntctNr((Integer)contactMap.get("Primary_Cntct_Nr"));
							contactObject.setIsEnabled((String)contactMap.get("Is Enabled"));
								
							contactDetailsResponseList.add(contactObject);
						}
					}  
				}
			}
			
			contactDetailsResponse.getContactDetailsList().addAll(contactDetailsResponseList);
			
		} catch (Exception e) {
			logger.error("An error occurred while fetching supplier contact details list: " + e);
		}
		return contactDetailsResponse;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		DatatypeCommonUtility.checkNull(1, callableStatement, contactDetailsRequest.getSupplierId());
		DatatypeCommonUtility.checkNull(2, callableStatement, contactDetailsRequest.getLangId());
		callableStatement.setString(3, contactDetailsRequest.getSsoId());
		callableStatement.setString(4, contactDetailsRequest.getErrorCd());
		connection.setAutoCommit(true);
		return callableStatement;
	}
}
