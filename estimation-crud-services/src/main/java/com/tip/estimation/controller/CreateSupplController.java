package com.tip.estimation.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.CreateSupplRequest;
import com.tip.estimation.service.CreateSupplService;

@RestController
@RequestMapping("/service/estimation-crud-service/1.0/")
public class CreateSupplController {
	

	@Autowired
	CreateSupplService createSupplService;


	@PostMapping("createSuppl")
	public Map<String, Object> createSupplementary(@RequestBody CreateSupplRequest createSupplRequest, HttpServletRequest request) {
		return createSupplService.createSuppl(createSupplRequest, request.getHeader("ssoId"));
	}
}


