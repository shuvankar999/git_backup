package com.tip.estimation.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tip.estimation.model.GenerateInvoiceRequest;
import com.tip.estimation.model.GeneratedInvoiceDetials;
import com.tip.estimation.model.GetDocumentRequest;

@Component
public class RestCallUtil {
	
	@Value("${restcall.url.generateInv}")
	private String generateInvUrl;
	
	@Value("${restcall.url.getInv}")
	private String getDoc;
	
	@Value("${restcall.url.dev}")
	private String devUrl;
	
	static final Logger logger = LoggerFactory.getLogger(RestCallUtil.class);
	
	public void generateInvoice(GenerateInvoiceRequest giRequest) {
		RestTemplate rt = new RestTemplate();
        //rt.getMessageConverters().add(new StringHttpMessageConverter());
		GeneratedInvoiceDetials response1 = new GeneratedInvoiceDetials();
		//HttpHeaders headers = new HttpHeaders();
		//headers.setContentType(MediaType.APPLICATION_JSON);
		//response1 = rt.postForObject(generateInvUrl, giRequest, GeneratedInvoiceDetials.class);
		logger.info("Calling RESTful API.."+generateInvUrl);
/*		try {
			System.out.println("Response:-------"+new ObjectMapper().writeValueAsString(rt.postForObject(generateInvUrl, giRequest, GeneratedInvoiceDetials.class)));
		} catch (RestClientException | JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		response1 = rt.postForObject(generateInvUrl, giRequest, GeneratedInvoiceDetials.class);
		/*HttpEntity requestEntity = new HttpEntity(giRequest,headers);
		ResponseEntity<GeneratedInvoiceDetials> response = rt.exchange(generateInvUrl, HttpMethod.POST, requestEntity, GeneratedInvoiceDetials.class);*/
		try {
			
			logger.info("Request Object..."+new ObjectMapper().writeValueAsString(giRequest));
			logger.info("Response Object..."+ new ObjectMapper().writeValueAsString(response1));
		} catch (JsonProcessingException e) {
			logger.error("An error occurred"+e);
			e.printStackTrace();
		}
/*		GetDocumentRequest pdfRequest = new GetDocumentRequest();
		pdfRequest.setAppCd(giRequest.getAppCd());
		pdfRequest.setDocumentId(response1.getDocId());
		pdfRequest.setDocumentName(response1.getDocName());
		getPdf(pdfRequest);*/
	}
	
	
	private void getPdf(GetDocumentRequest pdfRequest) {
		RestTemplate restTemplate = new RestTemplate();
		/*restTemplate.getMessageConverters().add(
	            new ByteArrayHttpMessageConverter());

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_PDF);
	    headers.setConte
	    //headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM_VALUE));

	    HttpEntity<String> entity = new HttpEntity<String>(headers);
	    
	   // ResponseEntity<byte[]> response = 
	    		restTemplate.exchange(devUrl+getDoc,HttpMethod.POST, entity, byte[].class, pdfRequest);*/
		
		
		ByteArrayHttpMessageConverter byteArrayHttpMessageConverter = new ByteArrayHttpMessageConverter();

		List<MediaType> supportedApplicationTypes = new ArrayList<MediaType>();
		MediaType pdfApplication = new MediaType("application","pdf");
		supportedApplicationTypes.add(pdfApplication);

		byteArrayHttpMessageConverter.setSupportedMediaTypes(supportedApplicationTypes);
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(byteArrayHttpMessageConverter);
		restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(messageConverters);

		Object result = restTemplate.postForObject(devUrl+getDoc, pdfRequest, Object.class);
		byte[] resultByteArr = (byte[])result;
	}
}
