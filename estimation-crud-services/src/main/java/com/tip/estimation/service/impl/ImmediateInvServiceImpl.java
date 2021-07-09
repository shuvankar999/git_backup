package com.tip.estimation.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.estimation.model.Consumables;
import com.tip.estimation.model.InvoiceBundleData;
import com.tip.estimation.model.InvoiceRequest;
import com.tip.estimation.model.InvoiceTyreService;
import com.tip.estimation.model.Part;
import com.tip.estimation.model.RebillDetails;
import com.tip.estimation.model.Task;
import com.tip.estimation.model.Tyre;
import com.tip.estimation.model.Workorder;
import com.tip.estimation.repository.ImmediateInvBundleRepository;
import com.tip.estimation.repository.ImmediateInvConsumablesRepository;
import com.tip.estimation.repository.ImmediateInvGenerateRepository;
import com.tip.estimation.repository.ImmediateInvHeaderRepository;
import com.tip.estimation.repository.ImmediateInvLoRepository;
import com.tip.estimation.repository.ImmediateInvPartRepository;
import com.tip.estimation.repository.ImmediateInvRebillRepository;
import com.tip.estimation.repository.ImmediateInvTyreRepository;
import com.tip.estimation.service.ImmediateInvService;


@Service
public class ImmediateInvServiceImpl implements ImmediateInvService{
	
	static final Logger logger = LoggerFactory.getLogger(ImmediateInvServiceImpl.class);
	
	@Autowired
	ImmediateInvHeaderRepository immediateInvHeaderRepository;
	
	@Autowired
	ImmediateInvLoRepository immediateInvLoRepository;
	
	@Autowired
	ImmediateInvPartRepository immediateInvPartRepository;
	
	@Autowired
	ImmediateInvTyreRepository immediateInvTyreRepository;
	
	@Autowired
	ImmediateInvBundleRepository immediateInvBundleRepository;
	
	@Autowired
	ImmediateInvConsumablesRepository immediateInvConsumablesRepository;
	
	@Autowired
	ImmediateInvRebillRepository immediateInvRebillRepository;
	
	@Autowired
	ImmediateInvGenerateRepository immediateInvGenerateRepository;
	
	@Override
	public Map<String, Object> saveRebillData(InvoiceRequest invoiceRequest) {
		RebillDetails rebillDetails =  immediateInvHeaderRepository.saveRebillHeader(invoiceRequest.getHeaderData());
		Map<String, Object> mapObjResponse = null;
		Map<String, Object> mapObjReturn = null;
		if(rebillDetails.getRebillNr()!=null){
			if(saveLabour(invoiceRequest, rebillDetails));
				mapObjResponse = immediateInvRebillRepository.saveRebillSave(rebillDetails);
		}
		
		switch ((Integer)mapObjResponse.get("ReturnVal")) {
		case 1:
			mapObjReturn = immediateInvGenerateRepository.generateInv(rebillDetails);
		default:
			break;
		}
		return mapObjReturn;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private boolean saveLabour(InvoiceRequest invoiceRequest, RebillDetails rebillDetails){
		List<Workorder> listOfLabour = new ArrayList();
		listOfLabour.addAll(invoiceRequest.getRegular());
		listOfLabour.addAll(invoiceRequest.getAdditional());
		List<Part> listOfParts = new ArrayList();
		List<Task> listOfTasks = new ArrayList();
		List<Tyre> listOfTyreSpecs = new ArrayList();
		List<InvoiceTyreService> listOfTyreServices = new ArrayList();
		List<InvoiceBundleData> listOfBundle = new ArrayList();
		List<Consumables> listOfConsumable = new ArrayList();
		//List<Integer> totalUpdateCount = new ArrayList();
		Integer totalUpdateCount = 0;
		for (Workorder labour : listOfLabour) {
			if (labour.getType().equalsIgnoreCase("Bundle")) {
				InvoiceBundleData bundleData = new InvoiceBundleData();
				bundleData.setBundleName(labour.getEstimationDetails());
				bundleData.setFee(labour.getActualCost());
				listOfBundle.add(bundleData);
			} else {
				addTask(listOfTasks, labour);
				addParts(listOfParts, labour);
				addTyres(listOfTyreSpecs, listOfTyreServices, labour);
				addConsumables(listOfConsumable, labour);
			}
		}
		if(!listOfTasks.isEmpty())
			totalUpdateCount = totalUpdateCount+Arrays.stream(immediateInvLoRepository.saveRebillLineLo(listOfTasks, rebillDetails)).sum();
		if(!listOfParts.isEmpty())
			totalUpdateCount = totalUpdateCount+Arrays.stream(immediateInvPartRepository.saveRebillParts(listOfParts, rebillDetails)).sum();
		if(!listOfTyreSpecs.isEmpty())
			totalUpdateCount = totalUpdateCount+Arrays.stream(immediateInvTyreRepository.saveRebillTyreSpecs(listOfTyreSpecs, rebillDetails)).sum();
		if(!listOfTyreServices.isEmpty())
			totalUpdateCount = totalUpdateCount+Arrays.stream(immediateInvTyreRepository.saveRebillTyreServices(listOfTyreServices, rebillDetails)).sum();
		if(!listOfBundle.isEmpty())
			totalUpdateCount = totalUpdateCount+Arrays.stream(immediateInvBundleRepository.saveRebillBundle(listOfBundle, rebillDetails)).sum();
		if(!listOfConsumable.isEmpty())
			totalUpdateCount = totalUpdateCount+Arrays.stream(immediateInvConsumablesRepository.saveRebillConsumables(listOfConsumable, rebillDetails)).sum();
		
		Integer objectCount = listOfTasks.size()+listOfParts.size()+listOfTyreSpecs.size()+listOfTyreServices.size()+listOfBundle.size()+listOfConsumable.size();
		if(totalUpdateCount>objectCount)
			return true;
		else
			return false;
	}

	private void addConsumables(List<Consumables> listOfConsumable, Workorder labour) {
		if(!labour.getConsumablesList().isEmpty())
			listOfConsumable.addAll(labour.getConsumablesList());
	}
	
	private void addTyres(List<Tyre> listOfTyreSpecs, List<InvoiceTyreService> listOfTyreServices, Workorder labour) {
		if(!labour.getTyres().isEmpty())
			listOfTyreSpecs.addAll(labour.getTyres());
		if(!labour.getTyreServices().isEmpty())
			listOfTyreServices.addAll(labour.getTyreServices());
	}

	private void addParts(List<Part> listOfParts, Workorder labour) {
		if(!labour.getParts().isEmpty())
			listOfParts.addAll(labour.getParts());
	}

	private void addTask(List<Task> listOfTasks, Workorder labour) {
		if(!labour.getTasks().isEmpty())
			listOfTasks.addAll(labour.getTasks());
	}

}
