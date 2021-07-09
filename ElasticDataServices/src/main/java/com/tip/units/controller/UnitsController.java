package com.tip.units.controller;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

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
import com.tip.units.controller.helper.UnitsControllerHelper;
import com.tip.units.model.ArrayOfUnitsData;
import com.tip.units.model.UnitsData;
import com.tip.units.service.UnitsDataService;

/**
 * Created by 885155 on 9/14/2017.
 * updated by 398072 on 9/19/2017
 */
@RestController
@RequestMapping("/service/elastic-data-service/1.0/")

public class UnitsController {

    @Autowired
    UnitsDataService unitsDataService;
    
    @Autowired
    UnitsControllerHelper unitsControllerHelper;
  
    public static final Logger logger = LoggerFactory.getLogger(UnitsController.class);

    @RequestMapping(value = "getUnitsData", method = RequestMethod.POST)
    @ResponseBody
    public ArrayOfUnitsData getUnitsData(@RequestBody UnitsData unitData, HttpServletResponse response) {
    	ArrayOfUnitsData lArrayOfUnitData = null;
        try {
        	Map<String, Object> fields = unitsControllerHelper.setUnitFilter(unitData);
        	lArrayOfUnitData = unitsDataService.getUnitsData(fields);
        } catch (UnknownHostException e) {
        	logger.error("Error Encountered while searching Customer Data");
        }
        return lArrayOfUnitData;
    }
    
    @RequestMapping(value = "getUnitsDataCount", method = RequestMethod.POST)
    @ResponseBody
    public ResponseCount getUnitsDataCount(@RequestBody UnitsData unitData, HttpServletResponse response) {
    	ResponseCount responseCount = new ResponseCount();
        try {
        	HashMap<String, Object> fields = (HashMap<String, Object>) unitsControllerHelper.setUnitFilter(unitData);
        	responseCount.setCount(unitsDataService.getUnitsDataCount(fields));
        } catch (UnknownHostException e) {
        	logger.error("Error Encountered while searching Customer Data");
        }
        return responseCount;
    }
    
    @RequestMapping(value = "unitDataTextSearch", method = RequestMethod.POST)
    @ResponseBody
    public ArrayOfUnitsData getUnitsTextSearch(@RequestBody RequestTextSearch requestTextSearch, HttpServletResponse response) {
    	ArrayOfUnitsData lArrayOfUnitData = null;
        try {
			lArrayOfUnitData = unitsDataService.unitsDataTextSearch(requestTextSearch.getText());
        } catch (UnknownHostException e) {
        	logger.error("Error Encountered while searching Customer Data");
        }
        return lArrayOfUnitData;
    }
}
