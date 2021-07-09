package com.tip.saveequipment.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.saveequipment.model.SaveEquipDetailsRequest;
import com.tip.saveequipment.service.SaveEquipDynamicService;

/**
 * @author Shuvankar Paul Created on Dec 15, 2017
 * 
 */
@RestController
@ComponentScan
@RequestMapping("/service/elastic-data-service/1.0/")

public class SaveEquipDynamicController {

	@Autowired
	SaveEquipDynamicService saveEquipDynamicService;

	@RequestMapping(value = "/saveEquipmentDynamic", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> saveEquipment(@Valid @RequestHeader(value = "ssoId") String ssoId,
			@RequestHeader(value = "appCd") String appCd,
			@RequestBody SaveEquipDetailsRequest saveEquipDetailsRequest) {

		Map<String, Object> saveEquipDetailsResponse = saveEquipDynamicService.saveEquip(saveEquipDetailsRequest, appCd,
				ssoId);

		return saveEquipDetailsResponse;

	}
}
