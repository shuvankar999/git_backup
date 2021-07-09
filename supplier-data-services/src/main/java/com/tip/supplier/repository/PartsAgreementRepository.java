package com.tip.supplier.repository;

import com.tip.supplier.model.PartsAgreementRequest;
import com.tip.supplier.model.PartsAgreementResponse;
@FunctionalInterface
public interface PartsAgreementRepository {

	public PartsAgreementResponse fetchPartsAgreement(PartsAgreementRequest partsAgreementRequest);

}
