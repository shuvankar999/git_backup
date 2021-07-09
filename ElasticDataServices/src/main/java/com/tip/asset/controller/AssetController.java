package com.tip.asset.controller;

import java.net.UnknownHostException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.asset.controller.helper.AssetControllerHelper;
import com.tip.asset.model.ArrayOfAssetData;
import com.tip.asset.model.ArrayOfDynamicPosVal;
import com.tip.asset.model.AssetData;
import com.tip.asset.model.DynamicPosVal;
import com.tip.asset.model.RequestTextSearch;
import com.tip.asset.model.ResponseCount;
import com.tip.asset.service.AssetDataService;
import com.tip.asset.service.DynamicPosValService;

@RestController
@RequestMapping("/service/elastic-data-service/1.0/")

public class AssetController {

    @Autowired
    AssetDataService assetDataService;
    
    @Autowired
    DynamicPosValService dynamicPosValService;
    
    @Autowired
    AssetControllerHelper assetControllerHelper;

    public static final Logger logger = LoggerFactory.getLogger(AssetController.class);

    @RequestMapping(value = "getAssetData", method = RequestMethod.POST)
    @ResponseBody
    public ArrayOfAssetData getAssetData(@RequestBody AssetData assetData) {
    	ArrayOfAssetData lArrayOfAssetData = null;
        try {
        	HashMap<String, String> fields = (HashMap<String, String>) assetControllerHelper.setUnitFilter(assetData);
        	lArrayOfAssetData = assetDataService.getAssetData(fields);
        } catch (UnknownHostException e) {
        	logger.error("Error Encountered while searching Asset Data" ,e);
        }
        return lArrayOfAssetData;
    }
    
    @RequestMapping(value = "getAssetDataCount", method = RequestMethod.POST)
    @ResponseBody
    public ResponseCount getAssetDataCount(@RequestBody AssetData assetData) {
    	ResponseCount responseCount = new ResponseCount();
        try {
        	HashMap<String, String> fields = (HashMap<String, String>) assetControllerHelper.setUnitFilter(assetData);
        	responseCount.setCount(assetDataService.getAssetDataCount(fields));
        } catch (UnknownHostException e) {
        	logger.error("Error Encountered while Fetching Asset Count", e);
        }
        return responseCount;
    }
    
    @RequestMapping(value = "assetDataTextSearch", method = RequestMethod.POST)
    @ResponseBody
    public ArrayOfAssetData getAssetDataText(@RequestBody RequestTextSearch requestTextSearch) {
    	ArrayOfAssetData lArrayOfAssetData = null;
        try {
			lArrayOfAssetData = assetDataService.assetDataTextSearch(requestTextSearch.getText());
        } catch (UnknownHostException e) {
        	logger.error("Error Encountered while Asset TextSearch", e);
        }
        return lArrayOfAssetData;
    }
    
    @RequestMapping(value = "getDynamicPossibleValues", method = RequestMethod.POST)
       @ResponseBody
       public ArrayOfDynamicPosVal getCustomerDataForCustomerNr(@RequestBody DynamicPosVal dynamicPosVal) {
    	   ArrayOfDynamicPosVal arrayOfDynamicPosVal = null;
           try {
        	HashMap<String, String> fields = (HashMap<String, String>) assetControllerHelper.setFilterForPosVals(dynamicPosVal);
        	arrayOfDynamicPosVal = dynamicPosValService.getDynamicPosVals(fields);
           } catch (UnknownHostException e) {
           	logger.error("Error Encountered while Fetching dynamic possible values", e);
           }
           return arrayOfDynamicPosVal;
       }
    
    /*@RequestMapping(value = "dynamicFilter", method = RequestMethod.POST)
    @ResponseBody
    public ArrayOfAssetData dynamicFilter() {
    	ArrayOfAssetData lArrayOfAssetData = null;
        try {
        	lArrayOfAssetData = assetDataService.dynamicFilterAsset();
        } catch (UnknownHostException e) {
        	logger.error("Error Encountered while Fetching dynamic possible values", e);
        }
        return lArrayOfAssetData;
    }*/
}