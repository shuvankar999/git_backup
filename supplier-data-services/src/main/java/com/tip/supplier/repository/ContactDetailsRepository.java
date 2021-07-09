package com.tip.supplier.repository;

import com.tip.supplier.model.ContactDetailsRequest;
import com.tip.supplier.model.ContactDetailsResponse;

@FunctionalInterface
public interface ContactDetailsRepository {

	public ContactDetailsResponse fetchContactDetails(ContactDetailsRequest contactDetailsRequest);

}
