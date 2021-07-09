package com.tip.tyrereading.service.impl;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.tip.tyrereading.model.TyreReadingRequest;
import com.tip.tyrereading.model.TyreReadingResponse;
import com.tip.tyrereading.repository.TyreReadingRepository;

public class TyreReadingServiceImplTest {

		private TyreReadingServiceImpl tyreReadingServiceImpl;
		
		@Mock
		TyreReadingRepository tyreReadingRepository;
		
		
		@Before
		public void setUp() throws Exception {
			tyreReadingServiceImpl = new TyreReadingServiceImpl();
			ReflectionTestUtils.setField(tyreReadingServiceImpl, "tyreReadingRepository", tyreReadingRepository);

			MockitoAnnotations.initMocks(this);
		}
	
	@Test
	public void testT() {
		TyreReadingRequest tyreReadingRequest = new TyreReadingRequest();
		/*tyreReadingRequest.setWorkPackNr(1.00);
		tyreReadingRequest.setAssetNr(1);*/

		tyreReadingRequest.setWorkPackNr(BigDecimal.TEN);
		tyreReadingRequest.setWorkOrderNr(20);
		tyreReadingRequest.setWorkOrderTaskNr(10);
		tyreReadingRequest.setLanguageId(5);
		try{
		tyreReadingServiceImpl.getTyreReadingDetails(tyreReadingRequest);
		}catch(Exception e){}
	}

}
