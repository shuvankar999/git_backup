package com.tip.supplier.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.supplier.model.FetchJobRequest;
import com.tip.supplier.model.FetchJobResponse;
import com.tip.supplier.model.JobResponseObjects;
import com.tip.supplier.model.SubTaskSupplier;
import com.tip.supplier.repository.FetchCustomisedJobRepository;
import com.tip.supplier.service.FetchCustomisedJobService;

@Service
@Transactional
public class FetchCustomisedJobServiceImpl implements FetchCustomisedJobService {

	@Autowired
	FetchCustomisedJobRepository fetchCustomisedJobRepository;

	Set<String> jobNames;

	@Override
	public FetchJobResponse fetchCustomisedJob(FetchJobRequest fetchJobRequest) {
		jobNames = new HashSet<>();
		FetchJobResponse fetchJobResponse = new FetchJobResponse();

		Map<String, Object> resultMap = fetchCustomisedJobRepository.fetchCustomisedJob(fetchJobRequest);
		if (null != resultMap) {
			for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
				if ("#result-set-1".equalsIgnoreCase(entry.getKey())) {
					List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
					setSupplierCustomisedJobData(lst, fetchJobResponse);
				}
			}
			fetchJobResponse.getJobResponseList();

		}
		return fetchJobResponse;
	}

	private void setSupplierCustomisedJobData(List<Map<String, Object>> lst, FetchJobResponse fetchJobResponse) {
		List<JobResponseObjects> jobList = new ArrayList<>();
		for (int i = 0; i < lst.size(); i++) {
			Map<String, Object> jobMap = lst.get(i);
			String jobname = (String) jobMap.get("jobName");
			fetchJobResponse.setSupplierId((Integer) jobMap.get("supplierId"));
			SubTaskSupplier subTask = new SubTaskSupplier();
			subTask.setDnaCode((String) jobMap.get("dnaCode"));
			subTask.setGroups((String) jobMap.get("groups"));
			subTask.setSubGroup((String) jobMap.get("subGroup"));
			subTask.setActivity((String) jobMap.get("activity"));
			subTask.setMaintActionCd((String) jobMap.get("maintActionCd"));
			subTask.setActionDesc((String) jobMap.get("actionDesc"));
			
			subTask.setManufacturerId((String) jobMap.get("manufacturerId"));
			subTask.setManufacturer((String) jobMap.get("manufacturer"));
			subTask.setOePartNr((String) jobMap.get("oePartNr"));
			subTask.setSupplierPartNr((String) jobMap.get("supplierPartNr"));
			subTask.setPartsDescription((String) jobMap.get("Parts_Description"));

			if (jobNames.contains(jobname)) {
				addCustomerExistingJob(jobList, jobname, subTask);
			} else {
				JobResponseObjects job = new JobResponseObjects();
				
				if(jobMap.get("fee") != null) {
					BigDecimal bd = (BigDecimal)jobMap.get("fee");
				    bd = bd.setScale(2, RoundingMode.DOWN);
				    job.setFee(bd.toString());
				}
				job.setJobName(jobname);

				job.getSupplierSubTaskList().add(subTask);
				jobList.add(job);
				job.setIsActive((String) jobMap.get("isActive"));
				job.setIsEnabled((String) jobMap.get("isEnabled"));
				jobNames.add(jobname);
			}
		}
		fetchJobResponse.getJobResponseList().addAll(jobList);
	}

	public void addCustomerExistingJob(List<JobResponseObjects> jobList, String jobname, SubTaskSupplier subTask) {
		for (int j = 0; j < jobList.size(); j++) {
			if (jobList.get(j).getJobName().equalsIgnoreCase(jobname)) {
				jobList.get(j).getSupplierSubTaskList().add(subTask);
				break;
			}
		}
	}

}