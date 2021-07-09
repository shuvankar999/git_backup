package com.tip.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.model.Keys;
import com.tip.model.SsoId;
import com.tip.repository.GenerateKeyRepository;
import com.tip.service.GenerateKeyService;
import com.tip.util.EncodeDecodeUtil;

/**
 * @author Shuvankar Paul
 * Created on Jan 9, 2018
 * 
 */
@Service
@Transactional
public class GenerateKeyServiceImpl implements GenerateKeyService{

	static final Logger logger = LoggerFactory.getLogger(GenerateKeyServiceImpl.class);
	
	@Autowired
	GenerateKeyRepository generateKeyRepository;
	
	@Override
	public SsoId getKey(String ssoId) {

		SsoId response = new SsoId();
		try {
			logger.info("Generating Key pair for "+ssoId);
			Keys keys= EncodeDecodeUtil.buildKeyPair(ssoId);
			logger.info("Private Key: "+keys.getPrivateK());
			logger.info("Public Key: "+keys.getPublicK());
			Map<String, Object> resultMap = generateKeyRepository.saveKey(keys);
			if (("SUCCESS").equalsIgnoreCase((String)resultMap.get("Error_Cd"))) {
				response.setPublicK(keys.getPublicK());
				response.setSsoId(keys.getSsoId());
				response.setErrorCd((String)resultMap.get("Error_Cd"));
			}else{
				response.setErrorCd((String)resultMap.get("Error_Cd"));
			}
		} catch (Exception e) {
			response.setErrorCd("EXCEPTION_OCCURRED");
			logger.error("An error occurred :"+e);
		}
		return response;
	}
	
}
