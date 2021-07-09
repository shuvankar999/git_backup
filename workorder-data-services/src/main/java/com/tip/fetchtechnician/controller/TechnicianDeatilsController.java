package com.tip.fetchtechnician.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.fetchtechnician.model.TechnicianDetails;
import com.tip.fetchtechnician.model.TechnicianDetailsRequest;
import com.tip.fetchtechnician.service.TechnicianDetailsService;

@RestController
@RequestMapping("/service/workorder-data-service/2.0/")

public class TechnicianDeatilsController {

	@Autowired
	TechnicianDetailsService technicianDetailsService;

	@RequestMapping(value = "technician", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, List<TechnicianDetails>> getTechnicianDetails(@RequestBody TechnicianDetailsRequest technicianDetailsRequest) {
		return technicianDetailsService.getTechnicianDetails(technicianDetailsRequest);
	}
}