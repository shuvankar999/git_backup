package com.tip.tyrereading.model;



import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;



public class TyreReadingRequestTest {
	private TyreReadingRequest tyreReadingRequest;

	@Before
	public void setUp() throws Exception { tyreReadingRequest = new TyreReadingRequest();
	/*tyreReadingRequest.setAssetNr(1);
	tyreReadingRequest.setWorkPackNr(1.00);*/

	tyreReadingRequest.setWorkPackNr(BigDecimal.TEN);
	tyreReadingRequest.setWorkOrderNr(20);
	tyreReadingRequest.setWorkOrderTaskNr(10);
	tyreReadingRequest.setLanguageId(5);
	
	
}
	@Test
	public void test() {
}

}
