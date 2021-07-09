package com.tip.estimation.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.estimation.model.CreateSupplRequest;
import com.tip.estimation.model.EnrichAddtnlPartsObject;
import com.tip.estimation.model.EnrichWoObject;
import com.tip.estimation.model.VersionObject;
import com.tip.estimation.repository.CreateSupplHeaderRepository;
import com.tip.estimation.repository.CreateSupplLabourRepository;
import com.tip.estimation.repository.CreateSupplPartsRepository;
import com.tip.estimation.service.CreateSupplService;


@Service
public class CreateSupplServiceImpl implements CreateSupplService {
	
	static final Logger logger = LoggerFactory.getLogger(CreateSupplServiceImpl.class);
	
	@Autowired
	CreateSupplHeaderRepository createSupplHeaderRepository;

	@Autowired
	CreateSupplLabourRepository createSupplLabourRepository;
	
	@Autowired
	CreateSupplPartsRepository createSupplPartsRepository;
	
	@Override
	public Map<String, Object> createSuppl(CreateSupplRequest createSupplRequest, String ssoId) {
		Map<String, Object> responseMap = new HashMap();
		List<EnrichAddtnlPartsObject> partList = new ArrayList();
		String msg = null;
		VersionObject versionObject = createSupplHeader(createSupplRequest.getEstimationId(), ssoId, null);
		responseMap.put("versionObject", versionObject);
		
		for(EnrichWoObject enrichWoObject: createSupplRequest.getEstnWoList()) {
			partList.addAll(enrichWoObject.getEstnMatchedPartLists());
		}
		partList.addAll(createSupplRequest.getEstnUnMatchedPartLists());
		if(versionObject!=null) {
			msg = saveLabour(createSupplRequest.getEstnWoList(), createSupplRequest.getEstimationId(), versionObject, ssoId);
			if(!partList.isEmpty()) {
				msg = saveParts(partList, createSupplRequest.getEstimationId(), versionObject, ssoId);
			}
			responseMap.put("errorCd", msg);
		}
		responseMap.put("errorCd", msg);
		return responseMap;
	}
	private VersionObject createSupplHeader(BigDecimal estimationId, String ssoId, Integer supplementary) {
		return createSupplHeaderRepository.saveHeaderSuppl(estimationId, ssoId, supplementary);
	}
	private String saveLabour(List<EnrichWoObject> enrichWoObjectList, BigDecimal estimationId, VersionObject versionObject, String ssoId) {
		return createSupplLabourRepository.saveLabourSuppl(enrichWoObjectList, estimationId, versionObject, ssoId);
	}
	private String saveParts(List<EnrichAddtnlPartsObject> partList, BigDecimal estimationId, VersionObject versionObject, String ssoId) {
		return createSupplPartsRepository.savePartsSuppl(partList, estimationId, versionObject, ssoId);
	}
}
