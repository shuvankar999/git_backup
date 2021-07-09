package com.tip.thirdpartyequip.repository;

import java.util.Map;

import com.tip.thirdpartyequip.model.ValidSerialNrRequest;

@FunctionalInterface
public interface ValidateSerialRepository {

	public Map<String, Object> validateSerialNr(ValidSerialNrRequest validSerialNrRequest);

}
