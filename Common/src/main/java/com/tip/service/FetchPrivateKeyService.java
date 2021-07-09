package com.tip.service;

/**
 * @author Shuvankar Paul
 * Created on Jan 9, 2018
 * 
 */
@FunctionalInterface
public interface FetchPrivateKeyService {

	/**
	 * @param ssoId
	 */
	public String fetchKey(String ssoId);

}
