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
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.tip.supplier.main.DatatypeCommonUtility;
import com.tip.supplier.main.SupplierDataConstants;
import com.tip.supplier.model.ActiveContractDocumentObject;
import com.tip.supplier.model.LabourDynamicLinesObject;
import com.tip.supplier.model.LabourLastUpdatedObject;
import com.tip.supplier.model.LabourRateStatusObject;
import com.tip.supplier.model.LabourRatesRequest;
import com.tip.supplier.model.LabourRatesResponse;
import com.tip.supplier.model.LabourStaticLinesObject;
import com.tip.supplier.repository.LabourRatesRepository;

@Repository
public class LabourRatesRepositoryImpl implements LabourRatesRepository, CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(LabourRatesRepositoryImpl.class);

	public static final String SUPPLIER_ID = "Supplier_Id";
	public static final String GRP_ID = "Group_Id";
	public static final String GRP_SHORT_DESC = "Group_Short_Desc";
	public static final String GRP_DESC = "Group_Desc";
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

	private LabourRatesRequest labourRatesRequest;

	public void setLabourRatesRequest(LabourRatesRequest labourRatesRequest) {
		this.labourRatesRequest = labourRatesRequest;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public LabourRatesResponse fetchLabourRates(LabourRatesRequest labourRatesRequest) {
		LabourRatesResponse labourRatesResponse = new LabourRatesResponse();

		List<LabourDynamicLinesObject> labourDynamicLinesResponseList = new ArrayList<>();
		List<LabourStaticLinesObject> labourStaticLinesResponseList = new ArrayList<>();
		List<LabourStaticLinesObject> contractDataResponseList = new ArrayList<>();
		List<ActiveContractDocumentObject> activeContractResponseList = new ArrayList<>();
		List<ActiveContractDocumentObject> archivedContractResponseList = new ArrayList<>();
		List<LabourLastUpdatedObject> labourLastUpdatedResponseList = new ArrayList<>();
		List<LabourRateStatusObject> labourRateStatusResponseList = new ArrayList<>();

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

			LabourRatesRepositoryImpl labourRatesRepositoryImpl = new LabourRatesRepositoryImpl();
			labourRatesRepositoryImpl.labourRatesRequest = labourRatesRequest;
			labourRatesRepositoryImpl.procedureCall = "{call " + SupplierDataConstants.PROC_FETCH_LABOUR_RATES
					+ " (?,?,?,?)}";
			resultMap = jdbcTemplate.call(labourRatesRepositoryImpl, paramList);

			if (null != resultMap) {
				for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
					if ("#result-set-1".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> dynamicMap = lst.get(i);
							LabourDynamicLinesObject labourDynamicLinesObject = new LabourDynamicLinesObject();
							labourDynamicLinesObject.setSupplierId((Integer) dynamicMap.get(SUPPLIER_ID));
							labourDynamicLinesObject
									.setLabourRateGroupId((Integer) dynamicMap.get("Labour_Rate_Group_Id"));
							labourDynamicLinesObject
									.setLabourRateGroupDesc((String) dynamicMap.get("Labour_Rate_Group_Desc"));
							labourDynamicLinesObject
									.setLabourRateSubGroupId((Integer) dynamicMap.get("Labour_Rate_Sub_Group_Id"));
							labourDynamicLinesObject
									.setLabourRateSubGroupDesc((String) dynamicMap.get("Labour_Rate_Sub_Group_Desc"));
							labourDynamicLinesObject.setLabourRateId((Integer) dynamicMap.get("Labour_Rate_Id"));
							labourDynamicLinesObject.setSeqId((Integer) dynamicMap.get("Seq_Id"));
							labourDynamicLinesObject.setLabourRateDesc((String) dynamicMap.get("Labour_Rate_Desc"));
							
							if(dynamicMap.get("Day_Rate") != null) {
								BigDecimal bd = (BigDecimal)dynamicMap.get("Day_Rate");
							    bd = bd.setScale(2, RoundingMode.DOWN);
							    labourDynamicLinesObject.setDayRate(bd.toString());
							}
							
							if(dynamicMap.get("Night_Rate") != null) {
								BigDecimal bd = (BigDecimal)dynamicMap.get("Night_Rate");
							    bd = bd.setScale(2, RoundingMode.DOWN);
							    labourDynamicLinesObject.setNightRate(bd.toString());
							}
							
							if(dynamicMap.get("Mobile_Rate") != null) {
								BigDecimal bd = (BigDecimal)dynamicMap.get("Mobile_Rate");
							    bd = bd.setScale(2, RoundingMode.DOWN);
							    labourDynamicLinesObject.setMobileRate(bd.toString());
							}
							
							if(dynamicMap.get("Sat_Rate") != null) {
								BigDecimal bd = (BigDecimal)dynamicMap.get("Sat_Rate");
							    bd = bd.setScale(2, RoundingMode.DOWN);
							    labourDynamicLinesObject.setSatRate(bd.toString());
							}
							
							if(dynamicMap.get("Sun_Rate") != null) {
								BigDecimal bd = (BigDecimal)dynamicMap.get("Sun_Rate");
							    bd = bd.setScale(2, RoundingMode.DOWN);
							    labourDynamicLinesObject.setSunRate(bd.toString());
							}
							
							if(dynamicMap.get("Pub_Holiday_Rate") != null) {
								BigDecimal bd = (BigDecimal)dynamicMap.get("Pub_Holiday_Rate");
							    bd = bd.setScale(2, RoundingMode.DOWN);
							    labourDynamicLinesObject.setPubHolidayRate(bd.toString());
							}
							
							labourDynamicLinesObject.setIsEnabled((String) dynamicMap.get("Is_Enabled"));

							labourDynamicLinesResponseList.add(labourDynamicLinesObject);
						}
					} else if ("#result-set-2".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> staticMap = lst.get(i);
							LabourStaticLinesObject labourStaticLinesObject = new LabourStaticLinesObject();
							labourStaticLinesObject.setSupplierId((Integer) staticMap.get(SUPPLIER_ID));
							labourStaticLinesObject.setGroupId((Integer) staticMap.get(GRP_ID));
							labourStaticLinesObject.setGroupShortDesc((String) staticMap.get(GRP_SHORT_DESC));
							labourStaticLinesObject.setGroupDesc((String) staticMap.get(GRP_DESC));
							labourStaticLinesObject.setSubGroupId((Integer) staticMap.get(SUB_GRP_ID));
							labourStaticLinesObject.setSubGroupDesc((String) staticMap.get(SUB_GRP_DESC));
							labourStaticLinesObject.setItemId((Integer) staticMap.get(ITEM_ID));
							labourStaticLinesObject.setItemShortDesc((String) staticMap.get(ITEM_SHORT_DESC));
							labourStaticLinesObject.setItemDesc((String) staticMap.get(ITEM_DESC));
							labourStaticLinesObject.setItemReq((String) staticMap.get(ITEM_REQ));
							labourStaticLinesObject.setItemValType((String) staticMap.get(ITEM_VAL_TYPE));
							labourStaticLinesObject.setItemOrder((Integer) staticMap.get(ITEM_ORDER));
							labourStaticLinesObject.setItemDataType((String) staticMap.get(ITEM_DATA_TYPE));
							labourStaticLinesObject.setItemModule((String) staticMap.get(ITEM_MODULE));
							labourStaticLinesObject.setItemValue((String) staticMap.get(ITEM_VALUE));
							labourStaticLinesObject.setIsEnabled((String) staticMap.get(IS_ENABLED));
							labourStaticLinesObject.setValType((String) staticMap.get(VAL_TYPE));

							labourStaticLinesResponseList.add(labourStaticLinesObject);
						}
					} else if ("#result-set-3".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> contractMap = lst.get(i);
							LabourStaticLinesObject labourStaticLinesObject = new LabourStaticLinesObject();
							labourStaticLinesObject.setSupplierId((Integer) contractMap.get(SUPPLIER_ID));
							labourStaticLinesObject.setGroupId((Integer) contractMap.get(GRP_ID));
							labourStaticLinesObject.setGroupShortDesc((String) contractMap.get(GRP_SHORT_DESC));
							labourStaticLinesObject.setGroupDesc((String) contractMap.get(GRP_DESC));
							labourStaticLinesObject.setSubGroupId((Integer) contractMap.get(SUB_GRP_ID));
							labourStaticLinesObject.setSubGroupDesc((String) contractMap.get(SUB_GRP_DESC));
							labourStaticLinesObject.setItemId((Integer) contractMap.get(ITEM_ID));
							labourStaticLinesObject.setItemShortDesc((String) contractMap.get(ITEM_SHORT_DESC));
							labourStaticLinesObject.setItemDesc((String) contractMap.get(ITEM_DESC));
							labourStaticLinesObject.setItemReq((String) contractMap.get(ITEM_REQ));
							labourStaticLinesObject.setItemValType((String) contractMap.get(ITEM_VAL_TYPE));
							labourStaticLinesObject.setItemOrder((Integer) contractMap.get(ITEM_ORDER));
							labourStaticLinesObject.setItemDataType((String) contractMap.get(ITEM_DATA_TYPE));
							labourStaticLinesObject.setItemModule((String) contractMap.get(ITEM_MODULE));
							labourStaticLinesObject.setItemValue((String) contractMap.get(ITEM_VALUE));
							labourStaticLinesObject.setIsEnabled((String) contractMap.get(IS_ENABLED));
							labourStaticLinesObject.setValType((String) contractMap.get(VAL_TYPE));

							contractDataResponseList.add(labourStaticLinesObject);
						}
					} else if ("#result-set-4".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> activeMap = lst.get(i);
							ActiveContractDocumentObject activeContractDocumentObject = new ActiveContractDocumentObject();
							activeContractDocumentObject.setSupplierId((Integer) activeMap.get("SUPPLIER_ID"));
							activeContractDocumentObject.setContractType((String) activeMap.get("CONTRACT_TYPE"));
							activeContractDocumentObject
									.setSelfApprovalLimit((BigDecimal) activeMap.get("SELF_APPROVAL_LIMIT"));
							activeContractDocumentObject
									.setContractStartDate((String) activeMap.get("CONTRACT_START_DATE"));
							activeContractDocumentObject
									.setContractExpireDate((String) activeMap.get("CONTRACT_EXPIRE_DATE"));
							activeContractDocumentObject.setDocName((String) activeMap.get("DOC_NAME"));
							activeContractDocumentObject.setDocStatus((Integer) activeMap.get("DOC_STATUS"));
							activeContractDocumentObject.setDocuwareDocId((Integer) activeMap.get("DOCUWARE_DOC_ID"));
							activeContractDocumentObject.setCreateUser((String) activeMap.get("CREATE_USER"));
							activeContractDocumentObject.setCreateDate((String) activeMap.get("CREATE_DATE"));
							activeContractDocumentObject.setMaintUser((String) activeMap.get("MAINT_USER"));
							activeContractDocumentObject.setMaintDate((String) activeMap.get("MAINT_DATE"));

							activeContractResponseList.add(activeContractDocumentObject);
						}
					} else if ("#result-set-5".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> archivedMap = lst.get(i);
							ActiveContractDocumentObject activeContractDocumentObject = new ActiveContractDocumentObject();
							activeContractDocumentObject.setSupplierId((Integer) archivedMap.get("SUPPLIER_ID"));
							activeContractDocumentObject.setContractType((String) archivedMap.get("CONTRACT_TYPE"));
							activeContractDocumentObject
									.setSelfApprovalLimit((BigDecimal) archivedMap.get("SELF_APPROVAL_LIMIT"));
							activeContractDocumentObject
									.setContractStartDate((String) archivedMap.get("CONTRACT_START_DATE"));
							activeContractDocumentObject
									.setContractExpireDate((String) archivedMap.get("CONTRACT_EXPIRE_DATE"));
							activeContractDocumentObject.setDocName((String) archivedMap.get("DOC_NAME"));
							activeContractDocumentObject.setDocStatus((Integer) archivedMap.get("DOC_STATUS"));
							activeContractDocumentObject.setDocuwareDocId((Integer) archivedMap.get("DOCUWARE_DOC_ID"));
							activeContractDocumentObject.setCreateUser((String) archivedMap.get("CREATE_USER"));
							activeContractDocumentObject.setCreateDate((String) archivedMap.get("CREATE_DATE"));
							activeContractDocumentObject.setMaintUser((String) archivedMap.get("MAINT_USER"));
							activeContractDocumentObject.setMaintDate((String) archivedMap.get("MAINT_DATE"));

							archivedContractResponseList.add(activeContractDocumentObject);
						}
					} else if ("#result-set-6".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> statusMap = lst.get(i);
							LabourLastUpdatedObject labourLastUpdatedObject = new LabourLastUpdatedObject();
							labourLastUpdatedObject.setLastUpdatedName((String) statusMap.get("Last_Updated_Name"));
							labourLastUpdatedObject.setLastUpdatedDate((String) statusMap.get("Last_Updated_Date"));

							labourLastUpdatedResponseList.add(labourLastUpdatedObject);
						}
					} else if ("#result-set-7".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> statusMap = lst.get(i);
							LabourRateStatusObject labourRateStatusObject = new LabourRateStatusObject();
							labourRateStatusObject.setSupplierId((Integer) statusMap.get("Supplier_Id"));
							labourRateStatusObject.setLabourErrorCd((String) statusMap.get("Labour_Error_Cd"));
							labourRateStatusObject.setStatusId((Integer) statusMap.get("Status_Id"));

							labourRateStatusResponseList.add(labourRateStatusObject);
						}
					}
				}
			}

			labourRatesResponse.getLabourDynamicLinesList().addAll(labourDynamicLinesResponseList);
			labourRatesResponse.getLabourStaticLinesList().addAll(labourStaticLinesResponseList);
			labourRatesResponse.getContractDataList().addAll(contractDataResponseList);
			labourRatesResponse.getActiveContractDocumentList().addAll(activeContractResponseList);
			labourRatesResponse.getArchivedContractDocumentList().addAll(archivedContractResponseList);
			labourRatesResponse.getLabourLastUpdatedList().addAll(labourLastUpdatedResponseList);
			labourRatesResponse.getLabourRateStatusResponseList().addAll(labourRateStatusResponseList);

		} catch (Exception e) {
			logger.error("An error occurred while fetching supplier labour rate details: " + e);
		}
		return labourRatesResponse;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		DatatypeCommonUtility.checkNull(1, callableStatement, labourRatesRequest.getSupplierId());
		DatatypeCommonUtility.checkNull(2, callableStatement, labourRatesRequest.getLangId());
		callableStatement.setString(3, labourRatesRequest.getSsoId());
		callableStatement.setString(4, labourRatesRequest.getErrorCd());
		connection.setAutoCommit(true);
		return callableStatement;
	}
}
