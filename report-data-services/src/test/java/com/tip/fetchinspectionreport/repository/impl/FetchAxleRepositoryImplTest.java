package com.tip.fetchinspectionreport.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
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

import com.tip.fetchinspectionreport.model.FetchAxleRequest;

public class FetchAxleRepositoryImplTest {

	FetchAxleRepositoryImpl fetchAxleRepositoryImpl;

	@Mock
	private JdbcTemplate jdbcTemplate;

	@Mock
	DataSource dataSource;

	@Mock
	private Connection mockConnection;

	@Mock
	private CallableStatement mockCallableStatement;

	@Before
	public void beforesetup() {
		fetchAxleRepositoryImpl = new FetchAxleRepositoryImpl();
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(fetchAxleRepositoryImpl, "jdbcTemplate", jdbcTemplate);
	}

	@Test
	public void testSetDataSource() {
		DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
		fetchAxleRepositoryImpl.setDataSource(ds);
	}

	@Test
	public void fetchNoOfAxlestest() {
		FetchAxleRequest fetchAxleRequest = new FetchAxleRequest();
		try {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("#result-set-1", 1);

			Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
			Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenReturn(mockConnection);
			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenReturn(resultMap);
			fetchAxleRepositoryImpl.fetchNoOfAxleForAsset(fetchAxleRequest);
			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenThrow(new IncorrectResultSizeDataAccessException(1));
			fetchAxleRepositoryImpl.fetchNoOfAxleForAsset(fetchAxleRequest);
		} catch (Exception e) {
		}
	}

	@Test
	public void createCallableStatement() {
		FetchAxleRequest fetchAxleRequest = new FetchAxleRequest();
		fetchAxleRequest.setUnitNr(1);

		fetchAxleRepositoryImpl.fetchNoOfAxleForAsset(fetchAxleRequest);
		try {
			Mockito.when(mockConnection.prepareCall(null)).thenReturn(mockCallableStatement);
			fetchAxleRepositoryImpl.createCallableStatement(mockConnection);

			fetchAxleRequest.setUnitNr(4);
			fetchAxleRepositoryImpl.fetchNoOfAxleForAsset(fetchAxleRequest);
			fetchAxleRepositoryImpl.createCallableStatement(mockConnection);
		} catch (SQLException e) {
		}
	}
}
