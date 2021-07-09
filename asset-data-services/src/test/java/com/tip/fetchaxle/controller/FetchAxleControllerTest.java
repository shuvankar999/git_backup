package com.tip.fetchaxle.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.tip.fetchaxle.model.FetchAxleRequest;
import com.tip.fetchaxle.service.FetchAxleService;

public class FetchAxleControllerTest {
	
	@Mock
	private FetchAxleService fetchAxleService;
	 FetchAxleController fetchAxleController;
	@Before
	public void setUp() throws Exception {
		fetchAxleController = new FetchAxleController();
			ReflectionTestUtils.setField(fetchAxleController, "fetchAxleService", fetchAxleService);
			MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testFetchAxle() {
		FetchAxleRequest fetchAxleRequest =new FetchAxleRequest();		
		fetchAxleRequest.setUnitNr(1);
		try{
		fetchAxleController.getNoOfAxles(fetchAxleRequest);
		}catch(Exception e){}
	}
}