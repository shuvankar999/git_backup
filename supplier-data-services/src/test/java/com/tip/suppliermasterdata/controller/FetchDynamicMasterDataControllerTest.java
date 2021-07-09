package com.tip.suppliermasterdata.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.tip.suppliermasterdata.model.FetchDynamicMasterDataRequest;
import com.tip.suppliermasterdata.service.FetchDynamicMasterDataService;

public class FetchDynamicMasterDataControllerTest {
	
	FetchDynamicMasterDataController fetchDynamicMasterDataController;
	
	@Mock
	FetchDynamicMasterDataService fetchDynamicMasterDataService;
	
	@Before
	public void beforesetup(){
		fetchDynamicMasterDataController = new FetchDynamicMasterDataController();
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(fetchDynamicMasterDataController, "fetchDynamicMasterDataService", fetchDynamicMasterDataService);
	}

	@Test
	public void getCategory() {
		
	FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest = new FetchDynamicMasterDataRequest();
	fetchDynamicMasterDataController.getCategory(fetchDynamicMasterDataRequest);
	
	}

}
