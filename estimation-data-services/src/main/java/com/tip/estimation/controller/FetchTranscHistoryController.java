package com.tip.estimation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.InvoiceRequest;
import com.tip.estimation.service.FetchTranscHistoryService;

@RestController
@RequestMapping("/service/estimation-data-service/1.0/")
public class FetchTranscHistoryController {
	 
	@Autowired
	FetchTranscHistoryService fetchTranscHistoryService;
	

	@RequestMapping(value = "transactionHistory", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getTransaction(@RequestBody InvoiceRequest invoiceRequest) {
		
		return fetchTranscHistoryService.getTransaction(invoiceRequest);
	}

}
