package com.tip.estimation.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.estimation.model.EnrichResponse;
import com.tip.estimation.model.SaveEnrichDetails;
import com.tip.estimation.model.VersionObject;
import com.tip.estimation.repository.EnrichHeaderRepository;
import com.tip.estimation.repository.SaveSupplementaryRepository;
import com.tip.estimation.service.SaveEnrichService;
import com.tip.estimation.service.SaveEnrichOtherDetailsService;
import com.tip.estimation.service.SaveSupplementaryService;

@Service
public class SaveEnrichServiceImpl implements SaveEnrichService {

	static final Logger logger = LoggerFactory.getLogger(SaveSupplementaryService.class);

	@Autowired
	SaveSupplementaryRepository saveSupplHeaderRepo;

	@Autowired
	EnrichHeaderRepository enrichHeaderRepository;

	@Autowired
	SaveEnrichOtherDetailsService saveEnrichOtherDetailsService;

	@Override
	public EnrichResponse saveAll(SaveEnrichDetails saveEnrichDetails, boolean isSupplementary) {
		VersionObject versnObject = new VersionObject();
		if (isSupplementary) {
			Map<String, Object> retrunMap = saveSupplHeaderRepo.saveHeaderSuppl(saveEnrichDetails.getEstnHeaderList());
			List retrunList = (List) retrunMap.get("#result-set-1");
			Map<String, Object> versionMap = (Map<String, Object>) retrunList.get(0);
			versnObject.setVersion((Integer) versionMap.get("version"));
			versnObject.setSupplementary((Integer) versionMap.get("supplementary"));

		} else {
			versnObject = enrichHeaderRepository.saveEnrichHeader(saveEnrichDetails.getEstnHeaderList());
		}
		return saveEnrichOtherDetailsService.saveDetails(saveEnrichDetails, versnObject);
	}

}
