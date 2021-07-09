package com.tip.estimationmailreport.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.estimationmailreport.model.EstnEmailReportRequest;
import com.tip.estimationmailreport.repository.EstnEmailReportRepository;
import com.tip.estimationmailreport.service.EstnEmailReportService;
import com.tip.estimationmailreport.util.MailUtil;


	@Service
	@Transactional
	public class EstnEmailReportServiceImpl implements EstnEmailReportService{
		
		static final Logger logger = LoggerFactory.getLogger(EstnEmailReportServiceImpl.class);

		@Autowired
		EstnEmailReportRepository estnEmailReportRepository;

		@Autowired
		MailUtil mailUtil;
		
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public Map<String, Object> sendEmail(EstnEmailReportRequest estnEmailReportRequest) {
			String msg = "FAILURE";
			Map<String, Object> msgMap = new HashMap<>(0);
			Map<String, Object> returnMap = estnEmailReportRepository.sendEmail(estnEmailReportRequest);

			List<Object> emailDetailslist = (List<Object>) returnMap.get("emailDetailsMap");
			try {
				Iterator emailDetailslistIterator = emailDetailslist.iterator();
				while (emailDetailslistIterator.hasNext()) {
					Map<String, Object> emailDetailsMap = (Map<String, Object>) emailDetailslistIterator.next();
					mailUtil.setMailHost((String) emailDetailsMap.get("smtp"));

					mailUtil.setMailFrom((String) emailDetailsMap.get("fromAdd"));

					List<String> mailTo = new ArrayList<>(0);
					if (estnEmailReportRequest.getEmailTo().contains(",")) {
						addMailTo(estnEmailReportRequest,mailTo);
					} else {
						mailTo.add(estnEmailReportRequest.getEmailTo());
					}
					mailUtil.setMailTo(mailTo);
					mailUtil.setMailSubject(estnEmailReportRequest.getEmailSubject());

					mailUtil.setMailBody(estnEmailReportRequest.getEmailBody());

					String pdfLoc = (String) emailDetailsMap.get("pdfLocation");
					String version = (estnEmailReportRequest.getVersion()==null) ? "_1" : "_"+String.valueOf(estnEmailReportRequest.getVersion());
					String supplementary = estnEmailReportRequest.getSupplementary()==null ? "_0" : "_"+String.valueOf(estnEmailReportRequest.getSupplementary());
					mailUtil.setFileAttachmentPath(pdfLoc+version+supplementary+".pdf");
					mailUtil.setUserName((String) emailDetailsMap.get("userName"));
					mailUtil.setPassword((String) emailDetailsMap.get("password"));
				}
				msg = mailUtil.sendEmail();
			} catch (Exception e) {
				logger.error("An error occurred while mailing Estimation Report: " + e);
			}
			msgMap.put("Error_Cd", msg);
			return msgMap;
		}
		
		public void addMailTo(EstnEmailReportRequest estnEmailReportRequest, List<String> mailTo){
			String[] toArr = estnEmailReportRequest.getEmailTo().split(",");
			for (String to : toArr) {
				mailTo.add(to);
			}
		}
		
		
	
	}
