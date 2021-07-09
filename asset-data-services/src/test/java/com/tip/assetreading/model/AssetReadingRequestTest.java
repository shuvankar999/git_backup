package com.tip.assetreading.model;


import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class AssetReadingRequestTest {
	private AssetReadingRequest assetReadingRequest;
	@Before
	public void setUp() throws Exception { assetReadingRequest = new AssetReadingRequest();
	
	//assetReadingRequest.setWorkPackNr(1.00);
    assetReadingRequest.setWorkPackNr(BigDecimal.TEN);
    assetReadingRequest.setWorkOrderNr(20);
    assetReadingRequest.setWorkOrderTaskNr(10);
    assetReadingRequest.setLanguageId(5);
	
}


	@Test
	public void test() {
	
	}

}
