package com.tip.suppliermasterdata.repository.impl;

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

import com.tip.suppliermasterdata.model.FetchDynamicMasterDataRequest;

public class FetchAllProceduresListRepositoryImplTest {

	FetchAllProceduresListRepositoryImpl fetchAllProceduresListRepositoryImpl;

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
		fetchAllProceduresListRepositoryImpl = new FetchAllProceduresListRepositoryImpl();
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(fetchAllProceduresListRepositoryImpl, "jdbcTemplate", jdbcTemplate);
	}

	@Test
	public void getAllProcedures() {
		String keyName = new String();
		FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest = new FetchDynamicMasterDataRequest();
		try {
			Map<String, Object> resultMap = new HashMap<>();
			Map<String, Object> array = new HashMap<>();
			List<Map<String, Object>> list = new ArrayList<>();
			array.put("key_name", "Standard");
			array.put("obj_name", "sdsfgg");
			array.put("proc_name", "sdf");
			list.add(array);
			resultMap.put("#result-set-1", list);
			Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
			Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenReturn(mockConnection);
			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenReturn(resultMap);

			fetchAllProceduresListRepositoryImpl.getAllProcedures(fetchDynamicMasterDataRequest);
		} catch (Exception e) {
		}
	}

	@Test
	public void getAllProceduresNull() {
		String keyName = new String();
		FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest = new FetchDynamicMasterDataRequest();
		try {
			Map<String, Object> resultMap = new HashMap<>();
			Map<String, Object> array = new HashMap<>();
			List<Map<String, Object>> list = new ArrayList<>();
			array.put("key_name", "Standard");
			array.put("obj_name", "sdsfgg");
			array.put("proc_name", "sdf");
			list.add(array);
			resultMap.put("addf", list);
			Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
			Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenReturn(mockConnection);
			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenReturn(resultMap);

			fetchAllProceduresListRepositoryImpl.getAllProcedures(fetchDynamicMasterDataRequest);
		} catch (Exception e) {
		}
	}

	@Test
	public void getAllProceduresException() {
		String keyName = new String();
		FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest = new FetchDynamicMasterDataRequest();
		try {
			Map<String, Object> resultMap = new HashMap<>();
			Map<String, Object> array = new HashMap<>();
			List<Map<String, Object>> list = new ArrayList<>();
			array.put("key_name", "Standard");
			array.put("obj_name", "sdsfgg");
			array.put("proc_name", "sdf");
			list.add(array);
			resultMap.put("addf", list);
			Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
			Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenReturn(mockConnection);
			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenReturn(resultMap);
			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenThrow(new IncorrectResultSizeDataAccessException(1));
			fetchAllProceduresListRepositoryImpl.getAllProcedures(fetchDynamicMasterDataRequest);
		} catch (Exception e) {
		}
	}

	@Test
	public void createCallableStatement() {
		FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest = new FetchDynamicMasterDataRequest();
		fetchDynamicMasterDataRequest.setSsoId("010");
		fetchAllProceduresListRepositoryImpl.setFetchDynamicMasterDataRequest(fetchDynamicMasterDataRequest);
		try {
			mockConnection.setAutoCommit(false);

			Mockito.when(mockConnection.prepareCall(null)).thenReturn(mockCallableStatement);
			fetchAllProceduresListRepositoryImpl.setFetchDynamicMasterDataRequest(fetchDynamicMasterDataRequest);
			fetchAllProceduresListRepositoryImpl.createCallableStatement(mockConnection);
		} catch (SQLException e) {
		}

	}

}
