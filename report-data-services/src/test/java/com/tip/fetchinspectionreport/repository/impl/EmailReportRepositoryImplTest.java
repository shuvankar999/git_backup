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

import com.tip.fetchinspectionreport.model.EmailReportRequest;

public class EmailReportRepositoryImplTest {
	
	EmailReportRepositoryImpl emailReportRepositoryImpl;

	@Mock
	private JdbcTemplate jdbcTemplate;
	
	@Mock
	DataSource dataSource;
	
	@Mock 
	private Connection mockConnection;
	
	@Mock
	private CallableStatement mockCallableStatement;

	@Before
	public void beforesetup(){
		emailReportRepositoryImpl = new EmailReportRepositoryImpl();
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(emailReportRepositoryImpl, "jdbcTemplate", jdbcTemplate);
	}

	@Test
	public void sendEmail() {

		EmailReportRequest emailReportRequest = new EmailReportRequest();
		try{
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("#result-set-1", 1);
			
			Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
			Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenReturn(mockConnection);
			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class))).thenReturn(resultMap);
			emailReportRepositoryImpl.sendEmail(emailReportRequest);
			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class))).thenThrow(new IncorrectResultSizeDataAccessException(1));
			emailReportRepositoryImpl.sendEmail(emailReportRequest);
		}catch(Exception e){}
	}

	@Test
	public void createCallableStatement(){
		EmailReportRequest emailReportRequest = new EmailReportRequest();
		emailReportRequest.setWorkPackNr(BigDecimal.valueOf(7900181290L));
		emailReportRequest.setWorkOrderNr(1);
		emailReportRequest.setWorkOrderTaskNr(2);
		emailReportRequest.setLangId(1);
		emailReportRequest.setSsoId("5135");
		emailReportRepositoryImpl.setEmailReportRequest(emailReportRequest);
		try {
			Mockito.when(mockConnection.prepareCall(null)).thenReturn(mockCallableStatement);
			emailReportRepositoryImpl.createCallableStatement(mockConnection);
			
			emailReportRequest.setWorkOrderNr(null);
			emailReportRepositoryImpl.setEmailReportRequest(emailReportRequest);
			emailReportRepositoryImpl.createCallableStatement(mockConnection);
		} catch (SQLException e) {
		}		
	}
}
