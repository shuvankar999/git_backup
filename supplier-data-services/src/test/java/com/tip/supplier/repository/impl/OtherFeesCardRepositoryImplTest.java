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

import com.tip.supplier.model.OtherFeesCardRequest;

public class OtherFeesCardRepositoryImplTest {

	@Mock
	private JdbcTemplate jdbcTemplate;

	@Mock
	DataSource dataSource;

	@Mock
	private Connection connection;

	@Mock
	private CallableStatement mockCallableStatement;

	OtherFeesCardRepositoryImpl otherFeesCardRepositoryImpl;

	@Before
	public void beforesetup() {
		otherFeesCardRepositoryImpl = new OtherFeesCardRepositoryImpl();
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(otherFeesCardRepositoryImpl, "jdbcTemplate", jdbcTemplate);
	}

	@Test
	public void otherFeesCards() {
		try {
			OtherFeesCardRequest otherFeesCardRequest = new OtherFeesCardRequest();
			Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
			Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenReturn(connection);
			otherFeesCardRepositoryImpl.fetchOtherFeesCard(otherFeesCardRequest);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			Map<String, Object> resultMap1 = new HashMap<String, Object>();
			Map<String, Object> map = new HashMap<String, Object>();
			List<Map<String, Object>> lst = new ArrayList<>();
			lst.add(map);
			resultMap.put("#result-set-1", lst);

			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenReturn(resultMap);
			otherFeesCardRepositoryImpl.fetchOtherFeesCard(otherFeesCardRequest);

			resultMap1.put("#result-set-1", lst);
			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenReturn(resultMap1);

			otherFeesCardRepositoryImpl.fetchOtherFeesCard(otherFeesCardRequest);
			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenReturn(null);
			otherFeesCardRepositoryImpl.fetchOtherFeesCard(otherFeesCardRequest);

			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenThrow(new IncorrectResultSizeDataAccessException(1));
			otherFeesCardRepositoryImpl.fetchOtherFeesCard(otherFeesCardRequest);
		} catch (SQLException e) {
		}
	}

	@Test
	public void createCallableStatement() throws SQLException {
		Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
		OtherFeesCardRequest otherFeesCardRequest = new OtherFeesCardRequest();

		otherFeesCardRequest.setSupplierId(5);
		otherFeesCardRequest.setLangId(5);
		otherFeesCardRequest.setSsoId("5646521");
		otherFeesCardRequest.setErrorCd("abc");

		otherFeesCardRepositoryImpl.setOtherFeesCardRequest(otherFeesCardRequest);

		otherFeesCardRepositoryImpl.fetchOtherFeesCard(otherFeesCardRequest);

		try {
			Mockito.when(connection.prepareCall(null)).thenReturn(mockCallableStatement);

			otherFeesCardRepositoryImpl.fetchOtherFeesCard(otherFeesCardRequest);
			otherFeesCardRepositoryImpl.createCallableStatement(connection);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
