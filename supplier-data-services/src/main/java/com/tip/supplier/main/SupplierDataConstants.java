package com.tip.supplier.main;

import java.util.Arrays;
import java.util.List;

public class SupplierDataConstants {
	
	public static final String PROC_FETCH_JOBS = "OPSmaint..ES_ws_fetch_supplier_cust_jobs";
	public static final String PROC_FEE_CARD_DETAILS = "OPSmaint..ES_ws_fetch_supplier_fee";
	public static final String PROC_FETCH_OTHER_FEES_CARD = "OPSmaint..ES_supplier_fetch_other_fees";
	public static final String PROC_FETCH_OPERATIONAL_DETAILS = "OPSmaint..ES_fetch_supplier_details";
	public static final String PROC_FETCH_PART_AGREEMENT_DETAILS = "OPSmaint..ES_ws_fetch_suppl_parts_dsct";
	public static final String PROC_FETCH_ACCOUNT_PAYABLES_DETAILS = "OPSmaint..ES_fetch_account_payables_Data";
	public static final String PROC_FETCH_ALL_PROCEDURES = "TIPism..ES_mds_key_procs";
	
	public static final String VENDOR_STATUS_DESC_ACTIVE = "A";
	public static final String VENDOR_STATUS_DESC_VOID = "V";
	public static final String VENDOR_STATUS_DESC_DRAFT = "D";
	public static final String VENDOR_STATUS_DESC_ALL = "All";
	
	public static final List<Integer> VENDOR_STATUS_ALL_NUMERIC = Arrays.asList(41, 42, 43);
	public static final Integer VENDOR_STATUS_ACTIVE_NUMERIC = 42;
	public static final Integer VENDOR_STATUS_DRAFT_NUMERIC = 41;
	public static final Integer VENDOR_STATUS_VOID_NUMERIC = 43;
	public static final String PROC_FETCH_SUPPLIER_CONTACT_DETAILS = "OPSmaint..ES_fetch_supplier_Contacts";
	public static final String PROC_FETCH_LABOUR_RATES = "OPSmaint..ES_ws_fetch_suppl_labour_rates";
	public static final String PROC_FETCH_SUPPLIER_INSURANCE = "OPSmaint..ES_fetch_supplier_insurance";
	public static final String PROC_FETCH_SUPPLIER_MSU_DATA = "OPSmaint..ES_ws_fetch_suppl_MSU_details";
	public static final String PROC_FETCH_ALL_CAPABILITIES = "OPSmaint..ES_ws_fetch_supplier_wshp_cap";

	private SupplierDataConstants() {
	}
	
}
