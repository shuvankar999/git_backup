package com.tip.common.service;

import java.net.UnknownHostException;

import com.tip.common.model.EmployeeDetailsRequest;
import com.tip.common.model.EmployeeDetailsResponse;
@FunctionalInterface
public interface EmployeeDetailsService {

	public EmployeeDetailsResponse fetchEmployeeDetails(EmployeeDetailsRequest employeeDetailsRequest) throws UnknownHostException;

}
