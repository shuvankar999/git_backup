package com.tip.inspection.service;

import com.tip.inspection.model.SaveCiaInspectionRequest;
import com.tip.inspection.model.SaveCiaInspectionResponse;

@FunctionalInterface
public interface SaveCiaInspService {

	public SaveCiaInspectionResponse saveCiaInsp(SaveCiaInspectionRequest saveCiaInspectionRequest);

}
