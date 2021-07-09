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
import com.tip.supplier.model.AdditionalCapabilitiesDataObject;
import com.tip.supplier.model.AllCapabilitiesRequest;
import com.tip.supplier.model.AllCapabilitiesResponse;
import com.tip.supplier.model.AuxiliaryEquipmentDataObject;
import com.tip.supplier.model.CapabilitiesStatusObject;
import com.tip.supplier.model.EquipmentDataObject;
import com.tip.supplier.model.InspectionCapabilitiesObject;
import com.tip.supplier.model.RepairBuilderCapabilitiesObject;
import com.tip.supplier.repository.AllCapabilitiesRepository;

@Repository
public class AllCapabilitiesRepositoryImpl implements AllCapabilitiesRepository, CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(AllCapabilitiesRepositoryImpl.class);

	public static final String SUPPLIER_ID = "Supplier_Id";
	public static final String SUB_GRP_ID = "Sub_Group_Id";
	public static final String GRP_ID = "Group_Id";
	public static final String GRP_DESC = "Group_Desc";
	public static final String SUB_GRP_DESC = "Sub_Group_Desc";
	public static final String ITEM_ID = "Item_Id";
	public static final String ITEM_DESC = "Item_Desc";
	public static final String IS_ENABLED = "Is_Enabled";
	public static final String VAL_TYPE = "Val_Type";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;

	private AllCapabilitiesRequest allCapabilitiesRequest;

	public void setAllCapabilitiesRequest(AllCapabilitiesRequest allCapabilitiesRequest) {
		this.allCapabilitiesRequest = allCapabilitiesRequest;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public AllCapabilitiesResponse fetchAllCapabilities(AllCapabilitiesRequest allCapabilitiesRequest) {
		AllCapabilitiesResponse allCapabilitiesResponse = new AllCapabilitiesResponse();
		List<EquipmentDataObject> equipmentDataResponseList = new ArrayList<>();
		List<AuxiliaryEquipmentDataObject> auxiliaryEquipmentDataResponseList = new ArrayList<>();
		List<AdditionalCapabilitiesDataObject> additionalCapabilitiesResponseList = new ArrayList<>();
		List<InspectionCapabilitiesObject> inspectionCapabilitiesResponseList = new ArrayList<>();
		List<RepairBuilderCapabilitiesObject> repairBuilderCapabilitiesResponseList = new ArrayList<>();
		List<CapabilitiesStatusObject> capabilitiesStatusResponseList = new ArrayList<>();
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

			AllCapabilitiesRepositoryImpl operationalDetailsRepositoryImpl = new AllCapabilitiesRepositoryImpl();
			operationalDetailsRepositoryImpl.allCapabilitiesRequest = allCapabilitiesRequest;
			operationalDetailsRepositoryImpl.procedureCall = "{call "
					+ SupplierDataConstants.PROC_FETCH_ALL_CAPABILITIES + " (?,?,?,?)}";
			resultMap = jdbcTemplate.call(operationalDetailsRepositoryImpl, paramList);

			if (null != resultMap) {
				for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
					if ("#result-set-1".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> equipmentMap = lst.get(i);

							EquipmentDataObject equipmentDataObject = new EquipmentDataObject();
							equipmentDataObject.setSupplierId((Integer) equipmentMap.get(SUPPLIER_ID));
							equipmentDataObject.setGroupId((Integer) equipmentMap.get(GRP_ID));
							equipmentDataObject.setGroupDesc((String) equipmentMap.get(GRP_DESC));
							equipmentDataObject.setSubGroupId((Integer) equipmentMap.get(SUB_GRP_ID));
							equipmentDataObject.setSubGroupDesc((String) equipmentMap.get(SUB_GRP_DESC));
							equipmentDataObject.setItemId((Integer) equipmentMap.get(ITEM_ID));
							equipmentDataObject.setItemDesc((String) equipmentMap.get(ITEM_DESC));
							equipmentDataObject.setEquipInd((String) equipmentMap.get("Equip_Ind"));
							equipmentDataObject.setRepairMaintInd((String) equipmentMap.get("Repair_Maint_Ind"));
							equipmentDataObject.setSparePartsInd((String) equipmentMap.get("Spare_Parts_Ind"));
							equipmentDataObject.setMsuInd((String) equipmentMap.get("MSU_Ind"));
							equipmentDataObject.setIsEnabled((String) equipmentMap.get(IS_ENABLED));

							equipmentDataResponseList.add(equipmentDataObject);

						}
					} else if ("#result-set-2".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> auxEquipMap = lst.get(i);
							AuxiliaryEquipmentDataObject auxiliaryEquipmentDataObject = new AuxiliaryEquipmentDataObject();
							auxiliaryEquipmentDataObject.setSupplierId((Integer) auxEquipMap.get(SUPPLIER_ID));
							auxiliaryEquipmentDataObject.setGroupId((Integer) auxEquipMap.get(GRP_ID));
							auxiliaryEquipmentDataObject.setGroupDesc((String) auxEquipMap.get(GRP_DESC));
							auxiliaryEquipmentDataObject.setSubGroupId((Integer) auxEquipMap.get(SUB_GRP_ID));
							auxiliaryEquipmentDataObject.setSubGroupDesc((String) auxEquipMap.get(SUB_GRP_DESC));
							auxiliaryEquipmentDataObject.setItemId((Integer) auxEquipMap.get(ITEM_ID));
							auxiliaryEquipmentDataObject.setItemDesc((String) auxEquipMap.get(ITEM_DESC));
							auxiliaryEquipmentDataObject.setAuxEquipInd((String) auxEquipMap.get("Aux_Equip_Ind"));
							auxiliaryEquipmentDataObject
									.setRepairMaintInd((String) auxEquipMap.get("Repair_Maint_Ind"));
							auxiliaryEquipmentDataObject.setSparePartsInd((String) auxEquipMap.get("Spare_Parts_Ind"));
							auxiliaryEquipmentDataObject.setMsuInd((String) auxEquipMap.get("MSU_Ind"));
							auxiliaryEquipmentDataObject.setOutsourceInd((String) auxEquipMap.get("Outsource_Ind"));
							auxiliaryEquipmentDataObject.setIsEnabled((String) auxEquipMap.get(IS_ENABLED));
							auxiliaryEquipmentDataResponseList.add(auxiliaryEquipmentDataObject);
						}
					} else if ("#result-set-3".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> additionalCapMap = lst.get(i);
							AdditionalCapabilitiesDataObject additionalCapabilitiesDataObject = new AdditionalCapabilitiesDataObject();
							additionalCapabilitiesDataObject.setSupplierId((Integer) additionalCapMap.get(SUPPLIER_ID));
							additionalCapabilitiesDataObject.setGroupId((Integer) additionalCapMap.get(GRP_ID));
							additionalCapabilitiesDataObject.setGroupDesc((String) additionalCapMap.get(GRP_DESC));
							additionalCapabilitiesDataObject.setSubGroupId((Integer) additionalCapMap.get(SUB_GRP_ID));
							additionalCapabilitiesDataObject
									.setSubGroupDesc((String) additionalCapMap.get(SUB_GRP_DESC));
							additionalCapabilitiesDataObject.setItemId((Integer) additionalCapMap.get(ITEM_ID));
							additionalCapabilitiesDataObject
									.setItemGroupDesc((String) additionalCapMap.get("Item_Group_Desc"));
							additionalCapabilitiesDataObject.setItemDesc((String) additionalCapMap.get(ITEM_DESC));
							additionalCapabilitiesDataObject
									.setAddlCapInd((String) additionalCapMap.get("Addl_Cap_Ind"));
							additionalCapabilitiesDataObject.setIsEnabled((String) additionalCapMap.get(IS_ENABLED));
							additionalCapabilitiesResponseList.add(additionalCapabilitiesDataObject);
						}
					} else if ("#result-set-4".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> inspectionMap = lst.get(i);
							InspectionCapabilitiesObject inspectionCapabilitiesObject = new InspectionCapabilitiesObject();
							inspectionCapabilitiesObject.setSupplierId((Integer) inspectionMap.get(SUPPLIER_ID));
							inspectionCapabilitiesObject.setGroupId((Integer) inspectionMap.get(GRP_ID));
							inspectionCapabilitiesObject.setSubGroupId((Integer) inspectionMap.get(SUB_GRP_ID));
							inspectionCapabilitiesObject.setItemId((Integer) inspectionMap.get(ITEM_ID));
							inspectionCapabilitiesObject.setDnaCd((String) inspectionMap.get("DNA_Cd"));
							inspectionCapabilitiesObject.setGroups((String) inspectionMap.get("groups"));
							inspectionCapabilitiesObject.setSubGroup((String) inspectionMap.get("subGroup"));
							inspectionCapabilitiesObject.setActivity((String) inspectionMap.get("activity"));
							inspectionCapabilitiesObject.setItemDesc((String) inspectionMap.get("Item_Desc"));
							inspectionCapabilitiesObject.setInspCapInd((String) inspectionMap.get("Insp_Cap_Ind"));
							inspectionCapabilitiesObject.setIsEnabled((String) inspectionMap.get(IS_ENABLED));

							inspectionCapabilitiesResponseList.add(inspectionCapabilitiesObject);
						}
					} else if ("#result-set-5".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> repairBuilderMap = lst.get(i);
							RepairBuilderCapabilitiesObject repairBuilderCapabilitiesObject = new RepairBuilderCapabilitiesObject();
							repairBuilderCapabilitiesObject.setSupplierId((Integer) repairBuilderMap.get(SUPPLIER_ID));
							repairBuilderCapabilitiesObject.setGroupId((Integer) repairBuilderMap.get(GRP_ID));
							repairBuilderCapabilitiesObject.setSubGroupId((Integer) repairBuilderMap.get(SUB_GRP_ID));
							repairBuilderCapabilitiesObject.setItemId((Integer) repairBuilderMap.get(ITEM_ID));
							repairBuilderCapabilitiesObject.setDnaCd((String) repairBuilderMap.get("DNA_Cd"));
							repairBuilderCapabilitiesObject.setGroups((String) repairBuilderMap.get("groups"));
							repairBuilderCapabilitiesObject.setSubGroup((String) repairBuilderMap.get("subGroup"));
							repairBuilderCapabilitiesObject.setActivity((String) repairBuilderMap.get("activity"));
							repairBuilderCapabilitiesObject.setItemDesc((String) repairBuilderMap.get("Item_Desc"));
							repairBuilderCapabilitiesObject.setCapInd((String) repairBuilderMap.get("Cap_Ind"));
							repairBuilderCapabilitiesObject.setIsEnabled((String) repairBuilderMap.get(IS_ENABLED));
							repairBuilderCapabilitiesResponseList.add(repairBuilderCapabilitiesObject);
						}
					} else if ("#result-set-6".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> statusMap = lst.get(i);
							CapabilitiesStatusObject capabilitiesStatusObject = new CapabilitiesStatusObject();
							capabilitiesStatusObject.setSupplierId((Integer) statusMap.get("Supplier_Id"));
							capabilitiesStatusObject.setErrorCd((String) statusMap.get("Error_Cd"));
							capabilitiesStatusObject.setStatusId((Integer) statusMap.get("Status_Id"));
							capabilitiesStatusResponseList.add(capabilitiesStatusObject);
						}
					}
				}
			}
			allCapabilitiesResponse.getEquipmentDataResponseList().addAll(equipmentDataResponseList);
			allCapabilitiesResponse.getAuxiliaryEquipmentDataResponseList().addAll(auxiliaryEquipmentDataResponseList);
			allCapabilitiesResponse.getAdditionalCapabilitiesDataResponseList()
					.addAll(additionalCapabilitiesResponseList);
			allCapabilitiesResponse.getInspectionCapabilitiesDataResponseList()
					.addAll(inspectionCapabilitiesResponseList);
			allCapabilitiesResponse.getRepairBuilderCapabilitiesDataResponseList()
					.addAll(repairBuilderCapabilitiesResponseList);
			allCapabilitiesResponse.getCapabilitiesStatusResponseList().addAll(capabilitiesStatusResponseList);

		} catch (Exception e) {
			logger.error("An error occurred while fetching supplier all capabilities details: " + e);
		}
		return allCapabilitiesResponse;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		DatatypeCommonUtility.checkNull(1, callableStatement, allCapabilitiesRequest.getSupplierId());
		DatatypeCommonUtility.checkNull(2, callableStatement, allCapabilitiesRequest.getLangId());
		callableStatement.setString(3, allCapabilitiesRequest.getSsoId());
		callableStatement.registerOutParameter(4, Types.VARCHAR);
		connection.setAutoCommit(true);
		return callableStatement;
	}
}
