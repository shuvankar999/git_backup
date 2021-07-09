package com.tip.inspection.repository;

import com.tip.inspection.model.InspectionDamageDataObject;
import com.tip.inspection.model.InspectionDamageResponseObject;

@FunctionalInterface
public interface SaveCiaInspDamageRepository {

	public InspectionDamageResponseObject saveCiaInsp(InspectionDamageDataObject inspectionDamageDataObject);

}
