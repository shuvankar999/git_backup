package com.tip.supplier.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.tip.supplier.model.FetchJobRequest;
import com.tip.supplier.repository.FetchCustomisedJobRepository;

public class FetchCustomisedJobServiceImplTest {

	@Mock
	private FetchCustomisedJobRepository fetchCustomisedJobRepository;

	private FetchCustomisedJobServiceImpl fetchCustomisedJobServiceImpl;

	@Before
	public void setUp() throws Exception {
		fetchCustomisedJobServiceImpl = new FetchCustomisedJobServiceImpl();
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(fetchCustomisedJobServiceImpl, "fetchCustomisedJobRepository",
				fetchCustomisedJobRepository);
	}

	@Test
	public void testJob() {
		try {
			FetchJobRequest fetchJobRequest = new FetchJobRequest();

			fetchJobRequest.setSupplierId(5);
			fetchJobRequest.setLangId(5);
			fetchJobRequest.setSsoId("5646521");
			fetchJobRequest.setErrorCd("abc");
			Map<String, Object> resultMap =new HashMap<>();
			Map<String, Object> map =new HashMap<>();
			Map<String, Object> map1 =new HashMap<>();
			map.put("fee", new BigDecimal(12.2));
			map.put("jobName","jobName");
			map1.put("jobName","jobName");
			List<Map<String, Object>> list =new ArrayList<>(); 
			list.add(map);
			list.add(map1);
			resultMap.put("#result-set-1",list );
			
			Mockito.when(fetchCustomisedJobRepository.fetchCustomisedJob(fetchJobRequest)).thenReturn(resultMap);
			fetchCustomisedJobServiceImpl.fetchCustomisedJob(fetchJobRequest);
			
		} catch (Exception e) {
		}
	}
}
