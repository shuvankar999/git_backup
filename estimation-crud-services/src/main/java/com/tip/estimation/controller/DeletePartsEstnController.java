package com.tip.estimation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.DeletePartsRequest;
import com.tip.estimation.service.DeletePartsEstnService;


	@RestController
	@RequestMapping("/service/estimation-crud-service/1.0/")
	public class DeletePartsEstnController {

		
		@Autowired
		DeletePartsEstnService deletePartsEstnService;
		
		@RequestMapping(value = "deletePartsEstn", method = RequestMethod.POST)
		@ResponseBody
		public  Object deleteParts(@RequestBody DeletePartsRequest deletePartsRequest) {
			return deletePartsEstnService.deleteParts(deletePartsRequest);
		}
}
