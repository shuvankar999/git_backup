package com.tip.estimation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.SuplymntryRequest;
import com.tip.estimation.model.SuplymntryResponse;
import com.tip.estimation.service.SuplymntryDetailsService;

	@RestController
	@RequestMapping("/service/estimation-data-service/1.0/")
	public class SuplymntryDetailsController {
		
		@Autowired
		SuplymntryDetailsService suplymntryDetailsService;
		
		@RequestMapping(value = "fetchSupplymntryDetails", method = RequestMethod.POST)
		@ResponseBody
		public SuplymntryResponse  getSupplymntryDetails(@RequestBody SuplymntryRequest suplymntryRequest){
			return suplymntryDetailsService.getSupplymntryDetails(suplymntryRequest);
			
			
		}
	}
