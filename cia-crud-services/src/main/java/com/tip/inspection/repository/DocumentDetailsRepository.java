package com.tip.inspection.repository;

import java.util.Map;

import com.tip.inspection.model.CiaInspectionDocuware;

public interface DocumentDetailsRepository {

	public Map<String, Object> saveDetails(CiaInspectionDocuware docInput);

}
