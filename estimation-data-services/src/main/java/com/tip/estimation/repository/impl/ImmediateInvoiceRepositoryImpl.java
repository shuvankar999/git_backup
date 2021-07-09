package com.tip.estimation.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.tip.estimation.model.InvoiceRequest;
import com.tip.estimation.repository.ImmediateInvoiceRepository;
import com.tip.util.DatatypeCommonUtility;
import com.tip.util.EstimationConstant;

@Repository
public class ImmediateInvoiceRepositoryImpl implements ImmediateInvoiceRepository, CallableStatementCreator {

	static final Logger logger = LoggerFactory.getLogger(ImmediateInvoiceRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	private InvoiceRequest invoiceRequest;

	public void setInvoiceRequest(InvoiceRequest invoiceRequest) {
		this.invoiceRequest = invoiceRequest;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Object> getInvoiceData(InvoiceRequest invoiceRequest) {

		Map<String, Object> resultMap = null;
		Map<String, Object> responseMap = new HashMap();;
		
		try {

			SqlParameter estimationIdSqlParam = new SqlParameter(Types.DECIMAL);
			SqlParameter langIdSqlParam = new SqlParameter(Types.INTEGER);

			List paramList = new ArrayList();
			paramList.add(estimationIdSqlParam);
			paramList.add(langIdSqlParam);

			ImmediateInvoiceRepositoryImpl immediateInvoiceRepositoryImpl = new ImmediateInvoiceRepositoryImpl();
			immediateInvoiceRepositoryImpl.invoiceRequest = invoiceRequest;
			immediateInvoiceRepositoryImpl.procedureCall = "{call " + EstimationConstant.PROC_ESTIMATION_INVOICE_IMMEDIATE
					+ "(?,?)}";
			resultMap = jdbcTemplate.call((CallableStatementCreator) immediateInvoiceRepositoryImpl, paramList);
			List<Map<String, Object>> lst;
			for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
				if (("#result-set-1").equalsIgnoreCase(entry.getKey())) {
					lst = (List<Map<String, Object>>) entry.getValue();
					responseMap.put("HeaderList", lst);
				} else if (("#result-set-2").equalsIgnoreCase(entry.getKey())) {
					lst = (List<Map<String, Object>>) entry.getValue();
					responseMap.put("WoList", lst);
				} else if (("#result-set-3").equalsIgnoreCase(entry.getKey())) {
					lst = (List<Map<String, Object>>) entry.getValue();
					responseMap.put("WoTaskList", lst);
				}else if (("#result-set-4").equalsIgnoreCase(entry.getKey())) {
					lst = (List<Map<String, Object>>) entry.getValue();
					responseMap.put("PartList", lst);
				} else if (("#result-set-5").equalsIgnoreCase(entry.getKey())) {
					lst = (List<Map<String, Object>>) entry.getValue();
					responseMap.put("TyreList", lst);
				} else if (("#result-set-6").equalsIgnoreCase(entry.getKey())) {
					lst = (List<Map<String, Object>>) entry.getValue();
					responseMap.put("TyreServicesList", lst);
				} else if (("#result-set-7").equalsIgnoreCase(entry.getKey())) {
					lst = (List<Map<String, Object>>) entry.getValue();
					responseMap.put("BundleList", lst);
				} else if (("#result-set-8").equalsIgnoreCase(entry.getKey())) {
					lst = (List<Map<String, Object>>) entry.getValue();
					responseMap.put("ConsumableList", lst);
				} else if (("#result-set-9").equalsIgnoreCase(entry.getKey())) {
					lst = (List<Map<String, Object>>) entry.getValue();
					responseMap.put("PartListAdditional", lst);
				} else if (("#result-set-10").equalsIgnoreCase(entry.getKey())) {
					lst = (List<Map<String, Object>>) entry.getValue();
					responseMap.put("AdditionalList", lst);
				}
			}

		} catch (Exception e) {
			logger.error("An error occurred: " + e);
		}

		return responseMap;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setBigDecimal(1, invoiceRequest.getEstimationId());
		DatatypeCommonUtility.checkNull(2, callableStatement, invoiceRequest.getLangId());
		connection.setAutoCommit(true);
		return callableStatement;
	}
}
