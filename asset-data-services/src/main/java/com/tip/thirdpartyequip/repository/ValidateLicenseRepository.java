package com.tip.thirdpartyequip.repository;

import java.util.Map;

import com.tip.thirdpartyequip.model.ValidLicenseRequest;

@FunctionalInterface
public interface ValidateLicenseRepository {

	public Map<String, Object> validateLicenseNr(ValidLicenseRequest validLicenseRequest);

}
