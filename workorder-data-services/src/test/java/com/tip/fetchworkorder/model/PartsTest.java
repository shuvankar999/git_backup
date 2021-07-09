package com.tip.fetchworkorder.model;


import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class PartsTest {

	private Parts parts;
	@Before
	public void setUp() throws Exception { parts = new Parts();
	parts.setCostCd("aa");
	parts.setCostPrice(BigDecimal.TEN);
	parts.setCostSubCd("aa");
	parts.setDiscount(BigDecimal.TEN);
	parts.setListPrice(BigDecimal.TEN);
	parts.setOePartNr("aa");
	parts.setPartNr("aa");
	parts.setPartsQty(5);
	parts.setPartStatus("aa");
	parts.setPositionCd("aa");
	parts.setReason("aa");
	parts.setRepairId("aa");
	parts.setSupplierPartNr("aa");
	parts.setTipPartNr("aa");
	parts.setWorkOrderNr(5);
	parts.setWorkOrderTaskNr(5);
	parts.setWorkPackNr(BigDecimal.TEN);
	}
	@Test
	public void test() {
	
	}

}