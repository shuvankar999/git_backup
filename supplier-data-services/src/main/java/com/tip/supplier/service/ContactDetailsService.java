package com.tip.supplier.service;

import com.tip.supplier.model.ContactDetailsRequest;
import com.tip.supplier.model.ContactDetailsResponse;

@FunctionalInterface
public interface ContactDetailsService {

	public ContactDetailsResponse fetchContactDetails(ContactDetailsRequest contactDetailsRequest);

}
