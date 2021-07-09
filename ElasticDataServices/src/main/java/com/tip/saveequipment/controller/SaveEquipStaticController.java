package com.tip.saveequipment.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.saveequipment.model.BaseResponse;
import com.tip.saveequipment.model.SaveEquipDetailsRequest;
import com.tip.saveequipment.service.SaveEquipStaticSerivce;
import com.tip.saveequipment.service.SaveEquipStaticStatusSerivce;

@RestController
@ComponentScan
@RequestMapping("/service/elastic-data-service/1.0/")

public class SaveEquipStaticController {

	@Autowired
	SaveEquipStaticSerivce saveEquipStaticSerivce;
	
	@Autowired
	SaveEquipStaticStatusSerivce saveEquipStaticStatusSerivce;

	@RequestMapping(value = "/saveEquipmentStatic", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<BaseResponse> saveEquipmentStatic(@Valid @RequestHeader(value = "ssoId") String ssoId,
			@RequestHeader(value = "appCd") String appCd,
			@RequestBody SaveEquipDetailsRequest saveEquipDetailsRequest) {

		List<BaseResponse> saveEquipStaticResponse = saveEquipStaticSerivce.saveEquipmentStatic(saveEquipDetailsRequest,
				appCd, ssoId);
		return saveEquipStaticResponse;
	}
	
	@RequestMapping(value = "/saveEquipStaticStatus", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<BaseResponse> saveEquipmentStaticStatus(@Valid @RequestHeader(value = "ssoId") String ssoId,
			@RequestHeader(value = "appCd") String appCd,
			@RequestBody SaveEquipDetailsRequest saveEquipDetailsRequest) {

		List<BaseResponse> saveEquipStaticResponse = saveEquipStaticStatusSerivce.saveEquipmentStaticStatus(saveEquipDetailsRequest,
				appCd, ssoId);
		return saveEquipStaticResponse;
	}
}
