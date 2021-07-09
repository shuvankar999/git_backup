package com.tip.estimation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.InvoiceRequest;
import com.tip.estimation.model.InvoiceResponse;
import com.tip.estimation.service.ImmediateInvoiceService;

@RestController
@RequestMapping("/service/estimation-data-service/1.0/")
public class ImmediateInvoiceController {
	 
	@Autowired
	ImmediateInvoiceService immediateInvoiceService;
	

	@RequestMapping(value = "immediateInvoiceData", method = RequestMethod.POST)
	@ResponseBody
	public InvoiceResponse getInvoiceData(@RequestBody InvoiceRequest invoiceRequest) {
		
		return immediateInvoiceService.getInvoiceData(invoiceRequest);
	}

}
