package com.tip.estimation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.GenerateInvoiceRequest;
import com.tip.estimation.model.InvoiceRequest;
import com.tip.estimation.service.ImmediateInvService;
import com.tip.estimation.util.RestCallUtil;

@RestController
@RequestMapping("/service/estimation-crud-service/1.0/")
public class ImmediateInvController {

	@Autowired
	ImmediateInvService immediateInvService;

/*	@Autowired
	RestCallUtil restCallUtil;*/
	
	@RequestMapping(value = "immediateInvGenerate", method = RequestMethod.POST)
	public Map<String, Object> saveInvoice(@RequestBody InvoiceRequest invoiceRequest) {
		Map<String, Object> mapObject = immediateInvService.saveRebillData(invoiceRequest);
		
		//GenerateInvoiceRequest giRequest = new GenerateInvoiceRequest();
		
		/*giRequest.setAppCd("ESTN");
		giRequest.setEnvironment((String) mapObject.get("environment"));
		giRequest.setInvoiceNr((String) mapObject.get("invoiceNr"));
		giRequest.setInvoiceRunNr((Integer) mapObject.get("invoiceRunNr"));
		restCallUtil.generateInvoice(giRequest);*/
		
		return mapObject;
	}

}
