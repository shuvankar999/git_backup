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
import com.tip.supplier.model.OtherFeeObject;
import com.tip.supplier.model.OtherFeesCardRequest;
import com.tip.supplier.model.OtherFeesCardResponse;
import com.tip.supplier.repository.OtherFeesCardRepository;

@Repository
public class OtherFeesCardRepositoryImpl implements OtherFeesCardRepository, CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(OtherFeesCardRepositoryImpl.class);

	@Autowired	
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;

	private OtherFeesCardRequest otherFeesCardRequest;
	
	public void setOtherFeesCardRequest(OtherFeesCardRequest otherFeesCardRequest) {
		this.otherFeesCardRequest = otherFeesCardRequest;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public OtherFeesCardResponse fetchOtherFeesCard(OtherFeesCardRequest otherFeesCardRequest) {
		OtherFeesCardResponse otherFeesCardResponse = new OtherFeesCardResponse();
	
		List<OtherFeeObject> otherFeeCardResponseList = new ArrayList<>();
	
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

			OtherFeesCardRepositoryImpl otherFeesCardRepositoryImpl = new OtherFeesCardRepositoryImpl();
			otherFeesCardRepositoryImpl.otherFeesCardRequest = otherFeesCardRequest;
			otherFeesCardRepositoryImpl.procedureCall = "{call " + SupplierDataConstants.PROC_FETCH_OTHER_FEES_CARD
					+ " (?,?,?,?)}";
			resultMap = jdbcTemplate.call(otherFeesCardRepositoryImpl, paramList);

			if (null != resultMap) {
				for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
					if ("#result-set-1".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> otherFeeMap = lst.get(i);
							OtherFeeObject otherFeeObject = new OtherFeeObject();
							otherFeeObject.setSupplierId((Integer)otherFeeMap.get("Supplier_Id"));
							otherFeeObject.setFeeGroupId((Integer)otherFeeMap.get("Fee_Group_Id"));
							otherFeeObject.setGroupDesc((String)otherFeeMap.get("Group_Desc"));
							otherFeeObject.setFeeSubGroupId((Integer)otherFeeMap.get("Fee_Sub_Group_Id"));
							otherFeeObject.setSubGroupDesc((String)otherFeeMap.get("Sub_Group_Desc"));
							otherFeeObject.setFeeItemId((Integer)otherFeeMap.get("Fee_Item_Id"));
							
							otherFeeObject.setSeqId((Integer)otherFeeMap.get("Seq_Id"));
							otherFeeObject.setItemDesc((String)otherFeeMap.get("Item_Desc"));
							otherFeeObject.setFeeDesc((String)otherFeeMap.get("Fee_Desc"));
							if(otherFeeMap.get("Fee") != null) {
								BigDecimal bd = (BigDecimal)otherFeeMap.get("Fee");
							    bd = bd.setScale(2, RoundingMode.DOWN);
							    otherFeeObject.setFee(bd.toString());
							}
							otherFeeObject.setIsActive((String)otherFeeMap.get("Is_Active"));
							otherFeeObject.setIsEnabled((String)otherFeeMap.get("Is_Enabled"));
								
							otherFeeCardResponseList.add(otherFeeObject);
						}
					}  
				}
			}
			
			otherFeesCardResponse.getOtherFeeCardList().addAll(otherFeeCardResponseList);
			
		} catch (Exception e) {
			logger.error("An error occurred while fetching supplier other fee card details: " + e);
		}
		return otherFeesCardResponse;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		DatatypeCommonUtility.checkNull(1, callableStatement, otherFeesCardRequest.getSupplierId());
		DatatypeCommonUtility.checkNull(2, callableStatement, otherFeesCardRequest.getLangId());
		callableStatement.setString(3, otherFeesCardRequest.getSsoId());
		callableStatement.setString(4, otherFeesCardRequest.getErrorCd());
		connection.setAutoCommit(true);
		return callableStatement;
	}
}
