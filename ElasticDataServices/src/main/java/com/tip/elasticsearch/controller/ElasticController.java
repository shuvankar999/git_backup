package com.tip.elasticsearch.controller;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.asset.model.ArrayOfAssetData;
import com.tip.asset.model.ElasticSearchRequest;
import com.tip.asset.service.AssetDataService;
import com.tip.asset.service.ThirdPartyAssetDataService;
import com.tip.customer.service.CustomerDataService;
import com.tip.download.assetdetail.DownloadAssetDetail;
import com.tip.elastic.util.ElasticSearchConstant;
import com.tip.elasticsearch.controller.helper.ElasticControllerHelper;
import com.tip.elasticsearch.model.EnterpriseSearchData;
import com.tip.equipmentdetails.model.DownloadAssetDetailResponse;
import com.tip.equipmentdetails.model.DownloadAssetDetailsRequest;
import com.tip.equipmentdetails.service.SaveAndProcessFilterService;
import com.tip.estimation.service.EstimationDataService;
import com.tip.supplier.service.SupplierDataService;
import com.tip.units.service.UnitsDataService;

/**
 * Created by 398072
 */
@RestController
@RequestMapping("/service/elastic-data-service/1.0/")

public class ElasticController {
	
	@Autowired
	ElasticControllerHelper elasticControllerHelper;
	
	@Autowired
	DownloadAssetDetail downloadAssetDetail;
	
	@Autowired
	UnitsDataService unitsDataService;

    @Autowired
    AssetDataService assetDataService;
    
    @Autowired
    ThirdPartyAssetDataService thirdPartyAssetDataService;
    
    @Autowired
    CustomerDataService customerDataService;
    
    @Autowired
    EstimationDataService estimationDataService;
    
    @Autowired
    SupplierDataService supplierDataService;
    
    @Autowired
    HttpServletRequest servletRequest;
    
    @Autowired
    SaveAndProcessFilterService saveAndProcessFilterService;
    
    public static final Logger logger = LoggerFactory.getLogger(ElasticController.class);

    @RequestMapping(value = "getElasticSearchData", method = RequestMethod.POST)
    @ResponseBody
    public EnterpriseSearchData getElasticSearchData(@RequestBody ElasticSearchRequest elasticSearchRequest) {
    	EnterpriseSearchData lEnterpriseSearchData = new EnterpriseSearchData();
        try {
			if(elasticSearchRequest.getScreenKey().equalsIgnoreCase(ElasticSearchConstant.SCREEN_KEY_COUNT))
			{
				Long assetCount = assetDataService.assetDataTextSearchCount(elasticSearchRequest.getSearchText());
				
				//Long tpCount = thirdPartyAssetDataService.tpAssetDataTextSearchCount(elasticSearchRequest.getSearchText());
				logger.info("asset & tpasset count: "+assetCount);
				//logger.info("tp count: "+tpCount);
				//lEnterpriseSearchData.setAssetCount(assetCount + tpCount);
				lEnterpriseSearchData.setAssetCount(assetCount);
    			lEnterpriseSearchData.setCustomerCount(customerDataService.customerDataTextSearchCount(elasticSearchRequest.getSearchText()));
  
    			lEnterpriseSearchData.setEstimationCount(estimationDataService.estimationDataTextSearchCount(elasticSearchRequest.getSearchText()));
    			lEnterpriseSearchData.setSupplierCount(supplierDataService.supplierDataTextSearchCount(elasticSearchRequest.getSearchText()));
			}
			else
			{
				getDetailsForTextSearch(elasticSearchRequest, lEnterpriseSearchData);
			}
        } catch (UnknownHostException e) {
        	lEnterpriseSearchData.setErrorMsg("Exception Occured In Enterprise Search");
			logger.error(lEnterpriseSearchData.getErrorMsg(), e);
        }
        return lEnterpriseSearchData;
    }
    
    public void getDetailsForTextSearch(ElasticSearchRequest elasticSearchRequest,	EnterpriseSearchData lEnterpriseSearchData) throws UnknownHostException
    {
    	if(elasticSearchRequest.getEntity().equalsIgnoreCase(ElasticSearchConstant.ENTITY_TYPE_ASSET) &&
				elasticSearchRequest.getScreenName().equalsIgnoreCase(ElasticSearchConstant.ASSET_SCREEN_SEARCH))
		{    					
			getAssetDetails(elasticSearchRequest,lEnterpriseSearchData);
		}
    	else if(elasticSearchRequest.getEntity().equalsIgnoreCase(ElasticSearchConstant.ENTITY_TYPE_ASSET) &&
				elasticSearchRequest.getScreenName().equalsIgnoreCase(ElasticSearchConstant.ASSET_DASHBORAD_ON_BASIS_OF_BRANCH_ID))
		{    					
    		getAssetForDashboard(elasticSearchRequest,lEnterpriseSearchData);
		}
		else if(elasticSearchRequest.getEntity().equalsIgnoreCase(ElasticSearchConstant.ENTITY_TYPE_CUSTOMER) &&
				elasticSearchRequest.getScreenName().equalsIgnoreCase(ElasticSearchConstant.CUSTOMER_SCREEN_SEARCH))
		{
			if(!elasticSearchRequest.getSearchText().isEmpty()) {
				lEnterpriseSearchData.setlArrayOfCustomerData(customerDataService.customerDataTextSearch(elasticSearchRequest.getSearchText()));
			}else {
				lEnterpriseSearchData.setlArrayOfCustomerData(customerDataService.customerDataDefaultSearch(servletRequest.getHeader("ssoId")));
			}
		}
    }

