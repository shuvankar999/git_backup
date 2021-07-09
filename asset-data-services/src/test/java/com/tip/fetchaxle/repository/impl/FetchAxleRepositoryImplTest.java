package com.tip.fetchaxle.repository.impl;
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

import com.tip.fetchaxle.model.FetchAxleRequest;

public class FetchAxleRepositoryImplTest {
	
	private FetchAxleRepositoryImpl fetchAxleRepositoryImpl;
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
		fetchAxleRepositoryImpl = new FetchAxleRepositoryImpl();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(fetchAxleRepositoryImpl, "jdbcTemplate", jdbcTemplate);    
    }
	@Test
	public void testSetDataSource() {
		DataSource ds=new org.apache.tomcat.jdbc.pool.DataSource();
		fetchAxleRepositoryImpl.setDataSource(ds);
	}

	@Test
	public void testFetchAxleDetails() {
		try
		{
		FetchAxleRequest fetchAxleRequest=new FetchAxleRequest();
		fetchAxleRequest.setUnitNr(2);
		
		Map<String, Object> fetchAxleMap = new HashMap<String, Object>();
		fetchAxleMap.put("#result-set-1", 1);
		fetchAxleMap.put("Error_Cd", 2);
		fetchAxleMap.put("eRROR_cD", 3);
	
		Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class))).thenReturn(fetchAxleMap);		
		Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
		Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenReturn(mockConnection); 
		fetchAxleRepositoryImpl.fetchNoOfAxleForAsset(fetchAxleRequest);
		
		Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class))).thenThrow(new IncorrectResultSizeDataAccessException(1));
		fetchAxleRepositoryImpl.fetchNoOfAxleForAsset(fetchAxleRequest);
	}
	catch(Exception e){}
}
	
	@Test
	public void createCallableStatement() {
		FetchAxleRequest fetchAxleRequest = new FetchAxleRequest();
		fetchAxleRequest.setUnitNr(2);
		fetchAxleRepositoryImpl.setFetchAxleRequest(fetchAxleRequest);
		try {
			Mockito.when(mockConnection.prepareCall(null)).thenReturn(mockCallableStatement);
			fetchAxleRepositoryImpl.createCallableStatement(mockConnection);
		} catch (SQLException e) {}
	}
}

