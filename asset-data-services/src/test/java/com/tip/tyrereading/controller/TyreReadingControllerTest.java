package com.tip.tyrereading.controller;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.tip.tyrereading.model.TyreReadingRequest;
import com.tip.tyrereading.service.TyreReadingService;

public class TyreReadingControllerTest {

	@Mock
	TyreReadingService tyreReadingService;
	
	TyreReadingController tyreReadingController;
	
	@Before
	public void setUp(){
		tyreReadingController = new TyreReadingController();
		ReflectionTestUtils.setField(tyreReadingController, "tyreReadingService", tyreReadingService);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void FetchAxle() {
		TyreReadingRequest tyreReadingRequest= new TyreReadingRequest();
		//tyreReadingRequest.setAssetNr(1);
		tyreReadingRequest.setWorkPackNr(BigDecimal.TEN);
		tyreReadingRequest.setWorkOrderNr(20);
		tyreReadingRequest.setWorkOrderTaskNr(10);
		tyreReadingRequest.setLanguageId(5);
	//	Mockito.when(tyreReadingService.getTyreReadingDetails(Mockito.isA(TyreReadingRequest.class)));
		try{
			tyreReadingController.getTyreReading(tyreReadingRequest);
			}catch(Exception e){}
	}
}