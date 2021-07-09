package com.tip.suppliermasterdata.repository.impl;

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

import com.tip.suppliermasterdata.model.AllProcedureDataResponse;
import com.tip.suppliermasterdata.model.FetchDynamicMasterDataRequest;

public class FetchDynamicMasterDataRepositoryImplTest {
	
	FetchDynamicMasterDataRepositoryImpl fetchDynamicMasterDataRepositoryImpl;

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
		fetchDynamicMasterDataRepositoryImpl = new FetchDynamicMasterDataRepositoryImpl();
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(fetchDynamicMasterDataRepositoryImpl, "jdbcTemplate", jdbcTemplate);
	}

	@Test
	public void getDynamicMasterData() {
		FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest = new FetchDynamicMasterDataRequest();
		AllProcedureDataResponse allProcedureDataResponse = new AllProcedureDataResponse();
		try {
			Map<String, Object> returnMap = new HashMap<String, Object>();
			returnMap.put("#result-set-1", "1");
			Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
			Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenReturn(mockConnection);
			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenReturn(returnMap);
			fetchDynamicMasterDataRepositoryImpl.getDynamicMasterData(fetchDynamicMasterDataRequest,
					allProcedureDataResponse);
		} catch (Exception e) {
		}
	}

	@Test
	public void getDynamicMasterDataNull() {
		FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest = new FetchDynamicMasterDataRequest();
		AllProcedureDataResponse allProcedureDataResponse = new AllProcedureDataResponse();
		try {
			Map<String, Object> returnMap = new HashMap<String, Object>();
			returnMap.put("das", 1);
			Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
			Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenReturn(mockConnection);
			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenReturn(returnMap);
			fetchDynamicMasterDataRepositoryImpl.getDynamicMasterData(fetchDynamicMasterDataRequest,
					allProcedureDataResponse);
		} catch (Exception e) {
		}
	}

	@Test
	public void getDynamicMasterDataException() {
		FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest = new FetchDynamicMasterDataRequest();
		AllProcedureDataResponse allProcedureDataResponse = new AllProcedureDataResponse();
		try {
			Map<String, Object> returnMap = new HashMap<String, Object>();
			returnMap.put("das", 1);
			Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
			Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenReturn(mockConnection);
			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenReturn(returnMap);
			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenThrow(new IncorrectResultSizeDataAccessException(1));
			fetchDynamicMasterDataRepositoryImpl.getDynamicMasterData(fetchDynamicMasterDataRequest,
					allProcedureDataResponse);
		} catch (Exception e) {
		}
	}

	@Test
	public void createCallableStatement() {

		FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest = new FetchDynamicMasterDataRequest();
		fetchDynamicMasterDataRequest.setBranchNr(02);
		fetchDynamicMasterDataRequest.setLanguageId(03);
		fetchDynamicMasterDataRepositoryImpl.setFetchDynamicMasterDataRequest(fetchDynamicMasterDataRequest);
		try {
			mockConnection.setAutoCommit(false);
			Mockito.when(mockConnection.prepareCall(null)).thenReturn(mockCallableStatement);
			fetchDynamicMasterDataRepositoryImpl.setFetchDynamicMasterDataRequest(fetchDynamicMasterDataRequest);
			fetchDynamicMasterDataRepositoryImpl.createCallableStatement(mockConnection);
		} catch (SQLException e) {
		}

	}
}
