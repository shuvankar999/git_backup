package com.tip.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.repository.FetchPrivateKeyRepository;
import com.tip.service.FetchPrivateKeyService;

/**
 * @author Shuvankar Paul
 * Created on Jan 9, 2018
 * 
 */
@Service
@Transactional
public class FetchPrivateKeyServiceImpl implements FetchPrivateKeyService{

	static final Logger logger = LoggerFactory.getLogger(FetchPrivateKeyServiceImpl.class);
	
	@Autowired
	FetchPrivateKeyRepository fetchPrivateKeyRepository;
	
	@Override
	public String fetchKey(String ssoId) {

		String privateKeyAsString = null;
		try {
			Map<String, Object> resultMap = fetchPrivateKeyRepository.fetchKey(ssoId);
			if (null != resultMap) {
				for (Map.Entry<String, Object> entry : resultMap.entrySet())
				{
					if("#result-set-1".equalsIgnoreCase(entry.getKey()))
	                {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						Map<String, Object> returnMap= lst.get(0);
						privateKeyAsString = (String) returnMap.get("privateKey");
	                }
				}
			}
		} catch (Exception e) {
			logger.error("An error occurred : "+e);
		}
		return privateKeyAsString;
	}
}
