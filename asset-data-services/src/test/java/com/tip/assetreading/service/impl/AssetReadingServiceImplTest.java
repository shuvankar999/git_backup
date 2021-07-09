package com.tip.assetreading.service.impl;
import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.tip.assetreading.model.AssetReadingRequest;
import com.tip.assetreading.repository.AssetReadingRepository;

public class AssetReadingServiceImplTest {

	private AssetReadingServiceImpl assetReadingServiceImpl;
	
	@Mock
	AssetReadingRepository assetReadingRepository;
	
	@Before
	public void setUp() throws Exception {
		    assetReadingServiceImpl = new AssetReadingServiceImpl();
			ReflectionTestUtils.setField(assetReadingServiceImpl, "assetReadingRepository", assetReadingRepository);
			MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testA() {
		AssetReadingRequest assetReadingRequest=new AssetReadingRequest();		    
		    assetReadingRequest.setWorkPackNr(BigDecimal.TEN);
		    assetReadingRequest.setWorkOrderNr(20);
		    assetReadingRequest.setWorkOrderTaskNr(10);
		    assetReadingRequest.setLanguageId(5);			
		try{
		assetReadingServiceImpl.getAssetReadingDetails(assetReadingRequest);
		}catch(Exception e){}	
	}
}