	public void getAssetDetails(ElasticSearchRequest elasticSearchRequest,	EnterpriseSearchData lEnterpriseSearchData) throws UnknownHostException
    {
    	ArrayOfAssetData lArrayOfAssetData = new ArrayOfAssetData();
		ArrayOfAssetData lArrayOfTipAssetData = assetDataService.assetDataTextSearch(elasticSearchRequest.getSearchText());
		ArrayOfAssetData lArrayOfTPAssetData = thirdPartyAssetDataService.tpAssetDataTextSearch(elasticSearchRequest.getSearchText());
		lArrayOfAssetData.getAssetDataItem().addAll(lArrayOfTipAssetData.getAssetDataItem());
		lArrayOfAssetData.getAssetDataItem().addAll(lArrayOfTPAssetData.getAssetDataItem());
		lArrayOfAssetData.setCount(lArrayOfTipAssetData.getCount() + lArrayOfTPAssetData.getCount());
		lEnterpriseSearchData.setlArrayOfAssetData(lArrayOfAssetData);
    }
	
	public void getAssetForDashboard(ElasticSearchRequest elasticSearchRequest,	EnterpriseSearchData lEnterpriseSearchData) throws UnknownHostException
    {
		ArrayOfAssetData lArrayOfAssetData = new ArrayOfAssetData();
    	HashMap<String, String> fields = (HashMap<String, String>) elasticControllerHelper.setBranchIdFilter(elasticSearchRequest.getSearchText());
    	ArrayOfAssetData lArrayOfTipAssetData = assetDataService.getPaginatedAssetData(fields,60,40);
		//ArrayOfAssetData lArrayOfTPAssetData = thirdPartyAssetDataService.getTpAssetData(fields);
		lArrayOfAssetData.getAssetDataItem().addAll(lArrayOfTipAssetData.getAssetDataItem());
		//lArrayOfAssetData.getAssetDataItem().addAll(lArrayOfTPAssetData.getAssetDataItem());
		//lArrayOfAssetData.setCount(lArrayOfTipAssetData.getCount() + lArrayOfTPAssetData.getCount());
		lEnterpriseSearchData.setlArrayOfAssetData(lArrayOfAssetData);
    }
	
	@RequestMapping(value = "downloadAssetDetails", method = RequestMethod.POST, produces = "application/vnd.ms-excel")
    @ResponseBody
    public void downloadAssetDetails(@RequestBody DownloadAssetDetailsRequest downloadAssetDetailsRequest, HttpServletResponse response) {
    	try {
    			ArrayOfAssetData finalArrayOfAssetData = new ArrayOfAssetData();
    			//List<Map<String, String>> fieldList = elasticControllerHelper.setFilterForDownloadAsset(downloadAssetDetailsRequest.getUnitNrList());
    			if(!downloadAssetDetailsRequest.isSelectAllFlag()) {
	    			ArrayOfAssetData arrayOfAssetData = assetDataService.getAssetDetailForExcel(downloadAssetDetailsRequest);
	    			ArrayOfAssetData tpArrayOfAssetData = thirdPartyAssetDataService.getTpAssetDetailForExcel(downloadAssetDetailsRequest);
	    			finalArrayOfAssetData.getAssetDataItem().addAll(arrayOfAssetData.getAssetDataItem());
	    			finalArrayOfAssetData.getAssetDataItem().addAll(tpArrayOfAssetData.getAssetDataItem());
    			} else {
    				EnterpriseSearchData enterpriseSearchData = saveAndProcessFilterService.processFilterDetails(downloadAssetDetailsRequest.getFetchFilterDetails(),false);
    				finalArrayOfAssetData.getAssetDataItem().addAll(enterpriseSearchData.getlArrayOfAssetData().getAssetDataItem());
    			}
    			
    			DownloadAssetDetailResponse responseObject = downloadAssetDetail.writeassetDetailsToExcel(finalArrayOfAssetData,downloadAssetDetailsRequest);
    			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    			response.setHeader("Content-disposition", "attachment; filename=" + responseObject.getFile().getName());
       			IOUtils.copy(responseObject.getInputStream(), response.getOutputStream());

	            responseObject.getOutputStream().close();
    			responseObject.getInputStream().close();
    			response.getOutputStream().close();
        } catch (IOException e) {
        	logger.error("Error Encountered In Download Asset Details" , e);
        }
    }
}