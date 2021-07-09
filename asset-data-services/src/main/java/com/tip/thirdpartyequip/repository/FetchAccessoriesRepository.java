package com.tip.thirdpartyequip.repository;

import java.util.Map;

import com.tip.thirdpartyequip.model.AccessoryRequest;

@FunctionalInterface
public interface FetchAccessoriesRepository {

	public Map<String, Object> fetchAccessories(AccessoryRequest accessoryRequest);

}
