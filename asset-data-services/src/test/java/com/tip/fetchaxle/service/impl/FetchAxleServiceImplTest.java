package com.tip.fetchaxle.service.impl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.tip.fetchaxle.model.FetchAxleRequest;
import com.tip.fetchaxle.repository.FetchAxleRepository;

public class FetchAxleServiceImplTest {
	
	private FetchAxleServiceImpl fetchAxleServiceImpl;
	
	@Mock
	private FetchAxleRepository fetchAxleRepository;	
	
	@Before
	public void setUp() throws Exception {
		fetchAxleServiceImpl = new FetchAxleServiceImpl();
		ReflectionTestUtils.setField(fetchAxleServiceImpl, "fetchAxleRepository", fetchAxleRepository);
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testF() {
		FetchAxleRequest fetchAxleRequest=new FetchAxleRequest();
		fetchAxleRequest.setUnitNr(1);
		try{
			fetchAxleServiceImpl.getNoOfAxlesForAsset(fetchAxleRequest);			
		}catch(Exception e){}		
	}
}
