package com.tip.fetchattention.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tip.fetchattention.model.AttentionRequest;
import com.tip.fetchattention.model.AttentionResponse;
import com.tip.fetchattention.service.FetchAttentionService;

@RestController
@RequestMapping("/service/workorder-data-service/2.0/")
public class FetchAttentionController {

	@Autowired
	FetchAttentionService fetchAttentionService;

	@RequestMapping(value = "fetchAttention", method = RequestMethod.POST)
	public AttentionResponse getAttentionDetails(@RequestParam("unitNr") int unitNr, @RequestParam("langId") int langId) {

		AttentionRequest attentionRequest = new AttentionRequest();
		attentionRequest.setUnitNr(unitNr);
		attentionRequest.setLangId(langId);
		return fetchAttentionService.getAttentionDetails(attentionRequest);
	}
}