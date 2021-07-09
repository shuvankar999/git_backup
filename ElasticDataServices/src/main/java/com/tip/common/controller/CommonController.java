package com.tip.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.common.model.DropDownRequest;
import com.tip.common.service.CommonService;

@RestController
@RequestMapping("/service/elastic-data-service/1.0/")
public class CommonController {
	
	@Autowired
	CommonService commonService;
	
	@PostMapping("dropdownList")
	@ResponseBody
	public List getDistinctList(@RequestBody DropDownRequest dropDownRequest) {
		
		return commonService.getDistinctList(dropDownRequest);
	}
}