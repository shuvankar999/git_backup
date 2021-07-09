package com.tip.inspection.controller;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.inspection.model.InspectionRequest;
import com.tip.inspection.model.InspectionResponse;
import com.tip.inspection.service.InspectionService;

@RestController
@RequestMapping("/service/workorder-data-service/2.0/")
public class InspectionController {

	@Autowired
	InspectionService inspectionService;

	@RequestMapping(value = "getInspection", method = RequestMethod.POST)
	@ResponseBody
	public InspectionResponse getResourcePipeline(@Valid @RequestBody InspectionRequest inspectionRequest) {
		return inspectionService.getInspectionDetails(inspectionRequest);
	}
}