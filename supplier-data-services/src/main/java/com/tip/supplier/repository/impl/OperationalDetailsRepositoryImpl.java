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
import com.tip.supplier.model.MapCoordinatesObject;
import com.tip.supplier.model.OpeningHoursObject;
import com.tip.supplier.model.OperationalAddressObject;
import com.tip.supplier.model.OperationalDetailsRequest;
import com.tip.supplier.model.OperationalDetailsResponse;
import com.tip.supplier.model.OperationalStatusObject;
import com.tip.supplier.model.OtherDetailsObject;
import com.tip.supplier.model.PaymentDetailsObject;
import com.tip.supplier.repository.OperationalDetailsRepository;

@Repository
public class OperationalDetailsRepositoryImpl implements OperationalDetailsRepository, CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(OperationalDetailsRepositoryImpl.class);

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

	private OperationalDetailsRequest operationalDetailsRequest;

	public void setOperationalDetailsRequest(OperationalDetailsRequest operationalDetailsRequest) {
		this.operationalDetailsRequest = operationalDetailsRequest;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public OperationalDetailsResponse fetchOperationalDetails(OperationalDetailsRequest operationalDetailsRequest) {
		OperationalDetailsResponse operationalDetailsResponse = new OperationalDetailsResponse();
		List<OperationalAddressObject> operationalAddressResponseList = new ArrayList<>();
		List<MapCoordinatesObject> operationalMapCoordinatesResponseList = new ArrayList<>();
		List<PaymentDetailsObject> operationalPaymentResponseList = new ArrayList<>();
		List<OtherDetailsObject> operationalOtherDetailsResponseList = new ArrayList<>();
		List<OpeningHoursObject> openingHoursResponseList = new ArrayList<>();
		List<OperationalStatusObject> operationalStatusOjectResponseList = new ArrayList<>();
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

			OperationalDetailsRepositoryImpl operationalDetailsRepositoryImpl = new OperationalDetailsRepositoryImpl();
			operationalDetailsRepositoryImpl.operationalDetailsRequest = operationalDetailsRequest;
			operationalDetailsRepositoryImpl.procedureCall = "{call "
					+ SupplierDataConstants.PROC_FETCH_OPERATIONAL_DETAILS + " (?,?,?,?)}";
			resultMap = jdbcTemplate.call(operationalDetailsRepositoryImpl, paramList);

			if (null != resultMap) {
				for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
					if ("#result-set-1".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> operationalMap = lst.get(i);

							operationalDetailsResponse.setSupplierId((Integer) operationalMap.get("Supplier_Id"));
							operationalDetailsResponse.setGroupId((Integer) operationalMap.get("Group_Id"));
							operationalDetailsResponse
									.setGroupShortDesc((String) operationalMap.get("Group_Short_Desc"));
							operationalDetailsResponse.setGroupDesc((String) operationalMap.get("Group_Desc"));

							OperationalAddressObject operationalAddressObject = new OperationalAddressObject();

							operationalAddressObject.setSubGroupId((Integer) operationalMap.get(SUB_GRP_ID));
							operationalAddressObject.setSubGroupDesc((String) operationalMap.get(SUB_GRP_DESC));
							operationalAddressObject.setItemId((Integer) operationalMap.get(ITEM_ID));
							operationalAddressObject.setItemShortDesc((String) operationalMap.get(ITEM_SHORT_DESC));
							operationalAddressObject.setItemDesc((String) operationalMap.get(ITEM_DESC));
							operationalAddressObject.setItemReq((String) operationalMap.get(ITEM_REQ));
							operationalAddressObject.setItemValType((String) operationalMap.get(ITEM_VAL_TYPE));
							operationalAddressObject.setItemOrder((Integer) operationalMap.get(ITEM_ORDER));
							operationalAddressObject.setItemDataType((String) operationalMap.get(ITEM_DATA_TYPE));
							operationalAddressObject.setItemModule((String) operationalMap.get(ITEM_MODULE));
							operationalAddressObject.setItemValue((String) operationalMap.get(ITEM_VALUE));
							operationalAddressObject.setIsEnabled((String) operationalMap.get(IS_ENABLED));
							operationalAddressObject.setValType((String) operationalMap.get(VAL_TYPE));
							operationalAddressResponseList.add(operationalAddressObject);

						}
					} else if ("#result-set-2".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> operationalMapMap = lst.get(i);
							MapCoordinatesObject mapCoordinatesObject = new MapCoordinatesObject();

							mapCoordinatesObject.setSubGroupId((Integer) operationalMapMap.get(SUB_GRP_ID));
							mapCoordinatesObject.setSubGroupDesc((String) operationalMapMap.get(SUB_GRP_DESC));
							mapCoordinatesObject.setItemId((Integer) operationalMapMap.get(ITEM_ID));
							mapCoordinatesObject.setItemShortDesc((String) operationalMapMap.get(ITEM_SHORT_DESC));
							mapCoordinatesObject.setItemDesc((String) operationalMapMap.get(ITEM_DESC));
							mapCoordinatesObject.setItemReq((String) operationalMapMap.get(ITEM_REQ));
							mapCoordinatesObject.setItemValType((String) operationalMapMap.get(ITEM_VAL_TYPE));
							mapCoordinatesObject.setItemOrder((Integer) operationalMapMap.get(ITEM_ORDER));
							mapCoordinatesObject.setItemDataType((String) operationalMapMap.get(ITEM_DATA_TYPE));
							mapCoordinatesObject.setItemModule((String) operationalMapMap.get(ITEM_MODULE));
							mapCoordinatesObject.setItemValue((String) operationalMapMap.get(ITEM_VALUE));
							mapCoordinatesObject.setIsEnabled((String) operationalMapMap.get(IS_ENABLED));
							mapCoordinatesObject.setValType((String) operationalMapMap.get(VAL_TYPE));
							operationalMapCoordinatesResponseList.add(mapCoordinatesObject);
						}
					} else if ("#result-set-3".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> paymentMap = lst.get(i);
							PaymentDetailsObject paymentDetailsObject = new PaymentDetailsObject();

							paymentDetailsObject.setSubGroupId((Integer) paymentMap.get(SUB_GRP_ID));
							paymentDetailsObject.setSubGroupDesc((String) paymentMap.get(SUB_GRP_DESC));
							paymentDetailsObject.setItemId((Integer) paymentMap.get(ITEM_ID));
							paymentDetailsObject.setItemShortDesc((String) paymentMap.get(ITEM_SHORT_DESC));
							paymentDetailsObject.setItemDesc((String) paymentMap.get(ITEM_DESC));
							paymentDetailsObject.setItemReq((String) paymentMap.get(ITEM_REQ));
							paymentDetailsObject.setItemValType((String) paymentMap.get(ITEM_VAL_TYPE));
							paymentDetailsObject.setItemOrder((Integer) paymentMap.get(ITEM_ORDER));
							paymentDetailsObject.setItemDataType((String) paymentMap.get(ITEM_DATA_TYPE));
							paymentDetailsObject.setItemModule((String) paymentMap.get(ITEM_MODULE));
							paymentDetailsObject.setItemValue((String) paymentMap.get(ITEM_VALUE));
							paymentDetailsObject.setIsEnabled((String) paymentMap.get(IS_ENABLED));
							paymentDetailsObject.setValType((String) paymentMap.get(VAL_TYPE));
							operationalPaymentResponseList.add(paymentDetailsObject);
						}
					} else if ("#result-set-4".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> operationalOtherMap = lst.get(i);
							OtherDetailsObject otherDetailsObject = new OtherDetailsObject();

							otherDetailsObject.setSubGroupId((Integer) operationalOtherMap.get(SUB_GRP_ID));
							otherDetailsObject.setSubGroupDesc((String) operationalOtherMap.get(SUB_GRP_DESC));
							otherDetailsObject.setItemId((Integer) operationalOtherMap.get(ITEM_ID));
							otherDetailsObject.setItemShortDesc((String) operationalOtherMap.get(ITEM_SHORT_DESC));
							otherDetailsObject.setItemDesc((String) operationalOtherMap.get(ITEM_DESC));
							otherDetailsObject.setItemReq((String) operationalOtherMap.get(ITEM_REQ));
							otherDetailsObject.setItemValType((String) operationalOtherMap.get(ITEM_VAL_TYPE));
							otherDetailsObject.setItemOrder((Integer) operationalOtherMap.get(ITEM_ORDER));
							otherDetailsObject.setItemDataType((String) operationalOtherMap.get(ITEM_DATA_TYPE));
							otherDetailsObject.setItemModule((String) operationalOtherMap.get(ITEM_MODULE));
							otherDetailsObject.setItemValue((String) operationalOtherMap.get(ITEM_VALUE));
							otherDetailsObject.setIsEnabled((String) operationalOtherMap.get(IS_ENABLED));
							otherDetailsObject.setValType((String) operationalOtherMap.get(VAL_TYPE));
							operationalOtherDetailsResponseList.add(otherDetailsObject);
						}
					} else if ("#result-set-5".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> opHoursMap = lst.get(i);
							OpeningHoursObject openingHoursObject = new OpeningHoursObject();

							openingHoursObject.setSubGroupId((Integer) opHoursMap.get(SUB_GRP_ID));
							openingHoursObject.setSubGroupDesc((String) opHoursMap.get(SUB_GRP_DESC));
							openingHoursObject.setItemId((Integer) opHoursMap.get(ITEM_ID));
							openingHoursObject.setItemDesc((String) opHoursMap.get(ITEM_DESC));

							openingHoursObject.setT0000((String) opHoursMap.get("T_00_00"));
							openingHoursObject.setT0030((String) opHoursMap.get("T_00_30"));
							openingHoursObject.setT0100((String) opHoursMap.get("T_01_00"));
							openingHoursObject.setT0130((String) opHoursMap.get("T_01_30"));
							openingHoursObject.setT0200((String) opHoursMap.get("T_02_00"));
							openingHoursObject.setT0230((String) opHoursMap.get("T_02_30"));
							openingHoursObject.setT0300((String) opHoursMap.get("T_03_00"));
							openingHoursObject.setT0330((String) opHoursMap.get("T_03_30"));
							openingHoursObject.setT0400((String) opHoursMap.get("T_04_00"));
							openingHoursObject.setT0430((String) opHoursMap.get("T_04_30"));
							openingHoursObject.setT0500((String) opHoursMap.get("T_05_00"));
							openingHoursObject.setT0530((String) opHoursMap.get("T_05_30"));
							openingHoursObject.setT0600((String) opHoursMap.get("T_06_00"));
							openingHoursObject.setT0630((String) opHoursMap.get("T_06_30"));
							openingHoursObject.setT0700((String) opHoursMap.get("T_07_00"));
							openingHoursObject.setT0730((String) opHoursMap.get("T_07_30"));
							openingHoursObject.setT0800((String) opHoursMap.get("T_08_00"));
							openingHoursObject.setT0830((String) opHoursMap.get("T_08_30"));
							openingHoursObject.setT0900((String) opHoursMap.get("T_09_00"));
							openingHoursObject.setT0930((String) opHoursMap.get("T_09_30"));
							openingHoursObject.setT1000((String) opHoursMap.get("T_10_00"));
							openingHoursObject.setT1030((String) opHoursMap.get("T_10_30"));
							openingHoursObject.setT1100((String) opHoursMap.get("T_11_00"));
							openingHoursObject.setT1130((String) opHoursMap.get("T_11_30"));
							openingHoursObject.setT1200((String) opHoursMap.get("T_12_00"));
							openingHoursObject.setT1230((String) opHoursMap.get("T_12_30"));

							openingHoursObject.setT1300((String) opHoursMap.get("T_13_00"));
							openingHoursObject.setT1330((String) opHoursMap.get("T_13_30"));
							openingHoursObject.setT1400((String) opHoursMap.get("T_14_00"));
							openingHoursObject.setT1430((String) opHoursMap.get("T_14_30"));
							openingHoursObject.setT1500((String) opHoursMap.get("T_15_00"));
							openingHoursObject.setT1530((String) opHoursMap.get("T_15_30"));
							openingHoursObject.setT1600((String) opHoursMap.get("T_16_00"));
							openingHoursObject.setT1630((String) opHoursMap.get("T_16_30"));
							openingHoursObject.setT1700((String) opHoursMap.get("T_17_00"));
							openingHoursObject.setT1730((String) opHoursMap.get("T_17_30"));
							openingHoursObject.setT1800((String) opHoursMap.get("T_18_00"));
							openingHoursObject.setT1830((String) opHoursMap.get("T_18_30"));
							openingHoursObject.setT1900((String) opHoursMap.get("T_19_00"));
							openingHoursObject.setT1930((String) opHoursMap.get("T_19_30"));
							openingHoursObject.setT2000((String) opHoursMap.get("T_20_00"));
							openingHoursObject.setT2030((String) opHoursMap.get("T_20_30"));
							openingHoursObject.setT2100((String) opHoursMap.get("T_21_00"));
							openingHoursObject.setT2130((String) opHoursMap.get("T_21_30"));
							openingHoursObject.setT2200((String) opHoursMap.get("T_22_00"));
							openingHoursObject.setT2230((String) opHoursMap.get("T_22_30"));
							openingHoursObject.setT2300((String) opHoursMap.get("T_23_00"));
							openingHoursObject.setT2330((String) opHoursMap.get("T_23_30"));

							openingHoursObject.setRoadsideCapBusInd((String) opHoursMap.get("Roadside_Cap_Bus_Ind"));
							openingHoursObject
									.setRoadsideCapNonBusInd((String) opHoursMap.get("Roadside_Cap_Non_Bus_Ind"));
							openingHoursObject.setEscortAvailBusInd((String) opHoursMap.get("Escort_Avail_Bus_Ind"));
							openingHoursObject.setEscortAvailCapNonBusInd(
									(String) opHoursMap.get("Escort_Avail_Cap_Non_Bus_Ind"));
							openingHoursObject.setStopGoBusInd((String) opHoursMap.get("Stop_Go_Bus_Ind"));
							openingHoursObject
									.setStopGoCapNonBusInd((String) opHoursMap.get("Stop_Go_Cap_Non_Bus_Ind"));
							openingHoursObject.setIsEnabled((String) opHoursMap.get("Is_Enabled"));

							openingHoursResponseList.add(openingHoursObject);
						}
					} else if ("#result-set-6".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> status = lst.get(i);
							OperationalStatusObject operationalStatusObject = new OperationalStatusObject();
							operationalStatusObject.setSupplierId((Integer) status.get("Supplier_Id"));
							operationalStatusObject.setErrorCd((String) status.get("Error_Cd"));
							operationalStatusObject.setStatusId((Integer) status.get("Status_Id"));
							operationalStatusOjectResponseList.add(operationalStatusObject);
						}
					}
				}
			}
			operationalDetailsResponse.getOperationalAddressDetailsList().addAll(operationalAddressResponseList);
			operationalDetailsResponse.getLocationMapCoordinatesList().addAll(operationalMapCoordinatesResponseList);
			operationalDetailsResponse.getPaymentSupplierDetailsList().addAll(operationalPaymentResponseList);
			operationalDetailsResponse.getOtherDetailsList().addAll(operationalOtherDetailsResponseList);
			operationalDetailsResponse.getOpeningHoursList().addAll(openingHoursResponseList);
			operationalDetailsResponse.getSupplierOperationalStatusList().addAll(operationalStatusOjectResponseList);

		} catch (Exception e) {
			logger.error("An error occurred while fetching supplier operational details: " + e);
		}
		return operationalDetailsResponse;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		DatatypeCommonUtility.checkNull(1, callableStatement, operationalDetailsRequest.getSupplierId());
		DatatypeCommonUtility.checkNull(2, callableStatement, operationalDetailsRequest.getLangId());
		callableStatement.setString(3, operationalDetailsRequest.getSsoId());
		callableStatement.registerOutParameter(4, Types.VARCHAR);
		connection.setAutoCommit(true);
		return callableStatement;
	}
}
