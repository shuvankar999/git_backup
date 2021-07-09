package com.tip.brakereading.service.impl;
import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.tip.brakereading.model.BrakeReadingRequest;
import com.tip.brakereading.repository.BrakeReadingRepository;

public class BrakeReadingServiceImplTest {
	
	private BrakeReadingServiceImpl brakeReadingServiceImpl;
	
	@Mock
	BrakeReadingRepository brakeReadingRepository;

	@Before
	public void setUp() throws Exception {
		brakeReadingServiceImpl = new BrakeReadingServiceImpl();
		ReflectionTestUtils.setField(brakeReadingServiceImpl, "brakeReadingRepository", brakeReadingRepository);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testB() {
		BrakeReadingRequest brakeReadingRequest=new BrakeReadingRequest();
		brakeReadingRequest.setWorkPackNr(BigDecimal.TEN);
		brakeReadingRequest.setWorkOrderNr(20);
		brakeReadingRequest.setWorkOrderTaskNr(10);
		brakeReadingRequest.setLanguageId(5);
		try{
		brakeReadingServiceImpl.getBrakeReadingDetails(brakeReadingRequest);
		}catch(Exception e){}
	}
}

