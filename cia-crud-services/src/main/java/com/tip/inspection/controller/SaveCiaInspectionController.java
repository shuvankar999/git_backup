package com.tip.inspection.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.inspection.model.SaveCiaInspectionRequest;
import com.tip.inspection.model.SaveCiaInspectionResponse;
import com.tip.inspection.service.SaveCiaInspService;

@RestController
@RequestMapping("/service/cia-crud-service/1.0/")
public class SaveCiaInspectionController {
	@Autowired
	SaveCiaInspService saveCiaInspService;

	@RequestMapping(value = "/saveCiaInsp", method = RequestMethod.POST)
	@ResponseBody
	public SaveCiaInspectionResponse saveUserProfile(@RequestBody @Valid SaveCiaInspectionRequest saveCiaInspectionRequest) {
		return saveCiaInspService.saveCiaInsp(saveCiaInspectionRequest);
	}
}
