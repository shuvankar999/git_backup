package com.tip.suppliermasterdata.service.impl;

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

import com.tip.suppliermasterdata.model.AllProcedureDataResponse;
import com.tip.suppliermasterdata.model.FetchDynamicMasterDataRequest;
import com.tip.suppliermasterdata.model.FetchDynamicMasterDataResponse;
import com.tip.suppliermasterdata.repository.FetchAllProceduresListRepository;
import com.tip.suppliermasterdata.repository.FetchDynamicMasterDataRepository;

public class FetchDynamicMasterDataServiceImplTest {

	FetchDynamicMasterDataServiceImpl fetchDynamicMasterDataServiceImpl = new FetchDynamicMasterDataServiceImpl();
	@Mock
	FetchAllProceduresListRepository fetchAllProceduresListRepository;

	@Mock
	FetchDynamicMasterDataRepository fetchDynamicMasterDataRepository;

	@Before
	public void beforesetup() {
		fetchDynamicMasterDataServiceImpl = new FetchDynamicMasterDataServiceImpl();
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(fetchDynamicMasterDataServiceImpl, "fetchAllProceduresListRepository",
				fetchAllProceduresListRepository);
		ReflectionTestUtils.setField(fetchDynamicMasterDataServiceImpl, "fetchDynamicMasterDataRepository",
				fetchDynamicMasterDataRepository);
	}

