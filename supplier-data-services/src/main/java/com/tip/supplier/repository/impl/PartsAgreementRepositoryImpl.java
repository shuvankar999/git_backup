package com.tip.supplier.repository.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import com.tip.supplier.model.PartsAgreementObjects;
import com.tip.supplier.model.PartsAgreementRequest;
import com.tip.supplier.model.PartsAgreementResponse;
import com.tip.supplier.model.PartsLineObjects;
import com.tip.supplier.repository.PartsAgreementRepository;

@Repository
public class PartsAgreementRepositoryImpl implements PartsAgreementRepository, CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(PartsAgreementRepositoryImpl.class);

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

	private PartsAgreementRequest partsAgreementRequest;

	public void setPartsAgreementRequest(PartsAgreementRequest partsAgreementRequest) {
		this.partsAgreementRequest = partsAgreementRequest;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PartsAgreementResponse fetchPartsAgreement(PartsAgreementRequest partsAgreementRequest) {
		PartsAgreementResponse partsAgreementResponse = new PartsAgreementResponse();
		List<PartsAgreementObjects> partsAgreementResponseList = new ArrayList<>();
		List<PartsLineObjects> partsLineResponseList = new ArrayList<>();

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

			PartsAgreementRepositoryImpl partsAgreementRepositoryImpl = new PartsAgreementRepositoryImpl();
			partsAgreementRepositoryImpl.partsAgreementRequest = partsAgreementRequest;
			partsAgreementRepositoryImpl.procedureCall = "{call "
					+ SupplierDataConstants.PROC_FETCH_PART_AGREEMENT_DETAILS + " (?,?,?,?)}";
			resultMap = jdbcTemplate.call(partsAgreementRepositoryImpl, paramList);

			if (null != resultMap) {
				for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
					if ("#result-set-1".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> agreementMap = lst.get(i);
							PartsAgreementObjects partsAgreementObjects = new PartsAgreementObjects();

							partsAgreementObjects.setSupplierId((Integer) agreementMap.get("Supplier_Id"));
							partsAgreementObjects.setGroupId((Integer) agreementMap.get("Group_Id"));
							partsAgreementObjects.setGroupShortDesc((String) agreementMap.get("Group_Short_Desc"));
							partsAgreementObjects.setGroupDesc((String) agreementMap.get("Group_Desc"));
							partsAgreementObjects.setSubGroupId((Integer) agreementMap.get(SUB_GRP_ID));
							partsAgreementObjects.setSubGroupDesc((String) agreementMap.get(SUB_GRP_DESC));
							partsAgreementObjects.setItemId((Integer) agreementMap.get(ITEM_ID));
							partsAgreementObjects.setItemShortDesc((String) agreementMap.get(ITEM_SHORT_DESC));
							partsAgreementObjects.setItemDesc((String) agreementMap.get(ITEM_DESC));
							partsAgreementObjects.setItemReq((String) agreementMap.get(ITEM_REQ));
							partsAgreementObjects.setItemValType((String) agreementMap.get(ITEM_VAL_TYPE));
							partsAgreementObjects.setItemOrder((Integer) agreementMap.get(ITEM_ORDER));
							partsAgreementObjects.setItemDataType((String) agreementMap.get(ITEM_DATA_TYPE));
							partsAgreementObjects.setItemModule((String) agreementMap.get(ITEM_MODULE));
							partsAgreementObjects.setItemValue((String) agreementMap.get(ITEM_VALUE));
							partsAgreementObjects.setIsEnabled((String) agreementMap.get(IS_ENABLED));
							partsAgreementObjects.setValType((String) agreementMap.get(VAL_TYPE));
							partsAgreementResponseList.add(partsAgreementObjects);

						}
					} else if ("#result-set-2".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> lineMap = lst.get(i);

							PartsLineObjects partsLineObjects = new PartsLineObjects();
							partsLineObjects.setSupplierId((Integer) lineMap.get("supplierId"));
							partsLineObjects.setDnaCode((String) lineMap.get("dnaCode"));
							partsLineObjects.setGroups((String) lineMap.get("groups"));
							partsLineObjects.setSubGroup((String) lineMap.get("subGroup"));
							partsLineObjects.setActivity((String) lineMap.get("activity"));

							partsLineObjects.setMaintActionCd((String) lineMap.get("maintActionCd"));
							partsLineObjects.setActionDesc((String) lineMap.get("actionDesc"));
							
							if(lineMap.get("fixedPrice") != null) {
								BigDecimal bd = (BigDecimal)lineMap.get("fixedPrice");
							    bd = bd.setScale(2, RoundingMode.DOWN);
							    partsLineObjects.setFixedPrice(bd.toString());
							}
							
							if(lineMap.get("costPlus") != null) {
								BigDecimal bd = (BigDecimal)lineMap.get("costPlus");
							    bd = bd.setScale(2, RoundingMode.DOWN);
							    partsLineObjects.setCostPlus(bd.toString());
							}
							
							if(lineMap.get("discount") != null) {
								BigDecimal bd = (BigDecimal)lineMap.get("discount");
							    bd = bd.setScale(2, RoundingMode.DOWN);
							    partsLineObjects.setDiscount(bd.toString());
							}

							partsLineObjects.setIsActive((String) lineMap.get("isActive"));
							partsLineObjects.setIsEnabled((String) lineMap.get("isEnabled"));

							partsLineObjects.setManufacturerId((String) lineMap.get("manufacturerId"));
							partsLineObjects.setManufacturer((String) lineMap.get("manufacturer"));
							partsLineObjects.setQuality((String) lineMap.get("quality"));
							partsLineObjects.setOePartNr((String) lineMap.get("oePartNr"));
							partsLineObjects.setSupplierPartNr((String) lineMap.get("supplierPartNr"));
							partsLineObjects.setPartsDescription((String) lineMap.get("Parts_Description"));

							partsLineResponseList.add(partsLineObjects);
						}
					} else if ("errorcd".equalsIgnoreCase(entry.getKey())) {
						partsAgreementResponse.setErrorCd((String) entry.getValue());
					}
				}
			}
			partsAgreementResponse.getSupplierPartsAgreementsList().addAll(partsAgreementResponseList);
			partsAgreementResponse.getSupplierPartsLineList().addAll(partsLineResponseList);
			partsAgreementResponse.getErrorCd();
		} catch (Exception e) {
			logger.error("An error occurred while fetching supplier part agreement details: " + e);
		}
		return partsAgreementResponse;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		DatatypeCommonUtility.checkNull(1, callableStatement, partsAgreementRequest.getSupplierId());
		DatatypeCommonUtility.checkNull(2, callableStatement, partsAgreementRequest.getLangId());
		callableStatement.setString(3, partsAgreementRequest.getSsoId());
		callableStatement.registerOutParameter(4, Types.VARCHAR);
		connection.setAutoCommit(true);
		return callableStatement;
	}
}
