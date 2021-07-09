package com.tip.estimation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.RecordAprovlPopupRequest;
import com.tip.estimation.service.RecordAprovlPopupService;


	@RestController
	@RequestMapping("/service/estimation-data-service/1.0/")
	public class RecordAprovlPopupController {
		
		@Autowired
		RecordAprovlPopupService recordAprovlPopupService;

		@RequestMapping(value = "recordAprovalPopup", method = RequestMethod.POST)
		@ResponseBody
		public Map<String, Object> getPopupList(@RequestBody RecordAprovlPopupRequest recordAprovlPopupRequest) {

			return recordAprovlPopupService.getPopupList(recordAprovlPopupRequest);

		}

	
	}
