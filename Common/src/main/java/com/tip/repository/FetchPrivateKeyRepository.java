package com.tip.repository;

import java.util.Map;

/**
 * @author Shuvankar Paul
 * Created on Jan 9, 2018
 * 
 */
@FunctionalInterface
public interface FetchPrivateKeyRepository {

	public Map<String, Object> fetchKey(String ssoId);
}
