package com.tip.customer.controller;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

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
import com.tip.customer.controller.helper.CustomerControllerHelper;
import com.tip.customer.model.ArrayOfCustomerData;
import com.tip.customer.model.CustomerData;
import com.tip.customer.model.CustomerListRequest;
import com.tip.customer.service.CustFetchFilterService;
import com.tip.customer.service.CustomerDataService;
import com.tip.customer.service.CustomerListService;
import com.tip.customer.service.CustomerProcessFilterService;
import com.tip.elasticsearch.model.EnterpriseSearchData;
import com.tip.equipmentdetails.model.FetchFilterDetails;
import com.tip.equipmentdetails.model.FetchFilterDetailsRequest;

@RestController
@RequestMapping("/service/elastic-data-service/1.0/")

public class CustomerController {

    @Autowired
    CustomerDataService customerDataService;
    
    @Autowired
    CustomerControllerHelper customerControllerHelper;
    
    @Autowired
    CustFetchFilterService custFetchFilterService;
    
    @Autowired
    CustomerProcessFilterService customerProcessFilterService;
    
    @Autowired
    CustomerListService customerListService;
    
    public static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @RequestMapping(value = "getCustomerData", method = RequestMethod.POST)
    @ResponseBody
    public ArrayOfCustomerData getCustomerData(@RequestBody CustomerData customerData) {
    	ArrayOfCustomerData lArrayOfCustomerData = null;
        try {
        	HashMap<String, String> fields = (HashMap<String, String>) customerControllerHelper.setCustomerFilter(customerData);
        	lArrayOfCustomerData = customerDataService.getCustomerData(fields);
        } catch (UnknownHostException e) {
        	logger.error("Error Encountered while searching Customer Data",e);
        }
        return lArrayOfCustomerData;
    }
    
    @RequestMapping(value = "getCustomerDataCount", method = RequestMethod.POST)
    @ResponseBody
    public ResponseCount getCustomerDataCount(@RequestBody CustomerData customerData) {
    	ResponseCount responseCount = new ResponseCount();
        try {
        	HashMap<String, String> fields = (HashMap<String, String>) customerControllerHelper.setCustomerFilter(customerData);
        	responseCount.setCount(customerDataService.getCustomerDataCount(fields));
        } catch (UnknownHostException e) {
        	logger.error("Error Encountered while searching Customer count",e);
        }
        return responseCount;
    }
    
    @RequestMapping(value = "customerDataTextSearch", method = RequestMethod.POST)
    @ResponseBody
    public ArrayOfCustomerData getCustomerData(@RequestBody RequestTextSearch requestTextSearch) {
    	ArrayOfCustomerData lArrayOfCustomerData = null;
        try {
			lArrayOfCustomerData = customerDataService.customerDataTextSearch(requestTextSearch.getText());
        } catch (UnknownHostException e) {
        	logger.error("Error Encountered while fetching Customer Data",e);
        }
        return lArrayOfCustomerData;
    }
    
    @RequestMapping(value = "getCustomerFromCustomerNumber", method = RequestMethod.POST)
       @ResponseBody
       public ArrayOfCustomerData getCustomerDataForCustomerNr(@RequestBody CustomerData customerData) {
       	ArrayOfCustomerData lArrayOfCustomerData = null;
           try {
        	HashMap<String, String> fields = (HashMap<String, String>) customerControllerHelper.setFilterForCustomerNr(customerData);
        	lArrayOfCustomerData = customerDataService.getCustomerData(fields);
           } catch (UnknownHostException e) {
           	logger.error("Error Encountered while searching Customer Data",e);
           }
           return lArrayOfCustomerData;
       }
    
	@RequestMapping(value = "custFetchFilter", method = RequestMethod.POST)
	@ResponseBody
	public FetchFilterDetails getFilterDetails(@RequestBody FetchFilterDetailsRequest fetchFilterDetailsRequest) {
		return custFetchFilterService.getFilterDetils(fetchFilterDetailsRequest);
	}
	
	@RequestMapping(value = "custProcessFilter", method = RequestMethod.POST)
	@ResponseBody
	public EnterpriseSearchData processFilterDetails(
			@RequestBody FetchFilterDetails fetchFilterDetails) {
		return customerProcessFilterService.processFilterDetails(fetchFilterDetails);
	}
	
	@RequestMapping(value = "fetchCustomerList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCustomerList(@RequestBody CustomerListRequest customerListRequest) {
		return customerListService.getCustomerList(customerListRequest);
	}
}