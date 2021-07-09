package com.tip.repository;

import java.util.Map;

import com.tip.model.Keys;

/**
 * @author Shuvankar Paul
 * Created on Jan 9, 2018
 * 
 */
@FunctionalInterface
public interface GenerateKeyRepository {

	public Map<String, Object> saveKey(Keys keys);
}
