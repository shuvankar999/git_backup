package com.tip.thirdpartyequip.service;

import java.util.Map;

import com.tip.thirdpartyequip.model.ValidLicenseRequest;
import com.tip.thirdpartyequip.model.ValidSerialNrRequest;

public interface ValidateService {

	public Map<String, Object> validateSerialNr(ValidSerialNrRequest validSerialNrRequest);
	public Map<String, Object> validateLicenseNr(ValidLicenseRequest validLicenseRequest);

}
