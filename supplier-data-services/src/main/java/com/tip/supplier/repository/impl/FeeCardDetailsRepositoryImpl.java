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
import com.tip.supplier.model.FeeCardDetailsRequest;
import com.tip.supplier.model.FeeCardDetailsResponse;
import com.tip.supplier.model.FeeDetailsObject;
import com.tip.supplier.repository.FeeCardDetailsRepository;

@Repository
public class FeeCardDetailsRepositoryImpl implements FeeCardDetailsRepository, CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(FeeCardDetailsRepositoryImpl.class);

	@Autowired	
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;

	private FeeCardDetailsRequest feeCardDetailsRequest;

	public void setFeeCardDetailsRequest(FeeCardDetailsRequest feeCardDetailsRequest) {
		this.feeCardDetailsRequest = feeCardDetailsRequest;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public FeeCardDetailsResponse fetchFeeCardDetails(FeeCardDetailsRequest feeCardDetailsRequest) {
		FeeCardDetailsResponse feeCardDetailsResponse = new FeeCardDetailsResponse();
	
		List<FeeDetailsObject> feeDetailsResponseList = new ArrayList<>();
	
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

			FeeCardDetailsRepositoryImpl feeCardDetailsRepositoryImpl = new FeeCardDetailsRepositoryImpl();
			feeCardDetailsRepositoryImpl.feeCardDetailsRequest = feeCardDetailsRequest;
			feeCardDetailsRepositoryImpl.procedureCall = "{call " + SupplierDataConstants.PROC_FEE_CARD_DETAILS
					+ " (?,?,?,?)}";
			resultMap = jdbcTemplate.call(feeCardDetailsRepositoryImpl, paramList);

			if (null != resultMap) {
				for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
					if ("#result-set-1".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> lCardsCountMap = lst.get(i);
							FeeDetailsObject feeDetailsObject = new FeeDetailsObject();
							feeDetailsObject.setSupplierId((Integer)lCardsCountMap.get("supplierId"));
							feeDetailsObject.setFeeGroupId((Integer)lCardsCountMap.get("feeGroupId"));
							feeDetailsObject.setFeeSubgrpId((Integer)lCardsCountMap.get("feeSubgrpId"));
							feeDetailsObject.setFeeItemId((Integer)lCardsCountMap.get("feeItemId"));
							feeDetailsObject.setDnaCode((String)lCardsCountMap.get("dnaCode"));
							feeDetailsObject.setGroups((String)lCardsCountMap.get("groups"));
							feeDetailsObject.setSubGroup((String)lCardsCountMap.get("subGroup"));
							feeDetailsObject.setActivity((String)lCardsCountMap.get("activity"));
							
							feeDetailsObject.setMaintActionCd((String)lCardsCountMap.get("maintActionCd"));
							feeDetailsObject.setActionDesc((String)lCardsCountMap.get("actionDesc"));
							if(lCardsCountMap.get("fee") != null) {
								BigDecimal bd = (BigDecimal)lCardsCountMap.get("fee");
							    bd = bd.setScale(2, RoundingMode.DOWN);
							    feeDetailsObject.setFee(bd.toString());
							}
							feeDetailsObject.setIsActive((String)lCardsCountMap.get("isActive"));
							feeDetailsObject.setIsEnabled((String)lCardsCountMap.get("isEnabled"));
								
							feeDetailsResponseList.add(feeDetailsObject);
						}
					}  
				}
			}
			feeCardDetailsResponse.getSupplierFeeDetailsList().addAll(feeDetailsResponseList);
		} catch (Exception e) {
			logger.error("An error occurred while fetching supplier fees card details: " + e);
		}
		return feeCardDetailsResponse;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		DatatypeCommonUtility.checkNull(1, callableStatement, feeCardDetailsRequest.getSupplierId());
		DatatypeCommonUtility.checkNull(2, callableStatement, feeCardDetailsRequest.getLangId());
		callableStatement.setString(3, feeCardDetailsRequest.getSsoId());
		callableStatement.setString(4, feeCardDetailsRequest.getErrorCd());
		connection.setAutoCommit(true);
		return callableStatement;
	}
}
