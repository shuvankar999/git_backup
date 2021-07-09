package com.tip.tyrereading.repository.impl;

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

import com.tip.tyrereading.model.TyreReadingRequest;

public class TyreReadingRepositoryImplTest {
	private TyreReadingRepositoryImpl tyreReadingRepositoryImpl;
	
	@Mock
	private JdbcTemplate jdbcTemplate;
	
	@Mock
	DataSource dataSource;
	
	@Mock 
	private Connection mockConnection;
	
	@Mock
	private CallableStatement mockCallableStatement;
	
	@Before
    public void setUp() throws Exception {
		tyreReadingRepositoryImpl = new TyreReadingRepositoryImpl();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(tyreReadingRepositoryImpl, "jdbcTemplate", jdbcTemplate);    
    }
	
	@Test
	public void testSetDataSource() {
		DataSource ds=new org.apache.tomcat.jdbc.pool.DataSource();
		tyreReadingRepositoryImpl.setDataSource(ds);
	}
	
	@Test
	public void testTyreReadingDetails() {
		try
		{
			TyreReadingRequest tyreReadingRequest=new TyreReadingRequest();
			tyreReadingRequest.setWorkPackNr(BigDecimal.ONE);
			
			Map<String, Object> tyreReadingMap = new HashMap<String, Object>();
			tyreReadingMap.put("#result-set-1", 1);
			tyreReadingMap.put("#result-set-2", 2);
			tyreReadingMap.put("#result-set-3", 3);
			tyreReadingMap.put("#result-set-4", 4);
			tyreReadingMap.put("Error_Cd", 5);
	
			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class))).thenReturn(tyreReadingMap);		
			Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
			Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenReturn(mockConnection);
			tyreReadingRepositoryImpl.fetchTyreReadingDetails(tyreReadingRequest);
			
			Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class))).thenThrow(new IncorrectResultSizeDataAccessException(1));
			tyreReadingRepositoryImpl.fetchTyreReadingDetails(tyreReadingRequest);
		}
		catch(Exception e){}
	}
	
	@Test
	public void createCallableStatement() {
		TyreReadingRequest tyreReadingRequest = new TyreReadingRequest();
		tyreReadingRequest.setWorkPackNr(BigDecimal.ONE);
		tyreReadingRequest.setWorkOrderNr(4);
		tyreReadingRequest.setWorkOrderTaskNr(45);
		tyreReadingRequest.setLanguageId(1);
		tyreReadingRepositoryImpl.setTyreReadingRequest(tyreReadingRequest);
		try {
			Mockito.when(mockConnection.prepareCall(null)).thenReturn(mockCallableStatement);
			tyreReadingRepositoryImpl.createCallableStatement(mockConnection);
			
			tyreReadingRequest.setWorkOrderTaskNr(null);
			tyreReadingRepositoryImpl.setTyreReadingRequest(tyreReadingRequest);
			tyreReadingRepositoryImpl.createCallableStatement(mockConnection);
		} catch (SQLException e) {}
	}
}
