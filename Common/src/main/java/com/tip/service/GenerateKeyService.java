package com.tip.service;

import com.tip.model.SsoId;

/**
 * @author Shuvankar Paul
 * Created on Jan 9, 2018
 * 
 */
@FunctionalInterface
public interface GenerateKeyService {

	/**
	 * @param ssoId
	 */
	public SsoId getKey(String ssoId);

}
