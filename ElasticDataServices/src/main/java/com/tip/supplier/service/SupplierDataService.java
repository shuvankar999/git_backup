package com.tip.supplier.service;

import java.net.UnknownHostException;

import com.tip.supplier.model.ArrayOfSupplierData;
import com.tip.supplier.model.MasterdataRequest;

public interface SupplierDataService {
	
	public Long supplierDataTextSearchCount(String text) throws UnknownHostException;
	public ArrayOfSupplierData getSupplierMasterdata(MasterdataRequest masterdataRequest);

}
