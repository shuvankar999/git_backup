package com.tip.technicianjob.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tip.technicianjob.service.TechnicianJobDataService;

@RestController
@RequestMapping("/service/workorder-data-service/2.0/")

public class TechnicianJobDataController {

	@Autowired
	TechnicianJobDataService technicianJobDataService;

	@RequestMapping(value = "techJobDataList/{sso_id}/{branch_id}", method = RequestMethod.POST)
	public Map<String, Object> getTechJobDataList(@PathVariable("sso_id") String ssoId, @PathVariable("branch_id") String branchId) {
		return technicianJobDataService.getTechJobDataList(ssoId, branchId);
	}
}