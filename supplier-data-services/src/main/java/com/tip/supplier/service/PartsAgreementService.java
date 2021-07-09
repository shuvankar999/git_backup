package com.tip.supplier.service;

import com.tip.supplier.model.PartsAgreementRequest;
import com.tip.supplier.model.PartsAgreementResponse;
@FunctionalInterface
public interface PartsAgreementService {

	public PartsAgreementResponse fetchPartsAgreement(PartsAgreementRequest partsAgreementRequest);

}
