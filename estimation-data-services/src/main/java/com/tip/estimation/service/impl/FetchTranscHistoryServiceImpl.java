package com.tip.estimation.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.estimation.model.InvoiceRequest;
import com.tip.estimation.repository.FetchTranscHistoryRepository;
import com.tip.estimation.service.FetchTranscHistoryService;

@Service
@Transactional
public class FetchTranscHistoryServiceImpl implements FetchTranscHistoryService {
	
	@Autowired
	FetchTranscHistoryRepository fetchTranscHistoryRepository;

	@Override
	public Map<String, Object> getTransaction(InvoiceRequest invoiceRequest) {

		Map<String, Object> resultMap = fetchTranscHistoryRepository.getTransaction(invoiceRequest);
		Map<String, Object> returnMap = new HashMap();
		for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
			if (("#result-set-1").equalsIgnoreCase(entry.getKey())) {
				List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
				returnMap.put("TransactionList", lst);
			}
		}
		
		return returnMap;
	}

}
