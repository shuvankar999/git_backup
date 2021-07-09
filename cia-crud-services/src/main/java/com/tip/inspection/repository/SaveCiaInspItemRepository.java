package com.tip.inspection.repository;

import com.tip.inspection.model.InspectionItemDataObject;
import com.tip.inspection.model.InspectionItemResponseObject;

@FunctionalInterface
public interface SaveCiaInspItemRepository {

	public InspectionItemResponseObject saveCiaInsp(InspectionItemDataObject inspectionItemDataObject);

}
