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
import com.tip.supplier.model.MsuCapabilitiesObject;
import com.tip.supplier.model.MsuDataObject;
import com.tip.supplier.model.MsuDataRequest;
import com.tip.supplier.model.MsuDataResponse;
import com.tip.supplier.model.MsuStatusObject;
import com.tip.supplier.repository.MsuDataRepository;

@Repository
public class MsuDataRepositoryImpl implements MsuDataRepository, CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(MsuDataRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;

	private MsuDataRequest msuDataRequest;

	public void setMsuDataRequest(MsuDataRequest msuDataRequest) {
		this.msuDataRequest = msuDataRequest;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public MsuDataResponse fetchMsuData(MsuDataRequest msuDataRequest) {
		MsuDataResponse msuDataResponse = new MsuDataResponse();

		List<MsuDataObject> msuDataResponseList = new ArrayList<>();
		List<MsuCapabilitiesObject> msuCapabilitiesResponseList = new ArrayList<>();
		List<MsuStatusObject> msuStatusResponseList = new ArrayList<>();

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

			MsuDataRepositoryImpl msuDataRepositoryImpl = new MsuDataRepositoryImpl();
			msuDataRepositoryImpl.msuDataRequest = msuDataRequest;
			msuDataRepositoryImpl.procedureCall = "{call " + SupplierDataConstants.PROC_FETCH_SUPPLIER_MSU_DATA
					+ " (?,?,?,?)}";
			resultMap = jdbcTemplate.call(msuDataRepositoryImpl, paramList);

			if (null != resultMap) {
				for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
					if ("#result-set-1".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> msuDataMap = lst.get(i);
							MsuDataObject msuDataObject = new MsuDataObject();
							msuDataObject.setSupplierId((Integer) msuDataMap.get("Supplier_Id"));
							msuDataObject.setMsuSeqId((Integer) msuDataMap.get("MSU_Seq_Id"));
							msuDataObject.setGroupId((Integer) msuDataMap.get("Group_Id"));
							msuDataObject.setGroupDesc((String) msuDataMap.get("Group_Desc"));
							msuDataObject.setSubGroupId((Integer) msuDataMap.get("Sub_Group_Id"));
							msuDataObject.setSubGroupDesc((String) msuDataMap.get("Sub_Group_Desc"));
							msuDataObject.setItemId((Integer) msuDataMap.get("Item_Id"));
							msuDataObject.setItemDesc((String) msuDataMap.get("Item_Desc"));
							msuDataObject.setRegNr((String) msuDataMap.get("Reg_Nr"));
							msuDataObject.setFleetNr((String) msuDataMap.get("Fleet_Nr"));
							msuDataObject.setDirectContactInd((String) msuDataMap.get("Direct_Contact_Ind"));
							msuDataObject.setDialingCd((String) msuDataMap.get("Dialing_Cd"));
							msuDataObject.setPhoneNr((String) msuDataMap.get("Phone_Nr"));
							msuDataObject.setBusHrCovRad((BigDecimal) msuDataMap.get("Bus_Hr_Cov_Rad"));
							msuDataObject.setNonBusHrCovRad((BigDecimal) msuDataMap.get("Non_Bus_Hr_Cov_Rad"));
							msuDataObject.setBusHrArea((String) msuDataMap.get("Bus_Hr_Area"));
							msuDataObject.setOpsArea((String) msuDataMap.get("Ops_Area"));
							msuDataObject.setIsActive((String) msuDataMap.get("Is_Active"));
							msuDataObject.setIsEnabled((String) msuDataMap.get("Is_Enabled"));
							msuDataResponseList.add(msuDataObject);
						}
					} else if ("#result-set-2".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> capabilitiesMap = lst.get(i);
							MsuCapabilitiesObject msuCapabilitiesObject = new MsuCapabilitiesObject();

							msuCapabilitiesObject.setSupplierId((Integer) capabilitiesMap.get("Supplier_Id"));
							msuCapabilitiesObject.setMsuSeqId((Integer) capabilitiesMap.get("MSU_Seq_Id"));
							msuCapabilitiesObject.setGroupId((Integer) capabilitiesMap.get("Group_Id"));
							msuCapabilitiesObject.setGroupDesc((String) capabilitiesMap.get("Group_Desc"));
							msuCapabilitiesObject.setSubGroupId((Integer) capabilitiesMap.get("Sub_Group_Id"));
							msuCapabilitiesObject.setSubGroupDesc((String) capabilitiesMap.get("Sub_Group_Desc"));

							msuCapabilitiesObject.setCapabilityId((Integer) capabilitiesMap.get("Capability_Id"));
							msuCapabilitiesObject.setItemDesc((String) capabilitiesMap.get("Item_Desc"));
							msuCapabilitiesObject.setCapInd((String) capabilitiesMap.get("Cap_Ind"));
							msuCapabilitiesObject.setIsEnabled((String) capabilitiesMap.get("Is_Enabled"));

							msuCapabilitiesResponseList.add(msuCapabilitiesObject);
						}
					} else if ("#result-set-3".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> statusMap = lst.get(i);
							MsuStatusObject msuStatusObject = new MsuStatusObject();
							msuStatusObject.setSupplierId((Integer) statusMap.get("Supplier_Id"));
							msuStatusObject.setErrorCd((String) statusMap.get("Error_Cd"));
							msuStatusObject.setStatusId((Integer) statusMap.get("Status_Id"));

							msuStatusResponseList.add(msuStatusObject);
						}
					}
				}
			}

			msuDataResponse.getMsuDataResponseList().addAll(msuDataResponseList);
			msuDataResponse.getMsuCapabilitiesResponseList().addAll(msuCapabilitiesResponseList);
			msuDataResponse.getMsuStatusResponseList().addAll(msuStatusResponseList);

		} catch (Exception e) {
			logger.error("An error occurred while fetching supplier MSU details: " + e);
		}
		return msuDataResponse;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		DatatypeCommonUtility.checkNull(1, callableStatement, msuDataRequest.getSupplierId());
		DatatypeCommonUtility.checkNull(2, callableStatement, msuDataRequest.getLangId());
		callableStatement.setString(3, msuDataRequest.getSsoId());
		callableStatement.setString(4, msuDataRequest.getErrorCd());
		connection.setAutoCommit(true);
		return callableStatement;
	}
}
