package com.tip.inspection.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.inspection.model.InspectionDamageDataObject;
import com.tip.inspection.model.InspectionHeaderDataObject;
import com.tip.inspection.model.InspectionItemDataObject;
import com.tip.inspection.model.SaveCiaInspectionRequest;
import com.tip.inspection.model.SaveCiaInspectionResponse;
import com.tip.inspection.repository.SaveCiaInspDamageRepository;
import com.tip.inspection.repository.SaveCiaInspHeaderRepository;
import com.tip.inspection.repository.SaveCiaInspItemRepository;
import com.tip.inspection.service.SaveCiaInspService;

@Service
@Transactional
public class SaveCiaInspServiceImpl implements SaveCiaInspService {

	@Autowired
	SaveCiaInspHeaderRepository saveCiaInspHeaderRepository;
	
	@Autowired
	SaveCiaInspItemRepository saveCiaInspItemRepository;
	
	@Autowired
	SaveCiaInspDamageRepository saveCiaInspDamageRepository;


	@Override
	public SaveCiaInspectionResponse saveCiaInsp(SaveCiaInspectionRequest saveCiaInspectionRequest) {
		SaveCiaInspectionResponse saveCiaInspectionResponse = new SaveCiaInspectionResponse();
		for (InspectionHeaderDataObject inspectionHeaderDataObject : saveCiaInspectionRequest
				.getInspectionHeaderDataList()) {
			saveCiaInspectionResponse.getInspectionHeaderResponseList()
					.add(saveCiaInspHeaderRepository.saveCiaInsp(inspectionHeaderDataObject));
		}
		for (InspectionItemDataObject inspectionItemDataObject : saveCiaInspectionRequest.getInspectionItemDataList()) {
			if (inspectionItemDataObject.getInspId() == null) {
				inspectionItemDataObject
						.setInspId(saveCiaInspectionResponse.getInspectionHeaderResponseList().get(0).getInspId());
			}
			saveCiaInspectionResponse.getInspectionItemResponseList()
					.add(saveCiaInspItemRepository.saveCiaInsp(inspectionItemDataObject));
		}

		for (InspectionDamageDataObject inspectionDamageDataObject : saveCiaInspectionRequest
				.getInspectionDamageDataList()) {
			if (inspectionDamageDataObject.getInspId() == null) {
				inspectionDamageDataObject
						.setInspId(saveCiaInspectionResponse.getInspectionHeaderResponseList().get(0).getInspId());
			}
			saveCiaInspectionResponse.getInspectionDamageResponseList()
					.add(saveCiaInspDamageRepository.saveCiaInsp(inspectionDamageDataObject));
		}

		return saveCiaInspectionResponse;
	}
}