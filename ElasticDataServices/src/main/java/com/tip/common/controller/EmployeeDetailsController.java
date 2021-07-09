package com.tip.common.controller;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.common.model.EmployeeDetailsRequest;
import com.tip.common.model.EmployeeDetailsResponse;
import com.tip.common.service.EmployeeDetailsService;

@RestController
@RequestMapping("/service/elastic-data-service/1.0/")
public class EmployeeDetailsController {
	
	@Autowired
	EmployeeDetailsService employeeDetailsService;
	
	@RequestMapping(value = "fetchEmployeeDetails", method = RequestMethod.POST)
	@ResponseBody
	public EmployeeDetailsResponse fetchEmployeeDetails(@RequestBody EmployeeDetailsRequest employeeDetailsRequest) throws UnknownHostException {
		return employeeDetailsService.fetchEmployeeDetails(employeeDetailsRequest);
		
	}

}
