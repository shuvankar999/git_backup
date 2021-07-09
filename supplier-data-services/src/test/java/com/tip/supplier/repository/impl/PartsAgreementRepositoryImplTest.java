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

import com.tip.supplier.model.PartsAgreementRequest;

public class PartsAgreementRepositoryImplTest {

	@Mock
	private JdbcTemplate jdbcTemplate;

	@Mock
	DataSource dataSource;

	@Mock
	private Connection connection;

	@Mock
	private CallableStatement mockCallableStatement;

	PartsAgreementRepositoryImpl partsAgreementRepositoryImpl;

	@Before
	public void beforesetup() {
		partsAgreementRepositoryImpl = new PartsAgreementRepositoryImpl();
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(partsAgreementRepositoryImpl, "jdbcTemplate", jdbcTemplate);
	}

	@Test
	public void otherFeesCards() {
		try {
			PartsAgreementRequest partsAgreementRequest = new PartsAgreementRequest();
			Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
			Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenReturn(connection);
			partsAgreementRepositoryImpl.fetchPartsAgreement(partsAgreementRequest);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			Map<String, Object> resultMap1 = new HashMap<String, Object>();
			Map<String, Object> map = new HashMap<String, Object>();
			List<Map<String, Object>> lst = new ArrayList<>();
			lst.add(map);
			resultMap.put("#result-set-1", lst);
			resultMap.put("#result-set-2", lst);
			resultMap.put("errorcd", "SSS");

			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenReturn(resultMap);
			partsAgreementRepositoryImpl.fetchPartsAgreement(partsAgreementRequest);

			resultMap1.put("#result-set-1", lst);
			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenReturn(resultMap1);

			partsAgreementRepositoryImpl.fetchPartsAgreement(partsAgreementRequest);
			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenReturn(null);
			partsAgreementRepositoryImpl.fetchPartsAgreement(partsAgreementRequest);

			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenThrow(new IncorrectResultSizeDataAccessException(1));
			partsAgreementRepositoryImpl.fetchPartsAgreement(partsAgreementRequest);
		} catch (SQLException e) {
		}
	}

	@Test
	public void createCallableStatement() throws SQLException {
		Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
		PartsAgreementRequest partsAgreementRequest = new PartsAgreementRequest();

		partsAgreementRequest.setSupplierId(5);
		partsAgreementRequest.setLangId(5);
		partsAgreementRequest.setSsoId("5646521");
		partsAgreementRequest.setErrorCd("abc");

		partsAgreementRepositoryImpl.setPartsAgreementRequest(partsAgreementRequest);

		partsAgreementRepositoryImpl.fetchPartsAgreement(partsAgreementRequest);

		try {
			Mockito.when(connection.prepareCall(null)).thenReturn(mockCallableStatement);

			partsAgreementRepositoryImpl.fetchPartsAgreement(partsAgreementRequest);
			partsAgreementRepositoryImpl.createCallableStatement(connection);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
