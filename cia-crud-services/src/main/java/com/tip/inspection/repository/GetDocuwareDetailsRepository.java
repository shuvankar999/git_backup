package com.tip.inspection.repository;

import java.util.Map;

public interface GetDocuwareDetailsRepository {

	public Map<String, Object> fetchDocuwareDetails(String appCd);

}