	@Test
	public void getAllProcedures1() {
		FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest = new FetchDynamicMasterDataRequest();

		FetchDynamicMasterDataResponse fetchDynamicMasterDataResponse = new FetchDynamicMasterDataResponse();
		AllProcedureDataResponse allProcedureDataResponse = new AllProcedureDataResponse();
		Map<String, Object> procDataMap = new HashMap<>();
		Map<String, Object> test = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		list.add(test);
		procDataMap.put("procDataList", list);
		allProcedureDataResponse.setKeyName("abc");
		allProcedureDataResponse.setObjName("countryDetailsLst");
		allProcedureDataResponse.setProcName("countryDetailsLst");
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);
		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);

		allProcedureDataResponse.setObjName("sdd");
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);
		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);

		allProcedureDataResponse.setKeyName(null);
		allProcedureDataResponse.setProcName(null);
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);
	}

	@Test
	public void getAllProcedures2() {
		FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest = new FetchDynamicMasterDataRequest();

		FetchDynamicMasterDataResponse fetchDynamicMasterDataResponse = new FetchDynamicMasterDataResponse();
		AllProcedureDataResponse allProcedureDataResponse = new AllProcedureDataResponse();
		Map<String, Object> procDataMap = new HashMap<>();
		Map<String, Object> test = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		list.add(test);
		procDataMap.put("procDataList", list);
		allProcedureDataResponse.setKeyName("abc");
		allProcedureDataResponse.setObjName("companyDetailsLst");
		allProcedureDataResponse.setProcName("countryDetailsLst");
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);

		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);
		allProcedureDataResponse.setObjName("sdd");
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);
		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);

		allProcedureDataResponse.setProcName(null);
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);
	}

	@Test
	public void getAllProcedures3() {
		FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest = new FetchDynamicMasterDataRequest();

		FetchDynamicMasterDataResponse fetchDynamicMasterDataResponse = new FetchDynamicMasterDataResponse();
		AllProcedureDataResponse allProcedureDataResponse = new AllProcedureDataResponse();
		Map<String, Object> procDataMap = new HashMap<>();
		Map<String, Object> test = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		list.add(test);
		procDataMap.put("procDataList", list);
		allProcedureDataResponse.setKeyName("abc");
		allProcedureDataResponse.setObjName("languageDetailsLst");
		allProcedureDataResponse.setProcName("xcc");
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);

		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);
		allProcedureDataResponse.setObjName("sdd");
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);
		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);

	}

	@Test
	public void getAllProcedures4() {
		FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest = new FetchDynamicMasterDataRequest();

		FetchDynamicMasterDataResponse fetchDynamicMasterDataResponse = new FetchDynamicMasterDataResponse();
		AllProcedureDataResponse allProcedureDataResponse = new AllProcedureDataResponse();
		Map<String, Object> procDataMap = new HashMap<>();
		Map<String, Object> test = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		list.add(test);
		procDataMap.put("procDataList", list);
		allProcedureDataResponse.setKeyName("abc");
		allProcedureDataResponse.setObjName("entitlementInvoiceDropdownDetailsLst");
		allProcedureDataResponse.setProcName("xcc");
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);

		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);
		allProcedureDataResponse.setObjName("sdd");
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);
		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);

	}

	@Test
	public void getAllProcedures5() {
		FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest = new FetchDynamicMasterDataRequest();

		FetchDynamicMasterDataResponse fetchDynamicMasterDataResponse = new FetchDynamicMasterDataResponse();
		AllProcedureDataResponse allProcedureDataResponse = new AllProcedureDataResponse();
		Map<String, Object> procDataMap = new HashMap<>();
		Map<String, Object> test = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		list.add(test);
		procDataMap.put("procDataList", list);
		allProcedureDataResponse.setKeyName("abc");
		allProcedureDataResponse.setObjName("entitlementLORDropdownDetailsLst");
		allProcedureDataResponse.setProcName("xcc");
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);

		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);
		allProcedureDataResponse.setObjName("sdd");
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);
		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);

	}

	@Test
	public void getAllProcedures6() {
		FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest = new FetchDynamicMasterDataRequest();

		FetchDynamicMasterDataResponse fetchDynamicMasterDataResponse = new FetchDynamicMasterDataResponse();
		AllProcedureDataResponse allProcedureDataResponse = new AllProcedureDataResponse();
		Map<String, Object> procDataMap = new HashMap<>();
		Map<String, Object> test = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		list.add(test);
		procDataMap.put("procDataList", list);
		allProcedureDataResponse.setKeyName("abc");
		allProcedureDataResponse.setObjName("addressTypeDropdownDetailsLst");
		allProcedureDataResponse.setProcName("xcc");
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);

		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);
		allProcedureDataResponse.setObjName("sdd");
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);
		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);

	}

	@Test
	public void getAllProcedures7() {
		FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest = new FetchDynamicMasterDataRequest();

		FetchDynamicMasterDataResponse fetchDynamicMasterDataResponse = new FetchDynamicMasterDataResponse();
		AllProcedureDataResponse allProcedureDataResponse = new AllProcedureDataResponse();
		Map<String, Object> procDataMap = new HashMap<>();
		Map<String, Object> test = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		list.add(test);
		procDataMap.put("procDataList", list);
		allProcedureDataResponse.setKeyName("abc");
		allProcedureDataResponse.setObjName("parkingLocationsLst");
		allProcedureDataResponse.setProcName("xcc");
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);

		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);
		allProcedureDataResponse.setObjName("sdd");
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);
		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);

	}

	@Test
	public void getAllProcedures8() {
		FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest = new FetchDynamicMasterDataRequest();

		FetchDynamicMasterDataResponse fetchDynamicMasterDataResponse = new FetchDynamicMasterDataResponse();
		AllProcedureDataResponse allProcedureDataResponse = new AllProcedureDataResponse();
		Map<String, Object> procDataMap = new HashMap<>();
		Map<String, Object> test = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		list.add(test);
		procDataMap.put("procDataList", list);
		allProcedureDataResponse.setKeyName("abc");
		allProcedureDataResponse.setObjName("assetOperationalStatusLst");
		allProcedureDataResponse.setProcName("xcc");
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);

		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);
		allProcedureDataResponse.setObjName("sdd");
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);
		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);

	}

	@Test
	public void getAllProcedures9() {
		FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest = new FetchDynamicMasterDataRequest();

		FetchDynamicMasterDataResponse fetchDynamicMasterDataResponse = new FetchDynamicMasterDataResponse();
		AllProcedureDataResponse allProcedureDataResponse = new AllProcedureDataResponse();
		Map<String, Object> procDataMap = new HashMap<>();
		Map<String, Object> test = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		list.add(test);
		procDataMap.put("procDataList", list);
		allProcedureDataResponse.setKeyName("abc");
		allProcedureDataResponse.setObjName("customerRefTypeValuesLst");
		allProcedureDataResponse.setProcName("xcc");
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);

		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);
		allProcedureDataResponse.setObjName("sdd");
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);
		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);

	}

	@Test
	public void getAllProcedures10() {
		FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest = new FetchDynamicMasterDataRequest();

		FetchDynamicMasterDataResponse fetchDynamicMasterDataResponse = new FetchDynamicMasterDataResponse();
		AllProcedureDataResponse allProcedureDataResponse = new AllProcedureDataResponse();
		Map<String, Object> procDataMap = new HashMap<>();
		Map<String, Object> test = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		list.add(test);
		procDataMap.put("procDataList", list);
		allProcedureDataResponse.setKeyName("abc");
		allProcedureDataResponse.setObjName("tpuRefTypeValuesLst");
		allProcedureDataResponse.setProcName("xcc");
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);

		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);
		allProcedureDataResponse.setObjName("sdd");
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);
		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);

	}

	@Test
	public void getAllProcedures11() {
		FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest = new FetchDynamicMasterDataRequest();

		FetchDynamicMasterDataResponse fetchDynamicMasterDataResponse = new FetchDynamicMasterDataResponse();
		AllProcedureDataResponse allProcedureDataResponse = new AllProcedureDataResponse();
		Map<String, Object> procDataMap = new HashMap<>();
		Map<String, Object> test = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		list.add(test);
		procDataMap.put("procDataList", list);
		allProcedureDataResponse.setKeyName("abc");
		allProcedureDataResponse.setObjName("equipDynamicTabsLst");
		allProcedureDataResponse.setProcName("xcc");
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);

		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);
		allProcedureDataResponse.setObjName("sdd");
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);
		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);

	}

	@Test
	public void getAllProcedures12() {
		FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest = new FetchDynamicMasterDataRequest();

		FetchDynamicMasterDataResponse fetchDynamicMasterDataResponse = new FetchDynamicMasterDataResponse();
		AllProcedureDataResponse allProcedureDataResponse = new AllProcedureDataResponse();
		Map<String, Object> procDataMap = new HashMap<>();
		Map<String, Object> test = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		test.put("Mod_Date", "dfs");
		list.add(test);
		procDataMap.put("procDataList", list);
		allProcedureDataResponse.setKeyName("abc");
		allProcedureDataResponse.setObjName("equipDynamicTabFieldsLst");
		allProcedureDataResponse.setProcName("xcc");
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);

		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);

		test.put("Mod_Date", null);
		list.add(test);
		procDataMap.put("procDataList", list);
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);
		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);

	}

	@Test
	public void getAllProcedures13() {
		FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest = new FetchDynamicMasterDataRequest();

		FetchDynamicMasterDataResponse fetchDynamicMasterDataResponse = new FetchDynamicMasterDataResponse();
		AllProcedureDataResponse allProcedureDataResponse = new AllProcedureDataResponse();
		Map<String, Object> procDataMap = new HashMap<>();
		Map<String, Object> test = new HashMap<>();
		test.put("Mod_Date", "dfs");
		List<Map<String, Object>> list = new ArrayList<>();
		list.add(test);
		procDataMap.put("procDataList", list);
		allProcedureDataResponse.setKeyName("abc");
		allProcedureDataResponse.setObjName("equipDynamicTabFieldPossibleValuesLst");
		allProcedureDataResponse.setProcName("xcc");
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);

		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);

		test.put("Mod_Date", null);
		list.add(test);
		procDataMap.put("procDataList", list);
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);
		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);

	}

	@Test
	public void getAllProcedures14() {
		FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest = new FetchDynamicMasterDataRequest();

		FetchDynamicMasterDataResponse fetchDynamicMasterDataResponse = new FetchDynamicMasterDataResponse();
		AllProcedureDataResponse allProcedureDataResponse = new AllProcedureDataResponse();
		Map<String, Object> procDataMap = new HashMap<>();
		Map<String, Object> test = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		list.add(test);
		procDataMap.put("procDataList", list);
		allProcedureDataResponse.setKeyName("abc");
		allProcedureDataResponse.setObjName("equipTypesLst");
		allProcedureDataResponse.setProcName("xcc");
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);

		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);
		allProcedureDataResponse.setObjName("sdd");
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);
		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);

	}

	@Test
	public void getAllProcedures15() {
		FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest = new FetchDynamicMasterDataRequest();

		FetchDynamicMasterDataResponse fetchDynamicMasterDataResponse = new FetchDynamicMasterDataResponse();
		AllProcedureDataResponse allProcedureDataResponse = new AllProcedureDataResponse();
		Map<String, Object> procDataMap = new HashMap<>();
		Map<String, Object> test = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		list.add(test);
		procDataMap.put("procDataList", list);
		allProcedureDataResponse.setKeyName("abc");
		allProcedureDataResponse.setObjName("equipCountryLst");
		allProcedureDataResponse.setProcName("xcc");
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);

		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);
		allProcedureDataResponse.setObjName("sdd");
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);
		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);

	}

	@Test
	public void getAllProcedures16() {
		FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest = new FetchDynamicMasterDataRequest();

		FetchDynamicMasterDataResponse fetchDynamicMasterDataResponse = new FetchDynamicMasterDataResponse();
		AllProcedureDataResponse allProcedureDataResponse = new AllProcedureDataResponse();
		Map<String, Object> procDataMap = new HashMap<>();
		Map<String, Object> test = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		list.add(test);
		procDataMap.put("procDataList", list);
		allProcedureDataResponse.setKeyName("abc");
		allProcedureDataResponse.setObjName("entitlementPoValidForDropdownDetailsLst");
		allProcedureDataResponse.setProcName("xcc");
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);

		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);
		allProcedureDataResponse.setObjName("sdd");
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);
		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);

	}

	@Test
	public void getAllProcedures17() {
		FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest = new FetchDynamicMasterDataRequest();

		FetchDynamicMasterDataResponse fetchDynamicMasterDataResponse = new FetchDynamicMasterDataResponse();
		AllProcedureDataResponse allProcedureDataResponse = new AllProcedureDataResponse();
		Map<String, Object> procDataMap = new HashMap<>();
		Map<String, Object> test = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		list.add(test);
		procDataMap.put("procDataList", list);
		allProcedureDataResponse.setKeyName("abc");
		allProcedureDataResponse.setObjName("tyreBrandsLst");
		allProcedureDataResponse.setProcName("xcc");
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);

		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);
		allProcedureDataResponse.setObjName("sdd");
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);
		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);

	}

	@Test
	public void getAllProcedures18() {
		FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest = new FetchDynamicMasterDataRequest();

		FetchDynamicMasterDataResponse fetchDynamicMasterDataResponse = new FetchDynamicMasterDataResponse();
		AllProcedureDataResponse allProcedureDataResponse = new AllProcedureDataResponse();
		Map<String, Object> procDataMap = new HashMap<>();
		Map<String, Object> test = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		test.put("Branch_Active_Startdate", "sss");
		test.put("Branch_Active_Enddate", "sss");
		list.add(test);
		procDataMap.put("procDataList", list);
		allProcedureDataResponse.setKeyName("abc");
		allProcedureDataResponse.setObjName("branchSettingsLst");
		allProcedureDataResponse.setProcName("xcc");
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);

		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);

		test.put("Branch_Active_Startdate", null);
		test.put("Branch_Active_Enddate", null);
		list.add(test);
		procDataMap.put("procDataList", list);
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest))
				.thenReturn(fetchDynamicMasterDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);
		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);

		allProcedureDataResponse.setProcName(null);
		fetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().add(allProcedureDataResponse);
		Mockito.when(fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,
				allProcedureDataResponse)).thenReturn(procDataMap);
		fetchDynamicMasterDataServiceImpl.getAllProcedures(fetchDynamicMasterDataRequest);
	}
}
