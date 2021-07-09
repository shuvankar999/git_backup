package com.tip.inspection.controller;

import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tip.inspection.service.ErrorCodeService;

@Component
@RequestMapping("/service/cia-data-service/1.0/")
public class ErrorCodeController {
	
	static final Logger logger = LoggerFactory.getLogger(ErrorCodeController.class); 
	
	@Autowired
	ErrorCodeService errorCodeService;
	
	@Scheduled(cron="0 0 */12 ? * *")
	public void  fetchMasterData() throws IOException   {
		
		logger.info(new Date() + " Starting the Cron expression");
		 errorCodeService.getFile();
	}
}
