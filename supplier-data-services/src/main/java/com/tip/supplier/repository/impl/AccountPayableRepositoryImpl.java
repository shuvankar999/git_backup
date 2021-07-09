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
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.tip.supplier.main.DatatypeCommonUtility;
import com.tip.supplier.main.SupplierDataConstants;
import com.tip.supplier.model.AccountPayableRequest;
import com.tip.supplier.model.AccountPayableResponse;
import com.tip.supplier.model.PayableBasicDetailsObject;
import com.tip.supplier.model.PayableRegisteredObject;
import com.tip.supplier.model.PayableStatusObject;
import com.tip.supplier.repository.AccountPayableRepository;

@Repository
public class AccountPayableRepositoryImpl implements AccountPayableRepository, CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(AccountPayableRepositoryImpl.class);
	
	public static final String SUB_GRP_ID = "Sub_Group_Id";
	public static final String SUB_GRP_DESC = "Sub_Group_Desc";
	public static final String ITEM_ID = "Item_Id";	
	public static final String ITEM_SHORT_DESC = "Item_Short_Desc";
	public static final String ITEM_DESC = "Item_Desc";
	public static final String ITEM_REQ = "Item_Req";
	public static final String ITEM_VAL_TYPE = "Item_Val_Type";
	public static final String ITEM_ORDER = "Item_Order";
	public static final String ITEM_DATA_TYPE = "Item_Data_Type";
	public static final String ITEM_MODULE = "Item_Module";
	public static final String ITEM_VALUE = "Item_Value";
	public static final String IS_ENABLED = "Is_Enabled";
	public static final String VAL_TYPE = "Val_Type";	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;

	private AccountPayableRequest accountPayableRequest;

	public void setAccountPayableRequest(AccountPayableRequest accountPayableRequest) {
		this.accountPayableRequest = accountPayableRequest;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public AccountPayableResponse fetchAccountPayable(AccountPayableRequest accountPayableRequest) {
		AccountPayableResponse accountPayableResponse = new AccountPayableResponse();
		List<PayableBasicDetailsObject> payableBasicResponseList = new ArrayList<>();
		List<PayableRegisteredObject> payableRegisteredResponseList = new ArrayList<>();
		List<PayableStatusObject> payableStatusResponseList = new ArrayList<>();
		Map<String, Object> resultMap = null;
		try {
			SqlParameter supplierIdParam = new SqlParameter(Types.INTEGER);
			SqlParameter langIdParam = new SqlParameter(Types.INTEGER);
			SqlParameter ssoIdParam = new SqlParameter(Types.VARCHAR);
			SqlOutParameter erroroutParameter = new SqlOutParameter("errorcd", Types.VARCHAR);

			List paramList = new ArrayList();
			paramList.add(supplierIdParam);
			paramList.add(langIdParam);
			paramList.add(ssoIdParam);
			paramList.add(erroroutParameter);

			AccountPayableRepositoryImpl accountPayableRepositoryImpl = new AccountPayableRepositoryImpl();
			accountPayableRepositoryImpl.accountPayableRequest = accountPayableRequest;
			accountPayableRepositoryImpl.procedureCall = "{call "
					+ SupplierDataConstants.PROC_FETCH_ACCOUNT_PAYABLES_DETAILS + " (?,?,?,?)}";
			resultMap = jdbcTemplate.call(accountPayableRepositoryImpl, paramList);

			if (null != resultMap) {
				for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
					if ("#result-set-1".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> basicMap = lst.get(i);
							
							accountPayableResponse.setSupplierId((Integer) basicMap.get("Supplier_Id"));
							accountPayableResponse.setGroupId((Integer) basicMap.get("Group_Id"));
							accountPayableResponse.setGroupShortDesc((String) basicMap.get("Group_Short_Desc"));
							accountPayableResponse.setGroupDesc((String) basicMap.get("Group_Desc"));
							
							PayableBasicDetailsObject payableBasicDetailsObject = new PayableBasicDetailsObject();
							
							payableBasicDetailsObject.setSubGroupId((Integer) basicMap.get(SUB_GRP_ID));
							payableBasicDetailsObject.setSubGroupDesc((String) basicMap.get(SUB_GRP_DESC));
							payableBasicDetailsObject.setItemId((Integer) basicMap.get(ITEM_ID));
							payableBasicDetailsObject.setItemShortDesc((String) basicMap.get(ITEM_SHORT_DESC));
							payableBasicDetailsObject.setItemDesc((String) basicMap.get(ITEM_DESC));
							payableBasicDetailsObject.setItemReq((String) basicMap.get(ITEM_REQ));
							payableBasicDetailsObject.setItemValType((String) basicMap.get(ITEM_VAL_TYPE));
							payableBasicDetailsObject.setItemOrder((Integer) basicMap.get(ITEM_ORDER));
							payableBasicDetailsObject.setItemDataType((String) basicMap.get(ITEM_DATA_TYPE));
							payableBasicDetailsObject.setItemModule((String) basicMap.get(ITEM_MODULE));
							payableBasicDetailsObject.setItemValue((String) basicMap.get(ITEM_VALUE));
							payableBasicDetailsObject.setIsEnabled((String) basicMap.get(IS_ENABLED));
							payableBasicDetailsObject.setValType((String) basicMap.get(VAL_TYPE));
							payableBasicResponseList.add(payableBasicDetailsObject);

						}
					} else if ("#result-set-2".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> registeredMap = lst.get(i);
							PayableRegisteredObject payableRegisteredObject = new PayableRegisteredObject();
							
							payableRegisteredObject.setSubGroupId((Integer) registeredMap.get(SUB_GRP_ID));
							payableRegisteredObject.setSubGroupDesc((String) registeredMap.get(SUB_GRP_DESC));
							payableRegisteredObject.setItemId((Integer) registeredMap.get(ITEM_ID));
							payableRegisteredObject.setItemShortDesc((String) registeredMap.get(ITEM_SHORT_DESC));
							payableRegisteredObject.setItemDesc((String) registeredMap.get(ITEM_DESC));
							payableRegisteredObject.setItemReq((String) registeredMap.get(ITEM_REQ));
							payableRegisteredObject.setItemValType((String) registeredMap.get(ITEM_VAL_TYPE));
							payableRegisteredObject.setItemOrder((Integer) registeredMap.get(ITEM_ORDER));
							payableRegisteredObject.setItemDataType((String) registeredMap.get(ITEM_DATA_TYPE));
							payableRegisteredObject.setItemModule((String) registeredMap.get(ITEM_MODULE));
							payableRegisteredObject.setItemValue((String) registeredMap.get(ITEM_VALUE));
							payableRegisteredObject.setIsEnabled((String) registeredMap.get(IS_ENABLED));
							payableRegisteredObject.setValType((String) registeredMap.get(VAL_TYPE));
							payableRegisteredResponseList.add(payableRegisteredObject);
						}
					} else if ("#result-set-3".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> status = lst.get(i);
							PayableStatusObject payableStatusObject = new PayableStatusObject();
							payableStatusObject.setSupplierId((Integer) status.get("Supplier_Id"));
							payableStatusObject.setErrorCd((String) status.get("Error_Cd"));
							payableStatusObject.setStatusId((Integer) status.get("Status_Id"));
							payableStatusResponseList.add(payableStatusObject);
						}
					} else if ("errorcd".equalsIgnoreCase(entry.getKey())) {
						accountPayableResponse.setErrorCd((String) entry.getValue());
					}
				}
			}
			accountPayableResponse.getPayableBasicDetailsList().addAll(payableBasicResponseList);
			accountPayableResponse.getPayableRegisteredDetailsList().addAll(payableRegisteredResponseList);
			accountPayableResponse.getSupplierPayableStatusList().addAll(payableStatusResponseList);

		} catch (Exception e) {
			logger.error("An error occurred while fetching supplier account and payables details: " + e);
		}
		return accountPayableResponse;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		DatatypeCommonUtility.checkNull(1, callableStatement, accountPayableRequest.getSupplierId());
		DatatypeCommonUtility.checkNull(2, callableStatement, accountPayableRequest.getLangId());
		callableStatement.setString(3, accountPayableRequest.getSsoId());
		callableStatement.registerOutParameter(4, Types.VARCHAR);
		connection.setAutoCommit(true);
		return callableStatement;
	}
}
