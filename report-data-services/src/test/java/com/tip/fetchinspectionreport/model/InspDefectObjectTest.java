package com.tip.fetchinspectionreport.model;

import org.junit.Before;
import org.junit.Test;

public class InspDefectObjectTest {
	private InspDefectObject inspDefectObject;
	@Before
	public void setUp() throws Exception { inspDefectObject = new InspDefectObject();
	inspDefectObject.setAction("abc");
	inspDefectObject.setActionBy("abc");
	inspDefectObject.setDefect("abc");
	inspDefectObject.setNr("abc");
	
	inspDefectObject.getAction();
	inspDefectObject.getActionBy();
	inspDefectObject.getDefect();
	inspDefectObject.getNr();
}


	@Test
	public void test() {
	
	}

}
