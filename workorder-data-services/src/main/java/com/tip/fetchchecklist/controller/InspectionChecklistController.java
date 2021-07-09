package com.tip.fetchchecklist.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.fetchchecklist.model.InspectionChecklistRequest;
import com.tip.fetchchecklist.model.InspectionChecklistResponse;
import com.tip.fetchchecklist.model.MaintenanceInspectionRequest;
import com.tip.fetchchecklist.model.MaintenanceInspectionResponse;
import com.tip.fetchchecklist.service.InspectionChecklistService;

@RestController
@RequestMapping("/service/workorder-data-service/2.0/")
public class InspectionChecklistController {

	@Autowired
	InspectionChecklistService inspectionChecklistService;

	@RequestMapping(value = "getInspectionChecklist", method = RequestMethod.POST)
	@ResponseBody public InspectionChecklistResponse getInspectionChecklist(@Valid @RequestBody InspectionChecklistRequest inspectionChecklistRequest) {
		return inspectionChecklistService.getInspectionChecklistDetails(inspectionChecklistRequest);
	}

	@RequestMapping(value = "getMaintenanceInspectionList", method = RequestMethod.POST)
	@ResponseBody public MaintenanceInspectionResponse getMaintenanceInspectionList(@Valid @RequestBody MaintenanceInspectionRequest maintenanceInspectionRequest) {
		return inspectionChecklistService.getMaintenanceInspectionDetails(maintenanceInspectionRequest);
	}
}