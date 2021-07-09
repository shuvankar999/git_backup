package com.tip.assetreading.controller;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.tip.assetreading.model.AssetReadingRequest;
import com.tip.assetreading.service.AssetReadingService;


public class AssetReadingControllerTest {

	@Mock
	private AssetReadingService assetReadingService;
	
	private AssetReadingController assetReadingController;

	@Before
	public void setUp() throws Exception {
		assetReadingController = new AssetReadingController();
		ReflectionTestUtils.setField(assetReadingController, "assetReadingService", assetReadingService);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAssetReading() {
		AssetReadingRequest assetReadingRequest=new AssetReadingRequest();
	    assetReadingRequest.setWorkPackNr(BigDecimal.TEN);
	    assetReadingRequest.setWorkOrderNr(20);
	    assetReadingRequest.setWorkOrderTaskNr(10);
	    assetReadingRequest.setLanguageId(5);
		try{
		assetReadingController.getAssetReading(assetReadingRequest);
		}catch(Exception e){}
	}

}

