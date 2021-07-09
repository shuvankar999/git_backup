package com.tip.supplier.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import com.tip.supplier.model.FeeCardDetailsRequest;

public class FeeCardDetailsRepositoryImplTest {

	@Mock
	private JdbcTemplate jdbcTemplate;

	@Mock
	DataSource dataSource;

	@Mock
	private Connection connection;

	@Mock
	private CallableStatement mockCallableStatement;

	FeeCardDetailsRepositoryImpl feeCardDetailsRepositoryImpl;

	@Before
	public void beforesetup() {
		feeCardDetailsRepositoryImpl = new FeeCardDetailsRepositoryImpl();
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(feeCardDetailsRepositoryImpl, "jdbcTemplate", jdbcTemplate);
	}

	@Test
	public void feeCard() {
		try {
			FeeCardDetailsRequest feeCardDetailsRequest = new FeeCardDetailsRequest();
			Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
			Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenReturn(connection);
			feeCardDetailsRepositoryImpl.fetchFeeCardDetails(feeCardDetailsRequest);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			Map<String, Object> resultMap1 = new HashMap<String, Object>();
			Map<String, Object> map = new HashMap<String, Object>();
			List<Map<String, Object>> lst = new ArrayList<>();
			lst.add(map);
			resultMap.put("#result-set-1", lst);
			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenReturn(resultMap);
			feeCardDetailsRepositoryImpl.fetchFeeCardDetails(feeCardDetailsRequest);

			resultMap1.put("#result-set-1", lst);
			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenReturn(resultMap1);

			feeCardDetailsRepositoryImpl.fetchFeeCardDetails(feeCardDetailsRequest);
			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenReturn(null);
			feeCardDetailsRepositoryImpl.fetchFeeCardDetails(feeCardDetailsRequest);

			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenThrow(new IncorrectResultSizeDataAccessException(1));
			feeCardDetailsRepositoryImpl.fetchFeeCardDetails(feeCardDetailsRequest);
		} catch (SQLException e) {
		}
	}

	@Test
	public void createCallableStatement() throws SQLException {
		Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
		FeeCardDetailsRequest feeCardDetailsRequest = new FeeCardDetailsRequest();

		feeCardDetailsRequest.setSupplierId(5);
		feeCardDetailsRequest.setLangId(5);
		feeCardDetailsRequest.setSsoId("5646521");
		feeCardDetailsRequest.setErrorCd("abc");

		feeCardDetailsRepositoryImpl.setFeeCardDetailsRequest(feeCardDetailsRequest);

		feeCardDetailsRepositoryImpl.fetchFeeCardDetails(feeCardDetailsRequest);

		try {
			Mockito.when(connection.prepareCall(null)).thenReturn(mockCallableStatement);

			feeCardDetailsRepositoryImpl.fetchFeeCardDetails(feeCardDetailsRequest);
			feeCardDetailsRepositoryImpl.createCallableStatement(connection);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
