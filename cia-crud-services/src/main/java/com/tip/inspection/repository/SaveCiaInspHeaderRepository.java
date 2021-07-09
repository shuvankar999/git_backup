package com.tip.inspection.repository;

import com.tip.inspection.model.InspectionHeaderDataObject;
import com.tip.inspection.model.InspectionHeaderResponseObject;

@FunctionalInterface
public interface SaveCiaInspHeaderRepository {

	public InspectionHeaderResponseObject saveCiaInsp(InspectionHeaderDataObject inspectionHeaderDataList);

}
