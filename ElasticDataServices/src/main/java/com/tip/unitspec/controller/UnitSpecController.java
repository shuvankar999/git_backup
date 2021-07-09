package com.tip.unitspec.controller;

import java.net.UnknownHostException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.asset.model.RequestTextSearch;
import com.tip.asset.model.ResponseCount;
import com.tip.unitspec.controller.helper.UnitSpecControllerHelper;
import com.tip.unitspec.model.ArrayOfUnitSpecData;
import com.tip.unitspec.model.UnitSpecData;
import com.tip.unitspec.service.UnitSpecDataService;

/**
 * Created by 885155 on 9/14/2017.
 * updated by 398072 on 9/19/2017
 */
@RestController
@RequestMapping("/service/elastic-data-service/1.0/")

public class UnitSpecController {

    @Autowired
    UnitSpecDataService unitsDataService;
    
    @Autowired
    UnitSpecControllerHelper unitSpecControllerHelper;

    public static final Logger logger = LoggerFactory.getLogger(UnitSpecController.class);
    
    @RequestMapping(value = "getUnitSpecData", method = RequestMethod.POST)
    @ResponseBody
    public ArrayOfUnitSpecData getUnitData(@RequestBody UnitSpecData unitSpecData, HttpServletResponse response) {
    	ArrayOfUnitSpecData lArrayOfUnitSpecData = null;
        try {
        	HashMap<String, String> fields = (HashMap<String, String>) unitSpecControllerHelper.setUnitFilter(unitSpecData);
        	lArrayOfUnitSpecData = unitsDataService.getUnitData(fields);
        } catch (UnknownHostException e) {
        	logger.error("Error Encountered while searching Unit Specification Data");
        }
        return lArrayOfUnitSpecData;
    }
    
    @RequestMapping(value = "getUnitSpecDataCount", method = RequestMethod.POST)
    @ResponseBody
    public ResponseCount getUnitSpecDataCount(@RequestBody UnitSpecData unitSpecData, HttpServletResponse response) {
    	ResponseCount responseCount = new ResponseCount();
        try {
        	HashMap<String, String> fields = (HashMap<String, String>) unitSpecControllerHelper.setUnitFilter(unitSpecData);
        	responseCount.setCount(unitsDataService.getUnitSpecDataCount(fields));
        } catch (UnknownHostException e) {
        	logger.error("Error Encountered while fetching Count Records in Unit Specification Data");
        }
        return responseCount;
    }
    
    @RequestMapping(value = "unitSpecDataTextSearch", method = RequestMethod.POST)
    @ResponseBody
    public ArrayOfUnitSpecData unitSpecDataTextSearch(@RequestBody RequestTextSearch requestTextSearch, HttpServletResponse response) {
    	ArrayOfUnitSpecData lArrayOfUnitSpecData = null;
        try {
			lArrayOfUnitSpecData = unitsDataService.unitDataSpecTextSearch(requestTextSearch.getText());
        } catch (UnknownHostException e) {
        	logger.error("Error Encountered while searching Text in Unit Specification Data");
        }
        return lArrayOfUnitSpecData;
    }
}