package com.tip.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tip.model.SsoId;
import com.tip.service.GenerateKeyService;

/**
 * @author Shuvankar Paul Created on Jan 9, 2018
 * 
 */
@Controller
public class GenerateKeyController {

	static final Logger logger = Logger.getLogger(GenerateKeyController.class);

	@Autowired
	GenerateKeyService generateKeyService;

	@RequestMapping(value="/GetKey",method = RequestMethod.POST)
	@ResponseBody
	public SsoId getKey(@RequestBody SsoId ssoId) {
		
		String sso_id = ssoId.getSsoId();
		return generateKeyService.getKey(sso_id);
	 }
}
