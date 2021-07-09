package com.tip.supplier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.supplier.model.ContactDetailsRequest;
import com.tip.supplier.model.ContactDetailsResponse;
import com.tip.supplier.repository.ContactDetailsRepository;
import com.tip.supplier.service.ContactDetailsService;

@Service
@Transactional
public class ContactDetailsServiceImpl implements ContactDetailsService {

	@Autowired
	ContactDetailsRepository contactDetailsRepository;
	
	@Override
	public ContactDetailsResponse fetchContactDetails(ContactDetailsRequest contactDetailsRequest)
	{
		return contactDetailsRepository.fetchContactDetails(contactDetailsRequest);
	}
}