package com.tip.supplier.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.tip.supplier.model.FetchJobRequest;
import com.tip.supplier.service.FetchCustomisedJobService;

public class FetchCustomisedJobControllerTest {

	@Mock
	private FetchCustomisedJobService fetchCustomisedJobService;

	private FetchCustomisedJobController fetchCustomisedJobController;

	@Before
	public void setUp() throws Exception {
		fetchCustomisedJobController = new FetchCustomisedJobController();
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(fetchCustomisedJobController, "fetchCustomisedJobService",
				fetchCustomisedJobService);
	}

	@Test
	public void testJob() {
		try {
			FetchJobRequest fetchJobRequest = new FetchJobRequest();

			fetchCustomisedJobController.fetchJob(fetchJobRequest);
		} catch (Exception e) {
		}
	}

}