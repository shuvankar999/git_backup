package com.tip.estimation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.PipelineRequest;
import com.tip.estimation.model.TransactionRequest;
import com.tip.estimation.service.AddPipelineService;
import com.tip.estimation.service.AddTransactionService;

@RestController
@RequestMapping("/service/estimation-crud-service/1.0/")
public class AddTransactionController {
	
	@Autowired
	AddTransactionService addTransactionService;
	

	@RequestMapping(value = "addTransaction", method = RequestMethod.POST)
	@ResponseBody
	public Object addTransaction(@RequestBody TransactionRequest transactionRequest) {
		
		return addTransactionService.addTransaction(transactionRequest);
	}

	

}
