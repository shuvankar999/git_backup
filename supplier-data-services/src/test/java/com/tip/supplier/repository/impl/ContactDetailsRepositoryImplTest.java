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

import com.tip.supplier.model.ContactDetailsRequest;

public class ContactDetailsRepositoryImplTest {
	
	@Mock
	private JdbcTemplate jdbcTemplate;

	@Mock
	DataSource dataSource;

	@Mock
	private Connection connection;

	@Mock
	private CallableStatement mockCallableStatement;

	ContactDetailsRepositoryImpl contactDetailsRepositoryImpl;

	@Before
	public void beforesetup() {
		contactDetailsRepositoryImpl = new ContactDetailsRepositoryImpl();
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(contactDetailsRepositoryImpl, "jdbcTemplate", jdbcTemplate);
	}

	@Test
	public void contact() {
		try {
			ContactDetailsRequest contactDetailsRequest = new ContactDetailsRequest();
			Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
			Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenReturn(connection);
			contactDetailsRepositoryImpl.fetchContactDetails(contactDetailsRequest);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			Map<String, Object> resultMap1 = new HashMap<String, Object>();
			Map<String, Object> map = new HashMap<String, Object>();
			List<Map<String, Object>> lst = new ArrayList<>();
			lst.add(map);
			resultMap.put("#result-set-1", lst);
			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenReturn(resultMap);
			contactDetailsRepositoryImpl.fetchContactDetails(contactDetailsRequest);

			resultMap1.put("#result-set-1", lst);
			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenReturn(resultMap1);

			contactDetailsRepositoryImpl.fetchContactDetails(contactDetailsRequest);
			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenReturn(null);
			contactDetailsRepositoryImpl.fetchContactDetails(contactDetailsRequest);

			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenThrow(new IncorrectResultSizeDataAccessException(1));
			contactDetailsRepositoryImpl.fetchContactDetails(contactDetailsRequest);
		} catch (SQLException e) {
		}
	}

	@Test
	public void createCallableStatement() throws SQLException {
		Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
		ContactDetailsRequest contactDetailsRequest = new ContactDetailsRequest();

		contactDetailsRequest.setSupplierId(5);
		contactDetailsRequest.setLangId(5);
		contactDetailsRequest.setSsoId("5646521");
		contactDetailsRequest.setErrorCd("abc");

		contactDetailsRepositoryImpl.setContactDetailsRequest(contactDetailsRequest);

		contactDetailsRepositoryImpl.fetchContactDetails(contactDetailsRequest);

		try {
			Mockito.when(connection.prepareCall(null)).thenReturn(mockCallableStatement);

			contactDetailsRepositoryImpl.fetchContactDetails(contactDetailsRequest);
			contactDetailsRepositoryImpl.createCallableStatement(connection);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
