package com.tip.inspection.service;

import com.tip.inspection.model.DocuwareDetails;

@FunctionalInterface
public interface GetDocuwareDetailsService {

	public DocuwareDetails getDocuwareDetails(String appCd);

}
