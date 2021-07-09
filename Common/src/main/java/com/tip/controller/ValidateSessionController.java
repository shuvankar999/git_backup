package com.tip.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tip.model.SessionRequest;
import com.tip.service.ValidateSessionService;

@Controller
public class ValidateSessionController {


	@Autowired
	private ValidateSessionService validateSessionService;

	@RequestMapping(value = "/ValidateSession", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getValidateSessionDetails(@RequestBody SessionRequest sessionRequest) {
		return validateSessionService.getValidateSessionDetails(sessionRequest);
	}
}