package com.tip.fetchworkorder.model;



import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;


public class AddTimeObjectTest {
	private AddTimeObject addTimeObject;
	@Before
	public void setUp() throws Exception { addTimeObject = new AddTimeObject();
	addTimeObject.setAddTimeComments("aa");
	addTimeObject.setAddTimeReason("ss");
	addTimeObject.setAddTimeReasonCd(5);
	addTimeObject.setTimeAdded(5d);
	addTimeObject.setWorkOrderNr(5);
	addTimeObject.setWorkOrderTaskNr(5);
	addTimeObject.setWorkPackNr(BigDecimal.ONE);
	
	}
	@Test
	public void test() {
	
	}

}