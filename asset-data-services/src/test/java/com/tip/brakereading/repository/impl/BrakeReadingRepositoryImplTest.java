package com.tip.brakereading.repository.impl;
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

import com.tip.brakereading.model.BrakeReadingRequest;


public class BrakeReadingRepositoryImplTest {

	private BrakeReadingRepositoryImpl brakeReadingRepositoryImpl;
	
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
        brakeReadingRepositoryImpl = new BrakeReadingRepositoryImpl();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(brakeReadingRepositoryImpl, "jdbcTemplate", jdbcTemplate);    
    }


	@Test
	public void testSetDataSource() {
		DataSource ds=new org.apache.tomcat.jdbc.pool.DataSource();
		brakeReadingRepositoryImpl.setDataSource(ds);
	}

	@Test
	public void testFetchBrakeReadingDetails() {
		try
		{
		BrakeReadingRequest brakeReadingRequest=new BrakeReadingRequest();
		brakeReadingRequest.setWorkPackNr(BigDecimal.ONE);
	
		Map<String, Object> brakeReadingMap = new HashMap<String, Object>();
		brakeReadingMap.put("#result-set-1", 1);
		brakeReadingMap.put("#result-set-2", 2);
		brakeReadingMap.put("#result-set-3", 3);
		brakeReadingMap.put("#result-set-4", 4);
		brakeReadingMap.put("Error_Cd", 5);
	
		Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class))).thenReturn(brakeReadingMap);		
		Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
		Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenReturn(mockConnection);

		brakeReadingRepositoryImpl.fetchBrakeReadingDetails(brakeReadingRequest);
		
		Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class))).thenThrow(new IncorrectResultSizeDataAccessException(1));
		brakeReadingRepositoryImpl.fetchBrakeReadingDetails(brakeReadingRequest);		
	}catch(Exception e){}
}

	@Test
	public void createCallableStatement(){
		BrakeReadingRequest brakeReadingRequest = new BrakeReadingRequest();
		brakeReadingRequest.setWorkPackNr(BigDecimal.ONE);
		brakeReadingRequest.setWorkOrderNr(1);
		brakeReadingRequest.setWorkOrderTaskNr(2);
		brakeReadingRequest.setLanguageId(1);
		brakeReadingRepositoryImpl.setBrakeReadingRequest(brakeReadingRequest);
		try {
			Mockito.when(mockConnection.prepareCall(null)).thenReturn(mockCallableStatement);
			brakeReadingRepositoryImpl.createCallableStatement(mockConnection);
			
			brakeReadingRequest.setWorkOrderNr(null);
			brakeReadingRepositoryImpl.setBrakeReadingRequest(brakeReadingRequest);
			brakeReadingRepositoryImpl.createCallableStatement(mockConnection);
		} catch (SQLException e) {
		}
	}
}