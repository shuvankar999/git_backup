package com.tip.fetchworkorder.model;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class WorkOrderTest {
	private WorkOrder workOrder;
	@Before
	public void setUp() throws Exception { workOrder = new WorkOrder();
	
	workOrder.setActualTime(5d);
	workOrder.setAlert("aaa");
	workOrder.setDiff(5d);
	workOrder.setGroupDesc("aaa");
	workOrder.setGuideTime(5d);
	workOrder.setLevel1("aaa");
	workOrder.setPaymentVendorCurrencyCd("aaa");
	workOrder.setPaymentVendorId(5);
	workOrder.setPurchaseOrderNr("aaa");
	workOrder.setPurchaseOrderNr("aaa");
	workOrder.setReason("aaa");
	workOrder.setReasonCd(5);
	workOrder.setSupplierId(5);
	workOrder.setSupplierName("aaa");
	workOrder.setValidationStatus("aaa");
	workOrder.setWorkorderComments("aaa");
	workOrder.setWorkOrderNr(5);
	workOrder.setWorkPackNr(BigDecimal.ONE);
	workOrder.setWoStatus(5);
	workOrder.setWoStatusDesc("aaa");
	workOrder.setWotCnt(5);
	
	
	
	}
	@Test
	public void test() {
	
	}

}