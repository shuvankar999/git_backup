package com.tip.supplier.repository.impl;

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

import com.tip.supplier.main.DatatypeCommonUtility;
import com.tip.supplier.main.SupplierDataConstants;
import com.tip.supplier.model.ActiveInsuranceDocumentObject;
import com.tip.supplier.model.InsuranceLinesObject;
import com.tip.supplier.model.InsuranceRequest;
import com.tip.supplier.model.InsuranceResponse;
import com.tip.supplier.model.InsuranceStatusObject;
import com.tip.supplier.repository.InsuranceRepository;

@Repository
public class InsuranceRepositoryImpl implements InsuranceRepository, CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(InsuranceRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;

	private InsuranceRequest insuranceRequest;

	public void setInsuranceRequest(InsuranceRequest insuranceRequest) {
		this.insuranceRequest = insuranceRequest;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public InsuranceResponse fetchInsuranceDetails(InsuranceRequest insuranceRequest) {
		InsuranceResponse insuranceResponse = new InsuranceResponse();

		List<InsuranceLinesObject> insuranceLinesResponseList = new ArrayList<>();
		List<ActiveInsuranceDocumentObject> activeInsuranceResponseList = new ArrayList<>();
		List<ActiveInsuranceDocumentObject> archivedInsuranceResponseList = new ArrayList<>();
		List<InsuranceStatusObject> insuranceStatusResponseList = new ArrayList<>();

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

			InsuranceRepositoryImpl insuranceRepositoryImpl = new InsuranceRepositoryImpl();
			insuranceRepositoryImpl.insuranceRequest = insuranceRequest;
			insuranceRepositoryImpl.procedureCall = "{call " + SupplierDataConstants.PROC_FETCH_SUPPLIER_INSURANCE
					+ " (?,?,?,?)}";
			resultMap = jdbcTemplate.call(insuranceRepositoryImpl, paramList);

			if (null != resultMap) {
				for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
					if ("#result-set-1".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> insuranceMap = lst.get(i);
							InsuranceLinesObject insuranceLinesObject = new InsuranceLinesObject();
							insuranceLinesObject.setSupplierId((Integer) insuranceMap.get("Supplier_Id"));
							insuranceLinesObject.setGroupId((Integer) insuranceMap.get("Group_Id"));
							insuranceLinesObject.setGroupDesc((String) insuranceMap.get("Group_Desc"));
							insuranceLinesObject.setSubGroupId((Integer) insuranceMap.get("Sub_Group_Id"));
							insuranceLinesObject.setSubGroupDesc((String) insuranceMap.get("Sub_Group_Desc"));
							insuranceLinesObject.setItemId((Integer) insuranceMap.get("Item_Id"));
							insuranceLinesObject.setItemDesc((String) insuranceMap.get("Item_Desc"));
							insuranceLinesObject.setInsuranceTypeDesc((String) insuranceMap.get("Insurance_Type_Desc"));
							insuranceLinesObject.setInsuredAmount((BigDecimal) insuranceMap.get("Insured_Amount"));
							insuranceLinesObject.setExpiryDate((String) insuranceMap.get("Expiry_Date"));
							insuranceLinesObject.setInsuranceStatus((Integer) insuranceMap.get("Insurance_Status"));
							insuranceLinesObject.setIsEnabled((String) insuranceMap.get("Is_Enabled"));

							insuranceLinesResponseList.add(insuranceLinesObject);
						}
					} else if ("#result-set-2".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> activeMap = lst.get(i);
							ActiveInsuranceDocumentObject activeInsuranceDocumentObject = new ActiveInsuranceDocumentObject();
							activeInsuranceDocumentObject.setSupplierId((Integer) activeMap.get("SUPPLIER_ID"));
							activeInsuranceDocumentObject
									.setInsuranceTypeId((Integer) activeMap.get("INSURANCE_TYPE_ID"));
							activeInsuranceDocumentObject
									.setInsuranceTypeDesc((String) activeMap.get("INSURANCE_TYPE_DESC"));
							activeInsuranceDocumentObject
									.setInsuredAmount((BigDecimal) activeMap.get("INSURED_AMOUNT"));
							activeInsuranceDocumentObject.setExpiryDate((String) activeMap.get("EXPIRY_DATE"));
							activeInsuranceDocumentObject.setDocName((String) activeMap.get("DOC_NAME"));
							activeInsuranceDocumentObject.setDocStatus((Integer) activeMap.get("DOC_STATUS"));
							activeInsuranceDocumentObject.setDocuwareDocId((Integer) activeMap.get("DOCUWARE_DOC_ID"));
							activeInsuranceDocumentObject.setCreateUser((String) activeMap.get("CREATE_USER"));
							activeInsuranceDocumentObject.setCreateDate((String) activeMap.get("CREATE_DATE"));
							activeInsuranceDocumentObject.setMaintUser((String) activeMap.get("MAINT_USER"));
							activeInsuranceDocumentObject.setMaintDate((String) activeMap.get("MAINT_DATE"));

							activeInsuranceResponseList.add(activeInsuranceDocumentObject);
						}
					} else if ("#result-set-3".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> archivedMap = lst.get(i);
							ActiveInsuranceDocumentObject activeInsuranceDocumentObject = new ActiveInsuranceDocumentObject();
							activeInsuranceDocumentObject.setSupplierId((Integer) archivedMap.get("SUPPLIER_ID"));
							activeInsuranceDocumentObject
									.setInsuranceTypeId((Integer) archivedMap.get("INSURANCE_TYPE_ID"));
							activeInsuranceDocumentObject
									.setInsuranceTypeDesc((String) archivedMap.get("INSURANCE_TYPE_DESC"));
							activeInsuranceDocumentObject
									.setInsuredAmount((BigDecimal) archivedMap.get("INSURED_AMOUNT"));
							activeInsuranceDocumentObject.setExpiryDate((String) archivedMap.get("EXPIRY_DATE"));
							activeInsuranceDocumentObject.setDocName((String) archivedMap.get("DOC_NAME"));
							activeInsuranceDocumentObject.setDocStatus((Integer) archivedMap.get("DOC_STATUS"));
							activeInsuranceDocumentObject.setDocuwareDocId((Integer) archivedMap.get("DOCUWARE_DOC_ID"));
							activeInsuranceDocumentObject.setCreateUser((String) archivedMap.get("CREATE_USER"));
							activeInsuranceDocumentObject.setCreateDate((String) archivedMap.get("CREATE_DATE"));
							activeInsuranceDocumentObject.setMaintUser((String) archivedMap.get("MAINT_USER"));
							activeInsuranceDocumentObject.setMaintDate((String) archivedMap.get("MAINT_DATE"));

							archivedInsuranceResponseList.add(activeInsuranceDocumentObject);
						}
					} else if ("#result-set-4".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> statusMap = lst.get(i);
							InsuranceStatusObject insuranceStatusObject = new InsuranceStatusObject();
							insuranceStatusObject.setSupplierId((Integer) statusMap.get("Supplier_Id"));
							insuranceStatusObject.setErrorCd((String) statusMap.get("Error_Cd"));
							insuranceStatusObject.setStatusId((Integer) statusMap.get("Status_Id"));

							insuranceStatusResponseList.add(insuranceStatusObject);
						}
					}
				}
			}

			insuranceResponse.getInsuranceLinesList().addAll(insuranceLinesResponseList);
			insuranceResponse.getActiveInsuranceDocumentList().addAll(activeInsuranceResponseList);
			insuranceResponse.getArchivedInsuranceDocumentList().addAll(archivedInsuranceResponseList);
			insuranceResponse.getInsuranceStatusResponseList().addAll(insuranceStatusResponseList);

		} catch (Exception e) {
			logger.error("An error occurred while fetching supplier insurance details: " + e);
		}
		return insuranceResponse;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		DatatypeCommonUtility.checkNull(1, callableStatement, insuranceRequest.getSupplierId());
		DatatypeCommonUtility.checkNull(2, callableStatement, insuranceRequest.getLangId());
		callableStatement.setString(3, insuranceRequest.getSsoId());
		callableStatement.setString(4, insuranceRequest.getErrorCd());
		connection.setAutoCommit(true);
		return callableStatement;
	}
}
