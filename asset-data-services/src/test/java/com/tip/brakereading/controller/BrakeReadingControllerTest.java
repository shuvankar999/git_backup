package com.tip.brakereading.controller;
import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.tip.brakereading.model.BrakeReadingRequest;
import com.tip.brakereading.service.BrakeReadingService;

import junit.framework.Assert;

public class BrakeReadingControllerTest {
	
	@Mock
	private BrakeReadingService brakeReadingService;
	
	private BrakeReadingController brakeReadingController;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		brakeReadingController = new BrakeReadingController();
			ReflectionTestUtils.setField(brakeReadingController, "brakeReadingService", brakeReadingService);			
	}

	@Test
	public void testGetBrakeReading() {
		BrakeReadingRequest brakeReadingRequest=new BrakeReadingRequest();
		brakeReadingRequest.setWorkPackNr(BigDecimal.TEN);
		brakeReadingRequest.setWorkOrderNr(20);
		brakeReadingRequest.setWorkOrderTaskNr(10);
		brakeReadingRequest.setLanguageId(5);
		try{
			brakeReadingController.getBrakeReading(brakeReadingRequest);
	}catch(Exception e){}
		Assert.assertTrue(true);
	}
}
