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

import com.tip.supplier.model.FetchJobRequest;

public class FetchCustomisedJobRepositoryImplTest {

	@Mock
	private JdbcTemplate jdbcTemplate;

	@Mock
	DataSource dataSource;

	@Mock
	private Connection connection;

	@Mock
	private CallableStatement mockCallableStatement;

	FetchCustomisedJobRepositoryImpl fetchCustomisedJobRepositoryImpl;

	@Before
	public void beforesetup() {
		fetchCustomisedJobRepositoryImpl = new FetchCustomisedJobRepositoryImpl();
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(fetchCustomisedJobRepositoryImpl, "jdbcTemplate", jdbcTemplate);
	}

	@Test
	public void job() {
		try {
			FetchJobRequest fetchJobRequest = new FetchJobRequest();
			Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
			Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenReturn(connection);
			fetchCustomisedJobRepositoryImpl.fetchCustomisedJob(fetchJobRequest);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			Map<String, Object> resultMap1 = new HashMap<String, Object>();
			Map<String, Object> map = new HashMap<String, Object>();
			List<Map<String, Object>> lst = new ArrayList<>();
			lst.add(map);
			resultMap.put("#result-set-1", lst);
			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenReturn(resultMap);
			fetchCustomisedJobRepositoryImpl.fetchCustomisedJob(fetchJobRequest);

			resultMap1.put("#result-set-1", lst);
			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenReturn(resultMap1);

			fetchCustomisedJobRepositoryImpl.fetchCustomisedJob(fetchJobRequest);
			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenReturn(null);
			fetchCustomisedJobRepositoryImpl.fetchCustomisedJob(fetchJobRequest);

			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenThrow(new IncorrectResultSizeDataAccessException(1));
			fetchCustomisedJobRepositoryImpl.fetchCustomisedJob(fetchJobRequest);
		} catch (SQLException e) {
		}
	}

	@Test
	public void createCallableStatement() throws SQLException {
		Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
		FetchJobRequest fetchJobRequest = new FetchJobRequest();

		fetchJobRequest.setSupplierId(5);
		fetchJobRequest.setLangId(5);
		fetchJobRequest.setSsoId("5646521");
		fetchJobRequest.setErrorCd("abc");

		fetchCustomisedJobRepositoryImpl.setFetchJobRequest(fetchJobRequest);

		fetchCustomisedJobRepositoryImpl.fetchCustomisedJob(fetchJobRequest);

		try {
			Mockito.when(connection.prepareCall(null)).thenReturn(mockCallableStatement);

			fetchCustomisedJobRepositoryImpl.fetchCustomisedJob(fetchJobRequest);
			fetchCustomisedJobRepositoryImpl.createCallableStatement(connection);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
