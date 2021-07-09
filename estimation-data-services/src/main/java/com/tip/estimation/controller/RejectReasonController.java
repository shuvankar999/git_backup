package com.tip.estimation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.RejectReasonRequest;
import com.tip.estimation.service.RejectReasonService;


	@RestController
	@RequestMapping("/service/estimation-data-service/1.0/")
	public class RejectReasonController {
		
		@Autowired
		RejectReasonService rejectReasonService;

		@RequestMapping(value = "rejectReasonPopup", method = RequestMethod.POST)
		@ResponseBody
		public Map<String, Object> getPopupList(@RequestBody RejectReasonRequest rejectReasonRequest) {

			return rejectReasonService.getPopupList(rejectReasonRequest);

		}

	
	}
