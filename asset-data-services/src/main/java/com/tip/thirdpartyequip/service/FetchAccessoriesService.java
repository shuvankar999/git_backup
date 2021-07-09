package com.tip.thirdpartyequip.service;

import java.util.Map;

import com.tip.thirdpartyequip.model.AccessoryRequest;

@FunctionalInterface
public interface FetchAccessoriesService {

	public Map<String, Object> fetchAccessories(AccessoryRequest accessoryRequest);

}
