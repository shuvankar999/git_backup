package com.tip.fetchinspectionreport.service.impl;

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

import com.tip.fetchinspectionreport.model.EmailReportRequest;
import com.tip.fetchinspectionreport.repository.EmailReportRepository;
import com.tip.fetchinspectionreport.service.EmailReportService;
import com.tip.report.util.MailUtil;

@Service
@Transactional
public class EmailReportServiceImpl implements EmailReportService {

	static final Logger logger = LoggerFactory.getLogger(EmailReportServiceImpl.class);

	@Autowired
	EmailReportRepository emailReportRepository;

	@Autowired
	MailUtil mailUtil;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> sendEmail(EmailReportRequest emailReportRequest) {
		String msg = "FAILURE";
		Map<String, Object> msgMap = new HashMap<>(0);
		Map<String, Object> returnMap = emailReportRepository.sendEmail(emailReportRequest);

		List<Object> emailDetailslist = (List<Object>) returnMap.get("emailDetailsMap");
		try {
			Iterator emailDetailslistIterator = emailDetailslist.iterator();
			while (emailDetailslistIterator.hasNext()) {
				Map<String, Object> emailDetailsMap = (Map<String, Object>) emailDetailslistIterator.next();
				mailUtil.setMailHost((String) emailDetailsMap.get("SMTP"));

				mailUtil.setMailFrom((String) emailDetailsMap.get("FromAdd"));

				List<String> mailTo = new ArrayList<>(0);
				if (emailReportRequest.getTo().contains(",")) {
					addMailTo(emailReportRequest,mailTo);
				} else {
					mailTo.add(emailReportRequest.getTo());
				}
				mailUtil.setMailTo(mailTo);
				mailUtil.setMailSubject(emailReportRequest.getSubject());

				mailUtil.setMailBody(emailReportRequest.getBody());

				mailUtil.setFileAttachmentPath((String) emailDetailsMap.get("PDFLocation"));
				mailUtil.setUserName((String) emailDetailsMap.get("UserName"));
				mailUtil.setPassword((String) emailDetailsMap.get("Password"));
			}
			msg = mailUtil.sendEmail();
		} catch (Exception e) {
			logger.error("An error occurred while mailing inspection report: " + e);
		}
		msgMap.put("Error_Cd", msg);
		return msgMap;
	}
	
	public void addMailTo(EmailReportRequest emailReportRequest, List<String> mailTo){
		String[] toArr = emailReportRequest.getTo().split(",");
		for (String to : toArr) {
			mailTo.add(to);
		}
	}
}
