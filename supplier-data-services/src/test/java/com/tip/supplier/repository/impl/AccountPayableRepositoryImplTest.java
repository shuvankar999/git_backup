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

import com.tip.supplier.model.AccountPayableRequest;

public class AccountPayableRepositoryImplTest {

	@Mock
	private JdbcTemplate jdbcTemplate;

	@Mock
	DataSource dataSource;

	@Mock
	private Connection connection;

	@Mock
	private CallableStatement mockCallableStatement;

	AccountPayableRepositoryImpl accountPayableRepositoryImpl;

	@Before
	public void beforesetup() {
		accountPayableRepositoryImpl = new AccountPayableRepositoryImpl();
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(accountPayableRepositoryImpl, "jdbcTemplate", jdbcTemplate);
	}

	@Test
	public void accountPayables() {
		try {
			AccountPayableRequest accountPayableRequest = new AccountPayableRequest();
			Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
			Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenReturn(connection);
			accountPayableRepositoryImpl.fetchAccountPayable(accountPayableRequest);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			Map<String, Object> resultMap1 = new HashMap<String, Object>();
			Map<String, Object> map = new HashMap<String, Object>();
			List<Map<String, Object>> lst = new ArrayList<>();
			lst.add(map);
			resultMap.put("#result-set-1", lst);
			resultMap.put("#result-set-2", lst);
			resultMap.put("#result-set-3", lst);
			resultMap.put("errorcd", "SS");
			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenReturn(resultMap);
			accountPayableRepositoryImpl.fetchAccountPayable(accountPayableRequest);

			resultMap1.put("#result-set-1", lst);
			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenReturn(resultMap1);

			accountPayableRepositoryImpl.fetchAccountPayable(accountPayableRequest);
			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenReturn(null);
			accountPayableRepositoryImpl.fetchAccountPayable(accountPayableRequest);

			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenThrow(new IncorrectResultSizeDataAccessException(1));
			accountPayableRepositoryImpl.fetchAccountPayable(accountPayableRequest);
		} catch (SQLException e) {
		}
	}

	@Test
	public void createCallableStatement() throws SQLException {
		Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
		AccountPayableRequest accountPayableRequest = new AccountPayableRequest();

		accountPayableRequest.setSupplierId(5);
		accountPayableRequest.setLangId(5);
		accountPayableRequest.setSsoId("5646521");
		accountPayableRequest.setErrorCd("abc");

		accountPayableRepositoryImpl.setAccountPayableRequest(accountPayableRequest);

		accountPayableRepositoryImpl.fetchAccountPayable(accountPayableRequest);

		try {
			Mockito.when(connection.prepareCall(null)).thenReturn(mockCallableStatement);

			accountPayableRepositoryImpl.fetchAccountPayable(accountPayableRequest);
			accountPayableRepositoryImpl.createCallableStatement(connection);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
