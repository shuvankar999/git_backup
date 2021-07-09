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

import com.tip.supplier.model.OperationalDetailsRequest;

public class OperationalDetailsRepositoryImplTest {

	@Mock
	private JdbcTemplate jdbcTemplate;

	@Mock
	DataSource dataSource;

	@Mock
	private Connection connection;

	@Mock
	private CallableStatement mockCallableStatement;

	OperationalDetailsRepositoryImpl operationalDetailsRepositoryImpl;

	@Before
	public void beforesetup() {
		operationalDetailsRepositoryImpl = new OperationalDetailsRepositoryImpl();
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(operationalDetailsRepositoryImpl, "jdbcTemplate", jdbcTemplate);
	}

	@Test
	public void operational() {
		try {
			OperationalDetailsRequest operationalDetailsRequest = new OperationalDetailsRequest();
			Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
			Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenReturn(connection);
			operationalDetailsRepositoryImpl.fetchOperationalDetails(operationalDetailsRequest);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			Map<String, Object> resultMap1 = new HashMap<String, Object>();
			Map<String, Object> map = new HashMap<String, Object>();
			List<Map<String, Object>> lst = new ArrayList<>();
			lst.add(map);
			resultMap.put("#result-set-1", lst);
			resultMap.put("#result-set-2", lst);
			resultMap.put("#result-set-3", lst);
			resultMap.put("#result-set-4", lst);
			resultMap.put("#result-set-5", lst);

			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenReturn(resultMap);
			operationalDetailsRepositoryImpl.fetchOperationalDetails(operationalDetailsRequest);

			resultMap1.put("#result-set-1", lst);
			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenReturn(resultMap1);

			operationalDetailsRepositoryImpl.fetchOperationalDetails(operationalDetailsRequest);
			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenReturn(null);
			operationalDetailsRepositoryImpl.fetchOperationalDetails(operationalDetailsRequest);

			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenThrow(new IncorrectResultSizeDataAccessException(1));
			operationalDetailsRepositoryImpl.fetchOperationalDetails(operationalDetailsRequest);
		} catch (SQLException e) {
		}
	}

	@Test
	public void createCallableStatement() throws SQLException {
		Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
		OperationalDetailsRequest operationalDetailsRequest = new OperationalDetailsRequest();

		operationalDetailsRequest.setSupplierId(5);
		operationalDetailsRequest.setLangId(5);
		operationalDetailsRequest.setSsoId("5646521");
		operationalDetailsRequest.setErrorCd("abc");

		operationalDetailsRepositoryImpl.setOperationalDetailsRequest(operationalDetailsRequest);

		operationalDetailsRepositoryImpl.fetchOperationalDetails(operationalDetailsRequest);

		try {
			Mockito.when(connection.prepareCall(null)).thenReturn(mockCallableStatement);

			operationalDetailsRepositoryImpl.fetchOperationalDetails(operationalDetailsRequest);
			operationalDetailsRepositoryImpl.createCallableStatement(connection);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
