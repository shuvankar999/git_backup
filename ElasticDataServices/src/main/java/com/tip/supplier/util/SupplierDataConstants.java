package com.tip.supplier.util;

import java.util.Arrays;
import java.util.List;

public class SupplierDataConstants {
	
	public static final String VENDOR_STATUS_DESC_ACTIVE = "A";
	public static final String VENDOR_STATUS_DESC_VOID = "V";
	public static final String VENDOR_STATUS_DESC_DRAFT = "D";
	public static final String VENDOR_STATUS_DESC_ALL = "All";
	
	public static final List<Integer> VENDOR_STATUS_ALL_NUMERIC = Arrays.asList(41, 42, 43);
	public static final Integer VENDOR_STATUS_ACTIVE_NUMERIC = 42;
	public static final Integer VENDOR_STATUS_DRAFT_NUMERIC = 41;
	public static final Integer VENDOR_STATUS_VOID_NUMERIC = 43;

	private SupplierDataConstants() {
	}
	
}
