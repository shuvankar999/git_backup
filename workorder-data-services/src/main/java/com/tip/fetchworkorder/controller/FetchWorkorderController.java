package com.tip.fetchworkorder.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tip.fetchworkorder.model.WorkPackResponse;
import com.tip.fetchworkorder.service.FetchWorkorderService;

@RestController
@RequestMapping("/service/workorder-data-service/2.0/")
public class FetchWorkorderController {

	@Autowired
	FetchWorkorderService fetchWorkorderService;

	@RequestMapping(value = "fetchWorkOrder/{workpack_nr}/{language_id}", method = RequestMethod.POST)
	public WorkPackResponse getWPackWorderTaskList(@PathVariable("workpack_nr") BigDecimal workPackNr, @PathVariable("language_id") int languageId) {
		return fetchWorkorderService.getWPackWorderTaskList(workPackNr, languageId);
	}
}