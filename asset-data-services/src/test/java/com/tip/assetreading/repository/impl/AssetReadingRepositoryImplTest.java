package com.tip.assetreading.repository.impl;

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

import com.tip.assetreading.model.AssetReadingRequest;

public class AssetReadingRepositoryImplTest {

	private AssetReadingRepositoryImpl assetReadingRepositoryImpl;
	
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
        assetReadingRepositoryImpl = new AssetReadingRepositoryImpl();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(assetReadingRepositoryImpl, "jdbcTemplate", jdbcTemplate);    
    }

	@Test
	public void testSetDataSource() {
		DataSource ds=new org.apache.tomcat.jdbc.pool.DataSource();
		assetReadingRepositoryImpl.setDataSource(ds);
	}

	@Test
	public void testFetchAssetReadingDetails() {
		try{
		AssetReadingRequest assetReadingRequest=new AssetReadingRequest();
		assetReadingRequest.setWorkPackNr(BigDecimal.ONE);
		
		Map<String, Object> assetReadingMap = new HashMap<String, Object>();
		assetReadingMap.put("#result-set-1", 1);
		assetReadingMap.put("#result-set-2", 2);
		assetReadingMap.put("#result-set-3", 3);
		assetReadingMap.put("#result-set-4", 4);
		assetReadingMap.put("Error_Cd", 5);
		
		Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class))).thenReturn(assetReadingMap);		
		Mockito.when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
		Mockito.when(jdbcTemplate.getDataSource().getConnection()).thenReturn(mockConnection);		
		assetReadingRepositoryImpl.fetchAssetReadingDetails(assetReadingRequest);
		
		Mockito.when(jdbcTemplate.call(Mockito.isA(CallableStatementCreator.class), Mockito.isA(List.class))).thenThrow(new IncorrectResultSizeDataAccessException(1));
		assetReadingRepositoryImpl.fetchAssetReadingDetails(assetReadingRequest);
		}catch(Exception e){}
	}
	
	@Test
	public void createCallableStatement() {
		AssetReadingRequest lAssetReadingRequest = new AssetReadingRequest();
		lAssetReadingRequest.setWorkPackNr(BigDecimal.ONE);
		lAssetReadingRequest.setWorkOrderNr(2);
		lAssetReadingRequest.setWorkOrderTaskNr(1);
		lAssetReadingRequest.setLanguageId(1);
		assetReadingRepositoryImpl.setAssetReadingRequest(lAssetReadingRequest);
		try {
			Mockito.when(mockConnection.prepareCall(null)).thenReturn(mockCallableStatement);
			assetReadingRepositoryImpl.createCallableStatement(mockConnection);
			
			lAssetReadingRequest.setWorkOrderNr(null);
			assetReadingRepositoryImpl.setAssetReadingRequest(lAssetReadingRequest);
			assetReadingRepositoryImpl.createCallableStatement(mockConnection);
		} catch (SQLException e) {
		}
	}
}
