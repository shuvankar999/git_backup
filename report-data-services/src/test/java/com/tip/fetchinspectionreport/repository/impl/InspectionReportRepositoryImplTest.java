package com.tip.fetchinspectionreport.repository.impl;

import java.math.BigDecimal;
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

import com.tip.fetchinspectionreport.model.InspectionReportRequest;

public class InspectionReportRepositoryImplTest {

	@Mock
	private JdbcTemplate jdbcTemplate;

	@Mock
	DataSource dataSource;

	@Mock
	private Connection mockConnection;

	@Mock
	private CallableStatement mockCallableStatement;

	InspectionReportRepositoryImpl inspectionReportRepositoryImpl;

	@Before
	public void beforesetup() {
		inspectionReportRepositoryImpl = new InspectionReportRepositoryImpl();
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(inspectionReportRepositoryImpl, "jdbcTemplate", jdbcTemplate);
	}

	@Test
	public void testSetDataSource() {
		DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
		inspectionReportRepositoryImpl.setDataSource(ds);
	}

	@Test
	public void getInspectionReport() {

		InspectionReportRequest inspectionReportRequest = new InspectionReportRequest();
		try {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("#result-set-1", 1);
			resultMap.put("#result-set-2", 2);
			resultMap.put("#result-set-3", 3);
			resultMap.put("#result-set-4", 4);
			resultMap.put("#result-set-5", 5);
			resultMap.put("#result-set-6", 6);
			resultMap.put("#result-set-7", 7);

			Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
			Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenReturn(mockConnection);
			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenReturn(resultMap);
			inspectionReportRepositoryImpl.getInspectionReport(inspectionReportRequest);
			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class)))
					.thenThrow(new IncorrectResultSizeDataAccessException(1));
			inspectionReportRepositoryImpl.getInspectionReport(inspectionReportRequest);
		} catch (Exception e) {
		}
	}

	@Test
	public void createCallableStatement() {
		InspectionReportRequest inspectionReportRequest = new InspectionReportRequest();
		inspectionReportRequest.setWorkPackNr(BigDecimal.ONE);
		inspectionReportRequest.setWorkOrderNr(5);
		inspectionReportRequest.setWorkOrderTaskNr(7);
		inspectionReportRequest.setLangId(1);
		inspectionReportRequest.setSsoId("154456");
		inspectionReportRepositoryImpl.setInspectionReportRequest(inspectionReportRequest);
		try {
			Mockito.when(mockConnection.prepareCall(null)).thenReturn(mockCallableStatement);
			inspectionReportRepositoryImpl.createCallableStatement(mockConnection);

			inspectionReportRequest.setWorkOrderNr(null);
			inspectionReportRepositoryImpl.setInspectionReportRequest(inspectionReportRequest);
			inspectionReportRepositoryImpl.createCallableStatement(mockConnection);
		} catch (SQLException e) {
		}
	}
}