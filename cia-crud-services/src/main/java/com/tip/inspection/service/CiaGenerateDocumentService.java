package com.tip.inspection.service;

import com.tip.inspection.model.ResponseObject;

@FunctionalInterface
public interface CiaGenerateDocumentService {

	public ResponseObject generateDocument(String inspType ,String inspId);

}
